package task_3;

public class Cat {
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double summaAmount = 0;
    private static int count = 0;
    private boolean isLive = true;

    public static final int EYES = 2;
    public static final int MAX_WEIGHT = 9000;
    public static final int MIN_WEIGHT = 1000;

    private Color color;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cat(double weight) {
        this();
        this.weight = weight;
    }

    public static int getCount() {
        return count;
    }

    public void meow() {
        weight = weight - 1;
        //System.out.println("Meow");
    }

    public void feed(Double amount) {
        if (isLive) {
            weight = weight + amount;
            summaAmount = summaAmount + amount;
        } else System.out.println("Кошки нет...");

    }

    public void drink(Double amount) {
        if (isLive)
            weight = weight + amount;
        else System.out.println("Кошки нет...");

    }

    public Double getWeight() {
        return weight;
    }

    public String getStatus() {
        if (weight < minWeight) {
            isLive = false;
            return "Dead";
        } else if (weight > maxWeight) {
            isLive = false;
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

    public Double getSummaAmount() {

        return summaAmount;
    }

    public void pee() {
        if (isLive) {
            weight = weight - 2;
            System.out.println("Кошка сделала дело");
        } else System.out.println("Кошки нет...");

    }

    public Cat(Cat copy) {
        this.weight = copy.weight;
        this.originWeight = copy.originWeight;
        this.maxWeight = copy.maxWeight;
        this.minWeight = copy.minWeight;
        count++;
    }
}