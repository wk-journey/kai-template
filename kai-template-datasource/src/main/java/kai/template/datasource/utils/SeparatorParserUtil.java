package kai.template.datasource.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangkai on 2018/9/9.
 */
public class SeparatorParserUtil {
    /**
     * 根据指定分隔符拆分字符串
     * @param original      原始字符串
     * @param separator0    一级分隔符
     * @param separator1    二级分隔符
     * @return
     */
    public static List<SplitItem> split2List(String original, String separator0, String separator1){
        ArrayList<SplitItem> items = Lists.newArrayList();
        List<String> list = split2List(original, separator0);
        if(CollectionUtils.isNotEmpty(list)){
            for (String ele : list) {
                SplitItem item = split2Item(ele, separator1);
                items.add(item);
            }
        }
        return items;
    }

    /**
     * 根据指定分隔符拆分字符串
     * @param original      原始字符串
     * @param separator     分隔符
     * @return
     */
    public static List<String> split2List(String original, String separator){
        if(StringUtils.isBlank(original) || StringUtils.isBlank(separator)){
            throw new IllegalArgumentException("Invalid original or separator");
        }
        List<String> list = Lists.newArrayList();
        if(StringUtils.contains(original,separator)){
            String[] array = StringUtils.split(original, separator);
            list.addAll(Arrays.asList(array));
        }else{
            list.add(original);
        }
        return list;
    }

    /**
     * 根据指定分隔服拆分字符串
     * @param original          原始字符串
     * @param separator         分隔符
     * @return
     */
    public static  SplitItem split2Item(String original, String separator){
        List<String> list = split2List(original, separator);
        if(CollectionUtils.isNotEmpty(list) && list.size() == 2){
            SplitItem item = new SplitItem();
            item.setFront(list.get(0));
            item.setBehind(list.get(1));
            return item;
        }
        return null;
    }

    public static class SplitItem{
        String front;
        String behind;

        public SplitItem() {
        }

        public SplitItem(String front, String behind) {
            this.front = front;
            this.behind = behind;
        }

        public String getFront() {
            return front;
        }

        public void setFront(String front) {
            this.front = front;
        }

        public String getBehind() {
            return behind;
        }

        public void setBehind(String behind) {
            this.behind = behind;
        }
    }

    public static void main(String[] args) {
        String hosts = "localhost:8080,192.168.1.99:9999,192.168.1236:8081";
        String separator = ",";
        List<String> list = split2List(hosts, separator);
        System.out.println(Arrays.toString(list.toArray()));

        String str = "localhost:8080";
        String separator0 = ":";
        SplitItem hostItem = split2Item(str, separator0);
        System.out.println(hostItem.getFront());

    }
}
