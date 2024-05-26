package productsapi.domain.persistence;

import productsapi.domain.model.Product;


public interface ReadProductPersistencePort {

    Product get(String id);
}
