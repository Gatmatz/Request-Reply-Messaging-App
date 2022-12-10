/*
    Message implementation class.
    Author: Georgios Atmatzidis
    AEM: 3908
 */
package Common;

public class Message implements java.io.Serializable
{
    int messageID; //An integer to define the message.
    boolean isRead; //A boolean variable to check if a message is read.
    String sender; //String that contains the sender's username.
    String receiver; //String that contains the receiver's username.
    String body; //String that contains the message text.
    /*
    Message constructor that accepts the sender, receiver and message body.
    It generates a message ID using hash functions and stores the ID to message_id variable.
     */
    public Message(String sender,String receiver, String body)
    {
        this.isRead=false;
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        this.messageID = generateID(receiver,body);
    }
    /*
    Function that returns the sender's username.
     */
    public String getSender()
    {
        return sender;
    }
    /*
    Hash function that generates a message ID using the mod method.
    It uses the message receiver and body to generate a unique id.
    It divides using number 97 that is a well-known good prime for hashing.
     */
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
