import javax.swing.*;

/**
 *  This class is an implementation of DVDUserInterface
 *  that uses JOptionPane to display the menu of command choices.
 */



public class AccountGUI implements AccountInterface {

    private AccountCollection acclist;

    public AccountGUI(AccountCollection al)
    {
        acclist = al;
    }

    public static void main(String[] args) {

        AccountInterface alInterface;
        AccountCollection al = new AccountCollection();

        al.loadData("accdata.txt");
        alInterface = new AccountGUI(al);
        alInterface.processCommands();
    }

    public void processCommands()
    {
        String[] commands = {"Login",
                "Register",
                "Exit"};

        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null,
                    "Select a command",
                    "Login Page",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    commands,
                    commands[commands.length - 1]);

            switch (choice) {
                case 0: login(); break;
                case 1: doAddOrModifyAcc(); break;
                case 2: doSave(); break;
                default:  // do nothing
            }

        } while (choice != commands.length-1);
        System.exit(0);
    }

    private void doAddOrModifyAcc() {

        // Request the Username
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null) {
            return;		// dialog was cancelled
        }


        // Request the Password
        String password = JOptionPane.showInputDialog("Enter Password for " + username);
        if (password == null) {
            return;		// dialog was cancelled
        }


        // Request the First Name
        String firstName = JOptionPane.showInputDialog("Enter First Name:  " + username);
        if (firstName == null) {
            return;
        }

        String lastName = JOptionPane.showInputDialog("Enter Last Name:  " + username);
        if (lastName == null) {
            return;
        }

        String email = JOptionPane.showInputDialog("Enter email:  " + username);
        if (email == null) {
            return;
        }

        String phone = JOptionPane.showInputDialog("Enter phone number:  " + username);
        if (phone == null) {
            return;
        }

        String role = JOptionPane.showInputDialog("Enter role:  " + username);
        if (role == null) {
            return;
        }

        // Add or modify the DVD (assuming the rating and time are valid
        acclist.addOrModifyAcc(username, password, firstName, lastName, email, phone, role);

        // Display current collection to the console for debugging
        System.out.println("Adding/Modifying: " + username + "," + password + "," + firstName);
        System.out.println(acclist);

    }

    private void doRemoveAcc() {

        // Request the title
        String username = JOptionPane.showInputDialog("Enter Username");
        if (username == null) {
            return;		// dialog was cancelled
        }


        // Remove the matching DVD if found
        acclist.removeAcc(username);

        // Display current collection to the console for debugging
        System.out.println("Removing: " + username);
        System.out.println(acclist);

    }

    private void doGetAccByFirstName() {

        // Request the rating
        String firstName = JOptionPane.showInputDialog("Enter First Name: ");
        if (firstName == null) {
            return;		// dialog was cancelled
        }


        String results = acclist.getAccByName(firstName);
        System.out.println("Accounts with first name " + firstName);
        System.out.println(results);

    }

    private void doGetTotalAccounts() {

        int total = acclist.getTotalAcc();
        System.out.println("Total Accounts : ");
        System.out.println(total);

    }

    private void login () {
        String username = JOptionPane.showInputDialog("Enter Username:");
        String password = JOptionPane.showInputDialog("Enter Password:");

        if(acclist.checkAcc(username,password)) {

            JOptionPane.showMessageDialog(null,"Welcome","Welcome!", JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            JOptionPane.showMessageDialog(null,"Error: Invalid Account credentials.","Invalid Login", JOptionPane.INFORMATION_MESSAGE);
        }


    }



    private void doSave() {

        acclist.save();

    }

}
