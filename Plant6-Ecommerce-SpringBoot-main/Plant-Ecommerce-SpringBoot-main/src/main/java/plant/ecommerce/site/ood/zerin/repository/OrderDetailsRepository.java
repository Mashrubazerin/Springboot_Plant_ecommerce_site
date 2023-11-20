package plant.ecommerce.site.ood.zerin.repository;

import plant.ecommerce.site.ood.zerin.model.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
}
