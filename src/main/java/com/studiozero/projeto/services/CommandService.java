package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.CommandProduct;
import com.studiozero.projeto.entities.Employee;
import com.studiozero.projeto.enums.Context;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.exceptions.UnauthorizedException;
import com.studiozero.projeto.mappers.CommandMapper;
import com.studiozero.projeto.repositories.ClientRepository;
import com.studiozero.projeto.repositories.CommandProductRepository;
import com.studiozero.projeto.repositories.CommandRepository;
import com.studiozero.projeto.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final CommandRepository commandRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final CommandMapper commandMapper;
    private final CommandProductRepository commandProductRepository;
    private final TracingService tracingService;

    public Command createCommand(CommandRequestDTO commanddto) {
        Employee employee = employeeRepository.findById(commanddto.getFkEmployee())
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado"));

        Command command = commandMapper.toEntity(commanddto);
        command.setEmployee(employee);

        if (commanddto.getFkClient() != null) {
            Client client = clientRepository.findById(commanddto.getFkClient()).orElse(null);
            command.setClient(client);

        }
        tracingService.setTracing(Context.BAR);

        return commandRepository.save(command);
    }

    public Command findCommandById(Integer id) {
        return commandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comanda não encontrada"));
    }

    public List<Command> listCommands() {
        return commandRepository.findAll();
    }

    public List<Command> listCommands(Status status) {
        return commandRepository.findAllByStatus(status);
    }

    public Command updateCommand(Command command) {
        if (!commandRepository.existsById(command.getId())) {
            throw new NotFoundException("Comanda não encontrada");
        }

        List<CommandProduct> commandProducts = commandProductRepository.findAllByCommand_Id(command.getId());

        double totalValue = commandProducts.stream()
                .mapToDouble(cp -> cp.getUnitValue() * cp.getProductQuantity())
                .sum();

        if (command.getStatus() == Status.CLOSED && Double.compare(command.getDiscount(), 0.0) != 0) {
            double discount = (totalValue * command.getDiscount()) / 100;
            totalValue -= discount;
        }

        if (command.getStatus() != Status.CLOSED && command.getDiscount() > 0) {
            throw new UnauthorizedException("Discount just can be applied in closed commands!");
        }


        command.setTotalValue(totalValue);
        tracingService.setTracing(Context.BAR);
        return commandRepository.save(command);
    }

    public void deleteCommand(Integer id) {
        Command command = commandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comanda não encontrada"));

        if (command.getStatus() != Status.CLOSED) {
            throw new UnauthorizedException("A comanda deve estar fechada para ser excluída");
        }

        tracingService.setTracing(Context.BAR);
        commandRepository.deleteById(id);
    }
}
