package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private Model model;

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController productController;

    private Product createSampleProduct(String id, String name, int quantity) {
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(quantity);
        return product;
    }

    @Test
    void testCreateProductPage() {
        // Act
        String viewName = productController.createProductPage(model);

        // Assert
        assertEquals("createProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        // Arrange
        Product product = createSampleProduct("1", "Laptop", 10);

        // Act
        String result = productController.createProductPost(product, model);

        // Assert
        assertEquals("redirect:list", result);
        verify(service).create(product);
    }

    @Test
    void testProductListPage() {
        // Arrange
        List<Product> productList = Arrays.asList(
                createSampleProduct("1", "Laptop", 10),
                createSampleProduct("2", "Phone", 5)
        );
        when(service.findAll()).thenReturn(productList);

        // Act
        String result = productController.productListPage(model);

        // Assert
        assertEquals("productList", result);
        verify(model).addAttribute("products", productList);
        verify(service).findAll();
    }

    @Test
    void testEditProductPage() {
        // Arrange
        String productId = "1";
        Product product = createSampleProduct(productId, "Laptop", 10);
        when(service.findById(productId)).thenReturn(product);

        // Act
        String result = productController.editProductPage(productId, model);

        // Assert
        assertEquals("editProduct", result);
        verify(model).addAttribute("product", product);
        verify(service).findById(productId);
    }

    @Test
    void testEditProductPost() {
        // Arrange
        Product product = createSampleProduct("1", "Updated Laptop", 15);

        // Act
        String result = productController.editProductPost(product, model);

        // Assert
        assertEquals("redirect:list", result);
        verify(service).update(product);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        String productId = "1";

        // Act
        String result = productController.deleteProduct(productId);

        // Assert
        assertEquals("redirect:/product/list", result);
        verify(service).delete(productId);
    }
}