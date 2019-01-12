package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such kierunki")
public class KierunkiNotFoundException extends RuntimeException{

    public KierunkiNotFoundException(){
        super(String.format("Kierunek nie istnieje"));
    }

    public KierunkiNotFoundException(Long id){
        super(String.format("Kierunek o id %d nie istnieje", id));
    }
}
