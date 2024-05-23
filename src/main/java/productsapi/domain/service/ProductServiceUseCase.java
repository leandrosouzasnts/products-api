package productsapi.domain.service;

import productsapi.domain.model.Product;

import java.math.BigDecimal;

public interface ProductServiceUseCase {

    Product get(Long productId);
    Product create(String name, BigDecimal price) throws Exception;
    Product enable(Long productId);
    Product disable(Long productId);
}
