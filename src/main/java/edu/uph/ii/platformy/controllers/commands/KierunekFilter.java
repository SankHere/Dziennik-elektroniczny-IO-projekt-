package edu.uph.ii.platformy.controllers.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Positive;



@Getter @Setter
public class KierunekFilter {

    private String phrase;

    @Positive
    private double liczbaMiejsc;


    public boolean isEmpty(){
        return StringUtils.isEmpty(phrase) && liczbaMiejsc == 0.0;
    }

    public void clear(){
        this.phrase = "";
        this.liczbaMiejsc = 0.0;
    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(phrase)) {
            return null;
        }else{
            return "%"+phrase+"%";
        }
    }


}
