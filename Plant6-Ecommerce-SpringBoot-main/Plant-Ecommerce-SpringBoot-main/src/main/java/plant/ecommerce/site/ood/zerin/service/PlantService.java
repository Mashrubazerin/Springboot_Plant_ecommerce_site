package plant.ecommerce.site.ood.zerin.service;

import plant.ecommerce.site.ood.zerin.model.Plant;
import plant.ecommerce.site.ood.zerin.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {
    @Autowired
    private PlantRepository productRepository;

    public List<Plant> getAll() {
        return (List<Plant>) productRepository.findAll();
    }

    public Plant get(long id) {
        return productRepository.getPlantById(id);
    }

    public void save(Plant plant) {
        productRepository.save(plant);
    }

    public void delete(long id) {
        productRepository.delete(get(id));
    }

}
