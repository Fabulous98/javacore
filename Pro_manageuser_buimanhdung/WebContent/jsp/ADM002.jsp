<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/user.js"></script>
<title>ユーザ管理</title>
</head>
<body>

	<!-- Begin vung header -->
	<%@ include file="/jsp/header.jsp"%>
	<!-- End vung header -->

	<form action="listUser.do" method="get" name="mainform">
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="fullName" value="${fn:escapeXml(fullName)}" size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" autofocus="autofocus" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left">
							<select name="groupId"
								style="max-width: 79px">
									<option value="0">全て</option>
									<c:forEach items="${listGroup}" var="listMstGroup">
										<c:set var="group" value="${listMstGroup.groupName }" />
										<c:choose>
											<c:when test="${listMstGroup.groupId==groupId}">
												<option value="${listMstGroup.groupId}" selected="selected">${fn:escapeXml(listMstGroup.groupName)}</option>
											</c:when>
											<c:otherwise>
												<option value="${listMstGroup.groupId}">${fn:escapeXml(listMstGroup.groupName)}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select></td>
							
								<td></td>
						</tr>
						
						<tr>
							<c:set var = "defaultCheck" scope = "session" value = "0"/>
							<td class="lbl_left">生年月日:&emsp;&emsp; &ensp;
							<input type="checkbox" id="byYear" name="byYear" value= "1"
							<c:if test="${byYear != defaultCheck}">
							checked
							</c:if>>
							
							</td>
								<td align="left">
								<select name="yearBirth">
										<c:forEach items="${listYear}" var="birthYear">
											<c:choose>
											<c:when test="${birthYear==yearBirth}">
												<option value="${birthYear}" selected="selected">${fn:escapeXml(birthYear)}</option>
											</c:when>
											<c:otherwise>
												<option value="${birthYear}">${fn:escapeXml(birthYear)}</option>
											</c:otherwise>
										</c:choose>
										</c:forEach>
								</select><input type="checkbox" id="byMonth" name="byMonth" value="1"
								<c:if test="${byMonth != defaultCheck}">
								checked
								</c:if>><select name="monthBirth">
										<c:forEach items="${listMonth}" var="birthMonth">
											<c:choose>
											<c:when test="${birthMonth==monthBirth}">
												<option value="${birthMonth}" selected="selected">${fn:escapeXml(birthMonth)}</option>
											</c:when>
											<c:otherwise>
												<option value="${birthMonth}">${fn:escapeXml(birthMonth)}</option>
											</c:otherwise>
										</c:choose>
										</c:forEach>
								</select><input type="checkbox" id="byDate" name="byDate" value="1"
								<c:if test="${byDate != defaultCheck}">
								checked
								</c:if>>
							<span class="checkmark"></span><select name="dateBirth">
										<c:forEach items="${listDate}" var="birthDate">
											<c:choose>
											<c:when test="${birthDate==dateBirth}">
												<option value="${birthDate}" selected="selected">${fn:escapeXml(birthDate)}</option>
											</c:when>
											<c:otherwise>
												<option value="${birthDate}">${fn:escapeXml(birthDate)}</option>
											</c:otherwise>
										</c:choose>
										</c:forEach>
								</select></td>
							<td align="left">
							&emsp;<input class="btn" type="submit" value="検索" />
								
								<input class="btn" type="button" value="新規追加 " onclick="javascript:location.href='addUserInput.do'"></td>
						</tr>
						
						<tr>
							<input name="action" value="search" style="display: none" />
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- Begin vung dieu kien tim kiem -->
	</form>

	<!-- Begin vung hien thi danh sach user -->
	<c:url value="listUser.do" var="url_sort">
		<c:param name="action" value="sort"></c:param>
		<c:param name="fullName" value="${fn:escapeXml(fullName)}"></c:param>
		<c:param name="groupId" value="${fn:escapeXml(groupId)}"></c:param>
		<c:param name="yearBirth" value="${fn:escapeXml(yearBirth)}"></c:param>
		<c:param name="monthBirth" value="${fn:escapeXml(monthBirth)}"></c:param>
		<c:param name="dateBirth" value="${fn:escapeXml(dateBirth)}"></c:param>
		<c:param name="byYear" value="${fn:escapeXml(byYear)}"></c:param>
		<c:param name="byMonth" value="${fn:escapeXml(byMonth)}"></c:param>
		<c:param name="byDate" value="${fn:escapeXml(byDate)}"></c:param>
		<c:param name="currentPage" value="${currentPage}"></c:param>
	</c:url>


	<!-- Begin vung hien thi tiltle của bảng -->
	<c:if test="${not empty listTblUserInfor}">
	<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
		width="80%">

		<tr class="tr2">
			<th align="center" width="20px">ID</th>
			<th align="left">氏名<a
				href="${url_sort}&sort_type=full_name&sortValue=${sortByFullName == 'ASC' ? 'DESC' : 'ASC'}">
					${sortByFullName == 'ASC' ?'▲▽'  : '△▼' } </a>
			</th>
			<th align="left">生年月日</th>
			<th align="left">グループ</th>
			<th align="left">メールアドレス</th>
			<th align="left" width="70px">電話番号</th>
			<th align="left">日本語能力 <a
				href="${url_sort}&sort_type=code_level&sortValue=${sortByCodeLevel == 'DESC' ? 'ASC' : 'DESC'}">
					${sortByCodeLevel == 'DESC' ?'▲▽'  : '△▼' } </a>
			</th>
			<th align="left">失効日 <a
				href="${url_sort}&sort_type=end_date&sortValue=${sortByEndDate == 'DESC' ? 'ASC' : 'DESC'}">
					${sortByEndDate == 'DESC' ?'△▼'  : '▲▽'} </a>
			</th>
			<th align="left">点数</th>
		</tr>
		<!-- End vung hien thi tiltle của bảng -->
		<!-- Begin vung hien thi list user -->
		<c:forEach items="${listTblUserInfor}" var="listUser">
			<tr style="white-space: nowrap;">
				<td align="right"><a
					href="viewUser.do?userId=${listUser.userId}">${listUser.userId}</a></td>
				<c:if test="${fn:length(fn:escapeXml(listUser.fullName)) > 25 }">
					<td align="left">${fn:substring(fn:escapeXml(listUser.fullName), 0, 22)}...</td>
				</c:if>
				<c:if test="${fn:length(fn:escapeXml(listUser.fullName)) <= 25 }">
					<td align="left">${fn:escapeXml(listUser.fullName)}</td>
				</c:if>
				<td align="center">${fn:replace(listUser.birthday,"-","/")}</td>
				<c:if test="${fn:length(fn:escapeXml(listUser.groupName)) > 25 }">
					<td align="left">${fn:substring(fn:escapeXml(listUser.groupName), 0, 22)}...</td>
				</c:if>
				<c:if test="${fn:length(fn:escapeXml(listUser.groupName)) <= 25 }">
					<td align="left">${fn:escapeXml(listUser.groupName)}</td>
				</c:if>
				<c:if test="${fn:length(fn:escapeXml(listUser.email)) > 25 }">
					<td align="left">${fn:substring(fn:escapeXml(listUser.email), 0, 22)}...</td>
				</c:if>
				<c:if test="${fn:length(fn:escapeXml(listUser.email)) <= 25 }">
					<td align="left">${fn:escapeXml(listUser.email)}</td>
				</c:if>

				<td>${fn:escapeXml(listUser.tel)}</td>
				<c:if test="${fn:length(fn:escapeXml(listUser.nameLevel)) > 25 }">
					<td align="left">${fn:substring(fn:escapeXml(listUser.nameLevel), 0, 22)}...</td>
				</c:if>
				<c:if test="${fn:length(fn:escapeXml(listUser.nameLevel)) <= 25 }">
					<td align="left">${fn:escapeXml(listUser.nameLevel)}</td>
				</c:if>

				<td align="center">${fn:replace(listUser.endDate,"-","/")}</td>
				<td align="right"><c:if test="${listUser.total != 0}">
					${listUser.total}
				</c:if></td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<!-- End vung hien thi danh sach user -->
	<!--  vung thong thong bao loi -->
	<c:if test="${empty listTblUserInfor}">
		${message}
	</c:if>
	<!-- end vung thong thong bao loi -->
	<!-- Begin vung paging -->
	<table>
		<tr>
			<c:url value="listUser.do" var="url_paging">
				<c:param name="action" value="paging"></c:param>
				<c:param name="fullName" value="${fullName}"></c:param>
				<c:param name="groupId" value="${groupId}"></c:param>
				<c:param name="yearBirth" value="${yearBirth}"></c:param>
				<c:param name="monthBirth" value="${monthBirth}"></c:param>
				<c:param name="dateBirth" value="${dateBirth}"></c:param>
				<c:param name="byYear" value="${byYear}"></c:param>
				<c:param name="byMonth" value="${byMonth}"></c:param>
				<c:param name="byDate" value="${byDate}"></c:param>
				<c:param name="sort_type" value="${sort_type}"></c:param>
				<c:param name="sortByFullName" value="${sortByFullName}"></c:param>
				<c:param name="sortByCodeLevel" value="${sortByCodeLevel}"></c:param>
				<c:param name="sortByEndDate" value="${sortByEndDate}"></c:param>
			</c:url>
			<td class="lbl_paging"><c:if test="${listPaging != null}">
					<c:if test="${totalPage > 1}">
						<c:if test="${currentPage > limitPage}">
							<a
								href="${url_paging}&action=paging&currentPage=${listPaging.get(0) - limitPage}">&lt&lt</a>
						</c:if>
						<c:forEach items="${listPaging}" var="paging">
							<c:choose>
								<c:when test="${paging == currentPage}">
									${paging}
								</c:when>
								<c:otherwise>
									<a href="${url_paging}&action=paging&currentPage=${paging}">${paging}</a>&nbsp;
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${listPaging.get(listPaging.size() - 1) < totalPage}">
							<a
								href="${url_paging}&action=paging&currentPage=${listPaging.get(listPaging.size() - 1) + 1}">
								&gt&gt </a>&nbsp;
					   	</c:if>

					</c:if>
				</c:if></td>
		</tr>
	</table>
	<!-- End vung paging -->

	<!-- Begin vung footer -->
	<%@ include file="/jsp/footer.jsp"%>
	<!-- End vung footer -->


</body>

</html>