/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Cita;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ximena
 */
public interface iCitaDAO {
    
    public boolean registrarCita(Cita cita) throws PersistenciaException;
    
    public boolean cancelarCita(int idCita) throws PersistenciaException;
    
    public List<Cita> consultarCitasPorEspecialidad(String especialidad) throws PersistenciaException;
    
    public List<Cita> consultarCitasRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
}
