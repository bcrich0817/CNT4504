

/*
 * CNT4504 Socket Client Example
 *
 * This program implements an example of the client side of a client server 
 * application using sockets. The client connects to the server at the IP address 
 * and port provided on the command line, then it sends a simple message cotaining 
 * the string MC:1 to the server (for management command 1) and waits for the response.  
 * When the response is recieved, it prints the response, closes the connection to the 
 * server and exits.  
 *
 * Input: The IP address and the port number of the server are supplied via the
 *        command line.  
 *
 * Output: The program prints the response from the server.
 *
 * Author: Dr. Douglas Leas
 * Date: 2/27/2017
 */
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.Socket;
public class CNTClientExample
{
    public static void main(String[] args) throws IOException 
    {

        if(args.length < 2)  // If there are no command line arguments print an error and exit.
        {
            System.out.println("Usage: CNTClient <IP> <PORT>\n");
                        //Server 139.62.210.150 Port 9661
                        //Client 139.62.210.149 Port 9661
            System.exit(0);
        }
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // Try to connect to port and the IP address given on the command line.
        Socket s=null;
        try
        {
            s = new Socket(args[0], Integer.parseInt(args[1]));
        }
        catch(Exception e)
        {
            System.out.println("Error: Could not open a socket to " + args[0] + "\n");
            System.exit(100);
        }
                
                //Create a buffered reader attached to the socket's input stream.
                        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

                        // Create a print writer attached to the socket's output stream.
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        System.out.println("Connecting to server at "+args[0]+":"+args[1]);
                
            //Create menu
            int swValue = 0;
            int numRun = 0;
            long start_time;
            long end_time;
                while(swValue != 7){
                System.out.println(
                    "\n" +
                    "--------------------------------------------------------\n" +
                    "--------------------------MENU--------------------------\n" +
                    "--------------------------------------------------------\n"  +
                    "  1) Get Current Date and Time on Server \n" +
                    "  2) Get Number of Running Processes on Server \n" +
                    "  3) Get Number of Active Socket Connections on Server \n" +
                    "  4) Get Time of Server's Last Reboot \n" +
                    "  5) Get Current Users on Server \n" +
                    "  6) Get Back What is Sent \n" +
                    "  7) Quit \n"               
                );
                swValue = Keyin.inInt(" Select option: ");
                
                if (swValue == 1 || swValue == 2 || swValue == 3 || swValue == 4 || swValue == 5 || swValue == 6){
                    //ask for number of times to run
                    numRun = Keyin.inInt("\tNumber of times you would like to run: ");
                }
                
                switch(swValue){
                    case 1:
                    	//Display running command
                        System.out.println("\tRequesting Current Date and Time on Server");
                        //Start time
                        start_time = System.currentTimeMillis();
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:1");
                            String answer;
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer);
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis();
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run
                        System.out.println("\tCompleted Option 1: "+numRun+" time(s).\n");
                        break;

                    case 2:
                        //Display running command
                        System.out.println("\tRequesting Number of Running Processes on Server");
                        //Start time
                        start_time = System.currentTimeMillis();
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:2");
                            String answer;
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer);
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis();
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run
                        System.out.println("\tCompleted Option 2: "+numRun+" time(s).\n");
                        break;

                    case 3:
                    	//Display running command
                        System.out.println("\tRequesting Number of Active Socket Connections on Server");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:3");
                            String answer;
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer); 
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis(); 
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms"); 
                        //Display complete message with number of times command was run
                        System.out.println("\tCompleted Option 3: "+numRun+" time(s).\n");
                        break; 

                    case 4:
                    	//Display running command
                        System.out.println("\tRequesting Time of Server's Last Reboot");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:4");
                            String answer;
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer.trim());
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis(); 
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run                        
                        System.out.println("\tCompleted Option 4: "+numRun+" time(s).\n");
                        break; 

                    case 5:
                    	//Display running command
                        System.out.println("\tRequesting Current Users on Server");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                    	//Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:5");
                            String answer;
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer);
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis(); 
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run                        
                        System.out.println("\tCompleted Option 5: "+numRun+" time(s).\n");
                        break;

                    case 6:
                        //Input to echo
                        String swEcho = Keyin.inString("\tEnter what you would like to send: ");
                        //Display running command
                        System.out.println("\tRequesting \"" + swEcho + "\" to be Sent Back");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                        //Runs command user specified number of times
                        for(int i = 0; i < numRun; i++){
                            //send command to server
                            out.println("MC:6");
                            out.println(swEcho);
                            String answer;
                            //Display server response
                            while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                            {
                                    System.out.println("\t" + answer);
                            }
                        }
                        //End time
                        end_time = System.currentTimeMillis(); 
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms");
                        //Display complete message with number of times command was run                        
                        System.out.println("\tCompleted Option 6: "+numRun+" time(s).\n");
                        break;    

                    case 7:
                        //Display running command
                        System.out.println("\tRequesting Disconnect and Quitting");
                        //Start time
                        start_time = System.currentTimeMillis(); 
                        System.out.println("\tResponse from the server:");
                        //send command to server
                        out.println("MC:7");
                        String answer;
                        //Display server response
                        while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
                        {
                                System.out.println("\t" + answer);
                        }
                        //End time
                        end_time = System.currentTimeMillis(); 
                        //Display time command took
                        System.out.println("\tCommand took " + (end_time-start_time) + "ms"); 
                        //Display complete message and goodbye                        
                        System.out.println("\tOption 7 Completed: Goodbye.\n");
                        break;

                    default:
                        System.out.println("Invalid Option\n");
                        break;
                }
                }
                
        
/*      System.out.println("Requesting system time");
        long start_time = System.currentTimeMillis();
        out.println("MC:1");
        System.out.println("Response from the server:\n");
        // Read lines from the server and print them until "ServerDone" on
        // a line by itself is encountered.
        String answer;
        while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
        {
            System.out.println(answer);
        } 
        long end_time = System.currentTimeMillis();

        System.out.println("Date command took " + (end_time-start_time) + "ms");
*/      
        return;
    }
}
