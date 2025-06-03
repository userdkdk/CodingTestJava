-- 코드를 작성해주세요
select id,
    case
        when p_rank <= 0.25 then 'CRITICAL'
        when p_rank <= 0.5 then 'HIGH'
        when p_rank <= 0.75 then 'MEDIUM'
        else 'LOW'
    END as COLONY_NAME
from (
    select id,
        percent_rank() over (order by size_of_colony desc) as p_rank
    from ecoli_data) as ranked
order by id;