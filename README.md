# Chatbot
Build your own chatbot on the Innovation Day

[![Watson: Conversation](https://img.shields.io/badge/watson-conversation-black.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Speech-to-Text](https://img.shields.io/badge/watson-speech--to--text-black.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Text-to-Speech](https://img.shields.io/badge/watson-text--to--speech-black.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/CognitiveBuild/Chatbot/master/LICENSE)

#Prerequisite
* Register your [Bluemix](https://console.ng.bluemix.net/) account
* Create `Java Liberty Runtime`
* Connect Java Liberty Runtime with `Watson Conversation`, `Speech to Text` and `Text to Speech` services
* Setup a `Workspace` of `Watson Conversation` and define your dialogs
* Install Bluemix and CF CLI
* Install **Eclipse Java EE IDE for Web Developers** as your IDE
* Setup Websphere Application Server Liberty Profile in the **Eclipse** for debugging purpose

#Installation guide
* Run commands 
```shell
	git clone git@github.com:CognitiveBuild/Chatbot.git
```
* Import **Chatbot** into your Eclipse workspace
* Add credentials into the file: `/Chatbot/src/com/ibm/cto/Consts.java`
```java
	// Sample only, please use your own credentials
	public static final String TTS_USERNAME = "9a5bfa13-624f-436e-8af1-fc677a59a123";
	public static final String TTS_PASSWORD = "berqyZXJ2J7f";
	
	public static final String STT_USERNAME = "af3a6ecc-2f35-4672-2595-35e15bcd758a";
	public static final String STT_PASSWORD = "KQ3itZUslHsc";

	public static final String CONVERSATION_USERNAME = "134f9b10-7d4a-4e4f-92a0-7372f67331f7";
	public static final String CONVERSATION_PASSWORD = "ijMoZB1vCVW6";
	public static final String WORKSPACE_ID = "1e28d5ef-7506-4e76-814e-e83f3cbe6816";
```
* **Right click** on the Chatbot project, choose `Run As` &gt; `Run on Server` to open `http://localhost:9080/Chatbot/`, there will be logs in the console including following, it means your server is started and the Chatbot is automatically deployed on your local server environment
```console
	Application Chatbot started in xxx seconds.
	......
	The server testServer is ready to run a smarter planet.
```
* Start chatting using text or microphone with your personal Chatbot and Enjoy!

#Dependencies
* Apache Common Codec
* Apache HTTP Client
* Watson Java SDK
* Watson Speech JavaScript SDK

#Issues
* Watson JavaScript Speech SDK does not support Safari

#License
Copyright 2016 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).
