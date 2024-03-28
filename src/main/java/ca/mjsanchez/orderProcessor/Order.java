package ca.mjsanchez.orderProcessor;

public class Order {
    private String id;
    private String timestamp;
    private String customer;
    private String address;
    private String pizzaType;
    private String size;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order() {
    }

    public Order(String id, String timestamp, String customerName, String customerAddress, String pizzaToppings,
            String pizzaSize) {
        this.id = id;
        this.timestamp = timestamp;
        this.customer = customerName;
        this.address = customerAddress;
        this.pizzaType = pizzaToppings;
        this.size = pizzaSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customerName) {
        this.customer = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String customerAddress) {
        this.address = customerAddress;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaToppings) {
        this.pizzaType = pizzaToppings;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String pizzaSize) {
        this.size = pizzaSize;
    }

}
