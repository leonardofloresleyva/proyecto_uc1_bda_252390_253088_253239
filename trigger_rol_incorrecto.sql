/*
	Trigger para evitar que se inserte un Usuario en
    Pacientes si su rol es diferente de Paciente.
*/
DELIMITER $$
CREATE TRIGGER ROL_INCORRECTO
BEFORE INSERT ON PACIENTES
FOR EACH ROW
BEGIN
	-- Obtiene el rol del usuario y lo almacena en una variable.
	DECLARE ROL_PACIENTE ENUM("Paciente", "Medico");
    SELECT ROL INTO ROL_PACIENTE
    FROM USUARIOS
    WHERE ID = NEW.ID_PACIENTE;
    -- Verifica si el rol obtenido es igual a "Paciente". En caso contrario, se dispara el trigger.
    IF ROL_PACIENTE != "Paciente"
    THEN 
		SIGNAL SQLSTATE "45000"
		SET MESSAGE_TEXT = 
		"El rol del usuario no corresponde con un paciente.";
    END IF;
END $$
DELIMITER ;
