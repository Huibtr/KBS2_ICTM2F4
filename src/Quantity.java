public class Quantity {
    private String StockItemName;
    private int StockItemID;
    private int QuantityOnHand;

    public Quantity(String StockItemName, int StockItemID, int QuantityOnHand) {
        this.StockItemName = StockItemName;
        this.StockItemID = StockItemID;
        this.QuantityOnHand = QuantityOnHand;
    }

    //region Getters
    public String getStockItemName() {
        return StockItemName;
    }

    public int getStockItemID() {
        return StockItemID;
    }

    public int getQuantityOnHand() {
        return QuantityOnHand;
    }
    //endregion
}
