 create database dbQuiz

use dbQuiz 
go

--create tbUser
create table tbUser
(
    userID int IDENTITY(1,1),
    name NVARCHAR(50),
    phone NVARCHAR(50) unique,
    gender NVARCHAR(10),
    bod DATE,
    role NVARCHAR(10),
    username NVARCHAR(50) UNIQUE,
    password NVARCHAR(50),
    email NVARCHAR (100) UNIQUE,
    CONSTRAINT PK_ID PRIMARY KEY NONCLUSTERED(userID),
    CONSTRAINT Ck_gender CHECK (gender in ('male','female', 'other')),
    CONSTRAINT ck_role check (role in('admin','player'))


);


-- Insert values
INSERT tbUser
VALUEs
    ('admin', '333-123-123', 'male', '03/03/2000', 'Admin', 'admin', 'admin', 'admin@gmail.com'),
    ('player', '444-123-323', 'male', '03/03/2000', 'Player', 'player1', 'player1', 'player.com')
go

-- create tbTOPIC
create table tbTopic
(
    topicID int IDENTITY(1,1),
    topic NVARCHAR(20) unique,
    longname NVARCHAR(100),
    CONSTRAINT PK_idTopic PRIMARY KEY NONCLUSTERED(topicID),
);
go
-- INsert tbTopic
INSERT tbTopic	
VALUEs
    ('HTML', 'HyperText Markup Language'),
    ('AJS' , 'Angular JavaScript'),
    ('Java', 'Java'),
    ('SQL', 'Structured Query Language'),
    ('PHP', 'Hypertext Preprocessor'),
    ('CSS', 'Cascading Style Sheets')
go

-- Create table tbQuestion
create table tbQuestion
(
    questionID int IDENTITY(1,1),
    content NVARCHAR(300) not null unique,
    [type] int not null,
    topic NVARCHAR(20),
    answer bit DEFAULT 1 null,
    CONSTRAINT PK_questionID PRIMARY KEY NONCLUSTERED(questionID),
    CONSTRAINT Fk_topic FOREIGN KEY (topic) REFERENCES tbtopic(topic),
);

-- Create table tbOption
create table tbOption
(
    optionID int IDENTITY(1,1),
    questionID int,
    content NVARCHAR(300) not null ,
    answer bit DEFAULT 1 null,
    CONSTRAINT PK_opID PRIMARY KEY NONCLUSTERED(optionID),
    CONSTRAINT FK_questionID FOREIGN KEY(questionID) REFERENCES tbQuestion(questionID)
)
go
-- Create table tbResult

create table tbResult
(
    resultID int IDENTITY(1,1),
    userID int,
    [score] int,
    duration VARCHAR(50),
    [date] date,
    [level] int ,
    topic NVARCHAR(20),
    CONSTRAINT PK_resultID PRIMARY KEY NONCLUSTERED(resultID),
    CONSTRAINT fk_userID FOREIGN KEY (userID) REFERENCES tbUser(userID),
    CONSTRAINT Fk_topicc FOREIGN KEY (topic) REFERENCES tbtopic(topic)
);
go

-- create view tbresult and tbuser--

CREATE VIEW vwUserResult
AS
    SELECT u.userID, u.name, r.score, r.duration, r.date, r.level, r.topic
    FROM tbUser [u]
        JOIN tbResult [r] ON u.userID = r.userID;
go

-------------java---

insert tbQuestion VALUES
('Java is purely procedural programming language?',1,'Java',0),
('Java applications are compiled into bytecode that runs on the Java Virtual Machine (JVM)?',1,'Java',1),
('Java supports multiple inheritance for classes?',1,'Java',0),
('Java uses a garbage collector to automatically manage memory?',1,'Java',1),
('The "String" class in Java is mutable, meaning its value can be changed after it is created?',1,'Java',0),
('"Break" statement can be used to exit both a loop and a switch statement.?',1,'Java',1),
('Java interfaces can have method implementations?',1,'Java',1),
('Java "NullPointerException" is a checked exception?',1,'Java',0),
('Java "ArrayList" is a synchronized collection?',1,'Java',0),
('Java supports pointers as part of its language feature?',1,'Java',0),
('Java "equals()" method is used to compare the values of two objects?',1,'Java',1),
('Java supports automatic type conversion (type coercion) between incompatible data types.?',1,'Java',0),

('What is the correct syntax to declare a varible in java?',2,'Java',null),
('Which keyword is used to indicate the start of a method in Java?',2,'Java',null),
('In Java, what is the purpose of the "super" keyword?',2,'Java',null),
('What is the purpose of the "this" keyword in Java?',2,'Java',null),
('Which of the following is a correct declaration of a method that does not return any value in Java?',2,'Java',null),
('What is the default value of a boolean variable in Java?',2,'Java',null),
('Which Java keyword is used to implement multiple interfaces in a class?',2,'Java',null),
('What is the purpose of the "break" statement in Java?',2,'Java',null),
('Which of the following is the correct way to create an object in Java?',2,'Java',null),
('What is the purpose of the "static" keyword in Java?',2,'Java',null),
('In Java, what is the purpose of the "try", "catch", and "finally" blocks in exception handling??',2,'Java',null),
('Which of the following is true about the "StringBuilder" class in Java?',2,'Java',null),

