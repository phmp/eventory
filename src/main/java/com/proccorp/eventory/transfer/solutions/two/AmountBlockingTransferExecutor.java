package com.proccorp.eventory.transfer.solutions.two;

import com.proccorp.eventory.model.Account;
import com.proccorp.eventory.transfer.TransferExecutor;
import com.proccorp.eventory.transfer.TransferFailureException;
import com.proccorp.eventory.transfer.validation.AccountValidator;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public class AmountBlockingTransferExecutor implements TransferExecutor {

    private AccountValidator validator;

    @Inject
    public AmountBlockingTransferExecutor(AccountValidator validator) {
        this.validator = validator;
    }

    @Override
    public void execute(Account giver, Account taker, BigInteger amount) throws TransferFailureException {
        log.info("from: {} to: {} money to transfer: {}, transfer STARTED", giver, taker, amount);
        synchronized (giver) {
            validator.checkIfGiverHasEnoughMoney(giver, amount);
            giver.subtractAmount(amount);
        }
        synchronized (taker) {
            taker.addAmount(amount);
        }
        log.info("from: {} to: {} money to transfer: {}, transfer SUCCESS", giver, taker, amount);
    }
}
