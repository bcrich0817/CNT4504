

/*
 * CNT4504 Server Example
 *
 * This program implements the Server side of a client server application.  The client 
 * connects to the server, then it requests the date and time from the server.  When 
 * the server receives a request over an established connection, it runs the appropriate 
 * Linux Bash command to generate the requested information, captures that information, 
 * and sends it back to the client that requested the information with the line 
 * "ServerDone" appended to the end.  The connection stays up until the client terminates 
 * it by sending a quit request.
 *
 * The request from the server is simply a String MC:1 
 *
 * Author: Dr. Douglas Leas
 * Date: 2/27/2017
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import static java.lang.System.out;
import java.net.Socket;

/**
 * A TCP server thread that runs on port 1234.  When a client connects, it
 * reads a request number and responds to that request
 */
public class CNTServerExample {
	Socket cSocket;
	MultiThreadServer(Socket mSocket){
		cSocket = mSocket;
	}
	
    public static void main(String[] args) throws IOException {
		//Get input for socket number
		int portNum = Integer.parseInt(args[0]);
		//Display start message
		System.out.println("CNT4504 Server Socket Example\n");
		System.out.println("Server starting on socket " + portNum);

		// Create the socket and bind to port number 1234
        ServerSocket listener = new ServerSocket(portNum);
        	while(true){
		// Listen for client connections, this will block waiting for a connection
		Socket socket = listener.accept();
		System.out.println("Accepted Client connection");
		new Thread(new MultiThreadServer((socket)).start();
		}

		// Attach a buffered reader to the socket's input stream
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		// Attach a print writer to the socket's output stream
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String answer = null;      
while(answer != "MC:7"){		
		
        // Read the request from the client
		answer = input.readLine();
		String mc6 = null;
		System.out.println(answer);   
		Process cmdProc;
		cmdProc=null;

		if (answer.equals("MC:6")){
			mc6 = input.readLine();
		}

		else if(answer.equals("MC:7"))
		{
			System.out.println("Responding to quit request from the client ");
			String[] cmd = {"bash", "-c", "exit"};
			cmdProc = Runtime.getRuntime().exec(cmd);
			out.println("Recieved Quit Request, Disconnecting");
			out.println("ServerDone");
            socket.close();
			break;
		}                
		
		// Execute the appropriate command.
		if(answer.equals("MC:1"))
		{
			System.out.println("Responding to date request from the client ");
			String[] cmd = {"bash", "-c", "date"};
			cmdProc = Runtime.getRuntime().exec(cmd);
		}
		
		else if(answer.equals("MC:2"))
		{
			System.out.println("Responding to number of running processes request from the client ");
			String[] cmd = {"bash", "-c", "ps -ef|wc -l"};
			cmdProc = Runtime.getRuntime().exec(cmd);
		}
                
		else if(answer.equals("MC:3"))
		{
			System.out.println("Responding to number of active socket connections request from the client ");
			String[] cmd = {"bash", "-c", "ss -t -a|grep ESTAB|wc -l"};
			cmdProc = Runtime.getRuntime().exec(cmd);
		}
                
		else if(answer.equals("MC:4"))
		{
			System.out.println("Responding to server time of last reboot request from the client ");
			String[] cmd = {"bash", "-c", "who -b"};
			cmdProc = Runtime.getRuntime().exec(cmd);
		}
 
		else if(answer.equals("MC:5"))
		{
			System.out.println("Responding server current users request from the client ");
			String[] cmd = {"bash", "-c", "users"};
			cmdProc = Runtime.getRuntime().exec(cmd);
		}
                
		else if(answer.equals("MC:6"))
		{
			System.out.println("Responding to server echo back request from the client ");
			String[] cmd = {"bash", "-c", "printf"};
			cmdProc = Runtime.getRuntime().exec(cmd);
			out.println(mc6);
		}
                       
        else 
        {
			System.out.println("Unknown request ");
			out.println("Error: Unknown request, connection aborted");
            out.println("ServerDone");
			socket.close();
			return;
		}   
                
		// Read the result of the commands and send the result to the client one line at a time
		// followed by the line "ServerDone"
		BufferedReader cmdin = new BufferedReader(new InputStreamReader(cmdProc.getInputStream()));
		String cmdans;
		while((cmdans = cmdin.readLine()) != null)
		{
			out.println(cmdans);
		}

		out.println("ServerDone");
	}
    }
}
