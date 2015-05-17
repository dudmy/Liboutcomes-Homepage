<%@ page contentType="text/html;charset=UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="memMgr" class="sample.board.model.MemberService"/>
<jsp:useBean id="regBean" class="sample.board.model.Register" />
<jsp:setProperty name="regBean" property="*" />
<% 
	boolean flag = memMgr.memberInsert(regBean);	
%>
<html>
<head>
<title>회원가입 확인</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<br><br>
<center>
<%
	if(flag){
		out.println("<b>회원가입을 축하 드립니다.</b><p>");
		out.println("<a href=Login.jsp>로그인</a>");
	}else{
		out.println("<b>다시 입력하여 주십시오.</b><p>");
		out.println("<a href=Register.jsp>다시 가입</a>");
	}
%>
</center>
</body>
</html>