('Which access modifiers are valid for a top-level class in Java?',3,'Java',null),
('What are checked exceptions in Java?',3,'Java',null),
('Which methods belong to the Object class in Java?',3,'Java',null),
('What is the purpose of the "super()" constructor call in a subclass?',3,'Java',null),
('Which statements about interfaces in Java are true?',3,'Java',null),
('What is the role of the "final" keyword in Java?',3,'Java',null),
('Which are valid ways to create a thread in Java?',3,'Java',null),
('Which of the following statements about Java packages are correct?',3,'Java',null),
('Which statements about Java annotations are true?',3,'Java',null),
('What is the purpose of the "throw" keyword in Java?',3,'Java',null),
('Which are valid ways to handle exceptions in Java?',3,'Java',null),
('What is the purpose of the "instanceof" operator in Java?',3,'Java',null);
GO

---SQL----

 insert tbQuestion VALUES 
('MySQL is a relational database management system (RDBMS).',1,'SQL',1),
('MySQL is owned and maintained by Microsoft Corporation.',1,'SQL',0),
('MySQL uses the SQL (Structured Query Language) for database manipulation.',1,'SQL',1),
('In MySQL, the primary key uniquely identifies each record in a table.',1,'SQL',1),
('MySQL supports only the Windows operating system.',1,'SQL',0),
('MySQL can be used as a backend database for web applications.',1,'SQL',1),
('MySQL supports stored procedures and triggers.',1,'SQL',1),
('MySQL is a NoSQL database.',1,'SQL',0),
('MySQL Workbench is a graphical tool for managing MySQL databases..',1,'SQL',1),
('In MySQL, the InnoDB storage engine supports transactions.',1,'SQL',1),
('MySQL is primarily designed for small-scale applications and is not suitable for large-scale enterprise use.',1,'SQL',0),
('MySQL is an open-source database system.',1,'SQL',1),

('Which SQL statement is used to retrieve data from a table?',2,'SQL',null),
('What is the purpose of the SQL ORDER BY clause?',2,'SQL',null),
('Which SQL function is used to count the number of rows in a table?',2,'SQL',null),
('What does the SQL INSERT statement do?',2,'SQL',null),
('Which SQL clause is used to filter the results of a query?',2,'SQL',null),
('Which is the purpose of the SQL JOIN clause?',2,'SQL',null),
('Which SQL statement is used to update data in a table?',2,'SQL',null),
('What is the role of the SQL GROUP BY clause?',2,'SQL',null),
('Which SQL data type is used to store text of variable length?',2,'SQL',null),
('What is the purpose of the SQL DISTINCT keyword?',2,'SQL',null),
('Which SQL statement is used to delete data from a table?',2,'SQL',null),
('What is the purpose of the SQL PRIMARY KEY constraint?',2,'SQL',null),


('Which SQL statements are used to filter the results of a query?',3,'SQL',null),
('What is the purpose of the SQL JOIN clause?',3,'SQL',null),
('Which SQL functions are used to perform aggregate calculations?',3,'SQL',null),
('In SQL, what does the acronym "SQL" stand for?',3,'SQL',null),
('Which SQL clauses is/are used for sorting the result set?',3,'SQL',null),
('Which of the following are valid SQL JOIN types?',3,'SQL',null),
('Which SQL statements are used to modify the structure of a table?',3,'SQL',null),
('What is the role of the SQL DISTINCT keyword?',3,'SQL',null),
('Which SQL clauses is/are used to specify conditions for groups?',3,'SQL',null),
('Which SQL data types is/are used to store date and time values?',3,'SQL',null),
('Which SQL statements is/are used to delete data from a table?',3,'SQL',null),
('What is the purposes of the SQL PRIMARY KEY constraint?',3,'SQL',null);


----php--

insert tbQuestion VALUES
('The $_SESSION superglobal in PHP is used to store user data across pages.',1,'PHP',0),
('The empty() function in PHP returns true if a variable does not exist or has an empty value.',1,'PHP',1),
('In PHP, the substr() function is used to extract a portion of a string.',1,'PHP',1),
('he $_GET superglobal in PHP is used to receive data from a form submitted with the POST method.',1,'PHP',0),
('The include() function in PHP is used to include and evaluate a specific file.',1,'PHP',1),
('PHP is a server-side scripting language.',1,'PHP',1),
('The array_push() function in PHP is used to remove an element from the end of an array.',1,'PHP',0),
('PHP is an acronym for "Personal Home Page,',1,'PHP',0),
('The mysql_query() function in PHP is used to execute MySQL queries',1,'PHP',1),
('PHP supports both procedural and object-oriented programming paradigms.',1,'PHP',1),
('The file_exists() function in PHP checks if a file or directory exists',1,'PHP',1),
('The header() function in PHP is used for sending raw HTTP headers',1,'PHP',1),

('What is the purpose of the echo statement in PHP?',2,'PHP',null),
('Which of the following functions is used to find the length of a string in PHP?',2,'PHP',null),
('What does the acronym "PHP" stand for?',2,'PHP',null),
('How do you start a PHP session?',2,'PHP',null),
('In PHP, what is the purpose of the require statement?',2,'PHP',null),
('Which superglobal array is used to retrieve form data sent with the HTTP POST method?',2,'PHP',null),
('What does the array_pop() function do in PHP?',2,'PHP',null),
('Which of the following is used to comment out a single line in PHP?',2,'PHP',null),
('What is the purpose of the unset() function in PHP?',2,'PHP',null),
('Which function is used to open a file in PHP for reading?',2,'PHP',null),
('What is the purpose of the count() function in PHP?',2,'PHP',null),
('In PHP, which of the following is used to perform a case-insensitive string comparison?',2,'PHP',null),

