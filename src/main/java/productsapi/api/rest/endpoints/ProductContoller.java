package productsapi.api.rest.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productsapi.domain.model.Product;
import productsapi.domain.model.request.ProductRequest;
import productsapi.domain.model.request.UpdateStatusEnabledProductTO;
import productsapi.domain.service.ProductServiceUseCase;

@RestController
@RequestMapping("/products")
public class ProductContoller {

    private final ProductServiceUseCase productServiceUseCase;

    public ProductContoller(ProductServiceUseCase productServiceUseCase) {
        this.productServiceUseCase = productServiceUseCase;
    }

    @Operation(description = "Add new product in base")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added")
    })
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest productRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(productServiceUseCase.create(productRequest.name(), productRequest.price()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findByProductId(@PathVariable String productId) {
        Product product = productServiceUseCase.get(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{productId}/disabled")
    public ResponseEntity<Product> disabled(@PathVariable String productId) {
       Product product = productServiceUseCase.get(productId);
        if (product != null) {
            return ResponseEntity.ok(productServiceUseCase.disable(productId));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{productId}/enabled")
    public ResponseEntity<Product> enabled(@PathVariable String productId, @RequestBody UpdateStatusEnabledProductTO to) {
        Product product = productServiceUseCase.get(productId);
        if (product != null) {
            return ResponseEntity.ok(productServiceUseCase.enable(productId, to));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
