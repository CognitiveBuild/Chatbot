package com.ibm.cto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;

import org.apache.http.util.EntityUtils;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.FindSimilarImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

/**
 * Servlet implementation class Application
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Application" })
@MultipartConfig
public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Application() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");

		switch(category) {
			case "icecream":
			case "delivery":
			case "wipe":
				String payload = request.getParameter("payload");
				// @TODO: This `APPLICATION_API_URL` should be changed to real IBM Blockchain API endpoint
				String url = Configuration.getInstance().APPLICATION_API_URL + (category.equals("delivery") ? "/api/sensor/newinfo" : (category.equals("wipe") ? "/api/sensor/ResetIOTTable" : "/api/order/newinfo")) + "?category="+category;
				String method = "POST";
				if(category.equals("wipe")) {
					method = "GET";
				}
				System.out.println("### Request ###");
				System.out.println(url);
				System.out.println(category);
				System.out.println(payload);
				System.out.println("### /Request ###");
				try {
					String result = sendRequest(url, payload, method);
					System.out.println(result);
					response.getWriter().append(result);
					response.getWriter().flush();
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(400, "Invalid request");
				}
				break;
			case "vr":
				VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
				service.setApiKey(Configuration.getInstance().VISUAL_RECOGNITION_API_KEY);

				Part filePart = request.getPart("file");
				InputStream fis = filePart.getInputStream();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int read = 0;
				final byte[] bytes = new byte[1024];
				
				while((read = fis.read(bytes)) != -1) {
					bos.write(bytes, 0, read);
				}

				byte[] imageBytes = bos.toByteArray();

				System.out.println("Classify an image: " + imageBytes.length + " bytes");
				ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
				    .images(imageBytes, "Cooler.jpg").classifierIds(Configuration.getInstance().VISUAL_RECOGNITION_CLASSIFIER_ID)
				    .build();
				VisualClassification result = service.classify(options).execute();
				System.out.println(result);
				fis.close();
				bos.close();
				
				response.getWriter().append(result.toString());
				response.getWriter().flush();
				break;
		}
	}

	private String sendRequest(String url, String payload, String method) throws Exception {
		URI converseURI = new URI(url).normalize();
		
		StringEntity entity = new StringEntity(payload);

		Request request;

		if(method == "POST") {
			request = Request.Post(converseURI).body(entity).addHeader("Content-Type", "application/json");
		}
		else {
			request = Request.Get(converseURI).addHeader("Content-Type", "application/json");
		}
		
		HttpResponse httpResponse = Utility.invokeRequest(request, url.startsWith("https"));
		int code = httpResponse.getStatusLine().getStatusCode();
		if(code == 200 || code == 201) {
			HttpEntity responseEntity = httpResponse.getEntity();
			String responseString = EntityUtils.toString(responseEntity);
			return responseString;
		}
		throw new Exception("Invalid request URL: " + httpResponse.getStatusLine().getStatusCode());
	}

}
