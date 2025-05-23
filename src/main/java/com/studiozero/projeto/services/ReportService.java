package com.studiozero.projeto.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final DashboardService dashboardService;

    public File gerarRelatorioCSV() {
        Map<String, Double> frequencias = dashboardService.getFrequencys();
        Map<String, Double> ativos = dashboardService.getActives();

        // Formatador de data PT-BR
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withLocale(new Locale("pt", "BR"));
        String dataFormatada = LocalDateTime.now().format(formatter);

        String nomeArquivo = "Relatório Gerado em - " + dataFormatada.replace(":", "-").replace("/", "-") + ".csv";
        String tempDir = System.getProperty("java.io.tmpdir");
        File arquivoCSV = new File(tempDir, nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV))) {
            writer.write("Relatório Studio Zero - Gerado em: " + dataFormatada);
            writer.newLine();
            writer.newLine();

            writer.write("Atendimentos por Tipo de Cliente");
            writer.newLine();
            writer.newLine();
            writer.write("Tipo;Quantidade");
            writer.newLine();
            writer.write("Atendimentos Por Mensais;" + frequencias.getOrDefault("frequencyMonthly", null));
            writer.newLine();
            writer.write("Atendimentos Por Avulsos;" + frequencias.getOrDefault("frequencySingle", null));
            writer.newLine();

            writer.newLine();
            writer.write("Atendimentos por Serviço");
            writer.newLine();
            writer.newLine();
            writer.write("Tipo;Quantidade");
            writer.newLine();
            writer.write("Podcast;" + frequencias.getOrDefault("frequencyByPc", null));
            writer.newLine();
            writer.write("Estúdio Fotográfico;" + frequencias.getOrDefault("frequencyByPv", null));
            writer.newLine();
            writer.write("Estúdio Musical;" + frequencias.getOrDefault("frequencyByMr", null));
            writer.newLine();

            writer.newLine();
            writer.write("Clientes Ativos");
            writer.newLine();
            writer.newLine();
            writer.write("Tipo;Quantidade");
            writer.newLine();
            writer.write("Mensal;" + ativos.getOrDefault("monthly", null));
            writer.newLine();
            writer.write("Avulso;" + ativos.getOrDefault("single", null));
            writer.newLine();

            System.out.println("CSV gerado em: " + arquivoCSV.getAbsolutePath());
            return arquivoCSV;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar CSV de relatório", e);
        }
    }

}
