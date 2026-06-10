package com.lgcns.banking;

import java.math.BigDecimal;

public class FreeAccount extends Account implements Withdrawable {
    @Override
    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
//    @Override
//    public void transfer(Account destination, BigDecimal amount) {
//        this.balance = balance.subtract(amount);
//        super.transfer(destination, amount);
//    }
}
