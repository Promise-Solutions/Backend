package Projeto.Studio.Zero.StudioZero.web.dtos.request;

import jakarta.validation.constraints.NotNull;

public class UserDeleteRequestDTO {
    @NotNull
    private Long id;

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }
}
