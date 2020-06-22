
//初始化界面
window.onload = function () {

    getUserTable("");

}

//获取所有用户信息
function getUserTable(jsonData) {
    //清空列表内容
    $('#bs-table-list-myself tbody').empty();

    //修改表格标题头文本
    var thead = "<tr><th class='col-1'>#</th><th class='col-1'>用户id</th><th class='col-2'>用户名</th><th class='col-3'>手机</th><th class='col-3'>邮箱</th> <th class='col-2'>操作</th><tr>"
    $('#bs-table-list-myself thead').html(thead);

    var xhr = new XMLHttpRequest();
    xhr.request = "json";
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {


            var jsonObj = JSON.parse(xhr.responseText);

            if (jsonData !=="" && jsonData !==null){
                jsonObj = JSON.parse(jsonData);
                // jsonObj = json;
            }

            // console.log(jsonObj.content[0]);
            // uBirthday: "2000-06-19T00:00:00.000+0000"
            // uCreateDate: "2020-06-10T00:00:00.000+0000"
            // uEmail: null
            // uId: 1
            // uName: "evan"
            // uPassword: "123"
            // uPhone: null
            // uRank:
            var str = 0;
            for (var j of jsonObj.content){

                //新建tr-->一行内容
                var newTr = document.createElement('tr');
                newTr.style.marginLeft = '50px';

                var newTd1 = document.createElement('td');

                //序号
                newTd1.innerHTML = "<td>"+ (str+1) +"</td>";
                newTr.append(newTd1);

                var jArr = [j.uId,j.uName,j.uPhone, j.uEmail];
                for (var t = 0;t<4;t++){
                    //动态新建td
                    var newTd = document.createElement('td');

                    var TestStr = jArr[t];
                    // if (t === 2){
                    //     TestStr = TestStr.replace(/\\/g,"/");
                    // }

                    var innerText = document.createTextNode(TestStr);
                    newTd.appendChild(innerText);
                    newTr.append(newTd);

                }

                //按钮
                var newTd2 = document.createElement('td');
                newTd2.innerHTML = "<td class='col-2'><button class='btn btn-success btn-xs' data-toggle='modal' >修改</button><button class=\"btn btn-danger btn-xs\" data-toggle=\"modal\">删除</button> </td>";

                /**
                 * uid	int	11
                 * uname	varchar	45
                 * upassword	varchar	20
                 * ubirthday	date	0
                 * urank	int	3
                 * ucreateDate	date	0
                 * uphone	varchar	30
                 * uemail	varchar	40
                 */
                newTd2.getElementsByTagName('button')[0].setAttribute('onclick',"showChangeUserInfo(this);");
                newTd2.getElementsByTagName('button')[0].setAttribute('id',"btnChaUserInfo"+j.uId);
                newTd2.getElementsByTagName('button')[1].setAttribute('id',"btnDelUserInfo"+j.uId);
                newTd2.getElementsByTagName('button')[1].setAttribute('onclick',"deleteUserModal(this);");

                // $(newTd3).children('button').attr('id',"btnChaUserInfo"+j.uid);
                // $(newTd3).children('button').attr("onclick","showChangeUserInfo(this);"); //('onclick', "f(" +j.uid+ ")");
                newTr.append(newTd2);

                $(newTr).appendTo("#bs-table-list-myself tbody");
                str++;
            }

        }
    };
    xhr.open('POST','getUsers',true);
    xhr.setRequestHeader("Content-type",
        "application/x-www-form-urlencoded; charset=UTF-8");
    xhr.send("state=*");

    $("#bs-table-list-myself").bootstrapTable('refresh');


}

//获取磁力链接信息
$('#magnetLinksManager').on('click', function () {
    getMagnetLinks("*");
});

function getMagnetLinks(key) {
    $.ajax({
        type:  "post",
        url:   "search",
        data: {
            page:"1",
            pmagnet_link:"*",
            pfrom_which_se:"*",
            pmagnetlink_key: key
        },
        // dataType: "json",
        success : function (data) {
            loadMagnetLinksData(data);

            // console.log("获取数据成功");
            // console.log("data.rows:"+data.rows);
            // console.log("data.pageTotal:"+data.pageTotal);
        },
        complete: function () {
            console.log("获取数据完成");

        },
        error: function () {
            console.log("获取数据失误");

        }
    });
}

