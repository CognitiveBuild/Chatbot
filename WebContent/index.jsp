<!DOCTYPE html>
<html lang="en">
<head>
<title>Talk to Watson</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/ionicons.min.css" />
<link rel="stylesheet" href="css/style.css" />
<script>
	var carServiceHost = '<%=com.ibm.cto.Configuration.getInstance().CAR_SERVICE_HOST %>';
</script>
</head>
<body>
	<header class="_demo--heading">
		<div class="_demo--container">
			<a class="wordmark" href="index.jsp">
				<span class="wordmark--left">Talk to</span>
				<span class="wordmark--right">Watson</span>
			</a>
			
			<!--nav class="heading-nav">
				<a class="heading-nav--item chatbot" href="./">
					Chatbot
				</a>
			</nav-->
		</div>
	</header>
	<div class="_demo--container">
		<article class="_demo--content base--article page" ref="chatbot">
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
	
					<input type="text" placeholder="Type a response and hit enter" value="" autocomplete="off" class="chat-window--message-input base--text-input ui-input-message" />
					<input type="button" value="Speak" class="base--button ui-button-microphone" />
				</div>
			</div>

			<div class="_content--data">
				<div class="tab-panels" role="tabpanel">
					<ul class="tab-panels--tab-list" role="tablist">
						<li class="tab-panels--tab-list-item base-li" role="presentation">
							<a class="tab-panels--tab base--a active" href="#control-panel" aria-controls="control" role="tab">Controller</a>
						</li>
						<li class="tab-panels--tab-list-item base-li" role="presentation">
							<a class="tab-panels--tab base--a" href="#json-panel" aria-controls="json" role="tab">JSON</a>
						</li>
					</ul>
					<div class="tab-panels--tab-content">
						<div id="json-panel" class="tab-panels--tab-pane" role="tab-panel">
							<textarea class="base--textarea"></textarea>
						</div>
						<div id="control-panel" class="tab-panels--tab-pane active" role="tab-panel">
							<div class="car-controller">
								<div class="row">
									<a class="item item-up" ref="w"><i class="ion-arrow-up-b"></i></a>
								</div>
								<div class="row">
									<a class="item item-left" ref="a"><i class="ion-arrow-left-b"></i></a>
									<a class="item item-right" ref="d"><i class="ion-arrow-right-b"></i></a>
								</div>
								<div class="row">
									<a class="item item-down" ref="s"><i class="ion-arrow-down-b"></i></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="ui-transcription"></div>
		</article>

	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/socket.io-1.3.7.js"></script>
	<script src="js/watson-speech.js"></script>
  	<script src="js/talk-to-watson.js"></script>
</body>
</html>
