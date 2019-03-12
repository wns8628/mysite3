package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)//모든익셉션의 부모는 익셉션이니깐 다올수있다.
	public ModelAndView handlerException(HttpServletRequest request, Exception e) { //시스템외적인부분이라 기술들어와도됨
		//1.로깅작업
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//log.error(errors.toString()); //이걸하면 파일로저장 //스트링으로해야 파일로저장하기떄문에
		
		//2.시스템 오류 안내 화면
		ModelAndView mav = new ModelAndView();
		mav.addObject("errors",errors.toString());
		mav.setViewName("error/exception");
		return mav;
	}
}
