/**
 * 表格和弹出层相关的js特效控制
 * author:liyuan
 */

//添加遮罩层函数
function msk() {

    var mskHeight = $(document).height();

    var mskWidth = $(document).width();

    $(window).resize(function(){

        mskHeight = $(document).height();

        mskWidth = $(document).width();
    });

    $('<div class=\"msk\"></div>').appendTo('body');

    $('.msk').css({
        'height':mskHeight,
        'width':mskWidth,
        'opacity':'0.4',
        'background':'black',
        'position':'absolute',
        'top':0,
        'left':0,
        'z-index':2
    });
}

//点击弹出遮罩层函数
function showPopPanel(parentName,childName,popName){

    $(parentName).on("click",childName,function(){

        $("input[type='checkbox']").each(function(){
            if(this.checked){
                $(msk());
                $(popName).css({
                    'z-index':1000
                }).slideDown('slow');
            }

        })

    });
}
showPopPanel(".right-content",".m-revise","#edit");
showPopPanel(".right-content",".m-stop","#delete");

//点击退出函数
$(function(){
    $(".cancel").on("click",this,function() {

        $(".m-popPanel").slideUp('500');

        setTimeout(function(){
            $(".msk").remove();
        },500);

    })
});

//点击添加
$(function(){
    $(".right-content").on("click",".m-add",function(){

        $(msk());

        $("#create input,#create textarea,#edit select").val("");

        $("#create").css({
            'z-index':1000
        }).slideDown('slow');
        var userType = $("#l-y-type").val();

        if(userType=="楼主"||userType=="楼管"){

            $("#l-management").css("display","block");

            $("#y-management").css("display","none");

            $("#staff-distribution").css("display","none");

            $("#l-management select").attr("name","management");

            $("#l-management input").attr("name","roomIds");

            $("#y-management select").attr("name","");

            $("#staff-distribution input[type=text]").attr("name","");
        }

        if(userType=="院主"){

            $("#l-management").css("display","none");

            $("#y-management").css("display","block");

            $("#staff-distribution").css("display","block");

            $("#l-management select").attr("name","");

            $("#l-management input").attr("name","");

            $("#y-management select").attr("name","management");

            $("#staff-distribution input[type=text]").attr("name","roomIds");
        }

        $("#l-y-type").on("change",this,function() {

            var userType1 = $(this).val();

            if(userType1=="楼主"||userType1=="楼管"){

                $("#l-management").css("display","block");

                $("#y-management").css("display","none");

                $("#staff-distribution").css("display","none");

                $("#l-management select").attr("name","management");

                $("#l-management input").attr("name","roomIds");

                $("#y-management select").attr("name","");

                $("#staff-distribution input[type=text]").attr("name","");
            }

            if(userType1=="院主"){

                $("#l-management").css("display","none");

                $("#y-management").css("display","block");

                $("#staff-distribution").css("display","block");

                $("#l-management select").attr("name","");

                $("#l-management input").attr("name","");

                $("#y-management select").attr("name","management");

                $("#staff-distribution input[type=text]").attr("name","roomIds");
            }

        });

    });
});

//点击修改获取数据
$(function(){

    $("#m-revise").on("click",this,function(){

        var arr=[];

        $("input[type='checkbox']").each(function(){

            if(this.checked){

                $(this).parent().parent().find("td").each(function(i){

                    arr[i] = $(this).text();

                    console.log("表格中的元素转换成数组："+arr);

                });

                var inputs = $("#edit input,#edit textarea,#edit select");

                console.log(inputs.length);

                for(var j = 0;j<inputs.length;j++){

                    if(inputs.eq(j).attr("class")!="management"){

                        if(inputs.eq(j).attr("class")=="userId"||inputs.eq(j).attr("class")=="roomPart"
                            ||inputs.eq(j).attr("class")=="floorcount"
                            ||inputs.eq(j).attr("class")=="partcount"||inputs.eq(j).attr("class")=="roomcount"
                            ||inputs.eq(j).attr("class")=="buildingId"){

                            inputs.eq(j).val(parseInt(arr[j+1]));
                        }
                        else
                            inputs.eq(j).val(arr[j+1]);
                    }

                }
            }

        });
        var userType = $("#l-y-type-edit").val();

        if(userType=="楼主"||userType=="楼管"){

            $("#l-management-edit").css("display","block");

            $("#y-management-edit").css("display","none");

            $("#l-management-edit select").attr("name","management");

            $("#l-management-edit input").attr("name","roomIds");

            $("#y-management-edit select").attr("name","");

            $("#staff-distribution-edit input[type=text]").attr("name","");
        }

    });

});

//表格模糊查询(比较简单，有待改进)
$(function(){

    var searchValue = $(".right-content .search");

    var tds = $("#tbody tr td");

    $(".search").on("change",this,function(){

        if(searchValue.val()){

            tds.parent().addClass("hide-tr");

            for(var i = 0;i<tds.length;i++){

                if(searchValue.val()==tds.eq(i).text()){

                    var preTd = tds.eq(i);

                    preTd.parent().removeClass("hide-tr");

                }
            }

        }

    })

});

// 复选框变单选框
$(function(){

    $("#tbody").on("click","input[type=checkbox]",function(e){

        $("input[type='checkbox']").each(function(){

            this.checked = false;
        });

        e.target.checked=true;
        var db=e.target;

        db.ondblclick=function () {

            db.checked=false;
        }
    });

});


//刷新
$(function(){
   $("#m-reload").on("click",this,function(){
       location.reload();
   })
});


