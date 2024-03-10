package org.example.client;

import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.model.Customer;
import org.example.services.customer.model.AccountServiceGrpc;
import org.example.services.customer.model.CustomerProto;
import org.springframework.stereotype.Service;

@Service
public class AccountClient {
    
    @GrpcClient("customer-account")
    AccountServiceGrpc.AccountServiceBlockingStub service;
    
    public CustomerProto.Account findByNumber(String number) {
        StringValue req = StringValue.newBuilder().setValue(number).build();
        return service.findByNumber(req);
    }
    
    public CustomerProto.Accounts findByCustomerId(int customerId) {
        Int32Value req = Int32Value.newBuilder().setValue(customerId).build();
        return service.findByCustomer(req);
    }
    
    public CustomerProto.Account addAccount(Customer customer) {
        CustomerProto.Account account = CustomerProto.Account.newBuilder()
                .setCustomerId(customer.getId())
                .setNumber(customer.getNumber())
                .build();
        return service.addAccount(account);
    }
    
}