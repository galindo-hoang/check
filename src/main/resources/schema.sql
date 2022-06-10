CREATE TABLE employ_meta_info
(
    visa                VARCHAR(255) NOT NULL,
    version             INT not null,
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
    version             INT not null,
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

CREATE TABLE employee_role
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    version             INT not null,
    employee_nr       INT,
    active            BOOLEAN,
    last              VARCHAR(255),
    first             VARCHAR(255),
    contract          CHAR,
    forfait           CHAR,
    user_level        VARCHAR(255),
    level             INT,
    sub_level         INT,
    part_time         DOUBLE,
    supervisor   VARCHAR(255),
    abbreviation VARCHAR(255),
    CONSTRAINT pk_employrole PRIMARY KEY (id)
);

ALTER TABLE employee_role
    ADD CONSTRAINT FK_EMPLOYROLE_ON_ABBREVIATION_VISA FOREIGN KEY (abbreviation) REFERENCES employ_meta_info (visa);

ALTER TABLE employee_role
    ADD CONSTRAINT FK_EMPLOYROLE_ON_SUPERVISOR_VISA FOREIGN KEY (supervisor) REFERENCES employ_meta_info (visa);


CREATE TABLE employee_monthly_vertec
(
    id                         BIGINT AUTO_INCREMENT NOT NULL,
    visa                       VARCHAR(255)          NOT NULL,
    version                    INT not null,
    date                       VARCHAR(255),
    code                       VARCHAR(255),
    hrs                        DOUBLE,
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


CREATE TABLE vertec
(
    month_year VARCHAR(255) NOT NULL,
    version    INT          NULL,
    visa       VARCHAR(255) NOT NULL,
    subproject VARCHAR(255) NULL,
    CONSTRAINT pk_vertec PRIMARY KEY (month_year, visa)
);

ALTER TABLE vertec
    ADD CONSTRAINT FK_VERTEC_ON_VISA FOREIGN KEY (visa) REFERENCES employ_meta_info (visa);

INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('ABC', 'DUMMY NAME 1', 'Name 1', 'Last Name 1', 'Dummy Division 1', 'Engineers', 'Group 1', 160.00, 160.00, 0.00, -3.58, 72.00, '12/16/2020', null, 132.00, 82.50, 20.00, 980, 'FALSE', 100, 0.0, 9, 'Engineers', '12/16/2020', 'Dummy Division 1', 'Engineers',  0, 0, 0, 0, 0, 0);
INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('XYZ', 'DUMMY NAME 2', 'Name 2', 'Last Name 2', 'Dummy Division 2', 'Engineers', 'Group 2', 160.00, 160.00, 0.00, -8.50, 128.00, '1/18/2021', null, 136.00, 85.00, 16.00, 1000, 'FALSE', 100, 0.0, 16, 'Engineers', '1/18/2021', 'Dummy Division 2', 'Engineers',  0, 0, 0, 0, 0, 0);
INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('DEF', 'DUMMY NAME 3', 'Name 3', 'Last Name 3', 'Dummy Division 3', 'Engineers', 'Group 3', 160.00, 160.00, 0.00, 0.00, 144.00, '11/2/2020', null, 152.00, 95.00, 0.00, 1000, 'FALSE', 100, 0.0, 18, 'Engineers', '11/2/2020', 'Dummy Division 3', 'Engineers',  0, 0, 0, 0, 0, 0);
INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('XY1', 'DUMMY NAME 4', 'Name 4', 'Last Name 4', 'Dummy Division 4', 'Engineers', 'Group 4', 160.00, 158.50, -1.50, 10.00, 112.00, '11/2/2020', null, 128.58, 81.13, 4.00, 950, 'FALSE', 100, 0.0, 14, 'Engineers', '11/2/2020', 'Dummy Division 4', 'Engineers',  0, 0, 0, 0, 0, 0);
INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('XY2', 'DUMMY NAME 5', 'Name 5', 'Last Name 5', 'Dummy Division 5', 'Engineers', 'Group 5', 160.00, 160.00, 0.00, 19.50, 136.00, '11/2/2020', null, 110.00, 68.75, 8.00, 1030, 'FALSE', 100, 0.0, 17, 'Engineers', '11/2/2020', 'Dummy Division 5', 'Engineers',  0, 0, 0, 0, 0, 0);
INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('AB2', 'DUMMY NAME 6', 'Name 6', 'Last Name 6', 'Dummy Division 6', 'Engineers', 'Group 6', 160.00, 160.00, 0.00, 0.00, 144.00, '3/1/2021', null, 152.00, 95.00, 0.00, 1000, 'FALSE', 100, 0.0, 18, 'Engineers', '3/1/2021', 'Dummy Division 6', 'Engineers',  0, 0, 0, 0, 0, 0);
INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('AB1', 'DUMMY NAME 7', 'Name 7', 'Last Name 7', 'Dummy Division 7', 'Engineers', 'Group 7', 160.00, 161.00, 1.00, 14.50, 88.00, '3/1/2021', null, 135.50, 84.16, 24.00, 1000, 'FALSE', 100, 0.0, 11, 'Engineers', '3/1/2021', 'Dummy Division 7', 'Engineers',  0, 0, 0, 0, 0, 0);
INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('DE1', 'DUMMY NAME 9', 'Name 9', 'Last Name 9', 'Dummy Division 9', 'Engineers', 'Group 9', 160.00, 171.00, 11.00, 30.50, 104.00, '1/11/2021', null, 17.50, 10.23, 24.00, 1000, 'FALSE', 100, 0.0, 13, 'Engineers', '1/11/2021', 'Dummy Division 9', 'Engineers',  0, 0, 0, 0, 0, 0);
INSERT INTO EMPLOY_META_INFO(visa, name, FIRST_NAME, LAST_NAME, division, is_Mgr, GROUP_SA, working_Hours, worked_Hours, difference, cumul_Diff, vac_Left, entrance, resignation, ch_Prd, ch_Prd_Percent, absence, sort, forfait, part_Time, CALCULATED, calculated_Vac_Left, calculated_Is_Mgr, vn_Entry, calculated_Division, calculated_Overview, holidays, illness, trainings, onboarding, others, version) VALUES ('DE2', 'DUMMY NAME 10', 'Name 10', 'Last Name 10', 'Dummy Division 10', 'Engineers', 'Group 10', 160.00, 160.00, 0.00, 15.50, 140.00, '1/18/2021', null, 139.00, 86.88, 4.00, 1000, 'FALSE', 100, 0.0, 17.5, 'Engineers', '1/18/2021', 'Dummy Division 10', 'Engineers',  0, 0, 0, 0, 0, 0);






INSERT INTO EMPLOYEE_MONTHLY_VERTEC (visa, DATE, code, hrs, comment, description, vn, SUBPROJECT, SUBPROJECT_NAME, PROJECT, project_Name, vn_Hrs, ch_Hrs, uniques, CALCULATED_SUBPROJECT_NAME, division, version) VALUES ('ABC', '2022-1-4', '543123-101-44', 8, 'comment of task 1', 'dummy description', 'FALSE', '543123-101', 'dummy sub name', 543123, 'dummy project name', 0, 8, 1, 'dummy project name, dummy sub name', 'reserve', 0);
INSERT INTO EMPLOYEE_MONTHLY_VERTEC (visa, DATE, code, hrs, comment, description, vn, SUBPROJECT, SUBPROJECT_NAME, PROJECT, project_Name, vn_Hrs, ch_Hrs, uniques, CALCULATED_SUBPROJECT_NAME, division, version) VALUES ('DEF', '2022-4-4', '543123-101-44', 8, 'comment of task 2', 'dummy description', 'FALSE', '543123-101', 'dummy sub name', 543123, 'dummy project name', 0, 8, 1, 'dummy project name, dummy sub name', 'reserve', 0);
INSERT INTO EMPLOYEE_MONTHLY_VERTEC (visa, DATE, code, hrs, comment, description, vn, SUBPROJECT, SUBPROJECT_NAME, PROJECT, project_Name, vn_Hrs, ch_Hrs, uniques, CALCULATED_SUBPROJECT_NAME, division, version) VALUES ('AB2', '2022-5-4', '543123-101-44', 8, 'comment of task 3', 'dummy description', 'FALSE', '543123-101', 'dummy sub name', 543123, 'dummy project name', 0, 8, 1, 'dummy project name, dummy sub name', 'reserve', 0);
INSERT INTO EMPLOYEE_MONTHLY_VERTEC (visa, DATE, code, hrs, comment, description, vn, SUBPROJECT, SUBPROJECT_NAME, PROJECT, project_Name, vn_Hrs, ch_Hrs, uniques, CALCULATED_SUBPROJECT_NAME, division, version) VALUES ('AB2', '2022-6-4', '19513-101-44', 8, 'Project X comment', 'dummy description', 'FALSE', '19513-101', 'dummy sub name', 19513, 'dummy project name', 0, 8, 1, 'dummy project name, dummy sub name', 'reserve', 0);
INSERT INTO EMPLOYEE_MONTHLY_VERTEC (visa, DATE, code, hrs, comment, description, vn, SUBPROJECT, SUBPROJECT_NAME, PROJECT, project_Name, vn_Hrs, ch_Hrs, uniques, CALCULATED_SUBPROJECT_NAME, division, version) VALUES ('XYZ', '2022-7-4', '18020-101-33', 1, 'Project X comment', 'dummy description', 'FALSE', '18020-101', 'dummy sub name', 18020, 'dummy project name', 0, 1, 1, 'dummy project name, dummy sub name', 'reserve', 0);
INSERT INTO EMPLOYEE_MONTHLY_VERTEC (visa, DATE, code, hrs, comment, description, vn, SUBPROJECT, SUBPROJECT_NAME, PROJECT, project_Name, vn_Hrs, ch_Hrs, uniques, CALCULATED_SUBPROJECT_NAME, division, version) VALUES ('ABC', '2022-7-4', '19513-101-44', 7, 'Project Y', 'dummy description', 'FALSE', '19513-101', 'dummy sub name', 19513, 'dummy project name', 0, 7, 1, 'dummy project name, dummy sub name', 'reserve', 0);








INSERT INTO EMPLOYEE_ROLE(active, EMPLOYEE_NR, last, first, abbreviation, contract, forfait, user_Level, level, sub_Level, part_Time, SUPERVISOR, version)
VALUES (true, 23209, 'dummy last name', 'first name', 'ABC', 'B', 'N', 'Eng begin Vietnam', 1, 2, 100.00, 'XYZ', 0);
INSERT INTO EMPLOYEE_ROLE(active, EMPLOYEE_NR, last, first, abbreviation, contract, forfait, user_Level, level, sub_Level, part_Time, SUPERVISOR, version)
VALUES (true, 23209, 'name', 'first name', 'XYZ', 'B', 'N', 'Eng begin Vietnam', 1, 4, 100.00, 'AB1', 0);






INSERT INTO CAPACITY (visa, reserve_1, reserve_2, reserve_3, reserve_4, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, version) VALUES ('ABC', 'raw data 1', 'raw data 2', 'raw data 3', 'raw data 4', '1', '1', '1', null, null, null, null, null, null, null, null, null, 0);
INSERT INTO CAPACITY (visa, reserve_1, reserve_2, reserve_3, reserve_4, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, version) VALUES ('DEF', 'raw data 1', 'raw data 2', 'raw data 3', 'raw data 4', '1', null, null, null, null, null, null, null, null, null, null, null, 0);
INSERT INTO CAPACITY (visa, reserve_1, reserve_2, reserve_3, reserve_4, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, version) VALUES ('XYZ', 'raw data 1', 'raw data 2', 'raw data 3', 'raw data 4', null, null, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 0);
INSERT INTO CAPACITY (visa, reserve_1, reserve_2, reserve_3, reserve_4, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, version) VALUES ('AB1', 'raw data 1', 'raw data 2', 'raw data 3', 'raw data 4', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 0);
INSERT INTO CAPACITY (visa, reserve_1, reserve_2, reserve_3, reserve_4, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, version) VALUES ('AB2', 'raw data 1', 'raw data 2', 'raw data 3', 'raw data 4', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 0);
INSERT INTO CAPACITY (visa, reserve_1, reserve_2, reserve_3, reserve_4, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, version) VALUES ('XY2', 'raw data 1', 'raw data 2', 'raw data 3', 'raw data 4', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 0);