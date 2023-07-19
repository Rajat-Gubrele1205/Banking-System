//import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
class CustomerDetails{
    public String Fname;
    public String Lname;
    public int customerID;
    public long allaccounts[];
    static int accountno;
    public int accounttype;
    private String aadharno;
    public long accbalance;
    public long userAge;
     //private String 
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> arr = new ArrayList<>();
    Map<Integer,ArrayList[]> map = new HashMap<Integer,ArrayList[]>();

    public void newAccount(){


        System.out.println("Do you have account in this bank(y/n): ");
        String ch = sc.nextLine();
        
        System.out.print("\nNAME : ");
        //Scanner sc = new Scanner(System.in);
        Fname = sc.nextLine();
        //customerID=generateCustomerId(Fname);
        
        System.out.print("Age :");
        userAge=sc.nextLong();
        System.out.print("Account Type :\n1.)Savings\n2.)Current\n3.)Loan\n");
        accounttype = sc.nextInt();
        System.out.print("Enter Balance : ");
        accbalance = sc.nextLong();
        if(accounttype==1 && accbalance<10000){
             System.out.println("Permission Denied");
             return;}
        if(accounttype==2 && accbalance<100000 && userAge<18){
             System.out.println("Permission Denied");
             return;}
        if(accounttype==3 && userAge<25){
             System.out.println("Permission Denied");
             return;}
        //System.out.print("Enter Aadhar number : ");
        //aadharno = sc.nextLine();
        accountno=accountno+1;
        // if(ch=="y"){
        //     System.out.println("Enter your Customer ID: ");
        //     long cID = sc.nextLong();
        //     map.put(customerID,arr.add(accountno));
        // }
        //accountno = accKey;
        //ThreadLocalRandom.current().nextLong(10000,2000);
        System.out.println("\nAccount Successfully Created");
        System.out.println(customerID);
        System.out.println("Your Account no. is : " + accountno+"\n");
    
    
    }

    
    public void showAccounts(){
        System.out.println("Details of account\n");
        System.out.println("Name: "+Fname);
        System.out.println("Account Type: "+accounttype);
        System.out.println("Aadhar: "+aadharno);
        System.out.println("Your Balance: "+accbalance);
    }
    private int generateCustomerId(String Fname) {
        int sum = 0;
        for (char ch : Fname.toCharArray()) {
            sum += (int) ch;
        }
        return sum;
    }
}