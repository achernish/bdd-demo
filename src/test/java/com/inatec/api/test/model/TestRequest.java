package com.inatec.api.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jbehave.core.annotations.AsParameters;

import java.math.BigDecimal;

/**
 * @author Anatoly Chernysh
 */
@AsParameters
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestRequest {

    private String string;

    private Integer integer;

    private BigDecimal bigDecimal;
}
