/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.iConexion;
import Entidades.Cita;
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
            try (ResultSet rs = ps.executeQuery()) { // Ejecutar para obtener los resultados
                while (rs.next()) {
                    // Crear entidad Medico null porque no es relevante para la agenda
                    Medico medico = new Medico();
                    // Obtener datos relevantes del paciente
                    Paciente paciente = new Paciente(
                            "",
                            "",
                            rs.getString("NOMBRES_PACIENTE"),
                            rs.getString("APELLIDO_PATERNO_PACIENTE"),
                            rs.getString("APELLIDO_MATERNO_PACIENTE"),
                            "",
                            rs.getDate("FECHA_NACIMIENTO_PACIENTE").toLocalDate(),
                            rs.getString("ESTADO_PACIENTE"),
                            "",
                            "",
                            ""
                    );
                    // Almacenar los datos relevantes de la cita
                    Cita cita = new Cita(
                            rs.getInt("ID_CITA"),
                            rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                            medico,
                            paciente,
                            rs.getString("TIPO_CITA")
                    );
                    // Añadir objeto cita a la lista creada
                    agendaMedico.add(cita);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, "Error al consultar agenda del médico.", ex);
            throw new PersistenciaException("Error al consultar agenda del médico.", ex);
        }
        
        return agendaMedico;
    }
    
}
