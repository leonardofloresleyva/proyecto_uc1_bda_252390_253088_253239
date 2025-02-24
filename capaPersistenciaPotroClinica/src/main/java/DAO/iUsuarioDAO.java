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
    
    public boolean iniciarSesion(String usuario, String contrasenia) throws PersistenciaException;
    
}
