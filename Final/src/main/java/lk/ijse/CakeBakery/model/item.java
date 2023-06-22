package lk.ijse.CakeBakery.model;

//import java.util.ArrayList;

import java.util.ArrayList;

public class item {
    private String itemId;
    private String name;
    private String description;
    private int unitPrice;
    private int qtyOnHand;

    public item() {
    }


    public item(String itemId, String name, String description, int unitPrice, int qtyOnHand) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;

    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }


}
