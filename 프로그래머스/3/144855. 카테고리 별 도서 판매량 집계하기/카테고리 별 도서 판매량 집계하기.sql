select b.category, sum(s.sales) as total_sales
from book b
join book_sales s on b.book_id = s.book_id
WHERE YEAR(s.sales_date) = 2022
  AND MONTH(s.sales_date) = 1
group by b.category
order by b.category;