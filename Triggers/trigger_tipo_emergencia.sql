/*
	Trigger que valida que el tipo de cita
    sea igual a Emergencia para las citas 
    de emergencia.
*/
DELIMITER $$
CREATE TRIGGER TIPO_CITA_EMERGENCIA
BEFORE INSERT ON CITAS_EMERGENCIA
FOR EACH ROW
BEGIN
	DECLARE CITA_TIPO ENUM("Previa", "Emergencia");
    SELECT TIPO_CITA INTO CITA_TIPO
    FROM CITAS
    WHERE ID_CITA = NEW.ID_CITA_EMERGENCIA;
    
    IF CITA_TIPO != "Emergencia"
    THEN
		SIGNAL SQLSTATE "45000"
        SET MESSAGE_TEXT = "El tipo de cita debe ser de emergencia";
	END IF;
END $$
DELIMITER ;