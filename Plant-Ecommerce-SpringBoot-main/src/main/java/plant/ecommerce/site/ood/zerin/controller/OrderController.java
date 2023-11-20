package plant.ecommerce.site.ood.zerin.controller;

import plant.ecommerce.site.ood.zerin.model.Card;
import plant.ecommerce.site.ood.zerin.model.Orders;
import plant.ecommerce.site.ood.zerin.model.Plant;
import plant.ecommerce.site.ood.zerin.service.CardService;
import plant.ecommerce.site.ood.zerin.service.OrderDetailsService;
import plant.ecommerce.site.ood.zerin.service.OrderService;
import plant.ecommerce.site.ood.zerin.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserDetailsServiceImplement userService;

    @Autowired
    private CardService cardService;

    @Autowired
    private OrderDetailsService orderDetailsService;


    @GetMapping("/checkout")
    private String checkout(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        model.addAttribute("plant", new Plant());
        return "checkout";
    }

    @PostMapping("/place-order")
    private String placeOrder(Principal principal,
                              @RequestParam("address") String address,
                              RedirectAttributes attributes) {

        if (principal == null) return "redirect:/login";

        Orders order = new Orders();
        order.setAddress(address);
        order.setUser(userService.getByUserEmail(principal.getName()));
        order.setDate(new Date());
        order.setPayment(true);
        order.setPaymentType("COD");

        List<Card> cards = cardService.listAll(userService.getByUserEmail(principal.getName()).getId());

        double totalPrice = cards.stream()
                .mapToDouble(card -> card.getPlant().getPrice() * card.getQuantity())
                .sum();

        order.setTotalPrice(totalPrice);
        orderService.save(order);
        orderDetailsService.saveBulk(cards, order);
        attributes.addFlashAttribute("success", "Order Placed Successfully");

        return "redirect:/";
    }
}
