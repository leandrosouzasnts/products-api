package productsapi.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import productsapi.application.model.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static productsapi.application.model.enums.Status.DISABLED;
import static productsapi.application.model.enums.Status.ENABLED;

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl productServiceMock ;

    @Mock
    ProductPersistence productPersistenceMock;

    Product productMock;

    @BeforeEach
    void setUp() throws Exception {
        productMock = new Product(1L, "Product 1", BigDecimal.valueOf(100), ENABLED);
    }

    @Test
    void givenValidProduct_whenGetByUUID_thenReturnsSuccessfully() {
        when(productPersistenceMock.get(anyLong())).thenReturn(productMock);

        Product productTest = productServiceMock.get(1L);

        assertEquals(productTest.getId(), productMock.getId());
    }

    @Test
    void givenValidProduct_whenCreated_thenReturnsSuccessfully() throws Exception {
        when(productPersistenceMock.save(any())).thenReturn(productMock);

        Product productTest = productServiceMock.create("Product 1", BigDecimal.valueOf(200));

        assertEquals(productTest.getId(), productMock.getId());
    }

    @Test
    void givenValidProduct_whenEnabled_thenReturnsSuccessfully() throws Exception {
        //Setup
        when(productPersistenceMock.save(any())).thenReturn(productMock);
        when(productServiceMock.get(1L)).thenReturn(productMock);

        //Act
        Product productTest = productServiceMock.enable(1L);

        //Verify
        assertNotNull(productTest);
        assertEquals(ENABLED, productTest.getStatus());
    }

    @Test
    void givenValidProduct_whenDisabled_thenReturnsSuccessfully() throws Exception {
        //Setup
        when(productPersistenceMock.save(any())).thenReturn(productMock);
        when(productServiceMock.get(1L)).thenReturn(productMock);

        //Act
        Product productTest = productServiceMock.disable(1L);

        //Verify
        assertNotNull(productTest);
        assertEquals(DISABLED, productTest.getStatus());
    }
}
