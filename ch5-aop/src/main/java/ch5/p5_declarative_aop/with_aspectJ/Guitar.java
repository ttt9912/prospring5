package ch5.p5_declarative_aop.with_aspectJ;

class Guitar {
    private String brand;

    String play() {
        return "G C G C Am D7";
    }

    void setBrand(final String brand) {
        this.brand = brand;
    }

    String getBrand() {
        return brand;
    }
}
