package com.dogsitter.model;

public class Info {
	String message;
	String messageType;
	
	public Info(String message, String messageType) {
		this.setMessage(message);
		this.setMessageType(messageType);
	}
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	

}
