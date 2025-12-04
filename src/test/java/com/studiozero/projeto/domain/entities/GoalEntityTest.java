package com.studiozero.projeto.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalEntityTest {
    @Test
    void testGoalCreation() {
        Goal g = new Goal(1, 100.0);
        assertEquals(1, g.getId());
        assertEquals(100.0, g.getGoal());
    }

    @Test
    void testGoalInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Goal(1, 0.0));
    }
}
