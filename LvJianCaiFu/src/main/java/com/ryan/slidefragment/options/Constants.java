package com.ryan.slidefragment.options;


public class Constants {
	/**
	 * "http://mczscms.qctt.cn/a/index2.html" 服务器接口：用post
	 * http://182.92.6.20/api/
	 */
	// public static final String SERVICE_URL =
	// "http://103.227.205.4/api/indtex.php";
	// public static final String SERVICE_URL =
	// "http://mczsapi.qctt.cn/index.php";
	//本地数据
	//public static final String SERVICE_URL = "http://192.168.0.51:8080/lvjiancfAPP";
	//网络数据
	//public static final String SERVICE_URL = "http://192.168.1.10:8088/";
	// public static final String SERVICE_URL ="http://192.168.1.10:8088";
	 public static final String SERVICE_URL ="http://106.2.219.210:8088";
	// "http://test.qctt.cn/api/index.php/";
	
	//收藏
	public static final String SHOUCHANG_CHANGPINZHANSHI=SERVICE_URL+"/shoucangxinxiwenzhang.action?";
	//取消收藏
	public static final String SHOUCHANG_QUXIAO=SERVICE_URL+"/shanchuyiguanzhu?";
	
	//我的收藏
	public static final String SHOUCHANG_1=SERVICE_URL+"/ljcollecselect";
	public static final String SHOUCHANG_shanchu=SERVICE_URL+"/wodeshoucangshanchu?id=";
	
	//投融资dialog
	public static final String TOURONGZIDIALOG=SERVICE_URL+"/shouyebiaotichaxunshifo?";
	//投融资成功案例
	public static final String TOURONGZI_CHENGGONG=SERVICE_URL+"/chegngonganliselect";
	//投融资成功案例详细
	public static final String TOURONGZIDIALOG_CHENGGONGXIANGXI=SERVICE_URL+"/chenggonganlixiangxi?id=";
	//投融资聚人脉
	public static final String TOURONGZI_JURENMAI=SERVICE_URL+"/jurenmaixixin";
	//聚人脉详情
	public static final String TOURONGZI_JURENMAI_XIANGQING=SERVICE_URL+"/zhaozijinxiagnmuxiangqin?id=";

	//股权投资IANGXI
	public static final String GUQUANTOUZI_LISTVIEW=SERVICE_URL+"/zhaozijincapguquan";
	public static final String GUQUANTOUZI_LISTVIEW_XiangQing=SERVICE_URL+"/zhaozijincapbyid?id=";
	//找项目
	public static final String ZHAOXIANGMU=SERVICE_URL+"/zhaoxiagnmuquanbu";
	public static final String ZHAOXIANGMUXiangQing=SERVICE_URL+"/zhaoxiagnmuxiangxilianjie?id=";
	//投资鉴赏
	public static final String XIANGMURONGZI=SERVICE_URL+"/zhaoxiagnmutouzijianchang";

	//参股合作
	public static final String CANGUHEZUO=SERVICE_URL+"/zhaoxiagnmucaiguhezuo";

	//找项目_项目建厂详情
	public static final String Xiangmujianchang_1=SERVICE_URL+"/zhaoxiagnmutouzijianchang";



	//债券投资
	public static final String ZHAIQUANTOUZI_LISTVIEW=SERVICE_URL+"/zhaozijincapzhaiquan";

