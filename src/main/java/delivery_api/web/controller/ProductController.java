package delivery_api.web.controller;

import delivery_api.models.Product;
import delivery_api.repositories.ProductRepository;
import delivery_api.web.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @PostMapping
    public ResponseEntity<Product> create (@RequestBody ProductRequest request){
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());

        return ResponseEntity.status(201).body(repository.save(product));
    }

    @GetMapping
    public List<Product> listAll(){
        return repository.findAll();
    }

}
