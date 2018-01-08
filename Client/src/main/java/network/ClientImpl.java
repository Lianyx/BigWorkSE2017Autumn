package network;


import dataService.test.TestData;
import dataService.test.TestdataService2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client{


    static Server server;

    protected ClientImpl() throws RemoteException {

    }


    @Override
    public void doSomething(String s) throws RemoteException {
        System.out.println(s);

    }





    public Object dataInteration(String klassname,String method,Object object) throws Exception{
        return server.dataInteration(klassname,method,object);
    }




    public static void main(String args[]){
        try
        {
            String registry = "localhost";
            int port=1099;
            String registration = "rmi://" + registry + ":" + port + "/Server";
            Remote remoteService = Naming.lookup ( registration );
            server = (Server) remoteService;

            Client client = new ClientImpl();
            server.addClient(client);
            server.notify("hahaha");

            TestData testData = (TestData) Naming.lookup("rmi://" + registry + ":" + port + "/TestData");
            BufferedImage image = ImageIO.read(ClientImpl.class.getResource("/default/timg.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            String test1 = testData.test(baos.toByteArray());
            System.out.println(test1);
            TestdataService2 testdataService2 = (TestdataService2) Naming.lookup("rmi://" + registry + ":" + port + "/TestdataService2");
            String test2 = testdataService2.test();
            System.out.println(test2);

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
