package start.test;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by Michael Krickl in 2017.
 */
public class InteractiveClient {
  
  public static void main(String[] args) throws Exception{
  
    Socket socket = new Socket("localhost", 7777);
    
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    
    Scanner cli = new Scanner(System.in);
    String line;
    
    while(!socket.isClosed()) {
      
      line = cli.nextLine();
      
      out.write(line);
      out.newLine();
      out.flush();
      
      System.out.println(in.readLine());
    }
  }
}
