package productsapi.domain.persistence;

import org.springframework.beans.BeanUtils;
import productsapi.adapters.persistence.ProductRepository;
import productsapi.domain.model.Product;
import productsapi.domain.persistence.entities.ProductEntity;

public class WriteProductPersistenceAdapter implements WriteProductPersistencePort {
    private final ProductRepository productRepository;

    public WriteProductPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(product, productEntity);

        productRepository.save(productEntity);

        product.setId(productEntity.getId());

        return product;
    }
}
