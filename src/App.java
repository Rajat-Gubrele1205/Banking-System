import java.util.Scanner;

public class App {
    static CustomerDetails customers[] = new CustomerDetails[1000];
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int accKey = -1;
        int input=0;
        
        //CustomerDetails customers[] = new CustomerDetails[1000];
        while(input!=4){
            System.out.println("Welcome to Inito Bank\n");
            System.out.println("1.) Open an account\n2.) Withdraw\n3.) ATM Withdrawal\n4.) Exit\n");
            input = sc.nextInt();
            switch(input){

                case 1:
                    accKey++;
                    customers[accKey] = new CustomerDetails();
                    customers[accKey].newAccount();
                    break;
                case 2:
                    System.out.println("Enter the Account Number: ");
                    int accnoInput = sc.nextInt();
                    //boolean ifExist=false;
                    int index=search(accnoInput);
                    int type = customers[index].accounttype;
                    if(type==1){
                        SavingsAccount user = new SavingsAccount(customers[index]);
                        user.directWithdraw();
                        customers[index].accbalance=user.superBalance();
                    //  System.out.println(user.superBalance());
                    // System.out.println(customers[index].accbalance); 
                    }
                    //customers[index].showAccounts();
                    break;
                case 3:
                    // System.out.println("Enter the Account Number: ");
                    // long accnoInput = sc.nextLong();
                    // boolean ifExist=false;
                    // int index=0;
                    // for(int i=0;i<customers.length;i++){
                    //     if(customers[i].accountno == accnoInput){
                    //         ifExist=true;
                    //         index=i;
                    //         break;
                    //     }
                    //     //if (ifExist){break;}
                    // }
                    // int type = customers[index].accounttype;
                    // if(type==1){
                    //     SavingsAccount user = new SavingsAccount(customers[index]);
                    //     user.withdraw();
                    // }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input\n");
                    break;    
            }  
        }
        
        














    }
    // public Object cust(int index){
    //     return customers[index];
    // }
    public static int search(int accno){
        int index=-1;
        for(int i=0;i<customers.length;i++){
            if(customers[i].accountno == accno){
                            //ifExist=true;
                index=i;
                break;
            }
                        //if (ifExist){break;}
        }
        return index;
    }
}

