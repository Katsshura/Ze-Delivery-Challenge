package com.katsshura.zechallenge.api.controllers;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.models.GeoInformation;
import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.zechallenge.api.viewModels.PartnerModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerDataManagement partnerManagement;

    public PartnerController(PartnerDataManagement partnerManagement) {
        this.partnerManagement = partnerManagement;
    }

    @RequestMapping(value = "/create/single", method = RequestMethod.POST)
    private ResponseEntity createPartner(@RequestBody PartnerModel body) {
        try {
            var partner = toPartner(body);
            var result = partnerManagement.save(partner);
            return new ResponseEntity("Partner saved with success", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/create/multiple")
    private ResponseEntity createPartner(@RequestBody List<PartnerModel> body) {
        try {
            var partners = body.stream().map(model -> toPartner(model))
                    .collect(Collectors.toList());

            partners.forEach(partner -> partnerManagement.save(partner));

            return new ResponseEntity("All partners saved with success", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Partner toPartner(PartnerModel model) {
        var coverageArea = new GeoInformation<>(model.getCoverageArea().getType(), model.getCoverageArea().getCoordinates());
        var address = new GeoInformation<>(model.getAddress().getType(), model.getAddress().getCoordinates());
        return new Partner(
                model.getId(),
                model.getTradingName(),
                model.getOwnerName(),
                model.getDocument(),
                coverageArea,
                address
        );
    }
}
