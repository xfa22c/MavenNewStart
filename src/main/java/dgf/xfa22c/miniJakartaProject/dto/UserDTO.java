package dgf.xfa22c.miniJakartaProject.dto;

import dgf.xfa22c.miniJakartaProject.entities.UserEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Name can not be empty далбаёб")
    @Size(min = 2, max = 50, message = "Чё такое дохуя длинное имя? Between 2-50")
    private String name;

    @Email(message = "Write down normal email address")
    @NotBlank(message = "Email can not be null")
    private String email;

    @Min(value = 18, message = "You must be at least 18")
    @Max(value = 110, message = "You must be at most 110")
    private int age;

    public UserEntity toUser() {
        UserEntity user = new UserEntity();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setAge(this.age);
        return user;
    }

}
