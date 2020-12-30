$('.input input').on('focus', function() {
    	$(this).parent().addClass('focus');
})

$('.input input').on('blur', function() {
  	if ($(this).val() === '')
  		$(this).parent().removeClass('focus');
})