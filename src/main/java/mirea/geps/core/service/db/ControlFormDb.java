package mirea.geps.core.service.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "control_form")
@Table(name = "control_form")
public class ControlFormDb {

    @Id
    @GeneratedValue
    private Long control_form_id;
    @Column
    private String name;


}
