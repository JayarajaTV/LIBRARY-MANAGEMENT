create database PROJECT;
use project;


create table adminTable(
S_NO int primary key auto_increment,
userName varchar(30),
password varchar(30)
);

select * from adminTable;
drop table adminTable;

insert into adminTable(userName , password) values('jayarajav','jayaraja001');



DELIMITER $$
CREATE PROCEDURE adminLogin(
IN user_name varchar(30),
IN user_password varchar(30),
OUT result varchar(10)
)
BEGIN
    DECLARE rowCount INT;

    SELECT COUNT(*) INTO rowCount FROM adminTable 
    WHERE userName = user_name AND password = user_password;

    IF rowCount > 0 THEN
       SET result = '1';
    ELSE
        SET result = '0';
    END IF;
    
END $$
DELIMITER ;

drop procedure adminLogin;


CREATE TABLE librarians (
s_no INT PRIMARY KEY auto_increment,
name VARCHAR(100),
email VARCHAR(100),
librarianId int,
password VARCHAR(50)
);



drop table librarians;

insert into librarians(name , email , librarianId , password) values('jai' , 'jaikumar234@gmail.com' , 731621 , 'jai001');

select * from librarians;



DELIMITER $$ 
CREATE PROCEDURE deleteLibrarian(IN libId int, 
OUT result varchar(20))
BEGIN
    DECLARE rowCount INT;
     SELECT COUNT(*) INTO rowCount FROM librarians WHERE librarianId = libId;
    
    IF rowCount > 0 THEN
        DELETE FROM librarians WHERE librarianId = libId;
        SET result = '1'; 
    ELSE
        SET result = '0';
    END IF;
 
END$$
DELIMITER ;
 
 drop procedure deleteLibrarian;
 
 CREATE TABLE students (
stu_id INT PRIMARY KEY auto_increment,
name VARCHAR(100),
email VARCHAR(100),
username VARCHAR(50),
password VARCHAR(50)
)auto_increment =106019 ;

drop table students;
insert into students(name , email , username , password) values('jai' , 'jaikumar234@gmail.com' , 'jayaraja' , 'jai001');
select * from students;

DELIMITER $$ 
CREATE PROCEDURE deleteStudent(IN studentId int, 
OUT result varchar(20))
BEGIN
    DECLARE rowCount INT;
     SELECT COUNT(*) INTO rowCount FROM students WHERE studentId = stu_id;
    
    IF rowCount > 0 THEN
        DELETE FROM students WHERE studentId = stu_id;
        SET result = '1'; 
    ELSE
        SET result = '0';
    END IF;
 
END$$
DELIMITER ;
 
 drop procedure deleteStudent;



DELIMITER $$
CREATE PROCEDURE LibrarianLogin(
IN lib_id INT,
IN user_password varchar(30),
OUT result varchar(10)
)
BEGIN
    DECLARE rowCount INT;

    SELECT COUNT(*) INTO rowCount FROM librarians 
    WHERE librarianId = lib_id AND password = user_password;

    IF rowCount > 0 THEN
       SET result = '1';
    ELSE
        SET result = '0';
    END IF;
    
END $$
DELIMITER ;

drop procedure LibrarianLogin;

DELIMITER $$
CREATE PROCEDURE studentLogin(
IN stuid INT,
IN stu_name varchar(30),
IN stu_password varchar(30),
OUT result varchar(10)
)
BEGIN
    DECLARE rowCount INT;

    SELECT COUNT(*) INTO rowCount FROM students 
    WHERE stu_id = stuid AND name = stu_name AND password = stu_password;

    IF rowCount > 0 THEN
       SET result = '1';
    ELSE
        SET result = '0';
    END IF;
    
END $$
DELIMITER ;

CREATE TABLE books(
S_NO int auto_increment primary key,
ISBNID VARCHAR(200),
TITLE varchar(100),
AUTHOR varchar(200),
PUBLISHER varchar(200),
QUANTITY int 
);
DROP TABLE BOOKS;

INSERT INTO books (ISBNID, TITLE, AUTHOR, PUBLISHER, QUANTITY) VALUES
('978-3-16-148410-0', 'The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', 10),
('978-0-7432-7356-5', 'To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott & Co.', 5),
('978-0-452-28423-4', '1984', 'George Orwell', 'Secker & Warburg', 15),
('978-1-56619-909-4', 'Animal Farm', 'George Orwell', 'Secker & Warburg', 8),
('978-0-06-112008-4', 'The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', 12);

