package server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import server.dto.VehicleModel;
import server.service.VehicleService;

@RestController
@ControllerAdvice
public class RestServer {

	private VehicleService vehicleService;

	public RestServer(@Autowired VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Long> createVehicle(@RequestBody VehicleModel vehicle) {
		return ResponseEntity.ok(vehicleService.create(vehicle));
	}

	@GetMapping(value = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehicleModel>> getVehicles() {
		return ResponseEntity.ok(vehicleService.getVehicles());
	}

	@GetMapping(value = "/findVehicle/{vehiclePK}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VehicleModel> findVehicle(@PathVariable Long vehiclePK) {
		return ResponseEntity.ok(vehicleService.find(vehiclePK));
	}

	@PostMapping(value = "/update/{vehiclePK}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateVehicle(@PathVariable Long vehiclePK, @RequestBody VehicleModel vehicle) {
		vehicleService.update(vehiclePK, vehicle);
		return ResponseEntity.ok("");
	}

	@GetMapping(value = "/delete/{vehiclePK}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Boolean> deleteVehicle(@PathVariable Long vehiclePK) {
		return ResponseEntity.ok(vehicleService.delete(vehiclePK));
	}

}
