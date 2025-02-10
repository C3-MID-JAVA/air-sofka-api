package ec.com.airsofka.user.values;

import ec.com.airsofka.generics.utils.Identity;

public class UserId extends Identity {

    public UserId() {
        super();
    }
    private UserId(final String id) {
        super(id);
    }
    public static UserId of(final String id) {
        return new UserId(id);
    }
}
