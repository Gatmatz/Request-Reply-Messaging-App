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
        //Get receiver's name as a sum of bytes.
        byte [] bytes = receiver.getBytes();
        int key = 0;
        for (byte aByte : bytes)
        {
            key += aByte;
        }
        //Get message's body as a sum of bytes.
        bytes = body.getBytes();
        for (byte aByte : bytes)
        {
            key += aByte;
        }
        return key%97;
    }
    /*
    Function that returns the receiver's name as a String.
     */
    public String getReceiver()
    {
        return receiver;
    }
    /*
    Function that return the message's body as a String.
     */
    public String getBody()
    {
        return body;
    }
    /*
    Function that returns if the message is read or not.
     */
    public boolean messageStatus()
    {
        return isRead;
    }
    /*
    Function that returns the message's ID as an integer.
     */
    public int getID()
    {
        return messageID;
    }
    /*
    Function that reads a message(changes isRead variable to true).
     */
    public void read()
    {
        isRead = true;
    }
}
