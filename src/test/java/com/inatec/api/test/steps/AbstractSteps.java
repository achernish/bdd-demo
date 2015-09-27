package com.inatec.api.test.steps;

import com.inatec.api.test.model.AuthorizeRequest;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.steps.Steps;

import java.math.BigDecimal;

/**
 * @author Anatoly Chernysh
 */
public abstract class AbstractSteps extends Steps {

    protected AuthorizeRequest authorizeRequest;

    @Given("a transaction [" +
            "merchantid=$merchantid, " +
            "amount=$amount, " +
            "currency=$currency, " +
            "orderid=$orderid, " +
            "language=$language, " +
            "gender=$gender, " +
            "firstname=$firstname, " +
            "lastname=$lastname, " +
            "street=$street, " +
            "zip=$zip, " +
            "city=$city, " +
            "country=$country, " +
            "company=$company, " +
            "email=$email, " +
            "customerip=$customerip, " +
            "payment_method=$payment_method, " +
            "ccn=$ccn, " +
            "cvc_code=$cvc_code, " +
            "cardholder_name=$cardholder_name, " +
            "exp_month=$exp_month, " +
            "exp_year=$exp_year]")
    public void givenTransaction(
            @Named("merchantid") String merchantid,
            @Named("amount") BigDecimal amount,
            @Named("currency") String currency,
            @Named("orderid") String orderid,
            @Named("language") String language,
            @Named("gender") String gender,
            @Named("firstname") String firstname,
            @Named("lastname") String lastname,
            @Named("street") String street,
            @Named("zip") String zip,
            @Named("city") String city,
            @Named("country") String country,
            @Named("company") String company,
            @Named("email") String email,
            @Named("customerip") String customerip,
            @Named("payment_method") String payment_method,
            @Named("ccn") String ccn,
            @Named("cvc_code") String cvc_code,
            @Named("cardholder_name") String cardholder_name,
            @Named("exp_month") String exp_month,
            @Named("exp_year") String exp_year
    ) {
        this.authorizeRequest = new AuthorizeRequest(
                merchantid,
                amount.toString(),
                currency,
                orderid,
                language,
                gender,
                lastname,
                street,
                zip,
                city,
                country,
                firstname,
                company,
                email,
                customerip,
                payment_method,
                ccn,
                cvc_code,
                cardholder_name,
                exp_month,
                exp_year,
                "123456"
        );
    }
}
