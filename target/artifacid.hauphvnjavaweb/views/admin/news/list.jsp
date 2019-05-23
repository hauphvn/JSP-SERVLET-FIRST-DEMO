<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>DANH SACH BAI VIET</title>
</head>

<body>
	<div class="main-content">
	<form action="<c:url value='/admin-news'/>" id="formSubmit" method="get">
		<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id=" ">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a
							href='<c:url value="/admin-home"/>'>Trang chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
											   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
											   title='Thêm bài viết' href='<c:url value="/admin-news?type=edit" />'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
											</a>
											<button id="btnDelete" type="button"
													class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table">
											<thead>
											<tr>

												<th>Title</th>
												<th>Thumbnail</th>
												<th>Thao tac</th>

											</tr>
											</thead>
											<tbody>
											<c:forEach items="${model.listResult}" var="item"
													   varStatus="loop">
												<tr>

													<td>${item.title}</td>
													<td>${item.thumbnail}</td>
													<td>
															<c:url var="editURL" value="/admin-news">
																<c:param name="type" value="edit"></c:param>
																<c:param name="id" value="${item.id}"></c:param>
															</c:url>
														<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
														   title="Cập nhật bài viết" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														</a>
													</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>

										<input type="hidden" value="" id="page" name="page"/>
										<input type="hidden" value="" id="maxItem" name="maxItem"/>
										<input type="hidden" value="" id="sortName" name="sortName"/>
										<input type="hidden" value="" id="sortBy" name="sortBy"/>
										<input type="hidden" value="" id="type" name="type"/>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			
		</div>
		</form>
	</div>

	<!-- /.main-content -->
	<script>
	var totalPages = ${model.totalPage};
	var currentPage = ${model.page};
	var limit = 2;
	var maxItem = ${model.maxItem};
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: totalPages,
				visiblePages: limit,
				startPage: currentPage,
				onPageClick: function (event, page) {
					if (currentPage != page) {
						$('#maxItem').val(maxItem);
						$('#page').val(page);
						$('#sortName').val('title');
						$('#sortBy').val('desc');
						$('#type').val('list');
						$('#formSubmit').submit();
					}
				}
			});
		});
	</script>
</body>

</html>