# Getting started guide on Bluemix

* Register your [Github](https://github.com) account
* Fork this repository and remember the link of forked application

	<img width="730" alt="Fork GitHub" src="https://cloud.githubusercontent.com/assets/1511528/20171945/01edad1a-a76e-11e6-83ed-3cd0245ae2d2.png">

	<img width="730" alt="Forked result" src="https://cloud.githubusercontent.com/assets/1511528/20475569/7f60d6cc-b007-11e6-870b-87ae0d7b238f.png">

* Create application of `Liberty for Java` on `Bluemix`

	<img width="730" alt="Create application" src="https://cloud.githubusercontent.com/assets/1511528/20180428/6c673cf2-a795-11e6-9424-fff51c0e28e9.png">

	<img width="730" alt="Liberty For Java" src="https://cloud.githubusercontent.com/assets/1511528/20165648/1d688ee4-a74a-11e6-8b8b-bb9698bbf338.png">

	<img width="730" alt="Liberty for Java" src="https://cloud.githubusercontent.com/assets/1511528/20166262/7b5e9184-a74e-11e6-9a5f-49c1776c2a46.png">

* Connect `Liberty for Java` with `Watson Conversation`, `Speech to Text` and `Text to Speech` services

	<img width="730" alt="Connect services" src="https://cloud.githubusercontent.com/assets/1511528/20166344/1900af6c-a74f-11e6-9616-655c7b081137.png">

	<img width="730" alt="Watson Conversation" src="https://cloud.githubusercontent.com/assets/1511528/20531887/1d4873f2-b113-11e6-891c-159bf84174b7.png">

	<img width="730" alt="Watson Conversation" src="https://cloud.githubusercontent.com/assets/1511528/20531913/35bea456-b113-11e6-9a55-1dca35a9ae19.png">

	<img width="411" alt="Cancel Restage" src="https://cloud.githubusercontent.com/assets/1511528/20531502/f1fb9f22-b111-11e6-8199-419437da07c7.png">

	<img width="730" alt="Connect new" src="https://cloud.githubusercontent.com/assets/1511528/20532520/4720aada-b115-11e6-9147-7d7ce35142ef.png">
	
	<img width="730" alt="Speech to Text" src="https://cloud.githubusercontent.com/assets/1511528/20532556/67a09c84-b115-11e6-9e33-9c3bbd60f9b5.png">

	<img width="730" alt="Speech to Text" src="https://cloud.githubusercontent.com/assets/1511528/20532569/7185d480-b115-11e6-9b6d-6c74337eb8a1.png">

	<img width="411" alt="Cancel Restage" src="https://cloud.githubusercontent.com/assets/1511528/20531502/f1fb9f22-b111-11e6-8199-419437da07c7.png">

	<img width="730" alt="Connect new" src="https://cloud.githubusercontent.com/assets/1511528/20532603/92e2dbaa-b115-11e6-97a3-98630c639a3b.png">

	<img width="730" alt="Text to Speech" src="https://cloud.githubusercontent.com/assets/1511528/20532611/981d427c-b115-11e6-9f39-4d57d0b88709.png">

	<img width="730" alt="Text to Speech" src="https://cloud.githubusercontent.com/assets/1511528/20532637/b2266220-b115-11e6-9afc-8051cd20d6f9.png">

	<img width="411" alt="Cancel Restage" src="https://cloud.githubusercontent.com/assets/1511528/20531502/f1fb9f22-b111-11e6-8199-419437da07c7.png">

	<img width="730" alt="Connected services" src="https://cloud.githubusercontent.com/assets/1511528/20532706/e3e5585c-b115-11e6-8753-594795d1937b.png">

* Click on `Conversation` from the `Connections` tab and click on `Launch tool` to open the [website of IBM Watson Conversation](https://ibmwatsonconversation.com), then click on `Login with IBM ID` to signin

	<img width="730" alt="Conversation launch tool" src="https://cloud.githubusercontent.com/assets/1511528/20166549/7d13ea86-a750-11e6-823b-a4dfe05ada6e.png">
	
	<img width="730" alt="Sign in conversation" src="https://cloud.githubusercontent.com/assets/1511528/20182253/1d6586fc-a79c-11e6-8423-af8dc921eb2d.png">

* Click on `Upload` icon to upload the training file, to locate the training file, go to `/Chatbot-docker/trainings/conversation` folder, find `innovation-day.json` file

	<img width="590" alt="Conversation" src="https://user-images.githubusercontent.com/1511528/28404805-5593bc6a-6d5d-11e7-8a95-b4a35e912000.png">
	<img width="652" alt="Upload popup" src="https://user-images.githubusercontent.com/1511528/28405189-e1af84bc-6d5e-11e7-88a6-6a67abe0a81f.png">
	<img width="614" alt="Select training file" src="https://user-images.githubusercontent.com/1511528/28405188-e1af5096-6d5e-11e7-8739-bea4db674ed2.png">
	<img width="636" alt="Import (upload)" src="https://user-images.githubusercontent.com/1511528/28405190-e1e35eea-6d5e-11e7-96be-13a47e981926.png">

* There will be one more panel named `iChat`

# Bluemix DevOps guide
* Register your [Github](https://github.com) account
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

	<img width="730" alt="Create Toolchain" src="https://cloud.githubusercontent.com/assets/1511528/20557452/934f7530-b1a6-11e6-8299-529c9f097e3a.png">

	<img width="658" alt="Checking" src="https://cloud.githubusercontent.com/assets/1511528/20168434/34452c7c-a75d-11e6-9ee9-6cd7af69d983.png">
	
* Verify configurations to ensure the Pipeline deploys on right runtime with the correct application name

	<img width="615" alt="Verify Pipeline Configuration" src="https://cloud.githubusercontent.com/assets/1511528/20418428/49f5a57a-ad88-11e6-895b-26ce3556bf8a.png">

	<img width="730" alt="Verify Pipeline Configuration" src="https://cloud.githubusercontent.com/assets/1511528/20419428/4d11de00-ad91-11e6-9ef2-4a12c58012c3.png">
	
	<img width="730" alt="Verify Pipeline Configuration" src="https://cloud.githubusercontent.com/assets/1511528/20589470/b4bb3c84-b257-11e6-8ed4-edc5179805e1.png">

* On this step, switch back to Watson Conversation website, copy `Workspace ID` from the created `Workspace` by clicking on the menu on the top right of the `Workspace panel`, then click on `View details` and copy the `Workspace ID`

	<img width="488" alt="Conversation Workplace" src="https://cloud.githubusercontent.com/assets/1511528/20168895/6beab0d2-a75f-11e6-9acc-d55aa6be15ac.png">

	<img width="472" alt="Conversation Workplace ID" src="https://cloud.githubusercontent.com/assets/1511528/20168898/716966ca-a75f-11e6-8adc-6fef6a9eb19a.png">

* Switch back to `DevOps service` and click on `Eclipse Orion Web IDE`, and select `manifest.yml` for editing. Change `name` to your app name then update `CONVERSATION_WORKSPACE_ID` to  copied `Workspace ID`
	
	<img width="730" alt="Edit manifest.yml" src="https://cloud.githubusercontent.com/assets/1511528/20534437/32caef94-b11c-11e6-950b-32e4a8190bae.png">

* Click `File` menu and select `Save`
	
	<img width="730" alt="Save yml file" src="https://cloud.githubusercontent.com/assets/1511528/20534840/aa8c6de0-b11d-11e6-89e7-9e6e407fc163.png">

* Click on `Git` tab on the left, add commit comments and click on `Commit` button, after that click on `Push` button to update changes on the `GitHub`

	<img width="225" alt="GitHub menu" src="https://cloud.githubusercontent.com/assets/1511528/20169303/c6bfe458-a761-11e6-9ae9-70c094d0b010.png">

	<img width="730" alt="GitHub commit" src="https://cloud.githubusercontent.com/assets/1511528/20169340/f2b5b15a-a761-11e6-92c1-97ffaa46a174.png">

	<img width="730" alt="GitHub push" src="https://cloud.githubusercontent.com/assets/1511528/20169861/bc0bf742-a764-11e6-8950-9d75a0fd8da5.png">

* Go back to toolchain then click on `Delivery Pipeline`, if it does not automatically start, click `Run Stage` icon and wait for the build and deployment process

	<img width="730" alt="Pipeline interface" src="https://cloud.githubusercontent.com/assets/1511528/20592411/41cf7e68-b268-11e6-92c6-ae6ce9cf1e47.png">
	
	<img width="730" alt="Pipeline interface" src="https://cloud.githubusercontent.com/assets/1511528/20592956/4499a774-b26b-11e6-9cad-13b78766b287.png">
	
	<img width="730" alt="pipeline deployed" src="https://cloud.githubusercontent.com/assets/1511528/20592749/33cf528c-b26a-11e6-8974-ed02a0679b26.png">

***Start chatting using text or microphone with your personal Chatbot and Enjoy!***