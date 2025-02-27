# 旅行社管理系统 - 前后端分离项目



## 项目简介
本项目旨在开发一套全面的旅行社管理系统，采用**前后端分离架构**，包含小程序前台、后台管理系统及后端服务。系统通过角色权限划分，实现旅行社业务的高效管理，涵盖线路维护、订单管理、数据统计等功能，助力旅行社提升运营效率。

---

## 技术栈
### 前端
- **小程序前台**：Vue.js + Taro/Uni-app  
- **后台管理系统**：Vue3 + Vue Router + Vuex + Element Plus  
- **通信**：Axios + RESTful API  
- **构建工具**：Vite

### 后端
- **核心框架**：Spring Boot 2.x  
- **数据库**：MySQL  
- **ORM**：MyBatis-Plus  
- **工具**：Maven + Lombok + Knife4j（接口文档）  
- **安全**：JWT + Spring Security  

### 其他
- **跨域解决方案**：CORS配置  
- **代码生成**：MyBatisX  
- **部署**：Nginx（可选）  

---

## 功能模块
### 角色权限划分
1. **管理员**  
   - 管理所有操作员信息  
   - 全局线路/景点/酒店维护  
2. **总店操作员**  
   - 维护旅行线路、分店及供应商信息  
   - 查看全部分店业绩 
3. **分店操作员**  
   - 管理本店线路、员工及参团人员  
   - 处理分店订单与业绩统计  
4. **普通用户（小程序端）**  
   - 微信登录、浏览/搜索线路  
   - 在线下单、查看个人订单  

### 核心功能
- **后台管理**  
  - 多角色权限控制  
  - 数据增删改查（CRUD）与条件搜索  
  - 业绩可视化统计（图表展示）  

- **小程序端**  
  - 响应式布局适配多设备  
  - 线路推荐、分类浏览、订单管理  

---

## 项目结构
### 前端

frontend/
├── admin/          # 后台管理系统
├── mini-program/  # 小程序源码
└── public/         # 静态资源

### 后端

backend/
├── src/
│   ├── main/
│   │   ├── java/com/travel/  
│   │   │   ├── config/      # 全局配置
│   │   │   ├── controller/  # API接口
│   │   │   ├── service/     # 业务逻辑
│   │   │   └── mapper/      # 数据持久层
│   │   └── resources/       # 配置文件
└── pom.xml                  # Maven依赖

---

## 快速开始
### 环境要求
- JDK 11+  
- Node.js 16+  
- MySQL 8.0+

### 后端部署
1. 导入SQL脚本初始化数据库  
2. 修改`application.yml`中的数据库配置  
3. 启动服务：
```bash
mvn spring-boot:run
```

### 前端部署
1. 安装依赖：
```bash
npm install
```
2. 运行开发环境：
```bash
npm run dev
```

---

## 团队成员
| 姓名   | 职责               |
|--------|--------------------|
| 杨兴龙 | 前端-管理员模块    |
| 王旭   | 前端-分店/总店模块 |
| 李帅   | 前端-小程序        |
| 黄梓桢 | 后端核心功能       |
| 邸子豪 | 后端接口实现       |



主要页面介绍：

登录页面：分店和总店分别登录

![img](D:/Typora_Img/wps1-1740631408639-3.jpg)![img](D:/Typora_Img/wps2-1740631408639-4.jpg) 

 

业绩汇总：总店店可查看所有店铺的月收入和个店铺的收入对比。

![img](D:/Typora_Img/wps3-1740631408639-1.jpg) 

分店只能查看分店的功能。

![img](D:/Typora_Img/wps4-1740631408639-2.jpg) 

 

***\*分店页面：分店的查看，添加，搜索，修改，删除\****

![img](D:/Typora_Img/wps5-1740631408639-5.jpg) 

![img](D:/Typora_Img/wps6-1740631408639-8.jpg) 

![img](D:/Typora_Img/wps7-1740631408639-6.jpg) 

![img](D:/Typora_Img/wps8-1740631408639-7.jpg) 

![img](D:/Typora_Img/wps9-1740631408640-9.jpg) 

 

景点酒店管理页面：对景点和酒店进行查看，添加，修改和删除功能。

![img](D:/Typora_Img/wps10-1740631408640-12.jpg) 

![img](D:/Typora_Img/wps11-1740631408640-10.jpg)![img](D:/Typora_Img/wps12-1740631408640-11.jpg)![img](D:/Typora_Img/wps13-1740631408640-13.jpg) 

 

 

总店路线管理：对总店线路进行添加，修改，，删除等操作。

