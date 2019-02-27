create table Volunteer
(
	idVolunteer SMALLINT NOT NULL AUTO_INCREMENT,
	firstname VARCHAR(30) NOT NULL,
	lastname VARCHAR(25) NOT NULL,
	tel 	 VARCHAR(15) NOT NULL,
	ic 		 VARCHAR(20) NOT NULL,
	email	 VARCHAR(30) NOT NULL,
	nationality VARCHAR(20) NOT NULL,
	university VARCHAR(30) NOT NULL,
	country    VARCHAR(15) NOT NULL,
	course 	   VARCHAR(25) NOT NULL,
	CONSTRAINT pk_volunteer PRIMARY KEY (idVolunteer)
);

create table Program
(
	idProgram SMALLINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	location VARCHAR(30) NOT NULL,
	dateStart DATE NOT NULL,
	dateEnd 	DATE NOT NULL,
	description VARCHAR(100) NOT NULL,
	CONSTRAINT pk_program PRIMARY KEY (idProgram)
);

CREATE TABLE Participation (
    volunteer_id SMALLINT NOT NULL,
    program_id SMALLINT NOT NULL,
    FOREIGN KEY (volunteer_id) REFERENCES Volunteer (idVolunteer),
    FOREIGN KEY (program_id) REFERENCES Program (idProgram),
    PRIMARY KEY (volunteer_id, program_id)
);