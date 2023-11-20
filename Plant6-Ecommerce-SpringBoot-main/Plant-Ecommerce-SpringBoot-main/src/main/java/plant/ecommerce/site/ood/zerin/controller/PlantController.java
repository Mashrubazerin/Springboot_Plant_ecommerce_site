package plant.ecommerce.site.ood.zerin.controller;

import plant.ecommerce.site.ood.zerin.model.Plant;
import plant.ecommerce.site.ood.zerin.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class PlantController {
    @Autowired
    private PlantService plantService;

    @PostMapping("/plant/new")
    public String plantSave(@ModelAttribute("plant") Plant plant,
                              RedirectAttributes attributes) {
        if (plant.getName() == null || plant.getPrice() == 0 || plant.getImage() == null || plant.getCategory() == null) {
            attributes.addFlashAttribute("error", "Value cannot be null");
            return "redirect:/";
        }

        plantService.save(plant);
        attributes.addFlashAttribute("success", "Plant Successfully Added");
        return "redirect:/";
    }

    @GetMapping("/plant/edit/{id}")
    public String plantEdit(@PathVariable String id,
                              Model model, Principal principal) {

        if (principal == null) return "redirect:/login";

        Plant plant = plantService.get(Long.parseLong(id));
        model.addAttribute("plantEdit", plant);
        model.addAttribute("plant", new Plant());

        return "edit_plant";
    }

    @PostMapping("/plant/edit/save")
    public String plantEditSave(@ModelAttribute("plantEdit") Plant plantEdit,
                                  Principal principal, RedirectAttributes attributes) {
        if (principal == null) return "redirect:/login";

        Plant plant = plantService.get(plantEdit.getId());

        plant.setCategory(plantEdit.getCategory());
        plant.setName(plantEdit.getName());
        plant.setImage(plantEdit.getImage());
        plant.setDescription(plantEdit.getDescription());
        plant.setPrice(plantEdit.getPrice());

        plantService.save(plant);
        attributes.addFlashAttribute("success", "Plant edit successful");
        return "redirect:/";
    }


    @GetMapping("/plant/delete/{id}")
    public String plantEdit(@PathVariable String id,
                              Model model, Principal principal,
                              RedirectAttributes attributes) {

        if (principal == null) return "redirect:/login";
        plantService.delete(Long.parseLong(id));
        attributes.addFlashAttribute("success", "Plant delete successful");
        return "redirect:/";
    }
}
