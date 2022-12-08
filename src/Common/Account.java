package Common;

import java.util.List;

public class Account implements java.io.Serializable
{
    String username;
    int authToken;
    List<Message> messageBox;
    public Account(String username, int authToken)
    {
        this.username = username;
        this.authToken = authToken;
        messageBox = null;
    }
    public String getUsername()
    {
        return username;
    }
    int getAuthToken()
    {
        return authToken;
    }
}
