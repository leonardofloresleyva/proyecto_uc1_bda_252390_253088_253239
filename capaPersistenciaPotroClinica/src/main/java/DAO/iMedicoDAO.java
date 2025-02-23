/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Cita;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Ximena
 */
public interface iMedicoDAO {
    
    public boolean darDeBajaMedico(int id) throws PersistenciaException;
    
    public boolean darDeAltaMedico(int id) throws PersistenciaException;
    
    public List<Cita> mostrarAgendaMedico(int id) throws PersistenciaException;
}
