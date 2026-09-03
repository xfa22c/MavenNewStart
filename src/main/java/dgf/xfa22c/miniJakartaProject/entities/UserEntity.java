package dgf.xfa22c.miniJakartaProject.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "userEntity")
@Table(name = "usersWeb")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private int age;

}
