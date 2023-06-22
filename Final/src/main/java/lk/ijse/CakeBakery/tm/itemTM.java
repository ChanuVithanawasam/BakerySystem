package lk.ijse.CakeBakery.tm;

import javafx.scene.control.Button;

public class itemTM {
    private String itemId;
    private String name;
    private String description;
    private String unitPrice;
    private String qtyOnHand;
    private Button btn1;



  //  public itemTM(String string, String resultString, String description, String qtyOnHand) {
    //}

    public itemTM(String itemId, String name, String description, String qtyOnHand, String unitPrice, Button btn1) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
        this.btn1 = btn1;
    }

    public itemTM() {
    }

    public itemTM(String itemId, String name, String description, String qtyOnHand, String unitPrice) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;

    }


    @Override
    public String toString() {
        return "itemTM{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
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

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Button getBtn1() {
        return btn1;
    }

    public void setBtn1(Button btn1) {
        this.btn1 = btn1;
    }


}
