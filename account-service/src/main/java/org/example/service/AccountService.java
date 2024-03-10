package org.example.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.repository.AccountRepository;
import org.example.services.account.model.AccountProto;
import org.example.services.account.model.AccountServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class AccountService extends AccountServiceGrpc.AccountServiceImplBase {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Override
    public void findByNumber(StringValue request, StreamObserver<AccountProto.Account> responseObserver) {
        AccountProto.Account account = accountRepository.findByNumber(request.getValue());
        responseObserver.onNext(account);
        responseObserver.onCompleted();
    }
    
    @Override
    public void findByCustomer(Int32Value request, StreamObserver<AccountProto.Accounts> responseObserver) {
        List<AccountProto.Account> accountList = accountRepository.findByCustomer(request.getValue());
        AccountProto.Accounts accounts = AccountProto.Accounts.newBuilder().addAllAccount(accountList).build();
        responseObserver.onNext(accounts);
        responseObserver.onCompleted();
    }
    
    @Override
    public void findAll(Empty request, StreamObserver<AccountProto.Accounts> responseObserver) {
        List<AccountProto.Account> accountList = accountRepository.findAll();
        AccountProto.Accounts accounts = AccountProto.Accounts.newBuilder().addAllAccount(accountList).build();
        responseObserver.onNext(accounts);
        responseObserver.onCompleted();
    }
    
    @Override
    public void addAccount(AccountProto.Account request, StreamObserver<AccountProto.Account> responseObserver) {
        AccountProto.Account account = accountRepository.add(request.getCustomerId(), request.getNumber());
        responseObserver.onNext(account);
        responseObserver.onCompleted();
    }
    
}