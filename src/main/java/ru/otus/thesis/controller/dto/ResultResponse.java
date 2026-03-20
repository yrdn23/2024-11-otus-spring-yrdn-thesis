package ru.otus.thesis.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResultResponse {

    public static final ResultResponse OK = new ResultResponse(0, "OK");

    @JsonProperty("code")
    @Schema(description = "Код результата выполнения операции")
    private int code;

    @JsonProperty("message")
    @Schema(description = "Текст результата выполнения операции")
    private String message;

}
