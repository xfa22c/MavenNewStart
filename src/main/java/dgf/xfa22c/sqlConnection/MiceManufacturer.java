package dgf.xfa22c.sqlConnection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mice_manufacturers")
public class MiceManufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brandName;

    @OneToMany(
            mappedBy = "miceManufacturer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Mouse> mice = new ArrayList<>();

    @SuppressWarnings("unused")
    public void addMouse(Mouse mouse) {
        mice.add(mouse);
        mouse.setMiceManufacturer(this);
    }

}
