/*
	Trigger para evitar que se inserte un Usuario en
    Médicos si su rol es diferente de Médico.
*/
DELIMITER $$
CREATE TRIGGER ROL_INCORRECTO_MEDICO
BEFORE INSERT ON MEDICOS
FOR EACH ROW
BEGIN
	-- Obtiene el rol del usuario y lo almacena en una variable.
	DECLARE ROL_MEDICO ENUM("Paciente", "Medico");
    SELECT ROL INTO ROL_MEDICO
    FROM USUARIOS
    WHERE ID = NEW.ID_MEDICO;
    -- Verifica si el rol obtenido es igual a "Paciente". En caso contrario, se dispara el trigger.
    IF ROL_MEDICO != "Medico"
    THEN 
		SIGNAL SQLSTATE "45000"
		SET MESSAGE_TEXT = 
		"El rol del usuario no corresponde con un medico.";
    END IF;
END $$
DELIMITER ;