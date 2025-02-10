package ec.com.airsofka.booking.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Status implements IValueObject<String> {
    private  final  String value;

    private Status(final String value)  {
        this.value = validate(value);
    }

    public static Status of(final String value) {
        return new Status(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        if(value == null){
            throw new IllegalArgumentException("The status can't be null");
        }

        return value;
    }
}
