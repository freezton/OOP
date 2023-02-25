public class Electronics extends Product {
    String brand;
    String model;

    public Electronics(int id, String name, double price, String description, String brand, String model) {
        super(id, name, price, description);
        this.brand = brand;
        this.model = model;
    }
}
