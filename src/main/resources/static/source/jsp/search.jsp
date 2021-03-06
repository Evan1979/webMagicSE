<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<!--    <link rel="stylesheet" href="css/bootstrap.min.css">-->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!-- cdn导入-->
    <!-- &lt;!&ndash; 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 &ndash;&gt;-->
    <!-- &lt;!&ndash;	&lt;!&ndash; jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) &ndash;&gt;&ndash;&gt;-->

    <!-- 引入bootstrap样式 -->
    <link href="source/css/bootstrap.css" rel="stylesheet">
    <!-- 引入bootstrap-table样式 -->
    <link href="source/css/bootstrap-table.css" rel="stylesheet">
    <!-- jquery -->


    <!-- bootstrap-table.min.js -->
    <script src="source/js/bootstrap-table.js"></script>
    <!-- 引入中文语言包 -->
    <script src="source/css/bootstrap-table-zh-CN.js"></script>
    <!--    <script src="js/jquery-3.4.1.js"></script>-->
<!--    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>-->
<!--    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8=" crossorigin="anonymous"></script>-->
    <style>
        body{
            background-image: url("source/images/2.jpg");
        }

        .searchTitle{
            padding-top: 40px !important;
            padding-bottom: 40px !important;
            font-weight: bold !important;
            font-size: 36px !important;
            font-style: italic !important;
            color: whitesmoke !important;
        }
        #searchModel{
            /*line-height: 0px;*/
            padding-top: 40px;
            padding-bottom: 40px;
            margin-bottom: 10px;
        }
        #searchIcon{
            background-color: #80bdff;
            color: #FFFFFF;
        }


        #blankHeight{
            height: 20px;
        }
        .myTable{
            margin: 30px !important;
            padding-top: 50px !important;
            border: 1px #a7a5a5 solid !important;
            border-radius: 5px !important;
            background: whitesmoke !important;
        }
        .clearFix {*zoom:1;}
        .clearFix:after {
            visibility: hidden;
            display: block;
            content: " ";
            clear: both;
            height: 0;
        }

        .font-color{
            color: whitesmoke !important;
            padding-top: 20px !important;
            margin-top: 10px !important;

        }

        .nav>li>a>img {
            max-width: none;
        }

        .liImg {
            height: 30px !important;
            width: auto !important;
            border-radius: 50%;
        }
        img {
            vertical-align: middle;
        }
        img {
            border: 0;
        }
        .navbar-nav>li>.aImg{
            padding: 10px 10px 10px 5px !important;
        }
    </style>
</head>
<body>

    <!--    导航-->
    <nav class="navbar navbar-default navbar-static-top  nav-color"  role="navigation"
         style="background-color: rgba(15,133,151,0.83) !important;
                font-size: 16px; ">
    <!--    <div style="background-color: rgba(15,133,151,0.83) !important; width: 500px !important;">-->
    <!--        <h1 class="col-sm-4 searchTitle">Aurora Magnet Search</h1>-->
    <!--    </div>-->

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#slider_sub" aria-expanded="false" aria-controls="navbar" id="btn-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <h1 class="navbar-brand searchTitle">Aurora Magnet Search</h1>

        </div>
        <ul class="nav navbar-nav navbar-right" style="margin-right: 10px; margin-left: 10px;">
            <li><a href="#"  class="font-color"><span id="user_name">${user.uName}</span></a></li>
            <li><a href="#"  class="font-color"><span class="badge" id="message_tag">${user.uRank}</span></a></li>

<%--            <li><a href="#"  class="font-color"><span id="user_name">Evan</span></a></li>--%>
<%--            <li><a href="#"  class="font-color"><span class="badge" id="message_tag">23</span></a></li>--%>
            <li><a href="#" class="font-color "><img src="source/images/timg.jpg" alt="error"  class="liImg aImg" id="profile_photo"></a></li>
            <li><a href="homePageLogin.html" class="font-color"><span class="glyphicon glyphicon-off"></span>&nbsp;注销</a></li>
        </ul>

    </nav>

    <div class="container-fluid">

        <div class="row">
            <div class="col-sm-6 col-sm-offset-3" id="searchModel">
                <div class="input-group">

                    <input type="text" class="form-control" id="keyText" placeholder="输入关键字进行搜索"/>
                    <!--				按钮前面的图标-->
                    <span class="input-group-addon" id="searchIcon" onclick="getSearchKey();">Search
                        <span class="glyphicon glyphicon-search"></span>
                    </span>
                </div>
            </div>
        </div>
