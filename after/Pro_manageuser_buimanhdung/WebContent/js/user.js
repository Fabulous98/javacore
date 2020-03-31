/**
 * Hàm ẩn hiện trình độ tiếng Nhật
 * 
 * @returns ẩn hiện vùng trình độ tiếng Nhật khi click [日本語能力]
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
function deleteConfirm(action, userId, msg) {
	if (window.confirm(msg)) {
		// tạo form
		var deleteForm = document.createElement("form");
		deleteForm.setAttribute("method", "post");
		deleteForm.setAttribute("action", action);
		deleteForm.setAttribute('id', "idForm");
		// Thêm thẻ input type là submit
		var input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "userId");
		input.setAttribute("value", userId);
		deleteForm.appendChild(input);
		// thêm deleteForm vào HTML
		document.getElementsByTagName('body')[0].appendChild(deleteForm);
		// Thực hiện submit
		document.getElementById("idForm").submit();
	}
}
