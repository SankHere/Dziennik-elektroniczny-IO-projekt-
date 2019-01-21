package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such kierunki")
public class StypendiaNotFoundException extends RuntimeException{

    public StypendiaNotFoundException(){
        super(String.format("Stypendium nie istnieje"));
    }

    public StypendiaNotFoundException(Long id){
        super(String.format("Stypendium o id %d nie istnieje", id));
    }
}
