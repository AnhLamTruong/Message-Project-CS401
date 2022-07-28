import java.net.ServerSocket;
import java.util.ArrayList;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle err

public class Log {
	
    public static ArrayList<String> chat=new ArrayList<>();
    public static ArrayList<String> username=new ArrayList<>();
    
	public static void log(String text) {
		System.out.println(text);
	}
	public static void saveUsername(String te) {
		username.add(te);
	}
	public static void saveLog(String te) {
		chat.add(te);
	}
	public static void fileMachine(String txt) {
		try {
		    FileWriter myWrite = new FileWriter("txt");
			for(String line:chat) {
				myWrite.write(line);
				myWrite.close();
			}
		    System.out.println("Successfully wrote to the file.");
		}catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
}
