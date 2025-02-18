package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    Product product;

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setup() {

    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde909c");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
    // Positive test for editing product
    @Test
    void testEditProduct_Positive() {
        // Edit product details
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Sampo Cap Bambang Baru");
        product.setProductQuantity(150);
        productRepository.update(product);

        // Assert the changes
        assertEquals("Sampo Cap Bambang Baru", product.getProductName());
        assertEquals(150, product.getProductQuantity());
    }

    // Negative test for editing product (invalid name)
    @Test
    void testEditProduct_Negative_InvalidName() {
        // Arrange
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product); // Simpan produk sebelum diuji

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setProductName("");
            productRepository.update(product); // Memanggil method update
        });

        assertEquals("Product name cannot be null or empty", exception.getMessage());}

    // Negative test for editing product (invalid quantity)
    @Test
    void testEditProduct_Negative_InvalidQuantity() {
        // Simulasikan pengaturan kuantitas produk yang tidak valid
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
        productRepository.create(product);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setProductQuantity(-10); // Kuantitas negatif
            productRepository.update(product);
        });

        // Verifikasi pesan kesalahan yang diharapkan
        assertEquals("Product quantity cannot be negative", exception.getMessage());
    }

    // Positive test for deleting product
    @Test
    void testDeleteProduct_Positive() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.deleteById(product.getProductId());

        // Assert that the product is deleted
        assertNull(productRepository.findById(product.getProductId()));
    }

    // Negative test for deleting product (product not found)
    @Test
    void testDeleteProduct_Negative_ProductNotFound() {
        Product product = productRepository.deleteById("aku suka kucing cerdas");
        assertNull(product);
    }

}
