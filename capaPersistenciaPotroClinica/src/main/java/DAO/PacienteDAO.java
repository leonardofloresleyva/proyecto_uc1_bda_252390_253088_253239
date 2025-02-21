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
        String comandoSQL = "CALL REGISTRAR_PACIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {

                // Agregar valores a tabla de usuarios y direcciones
                // Datos de tabla usuarios
                cs.setString(1, paciente.getUsuario());
                cs.setString(2, paciente.getContrasenia());
                cs.setString(3, paciente.getRol());
                // Datos de tabla pacientes
                cs.setString(4, paciente.getNombres());
                cs.setString(5, paciente.getApellidoPaterno());
                cs.setString(6, paciente.getApellidoMaterno());
                cs.setString(7, paciente.getTelefono());
                cs.setObject(8, paciente.getFechaNacimiento());
                cs.setString(9, paciente.getEstado());
                // Datos de tabla direcciones
                cs.setString(10, paciente.getColonia());
                cs.setString(11, paciente.getCalle());
                cs.setString(12, paciente.getNumero());

                // Ejecutar y regresar verdadero
                cs.execute();   
                return true;
            }

        } catch (Exception e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenciaException("Error al registrar el paciente.", e);
        }

    }
}
