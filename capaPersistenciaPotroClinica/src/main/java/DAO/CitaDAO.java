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
import java.sql.SQLException;
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
        String sentenciaSQL = "CALL REGISTRAR_CITA(?, ?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion()) {
            // Llamar al procedimiento almacenado
            try (CallableStatement cs = con.prepareCall(sentenciaSQL)) {
                
                // Agregar los parámetros del procedimiento
                cs.setObject(1, cita.getFechaHora());
                cs.setInt(2, cita.getIdMedico());
                cs.setInt(3, cita.getIdPaciente());
                cs.setString(4, cita.getEstado());
                cs.setString(5, cita.getTipoCita());
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al registrar cita.", ex);
        }
        
    }
    
}
