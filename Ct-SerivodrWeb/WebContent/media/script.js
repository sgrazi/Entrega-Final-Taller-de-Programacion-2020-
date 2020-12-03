$(".custom-file-input").on("change", function() {
	var fileName = $(this).val().split("\\").pop() || "Examinar...";
	$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});