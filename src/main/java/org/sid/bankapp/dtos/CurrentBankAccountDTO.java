package org.sid.bankapp.dtos;

import lombok.Data;
import org.sid.bankapp.enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO{
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private double overDrat;
   private CustomerDTO customerDTO;

}
