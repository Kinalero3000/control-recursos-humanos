package main.java.com.jgunzalesindustries.control.recursos.humanos.repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.jgunzalesindustries.control.recursos.humanos.config.DataBaseConnection;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response.RolResponse;
import main.java.com.jgunzalesindustries.control.recursos.humanos.model.User;


public class UserRepository {

    public boolean existsByEmail(String email) {
        String sql = "SELECT id_user FROM users WHERE email = ?";
        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            throw new RuntimeException("Error al validar el email.", e);
        }
    }

    public boolean save(User user) {
        String sql = "INSERT INTO users (id_user, names, lastname, email, password_hash, id_rol) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {
            pstm.setString(1, user.getIdUser());
            pstm.setString(2, user.getNames());
            pstm.setString(3, user.getLastName());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, user.getPasswordHash());
            pstm.setString(6, user.getIdRol());        

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el usuario en la base de datos.", e);
        }
    }

    public String generateUserId() {
        return UUID.randomUUID().toString();
    }


    public ObservableList<RolResponse> findAllRoles() {
        String sql = "SELECT id_rol, rol FROM rol";
        ObservableList<RolResponse> list = FXCollections.observableArrayList();
        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) { // "while" porque ahora SÍ hay varias filas
                list.add(new RolResponse(rs.getString("id_rol"), rs.getString("rol")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar los roles.", e);
        }
    }
}

