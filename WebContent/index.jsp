<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.2.0.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div style="width: 500px; margin: 0 auto;">

		<div style="margin-left: 100px;">
			<span>编号</span><span style="margin-left: 150px;">名字</span>
		</div>



		<div style="margin-top: 30px; margin-left: 100px;" id="taglink">
			<span>1</span><span style='margin-left: 150px;'>1</span>
		</div>


		<div style="margin-top: 20px;">

			<input id="pages" name="pages" value="1" type="hidden"> <a
				style="margin-right: 15px; margin-left: 15px; cursor: pointer;"
				onclick="pages();">第一页</a> <input id="PerPage" name="PerPage"
				value="1" type="hidden"> <a
				style="margin-right: 15px; margin-left: 30px; cursor: pointer;"
				onclick="PerPage();">上一页</a> <span>当前第<span id="span-ye">1</span>页/</span></span><span>共<span id="div-totalPage">1</span>页/共<span
				id="div-total">1</span>条
			</span> <input id="NextPage" name="NextPage" value="2" type="hidden">
			<a style="margin-right: 15px; margin-left: 10px; cursor: pointer;"
				onclick="NextPage();">下一页</a> <input name="totalPage"
				value="1" id="input-totalPage" type="hidden">
			<a style="margin-right: 15px; cursor: pointer;"
				onclick="totalPage();">最后一页</a> 
			<select id="select1" onblur="newselect()">
				<option>5</option>
				<option>10</option>
				<option>15</option>
			</select>

		</div>

	</div>

	<script type="text/javascript">
	
  var objs= document.getElementById("select1").value;
   $(function(){
	   $.ajax({
		 url:"stadent.action?op=staAll",
		 type:"POST",
		 data:{pages:1,pageSize:objs},
		 success :function(data){
			 if(data.code==1){
				 $("#taglink").html('');
				 $.each(data.obj.list,function(i,entity){
					
		 $("#taglink").
		 append("<span>"+entity.id+"</span><span style='margin-left: 150px;'>"+entity.name+"</span><br/>");
		 var Perpage=data.obj.pages<=1?1:data.obj.pages-1;
		 $("#PerPage").val(Perpage);
		 var nextPage=data.obj.pages<=data.obj.totalPage?data.obj.pages+1:data.obj.pages;
		 $("#NextPage").val(nextPage);
		 $("#input-totalPage").val(data.obj.totalPage);//共多少页
		 $("#div-totalPage").html(data.obj.totalPage);//共多少页
		 $("#div-total").html(data.obj.total);//一页多少条
		 $("#span-ye").html(data.obj.pages);//当前第几页
					});
			 } 
		 }
	   });
   });
    
	 function pages(){
		 var val= document.getElementById("pages").value;
		 var objs= document.getElementById("select1").value;
		 trunPage(val,objs);
	 }
	 
	 function PerPage(){
		 var val= document.getElementById("PerPage").value;
		 var objs= document.getElementById("select1").value;
		 trunPage(val,objs);
	 }
	 
	 function NextPage(){
		 var val= document.getElementById("NextPage").value;
		 var objs= document.getElementById("select1").value;
		 trunPage(val,objs);
	 }
	 
	 function totalPage(){
		 var val= document.getElementById("input-totalPage").value;
		 var objs= document.getElementById("select1").value;
		 trunPage(val,objs);
	 }
	 
   function trunPage(values,obj){
	   $.ajax({
		   url:"stadent.action?op=staPage",
			 type:"POST",
			 data:{pages:values,pageSize:obj},
			 success:function(data){
				 if(data.code==1){
					  $("#taglink").html('');
					 $.each(data.obj.list,function(i,entity){
						 $("#taglink").append("<span>"+entity.id+"</span><span style='margin-left: 150px;'>"+entity.name+"</span><br/>");
						 var Perpage=data.obj.pages<=1?1:data.obj.pages-1;
						 $("#PerPage").val(Perpage);
						 var nextPage=data.obj.pages<=data.obj.totalPage?data.obj.pages+1:data.obj.pages;
						 $("#NextPage").val(nextPage);
						 $("#input-totalPage").val(data.obj.totalPage);
						 $("#div-totalPage").html(data.obj.totalPage);
						 $("#input-totalPage").html(data.obj.totalPage);
						 $("#span-ye").html(data.obj.pages);
						}); 
				 }
			 }
	   });
   }
   
   function newselect(){
	   var val= 1;
	   var objs= document.getElementById("select1").value;
	   trunPage(val,objs);
   }
</script>

</body>
</html>