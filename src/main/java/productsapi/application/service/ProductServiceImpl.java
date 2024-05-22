package productsapi.application.service;

import productsapi.application.model.Product;
import productsapi.application.model.enums.Status;

import java.math.BigDecimal;
import java.util.Random;

public class ProductServiceImpl implements ProductService {

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductPersistence productPersistence;

    public ProductServiceImpl(ProductPersistence productPersistence) {
        this.productPersistence = productPersistence;
    }

    @Override
    public Product get(Long productId) {
        return productPersistence.get(productId);
    }

    @Override
    public Product create(String name, BigDecimal price) throws Exception {
        Random random = new Random();
        Product product = new Product(random.nextLong() * -1,name, price, Status.ENABLED);
        return productPersistence.save(product);
    }

    @Override
    public Product enable(Long productId) {
        Product product = this.get(productId);

        product.enable();

        return productPersistence.save(product);
    }

    @Override
    public Product disable(Long productId) {
        Product product = this.get(productId);

        product.disable();

        return productPersistence.save(product);
    }
}
