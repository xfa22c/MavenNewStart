package dgf.xfa22c.miniJakartaProject.entities;

import dgf.xfa22c.miniJakartaProject.enums.MouseTier;
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

    @Column(name = "price", nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MouseTier tier;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private ManufacturerEntity manufacturer;
}
