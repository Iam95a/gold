package com.cn.chen.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.awt.print.Pageable;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/12/6.
 */
public class HttpUtils {
    public static ConcurrentLinkedQueue<String> queue1=new ConcurrentLinkedQueue();
    public static Set<String> set1 = new HashSet();
    public static String get(String path) {
        String result = null;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(path);
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity);
                EntityUtils.consume(httpEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String post(String path, Map<String, String> param) {
        String result = null;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(path);
            if (param != null) {
                Set<String> strings = param.keySet();
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (String string : strings) {
                    nvps.add(new BasicNameValuePair(string, param.get(string)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            }
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity);
                EntityUtils.consume(httpEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getWithProxy(String path) {
        String result = null;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(path);
            httpGet.setHeader("Cookie", "__cfduid=d926287a3cbbc5e0d6203f47b224c58981481158875; PHPSESSID=gacjbijuss9q9j8m6s3cerj842; CNZZDATA950900=cnzz_eid%3D1601098015-1481155550-http%253A%252F%252Fwww.t66y.com%252F%26ntime%3D1481177150");
            HttpHost proxy = new HttpHost("127.0.0.1", 8087, "http");
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            httpGet.setConfig(config);
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity, Charset.forName("gb2312"));
                EntityUtils.consume(httpEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //获取帖子页面
    public static void get1024(int page){
        String string = getWithProxy("http://www.t66y.com/thread0806.php?fid=16&page="+ page);
        String rex = "htm_data/\\d{2}/\\d{4}/\\d{7}\\.html";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(string);
        System.out.println(string);

        while (matcher.find()) {
            queue1.add("http://www.t66y.com" + matcher.group());
        }
    }
    //获取帖子详细
    public static void get10241(){
        try {

            while (!queue1.isEmpty()) {
                String path = queue1.poll();
                String string1 =
                        getWithProxy(path);
                String regex = "<input src='http://.*?\\.[JPjp][PNpn][EGeg][Gg]?";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(string1);
                Set<String> set = new HashSet();
                while (m.find()) {
                    set.add(m.group().substring(12));
                }
                for (String s : set) {
                    downByUrl(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String string = getWithProxy("http://www.t66y.com/thread0806.php?fid=16");
        String rex = "htm_data/\\d{2}/\\d{4}/\\d{7}\\.html";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(string);
        System.out.println(string);
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
        while (matcher.find()) {
            queue.add("http://www.t66y.com" + matcher.group());
        }
        Set<String> set = new HashSet();

        while (!queue.isEmpty()) {
            String path = queue.poll();
            String string1 = getWithProxy(path);
            String regex = "http://.*?\\.[JPjp][PNpn][EGeg][Gg]?";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(string1);
            while (m.find()) {
                set.add(m.group());
//                System.out.println(m.group());
            }
        }
        for (String s : set) {
            downByUrl(s);
        }

    }
    public static void downByUrl(String path){

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(path);
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {

                HttpEntity httpEntity = response.getEntity();
                if(httpEntity.isStreaming()){
                    byte[] brr=EntityUtils.toByteArray(httpEntity);
                    int index=path.lastIndexOf("/");
                    String lastPath=path.substring(0,path.lastIndexOf(".")).replaceAll("/","");
                    lastPath=lastPath.replaceAll(":","");
                    lastPath=lastPath.replaceAll(".","");
                    String fileName=path.substring(index+1);
                    String folder="F:\\1024\\"+ lastPath;
                    File file =new File(folder);
                    if(!(file.exists()||file.isDirectory())){
                        file.mkdirs();
                    }
                    FileOutputStream fileOutputStream=new FileOutputStream("F:\\1024\\"+ lastPath+"\\"+fileName);
                    fileOutputStream.write(brr);
                }
                EntityUtils.consume(httpEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
