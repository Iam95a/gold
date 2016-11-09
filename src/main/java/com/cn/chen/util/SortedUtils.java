package com.cn.chen.util;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SortedUtils {

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static  Comparable[] insertSort(Comparable[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >=0 ; j--) {
                if((j>0&&less(arr[i],arr[j])&&less(arr[j-1],arr[i]))||(j==0&&less(arr[i],arr[j]))) {
                    Comparable temp = arr[i];
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }



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

    /**
     * a<=b返回true 反之返回false
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a,Comparable b){
        return a.compareTo(b)<=0;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[100];
        for (int i = 0; i < 100; i++) {
            arr[i]= (int) (Math.random()*100);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(insertSort(arr)));
    }
}
