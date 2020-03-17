package com.example.sellerspage;

public class BookDetails {
    public String bookName;
    public String authorName;
    public String edition;
    public String price;
    public  String image;

    public BookDetails(String bookName, String authorName, String edition, String price,String image) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.edition = edition;
        this.price = price;
        this.image = image;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getEdition() {
        return edition;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
