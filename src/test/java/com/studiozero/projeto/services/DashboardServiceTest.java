package com.studiozero.projeto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.studiozero.projeto.application.services.DashboardService;
import com.studiozero.projeto.application.services.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.*;

class DashboardServiceTest {

    @Mock private CommandRepository commandRepository;
    @Mock private SubJobRepository subJobRepository;
    @Mock private ClientRepository clientRepository;
    @Mock private JobRepository jobRepository;
    @Mock private TracingRepository tracingRepository;
    @Mock private ExpenseRepository expenseRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should calculate client stats correctly")
    void getClientStats_shouldReturnCorrectStats() {
        UUID clientId = UUID.randomUUID();

        Job job = new Job();
        Client client = new Client();
        client.setId(clientId);
        job.setClient(client);

        SubJob subJob1 = new SubJob();
        subJob1.setJob(job);
        subJob1.setStatus(Status.CLOSED);
        subJob1.setNeedsRoom(true);
        subJob1.setValue(100.0);

        SubJob subJob2 = new SubJob();
        subJob2.setJob(job);
        subJob2.setStatus(Status.CLOSED);
        subJob2.setNeedsRoom(true);
        subJob2.setValue(200.0);

        List<SubJob> subJobs = List.of(subJob1, subJob2);

        when(subJobRepository.findAll()).thenReturn(subJobs);

        Command closedCommand = new Command();
        closedCommand.setClient(client);
        closedCommand.setStatus(Status.CLOSED);
        closedCommand.setTotalValue(150.0);

        List<Command> closedCommands = List.of(closedCommand);

        when(commandRepository.findAllByClient_IdAndStatus(clientId, Status.CLOSED)).thenReturn(closedCommands);

        Map<String, Double> stats = dashboardService.getClientStats(clientId);

        assertEquals(2.0, stats.get("frequency"));
        assertEquals(300.0, stats.get("totalValue"));
        assertEquals(150.0, stats.get("totalCommandsValue"));
        assertEquals((300.0 + 150.0) / 2.0, stats.get("ticket"));
    }

    @Test
    @DisplayName("Should return correct frequencies")
    void getFrequencys_shouldReturnCorrectFrequencies() {
        Client client = new Client();

        Job jobSingle = new Job();
        jobSingle.setServiceType(JobType.SINGLE);
        jobSingle.setCategory(JobCategory.PODCAST);

        Job jobMonthly = new Job();
        jobMonthly.setServiceType(JobType.MONTHLY);
        jobMonthly.setCategory(JobCategory.MUSIC_REHEARSAL);

        SubJob subJob1 = new SubJob();
        subJob1.setJob(jobSingle);
        subJob1.setStatus(Status.CLOSED);
        subJob1.setNeedsRoom(true);

        SubJob subJob2 = new SubJob();
        subJob2.setJob(jobMonthly);
        subJob2.setStatus(Status.CLOSED);
        subJob2.setNeedsRoom(true);

        SubJob subJob3 = new SubJob();
        subJob3.setJob(jobSingle);
        subJob3.setStatus(Status.CLOSED);
        subJob3.setNeedsRoom(true);

        List<SubJob> subJobs = List.of(subJob1, subJob2, subJob3);

        when(subJobRepository.findAll()).thenReturn(subJobs);

        Map<String, Double> frequencies = dashboardService.getFrequencys();

        assertEquals(2.0, frequencies.get("frequencySingle"));
        assertEquals(1.0, frequencies.get("frequencyMonthly"));
        assertEquals(2.0, frequencies.get("frequencyByPc"));
        assertEquals(1.0, frequencies.get("frequencyByMr"));
        assertEquals(0.0, frequencies.get("frequencyByPv"));
    }

    @Test
    @DisplayName("Should return correct active client counts")
    void getActives_shouldReturnActiveClients() {
        when(clientRepository.countByActiveTrueAndClientType(ClientType.SINGLE)).thenReturn(10.0);
        when(clientRepository.countByActiveTrueAndClientType(ClientType.MONTHLY)).thenReturn(5.0);

        Map<String, Double> actives = dashboardService.getActives();

        assertEquals(5.0, actives.get("monthly"));
        assertEquals(10.0, actives.get("single"));
    }

