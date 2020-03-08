package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class BookMgr {

	BookSet book = new BookSet();	//创建图书对象
	private int lendCount = 0;		//已借出书籍数量
	private int existCount = 0;		//未借出书籍数量
    private long charge;			//每一次的暂存租金
    
	public BookSet getBook() {
		return book;
	}
	public int getLendCount() {
		return lendCount;
	}
	public int getExistCount() {
		return existCount;
	}
	public long getCharge() {
		return charge;
	}
	public void setCharge(long charge) {
		this.charge = charge;
	}
	
	/**
	 * 初始化四个图书
	 */
	public void initial() {
		book.name[0] = "数据结构";
		book.state[0] = 0;
		book.date[0] = "1997-7-1";
		book.count[0] = 12;
		
		book.name[1] = "数据库";
		book.state[1] = 1;
		book.count[1] = 14;
		
		book.name[2] = "Java手册";
		book.state[2] = 1;
		book.count[2] = 8;
		
		book.name[3] = "算法核心";
		book.state[3] = 1;
		book.count[3] = 4;
	}
	
	/**
	 * 开始菜单
	 */
	public void startMenu() {
		System.out.println("欢迎使用图书借阅系统");
		System.out.println("-------------------------------------");
		System.out.println("0. 退     出");     
		System.out.println("1.借出排行榜");	 
		System.out.println("2.新增图书");
		System.out.println("3.查看图书");
		System.out.println("4.删除图书");
		System.out.println("5.借出图书");
		System.out.println("6.归还图书");
		System.out.print("--------------------------------------\n");
		
		System.out.println("请选择：");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch(choice) {
			case 0:			//退出
				break;
			case 1:			
				list();
				returnMain();
				break;
			case 2:
				add();
				break;
			case 3:
				search();
		     	returnMain();
				break;
			case 4:
				delete();
				break;
			case 5:
				lend();
				break;
			case 6:
				returnbook();
				break;
			default:
				System.out.println("输入有误，请重新输入...");	
				returnMain();
		}
		input.close();		
	}
	
	/**
	 * 返回主菜单
	 */
	public void returnMain() {
		Scanner input = new Scanner(System.in);
		System.out.println("输入 0 返回：");
		if(input.nextInt() == 0) {
			startMenu();
		}
		else {
			System.out.println("输入错误, 异常终止！");
		}
		input.close();
	}
	
	/**
	 * 借出排行榜
	 * @return
	 */
	public String[] list() {
		//定义新数组，用来存放排序后图书信息
		String[] newname = new String[50];
		int[] newcount = new int[50];
		for(int k=0;k<book.name.length;k++) {
			newname[k] = book.name[k];
			newcount[k] = book.count[k];
		}
		
		//利用冒泡排序算法进行排序
		for(int i=0;i<newname.length-1;i++) {
			for(int j=i+1;j<newname.length;j++) {
				if(newname[j] == null) {
					break;
				}
				if(newcount[i] > newcount[j]) {
					int temp1 = newcount[i];
					newcount[i] = newcount[j];
					newcount[j] = temp1;
					
					String temp2 = newname[i];
					newname[i] = newname[j];
					newname[j] = temp2;
				}
			}
		}
		System.out.println("---> 排行榜\n");
    	System.out.println("**************************");
    	System.out.println("次数\t名称");
    	//显示排行榜信息
    	for(int i=newname.length-1;i>=0;i--) {
    		if(newname[i] != null) {
    			System.out.println(newcount[i]+"\t<<"+ newname[i]+ ">>" );
    		}
    	}
    	System.out.println("**************************");
     	return newname;
	}

	/**
	 * 新增图书
	 */
	public void add() {
		//为方便测试，拆分方法为键盘输入方法和核心追加书籍方法
		System.out.println("---> 新增图书\n");    	
		System.out.print("请输入图书名称： ");
		String name = getInputData();
		addBook(name);
		System.out.println("**************************");
	   	returnMain();
	}
	/**
	 * 键盘输入数据，用于新增图书和删除图书的输入
	 * @return
	 */
	private String getInputData() {
		Scanner input = new Scanner(System.in);
		String name = input.next();
		return name;
	}
	/**
	 * 追加图书
	 * @param naem
	 * @return
	 */
	public BookSet addBook(String name) {
		for(int i = 0; i < book.name.length; i++){   		 
	   		 if(book.name[i] == null){    	/*TODO 1.判断待插入信息在数组中位置*/
	    		//TODO 2.完成新增动作
	   			book.name[i] = name;
	   			book.state[i] = 1;
	   			book.count[i] = 0;
				System.out.println("新增《"+name+"》成功！");
				break;
			 }
		 }
		return book;
	}

	/**
	 * 查看图书
	 */
	public void search() {
		lendCount = 0;
		existCount = 0;
		System.out.println("---> 查看图书\n");
    	System.out.println("序号\t状 态\t名称\t\t借出日期");
    	//TODO 遍历图书信息并按顺序输出,序号从1开始，如果图书未借出，则借出日期用"----"表示，状态字段用"借出"和"未借出"标识
    	for(int i = 0; i < book.name.length; i++){   
			if(book.name[i] == null){	
				break;
			}
			else if(book.state[i] == 1){		
				System.out.println(i+1+"\t"+"未借出"+"\t"+book.name[i]+"\t\t"+"----");
				existCount++;
			}
			else if(book.state[i] == 0){		
				System.out.println(i+1+"\t"+"借出"+"\t"+book.name[i]+"\t\t"+book.date[i]);
				lendCount++;
			}
		}
		//TODO 打印已借出书籍，并设置lendCount
		System.out.println("已借出书籍数量："+lendCount);
    	//TODO 打印已未借出书籍，并设置existCount
    	System.out.println("未借出书籍数量："+existCount); 
	}

	/**
	 * 删除图书
	 */
	public void delete() {
		System.out.println("---> 删除图书\n");
    	System.out.print("请输入图书名称： ");
        String name = getInputData();
        //为方便测试用例，抽出一个方法
        deleteBook(name);
        returnMain();
	}
	/**
	 * 删除图书的核心方法
	 * @param name
	 */
	public void deleteBook(String name) {
		boolean flag = false;		//标识删除成功与否
		//遍历数组，查找匹配信息
		for(int i=0;i<book.name.length;i++) {
			if(book.name[i].equals(name) && book.state[i]==1){		/*TODO 判断书籍是否满足删除条件*/
      			/*TODO 完成删除操作*/
      			int j=i;
      			for(;j<book.name.length-1;j++) {
      				book.name[j] = book.name[j+1];
      				book.state[j] = book.state[j+1];
      				book.date[j] = book.date[j+1];
      				book.count[j] = book.count[j+1];
      			}
                //最后一个不为空的元素置空
                book.name[j]=null;
                book.date[j]=null;
				System.out.println("删除《"+name+"》成功！");
                flag=true;//置位，表示删除成功
                break;
      		}else if(book.name[i].equals(name) && book.state[i] == 0){		/*TODO 判断查询到的书籍已被借出*/
				System.out.println("《"+name+"》为借出状态，不能删除！"); 
                flag=true;//置位
                break;
            }
		}
		if(!flag){
			System.out.println("没有找到匹配信息！");
        }
		System.out.println("**************************");
	}
	
	/**
	 * 借出图书
	 */
	public void lend() {
		System.out.println("---> 借出图书\n");
	   	Scanner input = new Scanner(System.in);
	   	System.out.print("请输入图书名称： ");
	   	String want = input.next();  //要借出的图书名称 
	   	System.out.print("请输入借出日期（年-月-日）：");
	   	String lendDate = input.next();  //借出日期
	   	//为方便测试，抽出方法
	   	lendBook(want, lendDate);
	   	System.out.println("**************************");
	   	returnMain();
	}
	
	/**
	 * 借出图书的核心方法
	 * @param want
	 * @param lendDate
	 */
	public void lendBook(String want,String lendDate) {
		for(int i = 0; i < book.name.length; i++){   		 
			 if(book.name[i] == null){    //无匹配
				 System.out.println("没有找到匹配信息！");
				 break;
			 }else if(book.name[i].equals(want) && book.state[i]==1){ 	 /*TODO 判断匹配到可借书籍*/
				 /*TODO 补全借出操作*/
				 book.state[i] = 0;
				 book.date[i] = lendDate;
				 book.count[i]++;
				 System.out.println("《"+want+"》可以借出！");
				 break;
			 }else if(book.name[i].equals(want) && book.state[i]==0){  /*TODO 判断查询到的书籍已被借出*/
				 System.out.println("《"+want+"》已被借出！");
				 break;
			 }   		 
		 }
	}

	/**
              * 归还图书
     */
    public void returnbook() {
        System.out.println("---> 归还图书\n");
        Scanner input = new Scanner(System.in);
        System.out.print("请输入图书名称： ");
        String want = input.next();
        System.out.print("请输入归还日期（年-月-日）：");
        String redate = input.next();
        //为方便测试，抽出方法
        returnbook(want, redate);
        System.out.println("**************************");
        returnMain();
    }
    /**
             * 归还图书并计算租金
     */
    public void returnbook(String want, String redate) {
        //初始化租金
        this.setCharge(0);
        for (int i = 0; i < book.name.length; i++) {
            if (book.name[i] == null) {    //无匹配
                System.out.println("没有找到匹配信息！");
                break;
			 }else if(book.name[i].equals(want) && book.state[i] == 0){  //找到匹配	 
                /*补全归还操作 1.设置为未借出状态 2.计算租金*/
				book.state[i] = 1;
                System.out.println("\n归还《" + want + "》成功!");
                System.out.println("借出日期为：" + book.date[i]);
                System.out.println("归还日期为：" + redate);
                System.out.println("应付租金（元）：" + charge(book.date[i],redate));
                break;
			 }else if(book.name[i].equals(want) && book.state[i] == 1){ 		//找到匹配但没有借出  
                System.out.println("该图书没有被借出！无法进行归还操作。");
                break;
            }
        }
    }
    /**
              * 计算日期差
     *
     * @param dstr1 第一个日期
     * @param dstr2 第二个日期
     */
    public long charge(String dstr1, String dstr2) {
        long charge = 0;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = sd.parse(dstr1);
            Date d2 = sd.parse(dstr2);
            charge = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
            this.setCharge(charge);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return charge;
    }
}
