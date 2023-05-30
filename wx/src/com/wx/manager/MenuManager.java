package com.wx.manager;


import com.wx.vo.*;
import com.wx.servlet.TokenThread;
import com.wx.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类名: MenuManager </br>
 * 包名： com.wx.manager
 * 描述:菜单管理器类 </br>
 * 开发人员： 六 </br>
 * 创建时间：  2018-8-9 </br>
 * 发布版本：V1.0  </br>
 */
public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
    	
        /*// 第三方用户唯一凭证
        String appId = TokenThread.appid;
        // 第三方用户唯一凭证密钥
        String appSecret = TokenThread.appsecret;
        */
        // 第三方用户唯一凭证
        String appId = "wx354e34f1e820222e";
        // 第三方用户唯一凭证密钥
        String appSecret = "37685e8c3a5ff8fbbf8d6da72b83f995";
        
        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
        System.out.println("main TokenThread.accessToken==="+at);
        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getAccessToken());

            // 判断菜单创建结果
            if (0 == result)
                log.info("菜单创建成功！");
            else
                log.info("菜单创建失败，错误码：" + result);
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("现场监管");
        btn11.setType("click");
        btn11.setKey("11");
       
        CommonButton btn12 = new CommonButton();
        btn12.setName("检查监督");
        btn12.setType("click");
        btn12.setKey("12");
//
//        CommonButton btn13 = new CommonButton();
//        btn13.setName("建筑业产值");
//        btn13.setType("click");
//        btn13.setKey("13");
//
//        CommonButton btn14 = new CommonButton();
//        btn14.setName("通知公告");
//        btn14.setType("click");
//        btn14.setKey("14");

        CommonButton btn21 = new CommonButton();
        btn21.setName("企业注册");
        btn21.setType("click");
        btn21.setKey("21");
        

        CommonButton btn22 = new CommonButton();
        btn22.setName("统计报表");
        btn22.setType("click");
        btn22.setKey("22");
//
        CommonButton btn23 = new CommonButton();
        btn23.setName("信用评价");
        btn23.setType("click");
        btn23.setKey("23");
//
//        CommonButton btn24 = new CommonButton();
//        btn24.setName("学习强国");
//        btn24.setType("click");
//        btn24.setKey("24");
//
//        CommonButton btn25 = new CommonButton();
//        btn25.setName("信用评价");
//        btn25.setType("click");
//        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("人员注册");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("用工需求");
        btn32.setType("click");
        btn32.setKey("32");
//
        CommonButton btn33 = new CommonButton();
        btn33.setName("人员管理");
        btn33.setType("click");
        btn33.setKey("33");


        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("项目服务");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] {btn11,btn12});


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("企业服务");
        mainBtn2.setSub_button(new CommonButton[] {btn21,btn22,btn23});


        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("人才服务");
        mainBtn3.setSub_button(new CommonButton[] {btn31,btn32,btn33});


        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }
}