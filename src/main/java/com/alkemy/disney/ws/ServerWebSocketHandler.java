package com.alkemy.disney.ws;

import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

public class ServerWebSocketHandler extends TextWebSocketHandler {
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    String request = message.getPayload();
	    
	    try {
	    	
	    	JsonParser jp = JsonParserFactory.getJsonParser();
	    	
	    	Map<String, Object> map = jp.parseMap(request);
	    	
	    	Object jwt = map.get("token");
	    	
	    	String response = String.format("Your token is: %s", jwt);
	    	
		    session.sendMessage(new TextMessage(response));
		    
		    Object close = map.get("close");
		    
		    if(Boolean.parseBoolean(close.toString())) {
		    	session.close();
		    }
	    	
	    }catch(Exception e) {
	    	System.out.println("Error parsing JSON");
	    }

	}
	

}
