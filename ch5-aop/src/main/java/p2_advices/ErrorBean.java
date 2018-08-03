package p2_advices;

class ErrorBean {

    public void errorProneMethod() throws Exception {
        throw new Exception("Generic Exception");
    }

    public void otherErrorProneMethod() throws IllegalArgumentException {
        throw new IllegalArgumentException("Illegal Argument Exception");
    }
}
