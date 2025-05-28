package com.studiozero.projeto.controllers;

import com.studiozero.projeto.entities.Goal;
import com.studiozero.projeto.services.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable Integer id) {
        Goal goal = goalService.findGoalById(id);
        return ResponseEntity.ok(goal);
    }

    @GetMapping
    public ResponseEntity<List<Goal>> listGoals() {
        List<Goal> goals = goalService.listGoals();
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/recent")
    public ResponseEntity<Goal> getMostRecentGoal() {
        Goal goal = goalService.findMostRecentGoal();
        return ResponseEntity.ok(goal);
    }

    @PutMapping
    public ResponseEntity<Goal> updateGoal(@RequestBody Goal goal) {
        Goal updatedGoal = goalService.updateGoal(goal);
        return ResponseEntity.ok(updatedGoal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Integer id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}

