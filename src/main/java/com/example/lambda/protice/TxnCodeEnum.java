package com.example.lambda.protice;

public enum TxnCodeEnum {

    STATUS_TXN_0001("0001","账单查询"),
    STATUS_TXN_0002("0002","卡申请查询"),
    STATUS_TXN_0003("0003","物流查询"),
    STATUS_TXN_0004("0004","积分查询"),
    ;

    public static TxnCodeEnum valueByCode(String code){
        for (TxnCodeEnum tn :
                TxnCodeEnum.values()) {
            if (tn.getCode().equals(code)) {
                return tn;
            }else{
                return null;
            }
        }
        return null;
    }

    private String code;
    private String desc;

    TxnCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "TxnCodeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
