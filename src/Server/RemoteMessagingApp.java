/*
    Messaging application class implementating the messaging interface.
    Author: Georgios Atmatzidis
    AEM: 3908
 */
package Server;

import Common.Account;
import Common.Message;
import Common.MessagingApp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteMessagingApp extends UnicastRemoteObject implements MessagingApp
{
    ArrayList<Account> Accounts; //An ArrayList that keeps track of all Accounts in the system.
    public RemoteMessagingApp() throws RemoteException
    {
        super();
        Accounts = new ArrayList<>();
    }

    /*
    Hash Function that generates an authentication token for a new user.
    It implementates the divide hash method.
    Calculates the sum of bytes of username's String and then proceeds to divide it with the number 53(a good prime for hashing).
    Returns the authentication token as an integer.
     */
    int generateauthToken(String username)
    {
        byte [] bytes = username.getBytes();
        int key = 0;
        for (byte aByte : bytes)
        {
            key += aByte;
        }
        int size = 53;
        return key%size;
    }
    /*
    Check if a user exists using the given authentication token.
    Returns the user's Account if the user is found, null otherwise.
     */
    Account authenticate(int authToken)
    {
        for (Account account : Accounts)
        {
            if (account.authenticate(authToken))
                return account;
        }
        return null;
    }
    /*
    Check if a username is valid using regex expressions and the "java.lang.matches" function.
     */
    boolean checkUsername(String username)
    {
        String valid = "[a-zA-Z0-9_]*";
        return username.matches(valid);
    }

    /*
    Function signature that tries to create an account with given username.
     */
    @Override
    public int createAccount(String username) throws RemoteException
    {
        int new_token = generateauthToken(username);
        if (!(checkUsername(username)))
            return -1;
        else if (authenticate(new_token) != null)
            return 1;
        else
        {
            Account new_user = new Account(username,new_token);
            Accounts.add(new_user);
            return new_token;
        }
    }

    /*
    Function signature that logs a user into the system using an authentication token.
    Returns true if user exists(the authToken is valid), false otherwise.
     */
    @Override
    public boolean login(int authToken) throws RemoteException
    {
        Account user = authenticate(authToken);
        return user != null;
    }

    /*
    Function signature that shows all accounts of system using an authentication token.
    Returns an ArrayList of Accounts if authToken is valid, null otherwise.
     */
    @Override
    public ArrayList<Account> showAccounts(Integer authToken) throws RemoteException
    {
        return Accounts;
    }

    /*
    Function signature that sends a message(body) to a receiver using an authentication token.
    Returns true if sending was successful, false otherwise.
     */
    @Override
    public boolean sendMessage(Integer authToken, String receiver, String body) throws RemoteException
    {
        Account user = authenticate(authToken);
        for (Account account : Accounts)
        {
            if (account.getUsername().equals(receiver))
            {
                Message m = new Message(user.getUsername(),receiver,body);
                account.getInbox().add(m);
                return true;
            }
        }
        return false;
    }

    /*
    Function signature that shows user's inbox using an authentication token.
    Returns an ArrayList of Messages if authToken is valid, null otherwise.
     */
    @Override
    public ArrayList<Message> showInbox(Integer authToken) throws RemoteException
    {
        Account user = authenticate(authToken);
        return user.getInbox();
    }

    /*
    Function signature that reads a message(message_id) using an authentication token.
    Returns the message if message_id is valid, null otherwise.
     */
    @Override
    public Message readMessage(Integer authToken, Integer message_id) throws RemoteException
    {
        Account user = authenticate(authToken);
        ArrayList<Message> inbox = user.getInbox();
        for (Message message : inbox)
        {
            if (message.getID() == message_id)
            {
                message.read();
                return message;
            }
        }
        return null;
    }

    /*
    Function that deletes a message(message_id) using an authentication token.
    Returns true if deletion was successful, null otherwise.
     */
    @Override
    public boolean deleteMessage(Integer authToken, Integer message_id) throws RemoteException
    {
        Account user = authenticate(authToken);
        return user.deleteMessage(message_id);
    }
}
