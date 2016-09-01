package org.zipcode.integration.postmon;

import feign.Param;
import feign.RequestLine;

public interface PostmonIntegration {
    @RequestLine("GET /v1/cep/{zipCode}")
    AddressPostmon findAddresByZipCode(@Param("zipCode") String zipCode);
}
