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
import java.text.ParseException;
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
public class AuthorisationRequest {

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

    private String localTransactionTime;

    private String cardExpiryDate;

    private String merchantCategoryCode;

    private String countryCode;

    private String pointOfServiceEntryMode;

    private String posConditionCode;

    private String acquiringInstitutionCode;

    private String retrievalReferenceNumber;

    private String terminalID;

    private String merchantNumber;

    private String cardAcceptorNameAndLocation;

    private String cvc2;

    private String transactionCurrencyCode;

    private String additionalData;

    @Override
    public String toString() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<AuthorisationRequest>> violations = factory.getValidator().validate(this);
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

        return String.format("%04d", rawMessage.length()) + rawMessage.toString();
    }

    public static void main(String[] args) throws ParseException {
        AuthorisationRequest authorisationRequest = new AuthorisationRequest();
        authorisationRequest.setPrimaryBitmap("723c648108e18010");
        authorisationRequest.setPrimaryAccountNumber("XXXXXXXXXXXXXXX4");
        authorisationRequest.setProcessingCode("000000");
        authorisationRequest.setTransactionAmount(25000);

        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        authorisationRequest.setTransmissionDateTime(sdf.parse("1006220154"));

        authorisationRequest.setSystemTraceNumber("420455");

        System.out.println(authorisationRequest.toString());
    }
}