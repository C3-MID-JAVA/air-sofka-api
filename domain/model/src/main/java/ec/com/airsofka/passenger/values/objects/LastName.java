package ec.com.airsofka.passenger.values.objects;


import ec.com.airsofka.generics.interfaces.IValueObject;

public class LastName implements IValueObject<String> {
    private  final String value;

    private LastName(final String value)  {
        this.value = validate(value);
    }

    public static LastName of(final String value) {
        return new LastName(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        if(value == null){
            throw new IllegalArgumentException("The last name can't be null");
        }

        return value;
    }
}
