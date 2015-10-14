package com.inatec.bankproxy.omnipay.model;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Anatoly Chernysh
 */
@Data
public class AuthorisationResponse {

    public AuthorisationResponse(String rawRequest) throws ParseException {
        String msgLength = rawRequest.substring(0, 4);
        if (!Integer.valueOf(StringUtils.stripStart(msgLength, "0")).equals(rawRequest.length() - 4)) {
            throw new IllegalArgumentException("MessageLength is not correct.");
        }

        // BM01
        String messageTypeIdentifier = rawRequest.substring(4, 8);
        if (!messageTypeIdentifier.equals(MESSAGE_TYPE_IDENTIFIER)) {
            throw new IllegalArgumentException("MessageTypeIdentifier is not correct.");
        }
        this.primaryBitmap = rawRequest.substring(8, 24);

        // BM02
        Integer lengthOfPrimaryAccountNumber = Integer.valueOf(rawRequest.substring(24, 26));
        this.primaryAccountNumber = rawRequest.substring(26, 26 + lengthOfPrimaryAccountNumber);

        // BM03
        this.processingCode = rawRequest.substring(26 + lengthOfPrimaryAccountNumber, 26 + lengthOfPrimaryAccountNumber + 6);

        // BM04
        this.transactionAmount = Integer.valueOf(StringUtils.stripStart(rawRequest.substring(32 + lengthOfPrimaryAccountNumber, 44 + lengthOfPrimaryAccountNumber), "0"));

        // BM07
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        this.transmissionDateTime = sdf.parse(rawRequest.substring(44 + lengthOfPrimaryAccountNumber, 54 + lengthOfPrimaryAccountNumber));

        // BM11
        this.systemTraceNumber = rawRequest.substring(54 + lengthOfPrimaryAccountNumber, 60 + lengthOfPrimaryAccountNumber);

        // BM12
        String localTransactionTime = rawRequest.substring(60 + lengthOfPrimaryAccountNumber, 66 + lengthOfPrimaryAccountNumber);

        // B13
        String localTransactionDate = rawRequest.substring(66 + lengthOfPrimaryAccountNumber, 70 + lengthOfPrimaryAccountNumber);

        this.localTransactionDateTime = sdf.parse(localTransactionDate + localTransactionTime);

        // B18
        this.merchantCategoryCode = rawRequest.substring(70 + lengthOfPrimaryAccountNumber, 74 + lengthOfPrimaryAccountNumber);

        // BM19
        this.countryCode = rawRequest.substring(74 + lengthOfPrimaryAccountNumber, 77 + lengthOfPrimaryAccountNumber);

        // BM32
        Integer lengthOfAcquiringInstitutionCode = Integer.valueOf(rawRequest.substring(77 + lengthOfPrimaryAccountNumber, 79 + lengthOfPrimaryAccountNumber));
        this.acquiringInstitutionCode = rawRequest.substring(79 + lengthOfPrimaryAccountNumber, 79 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);

        // BM37
        this.retrievalReferenceNumber = rawRequest.substring(79 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 91 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);

        // BM38
        this.authorisationCode = rawRequest.substring(91 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 97 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);

        // BM39
        this.responseCode = rawRequest.substring(97 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 99 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode);

        // BM44
        Integer lengthOfAdditionalResponseData = Integer.valueOf(rawRequest.substring(99 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 101 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode));
        this.additionalResponseData = rawRequest.substring(101 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode, 101 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfAdditionalResponseData);

        // BM49
        this.transactionCurrencyCode = rawRequest.substring(101 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfAdditionalResponseData,
                104 + lengthOfPrimaryAccountNumber + lengthOfAcquiringInstitutionCode + lengthOfAdditionalResponseData);
    }

    /**
     * BM01
     */
    public static final String MESSAGE_TYPE_IDENTIFIER = "0110";

    @NotNull(message = "PrimaryBitmap is mandatory")
    @Size(min = 16, max = 16, message = "Length of PrimaryBitmap must be 16 characters long")
    private String primaryBitmap;

    /**
     * BM02
     */
    @NotNull(message = "PrimaryAccountNumber is mandatory")
    private String primaryAccountNumber;

    /**
     * BM03
     */
    @NotNull(message = "ProcessingCode is mandatory")
    @Size(min = 6, max = 6, message = "Length of ProcessingCode must be 6 characters long")
    private String processingCode;

    /**
     * BM04
     */
    @NotNull(message = "TransactionAmount is mandatory")
    private Integer transactionAmount;

    /**
     * BM07
     */
    @NotNull(message = "TransmissionDateTime is mandatory")
    private Date transmissionDateTime;

    /**
     * BM11
     */
    @NotNull(message = "SystemTraceNumber is mandatory")
    @Size(min = 6, max = 6, message = "Length of SystemTraceNumber must be 6 characters long")
    private String systemTraceNumber;

    /**
     * BM12 & BM13
     */
    @NotNull(message = "LocalTransactionDateTime is mandatory")
    private Date localTransactionDateTime;

    /**
     * B18
     */
    @NotNull(message = "MerchantCategoryCode is mandatory")
    @Size(min = 4, max = 4, message = "Length of MerchantCategoryCode must be 4 characters long")
    private String merchantCategoryCode;

    /**
     * BM19
     */
    @NotNull(message = "CountryCode is mandatory")
    @Size(min = 3, max = 3, message = "Length of CountryCode must be 3 characters long")
    private String countryCode;

    /**
     * BM32
     */
    @NotNull(message = "AcquiringInstitutionCode is mandatory")
    private String acquiringInstitutionCode;

    /**
     * BM37
     */
    @NotNull(message = "RetrievalReferenceNumber is mandatory")
    @Size(min = 12, max = 12, message = "Length of RetrievalReferenceNumber must be 12 characters long")
    private String retrievalReferenceNumber;

    /**
     * BM38
     */
    @NotNull(message = "AuthorisationCode is mandatory")
    @Size(min = 6, max = 6, message = "Length of AuthorisationCode must be 6 characters long")
    private String authorisationCode;

    /**
     * BM39
     */
    @NotNull(message = "ResponseCode is mandatory")
    @Size(min = 2, max = 2, message = "Length of ResponseCode must be 12 characters long")
    private String responseCode;

    /**
     * BM44
     */
    @NotNull(message = "AdditionalResponseData is mandatory")
    private String additionalResponseData;

    /**
     * BM49
     */
    @NotNull(message = "TransactionCurrencyCode is mandatory")
    @Size(min = 3, max = 3, message = "Length of TransactionCurrencyCode must be 3 characters long")
    private String transactionCurrencyCode;
}