package server.config;

import java.net.URISyntaxException;
import java.time.OffsetDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import server.dto.VehicleModel;
import server.enumerator.VehicleBrand;
import server.enumerator.VehicleType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class DatabaseTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();

	private Long vehiclePK;
	private static VehicleModel vehicle;

	@BeforeAll
	static void setup() {

		vehicle = new VehicleModel();
		vehicle.setBrand(VehicleBrand.HONDA);
		vehicle.setType(VehicleType.COUPE);
		vehicle.setPlateCountry("EE");
		vehicle.setVin("JH4DA3350GS005185");
		vehicle.setCreationDate(OffsetDateTime.now());
	}

	@Test
	public void crudTest() throws URISyntaxException, Exception {

		Assertions.assertThat(createVehicle().getStatusCode()).isEqualTo(HttpStatus.OK);

		Assertions.assertThat(getVehicles().getBody().length).isEqualTo(1);

		vehicle.setBrand(VehicleBrand.DAIHATSU);

		Assertions.assertThat(updateVehicle().getStatusCode()).isEqualTo(HttpStatus.OK);

		Assertions.assertThat(findVehicle().getBody().getBrand()).isEqualTo(VehicleBrand.DAIHATSU);

		Assertions.assertThat(deleteVehicle().getBody()).isEqualTo(true);

		Assertions.assertThat(findDeletedVehicle().getBody()).isEqualTo(null);

	}

	private ResponseEntity<Long> createVehicle() {
		ResponseEntity<Long> response = testRestTemplate
				.postForEntity(String.format("http://localhost:%d/create", port), vehicle, Long.class);
		vehiclePK = response.getBody();
		return response;
	}

	private ResponseEntity<VehicleModel[]> getVehicles() {
		return testRestTemplate.getForEntity(String.format("http://localhost:%d/vehicles", port), VehicleModel[].class);
	}

	private ResponseEntity<String> updateVehicle() {
		return testRestTemplate.postForEntity(String.format("http://localhost:%d/update/%d", port, vehiclePK), vehicle,
				String.class);
	}

	private ResponseEntity<VehicleModel> findVehicle() {
		return testRestTemplate.getForEntity(String.format("http://localhost:%d/findVehicle/%d", port, vehiclePK),
				VehicleModel.class);
	}

	private ResponseEntity<Boolean> deleteVehicle() {
		return testRestTemplate.getForEntity(String.format("http://localhost:%d/delete/%d", port, vehiclePK),
				Boolean.class);
	}

	private ResponseEntity<VehicleModel> findDeletedVehicle() {
		return testRestTemplate.getForEntity(String.format("http://localhost:%d/findVehicle/%d", port, vehiclePK),
				VehicleModel.class);
	}

}
