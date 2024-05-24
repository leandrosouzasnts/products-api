package productsapi.api.rest.adapters.persistence;

import productsapi.domain.persistence.entities.ProductEntity;

public interface ProductRepository {
    ProductEntity get(Long productId);
    ProductEntity save(ProductEntity product);
}
