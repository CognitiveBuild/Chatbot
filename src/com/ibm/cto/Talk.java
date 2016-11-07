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

		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_09_20);
		service.setUsernameAndPassword(Consts.CONVERSATION_USERNAME, Consts.CONVERSATION_PASSWORD);

		MessageRequest newMessage = new MessageRequest.Builder().context(contextMap).inputText(requestMessage).build();

		MessageResponse r = service.message(Consts.WORKSPACE_ID, newMessage).execute();

		response.getWriter().append(r.toString());
	}
}
