package org.sid.bankapp.dtos;

import lombok.Data;
import org.sid.bankapp.enums.OperationType;

import java.util.Date;

@Data
public class OperationDTO {
    private Long id;
    private Date date;
    private double amount;
    private OperationType type;
    private String description;
}
