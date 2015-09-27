package com.inatec.api.test.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Anatoly Chernysh
 */
@Data
public class RefundRequest {

    private final String merchantid;

    private final String transactionid;

    private final BigDecimal price;

    private final String currency;

    private final String language;
}
