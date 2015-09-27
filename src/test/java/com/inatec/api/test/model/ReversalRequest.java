package com.inatec.api.test.model;

import lombok.Data;

/**
 * @author Anatoly Chernysh
 */
@Data
public class ReversalRequest {

    private final String merchantid;

    private final String transactionid;

    private final String language;
}
