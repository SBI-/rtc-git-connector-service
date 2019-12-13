-- the create issue table has additional comments on certain
-- column specifications
CREATE TABLE RIODS.GIT_MERGE_REQUEST (
    -- Reference to gitlab merge request
    ID BIGINT NOT NULL,
    -- Project-local merge request reference
    IID BIGINT NOT NULL,
    -- Reference to github project
    PROJECT_ID BIGINT NOT NULL,
    TITLE VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(4000),
    STATE VARCHAR(50),
    MERGED_AT TIMESTAMP,
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP,
    TARGET_BRANCH VARCHAR(50),
    SOURCE_BRANCH VARCHAR(50),
    TARGET_PROJECT BIGINT,
    SOURCE_PROJECT BIGINT,
    IN_PROGRESS BOOLEAN DEFAULT FALSE,
    MERGE_SUCCESS BOOLEAN DEFAULT FALSE,
    MERGE_STATUS VARCHAR(50),
    SHA VARCHAR(100),
    MERGE_COMMIT_SHA VARCHAR(100),
    WEB_URL VARCHAR(2083),
    RICH_HOVER_URL VARCHAR(2083),

    ID_PK INTEGER NOT NULL,
    CONSTRAINT GIT_MERGE_REQUEST_PK PRIMARY KEY (ID_PK),

    -- FK references
    -- merged by
    MERGED_BY INTEGER DEFAULT -1 NOT NULL,
    CONSTRAINT MERGED_BY_FK FOREIGN KEY (MERGED_BY) REFERENCES RIODS.RESOURCE(RESOURCE_ID),
    -- author
    RTC_AUTHOR INTEGER DEFAULT -1 NOT NULL,
    CONSTRAINT AUTHOR_FK FOREIGN KEY (RTC_AUTHOR) REFERENCES RIODS.RESOURCE(RESOURCE_ID)
    -- assignees will require another FK table
);
