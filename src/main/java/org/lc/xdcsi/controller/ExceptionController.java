package org.lc.xdcsi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;

/**
 * Created by lc on 16/1/19.
 */
@ControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = {Exception.class})
    public String handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return "404";
    }

    @ExceptionHandler(value = {ServletException.class})
    public String handleServletException(ServletException e) {
        logger.error(e.getMessage(), e);
        return "404";
    }
}
