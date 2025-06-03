-- 코드를 작성해주세요
select dept.dept_id, dept.dept_name_en, emp.avg_sal
from hr_department dept
join (select dept_id, round(avg(sal),0) as avg_sal
          from hr_employees
          group by dept_id) emp on dept.dept_id = emp.dept_id
order by emp.avg_sal desc;