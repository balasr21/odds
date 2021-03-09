DROP TABLE IF EXISTS odds;
DROP TABLE IF EXISTS bet;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id UUID NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
  user_id varchar(255),
  created_time                    timestamp,
  status varchar(255)
);

CREATE TABLE bet (
  id UUID NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
  bet_id int,
  name varchar(255),
  category varchar(255),
  event_date date,
  event_time int,
  status varchar(255)
);

CREATE TABLE odds (
  id UUID NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
  initial_bet varchar(255),
  profit_won varchar(255),
  created_time                    timestamp,
  bet_id varchar(255),
  status varchar(255),
  user_id varchar(255)
);

INSERT INTO users(user_id,created_time,status) values(1,CURRENT_TIMESTAMP(),'ACTIVE');
INSERT INTO users(user_id,created_time,status) values(2,CURRENT_TIMESTAMP(),'ACTIVE');
INSERT INTO users(user_id,created_time,status) values(3,CURRENT_TIMESTAMP(),'ACTIVE');


INSERT INTO bet(bet_id,name,category,event_date,event_time,status) values(0,'Southwell','Horse Racing',CURRENT_TIMESTAMP(),8*60*60,'ACTIVE');
INSERT INTO bet(bet_id,name,category,event_date,event_time,status) values(1,'Lingfield','Horse Racing',CURRENT_TIMESTAMP(),9*45*60,'ACTIVE');
INSERT INTO bet(bet_id,name,category,event_date,event_time,status) values(2,'English Premier League','Football',CURRENT_TIMESTAMP(),10*15*60,'ACTIVE');
INSERT INTO bet(bet_id,name,category,event_date,event_time,status) values(3,'Spanish La Liga Primera','Football',CURRENT_TIMESTAMP(),12*15*60,'ACTIVE');
INSERT INTO bet(bet_id,name,category,event_date,event_time,status) values(4,'French Cup','Football',CURRENT_TIMESTAMP(),13*60*60,'ACTIVE');


