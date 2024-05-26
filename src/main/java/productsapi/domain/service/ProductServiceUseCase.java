package productsapi.domain.service;

import productsapi.domain.model.Product;

import java.math.BigDecimal;

public interface ProductServiceUseCase {

    Product get(String productId);
    Product create(String name, BigDecimal price) throws Exception;
    Product enable(String productId);
    Product disable(String productId);
}
