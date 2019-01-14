package edu.uph.ii.platformy.controllers.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Positive;



@Getter @Setter
public class SpecjalnoscFilter {

    private String phrase;



    public boolean isEmpty(){
        return StringUtils.isEmpty(phrase);
    }

    public void clear(){
        this.phrase = "";

    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(phrase)) {
            return null;
        }else{
            return "%"+phrase+"%";
        }
    }


}
