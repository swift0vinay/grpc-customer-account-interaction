package org.example.controller;

import com.google.protobuf.Descriptors;
import org.example.model.Customer;
import org.example.client.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccountController {
    
    @Autowired
    AccountClient client;
    
    @PostMapping("/account/add")
    public Map<Descriptors.FieldDescriptor, Object> addAccount(@RequestBody Customer customer) {
        return client.addAccount(customer).getAllFields();
    }
    
}