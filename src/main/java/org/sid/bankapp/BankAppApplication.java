package org.sid.bankapp;

import org.sid.bankapp.entities.CurrentAccount;
import org.sid.bankapp.entities.Customer;
import org.sid.bankapp.entities.Operation;
import org.sid.bankapp.entities.SavingAccount;
import org.sid.bankapp.enums.AccountStatus;
import org.sid.bankapp.enums.OperationType;
import org.sid.bankapp.repositories.BankAccountRepository;
import org.sid.bankapp.repositories.CustomerRepository;
import org.sid.bankapp.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            OperationRepository operationRepository){
        return args -> {
            Stream.of("Hassan", "Yassine", "Aicha").forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(customer -> {
                // Current account
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*10000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);
                // Savings account
                SavingAccount savingsAccount = new SavingAccount();
                savingsAccount.setId(UUID.randomUUID().toString());
                savingsAccount.setBalance(Math.random()*10000);
                savingsAccount.setCreatedAt(new Date());
                savingsAccount.setStatus(AccountStatus.CREATED);
                savingsAccount.setCustomer(customer);
                savingsAccount.setInterestRate(1.2);
                bankAccountRepository.save(savingsAccount);
            });

            bankAccountRepository.findAll().forEach(account->{
                for(int i = 0; i<10; i++){
                    Operation operation = new Operation();
                    operation.setDate(new Date());
                    operation.setAmount(Math.random()*10000);
                    operation.setBankAccount(account);
                    operation.setType(Math.random()>0.5? OperationType.DEBIT : OperationType.CREDIT);
                    operationRepository.save(operation);
                }
            });
        };
    }


}

