<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.itheima.dao.IUserDao" %>
<%@ page import="com.itheima.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>
<%
    // 1. 读取配置文件
    InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
    // 2. 创建SqlSessionFactoryBuilder工厂对象
    // 先创建SqlSessionFactoryBuilder对象
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    // 然后使用SqlSessionFactoryBuilder对象的build方法，创建SqlSessionFactoryBuilder工厂对象
    SqlSessionFactory factory = builder.build(inputStream);
    // 3. 使用工厂对象创建SqlSession对象。SqlSession对象可以用来操作数据库
    SqlSession sqlSession = factory.openSession();
    // 4. 使用SqlSession对象创建IUserDao的代理对象
    IUserDao userDao = sqlSession.getMapper(IUserDao.class);
    // 5. 使用代理对象执行方法
    List<User> users = userDao.findAll();
    for (User user : users) {
        System.out.println(user);
    }
    // 6. 释放资源
    sqlSession.close();
    inputStream.close();
%>
</body>
</html>
