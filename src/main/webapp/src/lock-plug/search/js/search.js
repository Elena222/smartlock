/**
 * Created by CC on 2017/1/4.
 */
$(function() {
    swapValues = [];
    $(".swap_value").each(function(i){
        swapValues[i] = $(this).val();
        $(this).focus(function(){
            if ($(this).val() == swapValues[i]) {
                $(this).val("");
            }
        }).blur(function(){
            if ($.trim($(this).val()) == "") {
                $(this).val(swapValues[i]);
            }
        });
        });
});