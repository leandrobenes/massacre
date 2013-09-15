package dto;

public class ServerResponse {

	private ServerResponseType type;
	private String username;
	private String x;
	private String y;

	public ServerResponse(ServerResponseType type, String username, String x,
			String y) {
		super();
		this.type = type;
		this.username = username;
		this.x = x;
		this.y = y;
	}
	
	//GETTERS AND SETTERS
	public ServerResponseType getType() {
		return type;
	}
	public void setType(ServerResponseType type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getX() {
		return x;
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
}
