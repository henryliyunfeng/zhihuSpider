package spider;

import java.io.*;
import java.util.ArrayList;

public class Filewriter {
	/*
	 * ������filename="output.txt";
	 * ʵ�����ļ��ľ���·����:out/output.txt
	 */
	public static void writeIntoFile(String filename){
		//��Ҫ����һ���ļ���
		File dir=new File("out");
		dir.mkdirs();
		//�ٴ����ļ�
		File file=new File("out/"+filename);
		try{
			file.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("create \"out/output.txt file\" successed.");
		
		//���մ������ļ�����д����
		try {
			BufferedWriter outwriter=new BufferedWriter(new FileWriter(file));
			outwriter.write("��Һã������ҵĲ��͵�ַ��henryliyunfeng.github.io\n�Ҿ��ǰ����ڵ�������");
			outwriter.flush();
			outwriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * ������filename="output.txt";
	 * ArrayList<Zhihu> myZhihu����д�������
	 * ʵ�����ļ��ľ���·����:out/output.txt
	 */
	public static void writeIntoFile(String filename,ArrayList<Zhihu> myZhihu ){
			//��Ҫ����һ���ļ���
			File dir=new File("out");
			dir.mkdirs();
			//�ٴ����ļ�
			boolean isSuccess=false;
			File file=new File("out/"+filename);//ֻ�ǰ��ַ������ֻ��ɳ��������
			try{
				isSuccess=file.createNewFile();//�����ʵ�ʵĴ����ļ�
			}catch(Exception e){
				e.printStackTrace();
			}
			if(isSuccess){
				System.out.println("create \"out/output.txt file\" successed.");
			}
			else
				System.out.println("create file failed!Already had,we override it");
			
			//��myZhihu�����е�Ԫ�أ�����д��filename�ļ����档
			try {
				BufferedWriter outWriter=new BufferedWriter(new FileWriter(file));
				for(Zhihu item : myZhihu){
					outWriter.write(item.realToString());
				}
				outWriter.flush();//ˢ�»�����������д��
				outWriter.close();//д��֮��Ҫһ���ر��ļ�
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
