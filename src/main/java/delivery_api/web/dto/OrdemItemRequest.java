package delivery_api.web.dto;

public record OrdemItemRequest(
        Long productId,
        Integer quantity
) {
}
