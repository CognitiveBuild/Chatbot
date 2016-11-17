/**
 * Copyright 2016 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

'use strict';

// conversation variables
var conversation_result, is_wating = false, methods = {
	chatbot: function () {
		var $chatInput = $('.chat-window--message-input'),
		$jsonPanel = $('#json-panel .base--textarea'),
		$loading = $('.loader'), 
		$mic = $('.ui-button-microphone');

		$chatInput.keyup(function(event){
			if(event.keyCode === 13) {
				var inputText = $(this).val();
				converse(inputText);
			}
		});

		/**
		 * Convert response context to car command
		 */
		var contextToCommand = function(conversation_result){
			var direction = '';
			var entities = conversation_result.entities;
			var intents = conversation_result.intents;
			for(var index in intents){
				var intent = intents[index];
				if(intent.confidence > 0.7 && intent.intent === 'move'){
					for(var key in entities){
						var entity = entities[key];
						if(entity.entity === 'direction') {
							switch(entity.value) {
							case 'right':
								direction = 'd';
								break;
							case 'forward':
								direction = 'w';
								break;
							case 'backward':
								direction = 's';
								break;
							case 'left':
								direction = 'a';
								break;
							}
							break;
						}
					}
					break;
				}
			}
			return direction;
		};

		/**
		 * Synthesize
		 */
		var synthesize = function(val) {
			var audio = new Audio();
			audio.src = 'Synthesize?text=' + val;
			audio.play();
		};

		/**
		 * Perform conversation
		 */
		var converse = function(userText) {
			if (is_wating) {
				return;
			}
			is_wating = true;
		    $loading.show();
		    // $chatInput.hide();
		
		    // check if the user typed text or not
		    if (typeof(userText) !== undefined && $.trim(userText) !== '')
		      submitMessage(userText);
		
		    // build the conversation parameters
		    var params = { message : userText };
		
		    if (conversation_result) {
		    	params.context = JSON.stringify(conversation_result.context);
		    }

		    $.ajax({
		    	url:'Talk',
		    	method: 'POST',
		    	data: params
		    }).done(function onSucess(response) {
		    	is_wating = false;
		    	conversation_result = JSON.parse(response);

		        $chatInput.val(''); // clear the text input

		        $jsonPanel.html(JSON.stringify(conversation_result, null, 2));

		        var texts = conversation_result.output ? conversation_result.output.text : [];
		        if(texts.length == 0){
		        	talk('WATSON', 'Sorry, I did not understand what you just said.');
		        	return;
		        }
		        var response = texts.join('<br />'); // &lt;br/&gt; is <br/>

		        $chatInput.show();
		        $chatInput[0].focus();

		        synthesize(response);

		        talk('WATSON', response); // show

		        var command = contextToCommand(conversation_result);
		        onSendingCommand(command);
		      })
		      .fail(function onError(error) {
		        talk('WATSON', error.responseJSON ? error.responseJSON.error : error.statusText);
		        is_wating = false;
		      })
		      .always(function always(){
		        $loading.hide();
		        scrollChatToBottom();
		        $chatInput.focus();
		      });
		};

		/**
		 * Scroll with animation
		 */
		var scrollChatToBottom = function() {
			var element = $('.chat-box--pane');
			element.animate({
				scrollTop: element[0].scrollHeight
			}, 420);
		};

		/**
		 * Scroll to input
		 */
		var scrollToInput = function() {
			var element = $('.chat-window--message-input');
			$('body, html').animate({
				scrollTop: (element.offset().top - window.innerHeight + element[0].offsetHeight) + 20 + 'px'
			});
		};

		/**
		 * Generate HTML to chat
		 */
		var talk = function(origin, text, format) {
			if(!format){
				format = 'html';
			}
			var $chatBox = $('.chat-box--item_' + origin).first().clone();
			var $loading = $('.loader');
			if(format == 'text')
				$chatBox.find('p').html($('<p/>').html(text).text());
			else
				$chatBox.find('p').html($('<p/>').html(text).html());

			$chatBox.insertBefore($loading);
			setTimeout(function() {
				$chatBox.removeClass('chat-box--item_HIDDEN');
			}, 100);
		};

		/**
		 * Send message to the service
		 */
		var submitMessage = function(text) {
			talk('YOU', text);
			scrollChatToBottom();
			clearInput();
		};

		/**
		 * Clear contents in the input
		 */
		var clearInput = function() {
			$('.chat-window--message-input').val('');
		};

		/**
		 * Tab switching
		 */
		$('.tab-panels--tab').click(function(e){
			e.preventDefault();
			var self = $(this);
			var inputGroup = self.closest('.tab-panels');
			var idName = null;
			inputGroup.find('.active').removeClass('active');
			self.addClass('active');
			idName = self.attr('href');
			$(idName).addClass('active');
		});

		/**
		 * Speech recognition UI
		 */
		var changeUIState = function(isSpeaking) {
			if (isSpeaking) {
				$loading.show();
				$mic.addClass('active');
				$mic.val('Speaking...');
			} else {
				$loading.hide();
				$mic.removeClass('active');
				$mic.val('Speak');
			}
		};

		/**
		 * Inovking Speech recognition
		 */
		var recognize = function(token) {
			return WatsonSpeech.SpeechToText.recognizeMicrophone({
				token : token,
				objectMode : true
			});
		};

		/**
		 * Obtain token of Speech services
		 */
		var initToken = function(val) {
			return $.ajax({
				url : 'Token',
				data : {
					category : val
				}
			});
		};

		var isSpeaking = false, stream = null, ttsToken = '', sttToken = '';

		$mic.on('click', function(evt){
			if(isSpeaking && stream){
				stream.stop();
				isSpeaking = false;
				changeUIState(isSpeaking);
			}
			else {
				if(isSpeaking){
					return;
				}
	
				stream = recognize(sttToken);
				isSpeaking = true;
				changeUIState(isSpeaking);
	
				stream.on('data', function(data) {
					console.log('data:');
					console.log(data);
					var transcript = data.alternatives[0].transcript;
					$('.ui-transcription').html('<div class="text">'+transcript+'</div>');
	
					if(data.hasOwnProperty('final') && data['final']){
						isSpeaking = false;
						changeUIState(isSpeaking);
						stream.stop();
						converse(transcript);
						$('.ui-transcription').text('');
					}
				});
				stream.on('error', function(err) {
					console.log(err);
					isSpeaking = false;
					changeUIState(isSpeaking);
				});
				stream.on('close', function(err) {
					console.log(err);
					isSpeaking = false;
					changeUIState(isSpeaking);
				});
				stream.on('connection-close', function(err) {
					console.log(err);
					isSpeaking = false;
					changeUIState(isSpeaking);
				});
			}
		});

		initToken('stt').then(function(t) {
			sttToken = t;
			converse('Hi Watson');
			if(window.navigator.userAgent.toLowerCase().indexOf('safari') == -1)
				$mic.show();
		});
		scrollToInput();

		// car control
		var socket = io.connect(carServiceHost);

		socket.on("message", function (data) {
			console.log("socket data: " + data);
		});

		var onSendingCommand = function(command){
			if(command === '') {
				return;
			}
			console.log(command);
			socket.emit("String", command);
		};
		var keyToCommand = function(key){
			var direction = '';
			switch(key){
			case 'w':
			case 'ArrowUp':
				direction = 'w';
				break;
			case 'a':
			case 'ArrowLeft':
				direction = 'a';
				break;
			case 's':
			case 'ArrowDown':
				direction = 's';
				break;
			case 'd':
			case 'ArrowRight':
				direction = 'd';
				break;
			}
			return direction;
		};

		var keys = $('.car-controller a');
		keys.on('click', function(evt){
			var command = $(this).attr('ref');
			// call Socket-IO
			onSendingCommand(command);
		});

		$(document).on('keyup', function(evt){
			var command = keyToCommand(evt.key);
			onSendingCommand(command);
			keys.removeClass('active');
		});
		$(document).on('keydown', function(evt){
			var command = keyToCommand(evt.key);
			if(command === ''){
				return;
			}
			keys.parent().find('[ref='+command+']').addClass('active');
		});
	}
};

$(document).ready(methods.chatbot);