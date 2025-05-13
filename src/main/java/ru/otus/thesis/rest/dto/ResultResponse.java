package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private int code;

    @JsonProperty("message")
    private String message;

}
