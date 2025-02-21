/*
	Trigger para validar que la fecha que se inserta en la
    cita esté dentro del horario del médico especificado.
*/
DELIMITER //
CREATE TRIGGER VALIDAR_HORARIO_CITA
BEFORE INSERT ON CITAS
FOR EACH ROW
BEGIN
	IF NOT EXISTS (
		SELECT 1
        FROM HOARIOS_MEDICOS
        WHERE ID_MEDICO = NEW.ID_MEDICO
        AND DIA_SEMANA = DAYNAME(NEW.FECHA_HORA) -- Toma el día de la semana de la fecha
        AND TIME(NEW.FECHA_HORA) BETWEEN HORA_ENTRADA AND HORA_SALIDA -- Compara que la hora esté dentro de las del horario del médico
    ) THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "La fecha y hora elegidas no están disponibles dentro del horario del médico.";
	END IF;
END //
DELIMITER ;