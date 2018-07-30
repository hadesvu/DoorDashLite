package doordash.lite.doordashlite.model;

public class Restaurant {
    private int id;
    private String name;
    private String description;
    private String coverImgUrl;
    private String status;
    private float deliveryFee;

    public Restaurant() {
    }

    public Restaurant(int id, String name, String description, String coverImgUrl, String status, float deliveryFee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.status = status;
        this.deliveryFee = deliveryFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
