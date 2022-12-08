package Server;

import Common.Account;
import Common.Message;
import Common.MessagingApp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteMessagingApp extends UnicastRemoteObject implements MessagingApp
{
    ArrayList<Account> Accounts;
    public RemoteMessagingApp() throws RemoteException
    {
        super();
        Accounts = new ArrayList<>();
    }

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
    Account authenticate(int authToken)
    {
        for (Account account : Accounts)
        {
            if (account.authenticate(authToken))
                return account;
        }
        return null;
    }
    boolean checkUsername(String username)
    {
        String valid = "[a-zA-Z0-9_]*";
        return username.matches(valid);
    }


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

    @Override
    public boolean login(int authToken) throws RemoteException
    {
        Account user = authenticate(authToken);
        return user != null;
    }

    @Override
    public ArrayList<Account> showAccounts(Integer authToken) throws RemoteException
    {
        return Accounts;
    }

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

    @Override
    public ArrayList<Message> showInbox(Integer authToken) throws RemoteException
    {
        Account user = authenticate(authToken);
        return user.getInbox();
    }

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

    @Override
    public boolean deleteMessage(Integer authToken, Integer message_id) throws RemoteException {
        return false;
    }
}
