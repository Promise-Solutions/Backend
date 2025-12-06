package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.application.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class SubJobRequestDTO {

    @NotBlank(message = "Title value is mandatory")
    private String title;

    @NotBlank(message = "Description value is mandatory")
    private String description;

    @NotNull(message = "Value is mandatory")
    @PositiveOrZero
    private Double value;

    private LocalDate date;

    private LocalTime startTime;

    @NotNull(message = "Needs Room value is mandatory")
    private Boolean needsRoom;

    private LocalTime expectedEndTime;

    @NotNull(message = "Status value is mandatory")
    private Status status;

    @NotNull(message = "fkService value is mandatory")
    private UUID fkService;

    public SubJobRequestDTO() {
    }

    public SubJobRequestDTO(String title, String description, Double value, LocalDate date, LocalTime startTime, Boolean needsRoom, LocalTime expectedEndTime, Status status, UUID fkService) {
        this.title = title;
        this.description = description;
        this.value = value;
        this.date = date;
        this.startTime = startTime;
        this.needsRoom = needsRoom;
        this.expectedEndTime = expectedEndTime;
        this.status = status;
        this.fkService = fkService;
    }

    public @NotBlank(message = "Title value is mandatory") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title value is mandatory") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Description value is mandatory") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description value is mandatory") String description) {
        this.description = description;
    }

    public @NotNull(message = "Value is mandatory") @PositiveOrZero Double getValue() {
        return value;
    }

    public void setValue(@NotNull(message = "Value is mandatory") @PositiveOrZero Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public @NotNull(message = "Needs Room value is mandatory") Boolean getNeedsRoom() {
        return needsRoom;
    }

    public void setNeedsRoom(@NotNull(message = "Needs Room value is mandatory") Boolean needsRoom) {
        this.needsRoom = needsRoom;
    }

    public LocalTime getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedEndTime(LocalTime expectedEndTime) {
        this.expectedEndTime = expectedEndTime;
    }

    public @NotNull(message = "Status value is mandatory") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status value is mandatory") Status status) {
        this.status = status;
    }

    public @NotNull(message = "fkService value is mandatory") UUID getFkService() {
        return fkService;
    }

    public void setFkService(@NotNull(message = "fkService value is mandatory") UUID fkService) {
        this.fkService = fkService;
    }
}
