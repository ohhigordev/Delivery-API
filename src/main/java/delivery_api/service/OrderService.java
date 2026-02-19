package delivery_api.service;

import delivery_api.domain.enums.OrderStatus;
import delivery_api.models.Order;
import delivery_api.models.OrderItem;
import delivery_api.models.Product;
import delivery_api.repositories.OrderRepository;
import delivery_api.repositories.ProductRepository;
import delivery_api.web.dto.OrderRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest request){
        Order order = new Order();
        order.setMoment(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        // Transformando os DTO's de itens em entidades reais
        order.setItems(request.items().stream().map(itemRequest -> {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.quantity());
            item.setPrice(product.getPrice()); // Fixando o preço atual do produto
            item.setOrder(order); // Vinculando o item ao pedido (importante!)
            return item;
        }).collect(Collectors.toList()));

        // Calculando o total do pedido
        BigDecimal total = order.getItems().stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);

        return orderRepository.save(order);
    }

    @Transactional
    public Order updateStatus(Long id, OrderStatus newStatus) {
        return null;
    }

    public List<Order> findOrderByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> findAll (){
        return orderRepository.findAll();
    }

    public BigDecimal getRevenue(){
        BigDecimal total = orderRepository.calculateTotalRevenue();

        return total != null ? total : BigDecimal.ZERO;
    }
}
