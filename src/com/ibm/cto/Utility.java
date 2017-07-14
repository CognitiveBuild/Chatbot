package com.ibm.cto;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Utility {

	public static HttpResponse invokeRequest(Request request, String username, String password, boolean useSSL) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException {
		Executor executor = getExecutor(useSSL);
		executor = executor.auth(username, password);

		HttpResponse httpResponse = invokeRequest(request, executor);
		return httpResponse;
	}

	private static Executor getExecutor(boolean useSSL) throws KeyManagementException, NoSuchAlgorithmException {
		Executor executor = null;
		if (useSSL){
			executor = Executor.newInstance(Utility.getTrustedHttpClient());
		}
		else{
			executor = Executor.newInstance();
		}
		return executor;
	}

	public static HttpResponse invokeRequest(Request request, Executor executor) throws ClientProtocolException, IOException {
		Response response = executor.execute(request);
		HttpResponse httpResponse = response.returnResponse();
		return httpResponse;
	}
	
	public static HttpResponse invokeRequest(Request request, boolean useSSL) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException {
		Response response = getExecutor(useSSL).execute(request);
		HttpResponse httpResponse = response.returnResponse();
		return httpResponse;
	}

	public static CloseableHttpClient getTrustedHttpClient() throws KeyManagementException, NoSuchAlgorithmException{
		CloseableHttpClient httpClient = null;

		SSLContext sslContext = SSLContext.getInstance("SSL");

		// set up a TrustManager that trusts everything
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) { }

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) { }
		} }, new SecureRandom());
		
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		PlainConnectionSocketFactory plainsf = new PlainConnectionSocketFactory();

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
	            .register("http", plainsf)
	            .register("https", sslsf)
	            .build();

		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

		return httpClient;
	}
	

	/**
	 * JSONArray to List of Object
	 * @param array
	 * @return List<Object>
	 */
	public static List<Object> toList(JSONArray array) {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.size(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}

	/**
	 * JSONObject to Map<String, Object>
	 * @param object
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> toMap(JSONObject object) {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<?> keyIterator = object.keySet().iterator();
	    while(keyIterator.hasNext()) {
	        String key = keyIterator.next().toString();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
}
