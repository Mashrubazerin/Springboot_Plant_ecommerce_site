package plant.ecommerce.site.ood.zerin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Orders(Long id, Date date, double totalPrice, String address, boolean payment, String paymentType, User user, List<OrderDetails> orderDetailList) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.address = address;
        this.payment = payment;
        this.paymentType = paymentType;
        this.user = user;
        this.orderDetailList = orderDetailList;
    }

    public Orders() {
    }

    private Date date;
    private double totalPrice;
    private String address;
    private boolean payment;
    private String paymentType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<OrderDetails> orderDetailList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetails> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetails> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
