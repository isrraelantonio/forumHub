package Challend.Forum.hub.infra.exception;

import Challend.Forum.hub.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class TatamentoDeExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream()
                .map(DadosErrorValidacao::new).toList());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity TratarErroDenegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }



    private record DadosErrorValidacao(String campo, String mensagem){

        private DadosErrorValidacao (FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
