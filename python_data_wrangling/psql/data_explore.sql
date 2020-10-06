-- show table schema; deprecated for psql < 12
-- \d+ retail

-- Q1: Show first 10 rows
select * 
from  retail 
limit 10;

-- Q2: Check # of records
select count(*) 
from retail; 

-- Q3: number of clients (e.g. unique client ID)
select count(customers)
from (
	select distinct(customer_id) customers
	from retail
	)
as r2;

-- Q4: invoice date range (e.g. max/min dates)
select min(invoice_date) min, max(invoice_date) max
from retail;

-- Q5: number of SKU/merchants (e.g. unique stock code)
select count(stocks) 
from (
	select distinct(stock_code) stocks
	from retail
	)
as r2;

-- Q6: Calculate average invoice amount excluding invoices with a negative amount (e.g. canceled orders have negative amount)
select avg(invoice)
from (
	select invoice_no, sum(amount) invoice 
	from (
		select invoice_no, quantity*unit_price amount
		from retail
		) r2
	group by invoice_no
	having sum(amount) >= 0
	) 
as r3;

-- Q7: Calculate total revenue (e.g. sum of unit_price * quantity)
select sum(amount) 
from (
	select quantity*unit_price amount 
	from retail
--	where unit_price >= 0
	)
as r2;

-- Q8: Calculate total revenue by YYYYMM
select y*100+m yyyymm, sum(amount) 
from (
	select invoice_date, cast(extract(year from invoice_date) as integer) y, cast(extract(month from invoice_date) as integer) m, quantity*unit_price amount
	from retail
	) as r2
group by yyyymm
order by yyyymm asc;
