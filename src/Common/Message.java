package Common;

public class Message implements java.io.Serializable
{
    int messageID;
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
        this.messageID = generateID(receiver,body);
    }
    public String getSender()
    {
        return sender;
    }
    int generateID(String receiver, String body)
    {
        byte [] bytes = receiver.getBytes();
        int key = 0;
        for (byte aByte : bytes)
        {
            key += aByte;
        }
        bytes = body.getBytes();
        for (byte aByte : bytes)
        {
            key += aByte;
        }
        int size = 53;
        return key%97;
    }
    public String getReceiver()
    {
        return receiver;
    }
    public String getBody()
    {
        return body;
    }
    public boolean messageStatus()
    {
        return isRead;
    }
    public int getID()
    {
        return messageID;
    }
    public void read()
    {
        isRead = true;
    }

}
