package delivery_api.web.dto;

import java.util.List;

public record OrderRequest(
        List<OrdemItemRequest> items
) {}
