package es.olidroide;

import java.util.HashMap;
import java.util.Map;

public class PaginationQuery {

    private static final String QUERY_LIMIT = "limit";
    private static final String QUERY_OFFSET = "offset";

    private int limit;
    private int offset;

    public PaginationQuery(Builder builder){
        this.limit = builder.limit;
        this.offset = builder.offset;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> returnValues = new HashMap<>();

        if (limit > 0) {
            returnValues.put(QUERY_LIMIT, limit);
        }

        if (offset > 0) {
            returnValues.put(QUERY_OFFSET, offset);
        }

        return returnValues;
    }


    public static abstract class Builder<T> {
        public final static int MAX_SIZE = 100;

        private int limit;
        private int offset;

        public Builder<T> withLimit(int limit) {
            checkLimit(limit);
            this.limit = limit;
            return this;
        }

        private void checkLimit(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("limit must be bigger than zero");
            }

            if (limit > MAX_SIZE) {
                throw new IllegalArgumentException("limit must be smaller than 100");
            }
        }

        public Builder<T> withOffset(int offset) {
            if (offset < 0) {
                throw new IllegalArgumentException("offset must be bigger or equals than zero");
            }

            this.offset = offset;
            return this;
        }

        public abstract T build();
    }
}
