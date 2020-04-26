<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<div>
		<%@ include file="/jsp/header.jsp"%>
	</div>
	<!-- End vung header -->

	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:formatDate var="nowYear" value="${now}" pattern="yyyy" />
	<fmt:formatDate var="nowMonth" value="${now}" pattern="MM" />
	<fmt:formatDate var="nowDate" value="${now}" pattern="dd" />
	<!-- Begin vung input-->
	<c:set var="checkMove" value="false" scope="session"/>
	<c:choose>
		<c:when test="${userInfor.userId > 0}">
			<c:url value="editUserInput.do?action=validate" var="url"></c:url>
		</c:when>
		<c:otherwise>
			<c:url value="addUserInput.do?action=validate" var="url">
			</c:url>
		</c:otherwise>
	</c:choose>
	<form action=" ${url}" method="post" name="inputform">
		
		<input type="hidden" name="userId" value="${userInfor.userId}" />
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>

			<c:forEach items="${listError}" var="eror">
				<tr class="errMsg" colspan="2">
					<td style="padding-left: 100px"><c:out value="${eror}" /></td>
				</tr>
			</c:forEach>

			<tr>
				<td class="errMsg">
					<div style="padding-left: 120px">&nbsp;</div>
				</td>
			</tr>

			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<c:choose>
								<c:when test="${userInfor.userId > 0}">
									<tr>
										<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
										<td align="left"><input class="txBox" type="text"
											style="border: none" name="loginName"
											value="${fn:escapeXml(userInfor.loginName)}" size="15"
											onfocus="this.style.borderColor='#0066ff';"
											onblur="this.style.borderColor='#aaaaaa';"
											readonly="readonly"  /></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
										<td align="left"><input class="txBox" type="text"
											name="loginName" value="${userInfor.loginName}" size="15"
											onfocus="this.style.borderColor='#0066ff';"
											onblur="this.style.borderColor='#aaaaaa';" autofocus="autofocus" /></td>
									</tr>
								</c:otherwise>
							</c:choose>

							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="groupId"
									style="max-width: 124px">
										<option value="0">選択してください</option>
										<c:forEach items="${listMstGroup}" var="group">
											<c:choose>
												<c:when test="${group.groupId == userInfor.groupId}">
													<option value="${group.groupId}" selected="selected">${fn:escapeXml(group.groupName)}</option>
												</c:when>
												<c:otherwise>
													<option value="${group.groupId}">${fn:escapeXml(group.groupName)}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select> </td>
							</tr>

							<c:choose>
								<c:when test="${userInfor.userId > 0}">
									<tr>
										
										<td class="lbl_left"><font color="red">*</font> 氏名:</td>
										<td align="left"><input class="txBox" type="text"
											name="fullName" value="${fn:escapeXml(userInfor.fullName)}"
											size="30" onfocus="this.style.borderColor='#0066ff';"
											onblur="this.style.borderColor='#aaaaaa';" autofocus="autofocus" /></td>
									
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td class="lbl_left"><font color="red">*</font> 氏名:</td>
										<td align="left"><input class="txBox" type="text"
											name="fullName" value="${fn:escapeXml(userInfor.fullName)}"
											size="30" onfocus="this.style.borderColor='#0066ff';"
											onblur="this.style.borderColor='#aaaaaa';" /></td>
									</tr>
								</c:otherwise>
							</c:choose>

							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullNameKana"
									value="${fn:escapeXml(userInfor.fullNameKana)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><c:set var="birthday"
										value="${fn:split(userInfor.birthday, '/')}">
									</c:set> <select name="yearBirth">
										<c:forEach items="${listYear}" var="birthYear"
											end="${listYear.size() - 2}">
											<c:if test="${userInfor.birthday == null}">
												<option value="${birthYear}"
													<c:if test="${birthYear == nowYear}"> selected="selected"</c:if>>
													<c:out value="${birthYear}" />
												</option>
											</c:if>
											<c:if test="${userInfor.birthday != null}">
												<option value="${birthYear}"
													<c:if test="${birthYear == birthday[0]}"> selected="selected"</c:if>>
													<c:out value="${birthYear}" />
												</option>
											</c:if>
										</c:forEach>
								</select>年 <select name="monthBirth">
										<c:forEach items="${listMonth}" var="birthMonth">
											<c:if test="${userInfor.birthday == null}">
												<option value="${birthMonth}"
													<c:if test="${birthMonth == nowMonth}"> selected</c:if>>
													<c:out value="${birthMonth}" />
												</option>
											</c:if>
											<c:if test="${userInfor.birthday != null}">
												<option value="${birthMonth}"
													<c:if test="${birthMonth == birthday[1]}"> selected="selected"</c:if>>
													<c:out value="${birthMonth}" />
												</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="dateBirth">
										<c:forEach items="${listDate}" var="birthDate">
											<c:if test="${userInfor.birthday == null}">
												<option value="${birthDate}"
													<c:if test="${birthDate ==nowDate }"> selected</c:if>>
													<c:out value="${birthDate}" />
												</option>
											</c:if>
											<c:if test="${userInfor.birthday != null}">
												<option value="${birthDate}"
													<c:if test="${birthDate == birthday[2]}"> selected="selected"</c:if>>
													<c:out value="${birthDate}" />
												</option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">性別:</td>
								<td>
									<input type="radio" id="male" name="gender" value="Nam"
									<c:if test="${userInfor.gender == 'Nam'}"> checked</c:if>>
  									<label for="male">Nam</label>
  									<input type="radio" id="female" name="gender" value="Nữ"
  									<c:if test="${userInfor.gender == 'Nữ'}"> checked</c:if>>
  									<label for="female">Nữ</label>
  									<input type="radio" id="other" name="gender" value="Khác"
  									<c:if test="${userInfor.gender == 'Khác'}"> checked</c:if>>
  									<label for="other">Khác</label>
								</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="${fn:escapeXml(userInfor.email) }"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>

							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="${fn:escapeXml(userInfor.tel)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<c:if test="${userInfor.userId == 0}">
								<tr>
									<td class="lbl_left"><font color="red">*</font> パスワード:</td>
									<td align="left"><input class="txBox" type="password"
										name="pass" value="" size="30"
										onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" /></td>
								</tr>
								<tr>
									<td class="lbl_left">パスワード（確認）:</td>
									<td align="left"><input class="txBox" type="password"
										name="confirmPass" value="" size="30"
										onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" /></td>
								</tr>
							</c:if>
							<tr>
								<th align="left" colspan="2"><a href="#"
									onclick="showHideClick()">日本語能力</a></th>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="codeLevel"
									style="max-width: 124px">
										<option value="0">選択してください</option>
										<c:forEach items="${listMstJapan}" var="japan">
											<c:choose>
												<c:when test="${japan.codeLevel==userInfor.codeLevel}">
													<option value="${japan.codeLevel}" selected="selected">${fn:escapeXml(japan.nameLevel)}</option>
												</c:when>
												<c:otherwise>
													<option value="${japan.codeLevel}">${fn:escapeXml(japan.nameLevel)}</option>
												</c:otherwise>
											</c:choose>n>
								</c:forEach>
								</select></td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><c:set var="startDay"
										value="${fn:split(userInfor.startDay, '/')}">
									</c:set> <select name="yearStart">
										<c:forEach items="${listYear}" var="yearStartDay"
											end="${listYear.size() - 2}">
											<c:if test="${userInfor.startDay == null}">
												<option value="${yearStartDay}"
													<c:if test="${yearStartDay == nowYear}"> selected="selected"</c:if>>
													<c:out value="${yearStartDay}" />
												</option>
											</c:if>
											<c:if test="${userInfor.startDay != null}">
												<option value="${yearStartDay}"
													<c:if test="${yearStartDay == startDay[0]}"> selected="selected"</c:if>>
													<c:out value="${yearStartDay}" />
												</option>
											</c:if>
										</c:forEach>
								</select>年 <select name="monthStart">
										<c:forEach items="${listMonth}" var="startMonth">
											<c:if test="${userInfor.startDay == null}">
												<option value="${startMonth}"
													<c:if test="${startMonth == nowMonth}"> selected="selected" </c:if>>
													<c:out value="${startMonth}" />
												</option>
											</c:if>
											<c:if test="${userInfor.startDay != null}">
												<option value="${startMonth}"
													<c:if test="${startMonth == startDay[1]}"> selected="selected"</c:if>>
													<c:out value="${startMonth}" />
												</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="dateStart">
										<c:forEach items="${listDate}" var="startDate">
											<c:if test="${userInfor.startDay == null}">
												<option value="${startDate}"
													<c:if test="${startDate == nowDate}"> selected="selected" </c:if>>
													<c:out value="${startDate}" />
												</option>
											</c:if>
											<c:if test="${userInfor.startDay != null}">
												<option value="${startDate}"
													<c:if test="${startDate == startDay[2]}"> selected="selected"</c:if>>
													<c:out value="${startDate}" />
												</option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">失効日:</td>
								<td align="left"><c:set var="endDate"
										value="${fn:split(userInfor.endDate, '/')}">
									</c:set> <select name="yearEnd">
										<c:forEach items="${listYear}" var="year">
											<c:if test="${userInfor.endDate == null}">
												<option value="${year}"
													<c:if test="${year == (nowYear + 1)}"> selected="selected"</c:if>>
													<c:out value="${year}" />
												</option>
											</c:if>
											<c:if test="${userInfor.endDate != null}">
												<option value="${year}"
													<c:if test="${year == endDate[0]}"> selected="selected"</c:if>>
													<c:out value="${year}" />
												</option>
											</c:if>
										</c:forEach>
								</select>年 <select name="monthEnd">
										<c:forEach items="${listMonth}" var="month">
											<c:if test="${userInfor.endDate == null}">
												<option value="${month}"
													<c:if test="${month == nowMonth}"> selected="selected"</c:if>>
													<c:out value="${month}" />
												</option>
											</c:if>
											<c:if test="${userInfor.endDate != null}">
												<option value="${month}"
													<c:if test="${month == endDate[1]}"> selected="selected"</c:if>>
													<c:out value="${month}" />
												</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="dateEnd">
										<c:forEach items="${listDate}" var="day">
											<c:if test="${userInfor.endDate == null}">
												<option value="${day}"
													<c:if test="${day == nowDate}"> selected="selected"</c:if>>
													<c:out value="${day}" />
												</option>
											</c:if>
											<c:if test="${userInfor.endDate != null}">
												<option value="${day}"
													<c:if test="${day == endDate[2]}"> selected="selected"</c:if>>
													<c:out value="${day}" />
												</option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr class="ShowHide" style="display: none;">
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="total" value="${fn:escapeXml(userInfor.total)}" size="5"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
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
					<td><input class="btn" type="submit" value="確認" onclick="<c:set var ='checkMove' value='true' scope='session'/>" /></td>
					<td><c:choose>
							<c:when test="${userInfor.userId > 0}">
								<a href="viewUser.do?userId=${userInfor.userId}"> <input
									class="btn" type="button" value="戻る" />
								</a>
							</c:when>
							<c:otherwise>
							
								<input class="btn" type="button" onclick="javascript:location.href='listUser.do?action=back'" value="戻る" />
								
							</c:otherwise>
						</c:choose></td>
				</tr>
			</table>

		</div>
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<%@ include file="/jsp/footer.jsp"%>
	<!-- End vung footer -->
</body>

</html>