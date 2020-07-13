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

/* Average used memory in percentage over 5 mins interval for each host. (used memory = total memory
 * - free memory). */
select 
    id, hostname, timestamp, average * 100 / total_mem as avg_used_mem_perc
from 
    (
        select
            id, hostname, date_trunc('minute', u.timestamp) as timestamp, total_mem, memory_free, avg(total_mem - memory_free) over (partition by id) average
        from 
            host_info as i
        join
            host_usage as u
        on
            i.id = u.host_id
    ) tmp
order by 
    id;
