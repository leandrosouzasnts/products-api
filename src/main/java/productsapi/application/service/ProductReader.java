package productsapi.application.service;

import productsapi.application.model.Product;

import java.util.UUID;


public interface ProductReader {

    Product get(UUID id);
}
