/**
 * Metodos generales para manejar en el bunsys
 */
var bunsys = {
		toUppercase: function(event, input){
			if(!input || !input.value) return;
			if(!$(input).hasClass("bun-uppercase")){
				$(input).addClass("bun-uppercase")
			}
			$(input).val($(input).val().toUpperCase());
		}
};