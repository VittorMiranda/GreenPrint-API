package GreenPrint.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity tratarErroRegraDeNegocio(RegraDeNegocioException ex) {
        return ResponseEntity.badRequest().body(new DadosErroGenerico(ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErro403(AccessDeniedException ex) {
        return ResponseEntity.status(403).body(new DadosErroGenerico("Acesso negado: você não tem permissão para realizar esta ação."));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErro401(AuthenticationException ex) {
        return ResponseEntity.status(401).body(new DadosErroGenerico("Usuário não autenticado ou token inválido."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(500).body(new DadosErroGenerico("Erro interno no servidor."));
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    private record DadosErroGenerico(String mensagem) {}
}
