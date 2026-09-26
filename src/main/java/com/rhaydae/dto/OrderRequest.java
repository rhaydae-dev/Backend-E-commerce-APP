package com.rhaydae.dto;

import java.util.List;

public record OrderRequest(Long userId, List<Long> productId) {

}