('Which of the following are valid PHP data types?',3,'PHP',null),
('Which of the following are used to handle exceptions in PHP?',3,'PHP',null),
('What functions are used for file handling in PHP?',3,'PHP',null),
('Which of the following functions are used for working with dates in PHP?',3,'PHP',null),
('What is true about the in_array() function in PHP?',3,'PHP',null),
('What are correct statements regarding PHP magic constants?',3,'PHP',null),
('What functions are used for sorting arrays in PHP?',3,'PHP',null),
('What are correct ways to handle sessions in PHP?',3,'PHP',null),
('What are valid methods for sending data between pages in PHP?',3,'PHP',null),
('What are correct statements about the implode() function in PHP?',3,'PHP',null),
('What are valid functions for working with regular expressions in PHP?',3,'PHP',null),
('What are valid methods for redirecting in PHP?',3,'PHP',null)


--HTML--
insert tbQuestion VALUES
	('HTML stands for "Hyper Text Markup Language',1,'HTML',1),
	('The <head> section of an HTML document is used for displaying content on the web page.',1,'HTML',0),
	('Inline styles are applied using the style attribute within the HTML tag',1,'HTML',1),
	('The<article> element in HTML is used to define a piece of content that is tangentially related to the content around it',1,'HTML',0),
	('The <iframe> element is used to embed external content, such as videos or maps, into an HTML document.',1,'HTML',1),
	('The <nav> element is used to define navigation links on a webpage',1,'HTML',1),
	('The alt attribute in the <img> tag is required for accessibility purposes and should be left empty if the image is purely decorative',1,'HTML',0),
	('The HTML5 doctype declaration is <!DOCTYPE html>',1,'HTML',1),
	('The <strong> element is used to create bold text, while the <b> element is used for semantic importance',1,'HTML',0),
	('The <footer> element should be used only for copyright information and contact details',1,'HTML',0),
	('The <progress> element is used to represent the completion progress of a task',1,'HTML',1),
	('The <label> element is used to associate text with form elements and improve accessibility',1,'HTML',1),

    ('What does HTML stand for?',2,'HTML',null),
	('Which tag is used to create an ordered list in HTML?',2,'HTML',null),
	('In HTML, which attribute is used to provide alternative text for images for accessibility?',2,'HTML',null),
	('What does the HTML element <em> represent?',2,'HTML',null),
	('Which of the following tags is used to create a hyperlink in HTML?',2,'HTML',null),
	('What does the HTML element <br> represent?',2,'HTML',null),
	('Which HTML5 element is used to play audio files on a web page?',2,'HTML',null),
	('Which tag is used to define a table row in HTML?',2,'HTML',null),
	('What does the HTML attribute colspan represent in a table cell?',2,'HTML',null),
	('Which HTML element is used to create a form on a web page?',2,'HTML',null),
	('In HTML, which attribute is used to specify the character encoding for the document?',2,'HTML',null),
	('What does the HTML element <cite> represent?',2,'HTML',null),

    ('Which elements are commonly found in the <head> section of an HTML document?',3,'HTML',null),
	('Which of the following are valid HTML5 semantic elements?',3,'HTML',null),
	('In HTML, what does the alt attribute in the <img> tag provide?',3,'HTML',null),
	('Which attributes can be used in the <a> (anchor) tag for creating hyperlinks?',3,'HTML',null),
	('Which of the following tags are used for text formatting in HTML?',3,'HTML',null),
	('Which elements are used to define lists in HTML?',3,'HTML',null),
	('Which of the following are inline-level elements in HTML?',3,'HTML',null),
	('Which of the following tags are used in HTML forms?',3,'HTML',null),
	('What does the HTML element <nav> represent?',3,'HTML',null),
	('Which of the following are block-level elements in HTML?',3,'HTML',null),
	('what Different input types you can use in HTML?',3,'HTML',null),
	('những thuộc tính bảng trong HTML ?',3,'HTML',null)


   --CSS--

