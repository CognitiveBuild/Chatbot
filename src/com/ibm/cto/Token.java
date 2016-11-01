package com.ibm.cto;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Servlet implementation class Token
 */
@WebServlet("/Token")
public class Token extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String categoryTTS = "tts";
	public static String categorySTT = "stt";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Token() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		
		if(category.equals(categoryTTS) || category.equals(categorySTT)){
			response.getWriter().append(getToken(categorySTT));
		}
		else {
			response.getWriter().append("Invalid category");
		}
	}

	public static String getToken(String category){
		String token = "";
		HttpEntity entity = getTokenEntity(category);
		InputStream inputStream;
		try {
			inputStream = entity.getContent();
			if(inputStream != null) {
				if(inputStream.available() > 0) {
					byte[] buffer = new byte[4096];
					int length = 0;
					StringBuilder sb = new StringBuilder();
					while((length = inputStream.read(buffer)) > 0) {
						sb.append(new String(buffer, 0, length));
					}
					token = sb.toString();
				}
				inputStream.close();
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return token;
	}

	public static HttpEntity getTokenEntity(String category){
		String url = "";
		String username = "", password = "";
		if(category.equals(categorySTT)) {
			url = Consts.TOKEN_API_URL + "?url=" + Consts.STT_API_URL;
			username = Consts.STT_USERNAME;
			password = Consts.STT_PASSWORD;
		}
		else if(category.equals(categoryTTS)) {
			url = Consts.TOKEN_API_URL + "?url=" + Consts.TTS_API_URL;
			username = Consts.TTS_USERNAME;
			password = Consts.TTS_PASSWORD;
		}
		System.out.println("url: "+url);
		if(url.length() > 0) {
			try {
				URI converseURI = new URI(url).normalize();
				Request request = Request.Get(converseURI);
				HttpResponse httpResponse = invokeRequest(request, username, password, true);
				if(httpResponse.getStatusLine().getStatusCode() == 200){
					HttpEntity entity = httpResponse.getEntity();
					return entity;
				}
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static HttpResponse invokeRequest(Request request, String username, String password, boolean useSSL) {
		Executor executor = null;
		if (useSSL){
			executor = Executor.newInstance(getTrustedHttpsClient());
		}
		else{
			executor = Executor.newInstance();
		}
		executor = executor.auth(username, password);

		return invokeRequest(request, executor);
	}

	public static HttpResponse invokeRequest(Request request, boolean useSSL) {
		Executor executor = null;
		if (useSSL){
			executor = Executor.newInstance(getTrustedHttpsClient());
		}
		else{
			executor = Executor.newInstance();
		}
		return invokeRequest(request, executor);
	}

	private static CloseableHttpClient getTrustedHttpsClient(){
		CloseableHttpClient httpClient = null;

		httpClient = HttpClients.custom().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

		return httpClient;
	}

	public static HttpResponse invokeRequest(Request request, Executor executor) {
		try {
			Response response = executor.execute(request);
			HttpResponse httpResponse = response.returnResponse();
			return httpResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
