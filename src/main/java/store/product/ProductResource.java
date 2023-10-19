package store.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*")
@RestController
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<ProductOut> list() {
        return productService.list().stream().map(ProductParser::to).toList();
    }

    @GetMapping("/product/{id}")
    public ProductOut get(@PathVariable(required = true) String id) {
        Product found = productService.find(id);
        return found == null ? null : ProductParser.to(found);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable(required = true) String id) {
        productService.delete(id);
    }

    @PostMapping("/product")
    public ResponseEntity<Object> create(@RequestBody ProductIn in) {
        return ResponseEntity.created(
                    ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(productService.create(ProductParser.to(in)).id())
                        .toUri())
                    .build();
    }

}
