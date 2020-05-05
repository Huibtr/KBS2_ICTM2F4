public class Order {
    private int CustomerID;
    private int OrderID;
    private int StockItemID;
    private int Quantity;

    public Order(int CustomerID, int OrderID, int StockItemID, int Quantity) {
        this.CustomerID = CustomerID;
        this.OrderID = OrderID;
        this.StockItemID = StockItemID;
        this.Quantity = Quantity;
    }

    //region Getters
    public int getCustomerID() {
        return CustomerID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getStockItemID() {
        return StockItemID;
    }

    public int getQuantity() {
        return Quantity;
    }
    //endregion
}
