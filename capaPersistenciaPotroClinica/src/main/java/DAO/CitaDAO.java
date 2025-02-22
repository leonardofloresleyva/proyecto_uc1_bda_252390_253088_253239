/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.iConexion;
import Entidades.Cita;
import Excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ximena
 */
public class CitaDAO implements iCitaDAO {

    iConexion conexion;

    public CitaDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public boolean registrarCita(Cita cita) throws PersistenciaException {
        // Sentencia sql
        String sentenciaSQL = "CALL REGISTRAR_CITA(?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion()) {
            // Llamar al procedimiento almacenado
            try (CallableStatement cs = con.prepareCall(sentenciaSQL)) {
                
                // Agregar los parámetros del procedimiento
                cs.setObject(1, cita.getFechaHora());
                cs.setInt(2, cita.getIdMedico());
                cs.setInt(3, cita.getIdPaciente());
                cs.setString(4, cita.getTipoCita());
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al registrar cita.", ex);
            throw new PersistenciaException("Error al registrar cita.", ex);
        } 
    }

    @Override
    public List<Cita> consultarCitasPorEspecialidad(String especialidad) throws PersistenciaException {
       String comandoSQL = "SELECT * FROM HISTORIAL_CITAS_PACIENTES WHERE ESPECIALIDAD = ?";
       List<Cita> citasEspecialidad = new ArrayList<>();

       try (Connection con = conexion.crearConexion();
            PreparedStatement ps = con.prepareStatement(comandoSQL)) {

           ps.setString(1, especialidad); // Asignamos el valor del parámetro
           try (ResultSet rs = ps.executeQuery()) { // Ejecutamos la consulta y obtenemos resultados
               while (rs.next()) {
                   Cita cita = new Cita(
                           rs.getInt("ID_CITA"),
                           rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                           rs.getInt("ID_MEDICO"),
                           rs.getInt("ID_PACIENTE"),
                           rs.getString("TIPO_CITA")
                   );
                   // Añadir cada cita obtenida a la lista
                   citasEspecialidad.add(cita);
               }
           }
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al consultar citas por especialidad", ex);
            throw new PersistenciaException("Error al consultar citas por especialidad.", ex);
        }
       // Regresar la lista de citas obtenidas
        return citasEspecialidad; 
    }

    @Override
    public List<Cita> consultarCitasRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException {
        String comandoSQL = "SELECT * FROM HISTORIAL_CITAS_PACIENTES WHERE FECHA_HORA BETWEEN ? AND ?";
        List<Cita> citasRangoFechas = new ArrayList<>();
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)) {
            
            ps.setObject(1, fechaInicio);
            ps.setObject(2, fechaFin);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cita cita = new Cita(
                           rs.getInt("ID_CITA"),
                           rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                           rs.getInt("ID_MEDICO"),
                           rs.getInt("ID_PACIENTE"),
                           rs.getString("TIPO_CITA")
                    );
                    // Añadir cita obtenida a la lista
                    citasRangoFechas.add(cita);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al consultar citas por rango de fechas.", ex);
            throw new PersistenciaException("Error al consultar citas por rango de fechas entre: " + fechaInicio + " y " + fechaFin, ex);
        }
        // Regresar la lista obtenida
        return citasRangoFechas;
    }

    @Override
    public boolean cancelarCita(int idCita) throws PersistenciaException {
        String consultaSQL = "CALL CANCELAR_CITA(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(consultaSQL)) {
                // Agregar parámetro a la llamada
                cs.setInt(1, idCita);
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al cancelar cita.", ex);
            throw new PersistenciaException("Error al cancelar cita.", ex);
        }
    }
    
}
