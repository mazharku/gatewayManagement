/**
 * 
 */
package com.mazhar.gateway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.mazhar.gateway.repository.GateWayRepository;

/**
 * @author mazhar
 *
 *         Dec 12, 2020
 */
@RestController
@RequestMapping("/api/v1")
public class GateWayController {
	
	
	@Autowired
	private GateWayRepository repository;

	@GetMapping("/gateways")
	public List<Gateway> getAllGateways() {
		return repository.findAll();
	}

	@GetMapping("/gateway/{id}")
	public ResponseEntity<Gateway> getGatewayById(@PathVariable(value = "id") String employeeId)
			throws ResourceNotFoundException {
		Gateway gateway = repository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(gateway);
	}

	@PostMapping(path = "/gateway", consumes = "application/json", produces = "application/json")
	public Gateway createGateway(@Validated @RequestBody Gateway gateway) throws ResourceNotFoundException {
	    String ipAddress = gateway.getIpAddress();
	    List<Peripheral> devices = gateway.getPeripherals();
	    if (devices!=null && !devices.isEmpty()) {
	    	
	    }
	    if (devices.size()>=10) {
	    	throw new ResourceNotFoundException("no more than 10 peripheral devices are allowed for a gateway.");
	    }
	    if (!isValidIPAddress(ipAddress)) {
	    	throw new ResourceNotFoundException("Please provide a valid ip address!");
	    }
		return repository.save(gateway);
	}

	@PutMapping("/gateway/{id}")
	public ResponseEntity<Gateway> updateGateway(@PathVariable(value = "id") String gatewayId,
			@Validated @RequestBody Gateway gatewayDetails) throws ResourceNotFoundException {
		Gateway gateway = repository.findById(gatewayId)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + gatewayId));
		 //gateway.setSerialNo(gatewayDetails.getSerialNo());
         gateway.setDeleted(gatewayDetails.isDeleted());
         gateway.setGateayName(gatewayDetails.getGateayName());
		/*
		 * if(!isValidIPAddress(gatewayDetails.getIpAddress())){ throw new
		 * ResourceNotFoundException("Please provide a valid ip address!"); }
		 */
         gateway.setIpAddress(gatewayDetails.getIpAddress());
         gateway.setPeripherals(gatewayDetails.getPeripherals());
		final Gateway updatedgateWay = repository.save(gateway);
		return ResponseEntity.ok(updatedgateWay);
	}

	@DeleteMapping("/gateway/{id}")
	public Map<String, Boolean> deleteGateway(@PathVariable(value = "id") String employeeId)
			throws ResourceNotFoundException {
		Gateway gateway = repository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + employeeId));

		repository.delete(gateway);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	private boolean isValidIPAddress(String IPAddress) {
		String ipValidationRegex = "'\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b'";
		Pattern p = Pattern.compile(ipValidationRegex);
		if (IPAddress == null) { 
            return false; 
        } 
        Matcher m = p.matcher(IPAddress); 
        return m.matches(); 
		
	}
}