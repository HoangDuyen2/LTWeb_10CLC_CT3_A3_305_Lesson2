package vn.iostar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll",query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int categoryid;

    @Column(name = "categoryName",columnDefinition = "VARCHAR(200) NOT NULL")
    @NotEmpty(message = "Không được phép rỗng")
    private String categoryname;

//    Trong SQLServer ta dung max nhung trong mysql ta dung longtext
    @Column(name = "images", columnDefinition = "LONGTEXT NULL")
    private String images;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "category")
    private List<Video> videos;

    public Video addVideo(Video video) {
        getVideos().add(video);
        video.setCategory(this);
        return video;
    }

    public Video removeVideo(Video video) {
        getVideos().remove(video);
        video.setCategory(null);
        return video;
    }
}

