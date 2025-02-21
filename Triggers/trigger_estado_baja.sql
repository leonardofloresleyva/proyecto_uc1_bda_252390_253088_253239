/*
	Trigger por si se quiere agregar un nuevo paciente y su
    estado no es igual a "Alta", es decir, no se pueden
    agregar nuevos pacientes y estar dados de baja.
*/
DELIMITER $$
CREATE TRIGGER PACIENTES_BAJA
BEFORE INSERT ON PACIENTES
FOR EACH ROW
BEGIN
	-- Si ESTADO es diferente de Alta.
	IF NEW.ESTADO != "Alta"
    THEN
		SIGNAL SQLSTATE "45000"
        SET MESSAGE_TEXT = "El nuevo paciente no puede estar dado de baja.";
	END IF;
END $$
DELIMITER ;