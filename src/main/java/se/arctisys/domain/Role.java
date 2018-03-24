package se.arctisys.domain;

import javax.persistence.*;

/**
 * Created by tornqvistb on 2018-03-23.
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int id;

    @Column(name="role")
    private String role;

    /*
    @ManyToMany(mappedBy = "roles")
    private Set<User> roles;
    */
}
