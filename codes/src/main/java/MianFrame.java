import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MianFrame extends JFrame implements KeyListener, ActionListener {

    int[][] datas =new int[4][4];
    int loseflag=1;

    int score =0;

    JButton rbutton;

    public void initData(){
        generatorNum();
        generatorNum();
    }



    public MianFrame(){
        initFrame();
        //初始化界面
        initData();
        paintView();
        //为窗体添加键盘监听事件
        this.addKeyListener(this);
        setVisible(true);
    }


    /**
     * 此方法用于窗体初始化
     */
    public void initFrame(){

        setTitle("2048小游戏");
        setSize(514,538);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(3);
        setLayout(null);
    }

    /**
     * 此方法用于绘制界面
     */
    public void paintView(){

        //移除掉，界面中所有的内容
        getContentPane().removeAll();

        if(loseflag==2){
            JLabel label = new JLabel(new ImageIcon("E:\\code\\2048\\gameOver.png"));
            label.setBounds(90,90,334,228);
            getContentPane().add(label);
            rbutton = new JButton(new ImageIcon("E:\\code\\2048\\restart.png"));;
            rbutton.setBounds(120,220,222,50);
            getContentPane().add(rbutton);
            rbutton.addActionListener(this);
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                JLabel jl = new JLabel(new ImageIcon("E:\\code\\2048\\"+datas[i][j]+".png"));
                jl.setBounds(50+100*j,50+100*i,100,100);
                getContentPane().add(jl);
            }
        }

        JLabel background = new JLabel(new ImageIcon("E:\\code\\2048\\background.png"));
        background.setBounds(40,40,420,420);
        getContentPane().add(background);

        JLabel scoreLable =new JLabel("得分："+score);
        scoreLable.setBounds(50,20,100,20);
        getContentPane().add(scoreLable);

        //刷新界面的方法
        getContentPane().repaint();
    }

    /**
     * 此方法用于初始化窗体，所有窗体的相关设置都在这个方法中实现
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /*
    键盘被按下时，所触发的方法，在这个方法中区分出上下左右按钮
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if(keycode==37){
            //调用左移动的方法
            moveToLeft(1);
            generatorNum();
        } else if (keycode==38) {
            //上移动
            moveToUp(1);
            generatorNum();
        } else if (keycode==39) {
            //右移
            moveToRight(1);
            generatorNum();
        } else if (keycode==40) {
            //下移
            moveToBottom(1);
            generatorNum();
        } else{
            return;
        }
        //每一次移动的逻辑执行完，都要去检查游戏是否结束
        check();
        //重新绘制界面
        paintView();
    }

    public void check(){
        if(checkLeft()==false &&checkRight()==false&&checkTop()==false&&checkBottom()==false){
            //失败
            loseflag=2;
        }
    }
    public void copyArray(int[][] src,int[][] des){
        for (int i=0;i<src.length;i++){
            for (int j=0;j< src.length;j++){
                des[i][j]=src[i][j];
            }
        }
    }

    public boolean checkLeft(){
        //1.创建新数组，用于备份原始数组
        int[][] newArr=new int[4][4];
        //2.将原始数据拷贝到新数组
        copyArray(datas,newArr);
        //3.调用左移动方法。对原始数据进行左移
        moveToLeft(2);
        //4.使用移动后的数据，和备份的数据进行比对，并使用flag变量进行记录
        //true  为可以移动  falsa为不可移动
        boolean flag=false;
        lo:
        for (int i=0;i<datas.length;i++){
            for (int j=0;j< datas.length;j++){
                if(datas[i][j]!=newArr[i][j]){
                    //主要元素有一个不相同的，就代表数据可移动
                    flag=true;
                    break lo;
                }
            }
        }
        copyArray(newArr,datas);
        return flag;
    }
    public boolean checkRight(){
        //1.创建新数组，用于备份原始数组
        int[][] newArr=new int[4][4];
        //2.将原始数据拷贝到新数组
        copyArray(datas,newArr);
        //3.调用右移动方法。对原始数据进行左移
        moveToRight(2);
        //4.使用移动后的数据，和备份的数据进行比对，并使用flag变量进行记录
        //true  为可以移动  falsa为不可移动
        boolean flag=false;
        lo:
        for (int i=0;i<datas.length;i++){
            for (int j=0;j< datas.length;j++){
                if(datas[i][j]!=newArr[i][j]){
                    //主要元素有一个不相同的，就代表数据可移动
                    flag=true;
                    break lo;
                }
            }
        }
        copyArray(newArr,datas);
        return flag;
    }
    public boolean checkTop(){
        //1.创建新数组，用于备份原始数组
        int[][] newArr=new int[4][4];
        //2.将原始数据拷贝到新数组
        copyArray(datas,newArr);
        //3.调用上移动方法。对原始数据进行左移
        moveToUp(2);
        //4.使用移动后的数据，和备份的数据进行比对，并使用flag变量进行记录
        //true  为可以移动  falsa为不可移动
        boolean flag=false;
        lo:
        for (int i=0;i<datas.length;i++){
            for (int j=0;j< datas.length;j++){
                if(datas[i][j]!=newArr[i][j]){
                    //主要元素有一个不相同的，就代表数据可移动
                    flag=true;
                    break lo;
                }
            }
        }
        copyArray(newArr,datas);
        return flag;
    }
    public boolean checkBottom(){
        //1.创建新数组，用于备份原始数组
        int[][] newArr=new int[4][4];
        //2.将原始数据拷贝到新数组
        copyArray(datas,newArr);
        //3.调用左移动方法。对原始数据进行左移
        moveToBottom(2);
        //4.使用移动后的数据，和备份的数据进行比对，并使用flag变量进行记录
        //true  为可以移动  falsa为不可移动
        boolean flag=false;
        lo:
        for (int i=0;i<datas.length;i++){
            for (int j=0;j< datas.length;j++){
                if(datas[i][j]!=newArr[i][j]){
                    //主要元素有一个不相同的，就代表数据可移动
                    flag=true;
                    break lo;
                }
            }
        }
        copyArray(newArr,datas);
        return flag;
    }

    public void moveToBottom(int flag) {
        //1.先逆时针旋转
        clockwise();
        //2.左移
        moveToLeft(1);
        //3，逆时针旋转
        anticlockwise();
    }

    public void moveToUp(int flag) {
        //1.先逆时针旋转
        anticlockwise();
        //2.左移
        moveToLeft(1);
        //3，逆时针旋转
        clockwise();
    }

    public void clockwise() {
        int[][] newArr=new int[4][4];
        for (int i=0;i< 4;i++){
            for (int j=0;j< 4;j++){
                newArr[j][3-i]=datas[i][j];
            }
        }
        datas=newArr;
    }

    public void anticlockwise() {
        int[][] newArr=new int[4][4];
        for (int i=0;i< 4;i++){
            for (int j=0;j< 4;j++){
                newArr[3-j][i]=datas[i][j];
            }
        }
        datas=newArr;
    }

    private void moveToRight(int flag) {
        //1.对一维数组进行反装
        horizontalSwap();
        //2.数组左移
        moveToLeft(1);
        //3.对一维数组再次反转
        horizontalSwap();
    }

    public void horizontalSwap() {
        for (int i=0;i< datas.length;i++){
            reverseArray(datas[i]);
        }
    }

    public void reverseArray(int[] datas) {
        for(int star=0,end=datas.length-1;star< end;star++,end--){
            int temp = datas[star];
            datas[star]=datas[end];
            datas[end]=temp;
        }
    }

    public void moveToLeft(int flag) {

        //对于datas中的每一行来说
        for (int i=0;i< datas.length;i++){
            int [] newArr = new int[4];
            int index =0;

            for (int j=0;j< datas.length;j++){
                if(datas[i][j]!=0){
                    newArr[index]=datas[i][j];
                    index++;
                }
            }

            datas[i]=newArr;

            /**
             * 另一种方法，移动法
             */