insert tbQuestion VALUES
   ('CSS stands for "Cascading Style Sheet',1,'CSS',1),
	('In CSS, the property font-size can only be specified in pixels',1,'CSS',0),
	('The margin property in CSS is used to add space inside an element',1,'CSS',0),
	('The box-sizing property in CSS allows you to control how the total width and height of an element is calculated',1,'CSS',1),
	('The float property in CSS is used to make an element float to the right or left within its container',1,'CSS',1),
	('CSS Grid is primarily designed for one-dimensional layouts',1,'CSS',0),
	('The z-index property in CSS only applies to positioned elements',1,'CSS',1),
	('The line-height property in CSS controls the space between the letters of text',1,'CSS',0),
	('The :before and :after pseudo-elements in CSS are used to insert content before and after an elements actual content',1,'CSS',1),
	('The CSS property opacity can only be set to a value between 0 and 1',1,'CSS',1),
	('The transition property in CSS is used to apply smooth transitions to an elements style changes',1,'CSS',1),
	('The display: none; property in CSS removes an element from the document flow but still allows it to take up space',1,'CSS',0),

    	('What does CSS stand for?',2,'CSS',null),
	('How can you select an element with the class "example" in CSS?',2,'CSS',null),
	('Which property is used to change the text color? ',2,'CSS',null),
	('What is the purpose of the "box-sizing: border-box;" property in CSS?',2,'CSS',null),
	('How do you center an element horizontally in CSS? ',2,'CSS',null),
	('Which property is used to set the background color of an element? ',2,'CSS',null),
	('What is the purpose of the "z-index" property in CSS? ',2,'CSS',null),
	('Which property is used to create space between the element border and content? ',2,'CSS',null),
	('What does the "display: none;" property do in CSS? ',2,'CSS',null),
	('Which property is used to make text bold? ',2,'CSS',null),
	('What is the purpose of the "float" property in CSS? ',2,'CSS',null),
	('Which property is used to change the style of a list item marker? ',2,'CSS',null),

    ('Which of the following are valid ways to include CSS in an HTML document?',3,'CSS',null),
	('Which of the following are valid CSS selectors?',3,'CSS',null),
	('What is the purpose of the media queries in CSS?',3,'CSS',null),
	('Which CSS property is used for adding shadows to text or elements?',3,'CSS',null),
	('What is the purpose of the CSS position property?',3,'CSS',null),
	('What is the purpose of the transition property in CSS?',3,'CSS',null),
	('Which CSS property is used to control the spacing between lines of text within an element?',3,'CSS',null),
	('The border-style property specifies what kind of border to display?',3,'CSS',null),
	('CSS has properties for specifying the margin for each side of an element?',3,'CSS',null),
	('CSS has properties for specifying the padding for each side of an element?',3,'CSS',null),
	('What values can the height and width attributes have?',3,'CSS',null),
	('What outline properties does CSS have?',3,'CSS',null),

    /* AJS*/
	('AngularJS is a JavaScript framework developed by Google',1,'AJS',1),
	('AngularJS uses two-way data binding to synchronize the model and the view automatically',1,'AJS',1),
	('Directives are markers on a DOM element that tell AngularJS to attach a specified behavior to that DOM element',1,'AJS',1),
	('Dependency Injection is a design pattern used in AngularJS to achieve better testability and maintainability',1,'AJS',1),
	('AngularJS supports both Single Page Applications (SPAs) and Multi-Page Applications (MPAs).',1,'AJS',1),
	('$scope in AngularJS is a global object that can be accessed from any part of the application',1,'AJS',0),
	('AngularJS uses the term "controller" to refer to both controllers and models interchangeably',1,'AJS',0),
	('In AngularJS, services are singleton objects that perform tasks common to the application',1,'AJS',1),
	('Filters in AngularJS are used for formatting data before it is displayed to the user',1,'AJS',1),
	('$routeProvider is used for configuring routes in AngularJS applications',1,'AJS',1),
	('AngularJS supports the use of custom directives to extend HTML with new attributes and elements',1,'AJS',1),
	('AngularJS applications can be tested using testing frameworks like Jasmine and Karma',1,'AJS',1),

    ('What does MVC stand for in the context of AngularJS? ',2,'AJS',null),
	('In AngularJS, what is the purpose of a directive? ',2,'AJS',null),
	('Which of the following is a two-way data binding expression in AngularJS? ',2,'AJS',null),
	('What is the purpose of ng-repeat in AngularJS?',2,'AJS',null),
	('What does $scope represent in AngularJS? ',2,'AJS',null),
	('Which service in AngularJS is used for making HTTP requests? ',2,'AJS',null),
	('How do you include external libraries (such as jQuery) in an AngularJS application? ',2,'AJS',null),
	('What does the ng-click directive do in AngularJS? ',2,'AJS',null),
	('Which of the following is used for dependency injection in AngularJS? ',2,'AJS',null),
	('What is the purpose of the $routeParams service in AngularJS? ',2,'AJS',null),
	('What is the purpose of the $compile service in AngularJS?',2,'AJS',null),
	('Which directive is used to conditionally display or hide HTML elements in AngularJS? ',2,'AJS',null),

    ('Which of the following are valid directive types in AngularJS?',3,'AJS',null),
	('Which of the following are ways to handle dependency injection in AngularJS?',3,'AJS',null),
	('Which of the following are valid ways to define routes in AngularJS?',3,'AJS',null),
	('Which of the following are lifecycle hooks in AngularJS controllers',3,'AJS',null),
	('Which of the following are valid ways to create a custom directive in AngularJS?',3,'AJS',null),
	('Which of the following are valid AngularJS filters?',3,'AJS',null),
	('Which directives can be used for conditional validation in AngularJS?',3,'AJS',null),
	('Which of the following are built-in directives in AngularJS?',3,'AJS',null),
	('Which of the following are valid ways to declare a module in AngularJS?',3,'AJS',null),
	('Which of the following are valid ways to handle routing in AngularJS?',3,'AJS',null),
	('Which of the following are valid methods to handle form validation in AngularJS?',3,'AJS',null),
	('Which of the following are valid ways to create custom directives in AngularJS?',3,'AJS',null);

Go
select *from tbQuestion


insert	 tbOption VALUEs
--java--
(13,'x = double;',0),
(13,'var = float x;',0),
(13,'x = int;',0),
(13,'var int x;',1),

(14,'method',0),
(14,'func',0),
(14,'start',0),
(14,'public',1),

