package com.chengjiaqi.beijing.subway.service.dataservice;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengjiaqi
 */
@Service
public class SubwayBaseDataService {
    private static final Logger logger = LoggerFactory.getLogger(SubwayBaseDataService.class);

    @Value(value = "${subway.url}")
    private String url;

    @Value("${subway.stationUrl}")
    private String stationUrl;

    @Value("${subway.lineUrl}")
    private String lineUrl;

//获取地铁路线的所有信息

    public String getSubwayInfo(String url) throws IOException{
        String text = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.OK.value()) {
                String s = EntityUtils.toString(response.getEntity(), "UTF-8");
                text = (String) JSON.toJSON(s);
            } else {
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text;
    }

/**
 * 提取API线路中站点信息
  */
    public List<List<String>> chooseSubwayLineInfo(String jsonText){
        List<List<String>> lineInfosList = new ArrayList<>();
        List<String> lineInfoList;
        String stationName;
        String stationOrder;
        String sid;
        String sl;
        String lineNumber;
        JSONObject jsonObject = JSONObject.fromObject(jsonText);
        JSONArray l = jsonObject.getJSONArray("l");
        for (int i = 0; i < l.size(); i++) {
            lineNumber = l.getJSONObject(i).getString("ln");
            JSONArray st = l.getJSONObject(i).getJSONArray("st");
            for (int j = 0; j < st.size() ; j++) {
                lineInfoList = new ArrayList<>();
                stationName = st.getJSONObject(j).getString("n");
                sid = st.getJSONObject(j).getString("sid");
                stationOrder = Integer.toString(j+1);
                sl = st.getJSONObject(j).getString("sl");
                lineInfoList.add(stationOrder);
                lineInfoList.add(lineNumber);
                lineInfoList.add(stationName);
                lineInfoList.add(sl);
                lineInfoList.add(sid);
                lineInfosList.add(lineInfoList);
            }
        }
        return lineInfosList;
    }

}
