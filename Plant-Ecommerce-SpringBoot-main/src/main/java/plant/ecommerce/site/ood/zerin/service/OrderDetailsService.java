package plant.ecommerce.site.ood.zerin.service;

import plant.ecommerce.site.ood.zerin.model.Card;
import plant.ecommerce.site.ood.zerin.model.Orders;
import plant.ecommerce.site.ood.zerin.model.OrderDetails;
import plant.ecommerce.site.ood.zerin.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private CardService cardService;

    public void save(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    public void saveBulk(List<Card> cards, Orders order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrders(order);
        for (Card card: cards) {
            orderDetails.setPrice(card.getPlant().getPrice());
            orderDetails.setPlant(card.getPlant());
            orderDetails.setQuantity(card.getQuantity());

            cardService.deleteCard(card);
            save(orderDetails);
        }
    }
}
