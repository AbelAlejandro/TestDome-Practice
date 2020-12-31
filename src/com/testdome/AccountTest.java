package com.testdome;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

//        The deposit and withdraw methods will not accept negative numbers.
//        Account cannot overstep its overdraft limit.
//        The deposit and withdraw methods will deposit or withdraw the correct amount, respectively.
//        The withdraw and deposit methods return the correct results.
public class AccountTest {
    public static final double OVERDRAFT_LIMIT = 110;
    public static final double DEPOSIT_AMOUNT = 11;
    public static final double WITHDRAW_AMOUNT = 10;
    private double epsilon = 1e-6;

    @Test
    public void accountCannotHaveNegativeOverdraftLimit() {
        Account account = new Account(-20);
        Assert.assertEquals(0d, account.getOverdraftLimit(), epsilon);
    }

    @Test
    public void accountCannotOverstepOverdraftLimit() {
        Account account = new Account(OVERDRAFT_LIMIT);
        Assert.assertFalse(account.withdraw(120));
    }

    @Test
    public void depositUsesCorrectAmount() {
        Account account = new Account(OVERDRAFT_LIMIT);
        account.deposit(DEPOSIT_AMOUNT);
        Assert.assertThat(account.getBalance(), equalTo(DEPOSIT_AMOUNT));
    }

    @Test
    public void withdrawUsesCorrectAmount() {
        Account account = new Account(OVERDRAFT_LIMIT);
        account.deposit(DEPOSIT_AMOUNT);
        account.withdraw(WITHDRAW_AMOUNT);
        Assert.assertThat(account.getBalance(), equalTo(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT));
    }

    @Test
    public void withdrawReturnsCorrectly() {
        Account account = new Account(OVERDRAFT_LIMIT);
        account.deposit(DEPOSIT_AMOUNT);
        Assert.assertTrue(account.withdraw(WITHDRAW_AMOUNT));
        Assert.assertFalse(account.withdraw(OVERDRAFT_LIMIT + 2));
        Assert.assertFalse(account.withdraw(-WITHDRAW_AMOUNT));

    }

    @Test
    public void depositReturnsCorrectly() {
        Account account = new Account(OVERDRAFT_LIMIT);
        Assert.assertTrue(account.deposit(DEPOSIT_AMOUNT));
        Assert.assertFalse(account.deposit(-DEPOSIT_AMOUNT));

    }
}
