package dto;

public class ServerResponse {

	private ServerResponseType type;
	private String username;
	
	private String previous_x;
	private String previous_y;

	private String x;
	private String y;

	public ServerResponse(ServerResponseType type, String username,
			String previous_x, String previous_y, String x, String y) {
		this.type = type;
		this.username = username;
		this.previous_x = previous_x;
		this.previous_y = previous_y;
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

	public String getPrevious_x() {
		return previous_x;
	}

	public void setPrevious_x(String previous_x) {
		this.previous_x = previous_x;
	}

	public String getPrevious_y() {
		return previous_y;
	}

	public void setPrevious_y(String previous_y) {
		this.previous_y = previous_y;
	}
}
