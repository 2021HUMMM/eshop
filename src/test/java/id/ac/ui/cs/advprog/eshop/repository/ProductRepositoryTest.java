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
    void testCreateProductWithoutID() {
        Product product = new Product();
        product.setProductName("testing");
        product.setProductQuantity(500);
        productRepository.create(product);
        assertNotNull(product.getProductId());
    }
    @Test
    void testCreateProductWithID() {
        Product product = new Product();
        product.setProductName("testing");
        product.setProductQuantity(500);
        productRepository.create(product);
        assertNotNull(product.getProductId());
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
    void testEditProductPositive() {
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
    @Test
    void testEditProductPositiveId() {
        // Edit product details
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductId("testing aja");
        productRepository.update(product);

        // Assert the changes
        assertEquals("testing aja", product.getProductId());
    }
    @Test
    void testEditProductPositiveName() {
        // Edit product details
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Sampo Cap Bambang Baru");
        productRepository.update(product);

        // Assert the changes
        assertEquals("Sampo Cap Bambang Baru", product.getProductName());
    }

    // Negative test for editing product (invalid name)
    @Test
    void testEditProductNegativeEmptyName() { // maaf kak minggu lalu saya kira negative test itu ngebuat build failed, ternyata seharusnya tetap success
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

        assertEquals("Product name cannot be null or empty", exception.getMessage());
    }
    @Test
    void testEditProductNegativeNullName() { // maaf kak minggu lalu saya kira negative test itu ngebuat build failed, ternyata seharusnya tetap success
        // Arrange
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product); // Simpan produk sebelum diuji

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setProductName(null);
            productRepository.update(product); // Memanggil method update
        });

        assertEquals("Product name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testEditProductNegativeProductIdEmpty() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setProductId("");
            productRepository.update(product);
        });

        // Verifikasi pesan kesalahan yang diharapkan
        assertEquals("Product ID cannot be null or empty", exception.getMessage());

    }
    @Test
    void testEditProductNegativeProductIdNull() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setProductId(null);
            productRepository.update(product);
        });

        // Verifikasi pesan kesalahan yang diharapkan
        assertEquals("Product ID cannot be null or empty", exception.getMessage());

    }
    // Negative test for editing product (invalid quantity)
    @Test
    void testEditProductNegativeInvalidQuantity() {
        // Simulasikan pengaturan kuantitas produk yang tidak valid
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setProductQuantity(-10); // Kuantitas negatif
            productRepository.update(product);
        });

        // Verifikasi pesan kesalahan yang diharapkan
        assertEquals("Product quantity cannot be negative", exception.getMessage());
    }
    @Test
    void testEditProductNegativeNullProduct(){

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.update(null);
        });
        assertEquals("Updated product cannot be null", exception.getMessage());

    }
    @Test
    void testEditProductNegativeProductExistsButIdMismatch(){
        Product existingProduct = new Product();
        existingProduct.setProductId("random-id-123");
        existingProduct.setProductName("Sabun Wangi");
        existingProduct.setProductQuantity(50);
        productRepository.create(existingProduct); // Tambahkan produk ke dalam repository

        Product product = new Product();
        product.setProductId("non-existent-id-999"); // ID ini tidak ada
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        assertNull(productRepository.update(product)); // Loop tetap jalan, tapi return null
    }

    @Test
    void testEditProductNegativeProductNotFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        assertNull(productRepository.update(product));
    }
    @Test
    void testEditProductPositiveProductFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        product.setProductName("Sampo Cap Bambang Baru");
        assertNotNull(productRepository.update(product));
    }

    // Positive test for deleting product
    @Test
    void testDeleteProductPositive() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.deleteById(product.getProductId());

        // Assert that the product is deleted
        assertNull(productRepository.findById(product.getProductId()));
    }
    @Test
    void testDeleteProductPositiveReturnProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newproduct = productRepository.deleteById(product.getProductId());
        assertNotNull(newproduct);
    }


    // Negative test for deleting product (product not found)
    @Test
    void testDeleteProductNegativeIdNotFound() {
        Product product = productRepository.deleteById("aku suka kucing cerdas");
        assertNull(product);
    }
    // Negative test for deleting product (product not found)
    @Test
    void testDeleteProductNegativeNullId() {
        Product product = productRepository.deleteById(null);
        assertNull(product);
    }
    @Test
    void testDeleteProductNegativeIdNotFound2() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newproduct = productRepository.deleteById("testing aja");
        assertNull(newproduct);
    }

    @Test
    void testFindById(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product = productRepository.findById(product.getProductId());
        assertNotNull(product);
    }
    @Test
    void testFindByIdNegativeRandomId(){
        Product product = productRepository.findById("aku suka kucing cerdas");
        assertNull(product);
    }
    @Test
    void testFindByIdNegativeNotEmptyList(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newproduct = productRepository.findById("aku suka kucing cerdas");
        assertNull(newproduct);
    }


}
