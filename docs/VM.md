# Host your Chatbot on Virtual Machine

This is the guide for preparing Bluemix and Docker environments

### Start Virtual Machine

- Load and launch Virtual Machine

	<img width="730" alt="Name and operating system" src="https://user-images.githubusercontent.com/1511528/28970838-85f31436-795c-11e7-82ea-d81c9f45f890.png">
	<img width="730" alt="Memory size" src="https://user-images.githubusercontent.com/1511528/28971204-bd6cefe4-795d-11e7-8572-d3086debb896.png">
	<img width="730" alt="Hard disk" src="https://user-images.githubusercontent.com/1511528/28971209-c3d97c1c-795d-11e7-9c65-cf9106c710a0.png">
	<img width="730" alt="Start" src="https://user-images.githubusercontent.com/1511528/28971217-cb48421c-795d-11e7-8ee8-7cab3d51dcc9.png">
	<img width="528" alt="Log In" src="https://user-images.githubusercontent.com/1511528/28971231-d3135630-795d-11e7-8dd8-b1db75f5ade9.png">
	<img width="528" alt="Chatbot on Desktop" src="https://user-images.githubusercontent.com/1511528/28971241-db0e6de8-795d-11e7-8010-95b50b893960.png">
	<img width="730" alt="Edit Docker.env" src="https://user-images.githubusercontent.com/1511528/28971245-df369044-795d-11e7-8899-71a62bbc8ff8.png">

### Environment settings

- Update `~/Desktop/Chatbot/Docker.env` with credentials

* Please be aware of the variables of the values DO NOT HAVE quotes and between key and value there is a `=`, e.g. `key`=`value`

- Good example:

	```shell
	VISUAL_RECOGNITION_API_KEY=abcdefghijklmnopqrstuvwxyz12345678901234
	```

- Bad example

	```shell
	VISUAL_RECOGNITION_API_KEY="abcdefghijklmnopqrstuvwxyz12345678901234"
	```

- Create Watson services

### Acquire `Visual Recognition` service on Bluemix

- After register the Bluemix account, then sign in

- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search `Visual Recognition`

	<img width="730" alt="Catalog" src="https://user-images.githubusercontent.com/1511528/28259910-40f3f71a-6b0b-11e7-922b-7701af5ae174.png">

- Click `Create` button after selecting the `Free plan`

	<img width="730" alt="Create Visual Recognition service" src="https://user-images.githubusercontent.com/1511528/28299281-df67a460-6baa-11e7-96e7-cfcac15cf301.png">
	<img width="730" alt="Created Visual Recognition service" src="https://user-images.githubusercontent.com/1511528/28259972-792e5e18-6b0b-11e7-9fd9-b03dda33f515.png">

### Get API key from tab of `Service credentials` then apply it in `~/Desktop/Chatbot/Docker.env`.

- Click on `Service credentials` tab, then select credential dropdown button, copy `api_key`

	<img width="730" alt="Service credentials" src="https://user-images.githubusercontent.com/1511528/28299355-491d1052-6bab-11e7-964c-565caf9027fe.png">

- Update `~/Desktop/Chatbot/Docker.env`, paste copied `api_key` to `Docker.env`

	<img width="726" alt="Update Docker.env" src="https://user-images.githubusercontent.com/1511528/28721601-8593f1be-73e3-11e7-91ec-5b9b2a0b2158.png">
	<img width="730" alt="Update Docker.env" src="https://user-images.githubusercontent.com/1511528/28721651-a62d3b10-73e3-11e7-8f48-7f29c4ad1bf3.png">

### Train `Visual Recognition` API with the training files, get new trained classifier ID then apply it in `~/Desktop/Chatbot/Docker.env`

- Go back to Bluemix console, go back to `Manage` tab then click on `Visual Recognition Tool (Beta)` button to launch the `Visual Recognition Tool`

	<img width="730" alt="Tooling for Visual Recognition" src="https://user-images.githubusercontent.com/1511528/28261034-3bf6882c-6b10-11e7-9bed-5dc613b3ed48.png">

