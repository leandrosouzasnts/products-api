package productsapi.infra.adapters.persistence;

import productsapi.infra.entities.ProductEntity;

public interface ProductRepository {
    ProductEntity get(Long productId);
    ProductEntity save(ProductEntity product);
}
