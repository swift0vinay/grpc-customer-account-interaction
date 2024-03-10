package org.example.client;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Int32Value;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.services.customer.model.CustomerServiceGrpc;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerClient {
    
    @GrpcClient("customer-customer")
    CustomerServiceGrpc.CustomerServiceBlockingStub service;
    
    public Map<Descriptors.FieldDescriptor, Object> findById(Integer id) {
        Int32Value req = Int32Value.newBuilder().setValue(id).build();
        return service.findById(req).getAllFields();
    }
    
    
}