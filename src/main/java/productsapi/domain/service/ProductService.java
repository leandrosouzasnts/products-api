package productsapi.domain.service;

import org.springframework.stereotype.Service;
import productsapi.domain.model.Product;
import productsapi.domain.model.enums.Status;
import productsapi.domain.model.request.UpdateStatusEnabledProductTO;
import productsapi.domain.persistence.ReadProductPersistencePort;
import productsapi.domain.persistence.WriteProductPersistencePort;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

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
    public Product get(String productId) {
        return readProductPersistencePort.get(productId);
    }

    @Override
    public Product create(String name, BigDecimal price) throws Exception {
        Product product = new Product(UUID.randomUUID().toString(), name, price, Status.ENABLED);
        return writeProductPersistencePort.save(product);
    }

    @Override
    public Product enable(String productId, UpdateStatusEnabledProductTO to) {
        Product product = this.get(productId);

        if (product != null) {
            product.setPrice(to.price());
            product.enable();
            return writeProductPersistencePort.save(product);
        } else
            return null;
    }

    @Override
    public Product disable(String productId) {
        Product product = this.get(productId);

        product.disable();

        return writeProductPersistencePort.save(product);
    }
}
