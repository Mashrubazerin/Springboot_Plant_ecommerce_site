package plant.ecommerce.site.ood.zerin.model;

import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Card(Long id, Plant plant, int quantity, User user) {
        this.id = id;
        this.plant = plant;
        this.quantity = quantity;
        this.user = user;
    }

    public Card() {

    }

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
