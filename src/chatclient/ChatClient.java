package chatclient;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ChatClient implements Runnable {

  // The client socket
  private static Socket clientSocket = null;
  // The output stream
  private static PrintStream os = null;
  // The input stream
  private static DataInputStream is = null;

  private static BufferedReader inputLine = null;
  private static boolean closed = false;
  
  private static ChatClientUI chatClientUI;
  
  public static void main(String[] args) {

    

    // The vraag host
    String host = JOptionPane.showInputDialog("Host:");
    // The vraag port
    int portNumber = Integer.parseInt(JOptionPane.showInputDialog("port:"));
    // open gui
    chatClientUI = new ChatClientUI("Super awsome chat");        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                chatClientUI.setVisible(true);                
            }
        });
    
    /*
     * socket open op port en host en streams
     */
    try {
      clientSocket = new Socket(host, portNumber);
      inputLine = new BufferedReader(new InputStreamReader(System.in));
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
    } catch (UnknownHostException e) {
      System.err.println("Kan niet verbinden met " + host);
    } catch (IOException e) {
      System.err.println("Kan I/O niet verkrijgen voor verbinding met "
          + host);
    }

    /*
     * If everything has been initialized then we want to write some data to the
     * socket we have opened a connection to on the port portNumber.
     */
    if (clientSocket != null && os != null && is != null) {
      try {

        /* server reader thread */
        new Thread(new ChatClient()).start();
        while (!closed) {
          os.println(inputLine.readLine().trim());
        }
        /*
         * allees afsluiten als niet meer nodig is.
         */
        os.close();
        is.close();
        clientSocket.close();
      } catch (IOException e) {
        System.err.println("IOException:  " + e);
      }
    }
  }

  
  public void run() {
    /*
     * blijf open tot de server zegt dat je moet afsluiten
     */
    
    String responseLine;
    try {
      while ((responseLine = is.readLine()) != null) {
//      zet text in text box van gui
        chatClientUI.append_To_Chat_Box(responseLine);
        System.out.println(responseLine);
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
  }
  
  public static void sendMessage(String message){
      os.println(message);
  }
}
