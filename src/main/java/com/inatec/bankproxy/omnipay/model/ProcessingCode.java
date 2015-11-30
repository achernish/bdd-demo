package com.inatec.bankproxy.omnipay.model;

/**
 * @author Anatoly Chernysh
 */
public enum ProcessingCode {

    Purchase("000000"),
    CashBack("090000"),
    QuasiCash("110000"),
    Refund("200000"),
    PaymentTransaction("280000"),
    BalanceEnquiry("300000"),
    ELV("380110");

    private String code;

    ProcessingCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}