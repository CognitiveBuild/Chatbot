package com.ibm.cto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

/**
 * Servlet implementation class Synthesize
 */
@WebServlet("/Synthesize")
public class Synthesize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Synthesize() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("text");

		System.out.println(message);

		OutputStream out = response.getOutputStream();
		InputStream inputStream = getVoiceInputStream(message);
		response.setContentType("audio/wav");
		byte[] buffer = new byte[2048];
		int read;
		while ((read = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}

		out.flush();
		out.close();
	}

	/**
	 * Get voice stream
	 * 
	 * @param message
	 * @return
	 */
	public InputStream getVoiceInputStream(String message) {
		TextToSpeech service = new TextToSpeech(Configuration.getInstance().TEXT_TO_SPEECH_USERNAME, Configuration.getInstance().TEXT_TO_SPEECH_PASSWORD);
		service.setEndPoint(Configuration.getInstance().TEXT_TO_SPEECH_API_URL);

		ServiceCall<InputStream> serviceCall = service.synthesize(message, Voice.EN_MICHAEL, AudioFormat.WAV);

		return serviceCall.execute();
	}
}
