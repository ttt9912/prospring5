package ch10.p3_validation.with_jsr349_bean_validation;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private String code;

    private Gender(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
