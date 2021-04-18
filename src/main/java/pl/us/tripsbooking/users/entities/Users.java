package pl.us.tripsbooking.users.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;

    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Roles role;

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
