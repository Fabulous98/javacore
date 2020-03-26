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
<script type="text/javascript" src="./js/user.js"></script>
<title>ユーザ管理</title>
</head>
<body>

	<!-- Begin vung header -->
	<%@ include file="/jsp/header.jsp"%>
	<!-- End vung header -->
	<c:choose>
		<c:when test="${userInfor.userId > 0}">
			<c:url value="editUserConfirm.do" var="url"></c:url>
		</c:when>
		<c:otherwise>
			<c:url value="addUserConfirm.do" var="url">
			</c:url>
		</c:otherwise>
	</c:choose>
	<!-- Begin vung input-->
	<form action="${url}" method="post" name="inputform">
		<input type="hidden" name="key" value="${key}" />
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">
						情報確認<br> 入力された情報をＯＫボタンクリックでＤＢへ保存してください 
					</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<!-- Start fix bug ID 57 – ThoaDT 2019/12/25 -->
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td class="textTable" align="left">${fn:escapeXml(userInfor.loginName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td class="textTable" align="left">${fn:escapeXml(userInfor.groupName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td class="textTable" align="left">${fn:escapeXml(userInfor.fullName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td class="textTable" align="left">${fn:escapeXml(userInfor.fullNameKana)}</td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left">${fn:escapeXml(userInfor.birthday)}</td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td class="textTable" align="left">${fn:escapeXml(userInfor.email)}</td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left">${fn:escapeXml(userInfor.tel)}</td>
							</tr>
							<tr>
								<th colspan="2"><a href="#" onclick="showHideClick()">日本語能力</a></th>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">資格:</td>
								<td class="textTable" align="left">${fn:escapeXml(userInfor.nameLevel)}</td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">資格交付日:</td>
								<td align="left">${fn:escapeXml(userInfor.startDay)}</td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">失効日:</td>
								<td align="left">${fn:escapeXml(userInfor.endDate)}</td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">点数:</td>
								<td align="left">${fn:escapeXml(userInfor.total)}</td>
							</tr>
							<!-- End fix bug ID 57 – ThoaDT 2019/12/25 -->
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="OK" /></td>
					<td><c:choose>
							<c:when test="${userInfor.userId > 0}">
								<a
									href="editUser.do?action=back&key=${key}&userId=${userInfor.userId}">
									<input class="btn" type="button" value="戻る" />
								</a>
							</c:when>
							<c:otherwise>
								<a href="addUserInput.do?action=back&key=${key}"> <input
									class="btn" type="button" value="戻る" />
								</a>
							</c:otherwise>
						</c:choose></td>


				</tr>
			</table>
			<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<%@ include file="/jsp/footer.jsp"%>
	<!-- End vung footer -->

</body>

</html>