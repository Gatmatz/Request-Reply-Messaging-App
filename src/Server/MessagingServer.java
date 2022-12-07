package Server;

import Common.MessagingApp;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessagingServer
{
    public static void main(String[] args)
    {
        try
        {
            //Get port number
            Integer port = Integer.valueOf(args[0]);

            //Launch Messaging App
            RemoteMessagingApp app = new RemoteMessagingApp();

            //Create RMI Registry
            Registry rmiRegistry = LocateRegistry.createRegistry(port);

            //path to access is rmi://localhost:port/messenger
            rmiRegistry.bind("messenger",app);
            System.out.println("Server is ready!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
