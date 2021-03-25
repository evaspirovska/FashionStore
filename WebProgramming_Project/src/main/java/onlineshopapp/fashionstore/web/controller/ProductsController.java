package onlineshopapp.fashionstore.web.controller;

import onlineshopapp.fashionstore.model.Clothes;
import onlineshopapp.fashionstore.service.ClothesService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ClothesService clothesService;

    public ProductsController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }


  /*  @GetMapping
    public String showProducts(@RequestParam(required = false) String nameSearch,
                               @RequestParam(required = false) String error, Model model) {
        if (nameSearch == null) {
            model.addAttribute("products", this.clothesService.listAllClothes());
        } else {
            model.addAttribute("products", this.clothesService.listProductsByName(nameSearch));
        }
        if(error != null)
            model.addAttribute("error", error);
        return "products";
    } */

    @GetMapping
    public String showProducts(Model model) {
         return listByPage(null, null, 1, model, "name", "asc");
    }

    @GetMapping("page/{pageNumber}")
    public String listByPage(@RequestParam(required = false) String nameSearch,
                             @RequestParam(required = false) String error,
                             @PathVariable("pageNumber") int currentPage, Model model,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir){
        if (nameSearch == null) {
            Page<Clothes> page = this.clothesService.findAll(currentPage, sortField, sortDir);

            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();

            List<Clothes> listProducts = page.getContent();

            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("products", listProducts);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
        } else {
            model.addAttribute("products", this.clothesService.listProductsByName(nameSearch));
        }
        if(error != null)
            model.addAttribute("error", error);
        return "products";

    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable Long id, @org.jetbrains.annotations.NotNull Model model) {
        model.addAttribute("product", this.clothesService.findById(id));
        model.addAttribute("produkti", this.clothesService.listAll());
        return "product-details";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/add")
    public String showAdd() {
        return "form";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("product", this.clothesService.findById(id));
        return "form";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public String create(@RequestParam String name, @RequestParam String description, @RequestParam String image, @RequestParam String image1, @RequestParam String image2,
                         @RequestParam String image3, @RequestParam Double price, @RequestParam Double grade, @RequestParam Integer quantitySizeS,
                         @RequestParam Integer quantitySizeM, @RequestParam Integer quantitySizeL, @RequestParam Integer quantitySizeXL) {
        this.clothesService.create(name, description, image, image1, image2, image3, price, grade, quantitySizeS, quantitySizeM, quantitySizeL, quantitySizeXL);
        return "redirect:/products";
    }
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @RequestParam String description, @RequestParam String image, @RequestParam String image1, @RequestParam String image2,
                         @RequestParam String image3, @RequestParam Double price, @RequestParam Double grade, @RequestParam Integer quantitySizeS,
                         @RequestParam Integer quantitySizeM, @RequestParam Integer quantitySizeL, @RequestParam Integer quantitySizeXL) {
        this.clothesService.update(id, name, description, image, image1, image2, image3, price, grade, quantitySizeS, quantitySizeM, quantitySizeL, quantitySizeXL);
        return "redirect:/products";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.clothesService.delete(id);
        return "redirect:/products";
    }
}
