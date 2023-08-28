/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package point_of_sell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.layout.VBox;

/**
 *
 * @author user
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    
    public Server(ServerSocket serverSocket){
        
        try{
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch(IOException e){
            System.out.println("Error creating server.");
            e.printStackTrace();
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
        
    }
    
    public void sendMessageToClient(String messageToClient){
        try{
            bufferedWriter.write(messageToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error sending message to client");
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    
    public void receiveMessageFromClient(VBox vBox){
       new Thread(new Runnable(){
           @Override
           public void run() {
              while(socket.isConnected()){
                  
                  try{
                      String messageFromClient = bufferedReader.readLine();
                      MessengerController.addLabel(messageFromClient, vBox);
                  }catch(IOException e){
                      e.printStackTrace();
                      System.out.println("Error receving message from client");
                      closeEverything(socket,bufferedReader,bufferedWriter);
                      break;
                  }
                  
              }
           }
           
       }).start();
    }
    
    public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