(15,'To refer to the superclass object',0),
(15,'To create a new object',0),
(15,'To indicate a super method',0),
(15,'All of the above ',1),

(16,'To refer to the current instance of the class ',1),
(16,'To refer to the current class object',0),
(16,'To create a new object',0),
(16,'To call a superclass method',0),

(17,'void method() { }',0),
(17,'int method() { }',0),
(17,'None of the above',1),
(17,'method() { }',0),

(18,'true',0),
(18,'false',0),
(18,'The default value is not defined ',1),
(18,'0',0),

(19,'implements ',1),
(19,'extends',0),
(19,'multi',0),
(19,'interfaces',0),

(20,'To skip the current iteration of a loop',0),
(20,'To exit a method',0),
(20,'To terminate a loop or switch statement ',1),
(20,'To throw an exception',0),


(21,'Object obj = new Object create();',0),
(21,'Object obj = Object.new();',0),
(21,'Object obj = new Object(); ',1),
(21,'create Object obj = new Object();',0),

(22,'A and B',0),
(22,'To declare a method that belongs to the class rather than an instance ',1),
(22,'To create an object of a class',0),
(22,'To make a variable final',0),

(23,'To define the exception type',0),
(23,'To catch and handle exceptions',1),
(23,'To throw an exception',0),
(23,'To declare variables',0),

(24,'It is immutable',0),
(24,'It is synchronized',0),
(24,'It is more efficient than "String" for concatenating strings ',1),
(24,'It does not have a "toString" method',0),


(25,'public',1),
(25,'private',0),
(25,'protected',0),
(25,'default',1),

(26,'IOException',1),
(26,'RuntimeException',0),
(26,'SQLException',1),
(26,'NullPointerException',0),

(27,' equals()',1) ,
(27,'hashCode() ',1) ,
(27,'toString() ',1) ,
(27,'finalize()',0) ,


(28,'It calls the superclass of constructor',1),
(28,'It initializes the subclass of instance variables',0),
(28,'It creates a new object',0),
(28,'It is not a valid Java syntax',0),

(29,'Interfaces can extend other interfaces ',1),
(29,'Interfaces can have variables with values',0),
(29,'A class can implement multiple interfaces ',1),
(29,'Interfaces can have constructors',0),

(30,'It can be used to declare a constant variable ',1),
(30,'It prevents a class from being extended',1),
(30,'It makes a method unable to be overridden',1),
(30,'It indicates that an object cannot be modified after creation',0),

(31,'Extending the Thread class ',1),
(31,'Implementing the Runnable interface ',1),
(31,'Using the Executor framework',0),
(31,'Declaring a thread with "var" keyword',0),

(32,'Packages can be nested ',1),
(32,'Packages can contain subpackages ',1),
(32,'Package names must be unique across all Java programs',0),
(32,'Classes in the same package cannot access each other of members',0),

(33,'It checks if an object is an instance of a particular class ',1),
(33,'It determines the type of a variable',0),
(33,'It is used for type casting',0),
(33,'It is used to compare two objects for equality',0),

(34,'Annotations can be applied to classes, methods, and fields ',1),
(34,'Custom annotations cannot be created',0),
(34,'Annotations affect the runtime behavior of the program ',1),
(34,'Annotations are only used for documentation purposes',0),

(35,'To create a new object',0),
(35,'To propagate an exception ',1),
(35,'To catch an exception',0),
(35,'To declare a custom exception class',0),

(36,'Using try-catch blocks ',1),
(36,'Declaring exceptions with the "throws" keyword ',1),
(36,'Ignoring exceptions',0),
(36,'Handling exceptions with "if-else" statements',0)

--sql--
insert tbOption values
(49,'FETCH',0),
(49,'SELECT',1),
(49,'EXTRACT',0),
(49,'RETRIEVE',0),

(50,'Filter data',0),
(50,' Group data',0),
(50,'Sort data ',1),
(50,'Join tables',0),

(51,'TOTAL()',0),
(51,' COUNT()',1),
(51,' SUM()',0),
(51,'AVG()',0),

(52,'Deletes records from a table',0),
(52,'Updates records in a table',0),
(52,'Inserts new records into a table',1),
(52,'Retrieves records from a table',0),


(53,'GROUP BY',0),
(53,'WHERE',1),
(53,'HAVING',0),
(53,'ORDER BY',0),

(54,'Create a new table',0),
(54,'Combine rows from two or more tables',1),
(54,'Order the result set',0),
(54,'Filter data',0),

(55,'MODIFY',0),
(55,'UPDATE',1),
(55,'ALTER',0),
(55,'CHANGE',0),

(56,'Filter data',0),
(56,'Sort data',0),
(56,'Group rows based on a column',1),
(56,'Join tables',0),

(57,'CHAR',0),
(57,'VARCHAR',1),
(57,'TEXT',0),
(57,'STRING',0),

(58,'Filter NULL values',0),
(58,'Remove duplicate rows from the result set ',1),
(58,'Group rows based on a column',0),
(58,'Sort the result set',0),

(59,'REMOVE',0),
(59,'DELETE',1),
(59,'DROP',0),
(59,'ERASE',0),

(60,'Enforce uniqueness on a column ',1),
(60,'Allow NULL values in a column',0),
(60,'Auto-increment a column',0),
(60,'Create a foreign key relationship',0),


