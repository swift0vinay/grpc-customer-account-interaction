package org.example;

import org.example.repository.AccountRepository;
import org.example.services.account.model.AccountProto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @Bean
    AccountRepository repository() {
        List<AccountProto.Account> accounts = new ArrayList<>();
        accounts.add(AccountProto.Account.newBuilder().setId(1).setCustomerId(1).setNumber("111111").build());
        accounts.add(AccountProto.Account.newBuilder().setId(2).setCustomerId(2).setNumber("222222").build());
        accounts.add(AccountProto.Account.newBuilder().setId(3).setCustomerId(3).setNumber("333333").build());
        accounts.add(AccountProto.Account.newBuilder().setId(4).setCustomerId(4).setNumber("444444").build());
        accounts.add(AccountProto.Account.newBuilder().setId(5).setCustomerId(1).setNumber("555555").build());
        accounts.add(AccountProto.Account.newBuilder().setId(6).setCustomerId(2).setNumber("666666").build());
        accounts.add(AccountProto.Account.newBuilder().setId(7).setCustomerId(2).setNumber("777777").build());
        return new AccountRepository(accounts);
    }
}