package com.inatec.bankproxy.omnipay.model;

import org.junit.Test;

import java.text.ParseException;


/**
 * @author Anatoly Chernysh
 */
public class AuthorisationResponseTest {

    @Test
    public void authorisationRequestIsCorrect() throws ParseException {
        String givenRawResponse = "01330110723860010e1080001644444401234544430000000000000051901215144923161837144923091259583720600000793491516183778916500115         M978";

        AuthorisationResponse authorisationResponse = new AuthorisationResponse(givenRawResponse);
    }
}