/**
 * Metodos generales para manejar en el bunsys
 */
var bunsys = {
		toUppercase: function(event, input){
			if(!input || !input.value) return;
			var key = event.charCode || event.keyCode;
			if(key == 32){
				return true;
			}
			if(!$(input).hasClass("bun-uppercase")){
				$(input).addClass("bun-uppercase")
			}
			$(input).val($(input).val().toUpperCase());
		}
};