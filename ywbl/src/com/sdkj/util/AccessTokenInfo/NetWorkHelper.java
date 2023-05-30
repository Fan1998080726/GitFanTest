package com.sdkj.util.AccessTokenInfo;

import javax.net.ssl.*;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * ���������õ��Ĺ�����
 */
public class NetWorkHelper {

    /**
     * ����Https����
     * @param reqUrl �����URL��ַ
     * @param requestMethod
     * @return ��Ӧ����ַ���
     */
    public String getHttpsResponse(String reqUrl, String requestMethod) {
        URL url;
        InputStream is;
        String resultData = "";
        try {
            url = new URL(reqUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = {xtm};

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });


            con.setDoInput(true); //����������������������

            //��android�б��뽫��������Ϊfalse
            con.setDoOutput(false); //������������������ϴ�
            con.setUseCaches(false); //��ʹ�û���
            if (null != requestMethod && !requestMethod.equals("")) {
                con.setRequestMethod(requestMethod); //ʹ��ָ���ķ�ʽ
            } else {
                con.setRequestMethod("GET"); //ʹ��get����
            }
            is = con.getInputStream();   //��ȡ����������ʱ��������������
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
            System.out.println(resultData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }
    /**
     * ����https����
     * 
     * @param requestUrl �����ַ
     * @param outputStr �ύ������
     * @param requestMethod ����ʽ��GET��POST��
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
     */
    public static JSONObject httpsRequest(String requestUrl, String outputStr, String requestMethod) {
      JSONObject jsonObject = null;
      try {
        // ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
        TrustManager[] tm = {xtm};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // ������SSLContext�����еõ�SSLSocketFactory����
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL url = new URL(requestUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setSSLSocketFactory(ssf);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        // ��������ʽ��GET/POST��
        conn.setRequestMethod(requestMethod);
        // ��outputStr��Ϊnullʱ�������д����
        if (null != outputStr) {
          OutputStream outputStream = conn.getOutputStream();
          // ע������ʽ
          outputStream.write(outputStr.getBytes("UTF-8"));
          outputStream.close();
        }
        // ����������ȡ��������
        InputStream inputStream = conn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
          buffer.append(str);
        }
        // �ͷ���Դ
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        conn.disconnect();
        jsonObject = JSONObject.parseObject(buffer.toString());
      } catch (ConnectException ce) {
        System.out.println("���ӳ�ʱ��{"+ce+"}");
      } catch (Exception e) {
    	System.out.println("https�����쳣��{"+e+"}");
      }
      return jsonObject;
    }


    static X509TrustManager xtm = new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {

        }

        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {

        }
    };
}