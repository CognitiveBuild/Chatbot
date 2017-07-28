# Host your Chatbot on Docker using command only

### Open your `Terminal` on macOS/Linux or `cmd` on Windows

- Go to `/Chatbot-docker/docker/` folder under the Chatbot project root folder from the `Terminal` or `cmd`

- Find your `Terminal` from `macOS`

	<img width="336" alt="Terminal" src="https://user-images.githubusercontent.com/1511528/28262080-5ba1002c-6b14-11e7-9522-aa395d4cf35e.png">
	<img width="572" alt="cmd" src="https://user-images.githubusercontent.com/1511528/28262785-f3775b24-6b16-11e7-8e50-51b2e853ad64.png">

- Find your `cmd` from `Windows`

	<img width="730" alt="Command" src="https://user-images.githubusercontent.com/1511528/28262556-f55a4fec-6b15-11e7-93b2-2d81ee7cd3d3.png">
	<img width="730" alt="Command" src="https://user-images.githubusercontent.com/1511528/28262716-9c734888-6b16-11e7-831b-8c6d870d0a22.png">

- Find your project folder, e.g. `/path/to/Chatbot-docker/docker`, then run the command:

    `cd /path/to/Chatbot-docker/docker`

### Pull Docker image

	`docker pull cognitivecoe/webchatbot:latest`

### Environment settings

- Update `/Chatbot-docker/docker/Docker.env` with credentials

* Please be aware of the variables of the values DO NOT HAVE quotes and between key and value there is a `=`, e.g. `key`=`value`

- Good example:

	```shell
	VISUAL_RECOGNITION_API_KEY=abcdefghijklmnopqrstuvwxyz12345678901234
	```

- Bad example

	```shell
	VISUAL_RECOGNITION_API_KEY="abcdefghijklmnopqrstuvwxyz12345678901234"
	```

- Update `/Chatbot-docker/docker/Docker.env` with your Blockchain service UR, replace it with actual one if you're hosting the Blockchain service on the Bluemix (refer to Blockchain practice)

    APPLICATION_API_URL=https://your_block_chain_service.mybluemix.net

If you are hosting the Blockchain service locally, replace the URL with the localhost service URL:

	APPLICATION_API_URL=http://localhost:3000

Now you're ready for building the `Docker` image.

### Build Docker image

	`docker run -p 8888:9080 --env-file Docker.env cognitivecoe/webchatbot`

### Launch

Now after getting this message `The server defaultServer is ready to run a smarter planet.`, you can visit your Chatbot web application via:

- macOS: [http://localhost:8888/](http://localhost:8888/)
- Windows:  Refer to `Install docker on Windows 10 (5, 6)` section of the [doc](DOCKER.md), so the format of the URL would be like `http://xxx.xxx.x.x:8888/`