	// 登录
	public static final String GETLOGINMESSAGE_URL = SERVICE_URL
			+ "/Androidlogin";// 获取userID
	// 注册协议
	public static final String GETAGREEMENT_URL = SERVICE_URL
			+ "/ljAgreementActionxieyi";
	// 加入联盟协议
	public static final String GETJOINUNION_URL = SERVICE_URL
			+ "/lianmengActionandroid";
	// 轮播图
	public static final String FOCUS_URL = SERVICE_URL + "/shouyeaction";
	// 意见反馈
	public static final String GETYIJIANFANKUI = SERVICE_URL + "/fankuixinxi?";
	// 联系客服
	public static final String GETLIANXIKEFU = SERVICE_URL
			+ "/ljTelephongmobile";
	// 服务
	public static final String FUWU_ZHANLANHUI = SERVICE_URL
			+ "/fuwulianmengxinxi";
	// 盟员级别详情
	public static final String MENGYUANJIBIEXIANGQING = SERVICE_URL
			+ "/mengyuanjibeixiangqing";
	// 加入盟员协议
	public static final String JIARUMENGYUANXIEYI = SERVICE_URL
			+ "/flchanxunowselect";
	// 二维码图片
	public static final String ERWEIMATUPIAN = SERVICE_URL
			+ "/shouyeactionerweima";
	// 首页Gridview
	public static final String GRIDVIEW = SERVICE_URL
			+ "/ljGetviewtupianzhanshi";
	//首页面中技术展示
	public static final String TECHNOLOGYSHOWS = SERVICE_URL
			+ "ljGetviewtupianzhanshijishu";
	//首页面中全部产品
	public static final String ALL_PRODUCTS = SERVICE_URL
			+ "ljGetviewtupianzhanshiquanbu";
	//首页面中行业资讯
	public static final String INDUSTRY_INFORMATION = SERVICE_URL
			+ "ljGetviewtupianzhanshihangye";
//	Announce ments
	//首页面中通知公告
	public static final String ANNOUNCE_MENTS = SERVICE_URL
			+ "ljGetviewtupianzhanshitongzhi";
	//首页面中成功案例
	public static final String SUCCESSFUL_CASES = SERVICE_URL
				+ "ljGetviewtupianzhanshichenggong";
	// 首页新闻
	public static final String SHOUYEXINWEN = SERVICE_URL + "/newsselectxinwen";
	// 首页新闻详情
	public static final String SHOUYEXINWENXIANGQING = SERVICE_URL
			+ "/newsselectxinwenid";
	// 首页重要新闻
	public static final String SHOUYEZHONGYAOXINWEN = SERVICE_URL
			+ "/chuisongxinxi";
	// 联盟新闻listview
	public static final String LIANMENGXINWENLISTVIEW = SERVICE_URL
			+ "/newsselectxinwenquanbu";
	// 行业资讯listview
	public static final String HANGYEZIXUN = SERVICE_URL + "/newsselect";
	// 通知公告listview 
	public static final String TONGZHIGONGGAO = SERVICE_URL
			+ "/newsselecttongzhi";
	// 技术展示listview
	public static final String JISHUZHANSHI = SERVICE_URL
			+ "/jishuzhanshiselect";
	// 技术展示详情listview
	public static final String JISHUZHANSHIXIANGQING = SERVICE_URL
			+ "/fintStrDataById";
	// 成功案例listview
	public static final String CHENGGONGANLI = SERVICE_URL + "/chenggonganli";
	// 成功案例详情listview
	public static final String CHENGGONGANLIXIANGQING = SERVICE_URL
			+ "/chenggonganlixinangxi";
	// 国家政策listview
	public static final String GUOJIAZHENGCE = SERVICE_URL
			+ "/selectLjPoliciesuserone";
	// 地方政策listview
	public static final String DIFANGZHENGCE = SERVICE_URL
			+ "/selectLjPoliciesusertwo";
	// 行业政策listview
	public static final String HANGYEZHENGCE = SERVICE_URL
			+ "/selectLjPoliciesuserthree";
	// 联盟政策listview
	public static final String LIANMENGZHENGCE = SERVICE_URL
			+ "/selectLjPoliciesuserfour";
	// 政策详情listview
	public static final String ZHENGCEXIANGQING = SERVICE_URL
			+ "/selectLjPoliciesuserid";
	// 获取验证码
	public static final String YANZHENMA = SERVICE_URL + "/sendtwomas";
	// 注册用户
	public static final String ZHUCEYONGHU = SERVICE_URL + "/comparephoneCods";
	// 成员理事长
	public static final String CEHNGYUANLISHIZHANG = SERVICE_URL
			+ "/listAlllishizhang";
	// 成员副理事长
	public static final String CHENGYUANFULISHIZHANG = SERVICE_URL
			+ "/listAllfulishizhang";
	// 成员常务理事长
	public static final String CHENGYUANCHANGWULISHIZHANG = SERVICE_URL
			+ "/listAllchangwulishizhang";
	// 成员理事
	public static final String CEHNGYUANLISHI = SERVICE_URL + "/listAlllishi";
	// 成员盟员
	public static final String CHENGYUANMENGYUAN = SERVICE_URL
			+ "/listAlllimengyuan";
	// 成员公司详情
	public static final String CEHNGYUANGONGSIXIANGQING = SERVICE_URL
			+ "/lianmengchengyuanxinxi";
	// 产品中的Gridview
	public static final String CHANGRIDVIEW = SERVICE_URL + "/ljptypeselectid";
	// 关于公司
	public static final String GUANYUGONGSI = SERVICE_URL + "/guanyugongsi";
	// 全部产品
	public static final String QUANBUCHANPIN = SERVICE_URL
			+ "/chanpintupianselectquanbu";
	// 全部产品详情
	public static final String QUANBUCHANPINXIANGQING = SERVICE_URL
			+ "/chanpintupianbyid";
	// 产品listview
	public static final String CHANPINLISTVIEW = SERVICE_URL + "/chanpintupian";
	// 更新
	public static final String GENGXIN = SERVICE_URL
			+ "/shouyeactionlistLjversion";
	// 服务的12个模块
	public static final String FUWUGRIDVIEW = SERVICE_URL
			+ "/ljGetviewtupianzhanshifuwu";
	// http://test.qctt.cn/api/index.php/Users/getThirdLoginMessage
	// public static final String THRIDLOGINMESSAGE_URL = HOST_URL
	// + "/Users/getThirdLoginMessage";// 获取userID（第三方）
	// // http://test.qctt.cn/api/index.php/Users/getUserMessage
	// public static final String GETUSETINFO_URL = HOST_URL
	// + "/Users/getUserMessage";// 获取用户信息
	// // http://test.qctt.cn/api/index.php/Users/getRegMessage
	// public static final String GETREGMESSAGE_URL = HOST_URL
	// + "/Users/getRegMessage";// 注册
	// // http://test.qctt.cn/api/index.php/Comment/getCollect
	// public static final String GETCOLLECT_URL = HOST_URL
	// + "/Comment/getCollect";// 判断是否收藏
	// // http://test.qctt.cn/api/index.php/Comment/Addcollection
	// public static final String ADDCOLLECT_URL = HOST_URL
	// + "/Comment/Addcollection";// 添加取消收藏
	// // http://test.qctt.cn/api/index.php/Comment/setComment
	// public static final String SUBMITCOMMENT_URL = HOST_URL
	// + "/Comment/setComment";// 详情页发布评论
	// // http://test.qctt.cn/api/index.php/Comment/setPraise
	// public static final String PRAISE_URL = HOST_URL + "/Comment/setPraise";
	// // 详情页评论点赞
	// // http://test.qctt.cn/api/index.php/Users/updateUserNikename
	// public static final String CHANGENICKNAME_URL = HOST_URL
	// + "/Users/updateUserNikename"; // 修改用户名称
	// // http://test.qctt.cn/api/index.php/News/getCollectionlist
	// public static final String GETCOLLECTIONLIST_URL = HOST_URL
	// + "/News/getCollectionlist";// 获取收藏列表

