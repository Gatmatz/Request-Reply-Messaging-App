# Student Info
**Name**:Atmatzidis Georgios <br />
**AEM**:3908

# Project Specifications
Η εργασία Request-Reply Messaging App έχει υλοποιηθεί με την τεχνολογία RMI.

### Account Κλάση
Η κλάση Account αναπαριστά έναν λογαριασμό χρήστη στο σύστημα.
Είναι μια Serializable κλάση.
Περιέχει ένα String username ως το όνομα του χρήστη, ένα int authToken ως το αναγνωριστικό του χρήστη και ένα ArrayList<Message> messageBox ως το inbox του χρήστη.<br />
- O κονστράκτορας ορίζει το username και authToken και αρχικοποιεί το inbox του χρήστη. <br />
- **getUsername()**: Επιστρέφει το όνομα του χρήστη. <br />
- **authenticate(int authToken)**: Ελέγχει αν το authentication token που δέχτηκε σαν όρισμα είναι του συγκεκριμένοι χρήστη.
  Επιστρέφει true αν ανήκει στον συγκεκριμένο χρήστη, false διαφορετικά. <br />
- **getInbox()**: Επιστρέφει ένα ArrayList με το inbox του χρήστη. <br />
- **deleteMessage(int message_id)**: Εντοπίζει ένα μήνυμα μέσω του message_id και το διαγράφει από το messageBox.
  Επιστρέφει true αν η διαγραφή ήταν επιτυχής, false διαφορετικά. <br />

### Message Κλάση
Η κλάση Message αναπαριστά ένα μήνυμα στο σύστημα.
Είναι μια Serializable κλάση.
Περιέχει ένα int messageID ως το αναγνωριστικό του χρήστη, ένα boolean isRead που καθορίζει αν ένα μήνυμα έχει διαβαστεί ή όχι,
ένα String sender που καθορίζει το username του αποστολέα, ένα String receiver που καθορίζει το username του παραλήπτη και
ένα String body που καθορίζει το περιεχόμενο του μηνύματος. <br />
- Ο κονστράκτορας ορίζει τα πεδία isRead, sender, receiver και body και δημιουργεί ένα αναγνωριστικό για το μήνυμα και το αποθηκεύει στο πεδίο messageID. <br />
- **getSender()**: Επιστρέφει το username του μηνύματος. <br />
- **generateID(String receiver, String body)**: Η συγκεκριμένη συνάρτηση δημιουργεί ένα αναγνωριστικό μηνύματος.
  Είναι μια hash συνάρτηση που παίρνει το άθροισμα των bytes του ονόματος του παραλήπτη και του περιεχομένου του μηνύματος και το διαιρεί με τον αριθμό 97.
  Ο αριθμός 97 είναι πρώτος και χρησιμοποιείται ευρέως ως hash number.
  Επιστρέφεται το αναγνωριστικό του μηνύματος. <br />
- **getReceiver()**: Επιστρέφει το όνομα του παραλήπτη. <br />
- **getBody()**: Επιστρέφει το περιεχόμενο του μηνύματος. <br />
- **messageStatus()**: Επιστρέφει true αν το μήνυμα έχει διαβαστεί, false διαφορετικά. <br />
- **getID()**: Επιστρέφει το αναγνωριστικό του μηνύματος. <br />
- **read()**: Διαβάζει το μήνυμα, κάνει το isRead true. <br />

### MessagingServer Κλάση
Η κλάση MessagingServer υλοποιεί την μεριά του Server.
Η main δέχεται το port number του Server μέσω του args[0].
Έπειτα αρχικοποιεί ένα αντικείμενο της κλάσης RemoteMessagingApp που ουσιαστικά υλοποιεί την εφαρμογή ανταλλαγής μηνυμάτων.
Στη συνέχεια, δημιουργείται εγγραφή για την εφαρμογή στο RMI Registry με port number το args[0] και μετονομάζεται σε "messenger".
Τέλος, προβάλλεται ενημερωτικό μήνυμα.

