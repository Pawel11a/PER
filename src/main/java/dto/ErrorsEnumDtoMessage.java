package dto;

public enum ErrorsEnumDtoMessage {

    ADD_COUNTRY(" - add country"),
    ADD_CUSTOMER(" - add customer"),


    ;

    private String name;

    ErrorsEnumDtoMessage(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}


