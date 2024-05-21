package productsapi.application.service;

import productsapi.application.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductPersistence productPersistence;

    public ProductServiceImpl(ProductPersistence productPersistence) {
        this.productPersistence = productPersistence;
    }

    @Override
    public Product get(UUID productId) {
        //logger.info("product persistence get --step:start");

        Product product = productPersistence.get(productId);

        //logger.info("product persistence get --step:end");

        return product;
    }

    @Override
    public Product create(String name, BigDecimal price) throws Exception {
        Product product = new Product(name, price);

        return productPersistence.save(product);
    }

    @Override
    public Product enable(UUID productId) {
        Product product = this.get(productId);

        product.enable();

        return productPersistence.save(product);
    }

    @Override
    public Product disable(UUID productId) {
        Product product = this.get(productId);

        product.disable();

        return productPersistence.save(product);
    }
}
