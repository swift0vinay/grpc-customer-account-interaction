package org.example.repository;

import org.example.services.account.model.AccountProto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AccountRepository {
    
    List<AccountProto.Account> accounts;
    
    AtomicInteger id;
    
    public AccountRepository(List<AccountProto.Account> accounts) {
        this.accounts = accounts;
        this.id = new AtomicInteger();
        this.id.set(accounts.size());
    }
    
    public List<AccountProto.Account> findAll() {
        return accounts;
    }
    
    public List<AccountProto.Account> findByCustomer(int customerId) {
        return accounts.stream()
                .filter(account -> account.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }
    
    public AccountProto.Account findByNumber(String number) {
        return accounts.stream()
                .filter(account -> account.getNumber().equalsIgnoreCase(number))
                .findFirst()
                .orElse(null);
    }
    
    public AccountProto.Account add(int customerId, String number) {
        AccountProto.Account account = AccountProto.Account.newBuilder()
                .setId(id.incrementAndGet())
                .setCustomerId(customerId)
                .setNumber(number)
                .build();
        return account;
    }
    
}