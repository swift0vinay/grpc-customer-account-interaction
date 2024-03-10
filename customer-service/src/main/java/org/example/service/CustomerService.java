package org.example.service;

import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.client.AccountClient;
import org.example.repository.CustomerRepository;
import org.example.services.customer.model.CustomerProto;
import org.example.services.customer.model.CustomerServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class CustomerService extends CustomerServiceGrpc.CustomerServiceImplBase {
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    AccountClient accountClient;
    
    @Override
    public void findByPesel(StringValue request, StreamObserver<CustomerProto.Customer> responseObserver) {
        CustomerProto.Customer customer = customerRepository.findByPesel(request.getValue());
        responseObserver.onNext(customer);
        responseObserver.onCompleted();
    }
    
    @Override
    public void findById(Int32Value request, StreamObserver<CustomerProto.Customer> responseObserver) {
        CustomerProto.Customer customer = customerRepository.findById(request.getValue());
        CustomerProto.Accounts accounts = accountClient.findByCustomerId(customer.getId());
        List<CustomerProto.Account> accList = accounts.getAccountList();
        customer = CustomerProto.Customer.newBuilder(customer).addAllAccounts(accList).build();
        responseObserver.onNext(customer);
        responseObserver.onCompleted();
    }
    
    @Override
    public void findAll(StringValue request, StreamObserver<CustomerProto.Customers> responseObserver) {
        List<CustomerProto.Customer> customerList = customerRepository.findAll();
        CustomerProto.Customers customers = CustomerProto.Customers.newBuilder().addAllCustomers(customerList).build();
        responseObserver.onNext(customers);
        responseObserver.onCompleted();
    }
    
    @Override
    public void addCustomer(CustomerProto.Customer request, StreamObserver<CustomerProto.Customer> responseObserver) {
        CustomerProto.Customer customer = customerRepository.add(request.getType(), request.getName(), request.getPesel());
        responseObserver.onNext(customer);
        responseObserver.onCompleted();
    }
    
}