package com.example.demo.roulette.api;

public class RestResponse<T> {
	private String message = "";
	private T content;
	
	public RestResponse(String message, T content) {
		this.message = message;
		this.content = content;
	}
	
	public String convertJson() {
		return "{" +
                "\"message\":\"" + this.message + "\"," +
                "\"content\":\"" + this.content + "\"" +
                "}";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

}
