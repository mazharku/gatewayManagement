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
import com.mazhar.gateway.repository.PeripheralRepository;
import com.mazhar.gateway.util.GatewayUtil;

/**
 * @author mazhar
 *
 *         Dec 13, 2020
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PeripheralController {

	@Autowired
	private GatewayRepository gatewayRepo;

	@Autowired
	private PeripheralRepository peripheralRepo;

	@GetMapping("/gateway/{id}/devices")
	public List<Peripheral> getAllDeviceByGatewayId(@PathVariable(value = "id") String id)
			throws ResourceNotFoundException {
		Gateway gateway = gatewayRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + id));
		List<Peripheral> list = gateway.getPeripherals();
		return list;
	}

	@PostMapping(path = "/gateway/{id}/device", consumes = "application/json", produces = "application/json")
	public Peripheral createDevice(@PathVariable(value = "id") String id, @Validated @RequestBody Peripheral device) throws ResourceNotFoundException, ValidationException {
		Gateway gateway = gatewayRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + id));
		Date date = device.getCreateDate();
		if(date!=null && !GatewayUtil.isValidDateFormat(date.toString())) {
			 throw new ValidationException("please provide a valid date format like yyyy-mm-dd");
		}
		List<Peripheral> list = gateway.getPeripherals();
		if (list == null) {
			list = new ArrayList<Peripheral>();
		}
		if(list.size()>=10) {
			throw new ValidationException("already max number of devices added to this GateWay!");
		}
		list.add(device);
		gateway.setPeripherals(list);
		gatewayRepo.save(gateway);
		return gatewayRepo.save(gateway).getPeripherals().get(list.size()-1);
	}

	@PutMapping("/gateway/{id}/device/{deviceId}")
	public Peripheral updateComment(@PathVariable(value = "id") String id,
			@PathVariable(value = "deviceId") int deviceId, @Validated @RequestBody Peripheral deviceRequest) {
		
		return null;
	}

	@DeleteMapping("/gateway/{id}/device/{deviceId}")
	public Map<String, Boolean> deleteDevice(@PathVariable(value = "id") String id,
			@PathVariable(value = "deviceId") int deviceId) throws ResourceNotFoundException {
		 Map<String, Boolean> response = new HashMap<>();
		/*Gateway gateway = gatewayRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gateway not found for this id :: " + id));
        List<Peripheral> list = gateway.getPeripherals();
        Map<String, Boolean> response = new HashMap<>();
        for (Peripheral peripheral : list) {
        	if(peripheral.getId()==deviceId) {
        		peripheralRepo.delete(peripheral);
        		gatewayRepo.save(gateway);
        		response.put("deleted", Boolean.TRUE);
        		break;
        	}
        	response.put("deleted", Boolean.FALSE);*/
		peripheralRepo.deleteCascade(deviceId);
	    response.put("deleted", Boolean.TRUE);
		return response;
		
	}

}
