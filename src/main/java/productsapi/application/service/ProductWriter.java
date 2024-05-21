package productsapi.application.service;

import productsapi.application.model.Product;


public interface ProductWriter {
    Product save(Product product);
}
