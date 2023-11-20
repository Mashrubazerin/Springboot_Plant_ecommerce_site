package plant.ecommerce.site.ood.zerin.controller;

import plant.ecommerce.site.ood.zerin.model.Card;
import plant.ecommerce.site.ood.zerin.model.Plant;
import plant.ecommerce.site.ood.zerin.model.User;
import plant.ecommerce.site.ood.zerin.service.CardService;
import plant.ecommerce.site.ood.zerin.service.PlantService;
import plant.ecommerce.site.ood.zerin.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CardController {

    @Autowired
    private UserDetailsServiceImplement userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private CardService cardService;

    @GetMapping("/card")
    public String card(Model model,
                       Principal principal) {

        if (principal == null) return "redirect:/login";

        List<Card> cards = cardService.listAll(userService.getByUserEmail(principal.getName()).getId());

        double totalPrice = cards.stream()
                .mapToDouble(card -> card.getPlant().getPrice() * card.getQuantity())
                .sum();

        model.addAttribute("cards", cards);
        model.addAttribute("title", "Card");
        model.addAttribute("plant", new Plant());
        model.addAttribute("totalPrice", totalPrice);

        return "card";
    }


    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long plantId,
            Principal principal,
            RedirectAttributes attributes){

        if (principal == null) return "redirect:/login";

        Plant plant = plantService.get(plantId);
        User user = userService.getByUserEmail(principal.getName());
        cardService.addToCard(plant, user);

        attributes.addFlashAttribute("success", "Plant added successfully");
        return "redirect:/card";
    }

    @GetMapping(value = "/delete-cart/{id}")
    public String deleteItemFromCart(Principal principal, @PathVariable String id){
        if(principal == null){
            return "redirect:/login";
        }else{
            cardService.deleteCard(cardService.get(Long.parseLong(id)));
            return "redirect:/card";
        }
    }

    @PostMapping(value = "/update-cart")
    public String updateItemFromCart(@RequestParam("id") Long cardId,
                                     @RequestParam("quantity") int quantity,
                                     Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            cardService.updateCard(cardId, quantity);
            return "redirect:/card";
        }
    }
}
