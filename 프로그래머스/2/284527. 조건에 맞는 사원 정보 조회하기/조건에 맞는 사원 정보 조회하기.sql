-- 코드를 작성해주세요
select gr.score as score, e.emp_no, e.emp_name, e.position, e.email
from hr_employees e
join (select emp_no, sum(score) as score
     from hr_grade
     group by emp_no) gr on e.emp_no = gr.emp_no
order by gr.score desc
limit 1;