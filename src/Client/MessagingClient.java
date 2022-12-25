/*
    Client implementation class.
    Author: Georgios Atmatzidis
    AEM: 3908
 */
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
            //Get specific arguments from args array
            String IP = args[0];
            int port = Integer.parseInt(args[1]);
            int FN_ID = Integer.parseInt(args[2]);

            //Connect to RMI Registry
            Registry rmiController = LocateRegistry.getRegistry(port);

            //Get reference for remote control - Create Skeleton
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
                    //Take username from args
                    username = String.valueOf(args[3]);
                    //Try to create account in stub object.
                    product = messenger.createAccount(username);
                    //Check the different cases
                    if (product == -2) //User exists
                        System.out.println("Sorry, the user already exists");
                    else if (product == -1) //Invalid username
                        System.out.println("Invalid Username");
                    else //Successful registration
                        System.out.println(product);
                    break;
                //Show Accounts
                case 2:
                    //Take authentication token from args
                    authToken = Integer.parseInt(args[3]);
                    //Check user's authentication token
                    if (messenger.login(authToken))
                    {
                        //Get account list from stub object
                        ArrayList<Account> Accounts = messenger.showAccounts();
                        //Print out the list with specific format
                        for (int i = 0;i<Accounts.size();i++)
                            System.out.println(i+1+"."+ Accounts.get(i).getUsername());
                    }
                    else //If the user does not exist
                        System.out.println("Invalid Auth Token");
                    break;
                //Send Message
                case 3:
                    //Take authentication token from args
                    authToken = Integer.parseInt(args[3]);
                    //Take receiver's username from args
                    recipient = String.valueOf(args[4]);
                    //Take message text from args
                    body = String.valueOf(args[5]);
                    //Check user's authentication token
                    if (messenger.login(authToken))
                    {
                        //Send message using stub object and return the status of sending
                        status = messenger.sendMessage(authToken,recipient,body);
                        if (status) //Successful sending
                            System.out.println("OK");
                        else //Unsuccessful sending
                            System.out.println("User does not exist");
                    }
                    else //If the user does not exist
                        System.out.println("Invalid Auth Token");
                    break;
                //Show Inbox
                case 4:
                    //Take authentication token from args
                    authToken = Integer.parseInt(args[3]);
                    //Check user's authentication token
                    if (messenger.login(authToken))
                    {
                        //Take user's inbox from stub object
                        ArrayList<Message> inbox = messenger.showInbox(authToken);
                        //Print out the inbox using specific format
                        if (inbox.isEmpty())
                            System.out.println("No messages");
                        else
                        {
                            for (Message message : inbox)
                            {
                                if (message.messageStatus())
                                    System.out.println(message.getID() + ". from: " + message.getSender());
                                else
                                    System.out.println(message.getID() + ". from: " + message.getSender() + "*");
                            }
                        }
                    }
                    else //If the user does not exist
                        System.out.println("Invalid Auth Token");
                    break;
                //Read Message
                case 5:
                    //Take authentication token from args
                    authToken = Integer.parseInt(args[3]);
                    //Take Message ID from args
                    int message_id = Integer.parseInt(args[4]);
                    //Check user's authentication token
                    if (messenger.login(authToken))
                    {
                        //Read message using stub object
                        Message message = messenger.readMessage(authToken,message_id);
                        //Print out message
                        if (message != null) //If message exists
                            System.out.println("("+message.getSender()+")"+message.getBody());
                        else //If message does not exist
                            System.out.println("Message ID does not exist");
                    }
                    else //If the user does not exist
                        System.out.println("Invalid Auth Token");
                    break;
                //Delete Message
                case 6:
                    //Take authentication token from args
                    authToken = Integer.parseInt(args[3]);
                    //Take Message ID from args
                    message_id = Integer.parseInt(args[4]);
                    //Check user's authentication token
                    if (messenger.login(authToken))
                    {
                        //Delete message using stub object and return the status of deletion
                        status = messenger.deleteMessage(authToken,message_id);
                        //Check deletion
                        if (status) //Successful deletion
                            System.out.println("OK");
                        else //Unsuccessful deletion
                            System.out.println("Message does not exist");
                    }
                    else //If the user does not exist
                        System.out.println("Invalid Auth Token");
                    break;
            }
        }
        catch (Exception e) //Catch any exception
        {
            //Print out the exception stack
            e.printStackTrace();
        }
    }
}
