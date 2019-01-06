package edu.uph.ii.platformy.controllers;

import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice//można wskazać, których konkretnie kontrolerów ma dotyczyć porada
@Log4j2
public class GlobalControlerAdvice {

    @ExceptionHandler({JDBCConnectionException.class, DataIntegrityViolationException.class})
    public String handleDbError(Model model, HttpServletRequest req, Exception ex) {
        log.error("Request: " + req.getRequestURL() + " raised " + ex);

        model.addAttribute("exception", ex);
        model.addAttribute("url", req.getRequestURL());

        return "errors/databaseErrorView";
    }


}
