DELIMITER $$
CREATE PROCEDURE REGISTRAR_MEDICO(
	IN CEDULA VARCHAR(150),
    IN PASSW VARCHAR(128),
    IN ROL_MEDICO ENUM("Medico"),
    IN NOMBRE_MEDICO VARCHAR(100),
    IN APELLIDO_P VARCHAR(100),
    IN APELLIDO_M VARCHAR(100),
    IN ESPECIALIDAD_MEDICO VARCHAR(50),
    IN ESTADO_MEDICO ENUM("Alta")
)
BEGIN
	-- EXIT HANDLER para revertir todos los cambios si sucede un error en una operación.
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Error en registro de paciente. Los cambios fueron revertidos.";
	END;
    -- Comienza la transacción.
	START TRANSACTION;
		-- 1.- Se inserta el usuario.
        INSERT INTO USUARIOS(
			USUARIO, 
            CONTRASENIA, 
            ROL
		)
        VALUES(
			CEDULA, PASSW, ROL_MEDICO
		);
		-- 2.- Se obtiene el ID generado por la tabla USUARIOS y se almacena en una variable.
        SET @ID_USUARIO = LAST_INSERT_ID();
		-- 3.- Se inserta el paciente.
        INSERT INTO MEDICOS(
			ID_MEDICO, 
			NOMBRES, 
            APELLIDO_PATERNO, 
            APELLIDO_MATERNO, 
            ESPECIALIDAD, 
            ESTADO
		)
        VALUES(
			@ID_USUARIO, 
            NOMBRE_MEDICO, 
            APELLIDO_P, 
            APELLIDO_M,
            ESPECIALIDAD_MEDICO, 
            ESTADO_MEDICO
		);
	COMMIT; -- Si no hubo ningún error, se guardan los cambios permanentemente.
END $$ -- Fin de procedimiento.
DELIMITER ;