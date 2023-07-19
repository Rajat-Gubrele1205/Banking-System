import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class App {
    public static CustomerDetails customers[] = new CustomerDetails[1000];
    public static SavingsAccount users[] = new SavingsAccount[1000];
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int accKey = -1;
        int input=0;
        // for(int i=0;i<users.length;i++){
        //     users[i] = new SavingsAccount(customers[i]);
        // }
       
        while(input!=4){
            System.out.println("Welcome to Inito Bank\n");
            System.out.println("1.) Open an account\n2.) Withdraw\n3.) Show Accounts\n4.) Exit\n");
            input = sc.nextInt();
            switch(input){

                case 1:
                    accKey++;
                    customers[accKey] = new CustomerDetails();
                    customers[accKey].newAccount();
                    users[accKey] = new SavingsAccount(customers[accKey]);
                    break;
                case 2:
                    System.out.println("Enter the Account Number: ");
                    int accnoInput = sc.nextInt();
                    
                    int index=indexSearch(accnoInput);
                    int type = customers[index].accounttype;
                    if(type==1){
                        
                        users[index].ATMWithdrawal();
                        customers[index].accbalance=users[index].superBalance();
                   
                    }
                    
                    break;
                case 3:
                    accKey++;
                    customers[accKey] = new CustomerDetails();
                    System.out.println("Enter the Customer ID: ");
                    int cID = sc.nextInt();
                    ArrayList<Integer> arr = customers[accKey].getAccounts(cID); 
                    //System.out.println(arr.size());
                    for(int i=0;i<arr.size();i++){
                      //  System.out.println(arr.get(i));
                        int index2 = indexSearch(arr.get(i));
                        customers[index2].showAccounts();
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input\n");
                    break;    
            }  
        }
        
    
    }
    
    public static int indexSearch(int accno){
        int index=-1;
        
        for(int i=0;i<customers.length;i++){
          
            if(customers[i].accountno == accno){
            
                index=i;
                break;
            }
                      
        }
        return index;
    }
}

