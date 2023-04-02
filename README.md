# 综合办公室业务平台

## 基本说明

本仓库为简化办公室工作，为办公室成员提高办公业务共同协作能力。

本仓库使用 **MIT LICENSE** ，需要的可以自行下载使用。

**本仓库会提供数据库的全部表，但是所有数据表内的数据都会清空。**

## 仓库分支说明

本仓库包含多个使用 git 命令 `git checkout orphan [分支名称]` 创建的孤立分支（该分支下提交的内容与其他分支提交的内容无关，不继承任何其他的提交）。

本仓库包含的分支如下：

```shell
# 前端代码分支
- webresources

# 后端代码分支与数据库
- javaresources
```

## 开发环境说明

### 前端

-   vuejs3.x
-   element-plus

### 后端

-   jdk8u202
-   maven 3.6.1
-   spring boot 2.7.10

## 如何下载本仓库

```shell
# 克隆仓库
git clone https://github.com/TianLongMengXue/zhbgs-service-platform.git

# 进入仓库目录,当前目录下显示的为 main 分支的内容
cd zhbgs-service-platform

# 切换仓库分支,如 webresources 分支,切换分支之后,仓库目录下显示的就是 webresources 分支的内容
git checkout webresources

# 切换仓库分支,如 javaresources 分支,切换分支之后,仓库目录下显示的就是 javaresources 分支的内容
git checkout javaresources
```

