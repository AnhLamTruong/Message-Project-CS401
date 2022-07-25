import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import javax.swing.*;
public class Login extends JFrame implements ActionListener {
    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    Login() {
        // Username Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        // Password Label
        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();
        // Submit
        submit = new JButton("SUBMIT");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Adding the listeners to components..
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(450,350);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Login();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        if (userName.trim().equals("admin") && password.trim().equals("admin")) {
            message.setText(" Hello " + userName + "");
        } else {
            message.setText(" Invalid user.. ");
        }
    }

    public void addOrModifyAcc(String username, String password, String firstName, String lastName, String email, int phone, String IDName, String role) {
        // NOTE: Be careful. Running time is a string here
        // since the user might enter non-digits when prompted.
        // If the array is full and a new DVD needs to be added,
        // double the size of the array first.
        if (!validRating(rating)){
            return;
        }
        int runTime =validateRunningTime(runningTime);
        if (runTime <=0){
            return;
        }
        modified = true;
        title = title.toUpperCase();


        //Search for existing element
        int index = findDVD(title);
        if (index >= 0){
            dvdArray[index].setTitle(title);
            dvdArray[index].setRating(rating);
            dvdArray[index].setRunningTime(runTime);
            System.out.println("Found duplicate at index " + index);
        }

        else {
            if (numdvds < dvdArray.length) {
                dvdArray[numdvds] = new DVD(title, rating, runTime);
                System.out.println("[" + numdvds + "]" + dvdArray[numdvds].toString());

                numdvds++;
                Arrays.sort(dvdArray, 0, numdvds, new DVDTitleSort());
            } else {
                System.out.println("Need to increase array size first");
                doubleDVDArraySize();
                System.out.println(toString());
                addOrModifyDVD(title, rating, runningTime);
                System.out.println(toString());
            }
        }
    }

    public void removeDVD(String title) {
        int index = findDVD(title);
        if (index >= 0){
            modified = true;
            DVD[] newArray = new DVD[dvdArray.length-1];
            System.arraycopy(dvdArray, 0, newArray, 0, index);
            System.arraycopy(dvdArray, index+1, newArray, index, dvdArray.length - index -1);
            dvdArray = newArray;
            numdvds--;
        }
        else{
            System.out.println("DVD not found");
        }

    }

    public void loadData(String filename) {
        sourceName = filename;
        modified = false;
        try {
            FileReader fin = new FileReader(sourceName);
            BufferedReader bis = new BufferedReader(fin);
            String line;
            while ((line = bis.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 3) {
                    System.out.println("Error: Invalid DVD entry in file \"" + line + "\"");
                    return;
                }
                addOrModifyDVD(values[0], values[1], values[2]);

            }
        }
        catch (IOException e) {
            System.out.println("Error: File not found: "+filename);
        }

    }

    public void save() {

        try {
            if(!modified){
                System.out.println("No changes made.");
                return;
            }

            Writer f2 = new FileWriter(sourceName, false);
            if (numdvds > 0) {
                for (int i = 0; i < numdvds; i++) {
                    f2.write(dvdArray[i].getTitle() + "," + dvdArray[i].getRating() + "," + dvdArray[i].getRunningTime());
                    f2.write(System.lineSeparator());
                    //[SUPERMAN/R/127min]

                }
            }
            f2.close();
            System.out.println("Save Successful.");
            modified = false;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("Error saving file: " + e);
        }

    }
}
