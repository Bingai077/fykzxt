package com.xsyy.form.config;

/**
 * 项目中的常量定义类
 */
public class Constant {
    /**
     * 企业corpid, 需要修改成开发者所在企业
     */
    public static final String CORP_ID = "dinge0707769ed6e372eee0f45d8e4f7c288";
    /**
     * 应用的AppKey，登录开发者后台，点击应用管理，进入应用详情可见
     */
    public static final String APPKEY = "dingoldyrud5duuj97is";
    /**
     * 应用的AppSecret，登录开发者后台，点击应用管理，进入应用详情可见
     */
    public static final String APPSECRET = "6915z6RRcGBlNqKie5_Tlhn4CNewtukHY590wV_xJpA7GlXNw9KY6hMVvmTQNdeQ";

    /**
     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成
     */
    public static final String ENCODING_AES_KEY = "3u1r2xOfmF4EiUCpGnvhMlK5YSgBDVXW6JtjQIy7be0";

    /**
     * 加解密需要用到的token，企业可以随机填写。如 "12345"
     */
    public static final String TOKEN = "12345";

    /**
     * 应用的agentdId，登录开发者后台可查看
     */
    public static final Long AGENTID = 967616506L;

    /**
     * 审批模板唯一标识，可以在审批管理后台找到PROC-E7736A06-19F0-4940-9F3A-2D63A79A8C75
     */
    public static final String PROCESS_CODE = "PROC-E7736A06-19F0-4940-9F3A-2D63A79A8C75";

    /**
     * 回调host
     */
    public static final String CALLBACK_URL_HOST = "http://bingai.vaiwan.com";






}
