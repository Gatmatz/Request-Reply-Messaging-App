package Common;

import java.util.List;

public class Account implements java.io.Serializable
{
    String username;
    private int authToken;
    List<Message> messageBox;
    public Account(String username)
    {
        this.username = username;
        authToken = 0;
        messageBox = null;
    }
    public String getUsername()
    {
        return username;
    }
}
