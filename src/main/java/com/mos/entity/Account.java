package com.mos.entity;
import com.mos.common.AccountStatus;
import com.mos.common.AccountType;
import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private AccountType accountType;//账户类型
    private AccountStatus accountStatus;//账户状态
}
