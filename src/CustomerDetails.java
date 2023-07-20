//import java.net.SocketTimeoutException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
class CustomerDetails{
    public String name;
    public int customerID;
    public int accountno;
    public int accounttype;
    public long accbalance;
    public long userAge;
    static int numbers=0;
    public int loanType;
    public int loanAmount;
    public int loanDuration;
    public LoanAccount loan;
    public int totalBalance;
    public ArrayList<Integer> arr;

    Scanner sc = new Scanner(System.in);

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
            System.out.println("\nEnter your Customer ID: ");
            int cID = sc.nextInt();
            if(!map.containsKey(cID)){
                System.out.println("Customer ID invalid");
            }
            int exitcode1=getData();
            if(exitcode1==1){return;}

            // For Loan Account


            if(accounttype==3){
                System.out.println("Enter the Loan Amount: ");
                loanAmount = sc.nextInt();
                System.out.println("Purpose of your Loan:\n1.)Home Loan\n2.)Car Loan\n3.)Personal Loan\n4.)Bussiness Loan");
                loanType= sc.nextInt();
                System.out.println("Enter the Loan Duration(Min 2 years)");
                loanDuration = sc.nextInt();
                ArrayList<Integer> arr = map.get(cID); 
                
                    //System.out.println(arr.size());
                    // for(int i=0;i<arr.size();i++){
                      //   //  System.out.println(arr.get(i));
                    //     int index2 = indexSearch(arr.get(i));
                    //     customers[index2].showAccounts();
                    // }
            }


            // System.out.print("Enter Balance : ");
            // accbalance = sc.nextLong();
            map.get(cID).add(accountno);
            
            System.out.println("\nAccount Successfully Created");
            //System.out.println(customerID);
            System.out.println("Your Account no. is : " + accountno+"\n"+ "Your Customer ID: "+cID+"\n");
            }
        else if(ch=='n'){
            int exitcode2=getData();
            if(exitcode2==1){return;}

            if(accounttype==3){System.out.println("Not Eligible for Loan Account");}
            
            customerID=generateCustomerId(name);
            map.put(customerID,new ArrayList<Integer>());
            map.get(customerID).add(accountno);
            System.out.println("\nAccount Successfully Created");
        //System.out.println(customerID);
            System.out.println("Your Account no. is : " + accountno+"\n"+ "Your Customer ID: "+customerID+"\n");
            }  
        
    
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
    public int getData(){
        System.out.print("Age :");
        userAge=sc.nextLong();
        System.out.print("Account Type :\n1.)Savings\n2.)Current\n3.)Loan\n");
        accounttype = sc.nextInt();
        if(accounttype!=3){
        System.out.print("Enter Balance : ");
        accbalance = sc.nextLong();
        }
        if(accounttype==3 && userAge<25){
             System.out.println("Permission Denied");
             numbers--;
             return 1;}
        if(accounttype==1 && accbalance<10000){
             System.out.println("Permission Denied");
             numbers--;
             return 1;}
        if(accounttype==2 && (accbalance<100000 || userAge<18)){
             System.out.println("Permission Denied");
             numbers--;
             return 1;}
        return 0;    
        }
}