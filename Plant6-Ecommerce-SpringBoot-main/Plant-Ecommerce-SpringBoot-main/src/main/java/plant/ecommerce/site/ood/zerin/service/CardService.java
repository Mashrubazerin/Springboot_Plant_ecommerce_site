package plant.ecommerce.site.ood.zerin.service;

import plant.ecommerce.site.ood.zerin.model.Card;
import plant.ecommerce.site.ood.zerin.model.Plant;
import plant.ecommerce.site.ood.zerin.model.User;
import plant.ecommerce.site.ood.zerin.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> listAll(long id) {
        return (List<Card>) cardRepository.findCardsByUserId(id);
    }

    public Card get(long id) {
        return cardRepository.findCardById(id);
    }

    public void addToCard(Plant plant, User user) {

        Card card = null;

        card = cardRepository.findCardByPlantIdAndUserId(plant.getId(), user.getId());

        if (card == null) {
            card = new Card();
            card.setUser(user);
            card.setPlant(plant);
            card.setQuantity(1);
        } else {
            card.setQuantity(card.getQuantity()+1);
        }

        cardRepository.save(card);
    }

    public void updateCard(long cardId, int quantity) {
        Card card = getById(cardId);
        card.setQuantity(quantity);

        cardRepository.save(card);
    }

    public void deleteCard(Card card) {
        cardRepository.delete(card);
    }

    Card getById(long id) {
        return cardRepository.findCardById(id);
    }
}
