package org.sid.bankapp.mappers;


import org.sid.bankapp.dtos.CurrentBankAccountDTO;
import org.sid.bankapp.dtos.CustomerDTO;
import org.sid.bankapp.dtos.OperationDTO;
import org.sid.bankapp.dtos.SavingBankAccountDTO;
import org.sid.bankapp.entities.CurrentAccount;
import org.sid.bankapp.entities.Customer;
import org.sid.bankapp.entities.Operation;
import org.sid.bankapp.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;

    }

    public SavingBankAccountDTO fromSavingsBankAccount(SavingAccount savingsAccount){
        SavingBankAccountDTO savingsBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingsAccount, savingsBankAccountDTO);
        savingsBankAccountDTO.setCustomerDTO(fromCustomer(savingsAccount.getCustomer()));
        savingsBankAccountDTO.setType(savingsAccount.getClass().getSimpleName());
        return savingsBankAccountDTO;
    }

    public SavingAccount fromSavingsBankAccountDTO(SavingBankAccountDTO savingsBankAccountDTO){
        SavingAccount savingsAccount = new SavingAccount();
        BeanUtils.copyProperties(savingsBankAccountDTO, savingsAccount);
        savingsAccount.setCustomer(fromCustomerDTO(savingsBankAccountDTO.getCustomerDTO()));
        return savingsAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO=new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public OperationDTO fromAccountOperation(Operation accountOperation){
        OperationDTO accountOperationDTO=new OperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }



}
