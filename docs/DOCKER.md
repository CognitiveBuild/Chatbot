# Install Docker

Before start, install the Docker for this practice, see the references [here](https://www.docker.com/community-edition#/download) about how to install the Docker for different platforms.

### Install Docker on Windows

Below is the guide especially for `Windows` as the `macOS` one is very easy and straightforward:

1) In order to use Docker, disable `Hyper-v` by using `cmd` `(Windows 10 only)`:

    `bcdedit /set hypervisorlaunchtype off`

   In case you want to re-enable it after practice, here is the command:

    `bcdedit /set hypervisorlaunchtype auto`

2) Install the Docker Toolbox, and make sure the `Virtualization` is enabled on `Windows`. You can read [this](https://docs.docker.com/toolbox/toolbox_install_windows/) for reference, then click on `Get Docker Toolbox for Windows` to [start downloading the installer](https://download.docker.com/win/stable/DockerToolbox.exe).

3) After installing the Docker Toolbox, click the `Quick Start Terminal` on the desktop. If you have passed previous 2 steps, you will see a page for downloading one `boot2docker.iso` file. You can manually download and put it into `C:\Users\your_username\.docker\machine\cache` folder. (Copy the `boot2docker.iso` from the USB provided on the class)

4) You can close the `Quick Start Terminal` window, then use `cmd` (refer to `Build Docker image` section on this page). Run a command:

    `docker-machine ls`

Then you should be able to see the default virtual machine.

5) Run `docker-machine env default` command. It will show some information about your environment. Choose the last row and run it manually. Then you will be able to run Docker in your `cmd` window, so here are the commands you need to execute:

	`docker-machine env default`

	`@FOR /f "TOKENS=*" %i IN ('docker-machine env default') DO %i`

	![Docker](https://user-images.githubusercontent.com/1511528/28406219-2e20f0c6-6d62-11e7-8282-673a1947f36d.png)

6) Remember the `IP address` in 2nd line as on `Windows`, you will use it to access the application you're deploying on Docker. 

You cannot just use localhost to access the service. Use the IP instead. The reason is there is a boot2docker VM running by VMBox. Docker connect with the VM directly. The IP is for the VM.

* Note: You can use Virtual Box to check your `docker-machine` instance. If you are experiencing the `VT-x` problem, please enable the `Virtualization` in the BIOS, read the docs below about how to enable virtualization: 

	<img width="730" alt="Docker" src="https://user-images.githubusercontent.com/1511528/28660510-cca3bd86-72e5-11e7-8a32-5ffc1430955a.png">
	<img width="730" alt="Docker" src="https://user-images.githubusercontent.com/1511528/28660509-cca2773c-72e5-11e7-8864-d904ad6a37a1.png">

- [English version](https://access.redhat.com/documentation/en-US/Red_Hat_Enterprise_Linux/5/html/Virtualization/sect-Virtualization-Troubleshooting-Enabling_Intel_VT_and_AMD_V_virtualization_hardware_extensions_in_BIOS.html)
- [Chinese version](http://jingyan.baidu.com/article/fc07f98976710e12ffe519de.html)

If there is an error of "this kernel requires an x86-64 CPU, but only detects an i686 CPU, unable to boot", please follow this [guide](https://askubuntu.com/questions/308937/cannot-install-ubuntu-in-virtualbox-due-to-this-kernel-requires-an-x86-64-cpu)

#### Install Docker on macOS

- Please refer the doc [here](https://store.docker.com/editions/community/docker-ce-desktop-mac)
- Find `Docker` app in the macOS's Launchpad, then click on it to start it

	<img width="354" alt="Docker" src="https://user-images.githubusercontent.com/1511528/28486956-bc904e16-6eba-11e7-97fc-acf197192861.png">
	<img width="498" alt="Docker started" src="https://user-images.githubusercontent.com/1511528/28487013-acdb2562-6ebb-11e7-899c-a1718cbbd9b8.png">

### Stop here and follow the `Blockchain` practice first.

Please [read this reference](https://github.com/yidlhu/blockchaindemo/blob/master/README.md) carefully, and finish the Blockchain practice.
