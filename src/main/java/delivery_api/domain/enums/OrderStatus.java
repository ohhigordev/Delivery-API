package delivery_api.domain.enums;

public enum OrderStatus {
    PENDING,  //Aguardando confirmação do restourante
    PREPARING, // Na cozinha
    OUT_FOR_DELIVERY, // Com o entregador
    DELIVERED, // Entregue ao cliente
    CANCELED // Cancelado por algum motivo
}
