package plant.ecommerce.site.ood.zerin.service;

import plant.ecommerce.site.ood.zerin.model.Orders;
import plant.ecommerce.site.ood.zerin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void save(Orders order) {
        orderRepository.save(order);
    }
}
