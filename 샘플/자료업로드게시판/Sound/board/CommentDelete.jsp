<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="SoundComment.*"%>
    
<jsp:useBean id="myComment" class="SoundComment.CommentBean" />
<jsp:useBean id="sc" class="SoundComment.CommentMgr" />

<%
   request.setCharacterEncoding("UTF-8");
   String mem_id = (String)session.getAttribute("idKey");
   String mem_name = (String)session.getAttribute("name");

   int bn = Integer.parseInt(request.getParameter("num"));
   int nowPage = Integer.parseInt(request.getParameter("page"));
   int cnum = Integer.parseInt(request.getParameter("cnum"));
   
   
   CommentBean tempBoard = sc.getComment(cnum);
   String writer = tempBoard.getName();
   
   request.setAttribute("num", bn); 
   request.setAttribute("page", nowPage);
   RequestDispatcher dispatcher = request.getRequestDispatcher("Read.jsp"); 
   
   if(mem_name.equals(writer) || mem_name.equals("Admin")) {
        boolean ch = sc.deleteComment(cnum);
        dispatcher.include( request, response );
        //response.sendRedirect("List.jsp");
   
   } else {
        out.println("<script>alert('권한이 없습니다.');</script>");
        dispatcher.include( request, response );
   }
   
%>
