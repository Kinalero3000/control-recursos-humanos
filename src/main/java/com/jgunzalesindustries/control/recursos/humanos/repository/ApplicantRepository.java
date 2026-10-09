package main.java.com.jgunzalesindustries.control.recursos.humanos.repository;
import main.java.com.jgunzalesindustries.control.recursos.humanos.config.DataBaseConnection;
import main.java.com.jgunzalesindustries.control.recursos.humanos.model.Applicant;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicantRepository {
    
    
    public void save(Applicant a) {
        String sql = "INSERT INTO applicant "
                   + "(id_applicant, names_applicant, lastname, email, phone, adress_id, age) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {
            ps.setString(1, a.getApplicant());
            ps.setString(2, a.getNamesApplicant());
            ps.setString(3, a.getLastName());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getPhone());
            ps.setString(6, a.getAddresId());
            ps.setInt(7, a.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el aplicante: " + e.getMessage(), e);
        }
    }

    
    public void update(Applicant a) {
        String sql = "UPDATE applicant SET names_applicant = ?, lastname = ?, email = ?, "
                   + "phone = ?, age = ?"
                   + "WHERE id_applicant = ?";
        try (PreparedStatement ps = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {
            ps.setString(1, a.getNamesApplicant());
            ps.setString(2, a.getLastName());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPhone());
            ps.setInt(5, a.getAge());
            ps.setString(6, a.getApplicant()); 
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el cliente: " + e.getMessage(), e);
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM applicant WHERE id_applicant = ?";
        try (PreparedStatement ps = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage(), e);
        }
    }



    public Optional<Applicant> findById(String id) {
        String sql = "SELECT * FROM applicant WHERE id_applicant = ?";
        try (PreparedStatement ps = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el cliente: " + e.getMessage(), e);
        }
        return Optional.empty();
    }



    public List<Applicant> findAll() {
        String sql = "SELECT * FROM applicant ORDER BY age DESC";
        List<Applicant> applicants = new ArrayList<>();
        try (PreparedStatement ps = DataBaseConnection.getDataBaseConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                applicants.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los clientes: " + e.getMessage(), e);
        }
        return applicants;
    }

    private Applicant mapRow(ResultSet rs) throws SQLException {
        return new Applicant(
                rs.getString("id_applicant"),
                rs.getString("names_aplicant"),
                rs.getString("lastname"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("adress_id"),
                rs.getInt("age"));
    }
}


