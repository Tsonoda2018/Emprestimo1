package com.br.santander.emprestimo.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@Autowired
	private MessageSource message;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public List<ErroDto> getErro(MethodArgumentNotValidException ex) {
		List<ErroDto> erros = ex.getFieldErrors().stream().map(e -> {
			String mensagem = message.getMessage(e, LocaleContextHolder.getLocale());
			return new ErroDto(e.getField(), mensagem);
		}).collect(Collectors.toList());
		return erros;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { IllegalArgumentException.class, EntityNotFoundException.class,
			EntityExistsException.class, UsernameNotFoundException.class})
	public ErroDto getErro(Exception ex) {
		// String message2 = message.getMessage(null, LocaleContextHolder.getLocale());
		// return new ErroDto(null, message2);
		return new ErroDto(null, ex.getMessage());
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { RuntimeException.class })
	public ErroDto getErro1(Exception ex) {
		// String message2 = message.getMessage(null, LocaleContextHolder.getLocale());
		// return new ErroDto(null, message2);
		return new ErroDto(null, ex.getMessage());
	}

}
