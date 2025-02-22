-- Registrar medicos llamando al procedimiento REGISTRAR_MEDICO()
CALL REGISTRAR_MEDICO(
	"dhfiurursf",
    "Medico",
    "Felipe Humberto",
    "Cabada",
    "Arismendiz", 
    "AE-1234567",
    "Medicina General",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"876543",
    "Medico",
    "Christian Gibrán",
    "Durán", "Solano",
    "765498",
    "Neumología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"0ASsuyw",
    "Medico",
    "José Guadalupe",
    "Valtierra",
    "Navarro",
    "AE-70870",
    "Pediatría",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"mdlmslnr",
    "Medico",
    "Claudia",
    "Sheinbaum",
    "Pardo",
    "AE-109965",
    "Psicología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"JbiIUbKBk",
    "Medico",
    "Kassandra",
    "Quezada",
    "Villalobos", 
    "113259",
    "Psicología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"86545hgf",
    "Medico",
    "Harry",
    "James",
    "Potter",
    "AE-009971",
    "Pediatría",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"njugi98h",
    "Medico",
    "Alejandro",
    "Almonte",
    "Rivera",
    "AE-112244", 
    "Cardiología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"HHAHA77",
    "Medico",
    "Enrique",
    "Peña",
    "Nieto",
    "AE-776655",
    "Oncología",
    "Alta"
);

-- Registrar horarios para médicos
-- Horarios para el médico con ID 3
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Monday', '09:00', '17:00', 3),
('Wednesday', '14:00', '20:00', 3),
('Friday', '10:00', '18:00', 3);
-- Horarios para el médico con ID 4
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Tuesday', '08:00', '16:00', 4),
('Thursday', '11:00', '19:00', 4);
-- Horarios para el médico con ID 5
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Monday', '13:00', '21:00', 5),
('Wednesday', '07:00', '15:00', 5),
('Friday', '15:00', '23:00', 5);
-- Horarios para el médico con ID 6
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Tuesday', '10:00', '18:00', 6),
('Thursday', '08:00', '16:00', 6);
-- Horarios para el médico con ID 7
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Monday', '08:00', '12:00', 7),
('Wednesday', '16:00', '20:00', 7),
('Friday', '11:00', '15:00', 7);
-- Horarios para el médico con ID 8
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Tuesday', '14:00', '18:00', 8),
('Thursday', '10:00', '14:00', 8);
-- Horarios para el médico con ID 9
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Monday', '11:00', '19:00', 9),
('Wednesday', '09:00', '17:00', 9),
('Friday', '13:00', '21:00', 9);
-- Horarios para el médico con ID 10
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Tuesday', '07:00', '15:00', 10),
('Thursday', '15:00', '23:00', 10);