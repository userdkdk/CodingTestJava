-- 코드를 작성해주세요
select emp.emp_no, emp.emp_name, gr.grade, case
    when gr.grade = 'S' then emp.sal * 0.2
    when gr.grade = 'A' then emp.sal * 0.15
    when gr.grade = 'B' then emp.sal * 0.1
    else 0
    end as bonus
from hr_employees emp
join (select emp_no, case
      when avg(score) >= 96 then 'S'
      when avg(score) >= 90 then 'A'
      when avg(score) >= 80 then 'B'
      else 'C'
      end as grade
     from hr_grade
     group by emp_no) gr on emp.emp_no = gr.emp_no
order by emp_no;