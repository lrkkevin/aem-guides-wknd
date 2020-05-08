$(window).adaptTo("foundation-registry").register(
    "foundation.validation.validator", {
        selector : "#alt-special", // 验证特定的alt字段
        validate : function(el) {
            debugger;
            var $el = $(el);
            var $form = $el.closest('form'); // 获得表单
            var $upload = $form.find("coral-fileupload[name$=image]"); // 找到文件上传小部件
            if ($upload.hasClass('is-filled') && !$el.val()) { // 如果类存在，则返回验证消息
              console.log("===========")
              return "Enter Alt text";
            } else {
              console.log("----------")
              return;
            }
        }
    });