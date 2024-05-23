package productsapi.domain.persistence;

import org.springframework.beans.BeanUtils;
import productsapi.adapters.persistence.ProductRepository;
import productsapi.domain.model.Product;
import productsapi.domain.persistence.entities.ProductEntity;

public class ReadProductPersistenceAdapter implements ReadProductPersistencePort {

    private final ProductRepository productRepository;

    public ReadProductPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product get(Long id) {
        ProductEntity productEntity = productRepository.get(id);
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        return product;
    }
}
