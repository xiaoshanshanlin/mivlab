package gitrepostats;

import java.io.File;
import java.util.Scanner;

import com.wm.utils.DbConn;

import utils.Dbhelper;

public class ReadCSV2 {
	 public static void main(int tid,String org,String proj,String rootpath){
			//δ��װ��CSV�������ݿ⺯��
					//�������ݿ��������
					DbConn db=Dbhelper.getDb();
					long startTime = System.currentTimeMillis();
					
					//File file1 = new File("E:/installBlock/eclipseEE/eclipse-jee-oxygen-2-win32-x86_64/eclipse/src2018Android/json/result.csv");
					//System.out.print(file1);
					File file = new File(rootpath+"/github/"+org+"/"+proj+"/src/json/result.csv");
					System.out.print(file);
					try {
					Scanner in = new Scanner(file);
			 		
					System.out.println("���ݿ����ӳɹ�");
					//�жϱ��Ƿ���ڣ��������򴴽���,ÿ����ʦһ����
					String tablename="outreposdate";
					
					//dao.CreateTable.main(tablename);
					//¼������
					in.next();
					while (in.hasNext())
					{
						String temp1 = in.nextLine();		//���Ե�һ��
						String[] temp = temp1.split(",");
						
						//System.out.println("temp="+temp);
						if (temp.length < 14)
							continue;
			 
						if (temp.length == 14)
						{	
							boolean bl=db.checkTrue("select id from "+tablename+" where Num='"+temp[1]+"' and Login='"+temp[0]+"' and org='" +org+"' and proj='"+proj+"' and tid="+tid);
							if(bl){
								System.out.print("�ļ�¼�Ѵ��ڣ�ִ�и��²���");
								System.out.println();
								int bls=db.executeUpdate("update "+ tablename+" set Login='"+temp[0]+"',IssueNumber='"+temp[2]+"',IssueCount='"+temp[3]+"',IssueLabels='"+temp[4]+"',Events='"+temp[5]+"',FirstTime='"+temp[6]+"',Pulls="+temp[7]+",Commits="+temp[8]+",Additions="+temp[9]+",Deletions="+temp[10]+",ChangedFiles="+temp[11]+",Comments="+temp[12]+",ReviewComments="+temp[13]+"  where Num='"+temp[1]+"' and Login='"+temp[0]+"' and org='" +org+"' and proj='"+proj+"' and tid="+tid);
								
							}else {
								System.out.print("ִ�в������ݲ���");
								System.out.println();
								int bls=db.executeUpdate("insert into "+tablename+" (Login,Num,IssueNumber,IssueCount,IssueLabels,Events,FirstTime,Pulls,Commits,Additions,Deletions,ChangedFiles,Comments,ReviewComments,org,proj,tid) values('"+temp[0]+"','"+temp[1]+"','"+temp[2]+"','"+temp[3]+"','"+temp[4]+"','"+temp[5]+"','"+temp[6]+"','"+temp[7]+"','"+temp[8]+"','"+temp[9]+"','"+temp[10]+"','"+temp[11]+"','"+temp[12]+"',"+temp[13]+",'"+org+"','"+proj+"',"+tid+")");
								
							}
						}
					}
						

			 
					long EndTime = System.currentTimeMillis();
					long time = (EndTime - startTime) / 1000;
			 
					System.out.println("�������ݹ���ʱ��" + time);
					in.close();
				 }catch (Exception ex) {			 
					      System.out.print("��ȡ���ݳ���");
				 }
					 
			        
			 }
		}