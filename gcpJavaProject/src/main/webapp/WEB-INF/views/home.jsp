<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#select").click(function(){
		$.ajax({
			type: "post",
			async:false,
			url:"/select",
			dataType:'text',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(null),
			success: function(data){ 
				alert("조회완료");
			},
			error: function (xhr, ajaxOptions, thrownError) { 
				alert(xhr.status+' Error');
			}  
		});
	});
	$("#insert").click(function(){
		$.ajax({
			type: "post",
			async:false,
			url:"/insert",
			dataType:'text',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(null),
			success: function(data){ 
				alert("추가완료");
			},
			error: function (xhr, ajaxOptions, thrownError) { 
				alert(xhr.status+' Error');
			}  
		});
	});
	$("#update").click(function(){
		$.ajax({
			type: "post",
			async:false,
			url:"/update",
			dataType:'text',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(null),
			success: function(data){ 
				alert("수정완료");
			},
			error: function (xhr, ajaxOptions, thrownError) { 
				alert(xhr.status+' Error');
			}  
		});
	});
	$("#delete").click(function(){
		$.ajax({
			type: "post",
			async:false,
			url:"/delete",
			dataType:'text',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(null),
			success: function(data){ 
				alert("삭제완료");
			},
			error: function (xhr, ajaxOptions, thrownError) { 
				alert(xhr.status+' Error');
			}  
		});
	});
	
	
	
	
	
	
});
</script>


<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  JAVA CRUD TEST </P>


<input type="button" id="select" value="조회"/> 
<input type="button" id="insert" value="추가"/> 
<input type="button" id="update" value="수정"/> 
<input type="button" id="delete" value="삭제"/> 

	




</body>
</html>
