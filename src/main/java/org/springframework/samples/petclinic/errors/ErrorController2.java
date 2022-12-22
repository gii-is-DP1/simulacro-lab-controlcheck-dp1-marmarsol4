package org.springframework.samples.petclinic.errors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
	
@Controller
public class ErrorController2 implements ErrorController {
			
	@RequestMapping("/error") 
	public String handleError(HttpServletRequest request) { 
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); // get error status 
		if (status != null) { 
			int statusCode = Integer.parseInt(status.toString()); // display specific error page 
			if (statusCode == HttpStatus.NOT_FOUND.value()) { 
				return "error-404"; 
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { 
				return "error-500"; 
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) { 
				return "error-403"; 
			} 
		}
		return "error"; // display generic error return "error";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}