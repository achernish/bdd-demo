package com.inatec.bankproxy.omnipay.model;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Anatoly Chernysh
 */
public class AuthorisationRequestTest {

    @Test
    public void authorisationRequestIsCorrect() throws ParseException {
        String givenRawRequest = "02080100723c648108e1801016XXXXXXXXXXXXXXX400000000000002500010062201544204550001541007170862112768128006000048527922420455PC21PAY4010010007700100BigOption               XXXXXXXXX6695 GB003XXX978015004400700563210";

        String msgLength = givenRawRequest.substring(0, 4);
        assertThat("0208", is(msgLength));

        // BM01
        String messageTypeIdentifier = givenRawRequest.substring(4, 8);
        assertThat("0100", is(messageTypeIdentifier));

        String primaryBitmap = givenRawRequest.substring(8, 24);
        assertThat("723c648108e18010", is(primaryBitmap));

        // BM02
        Integer lengthOfPrimaryAccountNumber = Integer.valueOf(givenRawRequest.substring(24, 26));
        String primaryAccountNumber = givenRawRequest.substring(26, 26 + lengthOfPrimaryAccountNumber);
        assertThat("XXXXXXXXXXXXXXX4", is(primaryAccountNumber));

        // BM03
        String processingCode = givenRawRequest.substring(26 + lengthOfPrimaryAccountNumber, 26 + lengthOfPrimaryAccountNumber + 6);
        assertThat("000000", is(processingCode));

        // BM04
        String transactionAmount = givenRawRequest.substring(32 + lengthOfPrimaryAccountNumber, 44 + lengthOfPrimaryAccountNumber);
        assertThat("000000025000", is(transactionAmount));

        // BM07
        String transmissionDateTime = givenRawRequest.substring(44 + lengthOfPrimaryAccountNumber, 54 + lengthOfPrimaryAccountNumber);
        assertThat("1006220154", is(transmissionDateTime));

        // BM11
        String systemTraceNumber = givenRawRequest.substring(54 + lengthOfPrimaryAccountNumber, 60 + lengthOfPrimaryAccountNumber);
        assertThat("420455", is(systemTraceNumber));

        // BM12
        String localTransactionTime = givenRawRequest.substring(60 + lengthOfPrimaryAccountNumber, 66 + lengthOfPrimaryAccountNumber);
        assertThat("000154", is(localTransactionTime));

        // B13
        String localTransactionDate = givenRawRequest.substring(66 + lengthOfPrimaryAccountNumber, 70 + lengthOfPrimaryAccountNumber);
        assertThat("1007", is(localTransactionDate));

        // B14
        String cardExpiryDate = givenRawRequest.substring(70 + lengthOfPrimaryAccountNumber, 74 + lengthOfPrimaryAccountNumber);
        assertThat("1708", is(cardExpiryDate));

        // B18
        String merchantCategoryCode = givenRawRequest.substring(74 + lengthOfPrimaryAccountNumber, 78 + lengthOfPrimaryAccountNumber);
        assertThat("6211", is(merchantCategoryCode));

        // BM19
        String countryCode = givenRawRequest.substring(78 + lengthOfPrimaryAccountNumber, 81 + lengthOfPrimaryAccountNumber);
        assertThat("276", is(countryCode));

        // BM22
        String pointOfServiceEntryMode = givenRawRequest.substring(81 + lengthOfPrimaryAccountNumber, 84 + lengthOfPrimaryAccountNumber);
        assertThat("812", is(pointOfServiceEntryMode));

        // BM25
        String posConditionCode = givenRawRequest.substring(84 + lengthOfPrimaryAccountNumber, 86 + lengthOfPrimaryAccountNumber);
        assertThat("80", is(posConditionCode));

        // BM32
        Integer lengthOfAcquiringInstitutionCode = Integer.valueOf(givenRawRequest.substring(86 + lengthOfPrimaryAccountNumber, 88 + lengthOfPrimaryAccountNumber));
        String acquiringInstitutionCode = givenRawRequest.substring(88 + lengthOfPrimaryAccountNumber, 88 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        assertThat("000048", is(acquiringInstitutionCode));

        // BM37
        String retrievalReferenceNumber = givenRawRequest.substring(88 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 100 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        assertThat("527922420455", is(retrievalReferenceNumber));

        // BM41
        String terminalID = givenRawRequest.substring(100 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 108 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        assertThat("PC21PAY4", is(terminalID));

        // BM42
        String merchantNumber = givenRawRequest.substring(108 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 123 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        assertThat("010010007700100", is(merchantNumber));

        // BM43
        String cardAcceptorNameAndLocation = givenRawRequest.substring(123 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 163 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        assertThat("BigOption               XXXXXXXXX6695 GB", is(cardAcceptorNameAndLocation));

        // BM48
        Integer lengthOfCVC2 = Integer.valueOf(givenRawRequest.substring(163 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 166 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode));
        String cvc2 = givenRawRequest.substring(166 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 166 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2);
        assertThat("XXX", is(cvc2));

        // BM49
        String transactionCurrencyCode = givenRawRequest.substring(166 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2, 169 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2);
        assertThat("978", is(transactionCurrencyCode));

        // B60
        Integer lengthOfAdditionalData = Integer.valueOf(givenRawRequest.substring(169 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2, 172 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2));
        String additionalData = givenRawRequest.substring(172 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2, 172 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2 + lengthOfAdditionalData);
        assertThat("004400700563210", is(additionalData));

        AuthorisationRequest authorisationRequest = new AuthorisationRequest();
        authorisationRequest.setPrimaryBitmap(primaryBitmap);
        authorisationRequest.setPrimaryAccountNumber(primaryAccountNumber);
        authorisationRequest.setProcessingCode(processingCode);
        authorisationRequest.setTransactionAmount(Integer.valueOf(StringUtils.stripStart(transactionAmount, "0")));

        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        authorisationRequest.setTransmissionDateTime(sdf.parse(transmissionDateTime));

        authorisationRequest.setSystemTraceNumber(systemTraceNumber);

        authorisationRequest.setLocalTransactionDateTime(sdf.parse(localTransactionDate + localTransactionTime));

        sdf = new SimpleDateFormat("yyMM");
        authorisationRequest.setCardExpiryDate(sdf.parse(cardExpiryDate));

        authorisationRequest.setMerchantCategoryCode(merchantCategoryCode);
        authorisationRequest.setCountryCode(countryCode);
        authorisationRequest.setPointOfServiceEntryMode(pointOfServiceEntryMode);
        authorisationRequest.setPosConditionCode(posConditionCode);
        authorisationRequest.setAcquiringInstitutionCode(acquiringInstitutionCode);
        authorisationRequest.setRetrievalReferenceNumber(retrievalReferenceNumber);
        authorisationRequest.setTerminalID(terminalID);
        authorisationRequest.setMerchantNumber(merchantNumber);
        authorisationRequest.setCardAcceptorNameAndLocation(cardAcceptorNameAndLocation);
        authorisationRequest.setCvc2(cvc2);
        authorisationRequest.setTransactionCurrencyCode(transactionCurrencyCode);
        authorisationRequest.setAdditionalData(additionalData);

        String testedRequest = authorisationRequest.toString();
        assertThat(givenRawRequest, is(testedRequest));
    }
}