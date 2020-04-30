public class Customer {
    private int customerID;
    private String customerName;
    private String deliveryAddressLine1;
    private String cityName;
    private String deliveryPostalCode;
    private String phoneNumber;

    public Customer (int customerID, String customerName, String deliveryAddressLine1, String cityName, String deliveryPostalCode, String phoneNumber) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.deliveryAddressLine1 = deliveryAddressLine1;
        this.cityName = cityName;
        this.deliveryPostalCode = deliveryPostalCode;
        this.phoneNumber = phoneNumber;
    }

    //region Getters
    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDeliveryAddressLine1() {
        return deliveryAddressLine1;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    //endregion
}
