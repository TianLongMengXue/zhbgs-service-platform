# 综合办公室业务平台

## 基本说明

本仓库为使用 spring boot 和 Vue.js 搭建的前后端分离项目，开发目的时为了简化办公室内部分业务、提高办公效率。本项目主要包含了基本的办公事务和党务工作。

本仓库使用 **MIT LICENSE** ，需要的可以自行下载使用。

**本仓库会提供数据库的全部表，但是所有数据表内的涉及人员个人信息的部分会被删除。**

## 项目基本功能说明

```shell
- 未定义接口统一响应

- 登录功能
  - 登录校验
  - 授予人员登录权限
  - 撤销人员登录权限
  - 修改登录密码
  - 修改人员头像
  - 重置登录密码

- 人员管理
  - 添加人员信息
  - 修改人员信息
  - 获取人员信息
  - 删除人员信息
```

## 开发环境说明

### 前端

- vue.js 3.2.13
- element-plus 2.3.2
- font-awesome 4.7.0
- axios 1.3.4
- vue-router 4.1.6
- vuex 4.1.0

### 后端

-   JDK8U202
-   maven 3.6.1
-   spring boot 2.7.10
-   springfox-swagger2 2.9.2
-   springfox-swagger-ui 2.9.2
-   mybatis-plus 3.4.2
-   mysql-connector-java 5.1.47
-   druid 1.1.20
-   jjwt 0.9.1
-   pinyin4j 2.5.0
-   commons-codec 1.15
-   tika-core 2.4.1

## 如何下载本仓库

```shell
# 克隆仓库
git clone https://github.com/TianLongMengXue/zhbgs-service-platform.git

# 进入仓库目录,当前目录下显示的为 main 分支的内容
cd zhbgs-service-platform

# 进入前端目录
cd fontend

# 为前端部分安装依赖
npm install

# 启动前端
npm run serve

# 进入后端目录
cd ../backend

# 为后端部分安装依赖,需要已配置好 maven 的环境变量
# 默认所有依赖会下载到 ${user.home}/.m2/repository
# 若是已在 maven 的安装目录下的 config/setting.xml 文件中配置好 maven 的依赖安装目录,那么所有依赖都会自动下载到这个指定的目录下
mvn dependency:copy-dependencies

# 将后端部分打成 jar 包
mvn jar:jar

# 运行后端部分,需要已配置好 java 环境变量
java -jar zhbgs-service-platform-1.0.0.jar
```

## 仓库目录说明

```shell
# 前端代码目录
- fontend

# 后端代码目录
- backend
```

## 数据库说明

### 1、人员信息表

（1）`t_member`在职人员信息表

| 字段名称         | 字段说明                                                     |
| ---------------- | ------------------------------------------------------------ |
| id               | 人员唯一标识（雪花算法）                                     |
| name             | 姓名（人员姓名）                                             |
| department       | 所属部门（由t_department部门信息表决定）                     |
| phone            | 手机号                                                       |
| landline         | 固定电话                                                     |
| political_status | 政治面貌（由t_political_status表决定）                       |
| party_branch     | 所属党支部（由t_party_branch表决定），没有所属党支部的该项默认为none |
| time             | 信息添加时间                                                 |

（2）`t_unestablished` 编外人员信息表

| 字段名称         | 字段说明                                                     |
| ---------------- | ------------------------------------------------------------ |
| id               | 人员唯一标识（雪花算法）                                     |
| name             | 姓名（人员姓名）                                             |
| department       | 所属部门（由t_department部门信息表决定）                     |
| phone            | 手机号                                                       |
| political_status | 政治面貌（由t_political_status表决定）                       |
| party_branch     | 所属党支部（由t_party_branch表决定），没有所属党支部的该项默认为none |
| time             | 信息添加时间                                                 |

（3）`t_retiring`退休人员信息表
| 字段名称         | 字段说明                                                     |
| ---------------- | ------------------------------------------------------------ |
| id               | 人员唯一标识（雪花算法）                                     |
| name             | 姓名（人员姓名）                                             |
| phone            | 手机号                                                       |
| political_status | 政治面貌（由t_political_status表决定）                       |
| party_branch     | 所属党支部（由t_party_branch表决定），没有所属党支部的该项默认为none |
| time             | 信息添加时间                                                 |

（4）`t_internship`见习/实习人员信息表
| 字段名称         | 字段说明                                                     |
| ---------------- | ------------------------------------------------------------ |
| id               | 人员唯一标识（雪花算法）                                     |
| name             | 姓名（人员姓名）                                             |
| department       | 所属部门（由t_department部门信息表决定）                     |
| phone            | 手机号                                                       |
| time             | 信息添加时间                                                 |

