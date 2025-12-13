package org.example.oops.polymorphism;

class Payment{
    void pay(double amount){
        System.out.println("Paid " + amount + " By Cash");
    }

    void pay(String UpiId, double amount){
        System.out.println("Paid " + amount + " By UPI Id : " + UpiId);
    }

    void pay(long creditCardNumber, double amount){
        System.out.println("Paid " + amount + " By Credit Card Number : " + creditCardNumber);
    }
}
public class Overloading {
    public static void main(String[] args) {
        Payment payment = new Payment();
        payment.pay(500.0);
        payment.pay("Dishant@UPI",1000.0);
        payment.pay(12346789, 5000.0);

    }
}
