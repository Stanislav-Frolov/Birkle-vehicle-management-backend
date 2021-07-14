package server.enumerator;

public enum VehicleType {
	
	SEDAN("Sedan"),
	COUPE("Coupe"),
	SPORT("Sport"),
	STATION_WAGON("Station wagon"),
	HATCHBACK("Hatchback"),
	CONVERTIBLE("Convertible"),
	SUV("Sport utility vehicle"),
	MINIVAN("Minivan"),
	PICKUP_TRUCK("Pickup truck"),
	MONSTER_TRUCK("monster truck"),
	UNKNOWN("unknown");

	private String type;

	private VehicleType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
