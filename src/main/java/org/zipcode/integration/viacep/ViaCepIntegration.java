package org.zipcode.integration.viacep;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zipcode.integration.postmon.AddressPostmon;

public interface ViaCepIntegration {
    @RequestLine("GET /{zipCode}/json")
    AddressViaCep findAddresByZipCode(@Param("zipCode") String zipCode);
}
