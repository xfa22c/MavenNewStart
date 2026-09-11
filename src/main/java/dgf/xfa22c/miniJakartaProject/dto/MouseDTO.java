package dgf.xfa22c.miniJakartaProject.dto;

import dgf.xfa22c.miniJakartaProject.entities.MouseEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MouseDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    private String sensor;

    @NotNull
    @Min(5)
    @Max(150)
    private int maxAcceleration;

    @NotNull
    @Min(125)
    @Max(16000)
    private int pollingRate;

    public MouseEntity toMouse(){
        MouseEntity mouse = new MouseEntity();
        mouse.setName(this.name);
        mouse.setSensor(this.sensor);
        mouse.setMaxAcceleration(this.maxAcceleration);
        mouse.setPollingRate(this.pollingRate);
        return mouse;
    }
}
