import java.net.ServerSocket;
import java.util.ArrayList;

public class Admin extends Account{
    public static ArrayList<String> BanLists=new ArrayList<>();
    public static ArrayList<String> adminReadLog=new ArrayList<>();

    public static void readUserLogs(){
    	if(Account.getRole()=="admin") {
    		for(String line:Log.chat) {
    			adminReadLog.add(line);
    		}
    		for(String line:adminReadLog) {
    			System.out.println(line);
    		}
    	}else {
    		Log.log("Not admin");
    	}
    }
    public static void searchUserLogs(String un) {
    	if(Account.getRole()=="admin") {
    		for (String user:Log.username) {
    			if(un==user) {
    				System.out.println(Log.chat.get(Log.username.indexOf(user)));
    			}
    		}
    	}
    }
    
    public static void removeFromList(String un) {
    	for (String line:BanLists) {
    		if(un==line) {
    			BanLists.remove(BanLists.indexOf(line));
    			
    		}else {
    			System.out.println("Not found!!!");
    		}
    	}
    }
    
    public static void addToBanList(String un) {
    	BanLists.add(un);
    }
}
