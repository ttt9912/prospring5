package ch14.p1_groovy_basics

class Singer {
    def firstName;
    def lastName;
    def birthDate;

    // toString is overriden with a closure
    String toString() {
        "ch14.p1_groovy_basics.Singer($firstName, $lastName, $birthDate)"
    }
}
