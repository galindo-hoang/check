CREATE TABLE employ_meta_info
(
    visa                VARCHAR(255) NOT NULL,
    name                VARCHAR(255),
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    division            VARCHAR(255),
    is_mgr              VARCHAR(255),
    group_sa            VARCHAR(255),
    working_hours       DOUBLE,
    worked_hours        DOUBLE,
    difference          DOUBLE,
    cumul_diff          DOUBLE,
    vac_left            DOUBLE,
    entrance            VARCHAR(255),
    resignation         INT,
    ch_prd              DOUBLE,
    ch_prd_percent      DOUBLE,
    absence             DOUBLE,
    sort                INT,
    forfait             BOOLEAN,
    part_time           INT,
    calculated          DOUBLE,
    calculated_vac_left DOUBLE,
    calculated_is_mgr   VARCHAR(255),
    vn_entry            VARCHAR(255),
    calculated_division VARCHAR(255),
    calculated_overview VARCHAR(255),
    holidays            INT,
    illness             INT,
    trainings           INT,
    onboarding           INT,
    others              INT,
    CONSTRAINT pk_employ_meta_info PRIMARY KEY (visa)
);


CREATE TABLE capacity
(
    visa      VARCHAR(255) NOT NULL,
    reserve_1 VARCHAR(255),
    reserve_2 VARCHAR(255),
    reserve_3 VARCHAR(255),
    reserve_4 VARCHAR(255),
    jan       INT,
    feb       INT,
    mar       INT,
    apr       INT,
    may       INT,
    jun       INT,
    jul       INT,
    aug       INT,
    sep       INT,
    oct       INT,
    nov       INT,
    dec       INT,
    CONSTRAINT pk_capacity PRIMARY KEY (visa)
);

ALTER TABLE capacity ADD CONSTRAINT FK_CAPACITY_ON_VISA FOREIGN KEY (visa) REFERENCES employ_meta_info (visa);

CREATE TABLE employ_role
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    employee_nr       INT,
    active            BOOLEAN,
    last              VARCHAR(255),
    first             VARCHAR(255),
    contract          CHAR,
    forfait           CHAR,
    user_level        VARCHAR(255),
    level             VARCHAR(255),
    sub_level         VARCHAR(255),
    part_time         DOUBLE,
    supervisor   VARCHAR(255),
    abbreviation VARCHAR(255),
    CONSTRAINT pk_employrole PRIMARY KEY (id)
);

ALTER TABLE employ_role
    ADD CONSTRAINT FK_EMPLOYROLE_ON_ABBREVIATION_VISA FOREIGN KEY (abbreviation) REFERENCES employ_meta_info (visa);

ALTER TABLE employ_role
    ADD CONSTRAINT FK_EMPLOYROLE_ON_SUPERVISOR_VISA FOREIGN KEY (supervisor) REFERENCES employ_meta_info (visa);


CREATE TABLE employee_monthly_vertec
(
    id                         BIGINT AUTO_INCREMENT NOT NULL,
    visa                       VARCHAR(255)          NOT NULL,
    date                       VARCHAR(255),
    code                       VARCHAR(255),
    hrs                        INT,
    comment                    VARCHAR(255),
    description                VARCHAR(255),
    vn                         BOOLEAN,
    subproject                 VARCHAR(255),
    subproject_name            VARCHAR(255),
    project                    BIGINT,
    project_name               VARCHAR(255),
    vn_hrs                     INT,
    ch_hrs                     INT,
    uniques                    INT,
    calculated_subproject_name VARCHAR(255),
    division                   VARCHAR(255),
    CONSTRAINT pk_employeemonthlyvertec PRIMARY KEY (id)
);

ALTER TABLE employee_monthly_vertec
    ADD CONSTRAINT FK_EMPLOYEEMONTHLYVERTEC_ON_VISA FOREIGN KEY (visa) REFERENCES employ_meta_info (visa);