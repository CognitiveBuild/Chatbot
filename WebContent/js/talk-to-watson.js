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
var conversation_result, 
is_wating = false, 
flavours = [
	{
		id: 1,
		name: 'Chocolate', 
		rate: 1.00
	}, 
	{
		id: 2,
		name: 'Vanilla', 
		rate: 2.00
	}, 
	{
		id: 3,
		name: 'Coffee', 
		rate: 3.00
	}, 
	{
		id: 4,
		name: 'Mango', 
		rate: 8.00
	}, 
	{
		id: 5,
		name: 'Strawberry', 
		rate: 2.00
	}, 
	{
		id: 6,
		name: 'Black sesame', 
		rate: 5.00
	}, 
	{
		id: 7,
		name: 'Red beans', 
		rate: 10.00
	}
], 
methods = {
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
					var transcript = data.results[0].alternatives[0].transcript;
					$('.ui-transcription').html('<div class="text">'+transcript+'</div>');

					if(data.results[0].hasOwnProperty('final') && data.results[0]['final']){
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
			var userAgent = window.navigator.userAgent.toLowerCase();
			if(userAgent.indexOf('chrome') != -1 || userAgent.indexOf('firefox') != -1){
				$mic.show();
			}
		});
		
		scrollToInput();

	}, 
	icecream: function() {

		var $flavour = $('.input-flavour');

		var getQty = function() {
			var qty = $('.input-qty').val();
			return qty;
		};

		var getIceCream = function() {
			var obj = $('.input-flavour');
			var id = obj.val();
			var opt = obj.find('option:selected');
			var rate = opt.data('rate');
			var name = opt.data('name');
			var price = (getQty() * rate).toFixed(2);
			var address = $('.input-shipping-address').val();

			return {
				name: name, 
				rate: rate, 
				price: price, 
				address: address
			}
		};

		$('.button-submit').on('click', function(evt) {
			console.log('### submit ###');
			var iceCream = getIceCream();
			console.log(iceCream);
		});

		$('.input-qty').on('change', function(evt) {
			var iceCream = getIceCream();
			$('.price-number').text(iceCream.price);
			console.log(iceCream);
		})

		$flavour.on('change', function(evt) {
			var iceCream = getIceCream();
			$('.price-number').text(iceCream.price);
			console.log(iceCream);
		});

		$flavour.empty();
		for(var i in flavours) {
			var opt = $('<option></option>');
			opt.html(flavours[i].name);
			opt.data('rate', flavours[i].rate).data('name', flavours[i].name);
			opt.attr('value', flavours[i].id);
			$flavour.append(opt);
		}
	}, 
	init: function() {
		methods.chatbot();
		methods.icecream();
	}
};

$(document).ready(methods.init);