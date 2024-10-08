package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll",query = "select u from User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    @Column(name = "fullName",columnDefinition = "VARCHAR(200) NOT NULL")
    private String fullname;

    @Column(name = "address",columnDefinition = "VARCHAR(500)")
    private String address;

    @Column(name = "username", columnDefinition = "VARCHAR(500) NOT NULL")
    private String username;

    @Column(name = "password",columnDefinition = "VARCHAR(500) NOT NULL")
    private String password;

    @Column(name = "images", columnDefinition = "LONGTEXT NULL")
    private String images;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "phone",columnDefinition = "VARCHAR(11) NOT NULL")
    private String phone;
}
