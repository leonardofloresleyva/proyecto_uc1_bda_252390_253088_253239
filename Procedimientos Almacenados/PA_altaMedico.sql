/*
	Procedimiento almacenado que permite a un médico darse de
    alta. Recibe como parámetro el id del médico y cambia el
    estado del médico de "Baja" a "Alta". Indica si el médico
    que se quiere dar de alta ya se encuentra dado de alta.
*/
DELIMITER //
CREATE PROCEDURE DAR_ALTA_MEDICO (
	IN MEDICO_ID INT
)
BEGIN
	DECLARE MEDICO_ALTA INT;
	-- Verificar que el médico exista en la base de datos
	IF NOT EXISTS (
		SELECT 1 FROM USUARIOS
		WHERE ID = MEDICO_ID
	) THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "El médico no existe en la base de datos.";
	ELSE 
		-- Verificar si el estado del médico ya es "Alta"
        IF EXISTS (
			SELECT 1 FROM MEDICOS
            WHERE ID_MEDICO = MEDICO_ID
            AND ESTADO = "Alta"
        ) THEN
			SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = "El médico ya está dado de alta.";
		ELSE
			START TRANSACTION;
				UPDATE MEDICOS
				SET ESTADO = "Alta"
				WHERE ID_MEDICO = MEDICO_ID;
			COMMIT;
		END IF;
	END IF;
END //
DELIMITER ;