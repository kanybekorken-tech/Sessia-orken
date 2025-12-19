interface IDeliveryMethod {
    double calculateCost();
    String trackStatus();
}

interface IPaymentMethod {
    void processPayment();
    void refundPayment();
    boolean confirmPayment();
}

class CourierDelivery implements IDeliveryMethod {
    public double calculateCost() {
        return 1500;
    }

    public String trackStatus() {
        return "Курьер жеткізіп жатыр";
    }
}

class PickupDelivery implements IDeliveryMethod {
    public double calculateCost() {
        return 0;
    }

    public String trackStatus() {
        return "Өзі алып кетуге дайын";
    }
}

class PostalDelivery implements IDeliveryMethod {
    public double calculateCost() {
        return 2500;
    }

    public String trackStatus() {
        return "Пошта арқылы жеткізілуде";
    }
}

class CardPayment implements IPaymentMethod {
    public void processPayment() {
        System.out.println("Карта арқылы төлем жасалды");
    }

    public void refundPayment() {
        System.out.println("Картаға қайтарым жасалды");
    }

    public boolean confirmPayment() {
        return true;
    }
}

class CashPayment implements IPaymentMethod {
    public void processPayment() {
        System.out.println("Қолма-қол төлем қабылданды");
    }

    public void refundPayment() {
        System.out.println("Қолма-қол қайтарым жасалды");
    }

    public boolean confirmPayment() {
        return true;
    }
}

class OnlinePayment implements IPaymentMethod {
    public void processPayment() {
        System.out.println("Онлайн төлем өңделді");
    }

    public void refundPayment() {
        System.out.println("Онлайн төлем қайтарылды");
    }

    public boolean confirmPayment() {
        return true;
    }
}

interface OrderFactory {
    IDeliveryMethod createDelivery();
    IPaymentMethod createPayment();
}

class CourierCardFactory implements OrderFactory {
    public IDeliveryMethod createDelivery() {
        return new CourierDelivery();
    }

    public IPaymentMethod createPayment() {
        return new CardPayment();
    }
}

class PickupCashFactory implements OrderFactory {
    public IDeliveryMethod createDelivery() {
        return new PickupDelivery();
    }

    public IPaymentMethod createPayment() {
        return new CashPayment();
    }
}

class PostalOnlineFactory implements OrderFactory {
    public IDeliveryMethod createDelivery() {
        return new PostalDelivery();
    }

    public IPaymentMethod createPayment() {
        return new OnlinePayment();
    }
}

public class Main {
    public static void main(String[] args) {
        OrderFactory factory = new CourierCardFactory();

        IDeliveryMethod delivery = factory.createDelivery();
        IPaymentMethod payment = factory.createPayment();

        System.out.println(delivery.calculateCost());
        System.out.println(delivery.trackStatus());

        payment.processPayment();
        System.out.println(payment.confirmPayment());
    }
}