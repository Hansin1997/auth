package cn.dustlight.auth.controllers;

import cn.dustlight.auth.AuthException;
import cn.dustlight.auth.ErrorDetails;
import cn.dustlight.auth.ErrorEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @ExceptionHandler(Throwable.class)
    public ErrorDetails onException(Throwable e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        logger.error(String.format("Error on path: %s, remote ip: %s", request.getContextPath(), request.getRemoteAddr()), e);
        return ErrorEnum.UNKNOWN.getDetails();
    }

    @ExceptionHandler(AuthException.class)
    public ErrorDetails onErrorException(AuthException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        logger.debug(e.getErrorDetails().getMessage(), e);
        return e.getErrorDetails();
    }

    @ExceptionHandler(AuthenticationException.class)
    public ErrorDetails onAuthenticationException(AuthenticationException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        logger.debug(e.getMessage(), e);
        return ErrorEnum.UNAUTHORIZED.details(e.getMessage());
    }

    @ExceptionHandler(ClientAuthenticationException.class)
    public ErrorDetails onClientAuthenticationException(ClientAuthenticationException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        logger.debug(e.getMessage(), e);
        return ErrorEnum.OAUTH_ERROR.details(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ErrorDetails onAccessDeniedException(AccessDeniedException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        logger.debug(e.getMessage(), e);
        return ErrorEnum.ACCESS_DENIED.details(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorDetails onNotFound(NoHandlerFoundException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        logger.debug(e.getMessage(), e);
        return ErrorEnum.RESOURCE_NOT_FOUND.getDetails();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorDetails onMissingServletRequestParameter(MissingServletRequestParameterException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        logger.debug(e.getMessage(), e);
        return ErrorEnum.INPUT_INVALID.details(e.getMessage());
    }

    @ExceptionHandler(ServletException.class)
    public ErrorDetails onServletException(ServletException e, HttpServletRequest request, HttpServletResponse response) {
        Integer statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        if (request != null && request.getAttribute("javax.servlet.error.status_code") != null)
            statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        response.setStatus(statusCode);
        logger.debug(e.getMessage(), e);
        return ErrorEnum.UNKNOWN.details(e.getMessage());
    }
}
