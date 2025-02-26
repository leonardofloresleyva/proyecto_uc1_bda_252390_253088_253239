package BO;

import Conexion.iConexion;
import DAO.ConsultaDAO;
import DTO.ConsultaDTO;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import Mapper.CitaMapper;
import Mapper.ConsultaMapper;
import Mapper.MedicoMapper;
import Mapper.PacienteMapper;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class ConsultaBO {
    
    private final ConsultaDAO consultaDAO;
    private final ConsultaMapper consultaMapper;

    public ConsultaBO(iConexion conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
        this.consultaMapper = new ConsultaMapper(new CitaMapper(new MedicoMapper(), new PacienteMapper()));
    }
    
    
    public boolean registrarConsultaPrevia(ConsultaDTO consulta) throws NegocioException{
        
        verificarConsulta(consulta);
        
        try{
            return consultaDAO.registrarConsulta(consultaMapper.toEntity(consulta));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public boolean registrarConsultaEmergencia(ConsultaDTO consulta, int folio) throws NegocioException{
        verificarConsulta(consulta);
        if(folio < 0)
            throw new NegocioException("El folio no puede ser menor que cero.");
        
        if(folio < 10000000 || folio > 99999999)
            throw new NegocioException("El folio debe contener 8 digitos.");
        
        if(folio > Integer.MAX_VALUE)
            throw new NegocioException("Ingrese un folio valido.");
        try {
            if(consultaDAO.verificarFolio(consulta.getCita().getIdCita(), folio))
                return consultaDAO.registrarConsulta(consultaMapper.toEntity(consulta));
            else
                throw new NegocioException("Error, el folio no corresponde a la cita.");
            
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    private void verificarConsulta(ConsultaDTO consulta) throws NegocioException{
        if(consulta == null)
            throw new NegocioException("La consulta no puede ser nula.");
        
        int idCita = consulta.getCita().getIdCita();
        String 
                motivo = consulta.getMotivo(),
                diagnostico = consulta.getDiagnostico(),
                tratamiento = consulta.getTratamiento();
        
        if(idCita < 1)
            throw new NegocioException("No se permiten IDs menores a uno. Intentelo de nuevo.");

        if(idCita > Integer.MAX_VALUE)
            throw new NegocioException("Ingrese un id valido.");
        
        if(motivo == null || motivo.isEmpty())
            throw new NegocioException("El motivo no puede esta vacio.");
        
        if(motivo.length() > 100)
            throw new NegocioException("El motivo no puede tener mas de 100 caracteres.");
        
        if(diagnostico == null || diagnostico.isEmpty())
            throw new NegocioException("El motivo no puede esta vacio.");
        
        if(tratamiento == null || tratamiento.isEmpty())
            throw new NegocioException("El motivo no puede esta vacio.");
    }
}