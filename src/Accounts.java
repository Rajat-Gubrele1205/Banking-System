
import java.util.*;
import java.time.*;
class Accounts {
    Scanner sc = new Scanner(System.in);
    protected long accountno;
    protected long balance;
    protected CustomerDetails customer ;
    protected List<Transaction> transactions;

    public Accounts(CustomerDetails customer){
        this.customer = customer;
        this.accountno=customer.accountno;
        //this.accountno = accountno;
        this.balance = customer.accbalance;
        this.transactions = new ArrayList<>();
        
    }

    LocalDate date = LocalDate.now();
    
    public void deposit(){
        System.out.println("Enter the amount: ");
        long amount = sc.nextLong();
        balance=balance+amount;
        Transaction transaction = new Transaction("Deposit", amount);
        transactions.add(transaction);
        System.out.println("Amount Deposited Successfully \n" + "Your Current Balance: " + balance);
    }
    public void withdraw(){
        System.out.println("Enter the  amount you want to withdraw: ");
        long amount = sc.nextLong();
        if(amount>balance){
            System.out.println("Not Enough Balance");
        }
        else{
            balance-=amount;
            Transaction transaction = new Transaction("Withdraw", amount);
            transactions.add(transaction);
            System.out.println("Amount Withdrawn Successfully");
        }
    }
    public void transactionHistory() {
        // System.out.println("Enter Your Account Number: ");
        // int accno = sc.nextInt();
        System.out.println("Transaction history for Account Number: " + customer.accountno);
        for (Transaction transaction : transactions) {
            System.out.println("Type: " + transaction.getTransactionType() +
                    ", Amount: " + transaction.getAmount() +
                    ", Timestamp: " + transaction.getTimestamp());
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
        dailyCheck();
        monthCheck();
        //System.out.println(balance);
        if(amount>maxAmountWithdrawal || amount>balance || ATMWithdrawalAmount+amount>maxDailyWithdrawal){
            System.out.println("Withdraw Failed");
            return;
        }
        else{
            if(ATMWithdrawalCount>maxATMWithdrawal){
                balance-=(amount+withdrawalCharge);
                
            }
            else{balance-=amount;}
        Transaction transaction = new Transaction("ATM Withdrawal", amount);
        transactions.add(transaction);    
        ATMWithdrawalAmount+=amount;
        ATMWithdrawalCount++;
        System.out.println("Amount Withdrawn Successfully \n" + "Your Current Balance: " + balance);
        }
    }
    public void createATMCard(){

    }

    public void directWithdraw(){
        System.out.println("Enter the  amount you want to withdraw: ");
        long amount = sc.nextLong();
        dailyCheck();
        if(amount>balance || amount>maxAmountWithdrawal || ATMWithdrawalAmount+amount>maxDailyWithdrawal){
            System.out.println("Withdrawal Failed");
        }
        else{
            balance-=amount;
            ATMWithdrawalAmount+=amount;
            System.out.println("Amount Withdrawn Successfully \n" + "Your Current Balance: " + balance);
        }
        
    }
    public void dailyCheck(){
        if(transactions.isEmpty()){
            return;
        }
        if (!date.equals(transactions.get(transactions.size()-1).getTimestamp())){
            //ATMWithdrawalreset();
            ATMWithdrawalAmount=0;
        }
    }
    public void monthCheck(){
        if(date.getDayOfMonth()==30){
            ATMWithdrawalCount=0;
        }
    }
    // public void ATMWithdrawalreset(){
    //     ATMWithdrawalAmount=0;
    //     ATMWithdrawalCount=0;
    // }

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
        System.out.println("Enter Amount: ");
        int amount = sc.nextInt();
        if(amount>100000){
            balance+=(amount-500);
            monthlyTransactionCount++;
            return;
        }
        balance+=(amount*99.5)/100;
        monthlyTransactionCount++;
        System.out.println("Amount Deposited Successfully \n" + "Your Current Balance: " + balance);
    }
    public void withdrawMoney(){
        System.out.println("Enter Amount; ");
        int amount = sc.nextInt();
        if(amount>100000){
            balance-=(amount+500);
            monthlyTransactionCount++;
            Transaction transaction = new Transaction("Withdraw", amount);
            transactions.add(transaction);
            System.out.println("Amount Withdrawn Successfully \n" + "Your Current Balance: " + balance);
            return;
        }
        balance-=(amount*100.5)/100;
        monthlyTransactionCount++;
        Transaction transaction = new Transaction("Withdraw", amount);
        transactions.add(transaction);
        System.out.println("Amount Withdrawn Successfully \n" + "Your Current Balance: " + balance);
    }
    public void transactionCountCheck(){
        if(monthlyTransactionCount<3){
            balance-=500;
        }
        monthlyTransactionCount=0;
    }
    public boolean monthCheck(){
        if(date.getDayOfMonth()==30){
            return true;
        }
        return false;
    }

    public long superBalance(){
        return super.balance;
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

    private double loanAmount;
    private double interestRate;
    private int loanDuration;
    private CustomerDetails customer;
    private int totalDeposits;
    
    public LoanAccount(CustomerDetails customer){
        this.loanAmount = customer.loanAmount;
        this.loanDuration = customer.loanDuration;
        this.customer = customer;
        this.totalDeposits = customer.totalBalance;
    }
    public void maxEligibleLoan(){
        //  ArrayList<Integer> arr = customer.getAccounts(cID); 
        //             //System.out.println(arr.size());
        //             for(int i=0;i<arr.size();i++){
        //               //  System.out.println(arr.get(i));
        //                 int index2 = indexSearch(arr.get(i));
        //                 customers[index2].showAccounts();
        //             }
    }
    public double setInterestRate(int num){
        switch(num){
            case 1:
                this.interestRate=homeLoanInterest;
                break;
            case 2:
                this.interestRate = carLoanInterest;
                break;
            case 3:
                this.interestRate = personalLoanInterest;
                break;
            case 4:
                this.interestRate = bussinessLoanInterest;
                break;
            default:
                System.out.println("Enter Valid Input");

        }
        return interestRate;
    }
    public boolean checkEligibility(){
        if(loanAmount<minLoanAmount || loanDuration<minLoanDuration || loanAmount>(totalDeposits*maxLoanAmountPercentage)/100){
            return false;
        }
        return true;
    }
    // public int indexSearch(ArrayList<Integer> arr,int accno){
    //     int index=-1;
        
    //     for(int i=0;i<arr.size();i++){
          
    //         if(customer.accountno == accno){
            
    //             index=i;
    //             break;
    //         }
                      
    //     }
    //     return index;
    // }

}