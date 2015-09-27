package com.inatec.api.test.model;

import lombok.Data;

/**
 * @author Anatoly Chernysh
 */
@Data
public class CaptureRequest {

    private final String merchantid;

    private final String transactionid;

    private final String language;
}
