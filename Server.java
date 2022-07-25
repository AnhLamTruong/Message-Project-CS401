import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;



public class Server {
	
	private ServerSocket serverSocket;
	
	public Server(ServerSocket sS) {
		this.serverSocket=sS;
	}
	
	public void startServer() {
		try {
			// While the server is open, accept() help to run the server
			while(!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				// While the server is opening, bring up a message
				System.out.println(" A new client has connected!");
				// pass the socket into clientHandler, so it will cover BufferedReader, and user's input
				ClientHandler clientHandler = new ClientHandler(socket);
				
				Thread thread= new Thread(clientHandler);
				thread.start();
				
			}
		}catch (IOException e) {
			
		}
	}
	public void closeServerSocket() {
		try {
			if(this.serverSocket!=null) {
				this.serverSocket.close();
			} 
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Main function to run a Server Socket
	public static void main(String[] args) throws IOException{
		ServerSocket sSocket =new ServerSocket(1234);
		Server server = new Server(sSocket);
		server.startServer();
	}
}
