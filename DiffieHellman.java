import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {

    // Large prime number (p) and generator (g)
    private static final BigInteger p = new BigInteger("104729"); // example prime
    private static final BigInteger g = new BigInteger("2");      // generator

    private BigInteger privateKey;
    private BigInteger publicKey;

    // Constructor: generate private and public keys
    public DiffieHellman() {
        SecureRandom random = new SecureRandom();
        privateKey = new BigInteger(512, random);  // private key
        publicKey = g.modPow(privateKey, p);       // public key = g^privateKey mod p
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger generateSharedSecret(BigInteger otherPublicKey) {
        return otherPublicKey.modPow(privateKey, p);
    }

    public static void main(String[] args) {

        // Create two participants: Alice and Bob
        DiffieHellman alice = new DiffieHellman();
        DiffieHellman bob = new DiffieHellman();

        // Exchange public keys and generate shared secrets
        BigInteger aliceSharedSecret = alice.generateSharedSecret(bob.getPublicKey());
        BigInteger bobSharedSecret = bob.generateSharedSecret(alice.getPublicKey());

        // Display results
        System.out.println("Alice's Public Key: " + alice.getPublicKey());
        System.out.println("Bob's Public Key: " + bob.getPublicKey());

        System.out.println("Alice's Shared Secret: " + aliceSharedSecret);
        System.out.println("Bob's Shared Secret: " + bobSharedSecret);

        // Verify both shared secrets are equal
        System.out.println("Shared secrets match: " + 
                aliceSharedSecret.equals(bobSharedSecret));
    }
}