	/**
	 * 获取图片路径
	 */
	public static final String IMAGE_URL = "http://elderly.qctt.cn";
	/**
	 * 主页
	 */
	public static final String MAIN_URL = "http://mczscms.qctt.cn/a/index2.html";
	/**
	 * 获取图片路径
	 */
	public static final String UPDATE_URL = "http://mczsapi.qctt.cn/index.php/Version/getNewVersion";
	/**
	 * 关于我们
	 */
	public static final String ABOUTUS_URL = "http://mczscms.qctt.cn/a/about.html";
	/**
	 * 用户手册
	 */
	public static final String USERBOOK_URL = "http://mczscms.qctt.cn/a/agreement.html";
	/**
	 * 应用推荐
	 */
	public static final String APP_URL = "http://mczscms.qctt.cn/a/apps.html";
	/**
	 * 获取地图 http://mczscms.qctt.cn/a/map.html
	 */
	public static final String MAP_URL = "http://mczscms.qctt.cn/a/map.html";
	/**
	 * webCartype
	 */
	public static final String WEBCARTYPE = "http://mczscms.qctt.cn/plus";
	/**
	 * 获取图片路径（七牛）
	 */
	public static final String IMAGE_QN_URL = "http://qcttapp.qiniudn.com/";
	// ###################################################################################

	/**
	 * 没有数据"statuses_code":10000
	 */
	public static final String NODATA = "10000";
	/**
	 * 成功有数据返回
	 */
	public static final String SUCCESS = "10001";

	/**
	 * 已存在
	 */
	public static final String EXIST = "20002";
	/**
	 * 注册成功
	 */
	public static final String OK = "20001";
	// ###################################################################################

