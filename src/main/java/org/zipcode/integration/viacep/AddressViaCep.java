package org.zipcode.integration.viacep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressViaCep {
    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("logradouro")
    private String street;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("localidade")
    private String city;

    @JsonProperty("uf")
    private String state;
}