### RemoteMessagingApp Κλάση
Η συγκεκριμένη κλάση υλοποιεί την λειτουργία της εφαρμογής ανταλλαγής μηνυμάτων.
Η κλάση κάνει implement το MessagingApp interface, ενώ υπάρχουν και μερικές επιπλέον συναρτήσεις.
Το ArrayList Accounts είναι ένας πίνακας που αποθηκεύει τους χρήστες του συστήματος σαν αντικείμενα της κλάσης Account. <br />
Παρακάτω περιγράφονται οι υλοποιημένες συναρτήσεις της κλάσης.
- **generateauthToken(String username)**: H συγκεκριμένη συνάρτηση δημιουργεί ένα νέο authentication token για έναν καινούργιο λογαριασμό.
Είναι μια hash συνάρτηση που χρησιμοποιεί την μέθοδο διαίρεσης για να δημιουργεί hash keys. 
Η συνάρτηση παίρνει το άθροισμα των bytes του username του χρήστη και το διαιρεί με τον αριθμό 53.
Η επιλογή για τον αριθμό 53 έγινε γιατί είναι πρώτος και χρησιμοποιείται ευρέως ως hash number.
Επιστρέφεται το authentication token του χρήστη.  
<br />
- **authenticate(int authToken)**: Η συγκεκριμένη συνάρτηση ελέγχει αν υπάρχει ο χρήστης στο σύστημα.
Η συνάρτηση δέχεται το authentication token του χρήστη και με την χρήση μιας loop μέσα στον πίνακα που περιέχει τα accounts του συστήματος ελέγχει αν υπάρχει ο χρήστης.
Αν υπάρχει τότε η συνάρτηση επιστρέφει το λογαριασμό του χρήστη σαν αντικείμενο της κλάσης Account, διαφορετικά γυρνάει null.  
<br />
- **checkUsername(String username)**: Η συγκεκριμένη συνάρτηση ελέγχει αν το username του χρήστη είναι γραμματικά σωστό.
Χρησιμοποιείται μια κανονική έκφραση (regex) ώστε να εκφραστούν εύκολα οι γραμματικοί κανόνες και έπειτα ελέγχεται το username με την συνάρτηση matches της κλάσης String.
Επιστρέφεται true αν το username είναι σωστό, διαφορετικά false.  
<br />
- **createAccount(String username)**: Η συγκεκριμένη συνάρτηση είναι μέρος του implementation του MessagingApp interface.
Δέχεται ένα username και αρχικά ελέγχει αν το username είναι γραμματικά σωστό. Έπειτα ελέγχεται μέσω του authentication token αν ο χρήστης υπάρχει ήδη.
Τέλος, αν το username είναι σωστό και ο χρήστης δεν υπάρχει τότε δημιουργείται ένα νέο αντικείμενο της κλάσης Account και προστίθεται στο πίνακα Accounts.
Επιστρέφεται -1 αν το username δεν είναι σωστό, -2 αν ο χρήστης υπάρχει ήδη και το authentication token αν η εγγραφή του χρήστη ήταν επιτυχής.  
<br />
- **login(int authToken)**: Η συγκεκριμένη συνάρτηση είναι μέρος του implementation του MessagingApp interface.
Χρησιμοποιεί ένα authentication Token και ελέγχει αν ο χρήστης υπάρχει στο σύστημα.
Αν υπάρχει τότε επιστρέφει true, διαφορετικά επιστρέφει false.  
<br />
- **showAccounts()**: Η συγκεκριμένη συνάρτηση είναι μέρος του implementation του MessagingApp interface.
Η συνάρτηση επιστρέφει ένα ArrayList με τους χρήστες του συστήματος.  
<br />
- **sendMessage(Integer authToken, String receiver, String body)**: Η συγκεκριμένη συνάρτηση είναι μέρος του implementation του MessagingApp interface.
Αρχικά εντοπίζει τον αποστολέα από το authentication Token και έπειτα εντοπίζει τον παραλήπτη από το username. Τέλος, δημιουργεί ένα αντικείμενο της κλάσης Message και το προσθέτει στο inbox του παραλήπτη.
Επιστρέφει true αν η αποστολή ήταν επιτυχής, διαφορετικά επιστρέφει false.  
<br />
- **showInbox(Integer authToken)**: Η συγκεκριμένη συνάρτηση είναι μέρος του implementation του MessagingApp interface.
Εντοπίζει τον χρήστη από το authentication token και επιστρέφει ένα ArrayList με το σύνολο των μηνυμάτων του.  
<br />
- **readMessage(Integer authToken, Integer message_id)**: Η συγκεκριμένη συνάρτηση είναι μέρος του implementation του MessagingApp interface.
Εντοπίζει τον χρήστη από το authentication token και παίρνει το inbox του. Έπειτα, ψάχνεται στο inbox του χρήστη το ζητούμενο μήνυμα μέσω του message_id.
Αν βρεθεί τότε το μήνυμα διαβάζεται (isRead = true) και επιστρέφεται το μήνυμα. Διαφορετικά επιστρέφεται null.  
<br />
- **deleteMessage(Integer authToken, Integer message_id)**: Η συγκεκριμένη συνάρτηση είναι μέρος του implementation του MessagingApp interface.
Εντοπίζεται ο χρήστης μέσω του authentication token και έπειτα διαγράφεται το ζητούμενο μήνυμα μέσω του message_id.
Επιστρέφεται true αν η διαγραφή ήταν επιτυχής, false διαφορετικά.  
<br />


### MessagingApp Interface
Το interface MessagingApp ορίζει όλες τις συνάρτησεις και συνεπώς τις λειτουργίες της εφαρμογής.
Η επεξήγηση της κάθε λειτουργίας υπάρχει στην επεξήγηση της RemoteMessagingApp κλάσης.
Το interface κάνει extend το interface Remote. <br />

### MessagingClient Κλάση
Η κλάση MessagingClient υλοποιεί την μεριά του Client.
Η κλάση δέχεται την IP, το port number και το Function_ID από τα arguments.
Έπειτα, βρίσκει τον Server μέσω του port από το RMI Registry και αρχικοποιεί ένα skeleton αντικείμενο του interface MessagingApp.
Στην συνέχεια ακολουθεί μια switch case στην οποία εκτελούνται διάφορες λειτουργίες ανάλογα με το Function_ID.
Κατά κύριο λόγο ελέγχεται αρχικά αν ο χρήστης υπάρχει και το authentication token είναι valid και έπειτα εκτελείται η λειτουργία που ζητείται.
Τέλος, εμφανίζονται τα κατάλληλα μηνύματα στην κονσόλα. <br />