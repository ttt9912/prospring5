package ch5.p2_advices;

class ErrorBean {

    void errorProneMethod() throws Exception {
        throw new Exception("Generic Exception");
    }

    void otherErrorProneMethod() throws IllegalArgumentException {
        throw new IllegalArgumentException("Illegal Argument Exception");
    }
}
