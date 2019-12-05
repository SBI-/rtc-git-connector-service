CREATE TABLE RIODS.GIT_ISSUE_RELATION (
    ISSUE_ID INTEGER NOT NULL,
    REQUEST_ID INTEGER NOT NULL,
    PROJECT_ID INTEGER NOT NULL,
    CONSTRAINT GIT_ISSUE_RELATION_PK PRIMARY KEY (ISSUE_ID, REQUEST_ID, PROJECT_ID),
    CONSTRAINT GIT_ISSUE_RELATION_ISSUE_FK FOREIGN KEY (ISSUE_ID) REFERENCES RIODS.GIT_ISSUE(ID_PK),
    CONSTRAINT GIT_ISSUE_RELATION_REQUEST_FK FOREIGN KEY (REQUEST_ID) REFERENCES RIODS.REQUEST(REQUEST_ID),
    CONSTRAINT GIT_ISSUE_RELATION_PROJECT_FK FOREIGN KEY (PROJECT_ID) REFERENCES RIODS.PROJECT(PROJECT_ID)
);