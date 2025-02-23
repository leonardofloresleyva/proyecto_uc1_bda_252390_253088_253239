/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Consulta;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ximena
 */
public interface iConsultaDAO {
    
    public boolean registrarConsulta(Consulta consulta) throws PersistenciaException;
    
    public List<Consulta> consultasPorEspecialidad(String especialidad) throws PersistenciaException;
    
    public List<Consulta> consultasRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
}
