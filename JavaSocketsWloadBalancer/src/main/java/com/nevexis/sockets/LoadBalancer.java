package com.nevexis.sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class LoadBalancer {
	
	private static final int BALANCER_PORT = 8080;
	private static boolean running = true;
	private static int activeThreads = 0;

	synchronized private static void incrementActiveThreadsCounter() {
		activeThreads++;
	}

	synchronized private static void decrementActiveThreadsCounter() {
		activeThreads--;
	}
	
	private static void shutdown() throws InterruptedException {
		running = false;
		while (activeThreads !=0 ) {
			Thread.sleep(50);
		}
	}
	
	public static void main(final String[] args) throws IOException {
		ServerSocket balancerSocket = new ServerSocket(BALANCER_PORT);

		int portsIndex = 0;
		while (running) {
			if (portsIndex == args.length) {
				portsIndex = 0;
			}
			final int port = Integer.parseInt(args[portsIndex]);

			final Socket clientSocket = balancerSocket.accept();

			WorkerPool.getExecutorService().submit(new Runnable() {

				@Override
				public void run() {
					incrementActiveThreadsCounter();
					try (Scanner clientScanner = new Scanner(clientSocket.getInputStream());
							Socket transfSocket = new Socket("localhost", port);
							Scanner scan2 = new Scanner(transfSocket.getInputStream());
							PrintStream printer = new PrintStream(transfSocket.getOutputStream());) {

						String clientName = clientScanner.nextLine();
						if (clientName.equals("SHUTDOWN")) {
							shutdown();
							return;
						}
						
						printer.println(clientName);
						String msg = scan2.nextLine();
						PrintStream printout = new PrintStream(clientSocket.getOutputStream());
						printout.println(msg);
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					} finally {
						decrementActiveThreadsCounter();
					}
				}
			});
			portsIndex++;
		}
	}
}
