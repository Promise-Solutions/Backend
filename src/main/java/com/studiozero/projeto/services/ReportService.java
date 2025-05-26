package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.*;
import com.studiozero.projeto.repositories.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ClientRepository clientRepository;
    private final CommandProductRepository commandProductRepository;
    private final EmployeeRepository employeeRepository;
    private final CommandRepository commandRepository;
    private final JobRepository jobRepository;
    private final SubJobRepository subJobRepository;
    private final ProductRepository productRepository;
    private final TaskRepository taskRepository;

    public File gerarRelatorioCSV() {
        List<Client> clients = clientRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();
        List<Command> commands = commandRepository.findAll();
        List<Job> jobs = jobRepository.findAll();
        List<SubJob> subJobs = subJobRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Task> tasks = taskRepository.findAll();
        List<CommandProduct> commandProducts = commandProductRepository.findAll();

        String dataGeracao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        File arquivoCSV = new File(System.getProperty("java.io.tmpdir"), "Relatório Gerado em - " + dataGeracao + ".csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV))) {
            writer.write("Relatório Completo - Gerado em: " + dataGeracao);
            writer.newLine(); writer.newLine();

            // CLIENTES
            writer.write("########## CLIENTES ##########");
            writer.newLine();
            writer.write("ID;Nome;CPF;Email;Contato;Tipo;Ativo;Data de Nascimento;Data de Criação");
            writer.newLine();
            for (Client c : clients) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        c.getId(), c.getName(), c.getCpf(), c.getEmail(), c.getContact(),
                        traduzTipoCliente(String.valueOf(c.getClientType())), traduzBoolean(c.getActive()),
                        formatDate(c.getBirthDay()), formatDate(c.getCreatedDate())));
                writer.newLine();
            }
            writer.newLine();

            // FUNCIONÁRIOS
            writer.write("########## FUNCIONÁRIOS ##########");
            writer.newLine();
            writer.write("ID;Nome;CPF;Email;Contato;Ativo");
            writer.newLine();
            for (Employee e : employees) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s",
                        e.getId(), e.getName(), e.getCpf(), e.getEmail(), e.getContact(), traduzBoolean(e.getActive())));
                writer.newLine();
            }
            writer.newLine();

            // PRODUTOS
            writer.write("########## PRODUTOS ##########");
            writer.newLine();
            writer.write("ID;Nome;Quantidade;Valor Unitário;Valor de Compra");
            writer.newLine();
            for (Product p : products) {
                writer.write(String.format("%d;%s;%d;%.2f;%.2f",
                        p.getId(), p.getName(), p.getQuantity(), p.getUnitValue(), p.getBuyValue()));
                writer.newLine();
            }
            writer.newLine();

            // COMANDAS
            writer.write("########## COMANDAS ##########");
            writer.newLine();
            writer.write("ID;Cliente;Funcionário;Status;Desconto;Valor Total;Data de Criação;Data de Fechamento");
            writer.newLine();
            for (Command cmd : commands) {
                writer.write(String.format("%d;%s;%s;%s;%.2f;%.2f;%s;%s",
                        cmd.getId(),
                        cmd.getClient() != null ? cmd.getClient().getName() : "",
                        cmd.getEmployee() != null ? cmd.getEmployee().getName() : "",
                        traduzStatus(String.valueOf(cmd.getStatus())),
                        cmd.getDiscount(),
                        cmd.getTotalValue(),
                        formatDateTime(cmd.getOpeningDateTime()),
                        formatDateTime(cmd.getClosingDateTime())));
                writer.newLine();
            }
            writer.newLine();

            // COMANDAS - PRODUTOS
            writer.write("########## PRODUTOS POR COMANDA ##########");
            writer.newLine();
            writer.write("ID;ComandaID;Produto;Quantidade;Valor Total");
            writer.newLine();
            for (CommandProduct cp : commandProducts) {
                writer.write(String.format("%d;%d;%s;%d;%.2f",
                        cp.getId(),
                        cp.getCommand() != null ? cp.getCommand().getId() : 0,
                        cp.getProduct() != null ? cp.getProduct().getName() : "",
                        cp.getProductQuantity(),
                        cp.getUnitValue() * cp.getProductQuantity()));
                writer.newLine();
            }
            writer.newLine();

            // SERVIÇOS
            writer.write("########## SERVIÇOS ##########");
            writer.newLine();
            writer.write("ID;Tipo;Categoria;Título;Valor Total;Status");
            writer.newLine();
            for (Job j : jobs) {
                writer.write(String.format("%s;%s;%s;%s;%.2f;%s",
                        j.getId(),
                        traduzTipoCliente(String.valueOf(j.getServiceType())),
                        traduzCategoria(String.valueOf(j.getCategory())),
                        j.getTitle(),
                        j.getTotalValue(),
                        traduzStatus(String.valueOf(j.getStatus()))));
                writer.newLine();
            }
            writer.newLine();

            // SUBSERVIÇOS
            writer.write("########## SUBSERVIÇOS ##########");
            writer.newLine();
            writer.write("ID;ServiçoID;Título;Valor Total;Data;Usou Sala?;Status");
            writer.newLine();
            for (SubJob sj : subJobs) {
                writer.write(String.format("%s;%s;%s;%.2f;%s;%s;%s",
                        sj.getId(),
                        sj.getJob() != null ? sj.getJob().getId() : 0,
                        sj.getTitle(),
                        sj.getValue(),
                        formatDate(sj.getDate()),
                        traduzBoolean(sj.getNeedsRoom()),
                        traduzStatus(String.valueOf(sj.getStatus()))));
                writer.newLine();
            }
            writer.newLine();

            // TAREFAS
            writer.write("########## TAREFAS ##########");
            writer.newLine();
            writer.write("ID;Título;Status");
            writer.newLine();
            for (Task t : tasks) {
                writer.write(String.format("%s;%s;%s",
                        t.getId(), t.getTitle(), traduzStatus(String.valueOf(t.getStatus()))));
                writer.newLine();
            }

            writer.flush();
            return arquivoCSV;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar relatório completo", e);
        }
    }

    public File gerarRelatorioExcel() {
        List<Client> clients = clientRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();
        List<Command> commands = commandRepository.findAll();
        List<Job> jobs = jobRepository.findAll();
        List<SubJob> subJobs = subJobRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Task> tasks = taskRepository.findAll();
        List<CommandProduct> commandProducts = commandProductRepository.findAll();

        String dataGeracao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        File arquivoXLSX = new File(System.getProperty("java.io.tmpdir"), "Relatório Gerado em - " + dataGeracao + ".xlsx");

        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fos = new FileOutputStream(arquivoXLSX)) {

            // Estilo de cabeçalho
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // CLIENTES
            Sheet clientesSheet = workbook.createSheet("Clientes");
            String[] clienteHeaders = {"ID", "Nome", "CPF", "Email", "Contato", "Tipo", "Ativo", "Nascimento", "Criação"};
            preencherSheetComDados(clientesSheet, clienteHeaders, headerStyle, clients, (row, c) -> {
                row.createCell(0).setCellValue(c.getId().toString());
                row.createCell(1).setCellValue(c.getName());
                row.createCell(2).setCellValue(c.getCpf());
                row.createCell(3).setCellValue(c.getEmail());
                row.createCell(4).setCellValue(c.getContact());
                row.createCell(5).setCellValue(traduzTipoCliente(c.getClientType().toString()));
                row.createCell(6).setCellValue(traduzBoolean(c.getActive()));
                row.createCell(7).setCellValue(formatDate(c.getBirthDay()));
                row.createCell(8).setCellValue(formatDate(c.getCreatedDate()));
            });

            // FUNCIONÁRIOS
            Sheet empSheet = workbook.createSheet("Funcionarios");
            String[] empHeaders = {"ID", "Nome", "CPF", "Email", "Contato", "Ativo"};
            preencherSheetComDados(empSheet, empHeaders, headerStyle, employees, (row, e) -> {
                row.createCell(0).setCellValue(e.getId().toString());
                row.createCell(1).setCellValue(e.getName());
                row.createCell(2).setCellValue(e.getCpf());
                row.createCell(3).setCellValue(e.getEmail());
                row.createCell(4).setCellValue(e.getContact());
                row.createCell(5).setCellValue(traduzBoolean(e.getActive()));
            });

            // Produtos
            Sheet prodSheet = workbook.createSheet("Produtos");
            String[] prodHeaders = {"ID", "Nome", "Quantidade", "Valor Unitário", "Valor de Compra"};
            preencherSheetComDados(prodSheet, prodHeaders, headerStyle, products, (row, p) -> {
                row.createCell(0).setCellValue(p.getId());
                row.createCell(1).setCellValue(p.getName());
                row.createCell(2).setCellValue(p.getQuantity());
                row.createCell(3).setCellValue(p.getUnitValue());
                row.createCell(4).setCellValue(p.getBuyValue());
            });

            // Comandas
            Sheet cmdSheet = workbook.createSheet("Comandas");
            String[] cmdHeaders = {"ID", "Cliente", "Funcionário", "Status", "Desconto", "Valor Total", "Abertura", "Fechamento"};
            preencherSheetComDados(cmdSheet, cmdHeaders, headerStyle, commands, (row, c) -> {
                row.createCell(0).setCellValue(c.getId());
                row.createCell(1).setCellValue(c.getClient() != null ? c.getClient().getName() : "Funcionário: " + c.getEmployee().getName());
                row.createCell(2).setCellValue(c.getEmployee() != null ? c.getEmployee().getName() : "-");
                row.createCell(3).setCellValue(traduzStatus(c.getStatus().toString()));
                row.createCell(4).setCellValue(c.getDiscount() + "%");
                row.createCell(5).setCellValue(c.getTotalValue());
                row.createCell(6).setCellValue(formatDateTime(c.getOpeningDateTime()));
                row.createCell(7).setCellValue(c.getClosingDateTime() != null ? (formatDateTime(c.getClosingDateTime())) : "-");
            });

            // Produtos por Comanda
            Sheet cpSheet = workbook.createSheet("Produtos por Comanda");
            String[] cpHeaders = {"ID", "ComandaID", "Usuário da Comanda", "Funcionário", "Produto", "Quantidade", "Valor"};
            preencherSheetComDados(cpSheet, cpHeaders, headerStyle, commandProducts, (row, cp) -> {
                row.createCell(0).setCellValue(cp.getId());
                row.createCell(1).setCellValue(cp.getCommand() != null ? cp.getCommand().getId() : null);
                row.createCell(2).setCellValue(cp.getCommand().getClient() != null ? "Cliente: " + cp.getCommand().getClient().getName() : "Funcionário: " + cp.getCommand().getEmployee().getName());
                row.createCell(3).setCellValue(cp.getCommand() != null ? cp.getCommand().getEmployee().getName() : "-");
                row.createCell(4).setCellValue(cp.getProduct() != null ? cp.getProduct().getName() : "-");
                row.createCell(5).setCellValue(cp.getProductQuantity());
                row.createCell(6).setCellValue(cp.getProductQuantity() * cp.getUnitValue());
            });

            // Serviços
            Sheet jobSheet = workbook.createSheet("Serviços");
            String[] jobHeaders = {"ID", "Tipo", "Categoria", "Cliente", "Título", "Valor Total", "Status"};
            preencherSheetComDados(jobSheet, jobHeaders, headerStyle, jobs, (row, j) -> {
                row.createCell(0).setCellValue(j.getId().toString());
                row.createCell(1).setCellValue(traduzTipoCliente(j.getServiceType().toString()));
                row.createCell(2).setCellValue(traduzCategoria(j.getCategory().toString()));
                row.createCell(3).setCellValue(j.getClient().getName());
                row.createCell(4).setCellValue(j.getTitle());
                row.createCell(5).setCellValue(j.getTotalValue());
                row.createCell(6).setCellValue(traduzStatus(j.getStatus().toString()));
            });

            // Subserviços
            Sheet subJobSheet = workbook.createSheet("Subserviços");
            String[] subJobHeaders = {"ID", "ServiçoID", "Cliente", "Título", "Valor Total", "Data", "Usou Sala?", "Status"};
            preencherSheetComDados(subJobSheet, subJobHeaders, headerStyle, subJobs, (row, sj) -> {
                row.createCell(0).setCellValue(sj.getId().toString());
                row.createCell(1).setCellValue(sj.getJob() != null ? sj.getJob().getId().toString() : "");
                row.createCell(2).setCellValue(sj.getJob().getClient().getName());
                row.createCell(3).setCellValue(sj.getTitle());
                row.createCell(4).setCellValue(sj.getValue());
                row.createCell(5).setCellValue(formatDate(sj.getDate()));
                row.createCell(6).setCellValue(traduzBoolean(sj.getNeedsRoom()));
                row.createCell(7).setCellValue(traduzStatus(sj.getStatus().toString()));
            });

            // Tarefas
            Sheet taskSheet = workbook.createSheet("Tarefas");
            String[] taskHeaders = {"ID", "Título", "Descrição", "Responsável", "Autor", "Data Inicio", "Data Limite", "Status"};
            preencherSheetComDados(taskSheet, taskHeaders, headerStyle, tasks, (row, t) -> {
                row.createCell(0).setCellValue(t.getId().toString());
                row.createCell(1).setCellValue(t.getTitle());
                row.createCell(2).setCellValue(t.getDescription());
                row.createCell(3).setCellValue(t.getEmployee().getName());
                row.createCell(4).setCellValue(t.getAssign().getName());
                row.createCell(5).setCellValue(formatDate(t.getStartDate()));
                row.createCell(6).setCellValue(t.getLimitDate() != null ? formatDate(t.getLimitDate()) : "-");
                row.createCell(7).setCellValue(traduzStatus(t.getStatus().toString()));
            });

            workbook.write(fos);
            return arquivoXLSX;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar relatório Excel", e);
        }
    }

    // Método genérico para preencher sheets
    private <T> void preencherSheetComDados(Sheet sheet, String[] headers, CellStyle headerStyle, List<T> data, BiConsumer<Row, T> rowFiller) {
        Row header = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowIndex = 1;
        for (T item : data) {
            Row row = sheet.createRow(rowIndex++);
            rowFiller.accept(row, item);
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "";
    }

    private String traduzBoolean(Boolean valor) {
        return valor != null && valor ? "Sim" : "Não";
    }

    private String traduzTipoCliente(String tipo) {
        return switch (tipo) {
            case "MONTHLY" -> "Mensal";
            case "SINGLE" -> "Avulso";
            default -> tipo;
        };
    }

    private String traduzStatus(String status) {
        return switch (status) {
            case "OPEN" -> "Aberto";
            case "CLOSED" -> "Fechado";
            case "PENDING" -> "Pendente";
            case "WORKING" -> "Em progresso";
            case "CANCELLED" -> "Cancelado";
            case "COMPLETED" -> "Finalizado";
            default -> status;
        };
    }

    private String traduzCategoria(String categoria) {
        return switch (categoria) {
            case"PODCAST" -> "Podcast";
            case "PHOTO_VIDEO_STUDIO" -> "Estúdio Fotográfico";
            case "MUSIC_REHEARSAL" -> "Ensaio Musical";
            default -> categoria;
        };
    }
}
