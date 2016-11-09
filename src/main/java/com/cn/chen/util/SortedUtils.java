package com.cn.chen.util;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SortedUtils {

    /**
     * 插入排序 本质上 是将未排序的元素插入到已经排序的元素当中去   且只能交换相连的元素
     * @param arr
     * @return
     */
    public static  Comparable[] insertSort(Comparable[] arr){
        for (int i = 1; i < arr.length; i++) {
            for(int j=i;j>0&&less(arr[j],arr[j-1]);j--){
                exch(arr,j,j-1);
            }
        }
        return arr;
    }



    /**
     * 选择排序  其本质是将未排序数组中的最小的和围排序数组中的第一个数交换位置
     * @param arr
     * @return
     */
    public static Comparable[] sectionSort(Comparable[] arr){
        for (int i = 0; i < arr.length; i++) {
            int j=getMin(arr,i);
            //交换i+1和j;
            Comparable temp=arr[j];
            arr[j]=arr[i];
            arr[i]=temp;
        }
        return arr;
    }



    /**
     * 从第i个元素开始在数组中选取最小的元素index
     * @param arr
     * @param i
     * @return
     */
    public static int getMin(Comparable[] arr,int i){
        Comparable min =arr[i];
        int j=i;
        for ( ;i <arr.length ;i ++) {
            if(!less(min,arr[i])){
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

    /**
     * 交换comparable a中i和j元素的位置
     * @param a
     * @param i
     * @param j
     * @return
     */
    public static Comparable[] exch(Comparable[] a,int i,int j){
        Comparable temp=a[j];
        a[j]=a[i];
        a[i]=temp;
        return a;

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
