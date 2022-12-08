package Common;

import java.util.ArrayList;

public class Account implements java.io.Serializable
{
    String username;
    int authToken;
    ArrayList<Message> messageBox;
    public Account(String username, int authToken)
    {
        this.username = username;
        this.authToken = authToken;
        messageBox = new ArrayList<>();
    }
    public String getUsername()
    {
        return username;
    }
    public boolean authenticate(int authToken)
    {
        return this.authToken == authToken;
    }
    public ArrayList<Message> getInbox()
    {
        return messageBox;
    }
}
