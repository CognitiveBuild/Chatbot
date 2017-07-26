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

// ice cream
var flavours = [
	{
		id: 1,
		name: 'Chocolate', 
		price: 1.00
	}, 
	{
		id: 2,
		name: 'Vanilla', 
		price: 2.00
	}, 
	{
		id: 3,
		name: 'Coffee', 
		price: 3.00
	}, 
	{
		id: 4,
		name: 'Mango', 
		price: 8.00
	}, 
	{
		id: 5,
		name: 'Strawberry', 
		price: 2.00
	}, 
	{
		id: 6,
		name: 'Black sesame', 
		price: 5.00
	}, 
	{
		id: 7,
		name: 'Red beans', 
		price: 10.00
	}
], order = {
	flavour: null, 
	amount: 0, 
	location: null
}, serial_number = '', consigner_name = '', consigner_phone = '', storage = {
	save: function(key, val) {
		console.log('### save ###');
		window.localStorage.setItem(key, val);
	},
	get: function(key) {
		console.log('### get ###');
		return window.localStorage.getItem(key);
	},
	remove: function(key) {
		console.log('### remove ###');
		window.localStorage.removeItem(key);
	}
}, start_button_producing_text = 'Produce', start_button_loading_text = 'Please wait...';

// conversation variables
var conversation_result, is_wating = false, controls = {
	$signout: $('.heading-nav > .signout'),
	$sn: $('.input-sn'),
	$name: $('.input-name'),
	$phone: $('.input-phone'), 
	$go: $('.input-go'), 
	$flavour: $('.input-flavour'), 
	$qty: $('.input-qty'), 
	$address: $('.input-shipping-address'),
	$consignee: $('.input-consignee'),
	$start: $('.button-start'), 
	$price: $('.price-number'), 
	$verify: $('.button-verify'), 
	$jsonPanel: $('#json-panel .base--textarea')
}, methods = {
	sn: function() {
		controls.$go.on('click', function() {
			serial_number = controls.$sn.val();
			consigner_name = controls.$name.val();
			consigner_phone = controls.$phone.val();
			if(serial_number === '' || serial_number.length < 6) {
				controls.$sn.focus();
				return;
			}
			if(consigner_name === '' || consigner_name.length < 4) {
				controls.$name.focus();
				return;
			}
			if(consigner_phone === '' || consigner_phone.length < 4) {
				controls.$phone.focus();
				return;
			}
			storage.save('SN', serial_number);
			storage.save('NAME', consigner_name);
			storage.save('PHONE', consigner_phone);
			methods.start();
		});
	}, 
	start: function() {
		$('._container--sn').addClass('hidden');
		$('._demo--content').removeClass('hidden');
		controls.$signout.removeClass('hidden');
		if(GLOBAL_LAYOUT === 'conversation') {
			methods.chatbot();
		}
	}, 
	end: function() {
		$('._container--sn').removeClass('hidden');
		$('._demo--content').addClass('hidden');
		controls.$signout.addClass('hidden');
		storage.remove('SN');
		storage.remove('NAME');
		storage.remove('PHONE');
	}, 
	chatbot: function () {
		var $chatInput = $('.chat-window--message-input'),
		
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
		var contextToCommand = function(conversation_result) {

			var context = conversation_result.context;
			if(context) {
				if(context.flavour) {
					order.flavour = context.flavour;
					controls.$flavour.find('option[data-name="'+order.flavour+'"]').prop('selected', true).trigger('change');
				}

				if(context.amount) {
					order.amount = context.amount;
					controls.$qty.val(order.amount).trigger('change');
				}

				if(context.location) {
					order.location = context.location;
					controls.$address.val(order.location).trigger('change');
				}

				if(context.consignee) {
					order.consignee = context.consignee;
					controls.$consignee.val(order.consignee).trigger('change');
				}

				if(context.reset) {
					conversation_result.context.flavour = order.flavour = null;
					conversation_result.context.amount = order.amount = 0;
					conversation_result.context.location = order.location = null;
				}

				if(context.placeorder) {
					controls.$start.trigger('click');
				}
			}
		};

		/**
		 * Synthesize
		 */
		var synthesize = function(val) {
			
			var url = 'Synthesize?text=' + val;
			var source = document.createElement('source');
			source.type = 'audio/wav';
			source.src = url;
			var audio = new Audio();
			audio.appendChild(source);
			audio.load();
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

		        controls.$jsonPanel.html(JSON.stringify(conversation_result, null, 2));

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

		        contextToCommand(conversation_result);

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
				stream.on('close', function() {
					console.log('stream close');
					isSpeaking = false;
					changeUIState(isSpeaking);
				});
				stream.on('connection-close', function(err) {
					console.log('connection close');
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

		var getQty = function() {
			var qty = controls.$qty.val();
			return qty;
		}, sendRequest = function(category, payload) {
			console.log('### payload ###');
			console.log(payload);
			return $.ajax({
				url : 'Application',
				method: 'POST', 
				data : {
					payload: payload,
					category : category
				}
			});
		};

		var getIceCream = function() {

			var id = controls.$flavour.val();
			var opt = controls.$flavour.find('option:selected');
			var price = opt.data('price');
			var flavour = opt.data('name');
			var qty = getQty();

			var total = (qty * price).toFixed(2);
			var address = controls.$address.val();
			var consignee = controls.$consignee.val();
			
			

			var sn = storage.get('SN');

			return {
				name: 'Watson Ice Cream Delivery',
				consigner_name: consigner_name,
				consigner_phone: consigner_phone, 
				consigner_address: '-',
				serial: sn, 
				flavour: flavour,
				price: price,
				total: total, 
				consignee_name: consignee, 
				consignee_address: address, 
				consignee_phone: '+86 8888888888',
				timestamp: new Date().getTime(),
				// typo: should be `environment_limit`
				environment_limit: {
					temperature_low: -30, 
					temperature_high: 0, 
					humidity_low: 20, 
					humidity_high: 50
				}
			}
		};

		controls.$start.val(start_button_producing_text);
		controls.$start.on('click', function(evt) {
			console.log('### submit ###');
			var iceCream = getIceCream();
			
			controls.$jsonPanel.html(JSON.stringify(iceCream, null, 2));

			if(iceCream.consignee_address === '') {
				controls.$address.focus();
				return null;
			}

			if(iceCream.consignee_name === '') {
				controls.$consignee.focus();
				return null;
			}
			
			if(controls.$start.val() === start_button_producing_text) {
				controls.$start.val(start_button_loading_text);

				sendRequest('icecream', JSON.stringify(iceCream)).then(function(xhr, status, code) {
					console.log('### service response ###');
					console.log(xhr);
					var json = JSON.parse(xhr);
					console.log('### /service response ###');
					var obj = $('a[href="#vr-panel"]');
					obj.trigger('click');
					controls.$start.val(start_button_producing_text);
					
				}, function(error) {
					console.log('### error ###');
					console.log(error);
					controls.$start.val(startText);
					console.log('### /error ###');
				});
			}

		});

		controls.$qty.on('change', function(evt) {
			var iceCream = getIceCream();
			controls.$price.text(iceCream.total);
			controls.$jsonPanel.html(JSON.stringify(iceCream, null, 2));
		});

		controls.$flavour.on('change', function(evt) {
			var iceCream = getIceCream();
			controls.$price.text(iceCream.total);
			controls.$jsonPanel.html(JSON.stringify(iceCream, null, 2));
		});

		controls.$flavour.empty();
		for(var i in flavours) {
			var opt = $('<option></option>');
			opt.html(flavours[i].name+' ('+ flavours[i].price.toFixed(2) +')');
			opt.attr('data-price', flavours[i].price.toFixed(2)).attr('data-name', flavours[i].name);
			opt.attr('value', flavours[i].id);
			controls.$flavour.append(opt);
		}

		controls.$verify.on('click', function() {
			var form = $('.vr-controller');
			$('.verify-result .ui-message-failure').addClass('hidden');
			$('.verify-result .ui-message-succeed').addClass('hidden');
			$('.verify-result .ui-message-loading').removeClass('hidden');
			var data = new FormData();
			data.append("file", form.find('input[type="file"]')[0].files[0]);
			$.ajax({
				url: 'Application?category=vr', 
				method: 'POST', 
				data: data, 
				cache: false,
		        contentType: false,
		        processData: false,

		        xhr: function() {
		            var aXhr = $.ajaxSettings.xhr();
		            if (aXhr.upload) {
		            	aXhr.upload.addEventListener('progress', function(e) {
		                    if (e.lengthComputable) {
		                        $('progress').attr({ value: e.loaded, max: e.total });
		                    }
		                } , false);
		            }
		            return aXhr;
		        }, 
		        success: function(data) {
		        	controls.$jsonPanel.html(data);
		        	var response = JSON.parse(data);
		        	$('.verify-result .ui-message-loading').addClass('hidden');
		        	if(response.hasOwnProperty('images') && response.images.length > 0) {
		        		console.log(response.images);
		        		 if(response.images[0].hasOwnProperty('classifiers') && response.images[0].classifiers.length > 0) {
		        			 if(response.images[0].classifiers[0].hasOwnProperty('classes') && response.images[0].classifiers[0].classes.length > 0) {
		        				 var result = response.images[0].classifiers[0].classes[0];
		        				 var message = $('.verify-result .ui-message-succeed');
		        				 message.removeClass('hidden');
		        				 message.find('.score').text('Score: ' + result.score);
		        				 message.find('.class').text('Class: ' + result['class']);
		        				 return;
		        			 }
		        		 }
		        	}
   				 	$('.verify-result .ui-message-failure').removeClass('hidden');
		        }
			});
		});
		controls.$signout.on('click', function() {
			methods.end();
		});

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

	}, 
	init: function() {
		var sn = storage.get('SN');
		consigner_name = storage.get('NAME');
		consigner_phone = storage.get('PHONE');

		if(sn && consigner_name && consigner_phone) {
			methods.start();
			controls.$sn.val(sn);
			controls.$name.val(consigner_name);
			controls.$phone.val(consigner_phone);
		}
		else {
			methods.end();
		}
		methods.sn();
		methods.icecream();
	}
};

$(document).ready(methods.init);