package com.proccorp.eventory.transfer.solutions.one;

import com.proccorp.eventory.model.Account;

import java.util.Comparator;

public class ByIdAccountsComparator implements Comparator<Account> {

    @Override
    public int compare(Account o1, Account o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
