with recursive ancestory as (
    select id, parent_id, 1 as generation
    from ecoli_data e
    where e.parent_id is null

    union all
    
    select e.id, e.parent_id, a.generation + 1
    from ecoli_data e
    inner join ancestory a on e.parent_id = a.id)
select count(*) as count, a.generation
from ancestory a
WHERE not EXISTS (
    SELECT 1
    FROM ecoli_data c
    WHERE c.parent_id = a.id
)
group by a.generation
order by a.generation;