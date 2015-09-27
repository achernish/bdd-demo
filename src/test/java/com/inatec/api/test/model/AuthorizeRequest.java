package com.inatec.api.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jbehave.core.annotations.AsParameters;

/**
 * @author Anatoly Chernysh
 */
@AsParameters
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeRequest {

    private String merchantid;

    private String amount;

    private String currency;

    private String orderid;

    private String language;

    private String gender;

    private String lastname;

    private String street;

    private String zip;

    private String city;

    private String country;

    private String firstname;

    private String company;

    private String email;

    private String customerip;

    private String payment_method;

    private String ccn;

    private String cvc_code;

    private String cardholder_name;

    private String exp_month;

    private String exp_year;

    private String custom1;

}