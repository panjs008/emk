<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
    <title>船舶运行管控中心</title>
    <link rel="shortcut icon" href="images/favicon.ico">
    <link href="plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="plug-in-ui/hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="plug-in-ui/hplus/css/animate.css" rel="stylesheet">
    <link href="plug-in-ui/hplus/css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="plug-in/themes/fineui/main/iconfont.css">
	<script src="plug-in/laydate/laydate.js"></script> 
    <!--  <link href="plug-in/themes/fineui/css/animate.css" rel="stylesheet">
    <link href="plug-in/themes/fineui/css/style.css?v=4.1.0" rel="stylesheet"> -->
    <style type="text/css">
	.gray-bg{
		background-color: #e9ecf3;
	}
	.col-sm-2 {
	    width: 10%;
		padding-left: 5px;
		padding-right: 5px;
		float: left;
	}
	.p-lg{
		padding:0px 0px 10px 0px;
	}
	.widget{
		margin-top: 0px;
	}
	.iconfont{
		font-size: 30px;
		color: white;
	}
	h2 {
        font-size: 19px;
    }
    .echart_div{
    	height:240px;width:100%;
    }
	.ibtn{
		cursor: pointer;
	}
	.flot-chart{
		height:400px;
	}
   /*  .top-navigation .wrapper.wrapper-content{padding:20px 5px !important;}
	.container {
    	 width:99% !important; margin:10px;
    	 padding-right: 1px !important;
    	 padding-left: 1px !important;
	}
	.color_red{color:#e55555;} */
    </style>
</head>
 <body class="gray-bg">

     <div class="row" >
                <div class="col-sm-5" style="width: 49%">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>待受理工单</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <table class="table table-hover no-margins" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>类型</th>
                                        <th>任务</th>
                                        <th>数量</th></tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>
                                            <span class="label label-warning">站内信</span></td>
                                        <td>会议</td>
                                        <td class="text-navy">5</td></tr>
                                    <tr>
                                        <td>2</td>
                                        <td>
                                            <span class="label label-primary">通知</span></td>
                                        <td>任务7</td>
                                        <td class="text-navy">4</td></tr>
                                    <tr>
                                        <td>3</td>
                                        <td>
                                            <span class="label label-warning">类型1</span></td>
                                        <td>任务六</td>
                                        <td class="text-navy">2</td></tr>
                                    <tr>
                                        <td>4</td>
                                        <td>
                                            <span class="label label-primary">类型2</span></td>
                                        <td>任务5</td>
                                        <td class="text-navy">0</td></tr>
                                    <tr>
                                        <td>5</td>
                                        <td>
                                            <span class="label label-warning">类型3</span></td>
                                        <td>任务4</td>
                                        <td class="text-navy">16</td></tr>
                                    <tr>
                                        <td>6</td>
                                        <td>
                                            <span class="label label-primary">类型4</span></td>
                                        <td>任务3</td>
                                        <td class="text-navy">3</td></tr>
                                    <tr>
                                        <td>7</td>
                                        <td>
                                            <span class="label label-warning">类型5</span></td>
                                        <td>任务2</td>
                                        <td class="text-navy">7</td></tr>
                                    <tr>
                                        <td>8</td>
                                        <td>
                                            <span class="label label-primary">类型6</span></td>
                                        <td>任务1</td>
                                        <td class="text-navy">2</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>


                <div class="col-sm-5" style="width: 49%">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>待派工单</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <table class="table table-hover no-margins" style="width: 100%">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>类型</th>
                                    <th>任务</th>
                                    <th>数量</th></tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>
                                        <span class="label label-warning">站内信</span></td>
                                    <td>会议</td>
                                    <td class="text-navy">5</td></tr>
                                <tr>
                                    <td>2</td>
                                    <td>
                                        <span class="label label-primary">通知</span></td>
                                    <td>任务7</td>
                                    <td class="text-navy">4</td></tr>
                                <tr>
                                    <td>3</td>
                                    <td>
                                        <span class="label label-warning">类型1</span></td>
                                    <td>任务六</td>
                                    <td class="text-navy">2</td></tr>
                                <tr>
                                    <td>4</td>
                                    <td>
                                        <span class="label label-primary">类型2</span></td>
                                    <td>任务5</td>
                                    <td class="text-navy">0</td></tr>
                                <tr>
                                    <td>5</td>
                                    <td>
                                        <span class="label label-warning">类型3</span></td>
                                    <td>任务4</td>
                                    <td class="text-navy">16</td></tr>
                                <tr>
                                    <td>6</td>
                                    <td>
                                        <span class="label label-primary">类型4</span></td>
                                    <td>任务3</td>
                                    <td class="text-navy">3</td></tr>
                                <tr>
                                    <td>7</td>
                                    <td>
                                        <span class="label label-warning">类型5</span></td>
                                    <td>任务2</td>
                                    <td class="text-navy">7</td></tr>
                                <tr>
                                    <td>8</td>
                                    <td>
                                        <span class="label label-primary">类型6</span></td>
                                    <td>任务1</td>
                                    <td class="text-navy">2</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

			</div>





    </div>
