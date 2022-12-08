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

            //Connect to RMI Registry
            Registry rmiController = LocateRegistry.getRegistry(port);

            //Get reference for remote control
            MessagingApp messenger = (MessagingApp) rmiController.lookup("messenger");

            //Initialize helpful variables
            int authToken,product;
            String username,recipient,body;
            boolean status;

            //Select between functions
            switch (FN_ID)
            {
                //Create Account
                case 1:
                    username = String.valueOf(args[3]);
                    product = messenger.createAccount(username);
                    if (product == 1)
                        System.out.println("Sorry, the user already exists");
                    else if (product == -1)
                        System.out.println("Invalid Username");
                    else
                        System.out.println(product);
                    break;
                //Show Accounts
                case 2:
                    authToken = Integer.parseInt(args[3]);
                    ArrayList<Account> Accounts = messenger.showAccounts(authToken);
                    if (!(Accounts.isEmpty()))
                    {
                        for (int i = 0;i<Accounts.size();i++)
                            System.out.println(i+1+"."+ Accounts.get(i).getUsername()+"\n");
                    }
                    break;
                //Send Message
                case 3:
                    authToken = Integer.parseInt(args[3]);
                    recipient = String.valueOf(args[4]);
                    body = String.valueOf(args[5]);
                    status = messenger.sendMessage(authToken,recipient,body);
                    if (status)
                        System.out.println("OK");
                    else
                        System.out.println("User does not exist");
                    break;
                //Show Inbox
                case 4:
                    authToken = Integer.parseInt(args[3]);
                    ArrayList<Message> inbox = messenger.showInbox(authToken);
                    break;
                //Read Message
                case 5:
                    authToken = Integer.parseInt(args[3]);
                    int message_id = Integer.parseInt(args[4]);
                    Message message = messenger.readMessage(authToken,message_id);
                    if (message != null)
                        System.out.println("("+message.getSender()+")"+message.getBody());
                    else
                        System.out.println("Message ID does not exist");
                    break;
                //Delete Message
                case 6:
                    authToken = Integer.parseInt(args[3]);
                    message_id = Integer.parseInt(args[4]);
                    status = messenger.deleteMessage(authToken,message_id);
                    if (status)
                        System.out.println("OK");
                    else
                        System.out.println("Message does not exist");
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
