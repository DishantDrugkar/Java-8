package org.example.oops.polymorphism;

class PaymentWays{
    void pay(){
        System.out.println("Ways of Payments");
    }
}

class CreditCard extends PaymentWays{
    @Override
    void pay(){
        System.out.println("Paid using Credit card");
    }
}

class UPI extends PaymentWays{
    @Override
    void pay(){
        System.out.println("Paid Using UPI Id");
    }
}

public class Overriding {
    public static void main(String[] args) {
       PaymentWays p1 = new CreditCard();
       PaymentWays p2 = new UPI();

       p1.pay();
       p2.pay();
    }
}
