package delivery_api.web.controller;

import delivery_api.domain.enums.OrderStatus;
import delivery_api.models.Order;
import delivery_api.service.OrderService;
import delivery_api.web.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderRequest request){
        return ResponseEntity.status(201).body(service.createOrder(request));
    }

    // Endpoint para listar por status (Ex: /api/orders?status=PENDING)
    @GetMapping
    public List<Order> listByStatus(@RequestParam(required = false) OrderStatus status){
        if (status != null){
            return service.findOrderByStatus(status);
        }

        return service.findAll();
    }

    // Endpoint para atualizar o status (PATCH é ideal para atualizações parciais)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id,@RequestBody OrderStatus newStatus){
        Order updateOrder = service.updateStatus(id, newStatus);
        return ResponseEntity.ok(updateOrder);
    }

    @GetMapping("/revenue")
    public ResponseEntity<BigDecimal> getRevenue(){
        BigDecimal revenue = service.getRevenue();
        return ResponseEntity.ok(revenue);
    }

}
