package productsapi.application.service;

import productsapi.application.model.Product;

import java.math.BigDecimal;

public interface ProductService {

    Product get(Long productId);
    Product create(String name, BigDecimal price) throws Exception;
    Product enable(Long productId);
    Product disable(Long productId);
}