select * from books;

DELIMITER $$
CREATE PROCEDURE checkISBN(
IN isbn varchar(200),
OUT result varchar(10)
)
BEGIN
    DECLARE rowCount INT;

    SELECT COUNT(*) INTO rowCount FROM books 
    WHERE isbnId = isbn ;

    IF rowCount > 0 THEN
       SET result = '1';
    ELSE
        SET result = '0';
    END IF;
     
END $$
DELIMITER ;

drop procedure checkISBN;

DELIMITER $$
CREATE PROCEDURE checkAuthorName(
IN authorName varchar(200),
OUT result varchar(10)
)
BEGIN
    DECLARE rowCount INT;

    SELECT COUNT(*) INTO rowCount FROM books 
    WHERE authorName = AUTHOR;

    IF rowCount > 0 THEN
       SET result = '1';
    ELSE
        SET result = '0';
    END IF;
     
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE checkBook(
IN bTitle varchar(200),
OUT result varchar(10)
)
BEGIN
    DECLARE rowCount INT;

    SELECT COUNT(*) INTO rowCount FROM books 
    WHERE bTitle = title and quantity > 0 ;

    IF rowCount > 0 THEN
       SET result = '1';
    ELSE
        SET result = '0';
    END IF;
     
END $$
DELIMITER ;

drop procedure checkBook;

DELIMITER $$ 
CREATE PROCEDURE removeBook(IN isbn_id varchar(20), 
OUT result varchar(20))
BEGIN
    DECLARE rowCount INT;
     SELECT COUNT(*) INTO rowCount FROM books WHERE isbnId = isbn_id;
    
    IF rowCount > 0 THEN
        DELETE FROM books WHERE isbnId = isbn_id;
        SET result = '1'; 
    ELSE
        SET result = '0';
    END IF;
 
END$$
DELIMITER ;

drop procedure removeBook;


create table borrowingDetails(
book_title varchar(50),
student_id int,
borrowing_date date,
return_date date
);

drop table borrowingDetails;
select * from borrowingDetails;
insert into borrowingDetails(book_title,student_id,borrowing_date,return_date) values('animal farm',106020,'2024-07-11','2024-08-11');

delete from borrowingDetails where s_no = 3 or s_no =  4 or s_no =  5;


DELIMITER $$
CREATE PROCEDURE checkStudentId(
IN sid varchar(200),
OUT result varchar(10)
)
BEGIN
    DECLARE rowCount INT;

    SELECT COUNT(*) INTO rowCount FROM students 
    WHERE stu_id = sid;

    IF rowCount > 0 THEN
       SET result = '1';
    ELSE
        SET result = '0';
    END IF;
     
END $$
DELIMITER ;

DELIMITER $$
create procedure editBookQuantity(
in btitle varchar(20),
out res varchar(10)
)
begin
   declare quan int;
   select quantity into quan from books where title = btitle;
   
   
   if quan > 0  then  
    update books 
    set quantity = quan - 1 
    where title = btitle;
   
   end if;
  
end $$
DELIMITER ;

DELIMITER $$
create procedure checkLimite(
in stuId int,
out res varchar(20) )
begin

declare count int;

select count(*) into count 
from borrowingDetails
where student_id = stuId ;

if count > 3 then
  set res = '0';
else 
  set res = '1';
  
end if;

end $$
DELIMITER ;


DELIMITER $$
create procedure checkBorrowStatue(
in stuId int,
out res varchar(20) )
begin

declare count int;

select count(*) into count 
from borrowingDetails
where student_id = stuId ;

if count > 0 then
  set res = '1';
else 
  set res = '0';
  
end if;

end $$
DELIMITER ;

create table fineDetails(
s_no int primary key auto_increment,
student_id int,
return_date date,
amount int
);
select * from fineDetails;

insert into fineDetails(student_id,return_date,amount) values(12345,'2000-12-12',120);
insert into fineDetails(student_id,return_date,amount) values(6789,'2020-10-10',56);
insert into fineDetails(student_id,return_date,amount) values(3456,'2010-5-4',66);