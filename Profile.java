package com.example.sellerspage;

public class Profile {
    private String bookName;
    private String authorName;
    private String edition;
    private String price;
    private String image;

    public Profile() { }

    public Profile(String bookName, String authorName, String edition, String image, String price) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.edition = edition;
        this.image = image;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
