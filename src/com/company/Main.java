package com.company;
import java.util.Date;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int i=0,right=0,a;
        double percent;
        Random random = new Random();
        int n = random.nextInt(20)%(11) + 10;
        System.out.println("The number of Calculation problems: "+n);
        for(i=0;i<n;i++)
        {
            a=takeTest();
            right=right+a;
        }

        System.out.println("The number of right: "+right);
        percent=((double)right*100.00)/(double)n;
        System.out.println("The percent of right:"+String.format("%.2f", percent)+"%!");
    }
    public static int maxDivisor(int u, int v)
    {
        int t;
        u=Math.abs(u);
        v=Math.abs(v);
        if(v>u)
        {
            t=u;
            u=v;
            v=t;
        }
        while(v!=0)
        {
            t=u%v;
            u=v;
            v=t;
        }
        return u;
    }
    public static int minMultiple(int u, int v)
    {
        u=Math.abs(u);
        v=Math.abs(v);
        return u*v/maxDivisor(u,v);
    }
    public static char getSignal(){
        char signal[]={'+','-','*','/'};
        Random r = new Random();
        r.setSeed(new Date().getTime());
        int x=(int)(Math.random()*4);
        return signal[x];
    }
    public static Double getResult(double number1,double number2,char signal){
        double result=0;
        switch (signal)
        {
            case '+':
                result=number1+number2;break;
            case '-':
                result=number1-number2;break;
            case '*':
                result=number1*number2;break;
            case '/':
                result=number1/number2;break;
            default:
                System.out.println("运算符错误！\n");
        }
        return result;
    }
    public static String getResult1(int number1,int number2,int number3,int number4,char signal){
        String result = null;
        int x1,x2,r1,r2;//x1为分母的最小公倍数，x2为最大公约数，r1为得数的分子，r2为得数的分母
        int res,mol,Den;//mol为分子,Den为分母;
        x1=minMultiple(number2,number4);
        x2=maxDivisor(number2,number4);
        switch (signal)
        {
            case '+':
                r1=number1*x1/number2+number3*x1/number4;
                r2=x1;
                if(maxDivisor(r1,r2)==1){
                        result=r1+"/"+r2;
                }
                else{
                    mol=r1/maxDivisor(r1,r2);
                    Den=r2/maxDivisor(r1,r2);
                    if(Den==1)
                        result=mol+"";
                    else
                        result=mol+"/"+Den;
                }
                break;
            case '-':
                r1=number1*x1/number2-number3*x1/number4;
                r2=x1;
                if(r1==0)
                    result=0+"";
                else {
                    if (maxDivisor(r1, r2) == 1) {
                        result = r1 + "/" + r2;
                    }
                    else {
                        mol = r1 / maxDivisor(r1, r2);
                        Den = r2 / maxDivisor(r1, r2);
                        if (Den == 1)
                            result = mol + "";
                        else
                            result = mol + "/" + Den;
                    }
                }
                break;
            case '*':
                r1=number1*number3;
                r2=number2*number4;
                if(r1==0)
                    result=0+"";
                else{
                    mol=r1 / maxDivisor(r1, r2);
                    Den = r2 / maxDivisor(r1, r2);
                    if(Den==1)
                        result=mol+"";
                    else
                        result=mol + "/" + Den;
                }
                break;
            case '/':
                r1=number1*number4;
                r2=number2*number3;
                if(r1==0)
                    result=0+"";
                else{
                    mol=r1 / maxDivisor(r1, r2);
                    Den = r2 / maxDivisor(r1, r2);
                    if(Den==1)
                        result=mol+"";
                    else
                        result=mol + "/" + Den;
                }
                break;
            default:
                System.out.println("运算符错误！\n");
        }
        return result;
    }
    public static int takeTest(){
        double  get=0,result=0;
        int number1,number2,number3,number4;
        int flag=0;
        char signal;
        String get1,result1;
        Random r = new Random();
        r.setSeed(new Date().getTime());
        signal=getSignal();
        number1=(int)(Math.random()*10);
        number2=(int)(Math.random()*10)+1;
        number3=(int)(Math.random()*10)+1;
        number4=(int)(Math.random()*10)+1;
        while(number1>=number2 || maxDivisor(number1,number2)!=1){
            number1=(int)(Math.random()*10);
        }
        while(number3>=number4 || maxDivisor(number3,number4)!=1){
            number3=(int)(Math.random()*10);
        }
        if(number2==1&&number4==1){
            System.out.print(number1+" "+signal+" "+number3+"=");
            Scanner a=new Scanner(System.in);
            get=a.nextInt();
            if(signal=='/'){

                result=Double.valueOf(String.format("%.2f", getResult(number1,number3,signal))).doubleValue();
            }
            else
                result=getResult(number1,number3,signal);

            if(get==result){
                System.out.println("You're right!");
                flag=1;
            }
            else{
                System.out.println("You're wrong!");
                System.out.println("The right answer is:"+result);
                flag=0;
            }
        }
        else{
            if(number2==1&&number4!=1)
                System.out.print(number1+" "+signal+" "+number3+"/"+number4+"=");
            else if(number2!=1&&number4==1)
                System.out.print(number1+"/"+number2+" "+signal+" "+number3+"=");
            else if(number2!=1&&number4!=1)
                System.out.print(number1+"/"+number2+" "+signal+" "+number3+"/"+number4+"=");
            Scanner a=new Scanner(System.in);
            get1=a.next();
            result1=getResult1(number1,number2,number3,number4,signal);

            if(get1.equals(result1)==true){
                System.out.println("You're right!");
                flag=1;
            }
            else{
                System.out.println("You're wrong!");
                System.out.println("The right answer is:"+result1);
                flag=0;
            }
        }

        return flag;

    }
}
