package server.config;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;

import server.controller.SoapServer;
import server.dto.Vehicle;
import server.enumerator.VehicleBrand;
import server.enumerator.VehicleType;
import server.util.MarshallingHelper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SoapServiceConfig.class, SoapServer.class, MarshallingHelper.class })
public class SoapTests {
	
	private MockWebServiceClient mockClient;

	@Autowired
	private MarshallingHelper marshallingHelper;

	public SoapTests(ApplicationContext context) {
		mockClient = MockWebServiceClient.createClient(context);
	}

	@Test
	public void vehicleEndpoint() throws Exception {

		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(VehicleBrand.HONDA.name());
		vehicle.setType(VehicleType.COUPE.name());
		vehicle.setPlateNumber("EE");
		vehicle.setVin("JH4DA3350GS005185");
		vehicle.setCreationDate(OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
		
		mockClient.sendRequest(RequestCreators.withPayload(marshallingHelper.vehicleToXml(vehicle)))
				.andExpect(ResponseMatchers.payload(marshallingHelper.marshalGenericSoapResponse()));

	}
	
}
