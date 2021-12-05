package cl.tofcompany.appmovilg1.Entities;

import java.io.Serializable;

public class FoodModelEntradas implements Serializable {

    private String title;
    private String pic;
    private String description;
    private int fee;
    private int numberInCard;

    public FoodModelEntradas() {
    }

    public FoodModelEntradas(String title, String pic, String description, int fee, int numberInCard) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCard = numberInCard;
    }

    public FoodModelEntradas(String title, String pic, String description, int fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getNumberInCard() {
        return numberInCard;
    }

    public void setNumberInCard(int numberInCard) {
        this.numberInCard = numberInCard;
    }
}
