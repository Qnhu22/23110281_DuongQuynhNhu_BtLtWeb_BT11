package model;

public class Category {
    private int cateId;
    private String cateName;
    private String icons;
    private int userId;

    public Category() {}

    public Category(int cateId, String cateName, String icons, int userId) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.icons = icons;
        this.userId = userId;
    }

    public int getCateId() { return cateId; }
    public void setCateId(int cateId) { this.cateId = cateId; }
    public String getCateName() { return cateName; }
    public void setCateName(String cateName) { this.cateName = cateName; }
    public String getIcons() { return icons; }
    public void setIcons(String icons) { this.icons = icons; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}