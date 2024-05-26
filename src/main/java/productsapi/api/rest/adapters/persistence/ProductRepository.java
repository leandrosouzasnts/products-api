package productsapi.api.rest.adapters.persistence;

import productsapi.domain.persistence.entities.ProductEntity;

public interface ProductRepository {
    ProductEntity get(String productId);
    ProductEntity save(ProductEntity product);
}
