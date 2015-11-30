package com.inatec.bankproxy.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Anatoly Chernysh
 */
public class AuthorisationRequest implements Serializable {

    /**
     * Card Number. Contains the primary account number as encoded on the card.
     */
    @NotNull(message = "PrimaryAccountNumber is mandatory")
    private String primaryAccountNumber;

    /**
     * The Transaction Amount is expressed in the transaction currency. Where the currency of the transaction can be split into smaller units
     * (e.g. US Dollars into Cents, French Francs into Centimes), the Amount, Transaction must be expressed in this smaller unit.
     * The default exponent (associated with that Currency Code) must be used to determine the decimal part of the amount.
     */
    @NotNull(message = "TransactionAmount is mandatory")
    private Integer transactionAmount;

    /**
     * System Time. This field represents the GMT/UTC time and date when the transaction entered the system.
     * Once set, the value of this field must remain unchanged for the life of the transaction.
     */
    @NotNull(message = "TransmissionDateTime is mandatory")
    private Date transmissionDateTime;

    /**
     * A number assigned by the originator of a message to identify a particular ‘transaction’.
     * The same System Trace Audit Number (STAN) must be used for all messages belonging to the same transaction.
     */
    @NotNull(message = "SystemTraceNumber is mandatory")
    @Size(min = 6, message = "Length of SystemTraceNumber must be 6 characters long")
    private String systemTraceNumber;

    /**
     * Date & Time on Website the Terminal or Website. This field represents the local time & date at the terminal
     * where the transaction occurred.
     */
    @NotNull(message = "LocalTransactionDateTime is mandatory")
    private Date localTransactionDateTime;


    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransmissionDateTime() {
        return transmissionDateTime;
    }

    public void setTransmissionDateTime(Date transmissionDateTime) {
        this.transmissionDateTime = transmissionDateTime;
    }

    public String getSystemTraceNumber() {
        return systemTraceNumber;
    }

    public void setSystemTraceNumber(String systemTraceNumber) {
        this.systemTraceNumber = systemTraceNumber;
    }

    public Date getLocalTransactionDateTime() {
        return localTransactionDateTime;
    }

    public void setLocalTransactionDateTime(Date localTransactionDateTime) {
        this.localTransactionDateTime = localTransactionDateTime;
    }
}