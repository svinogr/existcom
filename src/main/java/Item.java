public class Item {
    private String idMashine;
    private String id;
    private String description;
    private String cost;

    public Item() {
    }

    public Item(String idMashin, String id, String description, String cost) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.idMashine = idMashin;
    }

    public String getIdMashine() {
        return idMashine;
    }

    public void setIdMashine(String idMashine) {
        this.idMashine = idMashine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
