package com.gkzxhn.prison.keda.utils;

import android.content.Context;

import com.gkzxhn.prison.common.GKApplication;
import com.gkzxhn.prison.utils.KDInitUtil;
import com.kedacom.kdv.mt.api.Base;
import com.kedacom.kdv.mt.api.IM;

public final class TruetouchGlobal {

	// public final static String MTINFO_SKYWALKER = "TrueLink";
	public final static String MTINFO_SKYWALKER = "SKY for Android Phone";

	public final static String MTINFO_SKYWALKER_APS = "Android_Phone";

	public final static String MTINFO_SKYWALKER_DEVTYPE = "SKY for Android";

	// public final static String MTINFO_SKYWALKER = "SKY-X500-1080P60";

	public static String achJid;
	public static String achMoid;
	public static String achE164;
	public static String achEmail;
	public static String achXmppPwd;

	/**
	 * 返回Context 
	 *
	 * @return
	 */
	public static Context getContext() {
		return GKApplication.getInstance();
	}

	/**
	 * 注销
	 */
	public static void logOff() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				GKStateMannager.instance().unRegisterGK();
				GKStateMannager.restoreLoginState();
				if (!KDInitUtil.isH323) {
					// 退出平台
					Base.logOutPlatformServerCmd();
					Base.logOutIMCmd(IM.imHandle);
					/*	// 退出登陆NMS服务器
					KdvMtConfig.LogoutNmsServerCmd();*/
					// 注销Sps Server
					Base.logoutApsServerCmd();
					LoginStateManager.restoreLoginState();
				}
			}
		}).start();

	}

	/**
	 * 退出
	 */
	public static void logOut() {
		GKStateMannager.instance().unRegisterGK();
		GKStateMannager.restoreLoginState();
		// 退出平台
		if (!KDInitUtil.isH323) {
			// 退出平台
			Base.logOutPlatformServerCmd();
			Base.logOutIMCmd(IM.imHandle);
			/*	// 退出登陆NMS服务器
			KdvMtConfig.LogoutNmsServerCmd();*/
			// 注销Sps Server
			Base.logoutApsServerCmd();
			LoginStateManager.restoreLoginState();
		}
		Base.mtStop();
	}
}
