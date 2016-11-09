package com.cn.chen.util;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SortedUtils {
    /**
     * 选择排序  其本质是将未排序数组中的最小的和围排序数组中的第一个数交换位置
     * @param arr
     * @return
     */
    public static int[] sectionSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int j=getMin(arr,i);
            //交换i+1和j;
            int temp=arr[j];
            arr[j]=arr[i];
            arr[i]=temp;
        }
        return arr;
    }



    /**
     * 从第i个元素开始在数组中选取最小的元素
     * @param arr
     * @param i
     * @return
     */
    public static int getMin(int[] arr,int i){
        int min =arr[i];
        int j=i;
        for ( ;i <arr.length ;i ++) {
            if(min>arr[i]){
                min=arr[i];
                j=i;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        int[] arr=new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i]= (int) (Math.random()*100);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(sectionSort(arr)));
    }
}
