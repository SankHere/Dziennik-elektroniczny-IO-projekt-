package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such kierunki")
public class PodanieNotFoundException extends RuntimeException{

    public PodanieNotFoundException(){
        super(String.format("Podanie nie istnieje"));
    }

    public PodanieNotFoundException(Long id){
        super(String.format("Podanie o id %d nie istnieje", id));
    }
}
