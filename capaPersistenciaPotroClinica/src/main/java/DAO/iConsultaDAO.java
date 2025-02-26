/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Consulta;
import Excepciones.PersistenciaException;

/**
 * Interfaz de la clase ConsultaDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public interface iConsultaDAO {
    
    /**
     * Método que registra una consulta.
     * @param consulta Objeto de tipo Consulta.
     * @return True si se registra la consulta, false en caso contrario.
     * @throws PersistenciaException 
     */
    public boolean registrarConsulta(Consulta consulta) throws PersistenciaException;
    
}