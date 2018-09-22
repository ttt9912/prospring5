package ch10.p3_validation.with_jsr349_bean_validation;

public enum Genre {
    POP("P"),
    JAZZ("J"),
    BLUES("B"),
    COUNTRY("C");

    private String code;

    private Genre(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
