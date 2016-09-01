package org.zipcode.integration.postmon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressPostmon {
    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("logradouro")
    private String street;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    private String state;

}
