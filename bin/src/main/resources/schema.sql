create table if not exists history(
id int auto_increment primary key,
DateTime varchar(50),
StockTicker varchar(20),
Price int,
Volume int,
BuyOrSell varchar(20));

create table if not exists holdings(
id int auto_increment primary key,
StockTicker varchar(20),
price int,
Volume int);

create table if not exists stocks(
id int auto_increment primary key,
company varchar(200),
stockTicker varchar(20),
price float,
volume int);

insert into stocks(stockTicker, company, price, volume) values ('AAPL', 'Apple',30.67, 20000);
insert into stocks(stockTicker, company, price, volume) values ('INTC', 'Intel Corporation', '123', 10000);
insert into stocks(stockTicker, company, price, volume) values ('CSCO', 'CISCO Corporation', '78', 124563);

