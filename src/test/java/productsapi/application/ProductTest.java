package productsapi.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTest {

    @Test
    public void testEnableProductWithPositivePrice() {
        Product product = new Product("1", "Product", new BigDecimal("10.00"), Status.DISABLED);

        product.enable();

        assertEquals(Status.ENABLED, product.getStatus());
    }

    @Test
    public void testEnableProductWithZeroPrice() {
        Product product = new Product("1", "Product", BigDecimal.ZERO, Status.DISABLED);

        product.enable();

        assertEquals(Status.DISABLED, product.getStatus());
    }

    @Test
    public void testDisableProduct() {
        Product product = new Product("1", "Product", new BigDecimal("10.00"), Status.ENABLED);

        product.disable();

        assertEquals(Status.DISABLED, product.getStatus());
    }

    @Test
    public void testIsValidWhenProductIsEnabled() {
        Product product = new Product("1", "Product", new BigDecimal("10.00"), Status.ENABLED);

        assertTrue(product.isValid());
    }

    @Test
    public void testIsValidWhenProductIsDisabled() {
        Product product = new Product("1", "Product", new BigDecimal("10.00"), Status.DISABLED);

        assertFalse(product.isValid());
    }

}
