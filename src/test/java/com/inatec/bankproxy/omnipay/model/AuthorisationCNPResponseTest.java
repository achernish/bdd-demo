package com.inatec.bankproxy.omnipay.model;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class AuthorisationCNPResponseTest {

    @Test
    public void authorisationRequestIsCorrect() throws ParseException {
        String givenRawResponse = "01330110723860010e1080001644444401234544430000000000000051901215144923161837144923091259583720600000793491516183778916500115         M978";

        AuthorisationCNPResponse authorisationCNPResponse = new AuthorisationCNPResponse(givenRawResponse);
        assertThat("723860010e108000", is(authorisationCNPResponse.getPrimaryBitmap()));
        assertThat("4444440123454443", is(authorisationCNPResponse.getPrimaryAccountNumber()));
        assertThat("000000", is(authorisationCNPResponse.getProcessingCode()));
        assertThat(5190, is(authorisationCNPResponse.getTransactionAmount()));

        Calendar transmissionDataTime = Calendar.getInstance();
        transmissionDataTime.setTime(authorisationCNPResponse.getTransmissionDateTime());
        assertThat(11, is(transmissionDataTime.get(Calendar.MONTH)));
        assertThat(15, is(transmissionDataTime.get(Calendar.DAY_OF_MONTH)));
        assertThat(14, is(transmissionDataTime.get(Calendar.HOUR_OF_DAY)));
        assertThat(49, is(transmissionDataTime.get(Calendar.MINUTE)));
        assertThat(23, is(transmissionDataTime.get(Calendar.SECOND)));

        assertThat("161837", is(authorisationCNPResponse.getSystemTraceNumber()));

        Calendar localTransactionTime = Calendar.getInstance();
        localTransactionTime.setTime(authorisationCNPResponse.getLocalTransactionDateTime());

        assertThat(8, is(localTransactionTime.get(Calendar.MONTH)));
        assertThat(12, is(localTransactionTime.get(Calendar.DAY_OF_MONTH)));
        assertThat(14, is(localTransactionTime.get(Calendar.HOUR_OF_DAY)));
        assertThat(49, is(localTransactionTime.get(Calendar.MINUTE)));
        assertThat(23, is(localTransactionTime.get(Calendar.SECOND)));

        assertThat("372", is(authorisationCNPResponse.getCountryCode()));

        assertThat("000007", is(authorisationCNPResponse.getAcquiringInstitutionCode()));

        assertThat("934915161837", is(authorisationCNPResponse.getRetrievalReferenceNumber()));

        assertThat("789165", is(authorisationCNPResponse.getAuthorisationCode()));

        assertThat("00", is(authorisationCNPResponse.getResponseCode()));

        assertThat("5         M", is(authorisationCNPResponse.getAdditionalResponseData()));

        assertThat("978", is(authorisationCNPResponse.getTransactionCurrencyCode()));
    }
}