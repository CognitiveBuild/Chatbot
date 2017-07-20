# Hosting Chatbot on Docker

### Install docker

Before start, install the Docker for this practice, see the references [here](https://www.docker.com/community-edition#/download) about how to install the Docker for different platforms

###### Install docker on Windows 10

Below is the guide especially for `Windows 10` as the `macOS` one is very easy and straightforward:

1) In order to use docker, disable `Hyper-v` on `Windows 10`:

```shell
	bcdedit /set hypervisorlaunchtype off
```

In case you want to re-enable it after practice, here is the command:

```shell
	bcdedit /set hypervisorlaunchtype auto
```

2) Make sure the `Virtualization` is enabled in windows. You can read [this](https://docs.docker.com/toolbox/toolbox_install_windows/) for reference.

3) After installing the docker Toolbox, click the `Quick Start Terminal` on the desktop. If you have passed previous 2 steps, you will see a page for downloading one `boot2docker.iso` file. You can manually download and put it into `C:\Users\your_username\.docker\machine\cache` folder. (Copy the `boot2docker.iso` from the USB provided on the class)


4) You can close the `Quick Start Terminal` window, then use `cmd` (refer to `Build docker image` section on this page). Run a command:  

```shell
	docker-machine ls
```

Then you should be able to see the default virtual machine.

5) Run `docker-machine env default`. It will show some information about your environment. Choose the last row and run it manually. Then you will be able to run docker in your `cmd` window.

6)	Remember the `IP address` in 2nd line as on `Windows`, you will use it to access the application you're deploying on docker. 

You cannot just use localhost to access the service. Use the IP instead. The reason is there is a boot2docker VM running by VMBox. Docker connect with the VM directly. The IP is for the VM.

###### Install docker on macOS

