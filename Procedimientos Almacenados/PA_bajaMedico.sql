/*
	Procedimiento almacenado que permite a un médico darse de
    baja. Recibe como parámetro el id del médico y cambia el
    estado del médico de "Alta" a "Baja". No permite dar un
    médico de baja si este tiene citas en su agenda o citas
    activas.
*/
DELIMITER //
CREATE PROCEDURE DAR_BAJA_MEDICO(
	IN MEDICO_ID INT
)
BEGIN
		-- Declarar variables donde se guardará si el médico tiene citas pendientes
        DECLARE CITAS_PENDIENTES INT;
		-- Verificar que el médico exista en la base de datos
        IF NOT EXISTS (
			SELECT 1 FROM USUARIOS
			WHERE ID = MEDICO_ID
		) THEN
			SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = "El médico no existe en la base de datos.";
		ELSE 
            -- Obtener si el médico tiene citas en su agenda (que no han sido atendidas)
            SELECT COUNT(*)
            INTO CITAS_PENDIENTES
            FROM CITAS AS C
            LEFT JOIN CITAS_PREVIAS AS CP
            ON CP.ID_CITA_PREVIA = C.ID_CITA
            LEFT JOIN CITAS_EMERGENCIA AS CE
            ON CE.ID_CITA_EMERGENCIA = C.ID_CITA
            WHERE C.ID_MEDICO = MEDICO_ID
            AND (CP.ESTADO = "No atendida"
				OR CE.ESTADO = "No atendida"
                OR C.FECHA_HORA = NOW()
			);
            -- Verificar si tiene citas pendientes o activas
            IF CITAS_PENDIENTES > 0 THEN
				SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = "El médico no puede darse de baja porque tiene citas activas y pendientes.";
			ELSE 
				START TRANSACTION;
					UPDATE MEDICOS
                    SET ESTADO = 'Baja'
                    WHERE ID_MEDICO = MEDICO_ID;
				COMMIT;
			END IF;
		END IF;
END //
DELIMITER ;