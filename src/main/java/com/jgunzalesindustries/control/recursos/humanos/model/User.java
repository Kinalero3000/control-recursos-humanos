package main.java.com.jgunzalesindustries.control.recursos.humanos.model;

public class User {
    private String idUser;
    private String names;
    private String lastname;
    private String email;
    private String passwordHash;
    private String idRol;

    public User(String idUser, String names, String lastName, String email, String passwordHash, String idRol) {
        this.idUser = idUser;
        this.names = names;
        this.lastname = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.idRol = idRol;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
    
    
}