- Due to the GFW blocked the unsecured connection of Bluemix as below, use [https://visual-recognition-tooling.mybluemix.net/](https://visual-recognition-tooling.mybluemix.net/)
 
	<img width="712" alt="Broken link" src="https://user-images.githubusercontent.com/1511528/28299839-f086d13c-6bad-11e7-896c-6ee4438d06f4.png">
	<img width="610" alt="Use HTTPS" src="https://user-images.githubusercontent.com/1511528/28299997-00373378-6baf-11e7-8201-b032152c0173.png">
	
- For the first time visit of this tool, the API key is needed for authentication 

	<img width="575" alt="First time visit" src="https://user-images.githubusercontent.com/1511528/28349236-20dcba60-6c74-11e7-9b42-34fbadda1990.png">
	<img width="683" alt="Sign in using API key" src="https://user-images.githubusercontent.com/1511528/28349251-38d1d9de-6c74-11e7-803f-5d1e84765ec5.png">

- Then click on `Create` button to get started 

	<img width="730" alt="Tooling interface" src="https://user-images.githubusercontent.com/1511528/28261103-7a47890a-6b10-11e7-80e1-61874862802a.png">

- You will see this page, go to next step refer to following step

	<img width="730" alt="Tooling interface for training" src="https://user-images.githubusercontent.com/1511528/28261149-a2382546-6b10-11e7-806d-e6a51e693aee.png">

### Download training images and unzip it

- [Download training files here](https://github.com/CognitiveBuild/Chatbot/archive/training.zip)
- Unzip the `Chatbot-training.zip` file to `/Chatbot-training`

	<img width="423" alt="Unzip" src="https://user-images.githubusercontent.com/1511528/28726154-ac8e9a12-73f2-11e7-838a-6851d36d9a29.png">

### Upload training images

- Choose training zip files from `/Chatbot-training` folder, then choose `ALL` of them according to the class names including the negative sample

	<img width="730" alt="Tooling for training" src="https://user-images.githubusercontent.com/1511528/28261230-f0844bb2-6b10-11e7-9680-4d77d44244e0.png">
	<img width="730" alt="Tooling" src="https://user-images.githubusercontent.com/1511528/28304118-dd7f290c-6bc8-11e7-9ea6-17a2c40146a1.png">	

- Name each of the classes
 
	 <img width="730" alt="Tooling for training" src="https://user-images.githubusercontent.com/1511528/28261359-7ec60564-6b11-11e7-93ca-f02b87be47a6.png">
	 <img width="498" alt="Training in progress" src="https://user-images.githubusercontent.com/1511528/28261426-d72dc980-6b11-11e7-9406-8a12d23be525.png">

- Copy the `classifier ID`

	 <img width="645" alt="Trained classifier" src="https://user-images.githubusercontent.com/1511528/28261513-313ea8a4-6b12-11e7-9f0b-ddd68537c643.png">

- Paste `classifier ID` in `~/Desktop/Chatbot/Docker.env`

	<img width="707" alt="Update Docker.env" src="https://user-images.githubusercontent.com/1511528/28722326-ee4c505a-73e5-11e7-9f60-cef377cc63ad.png">

### Add other Watson services

- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search `Conversation`

	<img width="730" alt="Search Conversation service" src="https://user-images.githubusercontent.com/1511528/28298707-69b3b626-6ba7-11e7-8674-4dfc295fe976.png">

- Name service as you want, and make sure there is a `Credential name` available then click on `Create` button

	<img width="730" alt="Create Conversation service" src="https://user-images.githubusercontent.com/1511528/28304658-3996964c-6bcb-11e7-8478-b9501eaaf5ed.png">

- Then you will see this page, and click on `Service credentials`, then copy username and password
	
	<img width="730" alt="Conversation interface" src="https://user-images.githubusercontent.com/1511528/28349466-94688e18-6c75-11e7-917f-a15f34f21148.png">
	<img width="730" alt="Service credentials" src="https://user-images.githubusercontent.com/1511528/28349662-bcb1bf2e-6c76-11e7-8aed-4b907b8c302c.png">

- Paste `username` and `password` in `~/Desktop/Chatbot/Docker.env`

	<img width="730" alt="Username" src="https://user-images.githubusercontent.com/1511528/28722741-463d59ca-73e7-11e7-9015-8e5a89cb596d.png">
	<img width="730" alt="Password" src="https://user-images.githubusercontent.com/1511528/28722881-ad7906de-73e7-11e7-8e2f-4db0683526ba.png">

- Start creating a new `Conversation`, click on `Manage` menu on the left then click on `Launch tool`

	<img width="730" alt="Conversation" src="https://user-images.githubusercontent.com/1511528/28403835-2d98c538-6d59-11e7-8c96-6a542cb14569.png">

- For the first time visit of this tool, the IBM ID is needed for authentication, but as long as you've authenticated by Bluemix, it will treat you as a authorized user after clicking on the `Log in with IBM ID` by default. Otherwise you can try to sign in again with your Bluemix credentials

	<img width="427" alt="Conversation" src="https://user-images.githubusercontent.com/1511528/28404804-553486b4-6d5d-11e7-9078-45eac305dad1.png">

- Click on `Upload` icon to upload the training file, to locate the training file, go to `/Chatbot-training/conversation` folder, find `innovation-day.json` file

	<img width="590" alt="Conversation" src="https://user-images.githubusercontent.com/1511528/28404805-5593bc6a-6d5d-11e7-8a95-b4a35e912000.png">
	<img width="652" alt="Upload popup" src="https://user-images.githubusercontent.com/1511528/28405189-e1af84bc-6d5e-11e7-88a6-6a67abe0a81f.png">
	<img width="625" alt="Select training file" src="https://user-images.githubusercontent.com/1511528/28741085-74688d7e-7441-11e7-8792-c8743656e873.png">
	<img width="636" alt="Import (upload)" src="https://user-images.githubusercontent.com/1511528/28405190-e1e35eea-6d5e-11e7-96be-13a47e981926.png">

- There will be one more panel named `iChat`, click on the menu on the top right, then select `View details` in order to copy workspace ID

	<img width="436" alt="Created conversation workspace" src="https://user-images.githubusercontent.com/1511528/28405683-250e78a2-6d60-11e7-8874-5d2379887ac8.png">
	<img width="437" alt="Workspace menu" src="https://user-images.githubusercontent.com/1511528/28405684-250f608c-6d60-11e7-8c8f-5492e2e3b8f8.png">
	<img width="482" alt="View details and copy workspace ID" src="https://user-images.githubusercontent.com/1511528/28405685-251641d6-6d60-11e7-83a5-1be11daa8850.png">

- Paste `workspace ID` in `~/Desktop/Chatbot/Docker.env`

	<img width="662" alt="Update Workspace ID" src="https://user-images.githubusercontent.com/1511528/28723298-e90bb394-73e8-11e7-8560-c804c78dfbe9.png">

- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search `Speech to Text`

	<img width="730" alt="Speech-to-Text" src="https://user-images.githubusercontent.com/1511528/28350215-fd66d966-6c79-11e7-8af0-ab3ab33b7b64.png">

- Name service as you want, and make sure there is a `Credential name` available then click on `Create` button

	<img width="730" alt="Create Speech-to-Text service" src="https://user-images.githubusercontent.com/1511528/28353115-bcd7d306-6c8c-11e7-9d98-a96cf96eedd2.png">

- Then you will see this page below, go to next step refer to following step

	<img width="730" alt="Create Speech-to-Text service" src="https://user-images.githubusercontent.com/1511528/28353005-2c19fc68-6c8c-11e7-8f86-d39dd269bebb.png">

- Copy `username` and `password`, paste them to `~/Desktop/Chatbot/Docker.env` and save the file

	<img width="730" alt="Update Docker.env" src="https://user-images.githubusercontent.com/1511528/28723656-fd6b8642-73e9-11e7-835a-69348a34e394.png">
	<img width="730" alt="Update Docker.env" src="https://user-images.githubusercontent.com/1511528/28723670-0f508826-73ea-11e7-8fd6-6b30e5c12574.png">

- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search `Text to Speech`
	
	<img width="730" alt="Search Text to Speech" src="https://user-images.githubusercontent.com/1511528/28355571-292f501a-6c97-11e7-8339-d6dbcba0dfbb.png">

- Then you will see this page below, go to next step refer to following step, then copy username and password

	<img width="730" alt="Create Text to Speech service" src="https://user-images.githubusercontent.com/1511528/28353603-1786802a-6c8f-11e7-9a31-4d5d8e9fad71.png">
	<img width="730" alt="Service credentials" src="https://user-images.githubusercontent.com/1511528/28489652-1183dade-6efa-11e7-8c2d-59341d82775c.png">

- Paste `username` and `password` in `~/Desktop/Chatbot/Docker.env`

	<img width="730" alt="Update Docker.env" src="https://user-images.githubusercontent.com/1511528/28723824-9759ced0-73ea-11e7-9a6b-d7ba31a190eb.png">
	<img width="730" alt="Update Docker.env" src="https://user-images.githubusercontent.com/1511528/28723848-ab4c8d4c-73ea-11e7-9610-bc11b86801d4.png">

- Update `~/Desktop/Chatbot/Docker.env` with your Blockchain service UR, replace it with actual one if you're hosting the Blockchain service on the Bluemix (refer to Blockchain practice)

    `APPLICATION_API_URL=https://your_block_chain_service.mybluemix.net`


If you are hosting the Blockchain service locally, obtain IP address first:

- macOS: Go to `System Preferences` -> `Network`, then copy your IP address

	<img width="664" alt="IP Address" src="https://user-images.githubusercontent.com/1511528/28741947-99ec3dd0-7454-11e7-81a5-a1234f35f6b2.png">
	<img width="664" alt="IP Address" src="https://user-images.githubusercontent.com/1511528/28741956-c4d1a24c-7454-11e7-813e-7dd71fc14d50.png">

- Windows: [Refer this guide](https://github.com/CognitiveBuild/Chatbot/blob/master/docs/DOCKER.md#install-docker-on-windows) to get the IP address

- Replace the URL with service URL (with your `IP` address)

	ENV APPLICATION_API_URL http://xxx.xxx.xxx.xxx:3000

Now you're ready for building the `Docker` image.

### Build Docker image

	docker run -p 8888:9080 --env-file ~/Desktop/Chatbot/Docker.env chatbot

### Launch

Now after getting this message `The server defaultServer is ready to run a smarter planet.`, you can visit your Chatbot web application via [http://localhost:8888/](http://localhost:8888/)
