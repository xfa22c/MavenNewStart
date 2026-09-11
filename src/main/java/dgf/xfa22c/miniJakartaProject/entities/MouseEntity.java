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
@Entity
@Table(name = "miceEntities")

public class MouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sensor", nullable = false)
    private String sensor;

    @Column(name = "maxAcceleration", nullable = false)
    private int maxAcceleration;

    @Column(name = "pollingRate", nullable = false)
    private int pollingRate;
}
