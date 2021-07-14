package server.dto;

import java.time.OffsetDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import server.enumerator.VehicleBrand;
import server.enumerator.VehicleType;
import server.util.StaticStrings;

@Entity(name = StaticStrings.VEHICLE)
public class VehicleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private VehicleBrand brand;

	@Basic
	@Type(type = StaticStrings.JAVA_LANG_STRING)
	private String model;

	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private VehicleType type;

	@Basic
	@Column(name = "plate_country")
	@Type(type = StaticStrings.JAVA_LANG_STRING)
	private String plateCountry;

	@Basic
	@Column(name = "plate_number")
	@Type(type = StaticStrings.JAVA_LANG_STRING)
	private String plateNumber;

	@NaturalId
	@Basic(optional = false)
	@Type(type = StaticStrings.JAVA_LANG_STRING)
	private String vin;

	@Basic(optional = false)
	@Column(name = "creation_date")
	@Type(type = StaticStrings.JAVA_TIME_OFFSET_DATE_TIME)
	private OffsetDateTime creationDate;

	@Basic
	@Column(name = "manufactored_country")
	@Type(type = StaticStrings.JAVA_LANG_STRING)
	private String manufactoredCountry;

	public void setBrand(VehicleBrand brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public void setPlateCountry(String plateCountry) {
		this.plateCountry = plateCountry;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setManufactoredCountry(String manufactoredCountry) {
		this.manufactoredCountry = manufactoredCountry;
	}

	public Long getId() {
		return id;
	}

	public VehicleBrand getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public VehicleType getType() {
		return type;
	}

	public String getPlateCountry() {
		return plateCountry;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public String getVin() {
		return vin;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public String getManufactoredCountry() {
		return manufactoredCountry;
	}

	public void updateValues(VehicleModel newVehicle) {
		this.brand = newVehicle.getBrand();
		this.model = newVehicle.getModel();
		this.type = newVehicle.getType();
		this.plateCountry = newVehicle.getPlateCountry();
		this.plateNumber = newVehicle.getPlateNumber();
		this.vin = newVehicle.getVin();
		this.creationDate = newVehicle.getCreationDate();
		this.manufactoredCountry = newVehicle.getManufactoredCountry();

	}

}
