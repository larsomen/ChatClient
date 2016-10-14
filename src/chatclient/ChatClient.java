/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author kapteinl
 */
public class ChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        String serverIP = JOptionPane.showInputDialog("Ip adres van de server: ");
        
       
//      innit chat scherm als je functies wilt aan roepen voor scherm is dat via chatCluintUi
        ChatClientUI chatClientUI = new ChatClientUI("jasper is gay");        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                chatClientUI.setVisible(true);                
            }
        });
       
        String[] myStringArray = {"kevin","lars","lars","jasper"};
        chatClientUI.build_User_list(myStringArray);
        
//        Socket s = new Socket(serverIP, 9090);
//        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
//        String answer = input.readLine();
//        JOptionPane.showMessageDialog(null, answer);
//        System.exit(0);
    }
    
}
