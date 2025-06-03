-- 코드를 작성해주세요
select s.id, s.genotype, p.genotype as parent_genotype
from ecoli_data s
inner join ecoli_data p on s.parent_id = p.id
where (s.genotype & p.genotype) = p.genotype
order by s.id;