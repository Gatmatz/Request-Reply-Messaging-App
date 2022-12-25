/*
    Messaging application implementation interface.
    Author: Georgios Atmatzidis
    AEM: 3908
 */
package Common;

import java.rmi.*;
import java.util.ArrayList;

public interface MessagingApp extends Remote
{
    /*
    Function signature that tries to create an account with given username.
     */
    int createAccount(String username) throws RemoteException;
    /*
    Function signature that logs a user into the system using an authentication token.
    Returns true if user exists(the authToken is valid), false otherwise.
     */
    boolean login(int authToken) throws RemoteException;
    /*
    Function signature that shows all accounts of system using an authentication token.
    Returns an ArrayList of Accounts if authToken is valid, null otherwise.
     */
    ArrayList<Account> showAccounts() throws RemoteException;
    /*
    Function signature that sends a message(body) to a receiver using an authentication token.
    Returns true if sending was successful, false otherwise.
     */
    boolean sendMessage(Integer authToken,String receiver,String body) throws RemoteException;
    /*
    Function signature that shows user's inbox using an authentication token.
    Returns an ArrayList of Messages if authToken is valid, null otherwise.
     */
    ArrayList<Message> showInbox(Integer authToken) throws RemoteException;
    /*
    Function signature that reads a message(message_id) using an authentication token.
    Returns the message if message_id is valid, null otherwise.
     */
    Message readMessage(Integer authToken, Integer message_id) throws RemoteException;
    /*
    Function that deletes a message(message_id) using an authentication token.
    Returns true if deletion was successful, null otherwise.
     */
    boolean deleteMessage(Integer authToken, Integer message_id) throws RemoteException;
}
