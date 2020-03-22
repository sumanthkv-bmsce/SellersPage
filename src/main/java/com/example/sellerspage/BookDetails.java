package com.example.sellerspage;

public class BookDetails {
    public String bookName;
    public String authorName;
    public String edition;
    public String price;
    public  String image1;
    public  String image2;
    public  String image3;
    public  String image4;
    public String image5;
    public String image6;

    public BookDetails(String bookName, String authorName, String edition, String price,
                       String image1,String image2,String image3,String image4,String image5,String image6) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.edition = edition;
        this.price = price;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.image6 = image6;


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

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getImage4() {
        return image4;
    }
    public String getImage5() {
        return image5;
    }


}