<!--        <div class="row" id="blankHeight"></div>-->

        <div class="row myTable">
            <div class="col-sm-offset-1 col-sm-10 table-responsive" title="点击单行可直接查看详情">  <!--  fixed-table-container-->
                <table id="roleTable" data-toggle="#roleTable" data-width="50px"
                       class="table table-hover" >  <!-- data-detail-view="true"-->
                </table>   <!-- data-search="true"-->
            </div>
        </div>

    </div>


    <div class="row">
        <!--    获取详情弹窗-->
        <div class="modal fade" id="getDetail" role="dialog" aria-labelledby="gridSystemModalLabel"
            style="margin-top: 200px;">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">是否复制magnet链接：</h4>   <!--id="gridSystemModalLabel"-->
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                        <!--   // var params ="?magnetLinkKey=" + row.magnetLinkKey
                        + "&magnetLinkFileSize=" +  row.magnetLinkFileSize
                         + "&magnetLink=" +  row.magnetLink
                         + "&magnetDownloadTimes=" +  row.magnetDownloadTimes-->

                            <div class="form-group">
                                <p class="form-control" id="divFileSize">divFileSize</p>
                            </div>
                            <div class="form-group" >
                                <p class="form-control" id="divLinkKey">divLinkKey</p>
                            </div>
                            <div class="form-group">
                                <p class="form-control"  id="divDownloadTimes">divDownloadTimes</p>
                            </div>

                            <div class="form-group" >
                                <input type="text" class="form-control input-sm align" id="magnetLink" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="pull-right">
                            <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                            <button type="button" class="btn btn-xs btn-danger" id="btnCopy">复制链接</button>
                        </div>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>
    <footer>
        <div class="footerDiv col-lg-12">
            ©2019 Aurora Team   粤ICP备19125431号
        </div>
    </footer>
</body>
</html>

