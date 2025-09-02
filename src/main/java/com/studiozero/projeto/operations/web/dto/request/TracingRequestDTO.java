package com.studiozero.projeto.operations.web.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.shared.application.enums.Context;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TracingRequestDTO {
    private Context context;

    public TracingRequestDTO() {
    }

    public TracingRequestDTO(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
