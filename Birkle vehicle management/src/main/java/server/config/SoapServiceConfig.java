package server.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import server.util.StaticStrings;

@EnableWs
@Configuration
public class SoapServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}

	@Bean(name = "vehicles")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema vehicleSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("VehiclePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(StaticStrings.VEHICLE_SOAP_NAMESPACE_VALUE);
		wsdl11Definition.setSchema(vehicleSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema vehicleSchema() {
		return new SimpleXsdSchema(new ClassPathResource("vehicle.xsd"));
	}

}
