package PruebasNeogocio;

import BO.MedicoBO;
import Conexion.Conexion;
import Conexion.iConexion;
import DTO.CitaDTO;
import Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author PC WHITE WOLF
 */
public class PruebasNegocio {
    public static void main(String[] args) throws NegocioException {
        iConexion conexion = new Conexion();
        MedicoBO medicoBO = new MedicoBO(conexion);
        
        List<CitaDTO> citasMedico = medicoBO.agendaMedico(9);
        for(CitaDTO cita : citasMedico){
            System.out.println(cita);
        }
    }
}
