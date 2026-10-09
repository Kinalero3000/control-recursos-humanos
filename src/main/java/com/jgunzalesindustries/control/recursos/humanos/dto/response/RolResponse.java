package main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response;

public class RolResponse {
    private String idRol;
    private String rolName;

    public RolResponse(String idRol, String rolName) {
        this.idRol = idRol;
        this.rolName = rolName;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }
    
    @Override
    public String toString(){
    return rolName;
    
    }
    
}
