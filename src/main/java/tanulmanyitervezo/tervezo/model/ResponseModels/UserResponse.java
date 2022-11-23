package tanulmanyitervezo.tervezo.model.ResponseModels;

public class UserResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String role;

    public UserResponse(String accessToken, int id, String role) {
        this.token = accessToken;
        this.id = id;
        this.role = role;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }
}
