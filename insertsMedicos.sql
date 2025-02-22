-- Registrar medicos llamando al procedimiento REGISTRAR_MEDICO()
CALL REGISTRAR_MEDICO(
	"AE-1234567",
	"dhfiurursf",
    "Medico",
    "Felipe Humberto",
    "Cabada",
    "Arismendiz",
    "Medicina General",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"765498",
	"876543",
    "Medico",
    "Christian Gibrán",
    "Durán", "Solano",
    "Neumología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"AE-70870",
	"0ASsuyw",
    "Medico",
    "José Guadalupe",
    "Valtierra",
    "Navarro",
    "Pediatría",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"AE-109965",
	"mdlmslnr",
    "Medico",
    "Claudia",
    "Sheinbaum",
    "Pardo",
    "Psicología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"113259",
	"JbiIUbKBk",
    "Medico",
    "Kassandra",
    "Quezada",
    "Villalobos", 
    "Psicología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"AE-009971",
	"86545hgf",
    "Medico",
    "Harry",
    "James",
    "Potter",
    "Pediatría",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"AE-112244", 
	"njugi98h",
    "Medico",
    "Alejandro",
    "Almonte",
    "Rivera",
    "Cardiología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"AE-776655",
	"HHAHA77",
    "Medico",
    "Enrique",
    "Peña",
    "Nieto",
    "Oncología",
    "Alta"
);

-- Registrar horarios para médicos
-- Horarios para el médico con ID 1
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Monday', '09:00', '17:00', 1),
('Wednesday', '14:00', '20:00', 1),
('Friday', '10:00', '18:00', 1);
-- Horarios para el médico con ID 2
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Tuesday', '08:00', '16:00', 2),
('Thursday', '11:00', '19:00', 2);
-- Horarios para el médico con ID 3
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Monday', '13:00', '21:00', 3),
('Wednesday', '07:00', '15:00', 3),
('Friday', '15:00', '23:00', 3);
-- Horarios para el médico con ID 4
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Tuesday', '10:00', '18:00', 4),
('Thursday', '08:00', '16:00', 4);
-- Horarios para el médico con ID 5
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Monday', '08:00', '12:00', 5),
('Wednesday', '16:00', '20:00', 5),
('Friday', '11:00', '15:00', 5);
-- Horarios para el médico con ID 6
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Tuesday', '14:00', '18:00', 6),
('Thursday', '10:00', '14:00', 6);
-- Horarios para el médico con ID 7
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Monday', '11:00', '19:00', 7),
('Wednesday', '09:00', '17:00', 7),
('Friday', '13:00', '21:00', 7);
-- Horarios para el médico con ID 8
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('Tuesday', '07:00', '15:00', 8),
('Thursday', '15:00', '23:00', 8);