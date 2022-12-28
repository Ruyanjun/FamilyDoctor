
/**
 * 加入意向
 */
function buy(did){
	$.get("doctors_buy.action", {did:did}, function(data){
		if(data=="ok")
		{
			layer.msg("已加入签约意向!", {time:800}, function(){
               location.reload();
            });
		}
		else if(data=="fail")
		{
            layer.msg("医生已被签约,请选择其他医生!", {time:800}, function(){

            });
		}

	});
}

function lessen(did){
    $.post("doctors_lessen.action", {did:did}, function(data){
        if(data=="ok"){
            layer.msg("操作成功!", {time:800}, function(){
                location.reload();
            });
        }

    });
}
/**
 * 意向删除
 */
function deletes(did){
    $.post("doctors_delete.action", {did:did}, function(data){
        if(data=="ok"){
            layer.msg("删除成功!", {time:800}, function(){
                location.reload();
            });
        }
    });
}