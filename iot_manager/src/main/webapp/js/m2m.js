var pp = "……";
var cmdId = 0;
var t;
function getCmdLog() {
    if (cmdId == 0) {
        $("#modal-cmdShow-message").html("任务完成！");
    } else {
        $.post("SetM2MUSIM.aspx", { action: "getCmdLog", cmdid: cmdId }, function (data) {
            if (data.OK) {
                showMessage = " ICCID : " + data.Message.ICCID + "<br/>执行命令 : " + CommandTypeName(data.Message.CommandType);

                if (pp == "…")
                    pp = "……";
                else
                    pp = "…";

                switch (data.Message.Status) {
                    case 0: 
                        {
                            showMessage += "<br/>提交结果：等待" + pp;
                            break;
                        }
                    case 1: 
                        {
                            showMessage += "<br/>提交结果：执行中" + pp;
                            break;
                        }
                    case 2: 
                        {
                            showMessage += "<br/>提交结果：<font color='red'  >提交失败</font>，原因：" + data.Message.SubmitErrorCode;
                            showMessage += "<br/>提交时间：" + data.Message.SubmitTime;
                            break;
                        }
                    case 3: 
                        {
                            showMessage += "<br/>提交结果：<font color='green'>提交成功</font>，返回：" + data.Message.SubmitErrorCode;
                            showMessage += "<br/>提交时间：" + data.Message.SubmitTime;
                            showMessage += "<br/>报告结果：等待" + pp;
                            break;
                        }
                    case 4: 
                        {
                            showMessage += "<br/>提交结果：<font color='green'>提交成功</font>，返回：" + data.Message.SubmitErrorCode;
                            showMessage += "<br/>提交时间：" + data.Message.SubmitTime;
                            showMessage += "<br/>报告结果：<font color='red'  >报告失败</font>，原因：" + data.Message.ReportErrorCode;
                            showMessage += "<br/>报告时间：" + data.Message.ReportTime;
                            break;
                        }
                    case 5: 
                        {
                            showMessage += "<br/>提交结果：<font color='green'>提交成功</font>，返回：" + data.Message.SubmitErrorCode;
                            showMessage += "<br/>提交时间：" + data.Message.SubmitTime;
                            showMessage += "<br/>报告结果：<font color='green'>报告成功</font>，返回：" + data.Message.ReportErrorCode;
                            showMessage += "<br/>报告时间：" + data.Message.ReportTime;
                            break;
                        }
                }

                $("#modal-cmdShow-message").html(showMessage);
                if (data.Message.Status == 4 || data.Message.Status == 5 || data.Message.Status == 6) {
                    t = window.clearInterval(t);
                }
            } else {
                $("#modal-cmdShow-message").html("任务不存在或已超时！");
            }
        });
    }
}

function CommandTypeName(commandType) {
    switch (commandType) {
        case "CHANGEUSIMOWNER":
            return "<font color='orange'>更改归属</font>";
        case "SYNCUSIMLIST":
            return "<font color='blue'>同步号码</font>";
        case "PAUSEUSIM":
            return "<font color='orange'>停用</font>";
        case "RESUMEUSIM":
            return "<font color='green'>启用</font>";
        case "INITAPI":
            return "<font color='green'>套餐初始化</font>";
        case "CHARGE":
            return "<font color='green'>充值</font>";
        case "CHANGEPLAN":
            return "<font color='orange'>下期资费</font>";
        case "SYNCUSAGE":
            return "<font color='blue'>用量同步</font>";
        case "ACTIVATEUSIM":
            return "<font color='orange'>激活</font>";
        case "RELOADUSIM":
            return "<font color='orange'>数据重载</font>";
        case "AUTORENEWAL":
            return "<font color='green'>自动续费</font>";
        case "HIBERNATE":
            return "<font color='red'>休眠</font>";
        case "REMOVEPLAN":
            return "<font color='red'>取消套餐</font>";
        case "ADDPLAN":
            return "<font color='green'>平台叠加套餐</font>";
        case "CUTSERVICE":
            return "<font color='orange'>单独断网</font>";
        case "SETAUTORENEWAL":
            return "<font color='orange'>自动续费状态</font>";
        case "QUERYUSAGEDETAIL":
            return "<font color='blue'>历史用量</font>";
        case "RNR":
            return "<font color='orange'>同步实名</font>";
        default:
            return "<font color='red'>未知</font>";
    }

}

function StatusName(status) {
    if (status == 0) {
        return "<font color='blue'>待执行</font>";
    }
    else if (status == 1) {
        return "<font color='orange'>正在执行</font>";
    }
    else if (status == 2) {
        return "<font color='red'>执行失败</font>";
    }
    else if (status == 3) {
        return "<font color='green'>执行成功</font>";
    }
    else if (status == 4) {
        return "<font color='red'>命令失败</font>";
    }
    else if (status == 5) {
        return "<font color='green'>命令成功</font>";
    }
    else {
        return "<font color='orange'>未知</font>";
    }
}