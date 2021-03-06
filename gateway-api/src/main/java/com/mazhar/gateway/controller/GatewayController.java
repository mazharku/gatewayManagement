/**
 * 
 */
package com.mazhar.gateway.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazhar.gateway.exception.ResourceNotFoundException;
import com.mazhar.gateway.model.Gateway;
import com.mazhar.gateway.model.Peripheral;
import com.mazhar.gateway.repository.GatewayRepository;
import com.mazhar.gateway.util.GatewayUtil;

/**
 * @author mazhar
 *
 *         Dec 12, 2020
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class GatewayController {
	
	
	@Autowired
	private GatewayRepository repository;
	
	@GetMapping("/gateways")
	public List<Gateway> getAllGateways() {
		return repository.findAll();
	}

	@GetMapping("/gateway/{id}")
	public ResponseEntity<Gateway> getGatewayById(@PathVariable(value = "id") String id)
			throws ResourceNotFoundException {
		Gateway gateway = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + id));
		return ResponseEntity.ok().body(gateway);
	}

	@PostMapping(path = "/gateway", consumes = "application/json", produces = "application/json")
	public Gateway createGateway(@Validated @RequestBody Gateway gateway) throws ResourceNotFoundException, ValidationException {
		String ipAddress = gateway.getIpAddress();
		List<Peripheral> devices = gateway.getPeripherals();
		if(devices==null) {
			devices = new ArrayList<>();
		}
		if (devices != null && !devices.isEmpty()) {
             for (Peripheral device : devices) {
            	 Date date = device.getCreateDate();
            	 if(date==null) {
            		continue;
            	 }
            	 if(!GatewayUtil.isValidDateFormat(date.toString())) {
            		 throw new ValidationException("please provide a valid date format like yyyy-mm-dd");
            	 }
             }
		}
		if (devices.size() >= 10) {
			throw new ResourceNotFoundException("no more than 10 peripheral devices are allowed for a gateway.");
		}

		if (!GatewayUtil.isValidIPAddress(ipAddress)) {
			throw new ResourceNotFoundException("Please provide a valid ip address!");
		}

		return repository.save(gateway);
	}

	@PutMapping("/gateway/{id}")
	public ResponseEntity<Gateway> updateGateway(@PathVariable(value = "id") String gatewayId,
			@Validated @RequestBody Gateway gatewayDetails) throws ResourceNotFoundException {
		Gateway gateway = repository.findById(gatewayId)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + gatewayId));
		gateway.setDeleted(gatewayDetails.isDeleted());
		gateway.setGateayName(gatewayDetails.getGateayName());
		if (!GatewayUtil.isValidIPAddress(gatewayDetails.getIpAddress())) {
			throw new ResourceNotFoundException("Please provide a valid ip address!");
		}
		gateway.setIpAddress(gatewayDetails.getIpAddress());
		gateway.setPeripherals(gatewayDetails.getPeripherals());
		final Gateway updatedgateWay = repository.save(gateway);
		return ResponseEntity.ok(updatedgateWay);
	}

	@DeleteMapping("/gateway/{id}")
	public Map<String, Boolean> deleteGateway(@PathVariable(value = "id") String id)
			throws ResourceNotFoundException {
		Gateway gateway = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + id));

		repository.delete(gateway);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}