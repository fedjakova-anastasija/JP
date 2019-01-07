package com.company.cell;

public final class Number extends Cell<Float> {
    public Number() {
        set(0.f);
    }

    public Number(Float value) {
        set(value);
    }

    @Override
    public String toString() {
        return get().toString();
    }

    @Override
    protected Float analysis(String str) throws IllegalArgumentException {
        return Float.parseFloat(str.trim());
    }
}
