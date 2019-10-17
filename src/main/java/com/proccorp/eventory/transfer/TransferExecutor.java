package com.proccorp.eventory.transfer;

import com.proccorp.eventory.model.Account;

import java.math.BigInteger;

public interface TransferExecutor {

    void execute(Account giver, Account taker, BigInteger amount) throws TransferFailureException;
}
