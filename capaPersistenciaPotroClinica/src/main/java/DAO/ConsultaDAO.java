/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.iConexion;
import Entidades.Consulta;
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
public class ConsultaDAO implements iConsultaDAO {
    iConexion conexion;

    public ConsultaDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public boolean registrarConsulta(Consulta consulta) throws PersistenciaException {
        // Sentencia de MySQL
        String comandoSQL = "CALL REGISTRAR_CONSULTA(?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {
                // Agregar valores a la tabla de consultas
                cs.setString(1, consulta.getMotivo());
                cs.setString(2, consulta.getDiagnostico());
                cs.setString(3, consulta.getTratamiento());
                cs.setInt(4, consulta.getIdCita());
                
                cs.execute();
                return true; 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al insertar una consulta");
        }
    }
    
}
