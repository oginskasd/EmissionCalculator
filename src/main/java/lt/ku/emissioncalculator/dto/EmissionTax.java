package lt.ku.emissioncalculator.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmissionTax {
    @NotEmpty(message = "Šis laukelis yra privalomas.")
    private String fuelType;

    @NotNull(message = "Šis laukelis yra privalomas.")
    @Min(value = 1, message = "Minimali laukelio reikšmė turi būti 1.")
    @Max(value = 1000, message = "Laukelio reikšmė negali būti didesnė nei 1000.")
    private Integer emission;

    public Integer getEmission() {
        return emission;
    }

    public void setEmission(Integer emission) {
        this.emission = emission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
