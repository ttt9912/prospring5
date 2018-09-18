package ch9.p4_global_transactions_jta;

class AsyncXAResourcesException extends RuntimeException {

    public AsyncXAResourcesException(String message) {
        super(message);
    }
}
