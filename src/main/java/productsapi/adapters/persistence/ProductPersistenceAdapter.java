package productsapi.adapters.persistence;

import org.springframework.stereotype.Component;
import productsapi.domain.persistence.entities.ProductEntity;

import java.util.Optional;

@Component
public class ProductPersistenceAdapter implements ProductRepository{
    private final JpaProductRepository jpaProductRepository;

    public ProductPersistenceAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public ProductEntity get(Long productId) {
        Optional<ProductEntity> productEntity = jpaProductRepository.findById(productId);
        if(productEntity.isPresent()) {
            return productEntity.get();
        }
        return null;
    }


    @Override
    public ProductEntity save(ProductEntity product) {
        return jpaProductRepository.save(product);
    }
}
