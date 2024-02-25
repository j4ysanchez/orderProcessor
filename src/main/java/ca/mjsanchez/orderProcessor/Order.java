package ca.mjsanchez.orderProcessor;

public class Order {
    private String id;
    private String timestamp;
    private String customerName;
    private String customerAddress;
    private String pizzaToppings;
    private String pizzaSize;
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
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.pizzaToppings = pizzaToppings;
        this.pizzaSize = pizzaSize;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPizzaToppings() {
        return pizzaToppings;
    }

    public void setPizzaToppings(String pizzaToppings) {
        this.pizzaToppings = pizzaToppings;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

}
