package ec.com.airsofka.aggregate.auth.values;


import ec.com.airsofka.generics.utils.Identity;

public class AuthId extends Identity {

    public AuthId() {
        super();
    }

    public AuthId(String id) {
        super(id);
    }

    public static AuthId of(String id) {
        return new AuthId(id);
    }

}