</div>
     <div class="row" >

         <div class="col-sm-5" style="width: 49%">
             <div class="ibox float-e-margins">
                 <div class="ibox-title">
                     <h5>已完成工单</h5>
                     <div class="ibox-tools">
                         <a class="collapse-link">
                             <i class="fa fa-chevron-up"></i>
                         </a>
                     </div>
                 </div>
                 <div class="ibox-content">
                     <table class="table table-hover no-margins" style="width: 100%">
                         <thead>
                         <tr>
                             <th>序号</th>
                             <th>类型</th>
                             <th>任务</th>
                             <th>数量</th></tr>
                         </thead>
                         <tbody>
                         <tr>
                             <td>1</td>
                             <td>
                                 <span class="label label-warning">站内信</span></td>
                             <td>会议</td>
                             <td class="text-navy">5</td></tr>
                         <tr>
                             <td>2</td>
                             <td>
                                 <span class="label label-primary">通知</span></td>
                             <td>任务7</td>
                             <td class="text-navy">4</td></tr>
                         <tr>
                             <td>3</td>
                             <td>
                                 <span class="label label-warning">类型1</span></td>
                             <td>任务六</td>
                             <td class="text-navy">2</td></tr>
                         <tr>
                             <td>4</td>
                             <td>
                                 <span class="label label-primary">类型2</span></td>
                             <td>任务5</td>
                             <td class="text-navy">0</td></tr>
                         <tr>
                             <td>5</td>
                             <td>
                                 <span class="label label-warning">类型3</span></td>
                             <td>任务4</td>
                             <td class="text-navy">16</td></tr>
                         <tr>
                             <td>6</td>
                             <td>
                                 <span class="label label-primary">类型4</span></td>
                             <td>任务3</td>
                             <td class="text-navy">3</td></tr>
                         <tr>
                             <td>7</td>
                             <td>
                                 <span class="label label-warning">类型5</span></td>
                             <td>任务2</td>
                             <td class="text-navy">7</td></tr>
                         <tr>
                             <td>8</td>
                             <td>
                                 <span class="label label-primary">类型6</span></td>
                             <td>任务1</td>
                             <td class="text-navy">2</td></tr>
                         </tbody>
                     </table>
                 </div>
             </div>
         </div>


         <div class="col-sm-5" style="width: 49%">
             <div class="ibox float-e-margins">
                 <div class="ibox-title">
                     <h5>已关闭工单</h5>
                     <div class="ibox-tools">
                         <a class="collapse-link">
                             <i class="fa fa-chevron-up"></i>
                         </a>
                     </div>
                 </div>
                 <div class="ibox-content">
                     <table class="table table-hover no-margins" style="width: 100%">
                         <thead>
                         <tr>
                             <th>序号</th>
                             <th>类型</th>
                             <th>任务</th>
                             <th>数量</th></tr>
                         </thead>
                         <tbody>
                         <tr>
                             <td>1</td>
                             <td>
                                 <span class="label label-warning">站内信</span></td>
                             <td>会议</td>
                             <td class="text-navy">5</td></tr>
                         <tr>
                             <td>2</td>
                             <td>
                                 <span class="label label-primary">通知</span></td>
                             <td>任务7</td>
                             <td class="text-navy">4</td></tr>
                         <tr>
                             <td>3</td>
                             <td>
                                 <span class="label label-warning">类型1</span></td>
                             <td>任务六</td>
                             <td class="text-navy">2</td></tr>
                         <tr>
                             <td>4</td>
                             <td>
                                 <span class="label label-primary">类型2</span></td>
                             <td>任务5</td>
                             <td class="text-navy">0</td></tr>
                         <tr>
                             <td>5</td>
                             <td>
                                 <span class="label label-warning">类型3</span></td>
                             <td>任务4</td>
                             <td class="text-navy">16</td></tr>
                         <tr>
                             <td>6</td>
                             <td>
                                 <span class="label label-primary">类型4</span></td>
                             <td>任务3</td>
                             <td class="text-navy">3</td></tr>
                         <tr>
                             <td>7</td>
                             <td>
                                 <span class="label label-warning">类型5</span></td>
                             <td>任务2</td>
                             <td class="text-navy">7</td></tr>
                         <tr>
                             <td>8</td>
                             <td>
                                 <span class="label label-primary">类型6</span></td>
                             <td>任务1</td>
                             <td class="text-navy">2</td></tr>
                         </tbody>
                     </table>
                 </div>
             </div>
         </div>

     </div>





     </div>
     </div>
