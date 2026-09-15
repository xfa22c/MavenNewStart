package dgf.xfa22c.miniJakartaProject.dto;

import dgf.xfa22c.miniJakartaProject.entities.ManufacturerEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ManufacturerDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Min(1800)
    @Max(2100)
    private int yearOfCreation;

    public ManufacturerEntity toManufacturer(){
        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName(this.name);
        manufacturer.setYearOfCreation(this.yearOfCreation);
        return manufacturer;
    }

}
