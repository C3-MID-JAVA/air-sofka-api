package ec.com.airsofka.contact.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Prefix implements IValueObject<String> {
    private  final String value;

    private Prefix(final String value)  {
        this.value = validate(value);
    }

    public static Prefix of(final String value) {
        return new Prefix(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        if(value == null){
            throw new IllegalArgumentException("The prefix can't be null");
        }

        return value;
    }
}
