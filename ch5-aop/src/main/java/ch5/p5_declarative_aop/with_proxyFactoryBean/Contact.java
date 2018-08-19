package ch5.p5_declarative_aop.with_proxyFactoryBean;

class Contact {

    private String name;
    private String phoneNumber;
    private String email;

    public String getName() {
        return name;
    }

    // don't make setters package private
    @SuppressWarnings("WeakerAccess")
    public void setName(final String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
