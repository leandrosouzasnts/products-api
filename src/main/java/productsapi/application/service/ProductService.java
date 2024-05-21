package productsapi.application.service;

import productsapi.application.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductService {

    Product get(UUID productId);
    Product create(String name, BigDecimal price) throws Exception;
    Product enable(UUID productId);
    Product disable(UUID productId);
}
