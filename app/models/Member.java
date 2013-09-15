package models;

import org.codehaus.jackson.JsonNode;

import play.mvc.WebSocket;

public class Member {

	private String userName;
	private String x;
	private String y;
	WebSocket.Out<JsonNode> out;
	
	// GETTERS AND SETTERS
	public String getX() {
		return x;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public WebSocket.Out<JsonNode> getOut() {
		return out;
	}
	public void setOut(WebSocket.Out<JsonNode> out) {
		this.out = out;
	}
}
