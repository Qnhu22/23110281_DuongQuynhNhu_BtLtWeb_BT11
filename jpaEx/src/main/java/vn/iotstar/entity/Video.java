package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v"),
    @NamedQuery(name = "Video.findByUserId", query = "SELECT v FROM Video v WHERE v.user.userId = :userId")
})
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "videoId")
    private int videoId;

    @Column(nullable = false)
    private String title;

    private String description;

    private int views = 0; // mặc định = 0

    private int status; // 1=active, 0=locked

    private String images; // ảnh đại diện

    // Nhiều video thuộc 1 category
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    // Nhiều video thuộc 1 user
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // Getter & Setter
    public int getVideoId() {
        return videoId;
    }
    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
