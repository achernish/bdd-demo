package com.inatec.bankproxy.omnipay.model;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.BitSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class AuthorisationCPRequestTest {

    @Test
    public void authorisationRequestIsCorrect() throws ParseException {
        String givenRawRequest = "043601007238668128E082001654133300890000130000000000000004000427130751080761080751042760843720510010006000007365413330089000013=110600000000000000090990708065122334499000100769629355OmniPay Test             DUBLIN           IE9782125F2A02097882021A2B95051A2B3C4D5F9A030904099C01009F02060000000199999F03060000000000009F1006010003A410009F1A0205289F1E0831323334353637389F26080123456789ABCDEF9F2701CC9F3303E0E8809F34030000009F3602C3D49F3704CCDDEEFF";

        String msgLength = givenRawRequest.substring(0, 4);
        assertThat("0436", is(msgLength));

        // BM01
        String messageTypeIdentifier = givenRawRequest.substring(4, 8);
        assertThat("0100", is(messageTypeIdentifier));

        String primaryBitmap = givenRawRequest.substring(8, 24);
        assertThat("7238668128E08200", is(primaryBitmap));
        BitSet primaryBitmapSet = BitSet.valueOf(new long[] {Long.parseLong(primaryBitmap, 16)});

        // BM02
        Integer lengthOfPrimaryAccountNumber = Integer.valueOf(givenRawRequest.substring(24, 26));
        String primaryAccountNumber = givenRawRequest.substring(26, 26 + lengthOfPrimaryAccountNumber);
        assertThat("5413330089000013", is(primaryAccountNumber));

        // BM03
        String processingCode = givenRawRequest.substring(26 + lengthOfPrimaryAccountNumber, 26 + lengthOfPrimaryAccountNumber + 6);
        assertThat("000000", is(processingCode));

        // BM04
        String transactionAmount = givenRawRequest.substring(32 + lengthOfPrimaryAccountNumber, 44 + lengthOfPrimaryAccountNumber);
        assertThat("000000000400", is(transactionAmount));

        // BM07 (MMddHHmmss)
        String transmissionDateTime = givenRawRequest.substring(44 + lengthOfPrimaryAccountNumber, 54 + lengthOfPrimaryAccountNumber);
        assertThat("0427130751", is(transmissionDateTime));

        // BM11
        String systemTraceNumber = givenRawRequest.substring(54 + lengthOfPrimaryAccountNumber, 60 + lengthOfPrimaryAccountNumber);
        assertThat("080761", is(systemTraceNumber));

        // BM12 (hhmmss)
        String localTransactionTime = givenRawRequest.substring(60 + lengthOfPrimaryAccountNumber, 66 + lengthOfPrimaryAccountNumber);
        assertThat("080751", is(localTransactionTime));

        // B13 (MMDD)
        String localTransactionDate = givenRawRequest.substring(66 + lengthOfPrimaryAccountNumber, 70 + lengthOfPrimaryAccountNumber);
        assertThat("0427", is(localTransactionDate));

        // B14* (I need to check, if B14 is present in primaryBitMap)
        String cardExpiryDate = null;
        int lengthOfCardExpiryDate = 0;
        if (primaryBitmapSet.get(14)) {
            givenRawRequest.substring(70 + lengthOfPrimaryAccountNumber, 74 + lengthOfPrimaryAccountNumber);
            lengthOfCardExpiryDate = 4;
        }

        // B18
        String merchantCategoryCode = givenRawRequest.substring(70 + lengthOfCardExpiryDate + lengthOfPrimaryAccountNumber, 74 + lengthOfCardExpiryDate + lengthOfPrimaryAccountNumber);
        assertThat("6084", is(merchantCategoryCode));

            /*

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
        */

        AuthorisationCPRequest authorisationCPRequest = new AuthorisationCPRequest();
        authorisationCPRequest.setPrimaryBitmap(primaryBitmap);
        authorisationCPRequest.setPrimaryAccountNumber(primaryAccountNumber);
        authorisationCPRequest.setProcessingCode(processingCode);
        authorisationCPRequest.setTransactionAmount(Integer.valueOf(StringUtils.stripStart(transactionAmount, "0")));

        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        authorisationCPRequest.setTransmissionDateTime(sdf.parse(transmissionDateTime));

        authorisationCPRequest.setSystemTraceNumber(systemTraceNumber);

        authorisationCPRequest.setLocalTransactionDateTime(sdf.parse(localTransactionDate + localTransactionTime));

        if (org.springframework.util.StringUtils.hasText(cardExpiryDate)) {
            sdf = new SimpleDateFormat("yyMM");
            authorisationCPRequest.setCardExpiryDate(sdf.parse(cardExpiryDate));
        }

        authorisationCPRequest.setMerchantCategoryCode(merchantCategoryCode);
    }
}
