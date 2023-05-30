package com.wx.util;



import org.apache.http.HttpEntity;  
import org.apache.http.HttpHost;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;  
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;  
import org.apache.http.client.methods.RequestBuilder;  
import org.apache.http.conn.routing.HttpRoute;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClientBuilder;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
    
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;  
    
public class HttpClientUtils {  
    
    private static PoolingHttpClientConnectionManager connectionManager = null;  
    private static HttpClientBuilder httpBuilder = null;  
    private static RequestConfig requestConfig = null;  
    
    private static int MAXCONNECTION = 10;  
    
    private static int DEFAULTMAXCONNECTION = 5;  
    
    private static String IP = "cnivi.com.cn";  
    private static int PORT = 80;  
    
    static {  
        //设置http的状态参数  
        requestConfig = RequestConfig.custom()  
                .setSocketTimeout(5000)  
                .setConnectTimeout(5000)  
                .setConnectionRequestTimeout(5000)  
                .build();  
    
        HttpHost target = new HttpHost(IP, PORT);  
        connectionManager = new PoolingHttpClientConnectionManager();  
        connectionManager.setMaxTotal(MAXCONNECTION);//客户端总并行链接最大数  
        connectionManager.setDefaultMaxPerRoute(DEFAULTMAXCONNECTION);//每个主机的最大并行链接数  
        connectionManager.setMaxPerRoute(new HttpRoute(target), 20);  
        httpBuilder = HttpClients.custom();  
        httpBuilder.setConnectionManager(connectionManager);  
    }  
    
    public static CloseableHttpClient getConnection() {  
        CloseableHttpClient httpClient = httpBuilder.build();  
        return httpClient;  
    }  
    
    
    public static HttpUriRequest getRequestMethod(Map<String, String> map, String url, String method) {  
        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        Set<Map.Entry<String, String>> entrySet = map.entrySet();  
        for (Map.Entry<String, String> e : entrySet) {  
            String name = e.getKey();  
            String value = e.getValue();  
            NameValuePair pair = new BasicNameValuePair(name, value);  
            params.add(pair);  
        }  
        HttpUriRequest reqMethod = null;  
        if ("post".equals(method)) {  
            reqMethod = RequestBuilder.post().setUri(url)  
                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))  
                    .setConfig(requestConfig).build();  
        } else if ("get".equals(method)) {  
            reqMethod = RequestBuilder.get().setUri(url)  
                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))  
                    .setConfig(requestConfig).build();  
        }  
        return reqMethod;  
    }  
    
    public static void main(String args[]) throws IOException {  
        Map<String, String> map = new HashMap<String, String>();  
        map.put("account", "account");  
        map.put("password", "password");  
    
        HttpClient client = getConnection();  
        HttpUriRequest post = getRequestMethod(map, "http://127.0.0.1/wx!wxloginNew.action?prjId=20200303145111050203910907", "post");  
        HttpResponse response = client.execute(post);  
        
        try {
        		System.out.println("response.getStatusLine().getStatusCode()==="+response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {  
			    HttpEntity entity = response.getEntity();  
			    String message = EntityUtils.toString(entity, "utf-8");  
			    System.out.println(message);  
			} else {  
			    System.out.println("请求失败");  
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
    }  
    
    
    
    
    /** 
     * 发送 get请求 
     */   
    public void get() {   
        CloseableHttpClient httpclient = HttpClients.createDefault();   
        try {   
            // 创建httpget.     
            HttpGet httpget = new HttpGet("http://www.baidu.com/");   
            System.out.println("executing request " + httpget.getURI());   
            // 执行get请求.     
            CloseableHttpResponse response = httpclient.execute(httpget);   
            try {   
                // 获取响应实体     
                HttpEntity entity = response.getEntity();   
                System.out.println("--------------------------------------");   
                // 打印响应状态     
                System.out.println(response.getStatusLine());   
                if (entity != null) {   
                    // 打印响应内容长度     
                    System.out.println("Response content length: " + entity.getContentLength());   
                    // 打印响应内容     
                    System.out.println("Response content: " + EntityUtils.toString(entity));   
                }   
                System.out.println("------------------------------------");   
            } finally {   
                response.close();   
            }   
        } catch (ClientProtocolException e) {   
            e.printStackTrace();   
        } catch (ParseException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally {   
            // 关闭连接,释放资源     
            try {   
                httpclient.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
    }  

    
    /** 
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果 
     */   
    public void post() {   
        // 创建默认的httpClient实例.     
        CloseableHttpClient httpclient = HttpClients.createDefault();   
        // 创建httppost     
        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");   
        // 创建参数队列     
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();   
        formparams.add(new BasicNameValuePair("type", "house"));   
        UrlEncodedFormEntity uefEntity;   
        try {   
        	/*
        	 * post方式乱码补充　
        	如果有乱码,可以偿试使用 StringEntity 来替换HttpEntity:
        	StringEntity content =new StringEntity(soapRequestData.toString(), Charset.forName("UTF-8"));// 第二个参数，设置后才会对，内容进行编码  
            content.setContentType("application/soap+xml; charset=UTF-8");  
            content.setContentEncoding("UTF-8");  
            httppost.setEntity(content);  
        	 */
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");   
            httppost.setEntity(uefEntity);   
            System.out.println("executing request " + httppost.getURI());   
            CloseableHttpResponse response = httpclient.execute(httppost);   
            try {   
                HttpEntity entity = response.getEntity();   
                if (entity != null) {   
                    System.out.println("--------------------------------------");   
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));   
                    System.out.println("--------------------------------------");   
                }   
            } finally {   
                response.close();   
            }   
        } catch (ClientProtocolException e) {   
            e.printStackTrace();   
        } catch (UnsupportedEncodingException e1) {   
            e1.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally {   
            // 关闭连接,释放资源     
            try {   
                httpclient.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
    }  

}  

