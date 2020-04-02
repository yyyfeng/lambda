package com.example.lambda.pojo;


public enum CfsMailAddressFlagEnum {
    MAIL_ADDRESS_FLAG_HOME("H", "家庭地址"),
    MAIL_ADDRESS_FLAG_COMPANY("C", "公司地址"),
    MAIL_ADDRESS_FLAG_BANK(" ", "分行领卡"),

    ;

    private String code;
    private String text;
    CfsMailAddressFlagEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static CfsMailAddressFlagEnum valueByCode(String code) {
        for (CfsMailAddressFlagEnum enu : CfsMailAddressFlagEnum.values()) {
            if (enu.getCode().equals(code)) {
                return enu;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }

    public String toString() {
        return this.code;
    }
}
