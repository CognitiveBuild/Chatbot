<!DOCTYPE html>
<html lang="en">
<head>
<title>Talk to Watson</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/style.css" />
<%
	String appUrl = com.ibm.cto.Configuration.getInstance().getAppURL();
%>
<script>
	var GLOBAL_LAYOUT = '<%=com.ibm.cto.Configuration.getInstance().getLayout() %>';
	var GLOBAL_APP_URL = '<%=appUrl %>';
</script>
</head>
<body class="<%=com.ibm.cto.Configuration.getInstance().getLayout() %>">
	<header class="_demo--heading">
		<div class="_demo--container">
			<a class="wordmark" href="index.jsp">
				<span class="wordmark--left">Talk to</span>
				<span class="wordmark--right">Watson</span>
			</a>
			
			<nav class="heading-nav">
				<a class="heading-nav--item chatbot signout hidden">
					Sign out
				</a>
			</nav>
		</div>
	</header>
	<div class="_demo--container">

		<div class="_container--sn">
			<form class="sn-form-container">
				<div class="sn-form-cell">
					<div class="sn-input"><label>Your serial number</label><input type="text" value="" placeholder="" class="input-sn" minlength="6" /></div>
					<div class="sn-input"><label>Your name</label><input type="text" value="" placeholder="" class="input-name" minlength="6" /></div>
					<div class="sn-input"><label>Your phone number</label><input type="text" value="" placeholder="" class="input-phone" minlength="6" /></div>
					<div class="sn-button"><input type="button" class="input-go" value="Go" /></div>
				</div>
			</form>
		</div>

		<article class="_demo--content base--article page hidden" ref="chatbot">
			<div class="_content--dialog">
				<div class="chat-window">
					<div class="chat-box">
						<div class="chat-box--pane">
							<div class="chat-box--item_WATSON chat-box--item chat-box--item_HIDDEN">
								<div class="chat-box--container">
									<div class="img-container chat-box--avatar chat-box--avatar_WATSON">
										<img src="images/icons/avatar-watson.svg" class="chat-box--avatar-img">
									</div>
									<div class="chat-box--message">
										<div class="chat-box--message-vertical">
											<p class="chat-box--message-text base--p"></p>
										</div>
									</div>
								</div>
							</div>
							<div class="chat-box--item_YOU chat-box--item chat-box--item_HIDDEN">
								<div class="chat-box--container">
									<div class="chat-box--message">
										<div class="chat-box--message-vertical">
											<p class="chat-box--message-text base--p"></p>
										</div>
									</div>
									<div class="img-container chat-box--avatar chat-box--avatar_YOU">
										<img src="images/icons/avatar-me.svg" class="chat-box--avatar-img" />
									</div>
								</div>
							</div>
							<!-- adding extra space hack -->
							<div class="loader">
								<div class="loader--fallback"><img src="images/watson.gif" width="50" alt=""></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="_content--data">
				<div class="tab-panels" role="tabpanel">
					<ul class="tab-panels--tab-list" role="tablist">
						<li class="tab-panels--tab-list-item base-li" role="presentation">
							<a class="tab-panels--tab base--a active" href="#control-panel" aria-controls="control" role="tab">Manufacture</a>
						</li>
						<li class="tab-panels--tab-list-item base-li" role="presentation">
							<a class="tab-panels--tab base--a" href="#simulator-panel" aria-controls="simulator" role="tab" class="">Simulator</a>
						</li>
						<li class="tab-panels--tab-list-item base-li" role="presentation">
							<a class="tab-panels--tab base--a" href="#vr-panel" aria-controls="vr" role="tab" class="">Verification</a>
						</li>
						<li class="tab-panels--tab-list-item base-li" role="presentation">
							<a class="tab-panels--tab base--a" href="#json-panel" aria-controls="vr" role="tab">JSON</a>
						</li>
					</ul>
					<div class="tab-panels--tab-content">
						<div id="json-panel" class="tab-panels--tab-pane" role="tab-panel">
							<textarea class="base--textarea"></textarea>
						</div>
						<div id="control-panel" class="tab-panels--tab-pane active" role="tab-panel">
							<form class="icecream-controller">
								<h2>Watson Ice Cream Manufacture</h2>

								<div class="form-ice-cream">
									<div class="form-item form-item-inline">
										<label>Qty:</label>
										<div>
											<input type="number" name="qty" placeholder="Qty" value="1" min="1" max="999" class="input input-qty" />
										</div>
									</div>
									
									<div class="form-item">
										<label>Flavour:</label>
										<div>
											<select name="flavour" class="input input-flavour">
												<option>Loading...</option>
											</select>
										</div>
									</div>

									<div class="form-item">
										<label class="note">Shipping address: </label>
										<div>
											<input type="text" name="address" placeholder="Shipping address" required="required" class="input input-shipping-address" />
										</div>
									</div>

									<div class="form-item">
										<label class="note">Consignee: </label>
										<div>
											<input type="text" name="consignee" placeholder="Consignee" required="required" class="input input-consignee" />
										</div>
									</div>

									<div class="form-item">
										<div class="input input-price">
											<label>Price: </label>
											<span class="price-number">1.00</span>
											<span class="price-unit">$</span>
										</div>
									</div>
	
									<div class="form-item">
										<input type="button" value="" class="input button-submit button-start" />
									</div>
								</div>

							</form>
						</div>

						<div id="simulator-panel" class="tab-panels--tab-pane" role="tab-panel">
							<form class="simulator-controller">
								<h2>Send out data of sensors</h2>

								<div class="form-item">
									<label class="note">Shipping to: </label>
									<div>
										<input type="text" readonly="readonly" placeholder="Transit / Destination" class="input input-transit" />
									</div>
								</div>
								
								<div class="form-item">
									<label class="note">Shipping method: </label>
									<div>
										<input type="text" readonly="readonly" placeholder="Shipping method" class="input input-shipping-method" />
									</div>
								</div>
								
								<div class="form-item">
									<label class="note">Temperature: </label>
									<div>
										<input type="text" readonly="readonly" placeholder="Temperature" class="input input-temperature" />
									</div>
								</div>
								
								<div class="form-item">
									<label class="note">Time: </label>
									<div>
										<input type="text" readonly="readonly" placeholder="Time" class="input input-time" />
									</div>
								</div>

								<div class="form-item">
									<input type="button" value="Send" class="input button-submit button-simulator" />
								</div>
							</form>
						</div>

						<div id="vr-panel" class="tab-panels--tab-pane" role="tab-panel">
							<form class="vr-controller" enctype="multipart/form-data">
								<h2>Verify your cooler box after delivery</h2>

								<% if(appUrl != "") { %>
								<div>
									<label>Blockchain service URL:</label>
									<div class="ui-app-link">
										<a href="<%=appUrl %>" target="webchatbot"><%=appUrl %></a>
									</div>
								</div>
								<% } %>

								<div class="form-ice-cream">
									<div class="form-item">
										<label class="note">Take a photo of the cooler box then upload it after delivery: </label>
										<div>
											<input type="file" name="file" class="input input-file" />
										</div>
										<div><progress></progress></div>
									</div>
									<div class="form-item">
										<div class="verify-result">
											<span class="ui-message-succeed hidden">
												Congratulations, the ice cream is delivered successfully. 
												<br /> 
												<span class="score"></span>
												<br /> 
												<span class="class"></span>
											</span>
											<span class="ui-message-failure hidden">This is highly possible that this photo is not cooler box.</span>
											<span class="ui-message-loading hidden">Loading...</span>
										</div>
									</div>
									<div class="form-item">
										<input type="button" value="Verify the cooler box" class="input button-submit button-verify" />
									</div>
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>

			<div class="ui-transcription"></div>
		</article>

		<div class="ui-inputs hidden">
			<input type="text" placeholder="Type a response and hit enter" value="" autocomplete="off" class="chat-window--message-input base--text-input ui-input-message" />
			<input type="button" value="Speak" class="base--button ui-button-microphone" />
		</div>
	</div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/watson-speech.js"></script>
  	<script src="js/talk-to-watson.js"></script>
</body>
</html>
