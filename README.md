# U报名第二代后端开发文档

## 功能

## 数据库

### user_message(用户信息表)

* user_id(用户微信登录的openid插入数据库自动生成)

* openid

* user_name

* user_tel

* userQQ_num

* user_sex

* user_college

* user_profession
### department(部门详细信息表)

* department_id(部门id，插入其他信息时自动生成)

* department_name(部门名称)

* organization_id

* organization_name(所属组织名称)

* department_account(部门PC端登录账号)

* department_pwd(部门PC端登录密码)

* interview_rounds(面试轮次)

* param1_name

* param2_name

* param3_name

* param4_name

* param5_name

### department_member(部门成员表)

* department_id(部门id)

* department_name

* user_id(用户id)

* authority_id(权限id)

### resume(报名信息表)

* user_id

* user_name

* organization_id

* organization_name(组织名称)

* user_sex

* userStu_num(学号)

* user_college(学院)

* user_profession(专业)

* user_tel

* userQQ_num

* user_hobby

* user_introduction

### interview_status(面试状态)

* user_id

* organization_id

* organization_name(组织名称)

* first_choice(第一志愿选择)

* first_status(第一志愿状态)

* second_choice(第二志愿选择)

* second_status(第二志愿状态)

* retest_choice(二面选择部门)

* retest_status(二面面试状态)

### interview_score

* user_id

* user_name

* organization_id

* departmentName

* param1_score

* param2_score

* param3_score

* param4_score

* param5_score

* detail(备注)

* overview(综合评价)

* user_name(评价人姓名)

### interview_data

* department_id

* department_name 部门名称

* man_counts 性别为男的报名人数

* woman_counts 性别为女的报名人数

* cross_counts 跨部人数

* apply_counts 总报名人数

* interviewCounts 已经面试人数
