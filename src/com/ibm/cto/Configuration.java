package com.ibm.cto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Configuration {

	private static Configuration instance = null;

	public static final String SERVICE_SPEECH_TO_TEXT = "speech_to_text";
	public static final String SERVICE_TEXT_TO_SPEECH = "text_to_speech";
	public static final String SERVICE_CONVERSATION = "conversation";
	public static final String SERVICE_VISUAL_RECOGNITION = "watson_vision";
	public static final String TOKEN_API_URL = "https://stream.watsonplatform.net/authorization/api/v1/token";

	public String SPEECH_TO_TEXT_API_URL = "https://stream.watsonplatform.net/speech-to-text/api";
	public String TEXT_TO_SPEECH_API_URL = "https://stream.watsonplatform.net/text-to-speech/api";
	public String CONVERSATION_API_URL = "https://gateway.watsonplatform.net/conversation/api";

	public String APPLICATION_API_URL = "";
	
	public String TEXT_TO_SPEECH_USERNAME = "";
	public String TEXT_TO_SPEECH_PASSWORD = "";

	public String SPEECH_TO_TEXT_USERNAME = "";
	public String SPEECH_TO_TEXT_PASSWORD = "";

	public String CONVERSATION_USERNAME = "";
	public String CONVERSATION_PASSWORD = "";

	/**
	 * TODO: Get Workspace ID from IBM Watson Conversation: https://ibmwatsonconversation.com
	 */
	public String CONVERSATION_WORKSPACE_ID = "";
	
	public String VISUAL_RECOGNITION_API_KEY = "";
	public String VISUAL_RECOGNITION_CLASSIFIER_ID = "";

	/**
	 * Load credentials and URLs
	 * 
	 * @return VCAPConfiguration
	 */
	public static Configuration getInstance() {

		if(instance == null) {
			instance = new Configuration();
			String CONVERSATION_WORKSPACE_STRING = System.getenv("CONVERSATION_WORKSPACE_ID");

			if(CONVERSATION_WORKSPACE_STRING == null) {
				System.out.println("### No environment variables, using default hardcode settings ###");

				// TODO: [Required] Blockchain API Endpoint
				instance.APPLICATION_API_URL = "https://webchatbot-blockchain.mybluemix.net";
				// TODO: [Required] Visual Recognition API key and trained classifier ID
				instance.VISUAL_RECOGNITION_API_KEY = "";
				instance.VISUAL_RECOGNITION_CLASSIFIER_ID = "";

				// [Optional] If you're testing this application locally, please get the credentials from Bluemix
				instance.TEXT_TO_SPEECH_USERNAME = "";
				instance.TEXT_TO_SPEECH_PASSWORD = "";
				instance.SPEECH_TO_TEXT_USERNAME = "";
				instance.SPEECH_TO_TEXT_PASSWORD = "";
				instance.CONVERSATION_USERNAME = "";
				instance.CONVERSATION_PASSWORD = "";
				instance.CONVERSATION_WORKSPACE_ID = "";

				return instance;
			}
			else {
				System.out.println("### Using environment settings ###");
				instance.CONVERSATION_WORKSPACE_ID = CONVERSATION_WORKSPACE_STRING;

				instance.TEXT_TO_SPEECH_USERNAME = System.getenv("TEXT_TO_SPEECH_USERNAME");
				instance.TEXT_TO_SPEECH_PASSWORD = System.getenv("TEXT_TO_SPEECH_PASSWORD");
				instance.SPEECH_TO_TEXT_USERNAME = System.getenv("SPEECH_TO_TEXT_USERNAME");
				instance.SPEECH_TO_TEXT_PASSWORD = System.getenv("SPEECH_TO_TEXT_PASSWORD");
				instance.CONVERSATION_USERNAME = System.getenv("CONVERSATION_USERNAME");
				instance.CONVERSATION_PASSWORD = System.getenv("CONVERSATION_PASSWORD");
				instance.APPLICATION_API_URL = System.getenv("APPLICATION_API_URL");
				instance.VISUAL_RECOGNITION_API_KEY = System.getenv("VISUAL_RECOGNITION_API_KEY");
				instance.VISUAL_RECOGNITION_CLASSIFIER_ID = System.getenv("VISUAL_RECOGNITION_CLASSIFIER_ID");
			}

			System.out.println("### Extra settings from manifest.yml ###");
			System.out.println(instance.CONVERSATION_WORKSPACE_ID);
			System.out.println(instance.APPLICATION_API_URL);
			System.out.println("### /Extra settings from manifest.yml ###");

			JSONObject vcapConfig = getObjectSettings("VCAP_SERVICES");

			if(vcapConfig == null) {
				System.out.println("VCAP_SERVICES is invalid:");
				return instance;
			}
			else {
				System.out.println("### VCAP_SERVICES ###");
				System.out.println(vcapConfig);
				System.out.println("### /VCAP_SERVICES ###");
			}
			Set<String> keySet = vcapConfig.keySet();

			for (String serviceKey : keySet) {
				System.out.println("### Iterating key: " + serviceKey + " ###");
				JSONArray serviceList = vcapConfig.getJSONArray(serviceKey);
				if(serviceList.size() > 0) {
					JSONObject serviceItem = serviceList.getJSONObject(0);
					String credentialsKey = "credentials";

					if(serviceItem.containsKey(credentialsKey)) {
						JSONObject serviceCredentials = serviceItem.getJSONObject(credentialsKey);

						if (serviceKey.startsWith(SERVICE_CONVERSATION)) {
							instance.CONVERSATION_USERNAME = serviceCredentials.get("username").toString();
							instance.CONVERSATION_PASSWORD = serviceCredentials.get("password").toString();
							instance.CONVERSATION_API_URL = serviceCredentials.get("url").toString();
						}
						else if(serviceKey.startsWith(SERVICE_SPEECH_TO_TEXT)) {
							instance.SPEECH_TO_TEXT_USERNAME = serviceCredentials.get("username").toString();
							instance.SPEECH_TO_TEXT_PASSWORD = serviceCredentials.get("password").toString();
							instance.SPEECH_TO_TEXT_API_URL = serviceCredentials.get("url").toString();
						}
						else if(serviceKey.startsWith(SERVICE_TEXT_TO_SPEECH)) {
							instance.TEXT_TO_SPEECH_USERNAME = serviceCredentials.get("username").toString();
							instance.TEXT_TO_SPEECH_PASSWORD = serviceCredentials.get("password").toString();
							instance.TEXT_TO_SPEECH_API_URL = serviceCredentials.get("url").toString();
						}
						else if(serviceKey.startsWith(SERVICE_VISUAL_RECOGNITION)) {
							instance.VISUAL_RECOGNITION_API_KEY = serviceCredentials.get("api_key").toString();
						}
						else {
							System.out.println("### No such key: " + serviceKey);
						}
					}
					else {
						System.out.println("### No such key: " + credentialsKey);
					}

				}
				else {
					System.out.println("### Empty service list ###");
				}
			}
		}

		return instance;
	}


	/**
	 * Get VCAP configurations
	 * 
	 * @return JSONObject
	 */
	public static JSONObject getObjectSettings(String key) {
		String envServices = System.getenv(key);
		if (envServices == null)
			return null;
		JSONObject sysEnv = null;
		sysEnv = JSONObject.parseObject(envServices);
		return sysEnv;
	}
	public String getAppURL() {
		return instance.APPLICATION_API_URL;
	}
	public String getLayout() {
		if(instance.TEXT_TO_SPEECH_USERNAME == "" ||
				instance.TEXT_TO_SPEECH_USERNAME == "your_username" ||
		instance.TEXT_TO_SPEECH_PASSWORD == "" ||
				instance.TEXT_TO_SPEECH_PASSWORD == "your_password" ||
		instance.SPEECH_TO_TEXT_USERNAME == "" ||
				instance.SPEECH_TO_TEXT_USERNAME == "your_username" ||
		instance.SPEECH_TO_TEXT_PASSWORD == "" ||
				instance.SPEECH_TO_TEXT_PASSWORD == "your_password" ||
		instance.CONVERSATION_USERNAME == "" ||
				instance.CONVERSATION_USERNAME == "your_username" ||
		instance.CONVERSATION_PASSWORD == "" ||
				instance.CONVERSATION_PASSWORD == "your_password" ||
		instance.CONVERSATION_WORKSPACE_ID  == "" || 
			instance.CONVERSATION_WORKSPACE_ID  == "your_work_space_id") 
		{
			return "non-conversation";
		}
		return "conversation";
	}
}
