/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Usuario;
import Excepciones.PersistenciaException;

/**
 * Interfaz del usuario
 * @author Ximena
 */
public interface iUsuarioDAO {
    
    /**
     * Método que registra un usuario nuevo
     * @param usuario Objeto usuario
     * @return ID del usuario
     * @throws PersistenciaException Si no se pudo crear el usuario
     */
    public int registrarUsuario(Usuario usuario) throws PersistenciaException;
    
}
