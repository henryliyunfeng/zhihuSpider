package spider;

/*
 * 使用爬虫来获取知乎发现页面上的内容
 */
import java.util.ArrayList;  

public class Main {  
  
    public static void main(String[] args) {  
    	//知乎的发现页面
    	String url="https://www.zhihu.com/explore";
        // 访问链接并获取页面内容  
        String content = Spider.ReadPage(url);  
        // 获取该页面的所有的知乎对象  
        ArrayList<Zhihu> myZhihu = Spider.GetZhihu(content);  

        // 打印结果  
        System.out.println(content);
        System.out.println(myZhihu);
        
    }  
}  