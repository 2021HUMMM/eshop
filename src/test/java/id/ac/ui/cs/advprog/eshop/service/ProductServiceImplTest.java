package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");

        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("123", createdProduct.getProductId());
        assertEquals("Test Product", createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Product 1");
        Product product2 = new Product();
        product2.setProductId("456");
        product2.setProductName("Product 2");
        productList.add(product1);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> allProducts = productService.findAll();

        assertNotNull(allProducts);
        assertEquals(2, allProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");

        when(productRepository.findById("123")).thenReturn(product);

        Product foundProduct = productService.findById("123");

        assertNotNull(foundProduct);
        assertEquals("123", foundProduct.getProductId());
        assertEquals("Test Product", foundProduct.getProductName());
        verify(productRepository, times(1)).findById("123");
    }

    @Test
    void testFindProductByIdNotFound() {
        when(productRepository.findById("999")).thenReturn(null);

        Product foundProduct = productService.findById("999");

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("999");
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Updated Product");

        when(productRepository.update(product)).thenReturn(product);

        Product updatedProduct = productService.update(product);

        assertNotNull(updatedProduct);
        assertEquals("123", updatedProduct.getProductId());
        assertEquals("Updated Product", updatedProduct.getProductName());
        verify(productRepository, times(1)).update(product);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");

        when(productRepository.deleteById("123")).thenReturn(product);

        productService.delete("123");

        verify(productRepository, times(1)).deleteById("123");
    }
}