	/**
	 * 
	 * 
	 * 获取筛选条件的可选结果（可用）OK getOptions
	 */
	public static final String OPTION_URL = SERVICE_URL
			+ "/Selection/getOptions";
	/**
	 * "http://mczsapi.qctt.cn/index.php/Interest/interests"
	 * 
	 * 兴趣添加
	 */
	public static final String ADDINTER_URL = SERVICE_URL
			+ "/Interest/interests";
	/**
	 * 
	 * /Adver/getAdver 获取筛选条件的可选结果（可用）OK getOptions
	 */
	public static final String ADVER_URL = SERVICE_URL + "/Adver/getAdver";
	/**
	 * 
	 * 
	 * 获取用户信息
	 */
	public static final String USERINFOS_URL = SERVICE_URL
			+ "/User/getUserInfo";
	/**
	 * 
	 * 
	 * 预约试驾
	 */
	public static final String TRYDRIVE_URL = SERVICE_URL + "/Index/testDrive";

	/**
	 * 搜索结果
	 */
	public static final String SEARCH_URL = SERVICE_URL + "/Index/getSearch";
	/**
	 * 获取token
	 */
	public static final String TOKEN_URL = SERVICE_URL + "/Token/getToken";
	/**
	 * 获取token
	 */
	public static final String CANCELTEXT_URL = SERVICE_URL
			+ "/Mine/cancelTestDrive";
	/**
	 * 热门搜索
	 */
	public static final String HOTSEARCH_URL = SERVICE_URL
			+ "/Index/getHotSearch";
	/**
	 * 品牌接口（可用）OK
	 */
	public static final String BRAND_URL = SERVICE_URL
			+ "/Selection/getBrandList";
	/**
	 * 品牌和类型接口 getBrandCarTypeList
	 */
	public static final String BRANDANDTYPE_URL = SERVICE_URL
			+ "/Selection/getBrandCarTypeList";
	// ###################################################################################

	/**
	 * 鐭俊鐨勬帴鍙?:鑾峰彇楠岃瘉鐮?(鍙敤)ok
	 */
	public static final String GETSMS_URL = SERVICE_URL + "/Sms/getSmsCode";
	/**
	 * 鐭俊鐨勬帴鍙?:楠岃瘉鐭俊楠岃瘉鐮?(鍙敤)ok
	 */
	public static final String SENDSMS_URL = SERVICE_URL + "/Sms/sendSmsCode";
	/**
	 * 鐭俊鐨勬帴鍙?:娉ㄥ唽鎺ュ彛(鍙敤)ok getRegUser
	 */
	public static final String GETREG_URL = SERVICE_URL + "/User/getRegUser";
	/**
	 * 鐭俊鐨勬帴鍙?:娉ㄥ唽鎺ュ彛(鍙敤)ok getRegUser
	 * http://123.56.113.248/api/index.php/Token/getToken
	 */
	public static final String GETTOKEN_URL = SERVICE_URL + "/Token/getToken";
	/**
	 * 鐧婚檰鐨勬帴鍙?:鐧婚檰鎺ュ彛(鍙敤)
	 * http://123.56.113.248/api/index.php/User/getUserLogin
	 * http://123.56.113.248/api/index.php/User/getUserLogin
	 */
	public static final String LOGOIN_URL = SERVICE_URL + "/User/login_general";
	/**
	 * 绗笁鏂圭櫥闄嗙殑鎺ュ彛:鐧婚檰鎺ュ彛(鍙敤)
	 */
	public static final String THIRDLOGOIN_URL = SERVICE_URL
			+ "/User/login_thirdparty";
	/**
	 * 鐧婚檰鐨勬帴鍙?:鏍规嵁鐢ㄦ埛ID鑾峰彇鐢ㄦ埛淇℃伅鐨勬帴鍙?(鍙敤)
	 */
	public static final String USERINFO_URL = SERVICE_URL
			+ "/User/login_thirdparty";

