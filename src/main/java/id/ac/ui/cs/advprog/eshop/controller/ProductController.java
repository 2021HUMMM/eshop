package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    /**
     * Displays the product creation form.
     * @param model The model to store product data.
     * @return The name of the HTML template for creating a product.
     */
    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    /**
     * Handles form submission for creating a new product.
     * @param product The product data submitted from the form.
     * @return Redirects to the product list page.
     */
    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    /**
     * Displays the list of all available products.
     * @param model The model to store product list data.
     * @return The name of the HTML template for displaying the product list.
     */
    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    /**
     * Displays the product editing form for a given product ID.
     * @param productId The ID of the product to be edited.
     * @param model The model to store the product data.
     * @return The name of the HTML template for editing a product.
     */
    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        model.addAttribute("product", product);
        return "editProduct";
    }

    /**
     * Handles form submission for updating a product.
     * @param product The updated product data submitted from the form.
     * @return Redirects to the product list page.
     */
    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product, Model model) {
        service.update(product);
        return "redirect:list";
    }

    /**
     * Deletes a product by its ID and redirects to the product list.
     * @param productId The ID of the product to be deleted.
     * @return Redirects to the product list page.
     */
    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        service.delete(productId);
        return "redirect:/product/list";
    }
}
