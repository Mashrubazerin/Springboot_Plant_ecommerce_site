package plant.ecommerce.site.ood.zerin.model;

import javax.persistence.*;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public OrderDetails(Long id, Orders orders, double price, int quantity, Plant plant) {
        this.id = id;
        this.orders = orders;
        this.price = price;
        this.quantity = quantity;
        this.plant = plant;
    }

    public OrderDetails() {
    }

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
