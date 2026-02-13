package delivery_api.models;

import delivery_api.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime moment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total;

    // Relacionamento: Um pedido para muitos itens
    // mappedBy: indica qual o campo na classe OrdemItem é o "dono" do relacionamento
    // cascade: se eu salva o Pedido, ele salva automaticamente os itens dentro dele
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
}
