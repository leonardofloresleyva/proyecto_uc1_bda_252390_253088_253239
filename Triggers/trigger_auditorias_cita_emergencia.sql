# Trigger que añade los cambios de las citas_emergencia en la tabla de auditoria_citas
DELIMITER //
CREATE TRIGGER HISTORIAL_ESTADO_CITA_EMERGENCIA
AFTER UPDATE ON CITAS_EMERGENCIA
FOR EACH ROW
BEGIN
	-- Insertar la actualización del estado en la tabla de auditoria_citas solo si hay algún cambio
    IF OLD.ESTADO != NEW.ESTADO THEN
		INSERT INTO AUDITORIA_CITAS (FECHA, ESTADO_ANTERIOR, ESTADO_NUEVO, ID_CITA)
			VALUES (NOW(), OLD.ESTADO, NEW.ESTADO, OLD.ID_CITA);
	END IF;
END //
DELIMITER ;