- Please refer the doc [here](https://store.docker.com/editions/community/docker-ce-desktop-mac)

### Prepare Bluemix and Docker environments

##### Download this repository
- Go to [https://ibm.biz/webchatbot](https://ibm.biz/webchatbot)
- Click on `Download ZIP` to download the Chatbot project

	<img width="730" alt="Download" src="https://user-images.githubusercontent.com/1511528/28299055-8a5477c4-6ba9-11e7-928e-0f770f99824e.png">

- After downloading, unzip file `Chatbot-master.zip`
	
	<img width="614" alt="Unzip" src="https://user-images.githubusercontent.com/1511528/28299126-e3292778-6ba9-11e7-84b7-5e833e3bbafa.png">

##### Acquire `Visual Recognition` service on Bluemix

- After register the Bluemix account, then sign in

- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search `Visual Recognition`

	<img width="730" alt="Catalog" src="https://user-images.githubusercontent.com/1511528/28259910-40f3f71a-6b0b-11e7-922b-7701af5ae174.png">

- Click `Create` button after selecting the `Free plan`

	<img width="730" alt="Create Visual Recognition service" src="https://user-images.githubusercontent.com/1511528/28299281-df67a460-6baa-11e7-96e7-cfcac15cf301.png">
	<img width="730" alt="Created Visual Recognition service" src="https://user-images.githubusercontent.com/1511528/28259972-792e5e18-6b0b-11e7-9fd9-b03dda33f515.png">

##### Get API key from tab of `Service credentials` then apply it in `/Chatbot/docker/Dockerfile`.

- Click on `Service credentials` tab, then select credential dropdown button, copy `api_key`

	<img width="730" alt="Service credentials" src="https://user-images.githubusercontent.com/1511528/28299355-491d1052-6bab-11e7-964c-565caf9027fe.png">

- Update `/Chatbot/docker/Dockerfile`, paste copied `api_key` to `Dockerfile`

	<img width="730" alt="Update Dockerfile" src="https://user-images.githubusercontent.com/1511528/28260413-8a190e38-6b0d-11e7-8f4e-d2aafdf1cedc.png">

##### Train `Visual Recognition` API with the training files, get new trained classifier ID then apply it in `/Chatbot/docker/Dockerfile`

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

##### Upload training images

- Choose training zip files from `/Chatbot/trainings` folder, then choose `ALL` of them according to the class names including the negative sample

	<img width="730" alt="Tooling for training" src="https://user-images.githubusercontent.com/1511528/28261230-f0844bb2-6b10-11e7-9680-4d77d44244e0.png">
	<img width="730" alt="Tooling" src="https://user-images.githubusercontent.com/1511528/28304118-dd7f290c-6bc8-11e7-9ea6-17a2c40146a1.png">	

- Name each of the classes
 
	 <img width="730" alt="Tooling for training" src="https://user-images.githubusercontent.com/1511528/28261359-7ec60564-6b11-11e7-93ca-f02b87be47a6.png">
	 <img width="498" alt="Training in progress" src="https://user-images.githubusercontent.com/1511528/28261426-d72dc980-6b11-11e7-9406-8a12d23be525.png">

- Copy the classifier ID

	 <img width="645" alt="Trained classifier" src="https://user-images.githubusercontent.com/1511528/28261513-313ea8a4-6b12-11e7-9f0b-ddd68537c643.png">

- Paste classifier ID in `/Chatbot/docker/Dockerfile`

	<img width="730" alt="Update Dockerfile" src="https://user-images.githubusercontent.com/1511528/28261700-e16dadd8-6b12-11e7-9626-6476f2e8c514.png">

###### Add other Watson services

- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search `Conversation`

	<img width="730" alt="Search Conversation service" src="https://user-images.githubusercontent.com/1511528/28298707-69b3b626-6ba7-11e7-8674-4dfc295fe976.png">

- Name service as you want, and make sure there is a `Credential name` available then click on `Create` button

	<img width="730" alt="Create Conversation service" src="https://user-images.githubusercontent.com/1511528/28304658-3996964c-6bcb-11e7-8478-b9501eaaf5ed.png">

- Then you will see this page, and click on `Service credentials`, then copy username and password
	
	<img width="730" alt="Conversation interface" src="https://user-images.githubusercontent.com/1511528/28349466-94688e18-6c75-11e7-917f-a15f34f21148.png">
	<img width="730" alt="Service credentials" src="https://user-images.githubusercontent.com/1511528/28349662-bcb1bf2e-6c76-11e7-8aed-4b907b8c302c.png">

- Paste username and password in `/Chatbot/docker/Dockerfile`

	<img width="730" alt="Username" src="https://user-images.githubusercontent.com/1511528/28349917-38c6f3d0-6c78-11e7-9239-7ec3e3882651.png">
	<img width="730" alt="Password" src="https://user-images.githubusercontent.com/1511528/28349918-38c9aba2-6c78-11e7-9fd5-ae7f6b2b9098.png">

- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search `Speech to Text`

	<img width="730" alt="Speech-to-Text" src="https://user-images.githubusercontent.com/1511528/28350215-fd66d966-6c79-11e7-8af0-ab3ab33b7b64.png">

- Name service as you want, and make sure there is a `Credential name` available then click on `Create` button

	<img width="730" alt="Create Speech-to-Text service" src="https://user-images.githubusercontent.com/1511528/28353115-bcd7d306-6c8c-11e7-9d98-a96cf96eedd2.png">

- Then you will see this page below, go to next step refer to following step

	<img width="730" alt="Create Speech-to-Text service" src="https://user-images.githubusercontent.com/1511528/28353005-2c19fc68-6c8c-11e7-8f86-d39dd269bebb.png">

- Copy username and password, paste them to `Dockerfile` and save the file

	<img width="730" alt="Update Dockerfile" src="https://user-images.githubusercontent.com/1511528/28349917-38c6f3d0-6c78-11e7-9239-7ec3e3882651.png">
	<img width="730" alt="Update Dockerfile" src="https://user-images.githubusercontent.com/1511528/28349918-38c9aba2-6c78-11e7-9fd5-ae7f6b2b9098.png">

- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search `Text to Speech`
	
	<img width="730" alt="Search Text to Speech" src="https://user-images.githubusercontent.com/1511528/28355571-292f501a-6c97-11e7-8339-d6dbcba0dfbb.png">

- Then you will see this page below, go to next step refer to following step, then copy username and password

	<img width="730" alt="Create Text to Speech service" src="https://user-images.githubusercontent.com/1511528/28353603-1786802a-6c8f-11e7-9a31-4d5d8e9fad71.png">
	<img width="730" alt="Service credentials" src="https://user-images.githubusercontent.com/1511528/28355711-b5905fb8-6c97-11e7-8c16-a84a0f82aa92.png">

- Paste username and password in `/Chatbot/docker/Dockerfile`

	<img width="692" alt="Update Dockerfile" src="https://user-images.githubusercontent.com/1511528/28355888-7d1b5ba0-6c98-11e7-9a2d-7b07b038a12b.png">
	<img width="692" alt="Update Dockerfile" src="https://user-images.githubusercontent.com/1511528/28356038-1c0527d2-6c99-11e7-824d-5564364cf699.png">

###### Update `/Chatbot/docker/Dockerfile` with your Blockchain service URL - replace `your_block_chain_service` to actual one (refer to Blockchain practice)

	ENV APPLICATION_API_URL https://your_block_chain_service.mybluemix.net/api/order/newinfo

Now you're ready for building the docker image.

##### After installing the Docker, open `Terminal` on **macOS** or `cmd` on **Windows**

### Build docker image
##### Go to `/Chatbot/docker/` folder under the Chatbot project root folder from the `Terminal` or `cmd`

- Find your `Terminal` from `macOS`

	<img width="336" alt="Terminal" src="https://user-images.githubusercontent.com/1511528/28262080-5ba1002c-6b14-11e7-9522-aa395d4cf35e.png">
	<img width="572" alt="cmd" src="https://user-images.githubusercontent.com/1511528/28262785-f3775b24-6b16-11e7-8e50-51b2e853ad64.png">

- Find your `cmd` from `Windows`

	<img width="730" alt="Command" src="https://user-images.githubusercontent.com/1511528/28262556-f55a4fec-6b15-11e7-93b2-2d81ee7cd3d3.png">
	<img width="730" alt="Command" src="https://user-images.githubusercontent.com/1511528/28262716-9c734888-6b16-11e7-831b-8c6d870d0a22.png">

- Find your project folder, e.g. `/path/to/Chatbot/docker`, then run the command

	cd /path/to/Chatbot/docker

- To build the docker image, run the command: 

```shell
	docker build -t chatbot -f Dockerfile ./
```

### Run the docker image on a new container

- Run the command: 

```shell
	docker run -d -p 8888:9080 chatbot
```

##### Now you can visit your Chatbot web application via 

- macOS: [http://localhost:8888](http://localhost:8888)
- Windows:  Refer to section `Install docker on Windows 10 (5, 6)`

### Possible issues
If you encounter an issue like the output from `Terminal` or `cmd` as below

	Sending build context to Docker daemon  4.246MB
	Step 1/15 : FROM websphere-liberty:webProfile7

	Get https://registry-1.docker.io/v2/library/websphere-liberty/manifests/webProfile7: unauthorized: incorrect username or password

Please run this command: 

```shell
	docker logout
```

