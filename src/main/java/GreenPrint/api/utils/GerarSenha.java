package GreenPrint.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "admin123"; // senha que você quer criptografar
        String hash = encoder.encode(senha);
        System.out.println("Hash BCrypt: " + hash);
    }
}
