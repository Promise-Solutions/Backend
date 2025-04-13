package com.studiozero.projeto.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CommandResponseDTO {
    private Integer id;
    private LocalDateTime openingDateTime;
    private LocalDateTime closingDateTime;
    private Double discount;
    private Double totalValue;
    private UUID fkClient;
    private UUID fkEmployee;
    private Status status;

    public CommandResponseDTO(Command command) {
        this.id = command.getId();
        this.openingDateTime = command.getOpeningDateTime();
        this.closingDateTime = command.getClosingDateTime();
        this.discount = command.getDiscount();
        this.totalValue = command.getTotalValue();
        this.fkClient = command.getFkClient();
        this.fkEmployee = command.getFkEmployee();
        this.status = command.getStatus();
    }
}
