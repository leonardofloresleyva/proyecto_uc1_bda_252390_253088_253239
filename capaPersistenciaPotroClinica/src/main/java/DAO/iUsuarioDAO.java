/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Usuario;
import Excepciones.PersistenciaException;

/**
 *
 * @author Ximena
 */
public interface iUsuarioDAO {
    
    public int registrarUsuario(Usuario usuario) throws PersistenciaException;
    
}
