package com.example.lambda.pojo;

import java.math.BigInteger;

/**
 * @Author: yingfeng
 * @Date: 2019/11/7
 */
public class TestDto {

    private  int intValue;

    private BigInteger bigIntegerValue;

    public TestDto(int intValue, BigInteger bigIntegerValue) {
        this.intValue = intValue;
        this.bigIntegerValue = bigIntegerValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public BigInteger getBigIntegerValue() {
        return bigIntegerValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void setBigIntegerValue(BigInteger bigIntegerValue) {
        this.bigIntegerValue = bigIntegerValue;
    }

    public TestDto() {
    }
}
