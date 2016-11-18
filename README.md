# Chatbot
Build your own chatbot on the Innovation Day

[![Watson: Conversation](https://img.shields.io/badge/watson-conversation-5596e6.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Speech-to-Text](https://img.shields.io/badge/watson-speech--to--text-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Text-to-Speech](https://img.shields.io/badge/watson-text--to--speech-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/CognitiveBuild/Chatbot/master/LICENSE)

#Prerequisite
* Register your [Bluemix](https://bluemix.net) account
* Register your [Github](https://github.com) account 
* Fork this repository and https://github.com/ChristiaL/IOTCar and deploy the nodejs web application

	<img width="730" alt="Fork github" src="https://cloud.githubusercontent.com/assets/1511528/20171945/01edad1a-a76e-11e6-83ed-3cd0245ae2d2.png">

#Getting started
* Create application of `Liberty for Java` on `Bluemix`

	<img width="730" alt="Create application" src="https://cloud.githubusercontent.com/assets/1511528/20180428/6c673cf2-a795-11e6-9424-fff51c0e28e9.png">

	<img width="730" alt="Liberty For Java" src="https://cloud.githubusercontent.com/assets/1511528/20165648/1d688ee4-a74a-11e6-8b8b-bb9698bbf338.png">

	<img width="730" alt="Liberty for Java" src="https://cloud.githubusercontent.com/assets/1511528/20166262/7b5e9184-a74e-11e6-9a5f-49c1776c2a46.png">

* Connect `Liberty for Java` with `Watson Conversation`, `Speech to Text` and `Text to Speech` services

	<img width="730" alt="Connect services" src="https://cloud.githubusercontent.com/assets/1511528/20166344/1900af6c-a74f-11e6-9616-655c7b081137.png">

	<img width="730" alt="Watson Conversation" src="https://cloud.githubusercontent.com/assets/1511528/20166354/384e816e-a74f-11e6-9936-ab5323f40f8d.png">

	<img width="730" alt="Watson Conversation" src="https://cloud.githubusercontent.com/assets/1511528/20166380/5d65f3ce-a74f-11e6-81d4-d09708e22ed2.png">

	<img width="730" alt="Connected services" src="https://cloud.githubusercontent.com/assets/1511528/20166467/ed80eefa-a74f-11e6-9e2b-fe410d696ff0.png">

* Click on `Conversation` from the `Connections` tab and click on `Launch tool` to open the [website of IBM Watson Conversation](https://ibmwatsonconversation.com), then click on `Login with IBM ID` to signin

	<img width="730" alt="Conversation launch tool" src="https://cloud.githubusercontent.com/assets/1511528/20166549/7d13ea86-a750-11e6-823b-a4dfe05ada6e.png">
	
	<img width="730" alt="Sign in conversation" src="https://cloud.githubusercontent.com/assets/1511528/20182253/1d6586fc-a79c-11e6-8423-af8dc921eb2d.png">

* Setup a `Workspace` of `Watson Conversation` and define dialogs (We will do it together on the Innovation Day)

#Bluemix DevOps guide
* Register your [Github](https://github.com) account
* Fork this repository and https://github.com/ChristiaL/IOTCar and deploy the nodejs web application
* Go to `Overview` of the `Liberty for Java` application, find `Continuous Delivery` panel, and click on `Enable` button

	<img width="730" alt="Enable Continous Delivery" src="https://cloud.githubusercontent.com/assets/1511528/20168055/9a6de398-a75a-11e6-9d88-6a2d86271409.png">

	<img width="730" alt="Getting started" src="https://cloud.githubusercontent.com/assets/1511528/20167403/582e8cd4-a756-11e6-8218-d8fd9d367d94.png">
	
	<img width="527" alt="Enable toolchains" src="https://cloud.githubusercontent.com/assets/1511528/20216876/fb6e5904-a857-11e6-8c57-5ffd1d0874b2.png">

* Click on `Create Toolchain from Template` for the first time you enable the Toolchains, and click on `Simple Cloud Foundry toolchain`

	<img width="466" alt="Toolchains are enabled" src="https://cloud.githubusercontent.com/assets/1511528/20168096/d7e67da2-a75a-11e6-84f5-22fd0deb971f.png">

	<img width="730" alt="Choose Toolchain" src="https://cloud.githubusercontent.com/assets/1511528/20216896/1390db4c-a858-11e6-8272-c83843717659.png">

	<img width="730" alt="Toolchain template" src="https://cloud.githubusercontent.com/assets/1511528/20168220/b8181ef8-a75b-11e6-8101-ef53ef339779.png">

* Click on `GitHub` button to Authorize the access to the `GitHub`, then you'll be navigated to `GitHub` website

	<img width="730" alt="Authorize GitHub" src="https://cloud.githubusercontent.com/assets/1511528/20168241/e420d210-a75b-11e6-851d-8b053f3786a4.png">

* On the `GitHub`, click on `Authorize application` button to grant the access from Bluemix DevOps service

	<img width="730" alt="GitHub - Authroizing" src="https://cloud.githubusercontent.com/assets/1511528/20168256/ffe74416-a75b-11e6-9a1a-e80d348ff164.png">

* Type `Github` password to confirm the operation

	<img width="342" alt="GitHub - Authorizing" src="https://cloud.githubusercontent.com/assets/1511528/20168277/224dc4da-a75c-11e6-9eb5-db9351a06ec0.png">

* After authorization, select `Existing` as Repository type, and forked `Github URL` as Source repository URL, then click on `Create` button

	<img width="730" alt="Configuring GitHub" src="https://cloud.githubusercontent.com/assets/1511528/20168420/1656583a-a75d-11e6-8a64-d6448647f143.png">

	<img width="658" alt="Checking" src="https://cloud.githubusercontent.com/assets/1511528/20168434/34452c7c-a75d-11e6-9ee9-6cd7af69d983.png">

	<img width="608" alt="Toolchain created" src="https://cloud.githubusercontent.com/assets/1511528/20168439/3a19c19e-a75d-11e6-82ed-a7b12cc4595b.png">
	
* Verify configurations to ensure the Pipeline deploys on right runtime
	
	<img width="615" alt="Verify Pipeline Configuration" src="https://cloud.githubusercontent.com/assets/1511528/20418428/49f5a57a-ad88-11e6-895b-26ce3556bf8a.png">

	<img width="1081" alt="Verify Pipeline Configuration" src="https://cloud.githubusercontent.com/assets/1511528/20419428/4d11de00-ad91-11e6-9ef2-4a12c58012c3.png">

	<img width="765" alt="Verify Pipeline Configuration" src="https://cloud.githubusercontent.com/assets/1511528/20419582/72efe648-ad92-11e6-88fd-613f502b9be4.png">

* On this step, assuming you have already created one Watson Conversation, so switch back to Watson Conversation website, copy `Workspace ID` from the created `Workspace` by clicking on the menu on the top right of the `Workspace panel`, then click on `View details` and copy the `Workspace ID`

	<img width="488" alt="Conversation Workplace" src="https://cloud.githubusercontent.com/assets/1511528/20168895/6beab0d2-a75f-11e6-9acc-d55aa6be15ac.png">

	<img width="472" alt="Conversation Workplace ID" src="https://cloud.githubusercontent.com/assets/1511528/20168898/716966ca-a75f-11e6-8adc-6fef6a9eb19a.png">

* Switch back to `DevOps service` and click on `Eclipse Orion Web IDE`, and select `manifest.yml` for editing. Change `name` to your app name then update `CONVERSATION_WORKSPACE_ID` to  copied `Workspace ID`

	<img width="730" alt="manifest.yml" src="https://cloud.githubusercontent.com/assets/1511528/20168506/8b3cbefa-a75d-11e6-97b4-d186b252e716.png">

* Click `File` menu and select `Save`

	<img width="730" alt="Save yml file" src="https://cloud.githubusercontent.com/assets/1511528/20169273/8cbe31c4-a761-11e6-80b3-c70ecf40bbc5.png">

* Click on `Git` tab on the left, add commit comments and click on `Commit` button, after that click on `Push` button to update changes on the `GitHub`

	<img width="225" alt="GitHub menu" src="https://cloud.githubusercontent.com/assets/1511528/20169303/c6bfe458-a761-11e6-9ae9-70c094d0b010.png">

	<img width="730" alt="GitHub commit" src="https://cloud.githubusercontent.com/assets/1511528/20169340/f2b5b15a-a761-11e6-92c1-97ffaa46a174.png">

	<img width="730" alt="GitHub push" src="https://cloud.githubusercontent.com/assets/1511528/20169861/bc0bf742-a764-11e6-8950-9d75a0fd8da5.png">

* Go back to toolchain then click on `Delivery Pipeline`, click `Run Stage` icon and wait for the build and deployment process

	<img width="665" alt="DevOps Pipeline" src="https://cloud.githubusercontent.com/assets/1511528/20170198/5182d984-a766-11e6-971c-fcae22cd16cd.png">

	<img width="730" alt="DevOps build and deploy" src="https://cloud.githubusercontent.com/assets/1511528/20170470/4c66d9fe-a767-11e6-9fe4-78a041e885c0.png">

* Go back to the app and launch it after the deployment is done

	<img width="730" alt="View app" src="https://cloud.githubusercontent.com/assets/1511528/20171151/5a4a24ce-a76a-11e6-8119-4e01553d31de.png">

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
 	// Sample only, please USE YOUR CREDENTIALS instead
	public String TEXT_TO_SPEECH_USERNAME =  "9a5bfa13-624f-436e-8af1-fc677a59a123";
	public String TEXT_TO_SPEECH_PASSWORD = "berqyZXJ2J7f";

	public String SPEECH_TO_TEXT_USERNAME = "af3a6ecc-2f35-4672-2595-35e15bcd758a";
	public String SPEECH_TO_TEXT_PASSWORD = "KQ3itZUslHsc";
 
	public String CONVERSATION_USERNAME = "134f9b10-7d4a-4e4f-92a0-7372f67331f7";
	public String CONVERSATION_PASSWORD = "ijMoZB1vCVW6";

	public String CONVERSATION_WORKSPACE_ID = "1e28d5ef-7506-4e76-814e-e83f3cbe6816";
```
* **Right click** on the `Chatbot` project, choose `Run As` &gt; `Run on Server`, then choose `Tomcat v9.0 Server at localhost` to open `http://localhost:8080/Chatbot/` and see the running application
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
* Watson JavaScript Speech SDK does not support **Safari**
* The way of obtaining token is not secured, do not use it on Production

#License
Copyright 2016 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).
