$(document).ready(function () {

    $("#pageSelect").change(function () {
        location.href=$(this).val();
    });

    if ($(".datepick").length > 0) {
        $(".datepick").datepicker();
    }

    if ($(".select2-me").length > 0) {
        $(".select2-me").each(function () {
            if ($(this).attr("data-search") == "false") {
                $(this).select2({
                    minimumResultsForSearch: -1
                });
            }
            else
                $(this).select2();
        })
    }

    if ($(".spinner").length > 0) {
        $(".spinner").spinner();
    }

    if ($(".icheck-me").length > 0) {
        $(".icheck-me").each(function () {
            var $el = $(this);
            var skin = ($el.attr('data-skin') !== undefined) ? "_" + $el.attr('data-skin') : "", color = ($el.attr('data-color') !== undefined) ? "-" + $el.attr('data-color') : "";
            var opt = {
                checkboxClass: 'icheckbox' + skin + color,
                radioClass: 'iradio' + skin + color,
                increaseArea: "10%"
            }
            $el.iCheck(opt);
        });
    }

    if ($('.form-validate').length > 0) {
        $('.form-validate').each(function () {
            var id = $(this).attr('id');
            $("#" + id).validate({
                errorElement: 'span',
                errorClass: 'help-block error',
                errorPlacement: function (error, element) {
                    element.parents('.controls').append(error);
                },
                highlight: function (label) {
                    $(label).closest('.control-group').removeClass('error success').addClass('error');
                },
                success: function (label) {
                    label.addClass('valid').closest('.control-group').removeClass('error success').addClass('success');
                }
            });
        });
    }

    if ($('#checkall').length > 0) {
        $("#checkall").click(function () {
            if (this.checked) {
                $(".with-checkbox input").iCheck("check");
            }
            else {
                $(".with-checkbox input").iCheck("uncheck");
            }
        });
    }

    $(".check-all").on("click", function () {
        $(".table tbody").find("input[type='checkbox']").prop("checked", $(this).is(":checked"));
    });

    if ($('#deletebat').length > 0) {
        $("#deletebat").click(function () {
            $(this).attr("data-ids", "0");
            $(".with-checkbox input:checked").each(function () {
                var ids = $("#deletebat").attr("data-ids");
                ids += "," + $(this).val();
                $("#deletebat").attr("data-ids", ids);
            });
            bootbox.confirm("确认删除选定项目?", function (result) {
                if (result) {
                    var ids = $("#deletebat").attr("data-ids");
                    var url = $("#deletebat").attr("data-url");
                    $.get(url, { action: "deletebat", ids: ids },
              function (data) {
                  location.reload();
              });
                }
            });
        });
    }

    if ($('.deleterow').length > 0) {
        $(".deleterow").click(function () {
            var id = $(this).attr("data-id");
            var url = $(this).attr("data-url");
            bootbox.confirm("确认删除指定项目?", function (box) {
                if (box) {
                    $.get(url, { action: "deleterow", id: id },
            function (result) {
                if (result.OK) {
                    location.href = url;
                }
                else {
                    bootbox.alert(result.Message);
                }
            });
                }
            });
        });
    }


    if ($('.stoprow').length > 0) {
        $(".stoprow").click(function () {
            var id = $(this).attr("data-id");
            var url = $(this).attr("data-url");
            bootbox.confirm("确认终止指定项目?", function (box) {
                if (box) {
                    $.get(url, { action: "stop", ids: id },
						function (result) {
						    location.href = url;
						});
                }
            });
        });
    }

    try {
        if ($(".ckeditor").length > 0) {
            CKEDITOR.replace("ck");
        }
    } catch (e) {

    }
});
