package org.zipcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zipcode.dto.Address;
import org.zipcode.service.AddressService;

@RestController
@RequestMapping("/v1/zip-codes")
public class ZipCodeController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/{zipCode}", method = RequestMethod.GET)
    public ResponseEntity<Address> get(@PathVariable final String zipCode) {
        return ResponseEntity.ok(addressService.findAddressByZipCode(zipCode));
    }
}
