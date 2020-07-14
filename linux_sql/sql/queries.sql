/* Group hosts by CPU number and sort by their memory size in descending order(within each
 * cpu_number group) */ 
select 
    cpu_number, id, total_mem
from 
    (   select 
            count(*) over(partition by cpu_number), cpu_number, id, total_mem
        from 
            host_info
    ) as tmp
order by
    cpu_number, total_mem desc;

/* function to round timestamp to the nearest 5-min interval */
create function round5(ts timestamp) returns timestamp as 
$$
begin
    return date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
end;
$$
    language plpgsql;

/* Average used memory in percentage over 5 mins interval for each host; (used memory = total memory) */
select 
    id, hostname, round5(u.timestamp) as roundedts, avg(total_mem - memory_free) * 100 / total_mem as avg_used_mem_perc
from
    host_info as i
join
    host_usage as u
on 
    i.id = u.host_id
group by
    id, roundedts
order by
    id, roundedts;

/* Query to detect node failure; the cron job is supposed to insert a new entry to the host_usage
 * table every minute when the server is healthy; we can assume that a server fails when it
 * inserts less than three data points within 5-min interval. */
select
    host_id, ts, num_data_points
from 
    (
        select
            host_id, round5(timestamp) as ts, count(round5(timestamp)) as num_data_points
        from 
            host_usage
        group by
             host_id, ts
        order by
            host_id, ts
    ) as tmp
where
    num_data_points < 3;
