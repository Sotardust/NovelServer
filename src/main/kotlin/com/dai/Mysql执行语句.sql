# SELECT lastName FROM employees ORDER BY  lastName
# SELECT DISTINCT lastName FROM employees ORDER BY  lastName
# SELECT DISTINCT  state, city FROM  customers WHERE  state IS NOT NULL ORDER BY state , city
# SELECT COUNT(DISTINCT state) FROM customers WHERE country ="USA"
# SELECT DISTINCT state FROM customers WHERE state IS NOT NULL LIMIT 3;
# SELECT lastname, firstname, jobtitle FROM employees WHERE jobtitle = 'Sales Rep' AND officeCode = 1
# SELECT lastname, firstname, jobtitle FROM employees WHERE jobtitle <> 'Sales Rep';
# SELECT lastname, firstname, officeCode FROM employees WHERE officecode > 5;
# SELECT productCode, productName, buyPrice FROM products WHERE  buyPrice NOT BETWEEN 20 AND 100;

# 当使用BETWEEN运算符与日期类型值时，要获得最佳结果，应该使用类型转换将列或表达式的类型显式转换为DATE类型。
# SELECT orderNumber,requiredDate,status FROM orders
#   WHERE requireddate BETWEEN CAST('2013-01-01' AS DATE)AND CAST('2013-01-31' AS DATE);
# SELECT employeeNumber, lastName, firstName FROM employees WHERE firstName LIKE 'a%'; 以a开头
# SELECT employeeNumber, lastName, firstName FROM employees WHERE firstName LIKE '%a'; 以a结尾
# SELECT employeeNumber, lastName, firstName FROM employees WHERE firstName LIKE '%a%'; 只要包含a 就会被列出

# 查找名字以T开头的员工，以m结尾，并且包含例如Tom，Tim之间的任何单个字符
# SELECT employeeNumber, lastName, firstName FROM employees WHERE firstname LIKE 'T_m';

# MySQL允许将NOT运算符与LIKE运算符组合，以找到不匹配特定模式的字符串。查找不以B开头的字符串
# SELECT employeeNumber, lastName, firstName FROM employees WHERE lastName NOT LIKE 'B%';

# 则反斜杠字符\是默认转义字符。如下语句，将查询productCode字段中包含_20字符串的值
# SELECT productCode, productName FROM products WHERE productCode LIKE '%\_20%';

# 也可以使用ESCAPE子句指定一个不同的转义字符，例如$：
# SELECT productCode, productName FROM products WHERE  productCode LIKE '%$_20%' ESCAPE '$';

# 查找位于美国和法国的办事处，可以使用IN运算符作为以下查询：
# SELECT officeCode, city, phone, country FROM offices WHERE country IN ('USA' , 'France');
# SELECT officeCode, city, phone FROM offices WHERE country = 'USA' OR country = 'France'; 执行结果相同

# 获得不在美国和法国的办事处，在WHERE子句中使用NOT IN如下：
# SELECT officeCode, city, phone FROM offices WHERE country NOT IN( 'USA', 'France');

# IN运算符通常用于子查询。子查询不提供常量值列表，而是提供值列表。查找总金额大于60000的订单，则使用IN运算符查询如下
# SELECT orderNumber, customerNumber, status, shippedDate FROM orders WHERE orderNumber IN (
#     SELECT orderNumber FROM orderDetails GROUP BY orderNumber HAVING SUM(quantityOrdered * priceEach) > 60000);

# 使用LIMIT子句来选择表中的前N行记录。查询employees表中前5个客户
# SELECT customernumber, customername, creditlimit FROM customers LIMIT 5;
# SELECT customernumber, customername, creditlimit FROM customers LIMIT 3,10;

# 要查询信用额度最高的前五名客户
# SELECT customernumber, customername, creditlimit FROM customers ORDER BY creditlimit DESC LIMIT 5;

# 使用MySQL LIMIT获得第n个最高值
# 解决步骤：首先，按照降序对结果集进行排序。第二步，使用LIMIT子句获得第n贵的产品。
# (注意：偏移量从0开始，所以要指定从1开始，然后取一行记录）
# SELECT productCode, productName, buyprice FROM  products ORDER BY buyprice DESC LIMIT 1, 1;

# 查询有销售代表的客户，请使用IS NOT NULL运算符
# SELECT customerName,country,salesrepemployeenumber FROM customers
# WHERE salesrepemployeenumber IS NOT NULL ORDER BY customerName;

# 要按姓氏按降序和名字按升序排序联系人，请在相应列中分别指定DESC和ASC
# SELECT  contactLastname,  contactFirstname FROM customers
# ORDER BY contactLastname DESC,contactFirstname ASC LIMIT 10;

# ORDER BY子句允许使用FIELD()函数为列中的值定义自己的自定义排序顺序。
# SELECT  orderNumber, status FROM  orders
# ORDER BY FIELD(status,'In Process','On Hold','Cancelled','Resolved','Disputed','Shipped');

# 为输出的标题分配一个有意义的列别名
# SELECT CONCAT_WS(', ', lastName, firstname) AS `Full name`FROM employees;

# 使用ORDER BY，GROUP BY和HAVING子句中的列别名来引用该列。
# SELECT orderNumber `Order no.`, SUM(priceEach * quantityOrdered) total
# FROM orderdetails GROUP BY `Order no.`HAVING total > 60000;

