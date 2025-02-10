package ec.com.airsofka.passenger.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class PassengerType implements IValueObject<String> {
    private  final String value;

    private PassengerType(final String value)  {
        this.value = validate(value);
    }

    public static PassengerType of(final String value) {
        return new PassengerType(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        if(value == null){
            throw new IllegalArgumentException("The passenger type can't be null");
        }

        return value;
    }
}
