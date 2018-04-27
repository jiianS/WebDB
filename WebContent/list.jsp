<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리스트</title>
</head>
<body>
   <%
      List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)request.getAttribute("list");
	  String boardNo = request.getAttribute("boardNo").toString();
 	  String html = "";
      String html2 = "";
      if(list == null) {
         System.out.println("값이 없습니다.");
      }else {
         System.out.println("값이 있습니다.");
         
         for(int i = 0; i < list.size(); i++) {
	    		html += "<a href='List?boardNo=" + list.get(i).get("boardNo") + "'>" ;
            html += list.get(i).get("boardNo") + ") ";
            html += list.get(i).get("title"); 
            html += "</a><br>";
         }
         
         if(boardNo != null){
        	 System.out.println(boardNo);
        	 for(int i=0; i<list.size(); i++){
        		 if(boardNo.equals(list.get(i).get("boardNo").toString())){
        			 html2 = list.get(i).get("content").toString();        					 
        		 }
        	 }
         }
      }
   %>
   <p><a href="insert.jsp"> 글 작성 </a></p>
   
   <%= html%>
   <%= html2%>
</body>
</html>