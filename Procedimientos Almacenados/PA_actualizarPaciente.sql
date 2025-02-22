/*
	Procedimiento almacenado que actualiza los
    datos personales de un paciente.
*/
DELIMITER $$
CREATE PROCEDURE ACTUALIZAR_PACIENTE(
	IN ID_USUARIO INT,
	IN NOMBRE_PACIENTE VARCHAR(100),
    IN APELLIDO_P VARCHAR(100),
    IN APELLIDO_M VARCHAR(100),
    IN TELEFONO_PACIENTE VARCHAR(20),
    IN FECHA_N DATE,
    IN COLONIA_PACIENTE VARCHAR(100),
    IN CALLE_PACIENTE VARCHAR(100),
    IN NUMERO_PACIENTE VARCHAR(20)
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
			SET MESSAGE_TEXT = "El paciente a actualizar no existe en la base de datos";
		ELSE
			-- Se insertan los datos personales actualizados en la tabla PACIENTES.
			UPDATE PACIENTES SET
				NOMBRES = NOMBRE_PACIENTE,
				APELLIDO_PATERNO = APELLIDO_P,
				APELLIDO_MATERNO = APELLIDO_M,
				TELEFONO = TELEFONO_PACIENTE,
				FECHA_NACIMIENTO = FECHA_N
			WHERE ID_PACIENTE = ID_USUARIO;
            
			-- Se inserta la dirección actualizada en la tabla DIRECCIONES.
			UPDATE DIRECCIONES SET
				COLONIA = COLONIA_PACIENTE,
				CALLE = CALLE_PACIENTE,
				NUMERO = NUMERO_PACIENTE
			WHERE ID_PACIENTE = ID_USUARIO;
		END IF;
	COMMIT;
END $$
DELIMITER ;