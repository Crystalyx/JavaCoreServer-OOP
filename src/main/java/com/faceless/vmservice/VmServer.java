package com.faceless.vmservice;

import com.faceless.HttpServer;
import com.faceless.vmservice.filters.LoginFilter;
import com.faceless.vmservice.handlers.*;

public class VmServer extends HttpServer {
    public VmServer() {
        super(8080);
    }

    @Override
    protected void loadProperties() {
        System.out.println("localhost:" + this.PORT);
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
        mapper.registerFilter(new LoginFilter(),
                Paths.vmOrderPage,
                Paths.vmLookPage,
                Paths.removeVM,
                Paths.editVM,
                Paths.logout);

        super.loadProperties();
    }
}
