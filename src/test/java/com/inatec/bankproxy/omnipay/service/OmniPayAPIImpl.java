package com.inatec.bankproxy.omnipay.service;


import org.apache.commons.lang.StringUtils;

/**
 * @author Anatoly Chernysh
 */
public class OmniPayAPIImpl implements OmniPayAPI {

    @Override
    public void authorisationRequestForCNP() {
        //String sample = "02060100723c648108e18010164444440123454443000000000000005190121514492316183";
        String sample = "02080100723c648108e1801016XXXXXXXXXXXXXXX400000000000002500010062201544204550001541007170862112768128006000048527922420455PC21PAY4010010007700100BigOption               XXXXXXXXX6695 GB003XXX978015004400700563210";

        String msgLength = sample.substring(0, 4);
        System.out.println("MsgLength: " + msgLength);

        // BM01
        String messageTypeIdentifier = sample.substring(4, 8);
        System.out.println("MessageTypeIdentifier: " + messageTypeIdentifier);

        String primaryBitmap = sample.substring(8, 24);
        System.out.println("PrimaryBitmap: " + primaryBitmap);

        // BM02
        Integer lengthOfPrimaryAccountNumber = Integer.valueOf(sample.substring(24, 26));
        String primaryAccountNumber = sample.substring(26, 26 + lengthOfPrimaryAccountNumber);
        System.out.println("PrimaryAccountNumber: " + primaryAccountNumber);

        // BM03
        String processingCode = sample.substring(26 + lengthOfPrimaryAccountNumber, 26 + lengthOfPrimaryAccountNumber + 6);
        System.out.println("ProcessingCode: " + processingCode);

        // BM04
        //String transactionAmount = StringUtils.stripStart(sample.substring(49, 61), "0");
        String transactionAmount = sample.substring(32 + lengthOfPrimaryAccountNumber, 44 + lengthOfPrimaryAccountNumber);
        System.out.println("TransactionAmount: " + transactionAmount);

        // BM07
        String transmissionDateTime = sample.substring(44 + lengthOfPrimaryAccountNumber, 54 + lengthOfPrimaryAccountNumber);
        System.out.println("TransmissionDateTime: " + transmissionDateTime);

        // BM11
        String systemTraceNumber = sample.substring(54 + lengthOfPrimaryAccountNumber, 60 + lengthOfPrimaryAccountNumber);
        System.out.println("SystemTraceNumber: " + systemTraceNumber);

        // BM12
        String localTransactionTime = sample.substring(60 + lengthOfPrimaryAccountNumber, 66 + lengthOfPrimaryAccountNumber);
        System.out.println("LocalTransactionTime (hhmmss): " + localTransactionTime);

        // B13
        String localTransactionDate = sample.substring(66 + lengthOfPrimaryAccountNumber, 70 + lengthOfPrimaryAccountNumber);
        System.out.println("LocalTransactionDate (MMDD): " + localTransactionDate);

        // B14
        String cardExpiryDate = sample.substring(70 + lengthOfPrimaryAccountNumber, 74 + lengthOfPrimaryAccountNumber);
        System.out.println("CardExpiryDate (YYMM): " + cardExpiryDate);

        // B18
        String merchantCategoryCode = sample.substring(74 + lengthOfPrimaryAccountNumber, 78 + lengthOfPrimaryAccountNumber);
        System.out.println("MerchantCategoryCode: " + merchantCategoryCode);

        // BM19
        String countryCode = sample.substring(78 + lengthOfPrimaryAccountNumber, 81 + lengthOfPrimaryAccountNumber);
        System.out.println("CountryCode: " + countryCode);

        // BM22
        String pointOfServiceEntryMode = sample.substring(81 + lengthOfPrimaryAccountNumber, 84 + lengthOfPrimaryAccountNumber);
        System.out.println("PointOfServiceEntryMode: " + pointOfServiceEntryMode);

        // BM25
        String posConditionCode = sample.substring(84 + lengthOfPrimaryAccountNumber, 86 + lengthOfPrimaryAccountNumber);
        System.out.println("POS ConditionCode: " + posConditionCode);

        // BM32
        Integer lengthOfAcquiringInstitutionCode = Integer.valueOf(sample.substring(86 + lengthOfPrimaryAccountNumber, 88 + lengthOfPrimaryAccountNumber));
        String acquiringInstitutionCode = sample.substring(88 + lengthOfPrimaryAccountNumber, 88 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        System.out.println("Bin Number / Acquiring Institution Code: " + acquiringInstitutionCode);

        // BM37
        String retrievalReferenceNumber = sample.substring(88 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 100 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        System.out.println("RetrievalReferenceNumber: " + retrievalReferenceNumber);

        // BM41
        String terminalID = sample.substring(100 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 108 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        System.out.println("TerminalID: " + terminalID);

        // BM42
        String merchantNumber = sample.substring(108 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 123 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        System.out.println("MerchantNumber: " + merchantNumber);

        // BM43
        String cardAcceptorNameAndLocation = sample.substring(123 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 163 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);
        System.out.println("CardAcceptorNameAndLocation: " + cardAcceptorNameAndLocation);

        // BM48
        Integer lengthOfCVC2 = Integer.valueOf(sample.substring(163 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 166 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode));
        String cvc2 = sample.substring(166 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 166 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2);
        System.out.println("CVC2 / CVV2: " + cvc2);

        // BM49
        String transactionCurrencyCode = sample.substring(166 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2, 169 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2);
        System.out.println("TransactionCurrencyCode: " + transactionCurrencyCode);

        // B60
        Integer lengthOfAdditionalData = Integer.valueOf(sample.substring(169 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2, 172 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2));
        String additionalData = sample.substring(172 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2, 172 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfCVC2 + lengthOfAdditionalData);
        System.out.println("AdditionalData: " + additionalData);
    }

    public static void main(String[] args) {
        OmniPayAPI omniPayAPI = new OmniPayAPIImpl();
        omniPayAPI.authorisationRequestForCNP();
    }

}
