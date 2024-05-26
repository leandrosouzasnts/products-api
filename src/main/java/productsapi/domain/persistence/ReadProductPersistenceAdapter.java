package productsapi.domain.persistence;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import productsapi.api.rest.adapters.persistence.ProductRepository;
import productsapi.domain.model.Product;
import productsapi.domain.persistence.entities.ProductEntity;

@Component
public class ReadProductPersistenceAdapter implements ReadProductPersistencePort {

    private final ProductRepository productRepository;

    public ReadProductPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product get(String id) {
        ProductEntity productEntity = productRepository.get(id);
        Product product = new Product();

        if (productEntity != null) {
            BeanUtils.copyProperties(productEntity, product);
            return product;
        } else {
            return null;
        }

    }
}
