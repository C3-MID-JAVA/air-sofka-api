package ec.com.airsofka.contact.values;

import ec.com.airsofka.generics.utils.Identity;

public class ContactId extends Identity {


    public ContactId() {
        super();
    }

    private ContactId(final String id) {
        super(id);
    }

    public static ContactId of(final String id) {
        return new ContactId(id);
    }
}