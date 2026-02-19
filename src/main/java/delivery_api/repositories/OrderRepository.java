package delivery_api.repositories;

import delivery_api.domain.enums.OrderStatus;
import delivery_api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Busca todos os pedidos de um status especifico (ex: todos PENDING)
    List<Order> findByStatus (OrderStatus status);

    // Soma o campo total de pedidos que têm o status 'DELIVERED'
    @Query("SELECT SUM(o.total) FROM Order o WHERE o.status = com.ohhigordev.deliveryapi.domain.enums.OrderStatus.DELIVERED")
    BigDecimal calculateTotalRevenue();

    // Busca pedidos criados após uma determinada data/hora
    List<Order> findByMomentAfter(LocalDateTime moment);

}
