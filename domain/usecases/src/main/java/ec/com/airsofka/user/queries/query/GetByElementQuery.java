package ec.com.airsofka.user.queries.query;

import ec.com.airsofka.generics.utils.Query;

public class GetByElementQuery extends Query {
    private final String element;

    public GetByElementQuery(String element) {
        super(null);
        this.element = element;
    }

    public String getElement() {
        return element;
    }
}
