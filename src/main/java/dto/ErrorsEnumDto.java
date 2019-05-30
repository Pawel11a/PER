package dto;

public enum ErrorsEnumDto {


    CATEGORY("category"), COUNTRY("country"), CUSTOMER("customer"), CUSTOMERORDER("customer_order"), PRODUCER("producer"), GUARANTEECOMPONENTS("guarantee_components"), PAYMENT("payment"),
    PRODUCT("product"), SHOP("shop"), STOCK("stock"), TRADE("trade");

    private String name;

    ErrorsEnumDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
