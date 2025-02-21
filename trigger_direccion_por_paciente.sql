/*
	Trigger por si se intenta agregar más de una
    dirección a un mismo paciente.
*/
DELIMITER $$
CREATE TRIGGER DIRECCION_POR_PACIENTE
BEFORE INSERT ON DIRECCIONES
FOR EACH ROW
BEGIN
	-- Obtiene el ID de la dirección asociada al ID del paciente de la nueva direccion.
	DECLARE ADDRESS INT;
    SELECT ID_DIRECCION INTO ADDRESS
    FROM DIRECCIONES
    WHERE ID_PACIENTE = NEW.ID_PACIENTE;
    /*
		Si la consulta anterior no es null, es decir, si existe una dirección previa
        asociada al ID del paciente de la nueva dirección, se dispara el trigger.
    */
    IF ADDRESS IS NOT NULL
    THEN
		SIGNAL SQLSTATE "45000"
        SET MESSAGE_TEXT = "Un paciente no puede tener mas de una direccion.";
	END IF;
END $$
DELIMITER ;

-- Primera inserción: exitosa.
INSERT INTO DIRECCIONES(ID_DIRECCION, COLONIA, CALLE, NUMERO, ID_PACIENTE)
VALUES(1, "Jecopaco", "Galeana", "54", 3);

-- Segundo intento de inserción: fracaso; dispara el trigger
INSERT INTO DIRECCIONES(ID_DIRECCION, COLONIA, CALLE, NUMERO, ID_PACIENTE)
VALUES(2, "Esperanza", "Jalisco", "40", 3);