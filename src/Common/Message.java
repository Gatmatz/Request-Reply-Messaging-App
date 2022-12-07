package Common;

public class Message implements java.io.Serializable
{
    boolean isRead;
    String sender;
    String receiver;
    String body;
    public Message(String sender,String receiver, String body)
    {
        this.isRead=false;
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
    }
    public String getSender()
    {
        return sender;
    }
    public String getReceiver()
    {
        return receiver;
    }
    public String getBody()
    {
        return body;
    }
}