(61,'GROUP BY',0),
(61,'WHERE',1),
(61,'HAVING',1),
(61,'ORDER BY',0),

(62,'Combine rows from two or more tables',1),
(62,'Filter data',0),
(62,'Order the result set',0),
(62,'Create a new table ',1),


(63,'TOTAL()',0),
(63,'COUNT()',1),
(63,'SUM() ',1),
(63,'AVG() ',1),

(64,'Structured Query Language ',1),
(64,'Sequential Query Language',0),
(64,'System Query Language ',1),
(64,'Simplified Query Language',0),

(65,'GROUP BY',0),
(65,'WHERE',0),
(65,'ORDER BY ',1),
(65,'HAVING',1),

(66,'INNER JOIN',1),
(66,'OUTER JOIN',0),
(66,'LEFT JOIN ',1),
(66,'CROSS JOIN',0),

(67,'MODIFY',0),
(67,' UPDATE',0),
(67,'ALTER ',1),
(67,'CHANGE',1),

(68,' Filter NULL values',0),
(68,'Remove duplicate rows from the result set ',1),
(68,'Group rows based on a column ',1),
(68,'Sort the result set',0),

(69,'WHERE',0),
(69,'HAVING',1),
(69,' GROUP BY',1),
(69,'ORDER BY',0),

(70,'DATE',0),
(70,'TIME',0),
(70,'TIMESTAMP',1),
(70,'DATETIME',1),


(71,'REMOVE',0),
(71,'DELETE',1),
(71,'DROP',1),
(71,'ERASE',0),

(72,'Enforce uniqueness on a column ',1),
(72,'Allow NULL values in a column',0),
(72,'Auto-increment a column',0),
(72,'Create a foreign key relationship ',1)

--php---
INSERT tbOption values
(85,'To define a constant',0),
(85,'To display output to the browser',1),
(85,'To create a session variable',0),
(85,'To declare a function',0),

(86,'str_count()',0),
(86,'lengthOf()',0),
(86,'strlen()',1),
(86,'string_size()',0),

(87,'Personal Home Page',0),
(87,'Preprocessor Hypertext',0),
(87,'Programming Hypertext Processor',1),
(87,'Private Hosting Platform',0),

(88,'begin_session()',0),
(88,'start()',0),
(88,'session_start()',1),
(88,'init_session()',0),

(89,'To include and evaluate a file',1),
(89,'To declare a constant',0),
(89,'To define a function',0),
(89,'To create a loop',0),

(90,'$_GET',0),
(90,'$_POST',1),
(90,'$_REQUEST',0),
(90,'$_DATA',0),

(91,'Adds an element to the beginning of an array',0),
(91,'Removes the last element from an array',1),
(91,'Combines two arrays into one',0),
(91,'Retrieves the first element of an array',0),

(92,' /* ... */',0),
(92,' // ...',1),
(92,'<!-- ... -->',0),
(92,' # ...',0),

(93,'To destroy a session',0),
(93,'To remove a variable or element',1),
(93,'To redirect to another page',0),
(93,'To end a loop',0),

(94,'fopen()',1),
(94,'readfile()',0),
(94,'open_file()',0),
(94,'file_open()',0),

(95,'To calculate the average value of an array',0),
(95,'To count the number of elements in an array',1),
(95,'To find the maximum value in an array',0),
(95,'To determine if a variable is set',0),

(96,'strcmp()',0),
(96,'strcoll()',0),
(96,'strncasecmp()',0),
(96,' strcasecmp()',1),

(97,'STRING',1),
(97,'BOOLEAN',1),
(97,'None',0),
(97,'A and B',1),

(98,'try',1),
(98,'finally',1),
(98,'catch',1),
(98,'throw',0),

(99,'fopen()',1),
(99,'file_get_contents()',1),
(99,'readfile()',1),
(99,'writefile()',0),

(100,'date()',1),
(100,'time()',1),
(100,'strtotime()',1),
(100,'format_date()',0),


(101,'Checks if a value exists in an array',1),
(101,'Compares array values',0),
(101,'Searches for a value in an array',1),
(101,'Determines if a key exists in an array',0),


(102,'__LINE__ returns the current line number of the file',1),
(102,'__FUNCTION__ returns the name of the current function',0),
(102,'__DIR__ returns the directory of the file',1),
(102,'__FILE__ returns the full path and filename of the file',1),

(103,'sort()',1),
(103,'asort()',1),
(103,'shuffle()',0),
(103,'rsort()',1),

(104,'session_start()',1),
(104,'session_destroy()',1),
(104,'session_unset()',1),
(104,'start_session()',0),

(105,'GET',1),
(105,'POST',1),
(105,'PUT',0),
(105,'DATA',0),

(106,'Is an alias for explode()',0),
(106,'Joins array elements with a string',1),
(106,'Returns a string created by concatenating array elements',1),
(106,'Accepts only arrays as input',0),


(107,'preg_match()',1),
(107,'regex_search()',0),
(107,'str_replace()',1),
(107,'pattern_match()',0),

(108,'header()',1),
(108,'redirect()',1),
(108,'location()',1),
(108,'go_to()',0);

INSERT into  tbOption  VALUEs
/* dap an ONE OPTIN  <HTML>  */
(121,'Hyper Transfer Markup Language',0),
(121,'Hyper Text Makeup Language',0),
(121,'Hyper Text Markup Language',0),
(121,'High-Level Text Language',1),

