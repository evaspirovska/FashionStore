package onlineshopapp.fashionstore.web.controller;

import org.springframework.stereotype.Controller;

@Controller
public class ClothesController {
<<<<<<< Updated upstream
=======

    private final ClothesService clothesService;

    public ClothesController(ClothesService clothesService) {

        this.clothesService = clothesService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Clothes> products = this.clothesService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "products";
    }


>>>>>>> Stashed changes
}