![img](D:/Typora_Img/wps14-1740631408640-15.jpg)![img](D:/Typora_Img/wps15-1740631408640-14.jpg)![img](D:/Typora_Img/wps16-1740631408640-16.jpg)![img](D:/Typora_Img/wps17-1740631408640-17.jpg) 

 

 

 

订单管理页面：查看订单信息，可进行订单编号搜索订单信息。

![img](D:/Typora_Img/wps18-1740631408640-18.jpg) 

![img](D:/Typora_Img/wps19-1740631408640-20.jpg) 

 

 

分店人员管理：对分店人员信息进行管理，可添加修改，搜索，修改，删除。

![img](D:/Typora_Img/wps20-1740631408640-19.jpg)![img](D:/Typora_Img/wps21-1740631408640-21.jpg) 

![img](D:/Typora_Img/wps22-1740631408640-22.jpg)![img](D:/Typora_Img/wps23-1740631408640-23.jpg)![img](D:/Typora_Img/wps24-1740631408640-24.jpg) 

分店订单：

![img](D:/Typora_Img/wps25-1740631408640-25.jpg) 

![img](D:/Typora_Img/wps26-1740631408640-26.jpg) 

分店线路：

![img](D:/Typora_Img/wps27-1740631408640-27.jpg) 

***\*管理员模块：\****

在进行管理员模块的编写中运用到了vue3，axios，vite构建工具，element-plus组件和前端css等相关知识。

任务完成情况

主要页面介绍：

登录页面：管理员账号登录

![img](D:/Typora_Img/wps28-1740631408640-28.jpg) 

业绩汇总：管理员可查看所有店铺的月收入和个店铺的收入对比。

![img](D:/Typora_Img/wps29-1740631408640-29.jpg) 

线路管理页面：可以制订总的路线共店铺选择。一条线路包含线路名称，线路图片，线路详情，途径的景点及需要花费的金额。管理员可以进行添加，修改，，删除。

![img](D:/Typora_Img/wps30-1740631408640-30.jpg) 

![img](D:/Typora_Img/wps31-1740631408640-31.jpg)![img](D:/Typora_Img/wps32-1740631408640-32.jpg) 

![img](D:/Typora_Img/wps33-1740631408640-33.jpg) 

景点酒店管理页面：对景点和酒店进行添加，修改和删除功能。

![img](D:/Typora_Img/wps34-1740631408640-34.jpg) 

![img](D:/Typora_Img/wps35-1740631408640-35.jpg)![img](D:/Typora_Img/wps36-1740631408640-36.jpg) 

总店，分店操作员信息管理：对操作员的个人信息进行添加，修改，，删除，可进行姓名搜索等操作。

![img](D:/Typora_Img/wps37-1740631408640-37.jpg)![img](D:/Typora_Img/wps38-1740631408640-38.jpg) 

![img](D:/Typora_Img/wps39-1740631408640-39.jpg)![img](D:/Typora_Img/wps40-1740631408640-40.jpg) 

参团人员信息管理：对已下单的用户以同一路线为一个团进行管理。可根据订单编号进行搜索。

![img](D:/Typora_Img/wps41-1740631408640-41.jpg) 

![img](D:/Typora_Img/wps42-1740631408640-42.jpg) 

订单管理页面：查看订单信息，可进行订单编号搜索订单信息。

![img](D:/Typora_Img/wps43-1740631408640-43.jpg)![img](D:/Typora_Img/wps44-1740631408640-44.jpg) 

用户信息管理：对所有用户信息进行管理，可添加修改，搜索。

![img](D:/Typora_Img/wps45-1740631408640-45.jpg)![img](D:/Typora_Img/wps46-1740631408640-46.jpg)![img](D:/Typora_Img/wps47-1740631408640-47.jpg)![img](D:/Typora_Img/wps48-1740631408640-48.jpg) 

***\*小程序页面：\****

任务完成情况

主要页面介绍：

登录页面：用户账号登录,和注册

![img](D:/Typora_Img/wps49-1740631408640-50.jpg) 

 

 

首页，包含轮播图和一些推荐景点

![img](D:/Typora_Img/wps50-1740631408640-49.jpg) 

景区页面可以查看详细信息并下单

![img](D:/Typora_Img/wps51-1740631408640-52.jpg) 

![img](D:/Typora_Img/wps52-1740631408640-51.jpg) 

 



---

## 待完善功能
- 微信支付集成
- 图片上传/下载模块
- 更细粒度的权限控制

---

## 接口文档
访问 `http://localhost:8080/doc.html` 查看Knife4j接口文档

任务完成情况

完成情况：

前后端分离联调可以实现，跨域问题解决，数据的增删改查都可以实现，图片上传下载功能没有完成，小程序端完成基本样式。