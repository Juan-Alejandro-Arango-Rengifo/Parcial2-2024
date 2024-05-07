package org.example.repository;

import org.example.modelo.Paciente;
import org.example.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository<Paciente>{

    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstace();
    }
    @Override
    public List<Paciente> Listar() {
        List<Paciente> paciente = new ArrayList<>();
        try(Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM paciente")){
            while (rs.next()) {
                Paciente p = crearPaciente(rs);
                paciente.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }

    @Override
    public Paciente porid(Long id) {
        Paciente paciente = null;
        try(PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM paciente WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    paciente = crearPaciente(rs);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public void guardar(Paciente paciente) {

        String sql;
        if (paciente.getId() != 0 && paciente.getId()>0) {
            sql = "UPDATE  paciente SET nombre=? raza=? peso=?";
        } else {
            sql = "INSERT INTO paciente(nombre, raza, peso) VALUES(?,?,?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getRaza());
            stmt.setLong(3, paciente.getPeso());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(long id) {

        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM paciente WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

    }

    private static Paciente crearPaciente(ResultSet rs) throws SQLException {
        Paciente p = new Paciente();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setRaza(rs.getString("raza"));
        p.setPeso(rs.getLong("peso"));
        return p;
    }
}
