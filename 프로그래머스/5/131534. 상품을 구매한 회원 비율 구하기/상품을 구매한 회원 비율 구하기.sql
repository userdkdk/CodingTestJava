SELECT 
    YEAR(o.sales_date)  AS `YEAR`,
    MONTH(o.sales_date) AS `MONTH`,
    COUNT(DISTINCT o.user_id) AS `PURCHASED_USERS`,
    ROUND(
        COUNT(DISTINCT o.user_id) * 1.0 /
        (SELECT COUNT(*) FROM USER_INFO WHERE YEAR(joined) = 2021)
    , 1) AS `PURCHASED_RATIO`
FROM ONLINE_SALE o
JOIN USER_INFO u ON u.user_id = o.user_id
WHERE YEAR(u.joined) = 2021
GROUP BY YEAR(o.sales_date), MONTH(o.sales_date)
ORDER BY YEAR(o.sales_date), MONTH(o.sales_date);
