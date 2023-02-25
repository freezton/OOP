public class Clothing extends Product {
    int size;
    String material;
    String color;

    public Clothing(int id, String name, double price, String description, int size, String material, String color) {
        super(id, name, price, description);
        this.size = size;
        this.material = material;
        this.color = color;
    }
}
