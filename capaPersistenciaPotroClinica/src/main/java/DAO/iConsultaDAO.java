/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Consulta;
import Excepciones.PersistenciaException;

/**
 *
 * @author Ximena
 */
public interface iConsultaDAO {
    
    public boolean registrarConsulta(Consulta consulta) throws PersistenciaException;
    
}
