package uz.pdp.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String semester;

    private Integer year;


    @OneToMany
    private List<Subject>subjects;

    @OneToMany
    private List<Groups>groups;

}
