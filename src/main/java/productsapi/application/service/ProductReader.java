package productsapi.application.service;

import productsapi.application.model.Product;


public interface ProductReader {

    Product get(Long id);
}
