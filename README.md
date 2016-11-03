# Chatbot
Build your own chatbot on the Innovation Day

[![Watson: Conversation](https://img.shields.io/badge/watson-conversation-5596e6.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Speech-to-Text](https://img.shields.io/badge/watson-speech--to--text-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Text-to-Speech](https://img.shields.io/badge/watson-text--to--speech-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/CognitiveBuild/Chatbot/master/LICENSE)

#Prerequisite
* Register your [Bluemix](https://bluemix.net/) account
* Create `Java Liberty Runtime`
* Connect Java Liberty Runtime with `Watson Conversation`, `Speech to Text` and `Text to Speech` services
* Setup a `Workspace` of `Watson Conversation` and define your dialogs (We will do it together on the Innovation Day)
* Install [Bluemix](http://clis.ng.bluemix.net/ui/home.html) and [CF CLI](https://github.com/cloudfoundry/cli/releases)
* Install **Eclipse Java EE IDE for Web Developers** as your IDE ([Download](http://eclipse.bluemix.net/packages/neon.1/))
* Setup Websphere Application Server Liberty Profile in the **Eclipse** for debugging purpose ([Download](https://developer.ibm.com/wasdev/downloads/liberty-profile-using-non-eclipse-environments/))

#Installation guide
* Run git command or download the [source code here](https://github.com/CognitiveBuild/Chatbot/archive/master.zip)
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
#Deploy your Chatbot on the Bluemix
* **Right click** on the Chatbot project, choose `Export` &gt; `WAR file`, then save the `WAR file` into `Destination`
* Run `bluemix` and `cf` command to deploy the `WAR file` on the Bluemix, please refer `  Getting Started` section of the Java Liberty Runtime. And this is an example of the shell command
```shell
	cd your_new_directory
	bluemix api https://api.ng.bluemix.net
	bluemix login -u your_name -o your_organization -s your_space
	cf push -p your_war_file_directory/your_war_file.war
```
* Start chatting using text or microphone with your personal Chatbot and Enjoy!

#Dependencies
* Apache Common Codec
* Apache HTTP Client
* Watson Java SDK
* Watson Speech JavaScript SDK

#Issues
* Watson JavaScript Speech SDK does not support Safari
* The way of obtaining token is not secured, do not use it on Production

#License
Copyright 2016 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).
