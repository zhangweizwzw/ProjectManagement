package com.bj.yatu.projectmanagement.utils;

/**
 * Created by admin on 2017/4/17.
 */
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
/**
 * Taost 显示的工具类
 * @author liupanfeng
 *
 */
public class ProcessUtil {
    /** 上下文. */
    private static Context mContext = null;

    /** 显示Toast. */
    public static final int show_process = 0;
    static ProgressDialog progressDialog;
    Boolean isSuccess = false;
    /**
     * 主要Handler类，在线程中可用
     * what：0.提示文本信息
     */
    private static Handler baseHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case show_process:
                    showProcess(mContext,msg.getData().getString("TEXT"));
                    break;
                default:
                    break;
            }
        }
    };

    public static void showProcess(Context context,String text){
//	   ProgressDialog dialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog = new ProgressDialog(context,AlertDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage(text);
        progressDialog.setCancelable(true);
        progressDialog.show();

//	   Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    public static void dismiss(){
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 描述：在线程中提示文本信息.
     * @param
     */
    public static void showProcessInThread(Context context,String text) {
        mContext = context;
        Message msg = baseHandler.obtainMessage(show_process);
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", text);
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }
}
