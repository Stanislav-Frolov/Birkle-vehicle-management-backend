package server.controller;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.xml.transform.StringSource;

import server.dto.Vehicle;
import server.dto.VehicleModel;
import server.enumerator.VehicleBrand;
import server.util.MarshallingHelper;
import server.util.StaticStrings;

@Endpoint
public class SoapServer {
	
	@Autowired
	private MarshallingHelper marshallingHelper;

	@PayloadRoot(localPart = StaticStrings.VEHICLE, namespace = StaticStrings.VEHICLE_SOAP_NAMESPACE_VALUE)
	@ResponsePayload
	public StringSource saveVehicle(@RequestPayload Vehicle vehicle) {
		
		VehicleModel vehicleDAO = new VehicleModel();
		vehicleDAO.setBrand(VehicleBrand.valueOf( vehicle.getBrand() ) );
		vehicleDAO.setCreationDate(OffsetDateTime.parse( vehicle.getCreationDate() ));
		vehicleDAO.setManufactoredCountry(vehicle.getManufactoredCountry());
		vehicleDAO.setModel(vehicle.getModel());
		vehicleDAO.setPlateCountry( vehicle.getPlateCountry());
		vehicleDAO.setPlateNumber(vehicle.getPlateNumber());
		vehicleDAO.setVin(vehicle.getVin());
		
		return marshallingHelper.marshalGenericSoapResponse();
	}

}
