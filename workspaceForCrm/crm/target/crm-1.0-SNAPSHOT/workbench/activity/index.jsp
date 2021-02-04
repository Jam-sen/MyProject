<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" +
request.getServerName() + ":" + request.getServerPort() +
request.getContextPath() + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>"/>
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>


<script type="text/javascript">

	$(function () {
		//页面加载完毕后触发更新市场活动列表方法
		pageList(1, 5);

		//添加
		$("#addBtn").click(function () {
			//操作模态窗口的方式： 取得需要操作的模态窗口的jquery对象，调用modal方法，为该方法传递参数show：打开模态窗口   hide：隐藏模态窗口
			//引入时间控件
			$(".time").datetimepicker({
				minView: "month",
				language: 'zh-CN',
				format: 'yyyy-mm-dd',
				autoclose: true,
				todayBtn: true,
				pickerPosition: "bottom-left"
			});

			$.ajax({
				url: "activity/getUserList.do",
				type: "get",
				async: false,
				dataType: "json",
				success: function (data) {
					//data: 用户数组json
					var html = "";
					$.each(data, function (index, element) {
						html += "<option value='" + element.id + "'>" + element.name + "</option>"
					});
					//将拼接的option标签加入select标签中
					$("#create-owner").html(html)

					//设置select标签的默认值为当前用户
					$("#create-owner").val("${sessionScope.user.id}")
				}
			})
			//打开添加操作的模态窗口
			$("#createActivityModal").modal("show")
		})

		//保存
		$("#saveBtn").click(function () {
			$.ajax({
				url: "activity/save.do",
				data: {
					"owner": $.trim($("#create-owner").val()),
					"name": $.trim($("#create-name").val()),
					"startDate": $.trim($("#create-startDate").val()),
					"endDate": $.trim($("#create-endDate").val()),
					"cost": $.trim($("#create-cost").val()),
					"description": $.trim($("#create-description").val())
				},
				type: "post",
				dataType: "json",
				success: function (data) {
					//data: true/false
					if (data.success) {
						$("#activityAddForm")[0].reset();
						//添加成功后
						//刷新市场活动信息列表；关闭添加操作的模态窗口
						$("#createActivityModal").modal("hide");

						/*
						操作后停留在当前页
						$("#activityPage").bs_pagination('getOption', 'currentPage')

						操作后维持已经设置好的每页展现的记录数
						$("#activityPage").bs_pagination('getOption', 'rowsPerPage')

						这两个参数不需要进行任何修改，直接使用即可
						 */
						pageList(1, $("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
					} else {
						alert("添加市场活动失败")
					}
				}
			})
		})

		//为查询按钮绑定事件，触发pageList方法
		$("#searchBtn").click(function () {
			//点击查询按钮的时候，我们应该将搜索框中的信息保存起来，保存在隐藏域中
			$("#hidden-name").val($.trim($("#search-name").val()))
			$("#hidden-owner").val($.trim($("#search-owner").val()))
			$("#hidden-startDate").val($.trim($("#search-startTime").val()))
			$("#hidden-endDate").val($.trim($("#search-endTime").val()))
			pageList(1, 2);
		});
		//为全选的复选框绑定事件，触发全选操作
		$("#qx").click(function () {
			$("input[name=xz]").prop("checked", this.checked);
		});
		//为动态生成的子复选框绑定事件，更改全选复选框的状态
		$("#dataTbody").on("click", $("input[name=xz]"), function () {
			$("#qx").prop("checked", $("input[name = xz]").length == $("input[name=xz]:checked").length);
		});

		//为删除按钮绑定事件，执行市场活动删除操作
		$("#deleteBtn").click(function () {
			var $xz = $("input[name=xz]:checked");
			if ($xz.length == 0) {
				alert("请选择需要删除的记录");
			} else {
				//使用confirm()方法用于显示一个带有指定消息和确认及取消按钮的对话框。
				if (confirm("确定要删除吗")) {
					//同一个key下有多个value的情况，只能使用传统"crm/xxx？key=value&key=value"的形式传递参数，不能使用json，因为json的key不能重复
					//拼接参数
					var param = "";
					$.each($xz, function (index, element) {
						param += "id=";
						param += $(element).val() + "&"
					});
					param = param.substr(0, param.length - 1);
					$.ajax({
						url: "activity/delete.do",
						data: param,
						type: "post",
						dataType: "json",
						success: function (data) {
							if (data.flag) {
								//删除成功之后
								pageList(1, $("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
							} else {
								alert("删除失败")
							}
						}
					})
				}
			}
		})

		//为修改按钮绑定事件，打开修改操作的模态窗口
		$("#editBtn").click(function () {

			$(".time").datetimepicker({
				minView: "month",
				language: 'zh-CN',
				format: 'yyyy-mm-dd',
				autoclose: true,
				todayBtn: true,
				pickerPosition: "bottom-left"
			});

			var $xz = $("input[name=xz]:checked")
			if ($xz.length != 1) {
				alert("请勾选一条记录进行修改")
			} else {
				var id = $xz.val();
				$.ajax({
					url: "activity/getUserListAndActivity.do",
					data: {
						"id": id,
					},
					type: "get",
					dataType: "json",
					success: function (data) {
						//data: 用户列表、市场活动单条
						var html = "";
						$.each(data.userList, function (index, element) {
							html += '<option value="' + element.id + '">' + element.name + '</option>'
						})
						$("#edit-owner").html(html);

						//处理单条activity
						$("#edit-id").val(data.activity.id);
						$("#edit-name").val(data.activity.name);
						$("#edit-owner").val(data.activity.owner);
						$("#edit-startDate").val(data.activity.startDate);
						$("#edit-endDate").val(data.activity.endDate);
						$("#edit-cost").val(data.activity.cost);
						$("#edit-description").val(data.activity.description)

						//打开修改模态窗口
						$("#editActivityModal").modal("show");
					}
				})
			}
		})

		//为更新按钮绑定事件
		$("#updateBtn").click(function () {
			if (confirm("您确定要更新吗")){
				$.ajax({
					url: "activity/update.do",
					data: {
						"id": $.trim($("#edit-id").val()),
						"owner":$.trim($("#edit-owner").val()),
						"name":$.trim($("#edit-name").val()),
						"startDate":$.trim($("#edit-startDate").val()),
						"endDate":$.trim($("#edit-endDate").val()),
						"cost":$.trim($("#edit-cost").val()),
						"description":$.trim($("#edit-description").val()),
					},
					type: "post",
					dataType: "json",
					success:function (data) {
						if (data.flag) {
							alert("更新成功");
						} else {
							alert("更新失败");
						}
						$("#editActivityModal").modal("hide");
						pageList($("#activityPage").bs_pagination('getOption', 'currentPage'), $("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
					}
				})
			}
		});
	});

	function pageList(pageNum, pageSize) {

		//将全选框的🪝干掉
		$("#qx").prop("checked", false);

		$("#search-name").val($.trim($("#hidden-name").val()))
		$("#search-owner").val($.trim($("#hidden-owner").val()))
		$("#search-startDate").val($.trim($("#hidden-startTime").val()))
		$("#search-endDate").val($.trim($("#hidden-endTime").val()))

		$.ajax({
			url: "activity/pageList.do",
			data: {
				"pageNum":pageNum,
				"pageSize":pageSize,
				"name":$.trim($("#search-name").val()),
				"owner":$.trim($("#search-owner").val()),
				"startTime":$.trim($("#search-startTime").val()),
				"endTime":$.trim($("#search-endTime").val())
			},
			type: "get",
			dataType: "json",
			success: function (data) {
				//data:  {"total":xxx,"dataList":[{市场活动1},{2},{3}]}
				var html = "";
				$.each(data.dataList,function (index,element) {
					html += '<tr class="active">';
					html += '<td><input type="checkbox" name="xz" value='+element.id+' /></td>';
					html += '<td><a style="text-decoration: none; cursor: pointer;" ' +
							'onclick="window.location.href=\'activity/detail.do?id='+element.id+'\';">'+element.name+'</a></td>';
					html += '<td>'+element.owner+'</td>';
					html += '<td>'+element.startDate+'</td>';
					html += '<td>'+element.endDate+'</td>';
					html += '</tr>';
				})
				$("#dataTbody").html(html);

				//计算总页数
				var totalPages = data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1
				//数据处理完毕后，结合分页查询，对前段展现分页信息
				$("#activityPage").bs_pagination({
					currentPage: pageNum, // 页码
					rowsPerPage: pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: totalPages, // 总页数
					totalRows: data.total, // 总记录条数
					visiblePageLinks: 3, // 显示几个卡片
					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,
					onChangePage : function(event, data){
						pageList(data.currentPage , data.rowsPerPage);
					}
				});
			}
		})
	}

</script>
</head>
<body>
	<input type="hidden" id="hidden-name"/>
	<input type="hidden" id="hidden-owner"/>
	<input type="hidden" id="hidden-startDate"/>
	<input type="hidden" id="hidden-endDate"/>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="activityAddForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">

								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<%--data-dismiss:关闭模态窗口--%>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">

						<input id="edit-id" type="hidden"/>

						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">

								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-name" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startDate">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endDate">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<%--文本域textarea：
									1。一定要以标签对的形式来呈现，正常状态下标签对要紧紧的挨着
									2。textarea虽然是以标签对的形式来呈现，但是它也是属于表单元素范畴
									3。所有对textarea的取值和赋值操作，应该统一使用val()方法，而不是使用html
								--%>
								<textarea class="form-control" rows="3" id="edit-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
			<%--条件查询文本框--%>
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="search-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="search-startTime" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="search-endTime">
				    </div>
				  </div>
				  
				  <button type="button" id="searchBtn" class="btn btn-default">查询</button>
				  
				</form>
			</div>

			<%--活动创建、修改、删除按钮--%>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>

			<%--活动列表--%>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="dataTbody">

					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage"></div>
			</div>
			
		</div>
		
	</div>
</body>
</html>