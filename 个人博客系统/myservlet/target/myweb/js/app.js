$(function () {//网页加载完成之后执行里面的方法
    //$("#id")通过id获取页面元素，绑定表单的提交事件
    $("#login_form").submit(function () {
        //自定义自己ajax提交表单数据
        $.ajax({//传入一个json对象
            url:"/myweb/login",//请求的服务路径
            type:"post",//请求方法
            //contentType:"application/json", //请求数据类型，默认表单数据类型，其他则显示调用
            data:$("#login_form").serialize(),//序列化表单数据格式
            dataType:"json",//响应的数据类型
            success:function (r) {
                if (r.success) {//用户登录业务操作成功
                    //跳转到文章列表界面
                    window.location.href = "/myweb/jsp/articleList.jsp";
                }else {
                    alert("错误码："+r.code+"\n错误信息："+r.message);
                }
            }
        })
        //禁用表单默认提交
        return false
    })
})