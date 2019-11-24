package com.faceless;

import com.faceless.requests.RequestMapper;
import com.faceless.sql.Database;
import com.faceless.vmservice.containers.PropertyContainer;
import org.jsoup.nodes.Document;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public abstract class HttpServer {
	protected final int PORT;
	public static final Hashtable<String, PropertyContainer> propertyContainers = new Hashtable<>();
	protected final RequestMapper mapper = new RequestMapper();
	public Document mainPageDocument = new Utilities().readDocument("/mainpage.html");
	public Document loginPageDocument = new Utilities().readDocument("/loginpage.html");
	public Document orderVMPageDocument = new Utilities().readDocument("/order_vm.html");
	public Document lookVMPageDocument = new Utilities().readDocument("/look_vm.html");
	public Database database;

	public HttpServer(int port) {
		PORT = port;
	}


	public void runServer(String... args) throws Throwable {
		loadProperties();

		ServerSocket ss = new ServerSocket(PORT);//select socket to accept requests from
		//noinspection InfiniteLoopStatement
		while (true) {
			Socket socket = ss.accept();//lock until request accepted
			System.err.println("[INFO]Client accepted");
			new Thread(new SocketProcessor(this, socket)).start();//starting new thread to process request
		}
	}

	protected void loadProperties() {
		try {
			database = new Database();
			database.connect();
		} catch (Exception e) {
			System.out.println("ClassNotFoundException");
		}
	}

	public enum Paths {
		root("/"),
		getVar("/get"),
		setVar("/set"),
		createVar("/new"),
		deleteVar("/del"),
		loginPage("/loginpage"),
		vmOrderPage("/vmorderpage"),
		vmLookPage("/vmlookpage"),
		login("/login"),
		logout("/logout"),
		orderVM("/ordervm"),
		lookVms("/myvms"),
		removeVM("removevm"),
		editVM("/editvm");

		public String path;

		Paths(String path) {
			this.path = path;
		}
	}
}