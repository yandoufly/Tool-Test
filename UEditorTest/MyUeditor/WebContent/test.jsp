<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.all.min.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="js/ueditor/lang/zh-cn/zh-cn.js"></script>
	<link type="text/css" rel="stylesheet" href="js/ueditor/third-party/video-js/video-js.css"/>
	<script language="javascript" type="text/javascript" src="js/ueditor/third-party/video-js/video.js"></script>
	
	<link type="text/css" rel="stylesheet" href="js/ueditor/third-party/video-js/video-js.css"/>
	<script language="javascript" type="text/javascript" src="js/ueditor/third-party/video-js/video.js"></script>
	<!-- 在页面的head部分加入如下脚本，非支持 audio/video标签浏览器使用 -->
	<script type="text/javascript" src="js/html5media.min.js"></script>	
	
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.1.11.3.js"></script>
    <script src="js/SuperSlide.2.1.1.js"></script>
    <script src="js/js.js"></script>
</head>
<body>
    <header class="index_header">
        <div class="index_header_l">
            <img src="images/images/icon/index_03.png" alt="">
        </div>
        <ul class="index_header_l2">
            <li>名校云集</li>
            <li>·</li>
            <li>竞争激烈</li>
            <li>·</li>
            <li>品质保证</li>
            <li>·</li>
            <li>服务周到</li>
        </ul>
        <dl class="index_header_r">
            <dt><img src="images/index_05.png" alt=""><span>027-81331070</span>
            <div class="clear"></div>
            </dt>
            <dd>7X24小时全国服务热线，欢迎来电咨询！</dd>
        </dl>
        <div class="clear"></div>
    </header>
    <nav class="index_nav">
		<ul class="index_nav_ul index_public">
			<li><a href="index.html">首页</a></li>
			<li><a href="about.html">关于我们</a></li>
			<li><a href="product.html">成果展示</a></li>
			<li><a href="course.html">课程介绍</a></li>
			<li><a href="jmys.html">招生信息</a></li>
			<li><a href="news.html">新闻中心</a></li>
			<li><a href="case.html">资源与服务</a></li>
			<li><a href="contact.html">联系我们</a></li>
		</ul>
	</nav>
    <!-- banner -->
    <div class="index_case_banner index_public">
        <img src="images/case_03.png" alt="">
    </div>
    <!-- main -->
    <div class="case_main index_public">
        <section class="case_left">
            <footer class="case_left_t">
                <span class="case_left_t1">关于我们</span>
                <span class="case_left_t2">About us</span>
            </footer>
            <ul class="case_left_ul1">
                <li><a href="about.html">学院简介</a></li>
                <li><a href="about_2.html">校园环境</a></li>
                <li><a href="about_3.html">师资力量</a></li>
                <li><a href="about_4.html" class="case_left_li_on">组织架构</a></li>
                <li><a href="about_5.html">资质荣誉</a></li>
                <li><a href="about_6.html">领导致辞</a></li>
            </ul>

            <footer class="case_left_b">
                <span class="case_left_b1">联系我们</span>
                <span class="case_left_b2"><a href="">more>></a></span>
            </footer>
            <ul class="case_left_ul2">
                <li><strong>塔莎艺术设计培训学校</strong></li>
                <li>电 话：400-8441-400 </li>
                <li>传 真：0731—89747502 </li>
                <li>网 址：www.vliliang.com</li>
                <li>地 址：广西桂林市灵川县灵田镇</li>
                <li>地 址：西大门口 </li>
            </ul>

        </section>
        <section class="case_right">
            <header class="case_right_f">
                <div class="case_right_f_l">组织架构</div>
                <div class="case_right_f_r">
                    <a href="jvascript:;">首页</a>&gt
                    <a href="">关于我们</a>&gt
                    <a href="">组织架构</a>
                </div>
                <div class="clear"></div>
            </header>
            <div class="case_right_box">
                <div class="aboat_text">
                    ${content}
                </div>
            </div>
        </section>
        <div class="clear"></div>
    </div>
    <!-- 页尾 -->
    <footer class="index_footer">
        <div class="index_footer_box index_public">
            <div class="index_footer_l">
                <img src="images/index1_03.png" alt="">
            </div>
            <div class="index_footer_c">
                <ul>
                    <li><a href="">关于我们</a></li>
                    <li>|</li>
                    <li><a href="">家教互联</a></li>
                    <li>|</li>
                    <li><a href="">在线报名</a></li>
                    <li>|</li>
                    <li><a href="">联系我们</a></li>
                    <li>|</li>
                    <li><a href="">精彩新闻</a></li>
                    <li>|</li>
                    <li><a href="">新生指南</a></li>
                    <li>|</li>
                    <li><a href="">心理咨询</a></li>
                    <li>|</li>
                    <li><a href="">招聘信息</a></li>

                </ul>
                <div> 版权所有 © 塔莎艺术设计培训学校  咨询电话 0731-85864736 <br/> 
                    传真：027-81331070 地址:广西省桂林市桂林电子科技大学设计 </div>
            </div>
            <div class="index_footer_r">
                <img src="images/index_88.png" alt="">
            </div>
            <div class="clear">
            </div>
        </div>
    </footer>
</body>
</html>