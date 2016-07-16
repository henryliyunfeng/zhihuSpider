package spider;

import java.io.*;
import java.util.ArrayList;

public class Filewriter {
	/*
	 * 参数：filename="output.txt";
	 * 实际上文件的绝对路径是:out/output.txt
	 */
	public static void writeIntoFile(String filename){
		//先要创建一个文件夹
		File dir=new File("out");
		dir.mkdirs();
		//再创建文件
		File file=new File("out/"+filename);
		try{
			file.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("create \"out/output.txt file\" successed.");
		
		//往刚创建的文件里面写数据
		try {
			BufferedWriter outwriter=new BufferedWriter(new FileWriter(file));
			outwriter.write("大家好，这是我的博客地址：henryliyunfeng.github.io\n我就是爱折腾的李昀峰");
			outwriter.flush();
			outwriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 参数：filename="output.txt";
	 * ArrayList<Zhihu> myZhihu即将写入的内容
	 * 实际上文件的绝对路径是:out/output.txt
	 */
	public static void writeIntoFile(String filename,ArrayList<Zhihu> myZhihu ){
			//先要创建一个文件夹
			File dir=new File("out");
			dir.mkdirs();
			//再创建文件
			boolean isSuccess=false;
			File file=new File("out/"+filename);//只是把字符串名字换成抽象的名字
			try{
				isSuccess=file.createNewFile();//这才是实际的创建文件
			}catch(Exception e){
				e.printStackTrace();
			}
			if(isSuccess){
				System.out.println("create \"out/output.txt file\" successed.");
			}
			else
				System.out.println("create file failed!Already had,we override it");
			
			//将myZhihu数组中的元素，依次写入filename文件里面。
			try {
				BufferedWriter outWriter=new BufferedWriter(new FileWriter(file));
				for(Zhihu item : myZhihu){
					outWriter.write(item.realToString());
				}
				outWriter.flush();//刷新缓冲区，立即写入
				outWriter.close();//写完之后要一定关闭文件
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
