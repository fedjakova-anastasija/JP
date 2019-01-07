package com.company.cell;

public final class Str extends Cell<String> {
    public Str() {
        set("");
    }

    public Str(String value) {
        set(value);
    }

    @Override
    public String toString() {
        return get();
    }

    @Override
    protected String analysis(String valueStr) throws IllegalArgumentException {
        if (valueStr == null) {
            throw new IllegalArgumentException();
        }

        return valueStr;
    }
}
