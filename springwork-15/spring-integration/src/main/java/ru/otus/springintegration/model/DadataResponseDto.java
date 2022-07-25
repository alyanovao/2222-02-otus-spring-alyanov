package ru.otus.springintegration.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DadataResponseDto {

    @SerializedName("source")
    private String source;

    @SerializedName("result")
    private String result;

    @SerializedName("postal_code")
    private String postalCode;

    @SerializedName("fias_id")
    private String fiasId;

}
