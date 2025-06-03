-- 코드를 작성해주세요
select m.id, s.fish_name, m.length
from fish_info m
inner join fish_name_info s on m.fish_type = s.fish_type
where (m.fish_type,m.length) in (select fish_type,max(length)
            from fish_info
            group by fish_type)
order by m.id;