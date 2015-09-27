package com.inatec.api.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Anatoly Chernysh
 */
@Data
@NoArgsConstructor
public class Response {

    private String transactionid;

    private String transid;

    private Integer status;

    private String errormessage;

    private String errmsg;

    private BigDecimal amount;

    private BigDecimal price;

    private String currency;

    private String orderid;

    private
    String user_id;
}
