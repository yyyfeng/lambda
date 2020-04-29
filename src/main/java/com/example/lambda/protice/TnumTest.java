package com.example.lambda.protice;

public class TnumTest {
    public static void main(String[] args) {
        TxnCodeEnum txnCodeEnum = TxnCodeEnum.valueByCode("0001");
        System.out.println(txnCodeEnum.toString());

    }
}
