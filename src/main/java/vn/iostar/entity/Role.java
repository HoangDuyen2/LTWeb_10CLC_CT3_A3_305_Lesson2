package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int roleid;

    @Column(name = "roleName",columnDefinition = "VARCHAR(200) NOT NULL")
    private String rolename;

    @OneToMany(mappedBy = "user")
    private List<User> users;

    public User addUser(User user) {
        getUsers().add(user);
        user.setRole(this);
        return user;
    }

    public User removeUser(User user) {
        getUsers().remove(user);
        user.setRole(null);
        return user;
    }
}
