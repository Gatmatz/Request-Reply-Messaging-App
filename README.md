# Project Specifications
The Request-Reply Messaging App task is implemented with RMI technology.

### Account Class
The Account class represents a user account in the system.
It is a Serializable class.
It contains a String username as the user's name, an int authToken as the user's ID, and an ArrayList<Message> messageBox as the user's inbox.<br />
- The constructor sets the username and authToken and initializes the user's inbox. <br />
- **getUsername()**: Returns the username. <br />
- **authenticate(int authToken)**: Checks if the authentication token received as an argument belongs to the specific user.
   Returns true if it belongs to the specified user, false otherwise. <br />
- **getInbox()**: Returns an ArrayList with the user's inbox. <br />
- **deleteMessage(int message_id)**: Finds a message by message_id and deletes it from the messageBox.
   Returns true if the deletion was successful, false otherwise. <br />

### Message Class
The Message class represents a message in the system.
It is a Serializable class.
Contains an int messageID as the user's ID, a boolean isRead that determines whether a message has been read or not,
a String sender specifying the username of the sender, a String receiver specifying the username of the recipient and
a String body specifying the content of the message. <br />
- The constructor defines the isRead, sender, receiver and body fields and creates an identifier for the message and stores it in the messageID field. <br />
- **getSender()**: Returns the username of the message. <br />
- **generateID(String receiver, String body)**: This function generates a message ID.
   It is a hash function that takes the sum of the bytes of the recipient's name and the content of the message and divides it by the number 97.
   The number 97 is prime and widely used as a hash number.
   The message ID is returned. <br />
- **getReceiver()**: Returns the name of the receiver. <br />
- **getBody()**: Returns the body of the message. <br />
- **messageStatus()**: Returns true if the message has been read, false otherwise. <br />
- **getID()**: Returns the ID of the message. <br />
- **read()**: Reads the message, makes isRead true. <br />

### MessagingServer Class
The MessagingServer class implements the Server side.
main accepts the port number of the Server through args[0].
It then initializes an object of the RemoteMessagingApp class that actually implements the messaging application.
Then a record is created for the application in the RMI Registry with port number args[0] and renamed to "messenger".
Finally, an informational message is displayed.

### RemoteMessagingApp Class
This class implements the functionality of the messaging application.
The class implements the MessagingApp interface, and there are also some additional functions.
ArrayList Accounts is an array that stores system users as objects of the Account class. <br />
The implemented functions of the class are described below.
- **generateauthToken(String username)**: This function generates a new authentication token for a new account.
It is a hash function that uses the division method to generate hash keys.
The function takes the sum of bytes of the user's username and divides it by the number 53.
The choice for the number 53 was made because it is prime and is widely used as a hash number.
The user's authentication token is returned. <br />
- **authenticate(int authToken)**: This function checks if the user exists in the system.
The function accepts the authentication token of the user and by using a loop inside the table containing the system accounts checks if the user exists.
If it exists then the function returns the user's account as an object of the Account class, otherwise it returns null.<br />
- **checkUsername(String username)**: This function checks if the user's username is grammatically correct.
A regular expression (regex) is used to easily express the grammar rules and then the username is checked with the matches function of the String class.
Returns true if the username is correct, false otherwise.<br />
- **createAccount(String username)**: This function is part of the implementation of the MessagingApp interface.
It accepts a username and initially checks if the username is grammatically correct. Then it is checked through the authentication token if the user already exists.
Finally, if the username is correct and the user does not exist then a new object of the Account class is created and added to the Accounts table.
It returns -1 if the username is not correct, -2 if the user already exists and the authentication token if the user registration was successful.<br />
- **login(int authToken)**: This function is part of the MessagingApp interface implementation.
It uses an authentication token and checks if the user exists in the system.
If it exists then it returns true, otherwise it returns false.<br />
- **showAccounts()**: This function is part of the MessagingApp interface implementation.
The function returns an ArrayList with the users of the system.<br />
- **sendMessage(Integer authToken, String receiver, String body)**: This function is part of the implementation of the MessagingApp interface.
It first detects the sender from the authentication Token and then detects the recipient from the username. Finally, it creates an object of the Message class and adds it to the recipient's inbox.
Returns true if the send was successful, otherwise returns false.<br />
- **showInbox(Integer authToken)**: This function is part of the MessagingApp interface implementation.
It detects the user from the authentication token and returns an ArrayList with all of their messages.<br />
- **readMessage(Integer authToken, Integer message_id)**: This function is part of the implementation of the MessagingApp interface.
It detects the user from the authentication token and gets their inbox. Then, the user's inbox is searched for the requested message via message_id.
If found then the message is read (isRead = true) and the message is returned. Otherwise null is returned.<br />
- **deleteMessage(Integer authToken, Integer message_id)**: This function is part of the implementation of the MessagingApp interface.
The user is identified through the authentication token and then the requested message is deleted through the message_id.
Returns true if the deletion was successful, false otherwise.<br />

### MessagingApp Interface
The MessagingApp interface defines all the functions and thus the functions of the application.
The explanation of each function is in the RemoteMessagingApp class explanation.
The interface extends the Remote interface. <br />

### MessagingClient Class
The MessagingClient class implements the Client side.
The class accepts the IP, port number and Function_ID from the arguments.
It then finds the Server through the port from the RMI Registry and initializes a skeleton object of the MessagingApp interface.
Then follows a switch case in which various functions are performed depending on the Function_ID.
Mainly it is first checked if the user exists and the authentication token is valid and then the requested function is performed.
Finally, the appropriate messages are displayed on the console. <br />
