package com.mos.common;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum AccountType {
    ADMIN(1,"管理员"),CUSTOMER(2,"客户");

    private int flag;
    private String desc;

    AccountType(int flag,String desc) {
        this.flag = flag;
        this.desc = desc;
    }

    public static AccountType valueOf(int flag) {
        for (AccountType accountTypes : values()) {
            if(accountTypes.flag == flag) {
                return accountTypes;
            }
        }
        throw  new RuntimeException("AccountType flag " + flag + " not found.");
    }

}
