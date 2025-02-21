/*
	Trigger para validar que la consulta que se quiere
    crear esté asociada a una cita de emergencia que
    está siendo atendida. En caso contrario, se lanza el
    trigger y no se guarda la consulta.
*/
DELIMITER //
CREATE TRIGGER ESTADO_CITAEMERGENCIA_CONSULTA
BEFORE INSERT ON CONSULTAS
FOR EACH ROW
BEGIN
	-- Declarar variable para guardar el estado de la cita
    DECLARE ESTADO_CITA VARCHAR(50);
    -- Obtener estado de la cita 
    SELECT ESTADO
    INTO ESTADO_CITA
    FROM CITAS_EMERGENCIA
    WHERE ID_CITA = NEW.ID_CITA;
    -- Validar que el estado de la cita sea atendida
    IF ESTADO_CITA != "Atendida" THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "No se puede registrar la consulta. La cita no ha sido atendida.";
	END IF;
END //
DELIMITER ;