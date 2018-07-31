package ch4.p3_factory_beans;

import java.security.MessageDigest;
import java.util.Arrays;

/*
 * Verschlüsselt einen String mithilfe von java.security.MessageDigest
 */
 class MessageDigester {

    private MessageDigest digest1;
    private MessageDigest digest2;


    public void digest(String msg) {
        System.out.println("--- using digest 1 ---");
        digest(msg, digest1);

        System.out.println("\n--- using digest 2 ---");
        digest(msg, digest2);
    }

    private void digest(final String msg, final MessageDigest digest) {
        System.out.println("using algorithm: " + digest.getAlgorithm());

        digest.reset();
        byte[] bytes = msg.getBytes();
        byte[] out = digest.digest(bytes);

        System.out.println(out);
    }

    public void setDigest1(final MessageDigest digest1) {
        this.digest1 = digest1;
    }

    public void setDigest2(final MessageDigest digest2) {
        this.digest2 = digest2;
    }
}
