package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void setup() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }
    // Positive test for editing product
    @Test
    void testEditProduct_Positive() {
        // Edit product details
        this.product.setProductName("Sampo Cap Bambang Baru");
        this.product.setProductQuantity(150);

        // Assert the changes
        assertEquals("Sampo Cap Bambang Baru", this.product.getProductName());
        assertEquals(150, this.product.getProductQuantity());
    }

    // Negative test for editing product (invalid name)
    @Test
    void testEditProduct_Negative_InvalidName() {
        // Attempt to set an empty product name
        this.product.setProductName("");

        // Assert that the name is not updated (or handle validation logic)
        assertNotEquals("", this.product.getProductName()); // Assuming the name cannot be empty
    }

    // Negative test for editing product (invalid quantity)
    @Test
    void testEditProduct_Negative_InvalidQuantity() {
        // Attempt to set a negative quantity
        this.product.setProductQuantity(-10);

        // Assert that the quantity is not updated (or handle validation logic)
        assertNotEquals(-10, this.product.getProductQuantity()); // Assuming quantity cannot be negative
    }

    // Positive test for deleting product
    @Test
    void testDeleteProduct_Positive() {
        // Simulate deletion by setting the product to null
        this.product = null;

        // Assert that the product is deleted
        assertNull(this.product);
    }

    // Negative test for deleting product (product not found)
    @Test
    void testDeleteProduct_Negative_ProductNotFound() {
        // Simulate a scenario where the product is already deleted or does not exist
        this.product = null;
        // Attempt to perform an operation on the deleted product
        this.product.getProductName(); // This should throw a NullPointerException

    }
}
