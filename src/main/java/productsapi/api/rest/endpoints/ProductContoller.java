package productsapi.api.rest.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productsapi.domain.model.Product;
import productsapi.domain.model.request.ProductRequest;
import productsapi.domain.service.ProductServiceUseCase;

@RestController
@RequestMapping("/products")
public class ProductContoller {

    private final ProductServiceUseCase productServiceUseCase;

    public ProductContoller(ProductServiceUseCase productServiceUseCase) {
        this.productServiceUseCase = productServiceUseCase;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest productRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(productServiceUseCase.create(productRequest.name(), productRequest.price()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findByProductId(@PathVariable Long productId) {
        // ToDo Corrigir expcetion find by id e retornar record do respose personalizado
        return ResponseEntity.ok(productServiceUseCase.get(productId));
    }
}
