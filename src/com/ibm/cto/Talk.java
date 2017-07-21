package com.ibm.cto;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class Talk
 */
@WebServlet("/Talk")
public class Talk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Talk() {
        super();
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestMessage = request.getParameter("message");
		String contextString = request.getParameter("context");
		JSONObject contextObject = new JSONObject();

		if(contextString != null) {
			contextObject = JSONObject.parseObject(contextString);
		}

		System.out.println("Context: ");
		System.out.println(contextObject);

		Map<String, Object> contextMap = Utility.toMap(contextObject);

		if(requestMessage == null || requestMessage.isEmpty()){
			requestMessage = "Greetings";
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
		service.setUsernameAndPassword(Configuration.getInstance().CONVERSATION_USERNAME, Configuration.getInstance().CONVERSATION_PASSWORD);

		MessageRequest newMessage = new MessageRequest.Builder().context(contextMap).inputText(requestMessage).build();
		
		try {
			MessageResponse r = service.message(Configuration.getInstance().CONVERSATION_WORKSPACE_ID, newMessage).execute();

			response.getWriter().append(r.toString());
		}
		catch (Exception ex){

			JSONObject r = new JSONObject();
			JSONObject output = new JSONObject();
			JSONArray text = new JSONArray();
			text.add(ex.getLocalizedMessage());
			output.put("text", text);
			r.put("output", output);
			response.getWriter().append(r.toString());
		}
	}
}
