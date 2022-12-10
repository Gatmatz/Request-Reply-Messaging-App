/*
    Messaging application implementation interface.
    Author: Georgios Atmatzidis
    AEM: 3908
 */
package Common;

import java.rmi.*;
import java.util.ArrayList;
import java.util.List;

public interface MessagingApp extends Remote
{
    int createAccount(String username) throws RemoteException;
    boolean login(int authToken) throws RemoteException;
    ArrayList<Account> showAccounts(Integer authToken) throws RemoteException;
    boolean sendMessage(Integer authToken,String receiver,String body) throws RemoteException;
    ArrayList<Message> showInbox(Integer authToken) throws RemoteException;
    Message readMessage(Integer authToken, Integer message_id) throws RemoteException;
    boolean deleteMessage(Integer authToken, Integer message_id) throws RemoteException;
}
