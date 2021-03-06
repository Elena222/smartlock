var Login = function () {
    
    return {
        //main function to initiate the module
        init: function () {
        	
           $('.login-form').validate(
           	{
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	                username: {
	                    required: "用户名错误或为空！"
	                },
	                password: {
	                    required: "密码错误或为空！"
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-error', $('.login-form')).show();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
                             
	                   $.post("/ajax/checklogin.php",
                   {
                       
                      username:$('#username').val(),
                      password:$('#password').val()
                    
                    },
                    function(data,status){
                    
                        if(data>0)
                       window.location.href = "mainpage.php";
                       else
                       {
                        
                           alert("密码错误！");
                       }
                     
                        
                        
                    });
           
	            }
	      	}
               
          );

	      $('.login-form input').keypress(function (e) 
          
          {
              
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
                        
                        
                        
                        
						window.location.href = "mainpage.php";
	                }
	                return false;
	            }
	        });

        }

    };

}();