<script>

    var $table = $('#roleTable');
    loadTableData("*");

    function loadTableData(key) {

        var urlSearchParams = "search?page=1&pmagnet_link=*&pfrom_which_se=*&pmagnetlink_key="+key;
        $('#roleTable').bootstrapTable({   //$('#roleTable' + roleTableID)
            // url: 'backstageAction111.php?state=getUserChangePswTable', // 服务器数据的加载地址。
            // url: 'search?pmagnetlink_key=这个杀手不太冷&page=1&pmagnet_link=*&pfrom_which_se=*', // 服务器数据的加载地址。
            url: urlSearchParams, // 服务器数据的加载地址。
            method: 'post', // 服务器数据的请求方式 'get' 或 'post'。

            search: false,  //是否显示表格搜索，此搜索是客户端搜索，不会进服务端

            sortable:true, //可以排序

            order:'asc',//默认排序顺序，只能是'asc'或'desc'。

            //设置唯一标识的id是哪个字段 可以用来根据这个唯一id进行查询
            uniqueId: 'uid',

            //分页设置
            pagination: true, // 在表格底部显示分页信息(显示第 x 到第 x 条记录，总共 x 条记录)，翻页条 // 注意：数据页页数超过1页会才展示翻页条
            paginationVAlign: 'bottom', // 指定分页条在垂直方向的位置。'top'，'bottom' 或 'both'。
            sidePagination: 'client' ,//'server',
            // 设置在哪里进行分页，可选值为 'client' 或者 'server'。
            // 注意：设置 'server'时，必须设置服务器数据地址（url）或者重写ajax方法。
            pageSize: 10, // 如果启用分页，设置初始化界面时，默认的每页显示数据条数
            // pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 'All'], // 如果设置了分页，设置可供选择的页面记录数。设置为 All 或者 Unlimited，则显示所有记录
            smartDisplay: false, // 设置为 true 是程序自动判断显示分页信息和 card 视图。这会导致自定义的 pageList不起作用

            responseHandler: responseHandler, // 加载服务器数据之前的处理程序，可以用来格式化数据。
            silentSort: true, // 设置为true将在点击分页按钮时，自动记住排序项。仅在 sidePagination设置为 server时生效

            showColumns: false, // 展示内容列下拉框，方便设置展示那些列
            showRefresh: false, //   显示刷新按钮
            // idField: 'id', // 指定主键列
            // dataType:'json',
            dataField: "rows",  //返回的json数组记录(表格数据)的key.默认是"rows", 数据格式： {"total": 0, "rows": []}
            checkboxHeader: true, // 如果设置 false，将在列头隐藏全选复选框，反之则显示，设置为ture时，必须设置singleSelect=false 才有效
            singleSelect: false, //如果设置为true - 禁止多选，否则不禁止多选
            clickToSelect: true, // 如果设置 true，则在点击行时，自动选中 rediobox 、 checkbox。
            striped: true, // 设置为 true 会有隔行变色效果
            toolbar: '#toolbar', // 一个jQuery 选择器，形如#toolbar, .toolbar，指明自定义的 toolbar
            toolbarAlign: 'left', // 指定 toolbar 水平方向的位置。'left' 或 'right'。


            // crawledDate: "2020-06-16T00:00:00.000+0000"
            // fromWhichSe: "torKitty"
            // magnetDownloadTimes: 0
            // magnetLink: "magnet:?xt=urn:btih:01F62975848DEEFA1184E5C50611024FC9B6F84F&dn=%E8%BF%99%E4%B8%AA%E6%9D%80%E6%89%8B%E4%B8%8D%E5%A4%AA%E5%86%B7&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Ftracker.publicbt.com%3A80&tr=udp%3A%2F%2Ftracker.ccc.de%3A80&tr=http%3A%2F%2Ftv.tracker.prq.to%2Fannounce"
            // magnetLinkFileSize: "7.94 GB"
            // magnetLinkKey: "这个杀手不太冷"
            // magnetLinkSize: 0
            // pid: 666


            // {uid: "1", uname: "evan", upsw: "1234", ulogin_time: "2020-01-01 06:10:26", ustate: "1"}

            //{
            //     field: 'checkbox',
            //     align: 'center', // 设置数据对齐方式可选项： 'left', 'right', 'center'
            //     halign: 'center', // 设置表头标题对齐方式可选项： 'left', 'right', 'center'
            //     valign: 'middle', // 设置单元格数据的垂直方向上的对齐方式，有：top（靠上）、middle（居中）、bottom（靠下）针对checkbox似乎不起作用
            //     checkbox: true, // 显示为复选框
            //     width: '1%', // 设置列宽度
            // },

            columns: [{  //复选框栏目
                checkbox: false,
                visibility: false,
            },{
                field: 'pid',
                title: 'No',
                align: 'center',
                halign: 'center',
                valign: 'middle',
                // searchable:true,
                order: 'desc', // 默认排序方式升序-asc 降序-desc
                width: '5%',
                queryParams: function queryParams(params){
                    var param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                        sortName: params.sortName,
                        sortOrder: params.sortOrder,
                        searchText: $("#searchText").val(),
                        msgType: $("#msgType").val(),
                        productLine: $("#productLine").val()
                    };
                    return param;
                }

            }, {
                field: 'magnetLinkKey',
                title: '关键字',
                align: 'center',
                valign: 'middle',
                halign: 'center',
                width: '14%'
            }, {
                field: 'magnetLinkFileSize',
                title: '文件大小',
                sortable: true, // 允许排序列
                valign: 'middle',
                align: 'center',
                halign: 'center',
                width: '10%'
            }, {
                field: 'magnetDownloadTimes',
                title: '下载次数',
                sortable: true, // 允许排序列
                valign: 'middle',
                align: 'center',
                halign: 'center',
                width: '8%'
            }, {
                field: 'magnetLink',
                title: '链接',
                algin: 'left',
                halgin: 'center',
                width: '15%',
                // events: operateEvents,  //点击事件
                // formatter: operateFormatter   //格式化操作列函数
            }],
            locale:'zh-CN',//中文支持,

        });
    }

    // loadTableData();
    // 加载服务器数据之前的数据处理(responseHandler)

    /**
     * 加载服务器数据之前的处理程序，可以用来格式化数据。
     * 参数：result为从服务器请求到的数据
     * @param result
     */
    function responseHandler(result) {
        if (result.success === 'false') {
            alert('获取角色信息失败');
            return;
        }
        // result.rows.forEach(function (item, index) {
        //     console.log("item:"+item+"----index:"+index);
        //     console.log("item1:"+item.magnetLinkKey);
        // });
        // 返回数据，渲染表格
        return {
            //json数据格式
            // {"total": 0, "rows": []}
            // {"total":4,"row":[{"uid":"1","uname":"evan","upsw":"1234","ulogin_time":"2020-01-01 06:10:26","ustate":"1"},
            // {"uid":"15","uname":"ken2","upsw":"123","ulogin_time":"2020-01-01 04:38:38","ustate":"1"}]}
            total: result.total, //总页数, key名称固定为"total"
            rows: result.rows //行数据，key名称必须和bootstrapTable函数中dataField的值保持一致，默认为rows.

        };


    };


    function getSearchKey() {
        console.log("you click getSearchKey()")
        var key = $('#keyText').val();
        console.log(key)
        randomData(key);

    }

    function randomData(key) {
        console.log("into randomData(key)")

        $.ajax({
            type: "post",
            data: {
                "page": "1",
                "pmagnet_link": "*",
                "pfrom_which_se": "*",
                "pmagnetlink_key": key
            },
            url: "search",
            // dataType: "json",
            success: function (data) {
                var defaultData = [];
                defaultData.rows = {pid:"--", magnetLinkFileSize:"--请更换关键字查找",
                    magnetLinkKey: "--", magnetLink:"--",magnetDownloadTimes:"--"};
                console.log( defaultData.rows)
                //如果数据为空    则添加一条空数据
                if (Object.keys(data.rows).length ===0){
                    $table.bootstrapTable('load', defaultData);
                }else {
                    $table.bootstrapTable('load',data.rows);
                }

            },
            complete: function () {
                //生成分页条
            },
            error: function () {
                alert("加载失败....");
            }
        });
    }

    $('#roleTable').on('click-row.bs.table', function (e,row,$element,field,index) {
    	//取出来的行是个对象
    	// +"对应的element内容:"+ $($element).html() +'\n'
    	// console.log("对应的element内容:"+ $($element).html() +'\n');
        //
        // var params ="?magnetLinkKey=" + row.magnetLinkKey + "&magnetLinkFileSize=" +  row.magnetLinkFileSize  + "&magnetLink=" +  row.magnetLink  + "&magnetDownloadTimes=" +  row.magnetDownloadTimes
        // var url = "detail" + params;
        // window.location.href= url;
        getMagnetLink(row) ;
    });

    //
    // $(function () {
    //     $('#myModal').modal('hide')
    // });
    // $(function () {
    //     $('#myModal').on('hide.bs.modal',
    //         function () {
    //             alert('嘿，我听说您喜欢模态框...');
    //         })
    // });
    $('#detailModal').on('click', function () {
        getMagnetLink();
    });

    //获取链接模态框
    function getMagnetLink(row) {

        //清空输入内容   divFileSize divLinkKey divDownloadTimes
        // var params ="?magnetLinkKey=" + row.magnetLinkKey +
        // "&magnetLinkFileSize=" +  row.magnetLinkFileSize  + "&magnetLink=" +  row.magnetLink  + "&magnetDownloadTimes=" +  row.magnetDownloadTimes

        var link = $('#magnetLink').val(row.magnetLink);
        var link = $('#divFileSize').html(row.magnetLinkFileSize);
        var link = $('#divLinkKey').html(row.magnetLinkKey);
        var link = $('#divDownloadTimes').html("下载次数: "+row.magnetDownloadTimes);

        $('#getDetail').modal('show');

        $('#getDetail').on('shown.bs.modal', function () {

            $('#btnCopy').on('click', function () {

                //将链接复制到粘贴板
                // var oInput = document.createElement("input");
                // oInput.value = magnetLink;
                // document.body.appendChild(oInput);
                // oInput.select(); // 选择对象
                // oInput.className = "oInput";
                // oInput.style.display = "none";
                $("#magnetLink").select(); // 选择对象
                document.execCommand("Copy");  // 执行浏览器复制命令
                $('#getDetail').modal('hide');
                // alert("复制成功:" + '\n' +"magnetLink:"+magnetLink+'\n');
            });

        });
    }

</script>


