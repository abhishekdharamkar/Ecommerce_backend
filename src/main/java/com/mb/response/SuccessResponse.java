package com.mb.response;

import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SuccessResponse
{

	private String error;
	private Object data;
	private Integer statusCode;
	private String message;

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public ResponseEntity<Object> validationErrorsResponse(String message,
			List<ValidatioErrorResponse> validationErros)
	{

		ErrorResponse error = new ErrorResponse(new Date(), message, "cant proceed", HttpStatus.NOT_ACCEPTABLE.value(), message);
		error.setValidationErrors(validationErros);

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	public static SuccessResponse getInstance()
	{
		SuccessResponse response = new SuccessResponse();
		response.setStatusCode(HttpStatus.OK.value());
		return response;
	}

	public Integer getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(Integer statusCode)
	{
		this.statusCode = statusCode;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}
}
