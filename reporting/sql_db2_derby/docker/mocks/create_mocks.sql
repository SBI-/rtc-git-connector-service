CREATE TABLE RIODS.REQUEST (
    NAME VARCHAR(100),
    REQUEST_ID INTEGER NOT NULL,
    CONSTRAINT REQUEST_PK PRIMARY KEY (REQUEST_ID)
);

CREATE TABLE RIODS.PROJECT (
    NAME VARCHAR(100),
    PROJECT_ID INTEGER NOT NULL,
    CONSTRAINT PROJECT_PK PRIMARY KEY (PROJECT_ID)
);

CREATE TABLE RIODS.RESOURCE (
    NAME VARCHAR(100),
    FULL_NAME VARCHAR(100),
    REFERENCE_ID INTEGER,
    RESOURCE_ID INTEGER NOT NULL,
    CONSTRAINT PROJECT_PK PRIMARY KEY (RESOURCE_ID)
);