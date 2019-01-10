package com.ycy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SMSValidationCodeReceiver extends BroadcastReceiver {
    private static MessageListener mMessageListener;
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";//只要注册声明权限即可收到、阻断

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            //获得短信数据
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            //短信的类型, GSM与CDMA短信的解码方式不同
            String format = intent.getStringExtra("format");

            if (null != pdus) {
                for (Object pdu : pdus) {
                    //23以上版本显示 createFromPdu过时，多加一个format参数即可
                    SmsMessage smsMessage;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        smsMessage = SmsMessage.createFromPdu((byte[]) pdu, format);
                    }else {
                        smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    }

                    //发送号码-可以过滤需要读取的短信的发送号码
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    //短信内容
                    String content = smsMessage.getDisplayMessageBody();
                    if (content.contains("验证码")){
                        String code = extractCode(content);
                        mMessageListener.onReceived(code);
                        abortBroadcast();//中断广播的继续传递,防止优先级低的获取到
                    }
                }
            }
        }
    }

    private String extractCode(String content){
        //TODO
        return "";
    }

    //回调接口
    public interface MessageListener {
        void onReceived(String code);
    }

    public void setOnReceivedMessageListener(MessageListener messageListener) {
        mMessageListener = messageListener;
    }

}
