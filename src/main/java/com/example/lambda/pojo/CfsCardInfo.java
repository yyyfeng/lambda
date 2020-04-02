package com.example.lambda.pojo;

import java.io.Serializable;

public class CfsCardInfo extends CfsCardInfoKey implements Serializable {
    private String cardStatus;

    private String failReason;

    private String mailCompanyId;

    private String mailCompanyName;

    private String mailId;

    private String mailTime;

    private String mailRecipient;

    private String mailMobile;

    private String mailAddress;

    private String bsFlag;

    private String cardValidity;

    private String idType;

    private String idNo;

    private String userName;

    private String companyName;

    private String receiptType;

    private String address;

    private String region;

    private String city;

    private String province;

    private String country;

    private String zipCode;

    private String mobileNo;

    private String phoneNo;

    private String receiptBranch;

    private String billDate;

    private String homeCurrency;

    private String homeCreditLimit;

    private String foreignCurrency;

    private String foreignCreditLimit;

    private String productId;

    private String urgentFlag;

    private String mailAddressFlag;

    private String issuingBranch;

    private String sex;

    private String mailSmsFlag;

    private String mailSmsFileId;

    private String mailSmsSn;

    private String reserve;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    private static final long serialVersionUID = 1L;

    public String getCardStatus() {
        System.out.println("利用反射调用对应的方法");
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getMailCompanyId() {
        return mailCompanyId;
    }

    public void setMailCompanyId(String mailCompanyId) {
        this.mailCompanyId = mailCompanyId;
    }

    public String getMailCompanyName() {
        return mailCompanyName;
    }

    public void setMailCompanyName(String mailCompanyName) {
        this.mailCompanyName = mailCompanyName;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getMailTime() {
        return mailTime;
    }

    public void setMailTime(String mailTime) {
        this.mailTime = mailTime;
    }

    public String getMailRecipient() {
        return mailRecipient;
    }

    public void setMailRecipient(String mailRecipient) {
        this.mailRecipient = mailRecipient;
    }

    public String getMailMobile() {
        return mailMobile;
    }

    public void setMailMobile(String mailMobile) {
        this.mailMobile = mailMobile;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getBsFlag() {
        return bsFlag;
    }

    public void setBsFlag(String bsFlag) {
        this.bsFlag = bsFlag;
    }

    public String getCardValidity() {
        return cardValidity;
    }

    public void setCardValidity(String cardValidity) {
        this.cardValidity = cardValidity;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getReceiptBranch() {
        return receiptBranch;
    }

    public void setReceiptBranch(String receiptBranch) {
        this.receiptBranch = receiptBranch;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getHomeCurrency() {
        return homeCurrency;
    }

    public void setHomeCurrency(String homeCurrency) {
        this.homeCurrency = homeCurrency;
    }

    public String getHomeCreditLimit() {
        return homeCreditLimit;
    }

    public void setHomeCreditLimit(String homeCreditLimit) {
        this.homeCreditLimit = homeCreditLimit;
    }

    public String getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(String foreignCurrency) {
        this.foreignCurrency = foreignCurrency;
    }

    public String getForeignCreditLimit() {
        return foreignCreditLimit;
    }

    public void setForeignCreditLimit(String foreignCreditLimit) {
        this.foreignCreditLimit = foreignCreditLimit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUrgentFlag() {
        return urgentFlag;
    }

    public void setUrgentFlag(String urgentFlag) {
        this.urgentFlag = urgentFlag;
    }

    public String getMailAddressFlag() {
        return mailAddressFlag;
    }

    public void setMailAddressFlag(String mailAddressFlag) {
        this.mailAddressFlag = mailAddressFlag;
    }

    public String getIssuingBranch() {
        return issuingBranch;
    }

    public void setIssuingBranch(String issuingBranch) {
        this.issuingBranch = issuingBranch;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMailSmsFlag() {
        return mailSmsFlag;
    }

    public void setMailSmsFlag(String mailSmsFlag) {
        this.mailSmsFlag = mailSmsFlag;
    }

    public String getMailSmsFileId() {
        return mailSmsFileId;
    }

    public void setMailSmsFileId(String mailSmsFileId) {
        this.mailSmsFileId = mailSmsFileId;
    }

    public String getMailSmsSn() {
        return mailSmsSn;
    }

    public void setMailSmsSn(String mailSmsSn) {
        this.mailSmsSn = mailSmsSn;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }
}