package Client;

import Common.Account;
import Common.Message;
import Common.MessagingApp;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class MessagingClient
{
    public static void main(String[] args)
    {
        try
        {
            String IP = String.valueOf(args[0]);
            Integer port = Integer.valueOf(args[1]);
            Integer FN_ID = Integer.valueOf(args[2]);
            String arguments = String.valueOf(args[3]);

            //Connect to RMI Registry
            Registry rmiController = LocateRegistry.getRegistry(port);

            //Get reference for remote control
            MessagingApp messenger = (MessagingApp) rmiController.lookup("messenger");

            //Select between functions
            switch (FN_ID)
            {
                case 1:
                    int product = messenger.createAccount(arguments);
                    if (product == 1)
                        System.out.println("Sorry, the user already exists");
                    else if (product == -1)
                        System.out.println("Invalid Username");
                    else
                        System.out.println(product);
                    break;
                case 2:
                    ArrayList<Account> Accounts = messenger.showAccounts(Integer.valueOf(arguments));
                    if (!(Accounts.isEmpty()))
                    {
                        for (int i = 0;i<Accounts.size();i++)
                            System.out.println(i+1+"."+ Accounts.get(i).getUsername()+"\n");
                    }
                    break;
                case 3:
                    boolean status = messenger.sendMessage(Integer.valueOf(arguments));
                    if (status)
                        System.out.println("OK");
                    else
                        System.out.println("User does not exist");
                    break;
                case 4:
                    ArrayList<Message> inbox = messenger.showInbox(Integer.valueOf(arguments));
                    break;
                case 5:
                    Message message = messenger.readMessage(Integer.valueOf(arguments));
                    if (message != null)
                        System.out.println("("+message.getSender()+")"+message.getBody());
                    else
                        System.out.println("Message ID does not exist");
                    break;
                case 6:
                    status = messenger.deleteMessage(Integer.valueOf(arguments));
                    if (status)
                        System.out.println("OK");
                    else
                        System.out.println("Message does not exist");

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
