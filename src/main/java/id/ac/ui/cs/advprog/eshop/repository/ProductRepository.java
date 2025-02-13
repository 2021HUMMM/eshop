package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.UUID;

@Repository
public class ProductRepository {
    // List to store product data in memory
    private List<Product> productData = new ArrayList<>();

    /**
     * Creates a new product and assigns a unique ID if not provided.
     * @param product The product to be added.
     * @return The created product with an assigned ID.
     */
    public Product create(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    /**
     * Retrieves all products as an iterator.
     * @return Iterator of products.
     */
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    /**
     * Finds a product by its unique ID.
     * @param productId The ID of the product to find.
     * @return The found product or null if not found.
     */
    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Updates an existing product if found.
     * @param updatedProduct The product with updated information.
     * @return The updated product or null if not found.
     */
    public Product update(Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(updatedProduct.getProductId())) {
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    /**
     * Deletes a product by its ID.
     * @param productId The ID of the product to be deleted.
     */
    public void deleteById(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}
