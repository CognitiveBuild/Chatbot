package com.ibm.cto;

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

			JSONObject vcapConfig = getVCAPServices();

			if(vcapConfig == null) {
				
				return instance;
			}

			for (Object key : vcapConfig.keySet()) {
				String keyString = (String) key;
	
				if (keyString.startsWith(SERVICE_CONVERSATION)) {
					JSONObject credentials = queryObjectByKey(vcapConfig, "credentials");
					instance.CONVERSATION_USERNAME = credentials.getString("username");
					instance.CONVERSATION_PASSWORD = credentials.getString("password");
					instance.CONVERSATION_API_URL = credentials.getString("url");
				}
				else if(keyString.startsWith(SERVICE_SPEECH_TO_TEXT)) {
					JSONObject credentials = queryObjectByKey(vcapConfig, "credentials");
					instance.SPEECH_TO_TEXT_USERNAME = credentials.getString("username");
					instance.SPEECH_TO_TEXT_PASSWORD = credentials.getString("password");
					instance.SPEECH_TO_TEXT_API_URL = credentials.getString("url");
				}
				else if(keyString.startsWith(SERVICE_TEXT_TO_SPEECH)) {
					JSONObject credentials = queryObjectByKey(vcapConfig, "credentials");
					instance.TEXT_TO_SPEECH_USERNAME = credentials.getString("username");
					instance.TEXT_TO_SPEECH_PASSWORD = credentials.getString("password");
					instance.TEXT_TO_SPEECH_API_URL = credentials.getString("url");
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
		JSONArray services = (JSONArray) config.get(key);
		JSONObject service = (JSONObject) services.get(0);
		JSONObject queriedObject = (JSONObject) service.get(key);
		return queriedObject;
	}

	/**
	 * Get VCAP configurations
	 * 
	 * @return JSONObject
	 */
	public static JSONObject getVCAPServices() {
		String envServices = System.getenv("VCAP_SERVICES");
		System.out.println("VCAP_SERVICES:");
		System.out.println(envServices);
		if (envServices == null)
			return null;
		JSONObject sysEnv = null;
		sysEnv = JSONObject.parseObject(envServices);
		return sysEnv;
	}
}