//        for (int i=0;i< datas.length;i++){
//
//            for (int j=0;j< datas.length-1;j++){
//                if(datas[i][j]==0){
//                    for (int z=j;z< datas.length-1;z++){
//                        datas[i][z]=datas[i][z+1];
//                    }
//                    datas[i][3]=0;
//                }
//            }



            //对于每一行中的数字来说
            for(int x=0;x<3;x++){
                if(datas[i][x]==datas[i][x+1]){
                    datas[i][x]=datas[i][x]*2;
                    if(flag==1){
                        //计算得分
                        score+=datas[i][x];
                    }
                    //如果有相同数字，合并后将剩余数据前移
                    for (int y=x+1;y<3;y++){
                        datas[i][y]=datas[i][y+1];
                    }
                    datas[i][3]=0;
                }
            }
        }
    }

    /*
    键盘被松开时候，所触发的方法
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void generatorNum(){
        //1.创建两个数组，准备记录二维数组中空白格子i和j的索引位置
        int[] arrayI={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        int[] arrayJ={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        int w=0;
        //2.遍历二维数组，取出每一个元素，并判断当前元素是否是空白格子
        for (int i=0;i< datas.length;i++){
            for (int j=0;j< datas.length;j++){
                //3.是0的话，将索引存入arrayI和arrayJ
                if (datas[i][j]==0){
                    arrayI[w]=i;
                    arrayJ[w]=j;
                    w++;
                }
            }
        }

        //4.如果w变量记录不是0,代表数组中还有空白位置，还可以产生新的数字方块
        if(w!=0){
            Random r = new Random();
            int index = r.nextInt(w);
            int x=arrayI[index];
            int y=arrayJ[index];
            datas[x][y]=2;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==rbutton){
            datas =new int[4][4];
            loseflag=1;
            score =0;
            new MianFrame();
        }

    }
}
