package main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request;

public class RegisterDTORequest {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String confirmPassword;
    private String idRol;

    public RegisterDTORequest(String name, String lastname, String email, String password, String confirmPassword, String idRol) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.idRol = idRol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
    
    
    
}
