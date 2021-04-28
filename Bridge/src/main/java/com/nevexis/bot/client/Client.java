package com.nevexis.bot.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class Client {
	private Scanner scanner = new Scanner(System.in);

	protected WebSocketContainer container;
	protected Session userSession = null;

	public Client() {
		container = ContainerProvider.getWebSocketContainer();
	}

	public void connect(String sServer) throws DeploymentException, IOException, URISyntaxException {
		userSession = container.connectToServer(this, new URI(sServer));
	}

	public void sendMessage(String sMsg) throws IOException {
		userSession.getBasicRemote().sendText(sMsg);
	}

	@OnOpen
	public void onOpen(Session session) throws IOException {

	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws IOException {

	}

	@OnMessage
	public void onMessage(Session session, String msg) throws IOException {
			System.out.println(msg);
			
//			!!!!IF POSSIBLE//Message that comes in json format is a response status. Should be saved in a custom object. (For example on calling addOrder() the message given back from kraken
//			//looks sth like: {
//			  "descr": "buy 0.01770000 XBTUSD @ limit 4000",
//			  "event": "addOrderStatus",
//			  "status": "ok",
//			  "txid": "ONPNXH-KMKMU-F4MR5V"
//			})
//			the "txid"s are later used in cancelOrder()
//			!!!!
	}

	public void disconnect() throws IOException {
		userSession.close();
		scanner.close();
	}
}
