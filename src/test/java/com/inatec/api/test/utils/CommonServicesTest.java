package com.inatec.api.test.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class CommonServicesTest {

    @Test
    public void thatCalculateSignatureIsCorrect() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("merchantid", "api_test");
        params.put("amount", "1.23");
        params.put("currency", "EUR");
        params.put("orderid", "1234-123456789-4321");
        params.put("language", "de");
        params.put("gender", "");
        params.put("lastname", "Mustermann");
        params.put("street", "Hanauer Landstrasse");
        params.put("zip", "60322");
        params.put("city", "Frankfurt");
        params.put("country", "DEU");
        params.put("firstname", "Max");
        params.put("company", "Inatec");
        params.put("email", "aneu@inatec.com");
        params.put("customerip", "127.1.1.1");
        params.put("payment_method", "1");
        params.put("ccn", "4242424242424242");
        params.put("cvc_code", "123");
        params.put("cardholder_name", "Max Mustermann");
        params.put("exp_month", "01");
        params.put("exp_year", "2015");

        assertThat(CommonServices.calculateSignature("VeryGoodSecret", params), is("0172cb6ae420db4ec74349c65e1c3eb40f29895c"));
    }

}
