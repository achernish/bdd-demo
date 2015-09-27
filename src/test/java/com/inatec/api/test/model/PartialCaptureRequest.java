package com.inatec.api.test.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Anatoly Chernysh
 */
@Data
public class PartialCaptureRequest {

    private final String merchantid;

    private final String transactionid;

    private final BigDecimal amount;
}
