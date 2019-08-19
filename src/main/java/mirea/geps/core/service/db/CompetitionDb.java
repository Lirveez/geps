package mirea.geps.core.service.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity(name = "competition")
@Table(name = "competition")
@NoArgsConstructor
public class CompetitionDb {
    @Id
    @GeneratedValue
    private Long competition_id;
    @Column
    private String code;
    @Column
    private String description;
    @Column
    private String type;

}
