package dgf.xfa22c.sqlConnection;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "mice")
public class Mouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sensor;

    @Column(nullable = false)
    private int maxAcceleration;

    @Column(nullable = false)
    private int pollingRate;

}
