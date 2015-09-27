package com.inatec.api.test.model;

import lombok.Data;
import org.jbehave.core.annotations.AsParameters;

/**
 * @author Anatoly Chernysh
 */
@Data
@AsParameters
public class AuthorizeRequest {

    private final String merchantid;

    private final String amount;

    private final String currency;

    private final String orderid;

    private final String language;

    private final String gender;

    private final String lastname;

    private final String street;

    private final String zip;

    private final String city;

    private final String country;

    private final String firstname;

    private final String company;

    private final String email;

    private final String customerip;

    private final String payment_method;

    private final String ccn;

    private final String cvc_code;

    private final String cardholder_name;

    private final String exp_month;

    private final String exp_year;

    private final String custom1;
}