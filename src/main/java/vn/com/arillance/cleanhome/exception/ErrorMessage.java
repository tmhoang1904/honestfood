package vn.com.arillance.cleanhome.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;

@XmlRootElement
public class ErrorMessage {
	/** contains the same HTTP Status code returned by the server */
	@XmlElement(name = "status")
	int status;

	/** message describing the error */
	@XmlElement(name = "message")
	String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorMessage(Exception ex) {
		// BeanUtils.copyProperties(this, ex);
		this.status = 0;
		if (ex.getCause() != null) {
			this.message = ex.getCause().getMessage();
		} else {
			this.message = ex.getMessage();
		}
	}

	public ErrorMessage(NotFoundException ex) {
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.message = ex.getMessage();
	}

	public ErrorMessage(int status, String message) {
		this.status = status;
		this.message = message;
	}
}
