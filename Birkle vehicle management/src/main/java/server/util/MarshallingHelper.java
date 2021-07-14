package server.util;

import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringSource;

import server.dto.Response;
import server.dto.Vehicle;

@Component
public class MarshallingHelper {

	@Value("${soap.response.message}")
	private String soapServerResponse;
	
	private Jaxb2Marshaller jaxb2Marshaller;

	public MarshallingHelper() {
		jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(Vehicle.class, Response.class);
	}

	public StringSource vehicleToXml(Vehicle vehicle) {
		StringWriter sw = new StringWriter();
		jaxb2Marshaller.marshal(vehicle, new StreamResult(sw));
		return new StringSource(sw.toString());
	}

	public StringSource marshalGenericSoapResponse() {
		Response response = new Response();
		response.setMessage(soapServerResponse);

		StringWriter sw = new StringWriter();
		jaxb2Marshaller.marshal(response, new StreamResult(sw));
		return new StringSource(sw.toString());
	}

}
