package com.TiendaDisco.RegistroResenas.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class ManejoErrores extends RuntimeException{

    public ResponseEntity<Map<String,String>> manejo(MethodArgumentNotValidException ex){
        Map<String,String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errores.put(err.getField(),err.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    public ManejoErrores (String msje){
        super(msje);
    }

}
