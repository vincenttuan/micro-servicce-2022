-- config/Chunk_4_ItemReadFromDB.java 讀取數據用
CREATE TABLE user(
    id int NOT NULL AUTO_INCREMENT, -- 主鍵(序號)
    username varchar(50) not null UNIQUE, -- (姓名)
    password varchar(50) not null, -- (密碼)
    age int not null, -- (年齡)
    PRIMARY KEY (id)
);

INSERT INTO user(username, password, age) values('john', '1111', 21);
INSERT INTO user(username, password, age) values('mary', '2222', 22);
INSERT INTO user(username, password, age) values('bobo', '3333', 23);
INSERT INTO user(username, password, age) values('helen', '4444', 24);
INSERT INTO user(username, password, age) values('mark', '5555', 25);
INSERT INTO user(username, password, age) values('jean', '6666', 26);

-- config/Chunk_5_ItemWriterToDB.java 寫入數據用
CREATE TABLE customer(
    id int NOT NULL AUTO_INCREMENT, -- 主鍵(序號)
    cname varchar(50) not null, -- (姓名)
    birthday varchar(50), -- (生日)
    PRIMARY KEY (id)
);
