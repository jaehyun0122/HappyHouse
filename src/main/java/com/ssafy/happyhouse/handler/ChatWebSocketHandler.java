package com.ssafy.happyhouse.handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler{
//	여러 사람과 채팅을 하기 위해 세션을 저장해야함. = > 그냥 ArrayList에 담으면 안됨 = > 쓰레드에 불안전
//	쓰레드에 안전한 CopyOnWriteArrayList() 사용
	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	
//	연결 될 때마다 세션 담기.
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		System.out.println("새로운 사용자가 연결되었습니다");
		System.out.println("현재 접속 인원 : "+sessions.size()+"명");
	}
	
//	연결 해제될때마다 세션 삭제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
		System.out.println("연결이 해제되었습니다.");
		System.out.println("현재 접속 인원 : "+sessions.size()+"명");
	}
	

	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
//		받은 메시지를 클라이언트로 다시 전송.
		//session.sendMessage(message); // message.getPlayload();
		System.out.println(message);
//		접속한 사람들 모두의 세션에 전송
		for(WebSocketSession wsession : sessions)
			wsession.sendMessage(message);
	}
	
}
