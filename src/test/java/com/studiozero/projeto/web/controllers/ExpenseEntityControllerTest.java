package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.expense.CreateExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.DeleteExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.GetExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.ListExpensesUseCase;
import com.studiozero.projeto.application.usecases.expense.UpdateExpenseUseCase;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExpenseEntityControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CreateExpenseUseCase create = new CreateExpenseUseCase(null) {
            @Override
            public Expense execute(Object dto) { return null; }
        };

        GetExpenseUseCase get = new GetExpenseUseCase(null) {
            @Override
            public Expense execute(Integer id) { throw new IllegalArgumentException("not found"); }
        };

        UpdateExpenseUseCase update = new UpdateExpenseUseCase(null) {
            @Override
            public Expense execute(Expense expense) { return expense; }
        };

        DeleteExpenseUseCase delete = new DeleteExpenseUseCase(null) {
            @Override
            public void execute(Integer id) { }
        };

        ListExpensesUseCase list = new ListExpensesUseCase(null) {
            @Override
            public org.springframework.data.domain.Page<Expense> execute(org.springframework.data.domain.Pageable pageable) { return new PageImpl<>(List.of()); }
        };

        GetProductUseCase getProd = new GetProductUseCase(null) {
            @Override
            public Product execute(Integer id) { return null; }
        };

        ExpenseController controller = new ExpenseController(create, get, update, delete, list, getProd);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListAllExpenses() throws Exception {
        mockMvc.perform(get("/api/expenses").param("page","0").param("size","10"))
                .andExpect(status().isNoContent());
    }
}
