package kikiprinci.cleanarchitecture.poc.aws.application;

import java.util.List;

public class PaginatedResult<T> {
    private final List<T> items;
    private final String lastEvaluatedKey;

    public PaginatedResult(List<T> items, String lastEvaluatedKey) {
        this.items = items;
        this.lastEvaluatedKey = lastEvaluatedKey;
    }

    public List<T> getItems() {
        return items;
    }

    public String getLastEvaluatedKey() {
        return lastEvaluatedKey;
    }
}