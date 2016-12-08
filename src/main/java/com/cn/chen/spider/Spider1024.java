package com.cn.chen.spider;

import com.cn.chen.util.HttpUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016/12/6.
 */
public class Spider1024 {
    //    public static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
    public static void main(String[] args) {
        firstStep("1");


//        String string1 = HttpUtils.getWithProxy("http://www.t66y.com/htm_data/16/1612/2146123.html");
//        String regex = "<input src='http://.*?\\.[JPjp][PNpn][EGeg][Gg]?";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(string1);
//        Set<String> set = new HashSet();
//        while (m.find()) {
//            set.add(m.group().substring(12));
//
//        }
//        for (String s : set) {
//            HttpUtils.downByUrl(s);
//        }
//        HttpUtils.get1024(1);
//        HttpUtils.get10241();
//        HttpUtils.downByUrl("http://ipoock.com/img/g1/20161124235826057na.jpeg");
    }

    public static void firstStep(String page) {
        String string = HttpUtils.getWithProxy("http://www.t66y.com/thread0806.php?fid=16&page=" + page);
        String rex = "htm_data/\\d{2}/\\d{4}/\\d{7}\\.html";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            secondStep("http://www.t66y.com/" + matcher.group());
        }
    }

    public static void secondStep(String path) {
        Set<String> set = new HashSet();
        String string1 = HttpUtils.getWithProxy(path);
        String regex = "<input src='http://.*?\\.[JPGgjp][PNIipn][EGFfeg][Gg]?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string1);
        while (m.find()) {
            set.add(m.group().substring(12));
        }
        for (String s : set) {
            HttpUtils.downByUrl(s);
        }
    }
}
