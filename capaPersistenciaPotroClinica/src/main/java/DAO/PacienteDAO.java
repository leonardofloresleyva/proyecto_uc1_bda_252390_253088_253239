package DAO;

import Conexion.iConexion;
import Entidades.Paciente;
import Excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class PacienteDAO implements iPacienteDAO {

    iConexion conexion;

    public PacienteDAO(iConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        // Comando SQL para insertar un paciente
        String comandoSQL = "CALL REGISTRAR_PACIENTE2(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement ps = con.prepareCall(comandoSQL)) {

                // Agregar valores a tabla de usuarios y direcciones
                // Datos de tabla usuarios
                ps.setString(1, paciente.getUsuario());
                ps.setString(2, paciente.getContrasenia());
                ps.setString(3, paciente.getRol());
                // Datos de tabla pacientes
                ps.setString(4, paciente.getNombres());
                ps.setString(5, paciente.getApellidoPaterno());
                ps.setString(6, paciente.getApellidoMaterno());
                ps.setString(7, paciente.getTelefono());
                ps.setObject(8, paciente.getFechaNacimiento());
                ps.setString(9, paciente.getEstado());
                // Datos de tabla direcciones
                ps.setString(10, paciente.getColonia());
                ps.setString(11, paciente.getCalle());
                ps.setString(12, paciente.getNumero());

                // Ejecutar y regresar verdadero
                ps.execute();   
                return true;
            }

        } catch (Exception e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenciaException("Error al registrar el paciente.", e);
        }

    }
}