    @Test
    @DisplayName("Should return correct bar finances")
    void getBarFinances_shouldReturnCorrectValues() {
        Command closedCommand1 = new Command();
        closedCommand1.setStatus(Status.CLOSED);
        closedCommand1.setTotalValue(200.0);

        Command closedCommand2 = new Command();
        closedCommand2.setStatus(Status.CLOSED);
        closedCommand2.setTotalValue(100.0);

        Command openCommand = new Command();
        openCommand.setStatus(Status.OPEN);
        openCommand.setTotalValue(50.0);

        List<Command> commands = List.of(closedCommand1, closedCommand2, openCommand);

        Expense expense1 = new Expense();
        expense1.setExpenseCategory(ExpenseCategory.STOCK);
        expense1.setAmountSpend(80.0);

        Expense expense2 = new Expense();
        expense2.setExpenseCategory(ExpenseCategory.OTHERS);
        expense2.setAmountSpend(30.0);

        List<Expense> expenses = List.of(expense1, expense2);

        when(commandRepository.findAll()).thenReturn(commands);
        when(expenseRepository.findAll()).thenReturn(expenses);

        Map<String, Double> finances = dashboardService.getBarFinances();

        assertEquals(300.0, finances.get("totalEntryValue"));
        assertEquals(80.0, finances.get("totalExpenseValue"));
        assertEquals(220.0, finances.get("profitOrLoss"));
    }

    @Test
    @DisplayName("Should return correct category balances")
    void getCategoryBalances_shouldReturnCorrectBalances() {
        when(jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.PODCAST, Status.CLOSED)).thenReturn(100.0);
        when(jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.PHOTO_VIDEO_STUDIO, Status.CLOSED)).thenReturn(200.0);
        when(jobRepository.sumTotalValueByCategoryAndStatus(JobCategory.MUSIC_REHEARSAL, Status.CLOSED)).thenReturn(null);

        Map<String, Double> balances = dashboardService.getCategoryBalances();

        assertEquals(100.0, balances.get("podcastBalance"));
        assertEquals(200.0, balances.get("photoVideoStudioBalance"));
        assertEquals(0.0, balances.get("musicRehearsalBalance"));
    }

    @Test
    @DisplayName("Should return correct balances summary")
    void getBalances_shouldReturnCorrectSummary() {
        Command closedCommand = new Command();
        closedCommand.setStatus(Status.CLOSED);
        closedCommand.setTotalValue(150.0);

        Command openCommand = new Command();
        openCommand.setStatus(Status.OPEN);
        openCommand.setTotalValue(50.0);

        Job closedJob = new Job();
        closedJob.setStatus(Status.CLOSED);
        closedJob.setTotalValue(100.0);

        Job openJob = new Job();
        openJob.setStatus(Status.OPEN);
        openJob.setTotalValue(30.0);

        Expense expense1 = new Expense();
        expense1.setAmountSpend(40.0);

        Expense expense2 = new Expense();
        expense2.setAmountSpend(20.0);

        when(commandRepository.findAll()).thenReturn(List.of(closedCommand, openCommand));
        when(jobRepository.findAll()).thenReturn(List.of(closedJob, openJob));
        when(expenseRepository.findAll()).thenReturn(List.of(expense1, expense2));

        Map<String, Double> balances = dashboardService.getBalances();

        assertEquals(250.0, balances.get("totalEntryValue")); // 150 + 100
        assertEquals(60.0, balances.get("totalExpenseValue")); // 40 + 20
        assertEquals(190.0, balances.get("profitOrLoss")); // 250 - 60
    }

    @Test
    @DisplayName("Should return the most recent tracing date time")
    void getRecentTime_shouldReturnLatestDateTime() {
        LocalDateTime now = LocalDateTime.now();
        Tracing tracing = new Tracing();
        tracing.setDateTime(now);

        when(tracingRepository.findTopByOrderByDateTimeDesc()).thenReturn(tracing);

        LocalDateTime result = dashboardService.getRecentTime();

        assertEquals(now, result);
    }
}
