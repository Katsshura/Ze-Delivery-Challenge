package com.katsshura.zechallenge.api.controllers;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.interfaces.PartnerServiceDefinition;
import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.zechallenge.api.viewModels.PartnerModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerDataManagement partnerManagement;
    private final PartnerServiceDefinition partnerService;

    public PartnerController(PartnerDataManagement partnerManagement, PartnerServiceDefinition partnerService) {
        this.partnerManagement = partnerManagement;
        this.partnerService = partnerService;
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


    @RequestMapping(value = "/create/multiple", method = RequestMethod.POST)
    private ResponseEntity createPartner(@RequestBody List<PartnerModel> body) {
        try {
            var partners = body.stream().map(this::toPartner)
                    .collect(Collectors.toList());

            partners.forEach(partnerManagement::save);

            return new ResponseEntity("All partners saved with success", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity findPartnerById(@PathVariable String id) {
        try {
            var partner = partnerManagement.find(id);
            return partner != null
                    ? new ResponseEntity(partner, HttpStatus.OK)
                    : new ResponseEntity(HttpStatus.NO_CONTENT);
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
