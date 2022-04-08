package com.alkemy.disney.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

public class ServerWebSocketHandler extends TextWebSocketHandler {
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    String request = message.getPayload();
	    String response = String.format("MUTHER from server to '%s'", HtmlUtils.htmlEscape(request));
	    session.sendMessage(new TextMessage(response));
	    session.close(CloseStatus.NORMAL);
	}
	

}
