# Setup local environment

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
	/**
	 * TODO: If you're testing this application locally, please get the credentials from Bluemix
	 */
	public String TEXT_TO_SPEECH_USERNAME = "9a5bfa13-624f-436e-8af1-fc677a59a123";
	public String TEXT_TO_SPEECH_PASSWORD = "berqyZXJ2J7f";

	public String SPEECH_TO_TEXT_USERNAME = "af3a6ecc-2f35-4672-2595-35e15bcd758a";
	public String SPEECH_TO_TEXT_PASSWORD = "KQ3itZUslHsc";

	public String CONVERSATION_USERNAME = "134f9b10-7d4a-4e4f-92a0-7372f67331f7";
	public String CONVERSATION_PASSWORD = "ijMoZB1vCVW6";

	/**
	 * TODO: Get Workspace ID from IBM Watson Conversation: https://ibmwatsonconversation.com
	 */
	public String CONVERSATION_WORKSPACE_ID = "1e28d5ef-7506-4e76-814e-e83f3cbe6816";
```
* **Right click** on the `Chatbot` project, choose `Run As` &gt; `Run on Server`, then choose `Tomcat v9.0 Server at localhost` to open `http://localhost:8080/Chatbot/` and see the running application

**Deploy your Chatbot on the Bluemix**
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
