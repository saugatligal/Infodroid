package model;

/**
 * Created by macmini on 12/14/15.
 */
public class News {

    private String newsTitle;
    private String imageUrl;
    private String description;
    private String newsUrl;



    public News(String newsTitle, String imageUrl, String description,String newsUrl) {
        this.newsTitle = newsTitle;
        this.imageUrl = imageUrl;
        this.description = description;
        this.newsUrl=newsUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
