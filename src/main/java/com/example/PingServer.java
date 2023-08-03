package com.example;

import java.io.IOException;
import java.util.logging.Level;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.java.Log;

@Log
@ServerEndpoint("/ping")
public class PingServer {
	// クライアントから接続されたとき
	@OnOpen
	public void onOpen(Session session) {
		// session はこのクライアントとサーバのセッション
		try {
			// セッションから接続先のクライアントを取得してテキスト送信
			session.getBasicRemote().sendText("[Welcome! from PingServer]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 接続元のIPアドレスとURLをログで表示する
		log.log(Level.INFO, "onOpen");
	}

	// クライアントからメッセージを受信したとき
	@OnMessage
	public void onMessage(String mes, Session session) {
		// session はこのクライアントとサーバのセッション
		try {
			// セッションから接続先のクライアントを取得してテキスト送信
			session.getBasicRemote().sendText(mes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// エラーが発生したとき
    @OnError
    public void onError(Session session, Throwable e) {
		e.printStackTrace();
    }

	// クライアントから切断されたとき
	@OnClose
	public void onClose(Session session, CloseReason reason) {
		// 切断された理由をコンソールへ表示
		System.out.print(reason);
	}
	
}
