package com.inatec.api.test.model;

import lombok.Data;
import org.jbehave.core.annotations.AsParameters;
import org.jbehave.core.annotations.Parameter;

import java.math.BigDecimal;

/**
 * @author Anatoly Chernysh
 */
@AsParameters
@Data
public class TestRequest {

    private String string;

    private Integer integer;

    private BigDecimal bigDecimal;
}
