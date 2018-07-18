package com.conduit.plastic.entity;

/**
 * Created by android on 2017/4/21.
 */

public class BannerEntity {

    /**
     * isUseLink : 0
     * imagePath : http://ggbucket-1253515764.cosgz.myqcloud.com/image/sys/201704/13140910_7726265829272058104.png
     * title : 1
     * htmlPath :
     * id : 402880ed5b3c18bc015b3c1da246000b
     * content :
     */

    private int isUseLink;
    private String imagePath;
    private String title;
    private String htmlPath;
    private String id;
    private String content;

    public int getIsUseLink() {
        return isUseLink;
    }

    public void setIsUseLink(int isUseLink) {
        this.isUseLink = isUseLink;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