(122,'<ol>',1),
(122,'<ul>',0),
(122,'<li>',0),
(122,'<dl>',0),

(123,'title',0),
(123,'alt',1),
(123,'description',0),
(123,'image',0),

(124,'Emphasis text',1),
(124,'End text',0),
(124,'Enlarged text',0),
(124,'Embedded text',0),

(125,'<link>',0),
(125,'<a>',1),
(125,'<hlink>',0),
(125,'<href>',0),

(126,'Break line',1),
(126,'Bold text',0),
(126,'Background color',0),
(126,'Block element',0),

(127,'<audio>',1),
(127,'<sound>',0),
(127,'<music>',0),
(127,'<play>',0),

(128,'<row>',0),
(128,'<td>',0),
(128,'<tr>',1),
(128,'<table-row>',0),

(129,'Color of the cell',0),
(129,'Column span',1),
(129,'Content spacing',0),
(129,'Cell spacing',0),

(130,'<input>',0),
(130,'<form>',1),
(130,'<submit>',0),
(130,'<fieldset>',0),

(131,'charset',1),
(131,'encoding',0),
(131,'meta',0),
(131,'document-charset',0),

(132,'Citation',1),
(132,'Caption',0),
(132,'Code',0),
(132,'Character',0)
go
INSERT into  tbOption  VALUEs
/*MORE OPTION HTML */
(133,'<title>',1),
(133,'<meta>',1),
(133,'<body>',0),
(133,'<style>',1),

(134,'<section>',1),
(134,'<div>',0),
(134,'<article>',1),
(134,'<span>',0),

(135,'Image description for accessibility',1),
(135,'Alignment of the image',0),
(135,'Alternative text for decoration',1),
(135,'Image source URL',0),

(136,'href',1),
(136,'src',0),
(136,'alt',0),
(136,'target',1),

(137,'<strong>',1),
(137,'<em>',1),
(137,'<mark>',1),
(137,'<format>',0),

(138,'<ul>',1),
(138,'<li>',1),
(138,'<ol>',1),
(138,'<dl>',0),

(139,'<div>',0),
(139,'<span>',1),
(139,'<p>',1),
(139,'<h1>',0),

(140,'<input>',1),
(140,'<form>',1),
(140,'<submit>',0),
(140,'<label>',1),

(141,'Navigation links',1),
(141,'A new webpage',0),
(141,'A navigation bar',1),
(141,'News articles',0),

(142,'<span>',0),
(142,'<p>',1),
(142,'<em>',0),
(142,'<strong>',1),

(143,'<input type="button">',1),
(143,'.<input type="checkbox">',1),
(143,'. <input type="color">',1),
(143,'All is incorrect',0),

(144,'Border',1),
(144,'Cellpadding',1),
(144,'Cellspacing',1),
(144,'.All is incorrect',0)
go

select*from tbquestion
insert into tbOption VALUES
/* CSS */
(157,'Creative Style Sheets',0),
(157,'Cascading Style Sheets',1),
(157,'Computer Style Sheets',0),
(157,'Colorful Style Sheets ',0),

(158,'#example ',0),
(158,'.example ',1),
(158,'element.example ',0),
(158,'id.example',0),

(159,'font-color ',0),
(159,'text-color ',0),
(159,' color ',1),
(159,'font-style',0),

(160,'Adds a border around the box',0),
(160,'Includes the padding and border in the box is total width and height ',1),
(160,'Removes the box is borders',0),
(160,'Has no effect on the box model',0),

(161,'text-align: center ',0),
(161,'align: center ',0),
(161,'margin: auto ',1),
(161,'position: center',0),

(162,'bg-color ',0),
(162,'background-color ',1),
(162,'color ',0),
(162,'bg',0),

(163,'Adjusts the size of an element ',0),
(163,'Sets the position of an element along the z-axis  ',0),
(163,'Controls the stacking order of elements ',1),
(163,'Manages the visibility of an element ',0),

(164,'padding',1),
(164,'margin ',0),
(164,'space  ',0),
(164,'border-spacing ',0),

(165,'Hides the element from the page ',1),
(165,'Displays the element as a block ',0),
(165,'Removes the element is border',0),
(165,'Has no effect on the element is visibility',0),


(166,'font-weight ',1),
(166,'bold ',0),
(166,'text-weight ',0),
(166,'weight',0),

(167,'Makes elements float to the top of the page ',0),
(167,'Specifies whether or not an element should float ',0),
(167,'Positions an element to the left or right of its container ',1),
(167,'Has no effect on the layout of elements',0),

(168,'list-style-type ',1),
(168,'marker-style  ',0),
(168,'list-marker-style  ',0),
(168,'list-style-image',0),
(169,'External stylesheet using <link> element',1),
(169,'Inline styles using the <style> element',1),
(169,'Internal stylesheet using the <style> element',1),
(169,'All is incorrect',0),

(170,'Class selector (e.g., .example)',1),
(170,'Parent selector (e.g., parent > child)',0),
(170,'ID selector (e.g., #header)',1),
(170,'Universal selector (e.g., *)',1),

(171,'Adjust the layout and styles based on the characteristics of the device or viewport',1),
(171,'Create queries for a database',0),
(171,'Define media-related styles for print or screen',0),
(171,'Add multimedia content to the webpage',0),/**********/

