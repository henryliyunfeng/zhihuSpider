package spider;

/*
 * ʹ����������ȡ֪������ҳ���ϵ�����
 */
import java.util.ArrayList;  

public class Main {  
  
    public static void main(String[] args) {  
    	//֪���ķ���ҳ��
    	String url="https://www.zhihu.com/explore";
        // �������Ӳ���ȡҳ������  
        String content = Spider.ReadPage(url);  
        // ��ȡ��ҳ������е�֪������  
        ArrayList<Zhihu> myZhihu = Spider.GetZhihu(content);  

        // ��ӡ���  
        System.out.println(content);
        System.out.println(myZhihu);
        
    }  
}  