//加载数据，更新磁力信息表格
function loadMagnetLinksData(data) {


    //清空列表内容
    $('#magnetLinksTable tbody').empty();

    //修改表格标题头文本
    // class='col-1'
    var thead = "<tr>" +
        "<th>#</th>" +
        "<th>pid</th>" +
        "<th>magnetLinkKey</th>" +
        "<th>fromWhichSe</th>" +
        "<th>crawledDate</th>" +
        "<th>magnetLinkFileSize</th>" +
        "<th>magnetLink</th>" +
        "<th>操作</th><tr>"
    $('#magnetLinksTable thead').html(thead);
    // var jsonObj = JSON.parse(data.rows);
    // console.log("进入loadMagnetLinksData\n"+jsonObj);

    var str = 0;
    for (var j of data.rows) {

        //新建tr-->一行内容
        var newTr = document.createElement('tr');
        newTr.style.marginLeft = '50px';

        var newTd1 = document.createElement('td');

        //序号
        newTd1.innerHTML = "<td>" + (str + 1) + "</td>";
        newTr.append(newTd1);
        // "<th class='col-1'>pid</th>" +
        // "<th class='col-2'>magnetLinkKey</th>" +
        // "<th class='col-3'>fromWhichSe</th>" +
        // "<th class='col-2'>crawledDate</th>" +
        // "<th class='col-1'>magnetLinkFileSize</th>" +
        // " <th class='col-2'>magnetLink</th><tr>"
        // crawledDate   fromWhichSe   magnetDownloadTimes  magnetLink
        // magnetLinkFileSize  magnetLinkKey  magnetLinkSize  pid: 143
        var jArr = [j.pid, j.magnetLinkKey, j.fromWhichSe,
            j.crawledDate.substring(0,10), j.magnetLinkFileSize,j.magnetLink];
        // console.log(str+":"+jArr);

        for (var t = 0; t < 6; t++) {
            //动态新建td
            var newTd = document.createElement('td');
            // newTd.setAttribute('class', "col-1")
            var curTr = jArr[t];
            newTd.setAttribute("title",curTr);

            var innerText = document.createTextNode(curTr);
            newTd.appendChild(innerText);
            newTr.append(newTd);

        }

        //按钮
        var newTd2 = document.createElement('td');
        newTd2.innerHTML = "<td class='col-2'>" +
            "<button class='btn btn-success btn-xs' data-toggle='modal' >修改</button>" +
            "<button class=\"btn btn-danger btn-xs\" data-toggle=\"modal\">删除</button>" +
            " </td>";

        /**
         crawledDate: "2020-06-16T00:00:00.000+0000"
         fromWhichSe: "torKitty"
         magnetDownloadTimes: 0
         magnetLink: "magnet:?xt=urn:btih:0B18A86BCFE14E15C0E68F8ED204663058E43835&dn=M%E5%B0%B1%E6%98%AF%E5%87%B6%E6%89%8B.1931.%E5%9B%BD%E5%BE%B7%E5%8F%8C%E8%AF%AD.%E7%AE%80%E7%B9%81%E4%B8%AD%E5%AD%97%EF%BF%A1CMCT%E7%8E%84%E5%AD%90&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Ftracker.publicbt.com%3A80&tr=udp%3A%2F%2Ftracker.ccc.de%3A80&tr=http%3A%2F%2Ftracker.hdcmct.com%3A2710%2Fannounce"
         magnetLinkFileSize: "3.20 GB"
         magnetLinkKey: "M就是凶手.1931.国德双语.简繁中字￡CMCT玄子"
         magnetLinkSize: 0
         pid: 143
         */
        newTd2.getElementsByTagName('button')[0].setAttribute('onclick', "showChangeMagnetLinkInfo(this);");
        newTd2.getElementsByTagName('button')[0].setAttribute('id', "btnChangeMagnetLinkInfo" + j.pid);
        newTd2.getElementsByTagName('button')[1].setAttribute('id', "btnDelMagnetLinkInfo" + j.pid);
        newTd2.getElementsByTagName('button')[1].setAttribute('onclick', "deleteMagnetLinkModal(this);");

        // $(newTd3).children('button').attr('id',"btnChaUserInfo"+j.uid);
        // $(newTd3).children('button').attr("onclick","showChangeUserInfo(this);"); //('onclick', "f(" +j.uid+ ")");
        newTr.append(newTd2);

        $(newTr).appendTo("#magnetLinksTable tbody");
        str++;
        $("#magnetLinksTable").bootstrapTable('refresh');

    }
}


