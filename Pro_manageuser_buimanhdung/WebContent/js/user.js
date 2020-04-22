/**
 * Hàm hiển thị thông báo xác nhận xóa thông tin một User
 * 
 */

function deleteConfirm(action, userId, msg) {
	 var alert = window.confirm(msg);
	  if (alert == true) {
		  var deleteForm = document.createElement("form");
			deleteForm.setAttribute("method", "post");
			deleteForm.setAttribute("action", action);
			deleteForm.setAttribute('id', "delete");
			// Thêm thẻ input type là submit
			var input = document.createElement("input");
			input.setAttribute("type", "hidden");
			input.setAttribute("name", "userId");
			input.setAttribute("value", userId);
			deleteForm.appendChild(input);
			// thêm deleteForm vào HTML
			document.getElementsByTagName('body')[0].appendChild(deleteForm);
			// Thực hiện submit khi click OK
			document.getElementById("delete").submit();
	  } 
}

/**
 * Hàm ẩn hiện trình độ tiếng Nhật khi click [日本語能力]
 * 
 */

function showHideClick() {
	var displayValue = document.getElementsByClassName("ShowHide");
	for (var i = 0; i < displayValue.length; i++) {
		if (displayValue[i].style.display == 'table-row') {
			displayValue[i].style.display = 'none';
		} else {
			displayValue[i].style.display = 'table-row';
		}
	}
}