// 根据楼的名字改变段数
$(function(){
    // var buildings = $("#buildings option");
    //
    // $("#buildings").on("change",this,function(){
    //
    //     var that = this;
    //
    //     buildings.each(function(){
    //
    //         if(this.value==$(that).val()){
    //
    //             var duan = $(this).attr("data-duan");
    //
    //             console.log(duan);
    //
    //             var str = '<option value=""></option>';
    //
    //             for(var i = 1;i<=duan;i++){
    //
    //                 console.log(i);
    //
    //                 str += '<option value='+i+'>'+i+'</option>';
    //             }
    //
    //             $("#duan").empty().append(str);
    //
    //         }
    //     })
    // })
    var duan = $("#buildings").data("duan");

    var str = "";

    for(var i = 1;i<=duan;i++){

        str += '<option value='+i+'>'+i+'</option>';
    }

    $("#duan").empty().append(str);
});

//根据下级用户类型不同显示内容不同的弹出框
$(function(){

    $("#staff-distribution select[class=management]").on("click",this,function(){

        $("#staff-distribution .staff-distr").slideToggle("500");
    })
});

/**
 * 楼管大爷（教室状态）
 */

//点击侧栏时间提醒，页面部分刷新
$(function(){
    var str2 = '<span>请设置提醒时间：</span>'
                +'<input type="time" name="dateRemind" class="dateRemind"/>'
                +'<input type="checkbox" name="choose-all" class="choose-all"/>'
                +' <span>选择全部教研室</span>'
                +'<button type="submit" class="sub-btn" data-action="../../../webSmartLock/room/remind">确定</button>';

    $("#set-time",parent.document).on("click",this,function(){

        $("#classroom-state").empty().append(str2);

    });

});

//选择部分教室
$(function chooseRoom(){

    $(".ul").on("click","li",function(){

        if($(this).css("border-color")=='rgb(0, 0, 0)'){

            $(this).addClass("current");

        }else{

            $(this).removeClass("current");

        }
    });

});

//选择全部教室
$(function () {
    $(".choose-all").on("click",this,function(e){
        console.log($(this).prop("checked"));

        if(this.checked){

            $(".ul li").each(function () {

                if($(this).attr("data-room-type")=="教室"){
                    $(this).addClass("current");
                }
                else{
                    $(this).removeClass("current");
                }
            });
        }
        else{
            $(".ul li").removeClass("current");
        }

    });
});

//将选择的教室封装成数组传到controller层
function roomIdArray(){

    var roomId = new Array();

    $(".ul li").each(function(){

        if($(this).css("border-color")=='rgb(255, 0, 0)'){

            roomId.push($(this).attr("data-room-id"));
        }
    });

    $(".allRoomId").val(roomId);

    console.log($(".allRoomId").val());
}

//点击不同按钮，提交到相应的url
$(function(){

    $(".sub-btn").on("click",this,function(){

        roomIdArray();

        $("#room-form").attr("action",$(this).attr("data-action"));

    });

});

//将选择的教研室id封装成数组传到controller层
$(function(){

    $("#create .submit-create").on("click",this,function(){
        if($("#staff-distribution .staffrooms").data("type")=="院主"){
            var roomIdArray = new Array();
            $("#staff-distribution li input").each(function(){
                if(this.checked){
                    roomIdArray.push($(this).data("id"));
                    console.log($(this).val());
                }
            });
            $("#roomIdArray").val(roomIdArray);
        }
        if($("#staff-distribution .staffrooms").data("type")=="教研负责人"){
            var roomIdArray1 = [];

            $('#staff-distribution li input').each(function(){
                if(this.checked){
                    roomIdArray1.push($(this).data("id"));
                }
            });
            $("#roomIdArray-yuan").val(roomIdArray1);
            $("#yuan-management").val(roomIdArray1);
        }

    });

});

// 点击确定提交教学楼信息时，判断字段是否是空,如果为空，则阻止提交
$("#buildingContent .submit-create,#roomContent .submit-create").on("click",this,function(e){

    var flag = 0;

    $("#create input:not([type=hidden]),#create select").each(function(item){

        if(!$(this).val()){

            flag = 1;
        }

    });

    if(flag){

        $.Huimodalalert('字段不能为空！',2000);

        e.preventDefault();
    }
});
//修改教学楼信息时判断是否为空
$("#buildingContent .submit-edit,#roomContent .submit-edit").on("click",this,function(e){

    var flag = 0;

    $("#edit input:not([type=hidden]),#edit select").each(function(){

        if(!$(this).val()){

            flag = 1;
        }
    });

    if(flag){

        $.Huimodalalert('字段不能为空！',2000);

        e.preventDefault();
    }
});
//创建用户时判断登录名密码是否为空
$("#userContent .submit-create").on("click",this,function(e){

    var flag = 0;

    $("#create input[class=account],#create input[class=password]").each(function(item){

        if(!$(this).val()){

            flag = 1;
        }

    });

    if(flag){

        // alert("用户名和密码不能为空");
        $.Huimodalalert('用户名和密码不能为空！',2000);

        e.preventDefault();
    }
});
//修改时判断用户名密码是否为空
$("#userContent .submit-edit").on("click",this,function(e){

    var flag = 0;

    $("#edit input[class=account],#edit input[class=password]").each(function(item){

        if(!$(this).val()){

            flag = 1;
        }

    });

    if(flag){

        // alert("用户名和密码不能为空");
        $.Huimodalalert('用户名和密码不能为空！',2000);

        e.preventDefault();
    }
});


//总负责人--用户管理--分配楼管--如果教学楼已经被分配完，就禁用add按钮
$(function(){

    var judge = $("#buildingsExitJudge");
    if(!judge.val()&&$("#userContent #m-add").data("type")=="总负责人"){
        judge.next().on("click",this,function(){

            $.Huimodalalert('请先停用再分配！',2000);
            judge.next().attr("disabled","disabled");
        });
    }
});







