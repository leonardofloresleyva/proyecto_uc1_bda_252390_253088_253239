/*
	Trigger que valida que el tipo de cita
    sea igual a Previa para las citas previas.
*/
DELIMITER $$
CREATE TRIGGER TIPO_CITA_PREVIA
BEFORE INSERT ON CITAS_PREVIAS
FOR EACH ROW
BEGIN
	DECLARE CITA_TIPO ENUM("Previa", "Emergencia");
    SELECT TIPO_CITA INTO CITA_TIPO
    FROM CITAS
    WHERE ID_CITA = NEW.ID_CITA_PREVIA;
    
    IF CITA_TIPO != "Previa"
    THEN
		SIGNAL SQLSTATE "45000"
        SET MESSAGE_TEXT = "El tipo de cita debe ser previa";
	END IF;
END $$
DELIMITER ;