package pl.us.tripsbooking.trips.dto;

import java.math.BigDecimal;

public class TripListModel {

    private Integer number;
    private String title;
    private String mainImageUrl;
    private BigDecimal minPrice;

    public TripListModel(Integer number, String title, String mainImageUrl, BigDecimal minPrice) {
        this.number = number;
        this.title = title;
        this.mainImageUrl = mainImageUrl;
        this.minPrice = minPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
}
