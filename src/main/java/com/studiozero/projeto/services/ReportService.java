package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.*;
import com.studiozero.projeto.repositories.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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
    private final ExpenseRepository expenseRepository;
    private final DriveService driveService;

    public File gerarRelatorioExcel() {
        List<Client> clients = clientRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();
        List<Command> commands = commandRepository.findAll();
        List<Job> jobs = jobRepository.findAll();
        List<SubJob> subJobs = subJobRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Task> tasks = taskRepository.findAll();
        List<CommandProduct> commandProducts = commandProductRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();

        // Use '-' para o nome do arquivo para evitar problemas de path
        String dataGeracao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        File arquivoXLSX = new File(System.getProperty("java.io.tmpdir"),
                "Relatório Gerado em - " + dataGeracao + ".xlsx");

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

            // FINANÇAS
            Sheet financesSheet = workbook.createSheet("Finanças");
            String[] financesHeaders = { "Entrada", "Saída", "Lucro ou Perda" };
            // Calcula os valores conforme DashboardService.getBalances()
            double totalCommandEntryValue = commands
                    .stream()
                    .filter(command -> "CLOSED".equals(command.getStatus().toString()))
                    .mapToDouble(Command::getTotalValue)
                    .sum();

            double totalJobEntryValue = jobs
                    .stream()
                    .filter(job -> "CLOSED".equals(job.getStatus().toString()))
                    .mapToDouble(Job::getTotalValue)
                    .sum();

            double totalExpenseValue = expenses
                    .stream()
                    .mapToDouble(Expense::getAmountSpend)
                    .sum();

            double totalEntryValue = totalCommandEntryValue + totalJobEntryValue;
            double profitOrLoss = totalEntryValue - totalExpenseValue;

            // Formatação monetária
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            // Preenche a sheet de Finanças
            Row financesHeaderRow = financesSheet.createRow(0);
            for (int i = 0; i < financesHeaders.length; i++) {
                Cell cell = financesHeaderRow.createCell(i);
                cell.setCellValue(financesHeaders[i]);
                cell.setCellStyle(headerStyle);
            }
            Row financesRow = financesSheet.createRow(1);
            financesRow.createCell(0).setCellValue(currencyFormat.format(totalEntryValue));
            financesRow.createCell(1).setCellValue(currencyFormat.format(totalExpenseValue));
            financesRow.createCell(2).setCellValue(currencyFormat.format(profitOrLoss));
            for (int i = 0; i < financesHeaders.length; i++) {
                financesSheet.autoSizeColumn(i);
            }

            // DESPESAS
            Sheet expenseSheet = workbook.createSheet("Despesas");
            String[] expenseHeader = { "ID", "Data de Pagamento", "Tipo de Despesa", "Descrição", "Valor Pago",
                    "Produto", "Tipo de Pagamento" };
            preencherSheetComDados(expenseSheet, expenseHeader, headerStyle, expenses, (row, e) -> {
                row.createCell(0).setCellValue(e.getId().toString());
                row.createCell(1).setCellValue(formatDate(e.getDate()));
                row.createCell(2).setCellValue(traduzCategoria(e.getExpenseCategory().toString()));
                row.createCell(3).setCellValue(e.getDescription());
                row.createCell(4).setCellValue(e.getAmountSpend());
                row.createCell(5).setCellValue(e.getProduct().getName());
                row.createCell(6).setCellValue(traduzTipoPagamento(e.getPaymentType().toString()));
            });

            // SERVIÇOS
            Sheet jobSheet = workbook.createSheet("Serviços");
            String[] jobHeaders = { "ID", "Tipo", "Categoria", "Cliente", "Título", "Valor Total", "Status" };
            preencherSheetComDados(jobSheet, jobHeaders, headerStyle, jobs, (row, j) -> {
                row.createCell(0).setCellValue(j.getId().toString());
                row.createCell(1).setCellValue(traduzTipoCliente(j.getServiceType().toString()));
                row.createCell(2).setCellValue(traduzCategoria(j.getCategory().toString()));
                row.createCell(3).setCellValue(j.getClient().getName());
                row.createCell(4).setCellValue(j.getTitle());
                row.createCell(5).setCellValue(j.getTotalValue());
                row.createCell(6).setCellValue(j.getStatus().toString());
            });

            // SUBSERVIÇOS
            Sheet subJobSheet = workbook.createSheet("Sub-serviços");
            String[] subJobHeaders = { "ID", "ServiçoID", "Cliente", "Título", "Valor Total", "Data", "Usou Sala?",
                    "Status" };
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

            // COMANDAS
            Sheet cmdSheet = workbook.createSheet("Comandas");
            String[] cmdHeaders = { "ID", "Cliente", "Funcionário", "Status", "Desconto (%)", "Valor Total", "Abertura",
                    "Fechamento" };
            preencherSheetComDados(cmdSheet, cmdHeaders, headerStyle, commands, (row, c) -> {
                row.createCell(0).setCellValue(c.getId());
                row.createCell(1).setCellValue(
                        c.getClient() != null ? c.getClient().getName() : "Funcionário: " + c.getEmployee().getName());
                row.createCell(2).setCellValue(c.getEmployee() != null ? c.getEmployee().getName() : "-");
                row.createCell(3).setCellValue(traduzStatus(c.getStatus().toString()));
                // Desconto como número (ex: 10 para 10%)
                row.createCell(4).setCellValue(c.getDiscount());
                // Valor Total como número
                row.createCell(5).setCellValue(c.getTotalValue());
                row.createCell(6).setCellValue(formatDateTime(c.getOpeningDateTime()));
                row.createCell(7)
                        .setCellValue(c.getClosingDateTime() != null ? (formatDateTime(c.getClosingDateTime())) : "-");
            });

            // PRODUTOS
            Sheet prodSheet = workbook.createSheet("Produtos");
            String[] prodHeaders = { "ID", "Nome", "Quantidade", "Valor Cliente", "Valor Funcionário" };
            preencherSheetComDados(prodSheet, prodHeaders, headerStyle, products, (row, p) -> {
                row.createCell(0).setCellValue(p.getId());
                row.createCell(1).setCellValue(p.getName());
                row.createCell(2).setCellValue(p.getQuantity());
                row.createCell(3).setCellValue(p.getClientValue());
                row.createCell(4).setCellValue(p.getInternalValue());
            });

            // PRODUTOS POR COMANDA
            Sheet cpSheet = workbook.createSheet("Produtos por Comanda");
            String[] cpHeaders = { "ID", "ComandaID", "Usuário da Comanda", "Funcionário", "Produto", "Quantidade",
                    "Valor" };
            preencherSheetComDados(cpSheet, cpHeaders, headerStyle, commandProducts, (row, cp) -> {
                row.createCell(0).setCellValue(cp.getId());
                row.createCell(1).setCellValue(cp.getCommand() != null ? cp.getCommand().getId() : '-');
                row.createCell(2)
                        .setCellValue(cp.getCommand().getClient() != null
                                ? "Cliente: " + cp.getCommand().getClient().getName()
                                : "Funcionário: " + cp.getCommand().getEmployee().getName());
                row.createCell(3).setCellValue(cp.getCommand() != null ? cp.getCommand().getEmployee().getName() : "-");
                row.createCell(4).setCellValue(cp.getProduct() != null ? cp.getProduct().getName() : "-");
                row.createCell(5).setCellValue(cp.getProductQuantity());
                row.createCell(6).setCellValue(cp.getProductQuantity() * cp.getUnitValue());
            });

            // CLIENTES
            Sheet clientesSheet = workbook.createSheet("Clientes");
            String[] clienteHeaders = { "ID", "Nome", "CPF", "Email", "Contato", "Tipo", "Ativo", "Nascimento",
                    "Criação" };
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
            String[] empHeaders = { "ID", "Nome", "CPF", "Email", "Contato", "Ativo" };
            preencherSheetComDados(empSheet, empHeaders, headerStyle, employees, (row, e) -> {
                row.createCell(0).setCellValue(e.getId().toString());
                row.createCell(1).setCellValue(e.getName());
                row.createCell(2).setCellValue(e.getCpf());
                row.createCell(3).setCellValue(e.getEmail());
                row.createCell(4).setCellValue(e.getContact());
                row.createCell(5).setCellValue(traduzBoolean(e.getActive()));
            });

            // TAREFAS
            Sheet taskSheet = workbook.createSheet("Tarefas");
            String[] taskHeaders = { "ID", "Título", "Descrição", "Responsável", "Autor", "Data Inicio", "Data Limite",
                    "Status" };
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
            fos.flush();
            // Após gerar o arquivo, envia para o Google Drive sem MockMultipartFile
            enviarRelatorioParaDrive(arquivoXLSX);
            return arquivoXLSX;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar relatório Excel", e);
        }
    }

    private void enviarRelatorioParaDrive(File arquivo) {
        try (FileInputStream fis = new FileInputStream(arquivo)) {
            driveService.uploadFileStream(
                    arquivo.getName(),
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    fis);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar relatório para o Google Drive", e);
        }
    }

    // Método genérico para preencher sheets
    private <T> void preencherSheetComDados(Sheet sheet, String[] headers, CellStyle headerStyle, List<T> data,
            BiConsumer<Row, T> rowFiller) {
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
        // Retorna apenas a data no formato dd/MM/yyyy
        return dateTime != null ? dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
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
            case "PODCAST" -> "Podcast";
            case "PHOTO_VIDEO_STUDIO" -> "Estúdio Fotográfico";
            case "MUSIC_REHEARSAL" -> "Ensaio Musical";
            case "BILLS" -> "Contas";
            case "STOCK" -> "Estoque";
            case "MAINTENANCE" -> "Manutenção";
            case "OTHERS" -> "Outros";
            default -> categoria;
        };
    }

    private String traduzTipoPagamento(String tipo) {
        return switch (tipo) {
            case "CREDIT_CARD" -> "Cartão de Crédito";
            case "DEBIT_CARD" -> "Cartão de Débito";
            case "BILLET" -> "Boleto";
            case "MONEY" -> "Dinheiro";
            case "PIX" -> "Pix";
            case "TRANSFER" -> "Transferência Bancária";
            default -> tipo;
        };
    }
}
