package com.studiozero.projeto.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final DashboardService dashboardService;

    public File gerarRelatorioCSV() {
        Map<String, Double> atendimentos = dashboardService.getFrequencys();
        Map<String, Double> ativos = dashboardService.getActives();
        Map<String, Double> balancos = dashboardService.getBalances();
        LocalDate dataMaisRecente = dashboardService.getRecentTime();

        // Formatador de data PT-BR
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withLocale(new Locale("pt", "BR"));
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .withLocale(new Locale("pt", "BR"));

        String dataGeracao = LocalDateTime.now().format(formatterDataHora);
        String nomeArquivo = "Relatório Gerado em - " + dataGeracao.replace(":", "-").replace("/", "-") + ".csv";
        String tempDir = System.getProperty("java.io.tmpdir");
        File arquivoCSV = new File(tempDir, nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV))) {
            writer.write("Relatório Studio Zero - Gerado em: " + dataGeracao);
            writer.newLine();
            writer.newLine();

            // Atendimentos por Tipo de Cliente
            writer.write("Atendimentos por Tipo de Cliente");
            writer.newLine();
            writer.newLine();
            writer.write("Tipo;Quantidade");
            writer.newLine();
            writer.write("Atendimentos Por Mensais;" + atendimentos.getOrDefault("frequencyMonthly", null));
            writer.newLine();
            writer.write("Atendimentos Por Avulsos;" + atendimentos.getOrDefault("frequencySingle", null));
            writer.newLine();
            writer.newLine();

            // Atendimentos por Serviço
            writer.write("Atendimentos por Serviço");
            writer.newLine();
            writer.newLine();
            writer.write("Tipo;Quantidade");
            writer.newLine();
            writer.write("Podcast;" + atendimentos.getOrDefault("frequencyByPc", null));
            writer.newLine();
            writer.write("Estúdio Fotográfico;" + atendimentos.getOrDefault("frequencyByPv", null));
            writer.newLine();
            writer.write("Estúdio Musical;" + atendimentos.getOrDefault("frequencyByMr", null));
            writer.newLine();
            writer.newLine();

            // Clientes Ativos
            writer.write("Clientes Ativos");
            writer.newLine();
            writer.newLine();
            writer.write("Tipo;Quantidade");
            writer.newLine();
            writer.write("Mensal;" + ativos.getOrDefault("monthly", null));
            writer.newLine();
            writer.write("Avulso;" + ativos.getOrDefault("single", null));
            writer.newLine();
            writer.newLine();

            // Balanço por Categoria
            writer.write("Entrada Financeira por Categoria de Serviço (R$)");
            writer.newLine();
            writer.newLine();
            writer.write("Categoria;Valor Total");
            writer.newLine();
            writer.write("Podcast;" + balancos.getOrDefault("podcastBalance", null));
            writer.newLine();
            writer.write("Estúdio Fotográfico;" + balancos.getOrDefault("photoVideoStudioBalance", null));
            writer.newLine();
            writer.write("Estúdio Musical;" + balancos.getOrDefault("musicRehearsalBalance", null));
            writer.newLine();
            writer.newLine();

            // Data de Atividade Mais Recente
            writer.write("Data de Atividade Mais Recente");
            writer.newLine();
            writer.newLine();
            writer.write("Data;" + (dataMaisRecente != null ? dataMaisRecente.format(formatterData) : "N/A"));
            writer.newLine();

            System.out.println("CSV gerado em: " + arquivoCSV.getAbsolutePath());
            return arquivoCSV;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar CSV de relatório", e);
        }
    }

}
