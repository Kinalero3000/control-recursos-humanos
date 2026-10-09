package main.java.com.jgunzalesindustries.control.recursos.humanos.repository;

import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request.LoginDTORequest;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response.LoginDTOResponse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import main.java.com.jgunzalesindustries.control.recursos.humanos.config.DataBaseConnection;

public class AuthRepository {
    public LoginDTOResponse findUserByEmail(LoginDTORequest request) {

    
        String sql = "SELECT u.names, u.lastname, u.password_hash, r.rol_name "
                   + "FROM users AS u "
                   + "INNER JOIN rol AS r ON u.id_rol = r.id_rol "
                   + "WHERE u.email = ?";

        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {

            pstm.setString(1, request.getEmail()); 

            ResultSet rs = pstm.executeQuery();    

           
            if (rs.next()) {
                return new LoginDTOResponse(
                        rs.getString("names"),          // nombre de la COLUMNA en la BD
                        rs.getString("lastname"),
                        rs.getString("password_hash"),
                        rs.getString("rol_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el usuario en la base de datos.", e);
        }

        return null; // no se encontró el correo
    }

}
