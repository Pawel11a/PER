package dto;

public enum ErrorsEnumDto {


    CATEGORY("category"), COUNTRY("country"), CUSTOMERORDER("customer_order"), PRODUCER("customer"), GUARANTEECOMPONENTS("guarantee_components"), PAYMENT("payment"),
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
