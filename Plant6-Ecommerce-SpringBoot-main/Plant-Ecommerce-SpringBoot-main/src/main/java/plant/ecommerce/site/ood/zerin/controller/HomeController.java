package plant.ecommerce.site.ood.zerin.controller;

import plant.ecommerce.site.ood.zerin.model.Plant;
import plant.ecommerce.site.ood.zerin.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        if (principal == null)
            return "redirect:/login";

        model.addAttribute("title", "Plant Ecommerce");
        model.addAttribute("plant", new Plant());
        model.addAttribute("plants", plantService.getAll());
        return "index";
    }
}
