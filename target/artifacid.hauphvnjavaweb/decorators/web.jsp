<%--
  Created by IntelliJ IDEA.
  User: cmd
  Date: 20/05/2019
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><dec:title default="Trang Chu"></dec:title></title>
    <link
            href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css'/>"
            rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value='/template/web/css/style.css'/>"
          rel="stylesheet" type="text/css" media="all">

</head>
<body>
<!-- header -->
<%@include file="/common/web/header.jsp"%>
<!-- header -->
<div class="container">
    <dec:body />
</div>
<!-- footer -->
<%@include file="/common/web/footer.jsp"%>
<!-- footer -->

</body>
</html>