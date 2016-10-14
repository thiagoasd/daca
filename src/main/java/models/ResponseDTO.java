package models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
//Response Object for POST, DELETE and PUT
public class ResponseDTO {

	int code;	
	long objectId;
	boolean success;
	String errorMessage;

	public ResponseDTO() {
	}
	
	
	// Success Object
	public ResponseDTO(int code, boolean success, long objectId) {
		this.code = code;
		this.success = success;
		this.objectId = objectId;
	}

	// Error Object with ID
	public ResponseDTO(int code, boolean success, int objectId, String errorMessage) {

		this.code = code;
		this.success = success;
		this.objectId = objectId;
		this.errorMessage = errorMessage;
	}
	
	// Error Object without ID
	public ResponseDTO(int code, boolean success, String errorMessage) {

		this.code = code;
		this.success = success;
		this.errorMessage = errorMessage;
	}


	public int getCode() {
		return code;
	}

	public long getObjectId() {
		return objectId;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
