# CREATE TABLE IF NOT EXISTS tasks (
#   task_id INT(11) AUTO_INCREMENT,
#   subject VARCHAR(45) DEFAULT NULL,
#   start_date DATE DEFAULT NULL,
#   end_date DATE DEFAULT NULL,
#   description VARCHAR(200) DEFAULT NULL,
#   PRIMARY KEY (task_id)
# )ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 插入单行基本数据
# INSERT INTO tasks (subject, start_date, end_date, description)
# VALUES ('Learn MySQL INSERT', '2017-07-21', '2017-07-22', 'Start learning..');
# 插入多行数据
# INSERT INTO tasks(subject,start_date,end_date,description)
# VALUES ('任务-1','2017-01-01','2017-01-02','Description 1'),
#   ('任务-2','2017-01-01','2017-01-02','Description 2'),
#   ('任务-3','2017-01-01','2017-01-02','Description 3');

# 通过复制tasks表的结构，创建一个名为tasks_bak的新表
# CREATE TABLE tasks_bak LIKE tasks;
# INSERT INTO tasks_bak SELECT * FROM tasks;

####################### update 语句 ################################
# 使用UPDATE语句将Mary的电子邮件更新为新的电子邮件：mary.new@yiibai.com
# UPDATE employees SET  email = 'mary.new@yiibai.com' WHERE  employeeNumber = 1056;
# MySQL UPDATE多列
# UPDATE employees SET  lastname = 'Hill',  email = 'mary.hill@yiibai.com' WHERE  employeeNumber = 1056;

# 使用SELECT语句的MySQL UPDATE示例，使用SELECT语句查询来自其他表的数据来提供给SET子句的值。
# UPDATE customers SET salesRepEmployeeNumber = (
#     SELECT employeeNumber  FROM  employees WHERE  jobtitle = 'Sales Rep'LIMIT 1)
# WHERE salesRepEmployeeNumber IS NULL;

# 可以使用DELETE语句与LEFT JOIN子句来清理客户数据。 以下声明删除未下订单的客户：、
# DELETE customers
# FROM customers
#   LEFT JOIN
#   orders ON customers.customerNumber = orders.customerNumber
# WHERE
#   orderNumber IS NULL;