<!-- 全局js -->
<script src="plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="plug-in-ui/hplus/js/content.js"></script>
<script type="text/javascript" src="plug-in/echart/echarts.min.js"></script>
<script>
$(document).ready(function() {
	//直接嵌套显示
    laydate.render({
      elem: '#calendar'
      ,position: 'static'
      ,theme: 'molv'
    	
    });
	var chart1 = echarts.init(document.getElementById('chart1'));
	var chart2 = echarts.init(document.getElementById('chart2'));
	var chart3 = echarts.init(document.getElementById('chart3'));
	var chart4 = echarts.init(document.getElementById('chart4'));
	chart1.setOption({
		tooltip: {},
	    legend: {
	        data: ['Chrome', 'Firefox','IE','其他']
	    },
	    radar: {
	        name: {
	            textStyle: {
	            	 color: '#1f7a22'
	           }
	        },
	        indicator: [
	        	{ name: '人数', max: 5000},
		        { name: '活跃度', max: 100},
	            { name: '增长速度', max: 100},
	            { name: '好评', max: 100},
	            { name: '美观', max: 5}
	        ]
	    },
	    series: [{
	        name: 'ceshi',
	        type: 'radar',
	        // areaStyle: {normal: {}},
	        data : [
	            {
	                value : [4980,80, 80, 80, 4],
	                name : 'Chrome'
	            },{
	                value : [2050,60, 30, 50, 2],
	                name : 'Firefox'
	            },{
	                value : [3500,50, 50, 50, 3],
	                name : 'IE'
	            },{
	                value : [1000,70, 60, 60, 4],
	                name : '其他'
	            }
	        ]
	    }]
	});
	
	var option3 = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        feature: {
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    series: [
		        {
		            name: '业务指标',
		            type: 'gauge',
		            detail: {formatter:'{value}%'},
		            data: [{value: 50, name: '完成率'}]
		        }
		    ]
		};

		setInterval(function () {
		    option3.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
		    chart3.setOption(option3, true);
		},2000);
		
		
		var option4 = {
			    color: ['#3398DB'],
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['Chrome', 'Firefox', 'IE', '其他'],
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'用户人数',
			            type:'bar',
			            barWidth: '60%',
			            data:[4925, 509, 421, 100]
			        }
			    ]
			};
		chart4.setOption(option4, true);
		
		var colors = ['#5793f3', '#d14a61', '#675bba'];
		var option2 = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {
                    type : 'shadow'
                }
            },
            legend: {
                data: ['人数','活跃度','增长速度','好评','美观指数']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis:  {
                type: 'value'
            },
            yAxis: {
                type: 'category',
                data: ['Chrome', 'Firefox','IE','其他']
            },
            series: [
                {
                    name: '人数',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: [60, 20, 15,5]
                },
                {
                    name: '活跃度',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: [90, 80, 70,50]
                },
                {
                    name: '增长速度',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data:[90, 80, 70,50]
                },
                {
                    name: '好评',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data:[90, 80, 70,50]
                },
				{
                    name: '美观',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data:[80, 60, 40,40]
                }
            ]
        };
		chart2.setOption(option2, true);
		
		$(window).resize(chart1.resize);
		$(window).resize(chart2.resize);
		$(window).resize(chart3.resize);
		$(window).resize(chart4.resize);
});
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<!--统计代码，可删除-->
</body>
</html>