/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
 import java.io.File;  // Import the File class
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;  

/**
 *
 * @author polkumar
 */

public class FileModify {
   
    public static void userWrite(String username){
    try {
      int account;
      account = 1000;
      String finluserdetail = account+","+username+","+10000; 
      account = account +1;
      BufferedWriter myWriter = new BufferedWriter(new FileWriter("C:\\Users\\polkumar\\Documents\\User.txt",true));
      File file = new File("C:\\Users\\polkumar\\Documents\\User.txt");
      if (file.length()==0){
          myWriter.write(finluserdetail);
          myWriter.newLine();
      }
      else {
      myWriter.newLine();
      myWriter.write(finluserdetail);}
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}
    public static void userRead(){
    try {
 
      BufferedReader myReader = new BufferedReader(new FileReader("C:\\Users\\polkumar\\Documents\\User.txt"));
       String strCurrentLine;
      
       ArrayList<CreatePojo> hm = new ArrayList<>();
       String lastLine;
       
        System.out.println("Size is "+hm.size());
      
      while ((strCurrentLine = myReader.readLine()) != null) {
          CreatePojo cr = new CreatePojo();
          String arr[] = strCurrentLine.split(",");
            cr.setAccountnumber(Integer.parseInt(arr[0]));
            cr.setUsername(arr[1]);
            cr.setAmount(Integer.parseInt(arr[2]));
            hm.add(cr);  
   }  
      
      System.out.println(hm.size());
       for (int i=0;i<hm.size();i++) {
       			System.out.println(hm.get(i).getAccountnumber());
                        System.out.println(hm.get(i).getAmount());
                        System.out.println(hm.get(i).getUsername());
	} 
       
        for (int i=0;i<hm.size();i++) {
       
       if(hm.get(i).getAccountnumber() == 1000)
       {
           hm.get(i).setAmount(95);
            System.out.println(hm.get(i).getAmount());
       }
        }
        
        clearTheFile("User");
        String fileString;
        for (int i=0;i<hm.size();i++) {
           fileString = hm.get(i).getAccountnumber()+","+hm.get(i).getUsername()+","+hm.get(i).getAmount();
           fileUpdate(fileString);
        }
        
        
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}

     public static void fileUpdate(String username){
    try {
      
      BufferedWriter myWriter = new BufferedWriter(new FileWriter("C:\\Users\\polkumar\\Documents\\User.txt",true));
      
      myWriter.write(username);
      myWriter.newLine();
      myWriter.close();
      
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}
    public static void clearTheFile(String user) throws IOException {
         String filename = "C:\\Users\\polkumar\\Documents\\" + user + ".txt";
        FileWriter fwOb = new FileWriter(filename, false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
       
  public static void main(String[] args) {
    try {
      File myObj = new File("C:\\Users\\polkumar\\Documents\\User.txt");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter User Name : ");
      String username = sc.next();
   //  userWrite(username);
     userRead();
      }
    }
     
   catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
        }
  }
}



