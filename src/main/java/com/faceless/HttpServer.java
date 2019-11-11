package com.faceless;

import com.faceless.containers.PropertyContainer;
import com.faceless.filters.PermissionFilter;
import com.faceless.handlers.*;
import com.faceless.requests.RequestMapper;
import com.faceless.sql.Database;
import org.jsoup.nodes.Document;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer
{
	private static final int               PORT                = 8080;
	public final         PropertyContainer propertyContainer   = new PropertyContainer();
	final                RequestMapper     mapper              = new RequestMapper();
	public               Document          mainPageDocument    = new Utilities().readDocument("/mainpage.html");
	public               Document          loginPageDocument   = new Utilities().readDocument("/loginpage.html");
	public               Document          orderVMPageDocument = new Utilities().readDocument("/order_vm.html");
	public               Document          lookVMPageDocument  = new Utilities().readDocument("/look_vm.html");
	public               Database          database;

	public void runServer(String... args) throws Throwable
	{
		loadProperties();

		ServerSocket ss = new ServerSocket(PORT);//select socket to accept requests from
		//noinspection InfiniteLoopStatement
		while (true)
		{
			Socket socket = ss.accept();//lock until request accepted
			System.err.println("[INFO]Client accepted");
			new Thread(new SocketProcessor(this, socket)).start();//starting new thread to process request
		}
	}

	private void loadProperties()
	{
		int initialNumber = 25;
		propertyContainer.setProperty("counter", Integer.toString(initialNumber));
		propertyContainer.setProperty("logged_in", false);
		mapper.registerHandler(Paths.root, new MainHandler());
		mapper.registerHandler(Paths.getVar, new GetValueHandler());
		mapper.registerHandler(Paths.setVar, new SetValueHandler());
		mapper.registerHandler(Paths.createVar, new NewValueHandler());
		mapper.registerHandler(Paths.deleteVar, new DeleteValueHandler());
		mapper.registerHandler(Paths.loginPage, new LoginPageHandler());
		mapper.registerHandler(Paths.vmOrderPage, new OrderVMPageHandler());
		mapper.registerHandler(Paths.vmLookPage, new LookVMPageHandler());
		mapper.registerHandler(Paths.login, new LoginHandler());
		mapper.registerHandler(Paths.logout, new LogoutHandler());
		mapper.registerHandler(Paths.orderVM, new CreateVMHandler());
		mapper.registerHandler(Paths.lookVms, new VmListHandler());
		mapper.registerHandler(Paths.removeVM, new RemoveVMHandler());
		mapper.registerHandler(Paths.editVM, new VMCharacteristicsHandler());
		mapper.registerFilter(new PermissionFilter(),
				Paths.vmOrderPage,
				Paths.vmLookPage,
				Paths.removeVM,
				Paths.editVM,
				Paths.logout);

		try
		{
			database = new Database();
			database.connect();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("ClassNotFoundException");
		}
	}

	public enum Paths
	{
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
		orderVM("ordervm"),
		lookVms("/myvms"),
		removeVM("removevm"),
		editVM("/editvm");

		public String path;

		Paths(String path)
		{
			this.path = path;
		}
	}
}