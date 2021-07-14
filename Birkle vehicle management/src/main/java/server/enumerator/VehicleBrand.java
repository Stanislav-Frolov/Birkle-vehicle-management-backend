package server.enumerator;

public enum VehicleBrand {
	
	TOYOTA("Toyota"),
	HONDA("Honda"), 
	DAIHATSU("Daihatsu"),
	NISSAN("Nissan"),
	SUZUKI("Suzuki"),
	MAZDA("Mazda"),
	MITSUBISHI("Mitsubishi"), 
	SUBARU("Subaru"),
	ISUZU("Isuzu"),
	KAWASAKI("Kawasaki"),
	YAMAHA("Yamaha"),
	MITSUOKA("Mitsuoka"),
	UNKNOWN("unknown");
	
	private String brand;

	private VehicleBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

}
