package com.inatec.bankproxy.omnipay.model;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class AuthorisationResponseTest {

    @Test
    public void authorisationRequestIsCorrect() throws ParseException {
        String givenRawResponse = "01330110723860010e1080001644444401234544430000000000000051901215144923161837144923091259583720600000793491516183778916500115         M978";

        AuthorisationResponse authorisationResponse = new AuthorisationResponse(givenRawResponse);
        assertThat("723860010e108000", is(authorisationResponse.getPrimaryBitmap()));
        assertThat("4444440123454443", is(authorisationResponse.getPrimaryAccountNumber()));
        assertThat("000000", is(authorisationResponse.getProcessingCode()));
        assertThat(5190, is(authorisationResponse.getTransactionAmount()));

        Calendar transmissionDataTime = Calendar.getInstance();
        transmissionDataTime.setTime(authorisationResponse.getTransmissionDateTime());
        assertThat(11, is(transmissionDataTime.get(Calendar.MONTH)));
        assertThat(15, is(transmissionDataTime.get(Calendar.DAY_OF_MONTH)));
        assertThat(14, is(transmissionDataTime.get(Calendar.HOUR_OF_DAY)));
        assertThat(49, is(transmissionDataTime.get(Calendar.MINUTE)));
        assertThat(23, is(transmissionDataTime.get(Calendar.SECOND)));

        assertThat("161837", is(authorisationResponse.getSystemTraceNumber()));

        Calendar localTransactionTime = Calendar.getInstance();
        localTransactionTime.setTime(authorisationResponse.getLocalTransactionDateTime());

        assertThat(8, is(localTransactionTime.get(Calendar.MONTH)));
        assertThat(12, is(localTransactionTime.get(Calendar.DAY_OF_MONTH)));
        assertThat(14, is(localTransactionTime.get(Calendar.HOUR_OF_DAY)));
        assertThat(49, is(localTransactionTime.get(Calendar.MINUTE)));
        assertThat(23, is(localTransactionTime.get(Calendar.SECOND)));

        assertThat("372", is(authorisationResponse.getCountryCode()));

        assertThat("000007", is(authorisationResponse.getAcquiringInstitutionCode()));

        assertThat("934915161837", is(authorisationResponse.getRetrievalReferenceNumber()));

        assertThat("789165", is(authorisationResponse.getAuthorisationCode()));

        assertThat("00", is(authorisationResponse.getResponseCode()));

        assertThat("5         M", is(authorisationResponse.getAdditionalResponseData()));

        assertThat("978", is(authorisationResponse.getTransactionCurrencyCode()));
    }
}