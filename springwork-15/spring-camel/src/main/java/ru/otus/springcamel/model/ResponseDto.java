package ru.otus.springcamel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto {

    @JsonProperty("source")
    private String source;

    @JsonProperty("result")
    private String result;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("fias_id")
    private String fias;
}