(172,'opacity',0),
(172,'blur',0),
(172,'box-shadow',1),
(172,'text-shadow',0),/************/

(173,'Defines the positioning method used for an element',1),
(173,'Adjusts the width of an element',0),
(173,'Sets the position of an element relative to its normal position',1),
(173,'Controls the visibility of an element',0),

(174,'Adds a smooth transition effect to a specified CSS property change',1),
(174,'Defines the duration of a CSS animation',0),
(174,'Sets the color of a background',0),
(174,'Specifies the speed curve of the transition effect',0),/**********/


(175,'line-height',1),
(175,'margin-bottom',0),
(175,'padding-top',0),
(175,'spacing-line',0),

(176,'solid',1),
(176,'double',1),
(176,'outset',1),
(176,'only A & C ',0),

(177,'text-align: center',0),
(177,'margin-top',1),
(177,'margin-right',1),
(177,'margin-bottom ',1),

(178,'justify-content: center; ',0),
(178,'padding-right ',1),
(178,'padding-bottom',1),
(178,'padding-left ',1),

(179,'Initial',1),
(179,' length',1),
(179,'%',1),
(179,'All is incorrect',0),

(180,'outline-style',1),
(180,'outline-color',1),
(180,'outline-width',1),
(180,'All is incorrect',0)
go

--AJS--
insert into tbOption VALUES
(193,'Model-View-Controller ',1),
(193,'Main-View-Controller ',0),
(193,'Modular-View-Component ',0),
(193,'Multi-View-Cache ',0),

(194,'To define routes  ',0),
(194,'To bind data to HTML elements  ',0),
(194,'To create custom HTML elements and attributes *  ',0),
(194,'To handle server-side logic ',0),

(195,'{{ expression }} ',0),
(195,'[ expression ]  ',0),
(195,'ng-bind  ',0),
(195,'ng-model  ',1),

(196,'To create a new scope',0),
(196,'To repeat a block of HTML code for each item in a collection ',1),
(196,'To define a route ',0),
(196,'To declare a controller',0),

(197,'A built-in service ',0),
(197,'A controller ',0),
(197,'A global object that binds the controller with the view ',1),
(197,'A directive ',0),

(198,'$http ',1),
(198,'$ajax ',0),
(198,'$request ',0),
(198,'$xhr',0),

(199,'Use the <script> tag in HTML ',1),
(199,'Include them in the AngularJS module definition',0),
(199,'Use the ng-include directive ',0),
(199,'Use the angular.module(app).require() method ',0),

(200,'It triggers a click event on an element',0),
(200,'It binds an event handler to the click event of an element ',1),
(200,'It defines a new controller function',0),
(200,'It dynamically generates HTML content ',0),

(201,'@Inject',0),
(201,'$provide ',0),
(201,'$injector ',1),
(201,'ng-dependency',0),

(202,'To define route parameters ',0),
(202,'To access parameters passed in the URL ',1),
(202,'To manage route configuration',0),
(202,'To handle route events ',0),

(203,'To compile HTML templates into JavaScript code',0),
(203,'To convert AngularJS expressions into JavaScript functions ',0),
(203,'To link the template with the scope and produce a live view ',1),
(203,'To minify AngularJS code',0),


(204,'ng-show ',0),
(204,'ng-hide ',0),
(204,'ng-if ',0),
(204,'All of the above ',1),
(205,'Element directives ',1),
(205,'Attribute directives ',1),
(205,'CSS directives ',0),
(205,'Module directives',0),

(206,'Constructor injection ',1),
(206,'$inject service ',0),
(206,'Implicit injection using function parameters ',1),
(206,'Explicit injection using $injector service ',0),

(207,'Using ng-router module ',1),
(207,'Using ui-router module ',1),
(207,'Using ng-route directive  ',0),
(207,'Using ng-controller directive',0),

(208,'$onInit ',1),
(208,'$onDestroy ',1),
(208,'$preInit ',0),
(208,'$postRender',0),

(209,'Using the "directive" method in a module ',1),
(209,'Using the "ng-directive" attribute ',0),
(209,'Declaring a custom HTML tag ',1),
(209,'Both a, b and c ',0),

(210,'currency ',1),
(210,'uppercase ',1),
(210,'limitTo ',1),
(210,'Special characters  ',0),

(211,'"ng-required" directive',1),
(211,'"ng-disabled" directive ',1),
(211,'"ng-if" directive  ',1),
(211,'"ng-validate" directive  ',0),

(212,'ng-repeat ',1),
(212,'ng-if ',1),
(212,'ng-switch',1),
(212,'ng-case ',0),

(213,'angular.module(`myApp`, []) ',1),
(213,'ng-app="myApp" ',0),
(213,' var app = angular.module(`myApp`, [ ])',0),
(213,'All are correct',0),

(214,'Using ng-route module ',1),
(214,'Using ui-router module ',1),
(214,'Using ng-controller',0),
(214,'Using ng-view',1),

(215,'Using ng-required directive',1),
(215,'Using ng-minlength and ng-maxlength directives',1),
(215,'Using custom validation functions in ng-model',1),
(215,'Using ng-pattern directive ',1),

(216,'Using the restrict property',1),
(216,'Using the template property',1),
(216,'Using the link function ',1),
(216,'Using the ng-create directive ',0)

go

