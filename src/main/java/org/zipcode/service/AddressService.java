package org.zipcode.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.zipcode.dto.Address;
import org.zipcode.integration.postmon.AddressPostmon;
import org.zipcode.integration.postmon.PostmonIntegration;
import org.zipcode.integration.viacep.AddressViaCep;
import org.zipcode.integration.viacep.ViaCepIntegration;

@Service
@Log4j
public class AddressService {

    @Autowired
    private ViaCepIntegration viaCepIntegration;

    @Autowired
    private PostmonIntegration postmonIntegration;

    @HystrixCommand(fallbackMethod = "findAddressByZipCodeFallback")
    public Address findAddressByZipCode(final String zipCode) {
        log.info(">>> ViaCep Integration");

        final AddressViaCep addressViaCep = viaCepIntegration.findAddresByZipCode(zipCode);

        log.info(addressViaCep);

        return Address.builder()
                .complement(addressViaCep.getComplement())
                .neighborhood(addressViaCep.getNeighborhood())
                .state(addressViaCep.getState())
                .street(addressViaCep.getStreet())
                .zipCode(addressViaCep.getZipCode())
                .city(addressViaCep.getCity())
                .build();
    }

    public Address findAddressByZipCodeFallback(final String zipCode) {
        log.info(">>> Postmon Integration");

        final AddressPostmon addressPostmon = postmonIntegration.findAddresByZipCode(zipCode);

        log.info(addressPostmon);

        return Address.builder()
                .neighborhood(addressPostmon.getNeighborhood())
                .state(addressPostmon.getState())
                .street(addressPostmon.getStreet())
                .zipCode(addressPostmon.getZipCode())
                .city(addressPostmon.getCity())
                .build();
    }
}
