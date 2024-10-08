package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vieos")
@NamedQuery(name = "Videos.findAll",query = "SELECT v FROM Video v")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videoId")
    private int videoid;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description",columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "poster")
    private String poster;

    @Column(name = "title", columnDefinition = "LONGTEXT")
    private int title;

    @Column(name = "views")
    private int views;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}
