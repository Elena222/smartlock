/**
 * Created by 029call on 2017/1/13.
 */
//存放主要交互逻辑代码
//    javascript模块化
//    json表示对象的方式
var smartLock={
//    封装ajax的url
    URL:{



    },
    validateAccount:function (account) {
        if(account ){
            return true;

        }else{
            return false;
        }
    },

//    登录逻辑
   login:{
        //登录初始化
        init:function (params) {
        //登录
        var account=$.cookie('account');
        var password=$.cookie('psd') ;
        //    验证用户名
            if(!smartLock.validateAccount(account)){
            //    绑定account

                $('#submitBtn').click(function () {
                    var inputAccount=$('#id').val();
                    if(smartLock.validateAccount(inputAccount)){
                    //    账户写入

                        // $.cookie()
                        //刷新页面
                        window.location.reload();
                    }else{
                    //   弹出错误提示信息
                    }
                });
            }


        }
    }

}