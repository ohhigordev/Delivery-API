package delivery_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Entity
@Table(name = "tb_order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private BigDecimal price; // Preço unitário no momento da compra

    @ManyToOne
    @JoinColumn(name = "order_id") // Nome da coluna estrangeira no postgres
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Método auxiliar para calcular o subtotal deste item
    public BigDecimal getSubTotal(){
        return price.multiply(new BigDecimal(quantity));
    }
}

