package com.lgcns.banking;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    protected BigDecimal balance = BigDecimal.ZERO;
    ;

    public static void main(String[] args) {
        Account free = new FreeAccount();
        Account month = new MonthAccount();
        Account fund = new FundAccount();
        Account[] accounts = {free, month, fund};

        free.deposit(BigDecimal.valueOf(100000));
        free.transfer(month, BigDecimal.valueOf(10000));
        free.transfer(fund, BigDecimal.valueOf(20000));

        for (Account account : accounts) {
            if (account instanceof Withdrawable withAccount) {
                withAccount.withdraw(((Account) withAccount).getBalance().divide(BigDecimal.valueOf(2), RoundingMode.HALF_EVEN));
            }
            System.out.println(account.getBalance());
        }

        month.deposit(BigDecimal.valueOf(50000));
        month.deposit(BigDecimal.valueOf(100000));
        ((MonthAccount) month).mature(free);

    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    public void transfer(Account destination, BigDecimal amount) {
        this.balance = balance.subtract(amount);
        destination.deposit(amount);
    }
}
