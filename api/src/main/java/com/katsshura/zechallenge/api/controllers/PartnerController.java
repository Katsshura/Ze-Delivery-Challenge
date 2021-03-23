package com.katsshura.zechallenge.api.controllers;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.interfaces.PartnerServiceDefinition;
import com.katsshura.zechallenge.api.util.ViewModelConversion;
import com.katsshura.zechallenge.api.viewModels.CoordinateModel;
import com.katsshura.zechallenge.api.viewModels.PartnerModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Partner saved with success"),
        @ApiResponse(code = 204, message = "We couldn't find any match for your request"),
        @ApiResponse(code = 400, message = "You must provide a valid body!"),
        @ApiResponse(code = 500, message = "Something went wrong with ours servers"),
})
@RestController
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerDataManagement partnerManagement;
    private final PartnerServiceDefinition partnerService;
    private final ViewModelConversion conversion;

    public PartnerController(PartnerDataManagement partnerManagement, PartnerServiceDefinition partnerService, ViewModelConversion conversion) {
        this.partnerManagement = partnerManagement;
        this.partnerService = partnerService;
        this.conversion = conversion;
    }

    @ApiOperation(value = "Create a partner on database.")
    @RequestMapping(value = "/create/single", method = RequestMethod.POST)
    private ResponseEntity createPartner(@RequestBody PartnerModel body) {
        try {
            var partner = conversion.toDomainPartner(body);
            var result = partnerManagement.save(partner);
            return new ResponseEntity("Partner saved with success", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Create multiple partners on database.")
    @RequestMapping(value = "/create/multiple", method = RequestMethod.POST)
    private ResponseEntity createPartner(@RequestBody List<PartnerModel> body) {
        try {
            var partners = body.stream().map(conversion::toDomainPartner)
                    .collect(Collectors.toList());

            partners.forEach(partnerManagement::save);

            return new ResponseEntity("All partners saved with success", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Returns a partner if found by Id on database, else returns NO_CONTENT")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity findPartnerById(@PathVariable String id) {
        try {
            var partner = partnerManagement.find(id);
            return partner != null
                    ? new ResponseEntity(conversion.toViewModelPartner(partner), HttpStatus.OK)
                    : new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search the nearest partner to the provided location")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    private ResponseEntity searchNearestPartner(@RequestBody CoordinateModel model) {
        try {
            var res = partnerService.getNearestPartnerBasedOnLocation(conversion.toDomainCoordinate(model));

            if (res == null) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity(res, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