### 2、`t_login`登录表

| 字段名称 | 字段说明                               |
| -------- | -------------------------------------- |
| id       | 人员唯一标识（雪花算法）               |
| username | 人员登录账户名称（用户姓名的全拼）     |
| password | 人员登录账户密码（默认的密码为123456） |
| avatar   | 人员头像（默认的人员头像为login.ico）  |

**使用姓名全拼的方式作为登录用户名称使用,存在姓名可重复问题,此问题待日后更改为其他具有个人唯一性的（如：职工编号/手机号）等来修复。**

### 3、菜单表项信息表

（1）`t_root_menu` 父级菜单表项

| 字段名称 | 字段说明                                         |
| -------- | ------------------------------------------------ |
| id       | 菜单表项唯一标识（UUID）                         |
| name     | 菜单表项标识（前端路由标识）                     |
| info     | 菜单表项名称（前端菜单页面显示菜单表项名称）     |
| url      | 菜单表项路径（前端路由路径）                     |
| icon     | 菜单表项图标                                     |
| order    | 菜单表项顺序（前端菜单页面中菜单表项显示的顺序） |

（2）`t_child_menu` 子级菜单表项

| 字段名称  | 字段说明                                         |
| --------- | ------------------------------------------------ |
| id        | 菜单表项唯一标识（UUID）                         |
| name      | 菜单表项标识（前端路由标识）                     |
| info      | 菜单表项名称（前端菜单页面显示菜单表项名称）     |
| url       | 菜单表项路径（前端路由路径）                     |
| icon      | 菜单表项图标                                     |
| order     | 菜单表项顺序（前端菜单页面中菜单表项显示的顺序） |
| parent_id | 菜单表项的父级菜单                               |

（3）`t_user_menu` 用户菜单关联表

| 字段名称 | 字段说明                 |
| -------- | ------------------------ |
| id       | 行标识（自动递增）       |
| user_id  | 人员唯一标识（雪花算法） |
| menu_id  | 菜单表项唯一标识（UUID） |

### 4、接口信息表

（1）`t_api` api信息表

| 字段名称 | 字段说明                                      |
| -------- | --------------------------------------------- |
| id       | api唯一标识（UUID）                           |
| api      | 后端接口信息                                  |
| is_admin | 是否为系统管理员专属权限（1为true，0为false） |
| info     | api接口名称（前端页面显示的内容）             |
| name     | api接口标识                                   |

（2）`t_user_api` 用户api关联表

| 字段名称 | 字段说明                 |
| -------- | ------------------------ |
| id       | 行标识（自动递增）       |
| user_id  | 人员唯一标识（雪花算法） |
| api_id   | api唯一标识（UUID）      |

（3）`t_menu_api`菜单表项和api接口关联表

| 字段名称 | 字段说明                     |
| -------- | ---------------------------- |
| id       | 行标识（自动递增）           |
| menu_id  | 子级菜单表项唯一标识（UUID） |
| api_id   | api唯一标识（UUID）          |

### 5、`t_department` 部门名称表

| 字段名称 | 字段说明             |
| -------- | -------------------- |
| id       | 部门唯一标识（uuid） |
| name     | 部门名称             |

### 6、`t_post` 岗位表

| 字段名称 | 字段说明                                                 |
| -------- | -------------------------------------------------------- |
| id       | 岗位唯一标识（uuid）                                     |
| name     | 岗位名称（在职人员、编外人员、离/退休人员、实习/见习生） |

### 7、`t_political_status` 政治面貌表

| 字段名称 | 字段说明                                                     |
| -------- | ------------------------------------------------------------ |
| id       | 字段唯一标识（uuid）                                         |
| name     | 政治面貌（中共党员、中共预备党员、共青团员、民革党员、民盟盟员、民建会员、民进会员、农工党党员、致公党党员、九三学社社员、台盟盟员、无党派人士、群众） |

### 8、`t_party_branch` 党支部部门名称表

| 字段名称 | 字段说明             |
| -------- | -------------------- |
| id       | 字段唯一标识（uuid） |
| name     | 党支部名称           |

### 9、`t_fix_party_fee` 固定党费表

| 字段名称 | 字段说明                 |
| -------- | ------------------------ |
| id       | 人员唯一标识（雪花算法） |
| fee      | 费用                     |

### 10、`t_login_time`登录时间记录表

| 字段名称 | 字段说明                 |
| -------- | ------------------------ |
| id       | 行唯一标识（自动增长）   |
| user_id  | 人员唯一标识（雪花算法） |
| time     | 人员登录时间             |