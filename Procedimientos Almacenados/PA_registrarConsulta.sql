/*
	Procedimiento almacenado que registra una consulta en la base
    de datos. Recibe como parámetros los atributos de la consulta.
    Valida si el id de la cita existe, de ser así, inserta la nueva
    consulta en la base de datos.
*/
DELIMITER //
CREATE PROCEDURE REGISTRAR_CONSULTA (
	IN MOTIVO_CONSULTA VARCHAR(100),
    IN DIAGNOSTICO_CONSULTA TEXT,
    IN TRATAMIENTO_CONSULTA TEXT,
    IN ID INT
)
BEGIN
	-- EXIT HANDLER para revertir todos los cambios si sucede un error en una operación.
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Error en registro de consulta. Los cambios fueron revertidos.";
	END;
	-- Empezar transacción
    START TRANSACTION;
		-- Se obtiene el tipo de cita.
		SET @CITA_TIPO = (
			SELECT TIPO_CITA FROM CITAS
            WHERE ID_CITA = ID
		);
        IF @CITA_TIPO = "Emergencia" -- Si se trata de una cita de emergencia.
        THEN
			-- Se cambia el estado de la cita de emergencia a Atendida.
			UPDATE CITAS_EMERGENCIA SET ESTADO = "Atendida"
            WHERE ID_CITA_EMERGENCIA = ID;
		ELSE -- Si se trata de una cita previa.
			-- Se cambia el estado de la cita de previa a Atendida.
			UPDATE CITAS_PREVIAS SET ESTADO = "Atendida"
            WHERE ID_CITA_PREVIA = ID;
		END IF;
		-- Se inserta la consulta.
		INSERT INTO CONSULTAS (MOTIVO, DIAGNOSTICO, TRATAMIENTO, ID_CITA)
			VALUES (MOTIVO_CONSULTA, DIAGNOSTICO_CONSULTA, TRATAMIENTO_CONSULTA, ID);
	COMMIT;
END //
DELIMITER ;