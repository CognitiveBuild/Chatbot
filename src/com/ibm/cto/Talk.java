package com.ibm.cto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestMessage = request.getParameter("message");
		String contextString = request.getParameter("context");
		JSONObject contextObject = new JSONObject();

		if(contextString != null)
			contextObject = JSONObject.parse(contextString);

		Map<String, Object> contextMap = toMap(contextObject);

		if(requestMessage == null){
			requestMessage = "Greetings";
		}
		if(requestMessage.equals("")){
			JSONObject error = new JSONObject();
			error.put("message", "error");
			error.put("status", "failure");
			error.put("code", 400);
			response.getWriter().append(error.toString());
			return;
		}

		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_09_20);
		service.setUsernameAndPassword(Consts.CONVERSATION_USERNAME, Consts.CONVERSATION_PASSWORD);

		MessageRequest newMessage = new MessageRequest.Builder().context(contextMap).inputText(requestMessage).build();

		MessageResponse r = service.message(Consts.WORKSPACE_ID, newMessage).execute();

		response.getWriter().append(r.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static Map<String, Object> jsonToMap(JSONObject json) {
	    Map<String, Object> retMap = new HashMap<String, Object>();

	    if(JSONObject.isValidObject(json)) {
	        retMap = toMap(json);
	    }
	    return retMap;
	}
	
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

	public static Map<String, Object> toMap(JSONObject object) {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<?> keysItr = object.keySet().iterator();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next().toString();
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
