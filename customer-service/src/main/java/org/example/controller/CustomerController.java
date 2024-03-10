package org.example.controller;

import com.google.protobuf.Descriptors;
import org.example.client.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustomerController {
    
    @Autowired
    CustomerClient client;
    
    @GetMapping("/customer/{id}")
    Map<Descriptors.FieldDescriptor, Object> findById(@PathVariable("id") Integer id) {
        return  client.findById(id);
    }
}