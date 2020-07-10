# Open Test Class
[![Build Status](https://github.com/alaahong/open_test_class/workflows/CI/badge.svg)](https://github.com/alaahong/open_test_class)
[![Code Quality Score](https://www.code-inspector.com/project/4050/score/svg)](https://www.code-inspector.com/project/4050/score/svg)

------

## 初始化数据说明：
用户管理初始化4个用户账号，分别为admin、teacher、stu1和stu2；密码都是123。
<p>admin具有用户操作权限,可以对用户管理菜单进行操作；</p>
<p>teacher具有教师权限，可对课程管理菜单进行操作；</p>
<p>stu1和stu2具有学生权限，可对订阅管理菜单进行操作。</p>
 课程管理初始化了语文、数学、英语三门课程，均为有效状态。

### 校验方式
项目根目录执行以下命令后，运行即时生成的项目
> mvn clean package
 
