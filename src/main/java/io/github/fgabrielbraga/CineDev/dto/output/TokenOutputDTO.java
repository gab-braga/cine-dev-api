package io.github.fgabrielbraga.CineDev.dto.output;

public class TokenOutputDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public TokenOutputDTO() {
    }

    public TokenOutputDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
