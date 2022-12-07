package Server;

import Common.Account;
import Common.Message;
import Common.MessagingApp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteMessagingApp extends UnicastRemoteObject implements MessagingApp
{
    List<Account> Accounts;
    public RemoteMessagingApp() throws RemoteException
    {
        super();
        Accounts = new ArrayList<>();
    }

    @Override
    public int createAccount(String username) throws RemoteException {
        return 0;
    }

    @Override
    public ArrayList<Account> showAccounts(Integer authToken) throws RemoteException {
        return null;
    }

    @Override
    public boolean sendMessage(Integer authToken, String receiver, String body) throws RemoteException {
        return false;
    }

    @Override
    public ArrayList<Message> showInbox(Integer authToken) throws RemoteException {
        return null;
    }

    @Override
    public Message readMessage(Integer authToken) throws RemoteException {
        return null;
    }

    @Override
    public boolean deleteMessage(Integer authToken) throws RemoteException {
        return false;
    }
}
