package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import models.Member;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.ring;
import dto.ServerResponse;
import dto.ServerResponseType;

public class Engine extends Controller {
	
	
	static Map<String, Member> members = new HashMap<>();
	
    /**
     * Receive info from the ring
     */
    public static Result displayRing(String username) {
        if(username == null || username.trim().equals("")) {
            flash("error", "Please choose a valid username.");
            return redirect(routes.Application.index());
        }
        return ok(ring.render(username));
    }
    
    /**
     * Send info to the ring
     */
    public static WebSocket<JsonNode> connections(final String username) {
    	
    	
        return new WebSocket<JsonNode>() {
            // Called when the Websocket Handshake is done.
            public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
            	
            	addMember(username, out);
                
            	// ########################################
            	// Bind IN connection events
            	// ########################################
            	in.onMessage(new Callback<JsonNode>() {
                    public void invoke(JsonNode event) {
                    	
//                    	System.out.println("client call server");
                    	System.out.println("values obtained: " + event.toString());
                    	
                    	try {
						
                    		String previous_x = members.get(username).getX();
                        	String previous_y = members.get(username).getY();
                        	String new_x = event.get("x").asText();
                        	String new_y = event.get("y").asText();
                        	String action = event.get("action").asText();
                        	
//                        	System.out.println("username:" + username);
//                        	System.out.println("new_x:" + new_x);
//                        	System.out.println("new_y:" + new_y);
                            
                            updateMember(username, new_x, new_y);
                            notifyMembers(username,previous_x, previous_y, new_x, new_y);
                    		
						} catch (Exception e) {
							System.out.println("fallo obtener algun valor");
							e.printStackTrace();
						}
                    	

                    } 
                 });
            	
            	 // When the socket is closed.
                in.onClose(new Callback0() {
                   public void invoke() {
                	   // TODO
                   }
                });
                
            	// ########################################
            	// Bind OUT connection events
            	// ########################################
            	
                ObjectNode response = Json.newObject();
                response.put("message", "Connected...");
            	out.write(response);
            
            }
        };
    }
    
    public static void addMember(String username, WebSocket.Out<JsonNode> out) {
    	Member member = new Member();
    	member.setUserName(username);
    	member.setOut(out);
    	members.put(username, member);    	
    }
    
    public static void updateMember(String username, String x, String y) {
    	Member member = members.get(username);
    	member.setX(x);
    	member.setY(y);
    }
    
    public static void notifyMembers(String username, String previous_x, String previous_y, String x, String y) {
    	
    	ServerResponse serverResponse = new ServerResponse(ServerResponseType.UPDATE_SOME, username, previous_x, previous_y , x, y);
    	System.out.println("notify members");
    	System.out.println(Json.toJson(serverResponse));
    	
    	
    	for(String key : members.keySet()) {
    		Member member = members.get(key);
    		member.getOut().write(Json.toJson(serverResponse));
    	}
    	
    }
    
    public static Result members() {
    	ObjectNode response = Json.newObject();
    	ObjectMapper objectMapper = new ObjectMapper();
        try {
			response.put("members", objectMapper.writeValueAsString(members));
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
        return ok(response);
    }
}