//修改用户信息窗口
function showChangeUserInfo(btn){
    // UserInfoMail
    // data-target='#reviseUser'
    $(function() {
        $('#reviseUser').modal('show');
    });
    var id = $(btn).parent().parent().find("td").next().html();

    $('#reviseUser').on('shown.bs.modal', function () {
        $('#chUserInfoSpan').html(id);

        var tableObj = $(btn).get(0).parentElement.parentElement;
        var tdObj = tableObj.getElementsByTagName('td');

        //获取点击行数据
        var userName   = tdObj[2].innerHTML;
        var userPhone  = tdObj[3].innerHTML;
        var userEmail  = tdObj[4].innerHTML;


        $('#UserInfoName').prop('value',userName);
        $('#UserInfoMail').prop('value',userEmail);
        $('#UserInfoPhone').prop('value',userPhone);

        //确认修改
        $('#btnChangeUserInfo').on('click',function () {
            //初始化修改窗口中的内容

            var name = $('#UserInfoName').val();
            var mail = $('#UserInfoMail').val();
            var phone = $('#UserInfoPhone').val();

            if (name === "" || name === null || mail === "" || mail === null
                || phone === "" || phone === null){
                alert("请完善信息");
            }else {
                console.log("进入ajax");
                //进入ajax
                // $.ajax({
                //     url:"backstageAction.php",
                //     type:"POST",
                //     data:{"uid":id,"state":"changeUserInfo",
                //         "newName":name,
                //         "newMail":mail,
                //         "newPhone":phone
                //
                //     },
                //     success:function (result) {
                //         if (result === "+1"){
                //             alert("修改信息成功！");
                //
                //         }else if (result === "-0"){
                //             alert("修改信息失败（ sql更新出错！ ）");
                //         }else{
                //             alert("修改信息失败（ 未知错误，请反馈问题！ ）--- " + result);
                //         }
                //
                //         //关闭窗口
                //         $(function() {
                //             $('#reviseUser').modal('hide');
                //         });
                //
                //         //刷新列表
                //         getUserTable("");
                //
                //
                //     },
                //     error:function () {
                //         alert("修改用户信息错误！");
                //     }
                // });
            }
        });
    });

}


//删除用户弹窗
function deleteUserModal(btn) {

    $(function() {
        $('#deleteUser').modal('show');
    });


    //formChangPsw  id="btnDelUserInfo1"
    var id = $(btn).attr('id').split('btnDelUserInfo')[1];
    $('#deleteUser').on('shown.bs.modal', function () {
        $('#delUserIdSpan').html(id);

        //确认修改
        $('#btnDelUserSure').on('click',function () {
            $.ajax({
                url:"backstageAction.php",
                type:"POST",
                data:{"uid":id,"state":"delUser"},
                success:function (result) {
                    if (result === "+1"){
                        alert("删除用户成功！");

                    }else if (result === "-0"){
                        alert("删除用户失败（ sql更新出错！ ）");
                    }else{
                        alert("删除用户失败（ 未知错误，请反馈问题！ ）--- " + result);
                    }
                    // console.log(result);

                    //关闭窗口
                    $(function() {
                        $('#deleteUser').modal('hide');
                    });

                    //刷新列表
                    getUserTable("");

                },
                error:function () {
                    alert("删除用户错误！");
                }
            });
        });
    });

}

//磁力链接管理弹窗
//修改磁力链接信息窗口
function showChangeMagnetLinkInfo(btn) {

    console.log("进入到showChangeMagnetLinkInfo")
    $('#changeMagnetLink').modal('show');


    //$(btn).parent().parent().html()===
    // <td>1</td>
    // <td title="143">143</td>
    // <td title="M就是凶手.1931.国德双语.简繁中字￡CMCT玄子">M就是凶手.1931.国德双语.简繁中字￡CMCT玄子</td>
    // <td title="torKitty">torKitty</td>
    // <td title="2020-06-16">2020-06-16</td>
    // <td title="3.20 GB">3.20 GB</td>
    // <td title="magnet:?xt=urn:btih:0B18A86BCFE14E15C0E68F8ED204663058E43835&amp;dn=M%E5%B0%B1%E6%98%AF%E5%87%B6%E6%89%8B.1931.%E5%9B%BD%E5%BE%B7%E5%8F%8C%E8%AF%AD.%E7%AE%80%E7%B9%81%E4%B8%AD%E5%AD%97%EF%BF%A1CMCT%E7%8E%84%E5%AD%90&amp;tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&amp;tr=udp%3A%2F%2Ftracker.publicbt.com%3A80&amp
    var iid1 = $(btn).parent().parent().find("td").next().html();
}


//删除链接
function deleteMagnetLinkModal(btn) {
    // UserInfoMail
    // data-target='#reviseUser'
    $('#deleteMagnetLink').modal('show');

    //$(btn).parent().parent().html()===
    // <td>1</td>
    // <td title="143">143</td>
    // <td title="M就是凶手.1931.国德双语.简繁中字￡CMCT玄子">M就是凶手.1931.国德双语.简繁中字￡CMCT玄子</td>
    // <td title="torKitty">torKitty</td>
    // <td title="2020-06-16">2020-06-16</td>
    // <td title="3.20 GB">3.20 GB</td>
    // <td title="magnet:?xt=urn:btih:0B18A86BCFE14E15C0E68F8ED204663058E43835&amp;dn=M%E5%B0%B1%E6%98%AF%E5%87%B6%E6%89%8B.1931.%E5%9B%BD%E5%BE%B7%E5%8F%8C%E8%AF%AD.%E7%AE%80%E7%B9%81%E4%B8%AD%E5%AD%97%EF%BF%A1CMCT%E7%8E%84%E5%AD%90&amp;tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&amp;tr=udp%3A%2F%2Ftracker.publicbt.com%3A80&amp
    var iid1 = $(btn).parent().parent().find("td").next().html();
}

