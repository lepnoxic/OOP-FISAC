import java.util.Scanner;

class balanceExceededException extends Exception {
    balanceExceededException() {;}
}

class banking {
    double balance;

    banking() {
        balance=0.0;
    }

    void deposit(double x) {
        balance+=x;
    }

    void withdraw(double x) {
        try {
            if(balance<x) throw new balanceExceededException();
            balance-=x;
        }
        catch (balanceExceededException b) {
            System.out.println("Your account does not have sufficient balance to make this withdrawal!\n");
        }
    }

    void display() {
        System.out.println("Balance: "+balance+"\n");
    }
}

class Main {
    public static void main(String args[]) {
        banking a = new banking();
        Scanner s = new Scanner(System.in);
        int ch;
        double d;
        boolean flag=true;
        while(flag) {
            System.out.println("1.Deposit\t2.Withdraw\t3.Display\t0.Exit\n");
            ch = s.nextInt();
            switch(ch) {

                case 1:
                System.out.println("Enter deposit amount\n");
                d = s.nextInt();
                a.deposit(d);
                break;
                case 2:

                System.out.println("Enter withdrawal amount\n");
                d = s.nextInt();
                a.withdraw(d);
                break;

                case 3:
                a.display();
                break;

                case 0:
                flag = false;
                break;

                default:
                System.out.println("Invalid input\n");
            }
        }
    }
}