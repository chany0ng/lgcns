package com.lgcns.banking;

import java.math.BigDecimal;

public class MonthAccount extends Account {
    public void mature(Account freeAccount) {
        transfer(freeAccount, this.balance);
        this.balance = BigDecimal.ZERO;
    }
}
