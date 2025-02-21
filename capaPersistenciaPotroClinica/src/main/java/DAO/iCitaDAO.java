/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Cita;
import Excepciones.PersistenciaException;

/**
 *
 * @author Ximena
 */
public interface iCitaDAO {
    
    public boolean registrarCita(Cita cita) throws PersistenciaException;
}
