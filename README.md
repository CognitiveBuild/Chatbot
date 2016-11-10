# Chatbot
Build your own chatbot on the Innovation Day

[![Watson: Conversation](https://img.shields.io/badge/watson-conversation-5596e6.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Speech-to-Text](https://img.shields.io/badge/watson-speech--to--text-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Text-to-Speech](https://img.shields.io/badge/watson-text--to--speech-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/CognitiveBuild/Chatbot/master/LICENSE)

#Prerequisite
* Register your [Bluemix](https://bluemix.net) account
* Register your [Github](https://github.com) account and fork this repository

#Getting started
* Create application of `Liberty for Java` on `Bluemix`

<img width="730" alt="Create application" src="https://cloud.githubusercontent.com/assets/1511528/20165918/31889dcc-a74c-11e6-9191-eadb001f1cb3.png">

<img width="736" alt="Liberty For Java" src="https://cloud.githubusercontent.com/assets/1511528/20165648/1d688ee4-a74a-11e6-8b8b-bb9698bbf338.png">

<img width="730" alt="Liberty for Java" src="https://cloud.githubusercontent.com/assets/1511528/20166262/7b5e9184-a74e-11e6-9a5f-49c1776c2a46.png">

* Connect `Liberty for Java` with `Watson Conversation`, `Speech to Text` and `Text to Speech` services

<img width="730" alt="Connect services" src="https://cloud.githubusercontent.com/assets/1511528/20166344/1900af6c-a74f-11e6-9616-655c7b081137.png">

<img width="730" alt="Watson Conversation" src="https://cloud.githubusercontent.com/assets/1511528/20166354/384e816e-a74f-11e6-9936-ab5323f40f8d.png">

<img width="730" alt="Watson Conversation" src="https://cloud.githubusercontent.com/assets/1511528/20166380/5d65f3ce-a74f-11e6-81d4-d09708e22ed2.png">

<img width="730" alt="Connected services" src="https://cloud.githubusercontent.com/assets/1511528/20166467/ed80eefa-a74f-11e6-9e2b-fe410d696ff0.png">

* Click on `Conversation` from the `Connections` tab and click on `Launch tool` to open the [website of IBM Watson Conversation](https://ibmwatsonconversation.com), then click on `Login with IBM ID` to signin

<img width="730" alt="Conversation launch tool" src="https://cloud.githubusercontent.com/assets/1511528/20166549/7d13ea86-a750-11e6-823b-a4dfe05ada6e.png">

* Setup a `Workspace` of `Watson Conversation` and define dialogs (We will do it together on the Innovation Day)

#Bluemix DevOps guide
* Register your [Github](https://github.com) account, then fork this repository
* Go to `Overview` of the `Liberty for Java` application, find `Continuous Delivery` panel, and click on `Enable` button
* Click on `Create Toolchain from Template` for the first time you enable the Toolchains, and click on `Simple Cloud Foundry toolchain`
* Click on `GitHub` button to Authorize the access to the `GitHub`, then you'll be navigated to `GitHub` website
* On the `GitHub`, click on Authorize button to grant the access
* Type `Github` password to confirm the operation
* After authorization, select `Existing` as Repository type, and forked `Github URL` as Source repository URL, then click on `Create` button
* Click on `Eclipse Orion Web IDE` to open the repository on the DevOps service
* Go back to Watson Conversation website, copy `Workspace ID` from the created `Workspace` by clicking on the menu on the top right of the `Workspace panel`, then click on `View details` and copy the `Workspace ID`
* Switch to `Eclipse Orion Web IDE`, and select `manifest.yml` for editing. Change `name` to your app name then update `CONVERSATION_WORKSPACE_ID` to  copied `Workspace ID`
* Click `File` menu and select `Save`
* Click on `Git` tab on the left, add commit comments and click on `Commit` button, after that click on `Push` button to update changes on the `GitHub`
* Go back to toolchain then click on `Delivery Pipeline`, click `Run Stage` icon and wait for the build and deployment process

***Start chatting using text or microphone with your personal Chatbot and Enjoy!***

#Setup local environment (Optional)
**Installation**
* Install [Bluemix](http://clis.ng.bluemix.net/ui/home.html) and [CF CLI](https://github.com/cloudfoundry/cli/releases)
* Install **Eclipse Java EE IDE for Web Developers** as your IDE ([Download](http://eclipse.bluemix.net/packages/neon.1/))
* Install latest [JavaEE SDK](http://www.oracle.com/technetwork/java/javaee/downloads/index.html) or [JavaSE SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [Tomcat 9](http://tomcat.apache.org/download-90.cgi) and add it into `Eclipse` as a new Server

**Run Chatbot application locally**
* Run git command or download the [source code here](https://github.com/CognitiveBuild/Chatbot/archive/master.zip)
```shell
	git clone git@github.com:CognitiveBuild/Chatbot.git
```
* Import **Chatbot** into your Eclipse workspace
* Add credentials into the file: `/Chatbot/src/com/ibm/cto/Consts.java`
```java
	// Sample only, please use your own credentials
	public String TEXT_TO_SPEECH_USERNAME =  "9a5bfa13-624f-436e-8af1-fc677a59a123";
	public String TEXT_TO_SPEECH_PASSWORD = "berqyZXJ2J7f";

	public String SPEECH_TO_TEXT_USERNAME = "af3a6ecc-2f35-4672-2595-35e15bcd758a";
	public String SPEECH_TO_TEXT_PASSWORD = "KQ3itZUslHsc";

	public String CONVERSATION_USERNAME = "134f9b10-7d4a-4e4f-92a0-7372f67331f7";
	public String CONVERSATION_PASSWORD = "ijMoZB1vCVW6";
	public String CONVERSATION_WORKSPACE_ID = "1e28d5ef-7506-4e76-814e-e83f3cbe6816";
```
* **Right click** on the `Chatbot` project, choose `Run As` &gt; `Run on Server` to open `http://localhost:8080/Chatbot/`

* **Deploy your Chatbot on the Bluemix**
* **Right click** on the Chatbot project, choose `Export` &gt; `WAR file`, then save the `WAR file` into `Destination`
* Run `bluemix` and `cf` command to deploy the `WAR file` on the Bluemix, please refer `Getting Started` section of the `Liberty for Java`. And this is an example of the shell command
```shell
	cd your_new_directory
```
```shell
	bluemix api https://api.ng.bluemix.net
```
```shell
	bluemix login -u your_name -o your_organization -s your_space
```
```shell
	cf push your_app_name -p your_war_file_directory/your_war_file.war
```

#Dependencies
* Apache Common Codec
* Apache HTTP Client
* FastJSON
* Watson Java SDK
* Watson Speech JavaScript SDK

#Issues
* Watson JavaScript Speech SDK does not support Safari
* The way of obtaining token is not secured, do not use it on Production

#License
Copyright 2016 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).
