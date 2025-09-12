with total_first_half as (
    select flavor, sum(total_order) as total_count
    from first_half
    group by flavor)
, total_july as (
    select flavor, sum(total_order) as total_count
    from july
    group by flavor)
select tf.flavor
from total_first_half tf
join total_july tj on tf.flavor = tj.flavor
order by (tf.total_count + tj.total_count) desc
limit 3;