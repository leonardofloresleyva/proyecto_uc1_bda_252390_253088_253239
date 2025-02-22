/*
	Procedimiento almacenado que cambia
    la contrasenia de un paciente.
*/
DELIMITER $$
CREATE PROCEDURE CAMBIAR_CONTRASENIA(
	IN ID_USUARIO INT,
    IN NUEVA_CONTRASENIA VARCHAR(128)
)
BEGIN
	START TRANSACTION;
		-- Se verifica que el paciente exista en la base de datos.
		IF NOT EXISTS(
			SELECT 1 FROM USUARIOS
			WHERE ID = ID_USUARIO
		) THEN
			-- Si el usuario no existe, se envía mensaje de error.
			SIGNAL SQLSTATE "45000"
			SET MESSAGE_TEXT = "El paciente no existe en la base de datos";
		ELSE
			-- Se obtiene la contrasenia original del paciente.
			SET @CONTRASENIA_ANTIGUA = (
				SELECT CONTRASENIA FROM USUARIOS
                WHERE ID = ID_USUARIO
            );
            IF @CONTRASENIA_ANTIGUA = NUEVA_CONTRASENIA THEN
				-- Si la contrasenia nueva es igual que la anterior, se envía mensaje de error.
				SIGNAL SQLSTATE "45000"
				SET MESSAGE_TEXT = "La contrasenia nueva no puede ser igual que la anterior";
			ELSE
				-- Se cambia la contrasenia.
				UPDATE USUARIOS
				SET CONTRASENIA = NUEVA_CONTRASENIA
				WHERE ID = ID_USUARIO;
			END IF;
		END IF;
	COMMIT;
END $$
DELIMITER ;