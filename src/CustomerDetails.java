//import java.net.SocketTimeoutException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
class CustomerDetails{
    public String name;
    //public String Lname;
    public int customerID;
    public long allaccounts[];
    public int accountno;
    public int accounttype;
    private String aadharno;
    public long accbalance;
    public long userAge;
    static int numbers=0;
     //private String 
    Scanner sc = new Scanner(System.in);
    //ArrayList<Integer> arr = new ArrayList<>();
    static Map<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();


    public void newAccount(){

        System.out.print("\nNAME : ");
        //Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        System.out.println("Do you have account in this bank(y/n): ");
        char ch = sc.nextLine().charAt(0);
        accountno=numbers+1;
        numbers++;
        if(ch=='y'){
            System.out.println("Enter your Customer ID: ");
            int cID = sc.nextInt();
            map.get(cID).add(accountno);
        }
        else{
            customerID=generateCustomerId(name);
            map.put(customerID,new ArrayList<Integer>());
            map.get(customerID).add(accountno);
        }  
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
        
        
        
        //accountno = accKey;
        //ThreadLocalRandom.current().nextLong(10000,2000);
        System.out.println("\nAccount Successfully Created");
        System.out.println(customerID);
        System.out.println("Your Account no. is : " + accountno+"\n"+ "Your Customer ID: "+customerID+"\n");
        
    
    }

    public ArrayList<Integer> getAccounts(int cID){
        //System.out.println(map);
        ArrayList<Integer> arr = map.get(cID);
        //System.out.println(arr.size());
        return arr;
    }
    public void showAccounts(){

        
        
        ///System.out.println("Details of account\n");
        System.out.println("Name: "+name);
        System.out.println("Account Type: "+accounttype);
        //System.out.println("Aadhar: "+aadharno);
        System.out.println("Your Balance: "+accbalance);
        System.out.println("\n----------------------\n");
    }
    private int generateCustomerId(String Fname) {
        int sum = 0;
        for (char ch : Fname.toCharArray()) {
            sum += (int) ch;
        }
        return sum;
    }
}