package vn.anhnguyen.ticketmovie.domain.model.response;

public class NewsModel {
    private int id;
    private String img;
    private String title;
    private String contain;
    private String writer;

    public NewsModel() {
    }

    public NewsModel(int id, String img, String title, String contain, String writer) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.contain = contain;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
