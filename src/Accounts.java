import java.util.Scanner;

class Accounts {
    Scanner sc = new Scanner(System.in);
    protected long accountno;
    protected long balance;
    protected CustomerDetails customer ;

    public Accounts(CustomerDetails customer){
        this.customer = customer;
        this.accountno=customer.accountno;
        //this.accountno = accountno;
        this.balance = customer.accbalance;
        
    }
    public void deposit(){
        System.out.println("Enter the amount: ");
        long amount = sc.nextLong();
        balance=balance+amount;
        System.out.println("Amount Deposited");
    }
    public void withdraw(){
        System.out.println("Enter the  amount you want to withdraw: ");
        long amount = sc.nextLong();
        if(amount>balance){
            System.out.println("Not Enough Balance");
        }
        else{
            balance-=amount;
            System.out.println("Amount Withdrawn Successfully");
        }
    }
    
    
}
class SavingsAccount extends Accounts{

    private static final double interestRate = 6.0;
    private static final double minOpBalance = 10000.0;
    private static final double NRV = 100000.0;
    private static final double NRV_Charge = 1000.0;
    private static final int maxATMWithdrawal = 5;
    private static final double maxAmountWithdrawal = 20000.0;
    private static final double withdrawalCharge = 500.0;
    private static final double maxDailyWithdrawal = 50000.0;
    
    private int ATMWithdrawalCount;
    private int ATMWithdrawalAmount;

    public SavingsAccount(CustomerDetails customer){
        super(customer);
    }
    public void applyInterest(){
        double interest = (balance*interestRate)/100;
        balance+=interest;
    }
    public void ATMWithdrawal(){
        System.out.println("Enter the  amount you want to withdraw: ");
        int amount = sc.nextInt();
        System.out.println(balance);
        if(amount>maxAmountWithdrawal || amount>balance || ATMWithdrawalAmount>maxDailyWithdrawal){
            System.out.println("Withdraw Failed");
            return;
        }
        else{
            if(ATMWithdrawalCount>maxATMWithdrawal){
                balance-=(amount+withdrawalCharge);
                
            }
            else{balance-=amount;}
        ATMWithdrawalAmount+=amount;
        ATMWithdrawalCount++;
        System.out.println("Amount Withdrawn Successfully " + balance);
        }
    }
    public void createATMCard(){

    }
    public void directWithdraw(){
        System.out.println("Enter the  amount you want to withdraw: ");
        long amount = sc.nextLong();
        if(amount>balance || amount>maxAmountWithdrawal || ATMWithdrawalAmount>maxDailyWithdrawal){
            System.out.println("Withdrawal Failed");
        }
        else{
            balance-=amount;
            ATMWithdrawalAmount+=amount;
            System.out.println("Amount Withdrawn Successfully ");
        }
        
    }
    public void ATMWithdrawalreset(){
        ATMWithdrawalAmount=0;
        ATMWithdrawalCount=0;
    }
    public void NRV(){
        if(super.balance<NRV){
            super.balance-=NRV_Charge;
        }
    }
    public long superBalance(){
        return super.balance;
    }
}
class CurrentAccount extends Accounts{

    public CurrentAccount(CustomerDetails customer){
        super(customer);
    }
    private static final double minOpBalance = 100000.0;
    private static final double NRV = 500000.0;
    private static final double NRV_Charge = 5000.0;
    private static final int minMonthlyTransactions = 3;
    private static final double transactionFee = 0.5;
    private static final double maxTransactionFee = 500.0;

    private int monthlyTransactionCount;

    public void NRV(){
        if(balance<NRV){
            balance-=NRV_Charge;
        }
    }
    public void depositMoney(){
        System.out.println("Enter Amount; ");
        int amount = sc.nextInt();
        if(amount>100000){
            balance+=(amount-500);
            monthlyTransactionCount++;
            return;
        }
        balance+=(amount*99.5)/100;
        monthlyTransactionCount++;
    }
    public void withdrawMoney(){
        System.out.println("Enter Amount; ");
        int amount = sc.nextInt();
        if(amount>100000){
            balance-=(amount+500);
            monthlyTransactionCount++;
            return;
        }
        balance-=(amount*100.5)/100;
        monthlyTransactionCount++;
    }
    public void transactionCountCheck(){
        if(monthlyTransactionCount<3){
            balance-=500;
        }
    }

}
class LoanAccount{
    private static final double minLoanAmount = 500000.0;
    private static final double maxLoanAmountPercentage = 40.0;
    private static final int minLoanDuration = 2;
    private static final double homeLoanInterest = 7.0;
    private static final double carLoanInterest = 8.0;
    private static final double personalLoanInterest = 12.0;
    private static final double bussinessLoanInterest = 15.0;


}