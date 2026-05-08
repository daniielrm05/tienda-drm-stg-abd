package es.iesclaradelrey.da2d1a.tiendadrmstgabd.tiendadrmstgabdweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class WebApplicationTests {

    @Test
    void generarHash() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String hash = encoder.encode("Password");
        System.out.println("EL HASH ES: " + hash);
    }

}
