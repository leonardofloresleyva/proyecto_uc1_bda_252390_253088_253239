SELECT * FROM potro_clinica.citas;
SELECT * FROM potro_clinica.medicos;
SELECT * FROM potro_clinica.pacientes;
SELECT * FROM potro_clinica.consultas;
SELECT * FROM potro_clinica.citas_emergencia;
SELECT * FROM potro_clinica.citas_previas;
SELECT * FROM potro_clinica.usuarios;

SELECT * FROM HISTORIAL_CITAS_PACIENTES;

SELECT * FROM CITAS_EMERGENCIA WHERE ID_CITA_EMERGENCIA = 3 AND FOLIO = 80068587;

UPDATE CITAS_EMERGENCIA SET ESTADO = "No atendida" WHERE ID_CITA_EMERGENCIA = 1;
UPDATE CITAS_PREVIAS SET ESTADO = "No atendida" WHERE ID_CITA_PREVIA = 6;

CALL REGISTRAR_CONSULTA(
	"Hola",
    "Ya estoy harto de dar ejemplos",
    "Pelota",
    1);
    
SELECT
  ID,
  NOMBRES,
  APELLIDO_PATERNO,
  APELLIDO_MATERNO,
  ESPECIALIDAD,
  ESTADO
FROM DATOS_MEDICO
WHERE USUARIO = '113259' AND CONTRASENIA = 'JbiIUbKBk';