# Hosting Chatbot on Docker

Before start, install the Docker for this practice, see the references [here](https://www.docker.com/community-edition#/download) about how to install the Docker for different platforms

### Prepare Bluemix and Docker environments

##### Download this repository
- Go to [https://ibm.biz/webchatbot](https://ibm.biz/webchatbot)
- Click on `Download ZIP` to download the Chatbot project

	<img width="730" alt="Download" src="https://user-images.githubusercontent.com/1511528/28299055-8a5477c4-6ba9-11e7-928e-0f770f99824e.png">

- After downloading, unzip file `Chatbot-master.zip`
	
	<img width="614" alt="Unzip" src="https://user-images.githubusercontent.com/1511528/28299126-e3292778-6ba9-11e7-84b7-5e833e3bbafa.png">

##### Acquire `Visual Recognition` service on Bluemix
- After register the Bluemix account, then sign in
- Go to [Bluemix catalog](https://console.bluemix.net/catalog/), search for `Visual Recognition`

	<img width="730" alt="Catalog" src="https://user-images.githubusercontent.com/1511528/28259910-40f3f71a-6b0b-11e7-922b-7701af5ae174.png">

- Click `Create` button after selecting the `Free plan`

	<img width="801" alt="Create Visual Recognition service" src="https://user-images.githubusercontent.com/1511528/28299281-df67a460-6baa-11e7-96e7-cfcac15cf301.png">
	<img width="730" alt="Created Visual Recognition service" src="https://user-images.githubusercontent.com/1511528/28259972-792e5e18-6b0b-11e7-9fd9-b03dda33f515.png">

##### Get API key from tab of `Service credentials` then apply it in `/Chatbot/docker/Dockerfile`.

- Click on `Service credentials` tab, then select credential dropdown button

	<img width="730" alt="Service credentials" src="https://user-images.githubusercontent.com/1511528/28299355-491d1052-6bab-11e7-964c-565caf9027fe.png">

- Update `/Chatbot/docker/Dockerfile`

	<img width="730" alt="Update Dockerfile" src="https://user-images.githubusercontent.com/1511528/28260413-8a190e38-6b0d-11e7-8f4e-d2aafdf1cedc.png">

##### Train `Visual Recognition` API with the training files, get new trained classifier ID then apply it in `/Chatbot/docker/Dockerfile`

- Go back to Bluemix console, go back to `Manage` tab then click on `Visual Recognition Tool (Beta)` button

	<img width="730" alt="Tooling for Visual Recognition" src="https://user-images.githubusercontent.com/1511528/28261034-3bf6882c-6b10-11e7-9bed-5dc613b3ed48.png">
	<img width="730" alt="Tooling interface" src="https://user-images.githubusercontent.com/1511528/28261103-7a47890a-6b10-11e7-80e1-61874862802a.png">
	<img width="730" alt="Tooling interface for training" src="https://user-images.githubusercontent.com/1511528/28261149-a2382546-6b10-11e7-806d-e6a51e693aee.png">

##### Upload training images

- Choose training zip files from `/Chatbot/trainings` folder

	<img width="730" alt="Tooling for training" src="https://user-images.githubusercontent.com/1511528/28261230-f0844bb2-6b10-11e7-9680-4d77d44244e0.png">

- Name each of the classes
 
	 <img width="730" alt="Tooling for training" src="https://user-images.githubusercontent.com/1511528/28261359-7ec60564-6b11-11e7-93ca-f02b87be47a6.png">
	 <img width="498" alt="Training in progress" src="https://user-images.githubusercontent.com/1511528/28261426-d72dc980-6b11-11e7-9406-8a12d23be525.png">

- Copy the classifier ID

	 <img width="645" alt="Trained classifier" src="https://user-images.githubusercontent.com/1511528/28261513-313ea8a4-6b12-11e7-9f0b-ddd68537c643.png">

- Paste classifier ID in `/Chatbot/docker/Dockerfile`

	<img width="730" alt="Update Dockerfile" src="https://user-images.githubusercontent.com/1511528/28261700-e16dadd8-6b12-11e7-9626-6476f2e8c514.png">

##### After installing the Docker, open `Terminal` on **macOS** or `cmd` on **Windows**

### Build docker image
##### Go to `/Chatbot/docker/` folder under the Chatbot project root folder from the `Terminal` or `cmd`

- Find your `Terminal` from `macOS`

	<img width="336" alt="Terminal" src="https://user-images.githubusercontent.com/1511528/28262080-5ba1002c-6b14-11e7-9522-aa395d4cf35e.png">
	<img width="572" alt="screen shot 2017-07-17 at 17 39 40" src="https://user-images.githubusercontent.com/1511528/28262785-f3775b24-6b16-11e7-8e50-51b2e853ad64.png">

- Find your `cmd` from `Windows`

	<img width="730" alt="Command" src="https://user-images.githubusercontent.com/1511528/28262556-f55a4fec-6b15-11e7-93b2-2d81ee7cd3d3.png">
	<img width="730" alt="Command" src="https://user-images.githubusercontent.com/1511528/28262716-9c734888-6b16-11e7-831b-8c6d870d0a22.png">

- Find your project folder, e.g. `/path/to/Chatbot/docker`, then run the command

	`cd /path/to/Chatbot/docker`

- To build the docker image, run the command: 

	`docker build -t chatbot -f Dockerfile .`

### Run the docker image on a new container

- Run the command: 

	`docker run -d -p 8888:9080 chatbot`

##### Now you can visit your Chatbot web application via [http://localhost:8888](http://localhost:8888)

