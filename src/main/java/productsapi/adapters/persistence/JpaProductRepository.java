package productsapi.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productsapi.domain.persistence.entities.ProductEntity;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
