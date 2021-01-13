package com.xsyy.form.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import com.xsyy.form.domain.DingDingUser;
import com.xsyy.form.util.AccessTokenUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bingai
 * @create 2021-01-07 21:41
 */
@Controller
@RequestMapping("/dingdingform")
public class DingLoginController {

    private static final Logger logger = LoggerFactory.getLogger(DingLoginController.class);

    private static final String URL = "http://bingai.vaiwan.com/dingdingform/homePage";
    private static final String DINGDING_URL = "https://oapi.dingtalk.com";
    private static final String METHOD_GET = "GET";
    private static final String APP_ID = "dingoalxugtrzl1bpgl6q4";
    private static final String APP_SECRET = "loVaAG-okC8v7AhUgHGtjT2USW_61FupldtaNUKSjxUunI_x3xlkwG-fs4YGIAYV";

    @RequestMapping("/login")
    public void login(HttpServletResponse response) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DINGDING_URL).append("/connect/qrconnect?appid=" + APP_ID + "&")
                .append("response_type=code&scope=snsapi_login&state=")
                .append(System.currentTimeMillis()).append("&redirect_uri=").append(URL);
        response.sendRedirect(stringBuilder.toString());

    }

    /**
     * 主页
     * @param code
     * @param model
     * @return
     * @throws ApiException
     */
    @RequestMapping("/homePage")
    public String login(String code, Model model,HttpServletRequest request) throws ApiException {

        OapiV2UserGetResponse userDetails = getUserDetails(code);
        logger.info(String.valueOf(userDetails.getBody()));

        OapiV2UserGetResponse.UserGetResponse userDetailsResult = userDetails.getResult();
        String userName = userDetailsResult.getName();
        String userId = userDetailsResult.getUserid();
        Long deptId = userDetailsResult.getDeptIdList().get(0);
        // 审批里的部门id，1和-1要互相转换一下
        if (deptId.longValue() == 1L) {
            deptId = -1L;
        }
        String jobNumber = userDetailsResult.getJobNumber();

        DingDingUser dingUser = new DingDingUser();
        dingUser.setUserName(userName);
        dingUser.setUserId(userId);
        dingUser.setDeptId(deptId);
        dingUser.setJobNumber(jobNumber);

        //model.addAttribute("userName", userName);
        model.addAttribute("dingUser",dingUser);
        request.getSession().setAttribute("dingUser", dingUser);
        return "homePage";

    }


    /**
     * 出差单
     */
    @GetMapping("/chucai")
    public String chucaiForm(Model model, HttpServletRequest request) {

        //User user = (User) request.getSession().getAttribute("user");
        DingDingUser dingUser = (DingDingUser) request.getSession().getAttribute("dingUser");

        model.addAttribute("dingUser",dingUser);

        return "form_chucai";
    }

    /**
     * 省市区数据
     */
    @GetMapping("/cityData")
    @ResponseBody
    public String cityData() {

        InputStream is = Thread.currentThread().getClass().getClassLoader().getResourceAsStream("province.json");
        String data = null;
        try {
            data = IOUtils.toString(is, "UTF-8");
        } catch (IOException e) {
            logger.error("异常：" + e);
        }
        //String data = "[{\"n\":\"湖南省\",\"s\":[{\"n\":\"长沙市\",\"s\":[{\"n\":\"芙蓉区\"},{\"n\":\"天心区\"},{\"n\":\"岳麓区\"},{\"n\":\"开福区\"},{\"n\":\"雨花区\"},{\"n\":\"望城区\"},{\"n\":\"长沙县\"},{\"n\":\"宁乡县\"},{\"n\":\"浏阳市\"}]},{\"n\":\"株洲市\",\"s\":[{\"n\":\"荷塘区\"},{\"n\":\"芦淞区\"},{\"n\":\"石峰区\"},{\"n\":\"天元区\"},{\"n\":\"株洲县\"},{\"n\":\"攸县\"},{\"n\":\"茶陵县\"},{\"n\":\"炎陵县\"},{\"n\":\"醴陵市\"}]},{\"n\":\"湘潭市\",\"s\":[{\"n\":\"雨湖区\"},{\"n\":\"岳塘区\"},{\"n\":\"湘潭县\"},{\"n\":\"湘乡市\"},{\"n\":\"韶山市\"}]},{\"n\":\"衡阳市\",\"s\":[{\"n\":\"珠晖区\"},{\"n\":\"雁峰区\"},{\"n\":\"石鼓区\"},{\"n\":\"蒸湘区\"},{\"n\":\"南岳区\"},{\"n\":\"衡阳县\"},{\"n\":\"衡南县\"},{\"n\":\"衡山县\"},{\"n\":\"衡东县\"},{\"n\":\"祁东县\"},{\"n\":\"耒阳市\"},{\"n\":\"常宁市\"}]},{\"n\":\"邵阳市\",\"s\":[{\"n\":\"双清区\"},{\"n\":\"大祥区\"},{\"n\":\"北塔区\"},{\"n\":\"邵东县\"},{\"n\":\"新邵县\"},{\"n\":\"邵阳县\"},{\"n\":\"隆回县\"},{\"n\":\"洞口县\"},{\"n\":\"绥宁县\"},{\"n\":\"新宁县\"},{\"n\":\"城步苗族自治县\"},{\"n\":\"武冈市\"}]},{\"n\":\"岳阳市\",\"s\":[{\"n\":\"岳阳楼区\"},{\"n\":\"云溪区\"},{\"n\":\"君山区\"},{\"n\":\"岳阳县\"},{\"n\":\"华容县\"},{\"n\":\"湘阴县\"},{\"n\":\"平江县\"},{\"n\":\"汨罗市\"},{\"n\":\"临湘市\"}]},{\"n\":\"常德市\",\"s\":[{\"n\":\"武陵区\"},{\"n\":\"鼎城区\"},{\"n\":\"安乡县\"},{\"n\":\"汉寿县\"},{\"n\":\"澧县\"},{\"n\":\"临澧县\"},{\"n\":\"桃源县\"},{\"n\":\"石门县\"},{\"n\":\"津市市\"}]},{\"n\":\"张家界市\",\"s\":[{\"n\":\"永定区\"},{\"n\":\"武陵源区\"},{\"n\":\"慈利县\"},{\"n\":\"桑植县\"}]},{\"n\":\"益阳市\",\"s\":[{\"n\":\"资阳区\"},{\"n\":\"赫山区\"},{\"n\":\"南县\"},{\"n\":\"桃江县\"},{\"n\":\"安化县\"},{\"n\":\"沅江市\"}]},{\"n\":\"郴州市\",\"s\":[{\"n\":\"北湖区\"},{\"n\":\"苏仙区\"},{\"n\":\"桂阳县\"},{\"n\":\"宜章县\"},{\"n\":\"永兴县\"},{\"n\":\"嘉禾县\"},{\"n\":\"临武县\"},{\"n\":\"汝城县\"},{\"n\":\"桂东县\"},{\"n\":\"安仁县\"},{\"n\":\"资兴市\"}]},{\"n\":\"永州市\",\"s\":[{\"n\":\"零陵区\"},{\"n\":\"冷水滩区\"},{\"n\":\"祁阳县\"},{\"n\":\"东安县\"},{\"n\":\"双牌县\"},{\"n\":\"道县\"},{\"n\":\"江永县\"},{\"n\":\"宁远县\"},{\"n\":\"蓝山县\"},{\"n\":\"新田县\"},{\"n\":\"江华瑶族自治县\"}]},{\"n\":\"怀化市\",\"s\":[{\"n\":\"鹤城区\"},{\"n\":\"中方县\"},{\"n\":\"沅陵县\"},{\"n\":\"辰溪县\"},{\"n\":\"溆浦县\"},{\"n\":\"会同县\"},{\"n\":\"麻阳苗族自治县\"},{\"n\":\"新晃侗族自治县\"},{\"n\":\"芷江侗族自治县\"},{\"n\":\"靖州苗族侗族自治县\"},{\"n\":\"通道侗族自治县\"},{\"n\":\"洪江市\"}]},{\"n\":\"娄底市\",\"s\":[{\"n\":\"娄星区\"},{\"n\":\"双峰县\"},{\"n\":\"新化县\"},{\"n\":\"冷水江市\"},{\"n\":\"涟源市\"}]},{\"n\":\"湘西土家族苗族自治州\",\"s\":[{\"n\":\"吉首市\"},{\"n\":\"泸溪县\"},{\"n\":\"凤凰县\"},{\"n\":\"花垣县\"},{\"n\":\"保靖县\"},{\"n\":\"古丈县\"},{\"n\":\"永顺县\"},{\"n\":\"龙山县\"}]}]},{\"n\":\"广东省\",\"s\":[{\"n\":\"广州市\",\"s\":[{\"n\":\"荔湾区\"},{\"n\":\"越秀区\"},{\"n\":\"海珠区\"},{\"n\":\"天河区\"},{\"n\":\"白云区\"},{\"n\":\"黄埔区\"},{\"n\":\"番禺区\"},{\"n\":\"花都区\"},{\"n\":\"南沙区\"},{\"n\":\"萝岗区\"},{\"n\":\"增城市\"},{\"n\":\"从化市\"}]},{\"n\":\"韶关市\",\"s\":[{\"n\":\"武江区\"},{\"n\":\"浈江区\"},{\"n\":\"曲江区\"},{\"n\":\"始兴县\"},{\"n\":\"仁化县\"},{\"n\":\"翁源县\"},{\"n\":\"乳源瑶族自治县\"},{\"n\":\"新丰县\"},{\"n\":\"乐昌市\"},{\"n\":\"南雄市\"}]},{\"n\":\"深圳市\",\"s\":[{\"n\":\"罗湖区\"},{\"n\":\"福田区\"},{\"n\":\"南山区\"},{\"n\":\"宝安区\"},{\"n\":\"龙岗区\"},{\"n\":\"盐田区\"}]},{\"n\":\"珠海市\",\"s\":[{\"n\":\"香洲区\"},{\"n\":\"斗门区\"},{\"n\":\"金湾区\"}]},{\"n\":\"汕头市\",\"s\":[{\"n\":\"龙湖区\"},{\"n\":\"金平区\"},{\"n\":\"濠江区\"},{\"n\":\"潮阳区\"},{\"n\":\"潮南区\"},{\"n\":\"澄海区\"},{\"n\":\"南澳县\"}]},{\"n\":\"佛山市\",\"s\":[{\"n\":\"禅城区\"},{\"n\":\"南海区\"},{\"n\":\"顺德区\"},{\"n\":\"三水区\"},{\"n\":\"高明区\"}]},{\"n\":\"江门市\",\"s\":[{\"n\":\"蓬江区\"},{\"n\":\"江海区\"},{\"n\":\"新会区\"},{\"n\":\"台山市\"},{\"n\":\"开平市\"},{\"n\":\"鹤山市\"},{\"n\":\"恩平市\"}]},{\"n\":\"湛江市\",\"s\":[{\"n\":\"赤坎区\"},{\"n\":\"霞山区\"},{\"n\":\"坡头区\"},{\"n\":\"麻章区\"},{\"n\":\"遂溪县\"},{\"n\":\"徐闻县\"},{\"n\":\"廉江市\"},{\"n\":\"雷州市\"},{\"n\":\"吴川市\"}]},{\"n\":\"茂名市\",\"s\":[{\"n\":\"茂南区\"},{\"n\":\"茂港区\"},{\"n\":\"电白县\"},{\"n\":\"高州市\"},{\"n\":\"化州市\"},{\"n\":\"信宜市\"}]},{\"n\":\"肇庆市\",\"s\":[{\"n\":\"端州区\"},{\"n\":\"鼎湖区\"},{\"n\":\"广宁县\"},{\"n\":\"怀集县\"},{\"n\":\"封开县\"},{\"n\":\"德庆县\"},{\"n\":\"高要市\"},{\"n\":\"四会市\"}]},{\"n\":\"惠州市\",\"s\":[{\"n\":\"惠城区\"},{\"n\":\"惠阳区\"},{\"n\":\"博罗县\"},{\"n\":\"惠东县\"},{\"n\":\"龙门县\"}]},{\"n\":\"梅州市\",\"s\":[{\"n\":\"梅江区\"},{\"n\":\"梅县\"},{\"n\":\"大埔县\"},{\"n\":\"丰顺县\"},{\"n\":\"五华县\"},{\"n\":\"平远县\"},{\"n\":\"蕉岭县\"},{\"n\":\"兴宁市\"}]},{\"n\":\"汕尾市\",\"s\":[{\"n\":\"城区\"},{\"n\":\"海丰县\"},{\"n\":\"陆河县\"},{\"n\":\"陆丰市\"}]},{\"n\":\"河源市\",\"s\":[{\"n\":\"源城区\"},{\"n\":\"紫金县\"},{\"n\":\"龙川县\"},{\"n\":\"连平县\"},{\"n\":\"和平县\"},{\"n\":\"东源县\"}]},{\"n\":\"阳江市\",\"s\":[{\"n\":\"江城区\"},{\"n\":\"阳西县\"},{\"n\":\"阳东县\"},{\"n\":\"阳春市\"}]},{\"n\":\"清远市\",\"s\":[{\"n\":\"清城区\"},{\"n\":\"清新区\"},{\"n\":\"佛冈县\"},{\"n\":\"阳山县\"},{\"n\":\"连山壮族瑶族自治县\"},{\"n\":\"连南瑶族自治县\"},{\"n\":\"英德市\"},{\"n\":\"连州市\"}]},{\"n\":\"东莞市\"},{\"n\":\"中山市\"},{\"n\":\"潮州市\",\"s\":[{\"n\":\"湘桥区\"},{\"n\":\"潮安区\"},{\"n\":\"饶平县\"}]},{\"n\":\"揭阳市\",\"s\":[{\"n\":\"榕城区\"},{\"n\":\"揭东区\"},{\"n\":\"揭西县\"},{\"n\":\"惠来县\"},{\"n\":\"普宁市\"}]},{\"n\":\"云浮市\",\"s\":[{\"n\":\"云城区\"},{\"n\":\"新兴县\"},{\"n\":\"郁南县\"},{\"n\":\"云安县\"},{\"n\":\"罗定市\"}]}]}]";

        return data;
    }

    /**
     * 模拟数据
     */
    @GetMapping("/ysxmData")
    @ResponseBody
    public String ysxmData() {
        String ysxmData = "";

        return ysxmData;
    }


    public OapiV2UserGetResponse getUserDetails(String code) throws ApiException {

        // 获取access_token，注意正式代码要有异常流处理
        String access_token = AccessTokenUtil.getToken();

        DefaultDingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
        OapiSnsGetuserinfoBycodeRequest reqBycodeRequest = new OapiSnsGetuserinfoBycodeRequest();
        // 通过扫描二维码，跳转指定的redirect_uri后，向url中追加的code临时授权码
        reqBycodeRequest.setTmpAuthCode(code);
        OapiSnsGetuserinfoBycodeResponse bycodeResponse = client2.execute(reqBycodeRequest, APP_ID, APP_SECRET);

        // 根据unionid获取userid
        String unionid = bycodeResponse.getUserInfo().getUnionid();
        DingTalkClient clientDingTalkClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/user/getbyunionid");
        OapiUserGetbyunionidRequest reqGetbyunionidRequest = new OapiUserGetbyunionidRequest();
        reqGetbyunionidRequest.setUnionid(unionid);
        OapiUserGetbyunionidResponse oapiUserGetbyunionidResponse = clientDingTalkClient.execute(reqGetbyunionidRequest, access_token);

        // 根据userId获取用户信息
        String userid = oapiUserGetbyunionidResponse.getResult().getUserid();
        DingTalkClient clientDingTalkClient2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
        OapiV2UserGetRequest reqGetRequest = new OapiV2UserGetRequest();
        reqGetRequest.setUserid(userid);
        reqGetRequest.setLanguage("zh_CN");
        OapiV2UserGetResponse rspGetResponse = clientDingTalkClient2.execute(reqGetRequest, access_token);
//        System.out.println(rspGetResponse.getBody());
//        Map<String, Object> map = new HashMap<String,Object>();
//        map.put("userInfo", rspGetResponse.getBody());

        return rspGetResponse;
    }


}
