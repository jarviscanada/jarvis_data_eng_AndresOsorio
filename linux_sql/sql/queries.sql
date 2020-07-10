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


select 
    id, hostname, timestamp, average * 100 / total_mem as avg_used_mem_perc
from 
    (
        select
            id, hostname, u.timestamp, total_mem, memory_free, avg(total_mem - memory_free) over (partition by id) average
        from 
            host_info as i
        join
            host_usage as u
        on
            i.id = u.host_id
    ) tmp
order by 
    id;
