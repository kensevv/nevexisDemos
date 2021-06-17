package com.nevexis.sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
	private static boolean running = true;

	public static void main(String[] args) throws IOException {
		final ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[1]));

		while (running) {
			try (final Socket socket = serverSocket.accept();
					Scanner scan = new Scanner(socket.getInputStream());
					PrintStream printout = new PrintStream(socket.getOutputStream());) {
				printout.println(serverSocket.getLocalPort() + " ; HELLO " + scan.nextLine());
			}
		}
	}
}