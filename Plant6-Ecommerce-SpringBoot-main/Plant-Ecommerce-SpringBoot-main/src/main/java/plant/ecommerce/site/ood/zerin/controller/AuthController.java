package plant.ecommerce.site.ood.zerin.controller;

import plant.ecommerce.site.ood.zerin.model.User;
import plant.ecommerce.site.ood.zerin.repository.UserRepository;
import plant.ecommerce.site.ood.zerin.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private UserDetailsServiceImplement userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Principal principal, Model model) {
        if (principal != null) return "redirect:/";
        model.addAttribute("title", "CE Login");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "CE - Registration");
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("user")User user,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes attributes) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("user", user);
                return "register";
            }

            User userNew = userDetailsService.getByUserEmail(user.getUsername());

            if (userNew != null) {
                model.addAttribute("error", "Email is already registered");
                model.addAttribute("user", user);
                return "register";
            }

            if (user.getName().trim() == null) {
                model.addAttribute("error", "Name can not be empty");
                model.addAttribute("user", user);
                return "register";
            }

            if (user.getPassword().length() >= 5 && user.getPassword().length() <= 20) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                attributes.addFlashAttribute("success", user.getName() + " is successfully registered.");

                return "redirect:/login";
            } else {
                model.addAttribute("error", "Password should have 5-20 characters");
                model.addAttribute("user", user);
            }
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "Server have ran some problems");
            model.addAttribute("user", user);
        }
        return "register";
    }
}
