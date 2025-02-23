/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.iConexion;
import Entidades.Cita;
import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ximena
 */
public class MedicoDAO implements iMedicoDAO {
    iConexion conexion;

    public MedicoDAO(iConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean darDeBajaMedico(int id) throws PersistenciaException {
        String comandoSQL = "CALL DAR_BAJA_MEDICO(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {
                // Agregar parámetro a la llamada
                cs.setInt(1, id);
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, "Error al dar de baja al médico.", ex);
            throw new PersistenciaException("Error al dar de baja al médico.", ex);
        }
    }

    @Override
    public boolean darDeAltaMedico(int id) throws PersistenciaException {
        String comandoSQL = "CALL DAR_ALTA_MEDICO(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {
                // Agregar parámetro a la llamada
                cs.setInt(1, id);
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, "Error al dar de alta al médico.", ex);
            throw new PersistenciaException("Error al dar de alta al médico.", ex);
        }
    }

    @Override
    public List<Cita> mostrarAgendaMedico(int id) throws PersistenciaException {
        List<Cita> agendaMedico = new ArrayList<>();
        String comandoSQL = "SELECT * FROM AGENDA_CITAS_MEDICO WHERE ID_MEDICO = ?";
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)) {
            // Agregar parámetro a la llamada
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Crear objeto Medico
                    Medico medico = new Medico(
                        rs.getInt("ID_MEDICO"),
                        rs.getString("USUARIO"),
                        rs.getString("CONTRASENIA"),
                        rs.getString("ROL"),
                        rs.getString("NOMBRES"),
                        rs.getString("APELLIDO_PATERNO"),
                        rs.getString("APELLIDO_MATERNO"),
                        rs.getString("ESPECIALIDAD"),
                        rs.getString("ESTADO")
                    );
                    // Crear objeto Paciente
                    Paciente paciente = new Paciente(
                        rs.getInt("ID_PACIENTE"),
                        rs.getString("USUARIO"),
                        rs.getString("CONTRASENIA"),
                        rs.getString("ROL"),
                        rs.getString("NOMBRES"),
                        rs.getString("APELLIDO_PATERNO"),
                        rs.getString("APELLIDO_MATERNO"),
                        rs.getString("TELEFONO"),
                        rs.getDate("FECHA_NACIMIENTO").toLocalDate(),
                        rs.getString("ESTADO"),
                        rs.getString("COLONIA"),
                        rs.getString("CALLE"),
                        rs.getString("NUMERO")
                    );
                    // Crear la cita y agregar a la lista
                    agendaMedico.add(new Cita(
                        rs.getInt("ID_CITA"),
                        rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                        medico,
                        paciente,
                        rs.getString("TIPO_CITA")
                    ));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, "Error al consultar agenda del médico.", ex);
            throw new PersistenciaException("Error al consultar agenda del médico.", ex);
        }
        
        return agendaMedico;
    }
    
}
