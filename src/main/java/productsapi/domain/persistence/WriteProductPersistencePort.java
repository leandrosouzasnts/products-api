package productsapi.domain.persistence;

import productsapi.domain.model.Product;


public interface WriteProductPersistencePort {
    Product save(Product product);
}
