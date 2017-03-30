package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.JiaRuLianMengXieYiBean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

public class JiangRuLiangMengXieYiActivity extends Activity {
	private TextView t_jiarulianmengxieyisa;
	private JiaRuLianMengXieYiBean jrxy_bean;
	String t;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_jiang_ru_liang_meng_xie_yi);
		t_jiarulianmengxieyisa = (TextView) findViewById(R.id.t_jiarulianmengxieyis);
		// 将app里的t_jiarulianmengxieyis赋值在t_jiarulianmengxieyis上
		// t_jiarulianmengxieyis.;
		Text(BaseApplication.t_jiarulianmengxieyis);

	}

	// 加入联盟协议加载方法
	private void Text(String r) {
		final HashMap<String, String> ha = new HashMap<String, String>();
		// 给服务器传值
		ha.put("id", r);
		System.out.println("-------------" + r);
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.JIARUMENGYUANXIEYI, ha);
				System.out.println(resultFService);
				boolean judger = JsonJudger.JsonJudger(resultFService, "code",
						"200");
				if (judger) {
					ThreadUtils.post(new Runnable() {
						public void run() {
							setData(resultFService);
						}
					});

				} else {
					ThreadUtils.post(new Runnable() {

						public void run() {
							Toast.makeText(JiangRuLiangMengXieYiActivity.this,
									"网络异常", Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		});
	}

	// 拿到值
	private void setData(String resultFService) {
		jrxy_bean = JsonUtils.parser(resultFService,
				JiaRuLianMengXieYiBean.class);
		CacheUtils.putString("content", jrxy_bean.getContent());
		t = jrxy_bean.getContent() + "";
		t_jiarulianmengxieyisa.setText(t);
		BaseApplication.t_jiarulianmengxieyis = null;
	}
	// PRIVATE VOID JIARULIANMENGXIEYI() {
	// FINAL HASHMAP<STRING, STRING> HA = NEW HASHMAP<STRING, STRING>();
	// // 给服务器传值
	// THREADUTILS.NEWCACHEDTHREADPOOL().EXECUTE(NEW RUNNABLE() {
	//
	// PUBLIC VOID RUN() {
	// FINAL STRING RESULTFSERVICE = HTTPCLIENTDAO
	// .GETLISTHTTPCLIENTPOST(
	// CONSTANTS.JIARUMENGYUANXIEYI, HA);
	// SYSTEM.OUT.PRINTLN(RESULTFSERVICE);
	// BOOLEAN JUDGER = JSONJUDGER.JSONJUDGER(RESULTFSERVICE, "CODE",
	// "200");
	// IF (JUDGER) {
	// THREADUTILS.POST(NEW RUNNABLE() {
	// PUBLIC VOID RUN() {
	// SETDATA2(RESULTFSERVICE);
	// }
	// });
	//
	// } ELSE {
	// THREADUTILS.POST(NEW RUNNABLE() {
	//
	// PUBLIC VOID RUN() {
	// TOAST.MAKETEXT(MENGYUANJIBIEACTIVITY.THIS, "网络异常",
	// 0).SHOW();
	// }
	// });
	// }
	// }
	// });
	// }
	//
	// // 拿到值
	// PRIVATE VOID SETDATA2(STRING RESULTFSERVICE) {
	// JRXY_BEAN = JSONUTILS.PARSER(RESULTFSERVICE,
	// JIARULIANMENGXIEYIBEAN.CLASS);
	// CACHEUTILS.PUTSTRING("ROWS", JRXY_BEAN.GETCONTENT().TOSTRING());
	// BASEAPPLICATION.T_JIARULIANMENGXIEYIS = JRXY_BEAN.GETCONTENT() + "";
	// // GETACTIVITY().FINISH();
	// }
}
