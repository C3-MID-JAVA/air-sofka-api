package ec.com.airsofka.passenger.values;

import ec.com.airsofka.generics.utils.Identity;

public class PassengerId extends Identity {


    public PassengerId() {
        super();
    }

    private PassengerId(final String id) {
        super(id);
    }

    public static PassengerId of(final String id) {
        return new PassengerId(id);
    }
}