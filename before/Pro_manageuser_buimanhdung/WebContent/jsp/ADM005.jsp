<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
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

	<!-- Begin vung input-->
	<form action="editUserInput.do" method="get"
		name="inputform">
		<input type="hidden" name="userId" value="${userInfor.userId}" />
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">情報確認</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td class= "textTable" align="left" >${fn:escapeXml(userInfor.loginName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td class= "textTable" align="left" >${fn:escapeXml(userInfor.groupName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td class= "textTable" align="left">${fn:escapeXml(userInfor.fullName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td class= "textTable" align="left">${fn:escapeXml(userInfor.fullNameKana)}</td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left">${fn:replace(userInfor.birthday,"-","/")}</td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td class= "textTable" align="left">${fn:escapeXml(userInfor.email)}</td>
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
								<td class= "textTable" align="left">${fn:escapeXml(userInfor.nameLevel)}</td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">資格交付日:</td>
								<td align="left">${fn:replace(userInfor.startDay,"-","/")}</td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">失効日:</td>
								<td align="left">${fn:replace(userInfor.endDate,"-","/")}</td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">点数:</td>
								<td align="left">${fn:escapeXml(userInfor.total)}</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 100px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="編集" /></td>
					<td><input class="btn" type="button" value="削除"
						onclick="javascript:deleteConfirm('deleteUser.do','${userInfor.userId}','${msg004}')" />
					</td>
					<td><a href="listUser.do?action=back"> <input class="btn"
							type="button" value="戻る" />
					</a></td>
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