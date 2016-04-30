package es.olidroide.Note;

import es.olidroide.PaginationQuery;
import java.util.Map;

public class NotesQuery extends PaginationQuery {

    public NotesQuery(Builder builder) {
        super(builder);
    }

    @Override public Map<String, Object> toMap() {
        return super.toMap();
    }

    public static class Builder extends PaginationQuery.Builder<NotesQuery> {

        private Builder() {
            super();
        }

        public static Builder create() {
            return new Builder();
        }

        public NotesQuery build() {
            return new NotesQuery(this);
        }
    }
}
