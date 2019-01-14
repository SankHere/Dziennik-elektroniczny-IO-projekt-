package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such specjalnosci")
public class SpecjalnosciNotFoundException extends RuntimeException{

    public SpecjalnosciNotFoundException(){
        super(String.format("Specjalność nie istnieje"));
    }

    public SpecjalnosciNotFoundException(Long id){
        super(String.format("Specjalność o id %d nie istnieje", id));
    }
}
