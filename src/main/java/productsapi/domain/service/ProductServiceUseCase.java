package productsapi.domain.service;

import productsapi.domain.model.Product;
import productsapi.domain.model.request.UpdateStatusEnabledProductTO;

import java.math.BigDecimal;

public interface ProductServiceUseCase {

    Product get(String productId);
    Product create(String name, BigDecimal price) throws Exception;
    Product enable(String productId, UpdateStatusEnabledProductTO to);
    Product disable(String productId);
}
