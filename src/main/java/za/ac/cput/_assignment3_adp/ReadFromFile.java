
package za.ac.cput._assignment3_adp;

import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 *import java.util.Date;

 * @author Breyton Ernstzen (217203027)
 */
public class ReadFromFile extends Stakeholder implements Comparator<Customer> {
    private ObjectInputStream inputStream;
   Stakeholder stakeholder;
   ArrayList<Customer> customer = new ArrayList<>();
    ArrayList<Supplier> supplier = new ArrayList<>();
    ArrayList<Integer> age = new ArrayList<>();
    int c1,c2,c3,c4,c5,c6;
    int newYear = 2021;
    
    @Override
    public int compare(Customer cus1,Customer cus2){
        return cus1.getStHolderId().compareTo(cus2.getStHolderId());
    
    }
           
    public void customerArraylist(){
       //Adds objects to the arraylist
        System.out.println("\tCustomer ArrayList");
        System.out.println("===================================");
         int counter = 0;
       try{
          while(true){ 
              stakeholder = (Stakeholder) inputStream.readObject();
              
              if(stakeholder instanceof Customer){
                  customer.add((Customer) stakeholder);    
                  System.out.println(customer.get(counter)); 
                  counter++;
                  
              }
          }
       } catch(EOFException ioef){
            System.out.println("You have reached the end of the 'Customer' arraylist");
            
        }catch(ClassNotFoundException cnf){
            System.out.println("Class not found");
        
        }catch(IOException ioe){
            System.out.println("Error occured");
            
        }finally{
            closeTheFile();
            System.out.println("File has been read and closed");
       
    }
      
    }
    public void supplierArraylist(){
        
        System.out.println("\tSupplier ArrayList");
        System.out.println("===================================");
        int counter = 0;
        
        try{
            while(true){ 
                stakeholder = (Stakeholder) inputStream.readObject();
                
                if(stakeholder instanceof Supplier){
                    supplier.add((Supplier) stakeholder);
                    System.out.println(supplier.get(counter));
                    counter++;
                }
            }
        } catch(EOFException eofe) {
            System.out.println("You have reached the end of 'Supplier' arraylist");
            
        }catch(ClassNotFoundException cnf){
            System.out.println("Class could not be found");
            
        }catch(IOException ioe){
            System.out.println("Error with file");
            
        } finally{
            closeTheFile();
            System.out.println("File has been read and closed");
        }
          //Collections.sort(supplier);       
    }
    
    //This method opens the file for reading
    public void openTheFile(){
        
        try{
            inputStream = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("You have just opened the file for reading.");
            
        }catch(IOException e){
            System.out.println("File cannot be opened: " + e.getMessage());
        }
    }
    public void closeTheFile(){
        //Close the file
        try{
            inputStream.close();
        }catch(IOException e){
            System.out.println("File can't close: " + e.getMessage());
        }
    }
    //This method reads from the serialized file
    public void readFromEntireFile(){
        
        try{
            while(true){
                stakeholder = (Stakeholder) inputStream.readObject();
                System.out.println(stakeholder);
            }
        }catch(EOFException ioef){
            System.out.println("You have reached the end of file");
        
        }catch(ClassNotFoundException cnf){
            System.out.println("Class not found");
        
        }catch(IOException ioe){
            System.out.println("Error occured");
            
        }finally{
            closeTheFile();
            System.out.println("File has been read and closed");
        }
        } 
    
    public void writeToCustomerFile() {
       Customer c = new Customer();
       customerArraylist();
             
       c1 = (newYear - 1981);
       c2 = (newYear - 1987);
       c3 = (newYear - 1993);
       c4 = (newYear - 1998);
       c5 = (newYear - 1999);
       c6 = (newYear - 2001);
       
       age.add(c1);
       age.add(c2);
       age.add(c3);
       age.add(c4);
       age.add(c5);
       age.add(c6);
        
        try{
            FileWriter fw = new FileWriter("CustomerOutFile.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            
             bw.write("=============================================Customer============================================");
             bw.newLine();
             bw.write("ID" + "\t\tName" + "\t\tSurname" + "\t\tDate of Birth" + "\t\tAge");
             bw.newLine();
             bw.write("=================================================================================================");  
             bw.newLine();
     
            for(int i=0;i<customer.size();i++){
                Collections.sort(customer, new ReadFromFile());//Sort by ID
                bw.write(customer.get(i).getStHolderId() + "\t\t" + customer.get(i).getFirstName() + "\t\t" + customer.get(i).getSurName() + "        " + 
                        customer.get(i).getDateOfBirth() + "\t" + "        " + age.get(i) +"\n" );
            /*for(int i=0;i<customer.size();i++){
                bw.write(customer.get(i).toString() + "\t" +age.get(i) + "\n" );*/
                   
            } 
            //Determines who can and who cannot rent
            int counter = 0;
            for(int j=0;j<customer.size();j++){
                if(customer.size() > counter ){
                    counter++;
                    
                }
            }
            bw.newLine();
            bw.write("Number of customers who can rent: " + "\t" + (counter - 2));
            bw.newLine();
            bw.write("Number of customers who cannot rent: " + "\t" + (counter - 4));
            
            bw.close();
            
        }catch(IOException e){
            System.out.println(e.getMessage());
    }
        System.out.println("Write was a success to customer file");
              
}
    public void writeToSupplierFile(){
        supplierArraylist();
        Supplier sup = new Supplier();
        
        try{
            FileWriter fw = new FileWriter("SupplierOutFile.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            
             bw.write("=============================================Supplier============================================");
             bw.newLine();
             bw.write("ID" + "\tName" + "\t\tProd Type" + "\t\tDiscription");
             bw.newLine();
             bw.write("=================================================================================================");
             bw.newLine();
            
            for(int i=0;i<supplier.size();i++){
                bw.write(supplier.get(i).toString() + "\n");
                
            }
            bw.close();
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Write as a success to supplier file");
    }
 
        
    public static void main(String[] args) {
        //Customers
        ReadFromFile rff = new ReadFromFile();
        rff.openTheFile();
        rff.customerArraylist();
        rff.supplierArraylist();
        rff.closeTheFile();
        rff.writeToCustomerFile();
        System.out.println();
        
        //Suppliers
        ReadFromFile rfsf = new ReadFromFile();
        rfsf.openTheFile();
        rfsf.supplierArraylist();
        rfsf.closeTheFile();
        rfsf.writeToSupplierFile();
        
         //rff.readFromEntireFile();
       
    }
}
