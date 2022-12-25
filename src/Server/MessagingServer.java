/*
    Server implementation class.
    Author: Georgios Atmatzidis
    AEM: 3908
 */
package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessagingServer
{
    public static void main(String[] args)
    {
        try
        {
            //Get port number
            int port = Integer.parseInt(args[0]);

            //Launch Messaging App
            RemoteMessagingApp app = new RemoteMessagingApp();

            //Create RMI Registry/Stub
            Registry rmiRegistry = LocateRegistry.createRegistry(port);

            //path to access is rmi://localhost:port/messenger
            rmiRegistry.bind("messenger",app);
            System.out.println("Server is ready!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
