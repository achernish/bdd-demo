package com.inatec.bankproxy.omnipay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Anatoly Chernysh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorisationCPRequest {

    /**
     * BM01
     */
    public static final String MESSAGE_TYPE_IDENTIFIER = "0100";

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
     * B14*
     */
    @NotNull(message = "CardExpiryDate is mandatory")
    private Date cardExpiryDate;

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
     * BM22
     */
    @NotNull(message = "PointOfServiceEntryMode is mandatory")
    @Size(min = 3, max = 3, message = "Length of PointOfServiceEntryMode must be 3 characters long")
    private String pointOfServiceEntryMode;

    /**
     * BM25
     */
    @NotNull(message = "PosConditionCode is mandatory")
    @Size(min = 2, max = 2, message = "Length of PosConditionCode must be 2 characters long")
    private String posConditionCode;

    /**
     * BM32
     */
    @NotNull(message = "AcquiringInstitutionCode is mandatory")
    private String acquiringInstitutionCode;

    /**
     * BM35
     */


    /**
     * BM37
     */
    @NotNull(message = "RetrievalReferenceNumber is mandatory")
    @Size(min = 12, max = 12, message = "Length of RetrievalReferenceNumber must be 12 characters long")
    private String retrievalReferenceNumber;

    /**
     * BM41
     */
    @NotNull(message = "TerminalID is mandatory")
    @Size(min = 8, max = 8, message = "Length of TerminalID must be 8 characters long")
    private String terminalID;

    /**
     * BM42
     */
    @NotNull(message = "MerchantNumber is mandatory")
    @Size(min = 15, max = 15, message = "Length of MerchantNumber must be 15 characters long")
    private String merchantNumber;

    /**
     * BM43
     */
    @NotNull(message = "CardAcceptorNameAndLocation is mandatory")
    @Size(min = 40, max = 40, message = "Length of CardAcceptorNameAndLocation must be 40 characters long")
    private String cardAcceptorNameAndLocation;

    /**
     * BM45*
     */

    /**
     * BM49
     */
    @NotNull(message = "TransactionCurrencyCode is mandatory")
    @Size(min = 3, max = 3, message = "Length of TransactionCurrencyCode must be 3 characters long")
    private String transactionCurrencyCode;

    /**
     * BM55
     */

    @Override
    public String toString() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<AuthorisationCPRequest>> violations = factory.getValidator().validate(this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

        StringBuffer rawMessage = new StringBuffer();
        rawMessage.append(MESSAGE_TYPE_IDENTIFIER);
        rawMessage.append(primaryBitmap);
        rawMessage.append(String.format("%02d", primaryAccountNumber.length()) + primaryAccountNumber);
        rawMessage.append(processingCode);
        rawMessage.append(String.format("%012d", transactionAmount));

        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        rawMessage.append(sdf.format(transmissionDateTime));

        rawMessage.append(systemTraceNumber);

        sdf = new SimpleDateFormat("HHmmssMMdd");
        rawMessage.append(sdf.format(localTransactionDateTime));

        sdf = new SimpleDateFormat("yyMM");
        rawMessage.append(sdf.format(cardExpiryDate));

        rawMessage.append(merchantCategoryCode);
        rawMessage.append(countryCode);
        rawMessage.append(pointOfServiceEntryMode);
        rawMessage.append(posConditionCode);
        rawMessage.append(String.format("%02d", acquiringInstitutionCode.length()) + acquiringInstitutionCode);
        rawMessage.append(retrievalReferenceNumber);
        rawMessage.append(terminalID);
        rawMessage.append(merchantNumber);
        rawMessage.append(cardAcceptorNameAndLocation);

        /*
        rawMessage.append(String.format("%03d", cvc2.length()) + cvc2);
        */

        rawMessage.append(transactionCurrencyCode);

        /*
        rawMessage.append(String.format("%03d", additionalData.length()) + additionalData);
        */

        return String.format("%04d", rawMessage.length()) + rawMessage.toString();
    }
}