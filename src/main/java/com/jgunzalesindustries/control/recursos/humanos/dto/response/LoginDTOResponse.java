package main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response;

public class LoginDTOResponse {
    private String names;
    private String lastname;
    private String passwordHash;
    private String rolName;

    public LoginDTOResponse(String names, String lastname, String passwordHash, String rolName) {
        this.names = names;
        this.lastname = lastname;
        this.passwordHash = passwordHash;
        this.rolName = rolName;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }
    
}
