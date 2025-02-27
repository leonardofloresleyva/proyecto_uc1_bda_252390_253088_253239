/*
	Trigger por si se intenta insertar una fecha de 
    nacimiento mayor que la fecha actual o con
    antigüedad mayor a 120 años, a partir de la
    fecha actual.
*/
DELIMITER $$
CREATE TRIGGER FECHA_INCORRECTA
BEFORE INSERT ON PACIENTES
FOR EACH ROW
BEGIN
	-- Si la nueva fecha es mayor que la fecha actual, se dispara el trigger.
    IF NEW.FECHA_NACIMIENTO > CURRENT_DATE()
    THEN
		SIGNAL SQLSTATE "45000"
        SET MESSAGE_TEXT = "La fecha de nacimiento no puede ser mayor que la fecha actual.";
	END IF;
    -- Si la edad del nuevo paciente es mayor a 120 años, se dispara el trigger.
	IF TIMESTAMPDIFF(YEAR, NEW.FECHA_NACIMIENTO, CURRENT_DATE()) > 120
    THEN
		SIGNAL SQLSTATE "45000"
        SET MESSAGE_TEXT = "La fecha de nacimiento no puede tener mas de 120 años de antigüedad.";
	END IF;
END $$
DELIMITER ;