package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.GoalRequestDTO;
import com.studiozero.projeto.dtos.response.GoalResponseDTO;
import com.studiozero.projeto.entities.Goal;
import com.studiozero.projeto.mappers.GoalMapper;
import com.studiozero.projeto.services.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @PostMapping()
    public ResponseEntity<GoalResponseDTO> createGoal(@Valid @RequestBody GoalRequestDTO dto) {

        Goal goal = GoalMapper.toEntity(dto);
        goalService.createGoal(goal);

        return ResponseEntity.ok(GoalMapper.toDTO(goal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalResponseDTO> getGoalById(@PathVariable Integer id) {
        Goal goal = goalService.findGoalById(id);
        return ResponseEntity.ok(GoalMapper.toDTO(goal));
    }

    @GetMapping
    public ResponseEntity<List<GoalResponseDTO>> listGoals() {
        List<Goal> goals = goalService.listGoals();
        List<GoalResponseDTO> goalsDtos = GoalMapper.toListDtos(goals);
        return ResponseEntity.ok(goalsDtos);
    }

    @GetMapping("/recent")
    public ResponseEntity<GoalResponseDTO> getMostRecentGoal() {
        Goal goal = goalService.findMostRecentGoal();
        return ResponseEntity.ok(GoalMapper.toDTO(goal));
    }

    @PutMapping
    public ResponseEntity<GoalResponseDTO> updateGoal(@Valid @RequestBody Goal goal) {
        Goal updatedGoal = goalService.updateGoal(goal);
        return ResponseEntity.ok(GoalMapper.toDTO(updatedGoal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Integer id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}

