/*
    Account implementation class.
    Author: Georgios Atmatzidis
    AEM: 3908
 */
package Common;

import java.util.ArrayList;

public class Account implements java.io.Serializable
{
    //Account's username
    String username;
    //Account's authentication Token
    int authToken;
    //Account's inbox
    ArrayList<Message> messageBox;

    /*
    The account constructor accepts a username and an authToken.
    It initializes the inbox list.
     */
    public Account(String username, int authToken)
    {
        this.username = username;
        this.authToken = authToken;
        messageBox = new ArrayList<>();
    }
    /*
    Return a String with user's username.
     */
    public String getUsername()
    {
        return username;
    }

    /*
    Check authentication with a given authToken.
     */
    public boolean authenticate(int authToken)
    {
        return this.authToken == authToken;
    }
    /*
    Return an Array List with user's Inbox.
     */
    public ArrayList<Message> getInbox()
    {
        return messageBox;
    }
    /*
    Function that removes a message from user's inbox.
    It searches the inbox array to find the message with the given message_id and then proceeds to delete it.
    Return true if the deletion was successful, and false otherwise.
     */
    public boolean deleteMessage(int message_id)
    {
        for (Message box : messageBox)
        {
            if (box.messageID == message_id)
            {
                messageBox.remove(box);
                return true;
            }
        }
        return false;
    }
}
