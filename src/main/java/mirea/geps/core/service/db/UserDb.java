package mirea.geps.core.service.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_table")
public class UserDb {

    @Id
    @GeneratedValue
    @Column
    private Long user_id;

    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String patronymic;
    @Column
    private String degree;


}

