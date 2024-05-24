package productsapi.domain.service;

import org.springframework.stereotype.Service;
import productsapi.domain.model.Product;
import productsapi.domain.model.enums.Status;
import productsapi.domain.persistence.ReadProductPersistencePort;
import productsapi.domain.persistence.WriteProductPersistencePort;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class ProductService implements ProductServiceUseCase {

    private final WriteProductPersistencePort writeProductPersistencePort;
    private final ReadProductPersistencePort readProductPersistencePort;

    private Random random = new Random();

    public ProductService(WriteProductPersistencePort writeProductPersistencePort, ReadProductPersistencePort readProductPersistencePort) {
        this.writeProductPersistencePort = writeProductPersistencePort;
        this.readProductPersistencePort = readProductPersistencePort;
    }

    @Override
    public Product get(Long productId) {
        return readProductPersistencePort.get(productId);
    }

    @Override
    public Product create(String name, BigDecimal price) throws Exception {
        Long id = random.nextLong() * -1;
        Product product = new Product(id, name, price, Status.ENABLED);
        return writeProductPersistencePort.save(product);
    }

    @Override
    public Product enable(Long productId) {
        Product product = this.get(productId);

        product.enable();

        return writeProductPersistencePort.save(product);
    }

    @Override
    public Product disable(Long productId) {
        Product product = this.get(productId);

        product.disable();

        return writeProductPersistencePort.save(product);
    }
}