	/**
	 * 淇敼瀵嗙爜鐨勬帴鍙?:鍜屽繕璁板瘑鐮佷竴涓帴鍙?
	 */
	public static final String REPWD_URL = SERVICE_URL
			+ "/User/setResettingPassword";
	/**
	 * 鑾峰彇绯荤粺娑堟伅
	 */
	public static final String GETSYSTEMMSG_URL = SERVICE_URL
			+ "/Mine/getSystemMsg";
	/**
	 * 淇敼鐢ㄦ埛淇℃伅鐨勬帴鍙?:
	 */
	public static final String REUSER_URL = SERVICE_URL + "/Mine/modifyuser";
	/**
	 * 淇敼鐢ㄦ埛淇℃伅鐨勬帴鍙?:
	 */
	public static final String CHECKUSER_URL = SERVICE_URL
			+ "/User/checkUserExist";
	/**
	 * 淇敼鐢ㄦ埛淇℃伅鐨勬帴鍙?: http://mczsapi.qctt.cn/index.php/Mine/Feedback
	 */
	public static final String ISSUE_URL = SERVICE_URL + "/Mine/Feedback";
	/**
	 * 閫?鍑虹櫥褰曠殑鎺ュ彛:
	 */
	public static final String OUTUSER_URL = SERVICE_URL + "/User/outLogin";
	// ###################################################################################
	/**
	 * 鍙戝竷璇勮鐨勬帴鍙?:闂瓟鍒楄〃鎺ュ彛(鍙敤)ok
	 */
	public static final String ASKLIST_URL = SERVICE_URL
			+ "/Questions/getQuestionsList";
	/**
	 * 璁剧疆鏈娑堟伅鏁?
	 */
	public static final String UNREDNUM_URL = SERVICE_URL
			+ "/Questions/getMsgCount";
	/**
	 * 鍙戝竷璇勮鐨勬帴鍙?(鍙敤) /Questions/sendComment
	 */
	public static final String SENDCOMMENT_URL = SERVICE_URL
			+ "/Questions/sendComment";
	/**
	 * 鎻愰棶鐨勬帴鍙?(鍙敤)
	 */
	public static final String SENDQUESTIONS_URL = SERVICE_URL
			+ "/Questions/sendQuestions";
	/**
	 * 鍥炵瓟璇︽儏鐨勬帴鍙?(鍙敤)
	 */
	public static final String GETCOMMENTLIST_URL = SERVICE_URL
			+ "/Questions/getCommentList";
	/**
	 * 鍚岄棶鐨勬帴鍙?(鍙敤)ok
	 */
	public static final String SAMEPROBLEM_URL = SERVICE_URL
			+ "/Questions/sameproblem";
	/**
	 * 鍚岄棶鐨勬帴鍙?(鍙敤)ok
	 */
	public static final String LIKEIT_URL = SERVICE_URL + "/Questions/likeit";
	// ###################################################################################
	/**
	 * 鎴戠殑娑堟伅鍒楄〃鐨勬帴鍙?(鍙敤)
	 */
	public static final String GETMSGLIST_URL = SERVICE_URL
			+ "/Questions/getMsgList";
	/**
	 * 鎴戠殑娑堟伅鍒楄〃璁剧疆宸茶鐨勬帴鍙?(鍙敤)
	 */
	public static final String DELMSG_URL = SERVICE_URL
			+ "/Questions/setReadStatus";
	// ###################################################################################
	/**
	 * 鎴戠殑鍏虫敞鐨勬帴鍙?(鍙敤娑佃疆鎾浘)
	 */
	public static final String INTEREST_URL = SERVICE_URL
			+ "/Index/getSpecialFocus";
	/**
	 * 鍏虫敞鍒楄〃鐨勬帴鍙?
	 */
	public static final String ATTENTION_URL = SERVICE_URL
			+ "/Index/getFocusList";
	/**
	 * 鍏虫敞杞﹀瀷鎺ュ彛
	 */
	public static final String FOCUSECARTYPE_URL = SERVICE_URL
			+ "/Index/cancelfocus";
	/**
	 * 鍙栨秷鍏虫敞杞﹀瀷鎺ュ彛
	 */
	public static final String ADDSPECIAL_URL = SERVICE_URL
			+ "/Index/addSpecialfocus";
	/**
	 * 鎴戠殑闂瓟
	 */
	public static final String MYSAMEASK_URL = SERVICE_URL
			+ "/Mine/getUserQuestionsList";
	/**
	 * 鎴戠殑棰勭害
	 */
	public static final String MYTEXTDRIVE_URL = SERVICE_URL
			+ "/Mine/getTestDrive";
	/**
	 * 娣诲姞鍏虫敞杞﹀瀷
	 */
	public static final String ADDFOCUS_URL = SERVICE_URL + "/Index/addfocus";
	/**
	 * 娣诲姞鍏虫敞杞﹀瀷
	 */
	public static final String CARCOMMENT_URL = SERVICE_URL
			+ "/Index/sendCarComment";
	/**
	 * 鑾峰彇杞﹀瀷鍥剧墖 /Index/getCarPic
	 */
	public static final String GETCARPIC_URL = SERVICE_URL + "/Index/getCarPic";
	/**
	 * 鑾峰彇杞﹀瀷鍥剧墖 /Index/getCarPic
	 */
	public static final String PUTFEEDBACK_URL = SERVICE_URL
			+ "/Index/getCarPic";
	/**
	 * 璁剧疆鏁版嵁淇濆瓨
	 */
	public static final String QCTT_SETTINS = "qctt_settings";
}
