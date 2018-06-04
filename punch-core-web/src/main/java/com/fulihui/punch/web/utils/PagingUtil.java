package com.fulihui.punch.web.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhi
 */
public class PagingUtil {
    /**
     * 数据集合的分页方法，根据传入总共的数据跟页码，返回页码所需要显示多少条的数据
     * <BR/>采用泛型的方法，即为，list中为什么类型的数据就返回什么类型的数据
     * @param f 带有需要进行分页的数据集合
     * @param pageNo 第几页
     * @param dataSize 显示多少条数据
     * @return 进过分页之后返回的数据
     */
    public static <F> List<F> dataPaging(List<F> f, int pageNo, int dataSize) {
        /*
        * 经过测试发现当pageNo为0或者小于时，也就是第0页时，程序会报错，所以需要处理一下pageNo的值
        *
        * 先进行空值的判断，避免程序出现null异常
        *
        * 当pageNo的值小于等于0时，我们让它的值为1
        */
        //参数的校验         //当传入过来的list集合为null时，先进行实例化
        if (f == null) {
            f = new ArrayList<F>();
        }
        //当传入过来的pageNo为null时，先进行赋值操作
        //当传入过来的dataSize为null时，先进行赋值操作
        if (pageNo <= 0) {
            pageNo = 1;
        }

        //记录一下数据一共有多少条
        int totalitems = f.size();
        //实例化一个接受分页处理之后的数据
        List<F> afterList = new ArrayList<F>();
        /*
        * 进行分页处理,采用for循环的方式来进行处理
        *
        * 首先for循环中，i应该从哪里开始:i应该从 (当前是第几页 -1 乘以 条数) 开始
        *
        * 然后for循环应该到哪里结束，也就是i应该小于:判断(开始的索引+显示条数)是不是大于总条数，如果大于就是总条数，如果小于就是(开始的索引+显示条数)
        *
        * 然后让i++
        */

        for (int i = (pageNo - 1) * dataSize; i < (((pageNo - 1) * dataSize) + dataSize > totalitems
            ? totalitems
            : ((pageNo - 1) * dataSize) + dataSize); i++) {
            //然后将数据存入afterList中
            afterList.add(f.get(i));
        }

        //然后将处理后的数据集合进行返回
        return afterList;
    }
}
