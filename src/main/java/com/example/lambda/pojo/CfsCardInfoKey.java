package com.example.lambda.pojo;

import java.io.Serializable;

public class CfsCardInfoKey implements Serializable {
    private String orgId;

    private String batchDate;

    private String productCode;

    private String batchSeq;

    private String cardNo;

    private static final long serialVersionUID = 1L;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBatchSeq() {
        return batchSeq;
    }

    public void setBatchSeq(String batchSeq) {
        this.batchSeq = batchSeq;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}