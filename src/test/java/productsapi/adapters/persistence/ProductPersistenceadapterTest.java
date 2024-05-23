package productsapi.adapters.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import productsapi.domain.model.enums.Status;
import productsapi.domain.persistence.entities.ProductEntity;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
class ProductPersistenceadapterTest {

    @InjectMocks
    private ProductPersistenceAdapter productPersistenceAdapter;

    @Mock
    private JpaProductRepository productRepository;

    ProductEntity productMock;

    @BeforeEach
    void setUp(){
        productMock = new ProductEntity();
        productMock.setId(1L);
        productMock.setName("Product test");
        productMock.setPrice(BigDecimal.valueOf(100));
        productMock.setStatus(Status.ENABLED);
    }

    @Test
    void givenValidProduct_whenSave_thenReturnByIdSuccess(){
        //Arrange
        when(productRepository.save(productMock)).thenReturn(productMock);
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(productMock));

        //Act
        ProductEntity productSave = productPersistenceAdapter.save(productMock);

        //Assert
        assertNotNull(productSave);
        assertEquals(productMock.getId(), productPersistenceAdapter.get(productSave.getId()).getId());
    }

    @Test
    void givenValidProduct_whenGet_thenReturnNull(){
        //Arrange
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        //Act
        ProductEntity productSave = productPersistenceAdapter.get(productMock.getId());

        //Assert
        assertNull(productSave);
    }
}
