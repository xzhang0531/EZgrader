drop database ezgrader;
create database ezgrader;  
use ezgrader;  

CREATE TABLE Student ( 
buid       CHAR(10) NOT NULL,
fname      VARCHAR(20) NOT NULL,
lname      VARCHAR(20) NOT NULL,
mname    VARCHAR(20),
major       VARCHAR(30),
college     CHAR(3),
gpa         FLOAT(4),
specialization VARCHAR(40),
stu_type         VARCHAR(10) NOT NULL,
stu_year        CHAR(4),
undergardmajor    VARCHAR(30),
PRIMARY KEY (buid)
);


CREATE TABLE Course ( 
courseid       INT NOT NULL AUTO_INCREMENT,
coursecode      VARCHAR(10) NOT NULL,
semester      VARCHAR(10) NOT NULL,
coursename   VARCHAR(20) NOT NULL,
collegecode     VARCHAR(3) NOT NULL,
year                INT NOT NULL,
section  VARCHAR(2),
PRIMARY KEY (courseid)
);

CREATE TABLE Category ( 
courseid       INT NOT NULL,
gweight      FLOAT NOT NULL,
ugweight      FLOAT NOT NULL,
categoryname   VARCHAR(20) NOT NULL,
categoryseq   INT NOT NULL,
PRIMARY KEY (courseid, categoryname),
FOREIGN KEY (courseid) REFERENCES Course(courseid)
);


CREATE TABLE Assignment ( 
courseid       INT NOT NULL,
gweight      FLOAT NOT NULL,
ugweight      FLOAT NOT NULL,
assignmentname   VARCHAR(20) NOT NULL,
assignmentseq   INT NOT NULL,
categoryname    VARCHAR(20) NOT NULL,
maxraw      FLOAT,
curve      FLOAT,
PRIMARY KEY (courseid, assignmentname),
FOREIGN KEY (courseid) REFERENCES Course(courseid),
FOREIGN KEY (courseid, categoryname) REFERENCES Category(courseid, categoryname)
);


CREATE TABLE AssignmentScore ( 
buid       CHAR(10) NOT NULL,
courseid       INT NOT NULL,
assignmentname   VARCHAR(20) NOT NULL,
pointslost      FLOAT,
comment     VARCHAR(500),
PRIMARY KEY (buid,courseid, assignmentname),
FOREIGN KEY (buid) REFERENCES Student(buid),
FOREIGN KEY (courseid) REFERENCES Course(courseid),
FOREIGN KEY (courseid, assignmentname) REFERENCES Assignment(courseid, assignmentname)
);


CREATE TABLE Enrollment ( 
buid       CHAR(10) NOT NULL,
courseid       INT NOT NULL,
rawtotal      FLOAT,
PRIMARY KEY (buid, Courseid),
FOREIGN KEY (buid) REFERENCES Student(buid),
FOREIGN KEY (courseid) REFERENCES Course(courseid)
);