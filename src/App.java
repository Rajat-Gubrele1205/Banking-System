import java.util.*;
import java.time.*;


public class App {
    public static CustomerDetails customers[] = new CustomerDetails[1000];
    public static SavingsAccount savingCustomer[] = new SavingsAccount[1000];
    public static CurrentAccount currentCustomer[] = new CurrentAccount[1000];
    public static LoanAccount loanCustomer[] = new LoanAccount[1000];
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int accKey = -1;
        int input=0;
        boolean loaded=false;
        while(input!=6){
            
            System.out.println("Welcome to Inito Bank\n");
            System.out.println("1.) Open an account\n2.) Withdraw/Deposit\n3.) Show Accounts\n4.) Print PassBook\n5.) Profit/Loss\n");
            input = sc.nextInt();
            switch(input){

                case 1:
                    accKey++;
                    customers[accKey] = new CustomerDetails();
                    customers[accKey].newAccount();
                    savingCustomer[accKey] = new SavingsAccount(customers[accKey]);
                    currentCustomer[accKey] = new CurrentAccount(customers[accKey]);
                    // savingCustomer[accKey].createATMCard();
                    // savingCustomer[accKey].ATM_Card=customers[accKey].ATM_Card;
                    // savingCustomer[accKey].CVV=customers[accKey].CVV;
                    // savingCustomer[accKey].expiryYear=customers[accKey].expiryYear;
                    // Loan account
                    if(customers[accKey].accounttype==3){
                        ArrayList<Integer> arr1 = customers[accKey].arr1;
                      //3  System.out.println(arr1); 
                    //System.out.println(arr1.size());
                        long totalBalance=0;
                        for(int i=0;i<arr1.size();i++){
                        //  System.out.println(arr.get(i));
                            int index2 = indexSearch(arr1.get(i));
                            //System.out.println(index2);
                            totalBalance+=customers[index2].accbalance;
                    }
                    //System.out.print(totalBalance);
                    customers[accKey].totalBalance=totalBalance;
                    //System.out.print(customers[accKey].totalBalance);
                    loanCustomer[accKey] = new LoanAccount(customers[accKey]);
                    if(loanCustomer[accKey].checkEligibility()){
                        customers[accKey].loanData(customers[accKey].customerID);
                        loanCustomer[accKey].creationDate = customers[accKey].date;
                        //customers[accKey].map.get(customers[accKey].customerID).add(customers[accKey].accountno);
                    }
                    }
                    
                    break;

                case 2:
                    System.out.println("Enter the Account Number: ");
                    int accnoInput = sc.nextInt();
                    System.out.println("1.)Withdraw\n2.)Deposit");
                    int transactiontype = sc.nextInt();
                    int index=indexSearch(accnoInput);
                    int type = customers[index].accounttype;
                    if(transactiontype==1){
                        if(type==1){

                        System.out.println("1.)Direct\n2.)ATM");
                        int withdrawalType = sc.nextInt();
                        if(withdrawalType==1){
                            savingCustomer[index].directWithdraw();
                            customers[index].accbalance=savingCustomer[index].superBalance();
                        }
                        else if(withdrawalType==2){
                            savingCustomer[index].ATMWithdrawal();
                            customers[index].accbalance=savingCustomer[index].superBalance();
                        }
                        
                    }
                    else if(type==2){
                        currentCustomer[index].withdrawMoney();
                        customers[index].accbalance=currentCustomer[index].superBalance();
                    }
                    }
                    else if(transactiontype==2){
                        if(type==1){
                            savingCustomer[index].deposit();
                            customers[index].accbalance=savingCustomer[index].superBalance();
                        }
                        else if(type==2){
                            currentCustomer[index].depositMoney();
                            customers[index].accbalance=currentCustomer[index].superBalance();
                        }
                    }
                    
                    
                    break;
                case 3:
                    // accKey++;
                    // customers[accKey] = new CustomerDetails();
                    
                    System.out.println("Enter the Customer ID: ");
                    int cID = sc.nextInt();
                    
                    
                    ArrayList<Integer> arr = customers[0].getAccounts(cID); 
                    //System.out.println(arr.size());
                    for(int i=0;i<arr.size();i++){
                      //  System.out.println(arr.get(i));
                        int index2 = indexSearch(arr.get(i));
                        customers[index2].showAccounts();
                    }
                    break;
                case 4:
                    System.out.println("Enter the Account Number: ");
                    int accnoInput4 = sc.nextInt();
                    int index4=indexSearch(accnoInput4);
                    int type4 = customers[index4].accounttype;
                    if(type4==1){
                        savingCustomer[index4].transactionHistory();
                    }
                    else if(type4==2){
                        currentCustomer[index4].transactionHistory();
                    }
                    break;
                case 5:
                    System.out.println("Enter the Account Number: ");
                    int accnoInput1 = sc.nextInt();
                    
                    int index3=indexSearch(accnoInput1);
                    int type1 = customers[index3].accounttype;
                    if(type1==1){
                        savingCustomer[index3].netProfit();;
                            
                    }
                    else if(type1==2){
                        currentCustomer[index3].netProfit();
                        
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid Input\n");
                    break;    
            }  
            LocalDate date = LocalDate.now();
            if(!monthCheck(date)){loaded=false;}
            if(!loaded){
                if(monthCheck(date)){
                for(int i=0;i<savingCustomer.length;i++){
                    if(savingCustomer[i]!=null){
                        savingCustomer[i].applyInterest();
                        savingCustomer[i].NRV();
                        customers[i].accbalance=savingCustomer[i].superBalance();
                    }
                    if(currentCustomer[i]!=null){
                        currentCustomer[i].NRV();
                        currentCustomer[i].transactionCountCheck();
                        customers[i].accbalance=currentCustomer[i].superBalance();
                    }
                }
                loaded=true;
            }
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

    public static boolean monthCheck(LocalDate date){
        if(date.getDayOfMonth()==20){
            return true;
        }
        return false;
    }
}

