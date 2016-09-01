package org.zipcode.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String zipCode;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
}
