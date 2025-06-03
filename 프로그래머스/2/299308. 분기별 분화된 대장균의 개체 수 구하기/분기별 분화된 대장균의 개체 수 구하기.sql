-- 코드를 작성해주세요
select concat(quarter(differentiation_date),'Q') as quarter,
    count(*) as ecoli_count
from ecoli_data
group by concat(quarter(differentiation_date),'Q');