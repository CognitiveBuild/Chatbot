package com.ibm.cto;

import java.util.Iterator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Configuration {

	private static Configuration instance = null;

	public static final String SERVICE_SPEECH_TO_TEXT = "speech_to_text";
	public static final String SERVICE_TEXT_TO_SPEECH = "text_to_speech";
	public static final String SERVICE_CONVERSATION = "conversation";

	public static final String TOKEN_API_URL = "https://stream.watsonplatform.net/authorization/api/v1/token";

	public String SPEECH_TO_TEXT_API_URL = "https://stream.watsonplatform.net/speech-to-text/api";
	public String TEXT_TO_SPEECH_API_URL = "https://stream.watsonplatform.net/text-to-speech/api";
	public String CONVERSATION_API_URL = "https://gateway.watsonplatform.net/conversation/api";

	/**
	 * TODO: If you're testing this application locally, please get the credentials from Bluemix
	 */
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

	/**
	 * Load credentials and URLs
	 * 
	 * @return VCAPConfiguration
	 */
	public static Configuration getInstance() {
		if(instance == null) {
			instance = new Configuration();
			instance.CONVERSATION_WORKSPACE_ID = System.getenv("CONVERSATION_WORKSPACE_ID");

			System.out.println("### Conversation Workspace ID ###");
			System.out.println(instance.CONVERSATION_WORKSPACE_ID);
			System.out.println("### /Conversation Workspace ID ###");

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
			
			Iterator<String> iterator = vcapConfig.keySet().iterator();
			while(iterator.hasNext()){
				String keyString = iterator.next();
				JSONObject valueObject = vcapConfig.getJSONObject(keyString);
				System.out.println("### Key ###");
				System.out.println(keyString);
				System.out.println("### /Key ###");

				if (keyString.startsWith(SERVICE_CONVERSATION)) {
					JSONObject credentials = queryObjectByKey(valueObject, "credentials");
					instance.CONVERSATION_USERNAME = credentials.get("username").toString();
					instance.CONVERSATION_PASSWORD = credentials.get("password").toString();
					instance.CONVERSATION_API_URL = credentials.get("url").toString();
				}
				else if(keyString.startsWith(SERVICE_SPEECH_TO_TEXT)) {
					JSONObject credentials = queryObjectByKey(valueObject, "credentials");
					instance.SPEECH_TO_TEXT_USERNAME = credentials.get("username").toString();
					instance.SPEECH_TO_TEXT_PASSWORD = credentials.get("password").toString();
					instance.SPEECH_TO_TEXT_API_URL = credentials.get("url").toString();
				}
				else if(keyString.startsWith(SERVICE_TEXT_TO_SPEECH)) {
					JSONObject credentials = queryObjectByKey(valueObject, "credentials");
					instance.TEXT_TO_SPEECH_USERNAME = credentials.get("username").toString();
					instance.TEXT_TO_SPEECH_PASSWORD = credentials.get("password").toString();
					instance.TEXT_TO_SPEECH_API_URL = credentials.get("url").toString();
				}
			}

		}

		return instance;
	}

	/**
	 * Query JSONObject by indicating a key
	 * 
	 * @param config
	 * @param key
	 * @return JSONObject
	 */
	private static JSONObject queryObjectByKey(JSONObject config, String key) {
		JSONArray services = config.getJSONArray(key);
		JSONObject service = services.getJSONObject(0);
		JSONObject queriedObject = service.getJSONObject(key);
		return queriedObject;
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
}
