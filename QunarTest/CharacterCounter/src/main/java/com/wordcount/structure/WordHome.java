package com.wordcount.structure;

/**
 * Created by admin on 2017/1/9.
 */
public class WordHome {
    private byte[][] rooms = new byte[255][];//各个堆,按照需要填充,内部数组每一个大小为1024
    private byte[] top  = null;//顶级堆
    private byte size = 2,maxSize = 2;//初始化单词数量所字节数,单词固定占10位
    private byte mask = (byte)63;
    private byte maskLength = 6;
    private int characterNum = 0;//字母数量
    private int numberNum = 0;//数字数量
    private int chineseNum = 0;//汉字数量
    private int punctuationNum = 0;//标点符号数量

    public byte getSize() {
        return size;
    }

    public void setSize(byte size) {
        this.size = size;
    }

    public void initRooms(int n){//初始化room堆，需要指出初始化哪个堆
        if(rooms[n]==null||rooms[n].length<=0){
            rooms[n] = new byte[1024];
        }

    }

    public void initHeap(byte[] context,int len,byte size,byte mask){//初始化为一个大根堆
        int pos = 0;
       // System.out.println(len+":"+size);
        for(int i = ((len+1)>>1)-1;i >= 0;i--){
            sort(context,i,len,size,mask);
        }
    }

    public void put(char word,int num){//添加字符
        boolean isOut =  false;//是否越界
        int pos = word/512;//第几个room
        int inerPos = word%512;//第几个字符
        if((word>=97&&word<=122)||(word>=65&&word<=90)){//判断是否是字母
            characterNum++;
        }else if((word>=19968&&word<=40907)||(word>=13312&&word<=19893)||(word>=131072&&word<=173782)||
                (word>=173824&&word<=178205)||(word>=12032&&word<=12245)||(word>=11904&&word<=12019)||
                (word>=63744&&word<=64217)||(word>=194560&&word<=195101)||(word>=59413&&word<=59503)||
                (word>=58358&&word<=58856)||(word>=58880&&word<=59087)||(word>=12736&&word<=12771)||
                (word>=12272&&word<=12283)||(word>=12549&&word<=12576)||(word>=12704&&word<=12730)||
                (word==12295)){//判断是否是汉字
            chineseNum++;

        }else if(word>=48&&word<=57){//是否是数字
            numberNum++;
        }else if(word==','||word=='.'||word=='+'||word=='-'||
                word=='!'||word=='@'||word=='#'||word=='$'||
                word=='%'||word=='^'||word=='&'||word=='*'||
                word=='('||word==')'||word==';'||word==':'||
                word=='\''||word=='{'||word=='}'||word=='"'||
                word=='/'||word=='\\'||word=='?'||word=='<'||
                word=='>'||word=='='||word=='~'||word=='`'){//是否是标点符号
            punctuationNum++;
        }
        if(rooms[pos]==null) {
            initRooms(pos);
        }
        size = (byte)(rooms[pos].length/512);
        int count = getCount(rooms[pos],inerPos,size,mask);
      //  System.out.println("添加字符"+pos+":"+inerPos+":"+(byte)((rooms[pos][inerPos*size+1]>>6)&3)+":"+(rooms[pos][inerPos*size]<<2));
        if(count == 0){
            rooms[pos][inerPos*size] = (byte)((inerPos)>>2);
            rooms[pos][inerPos*size+1] = (byte)((inerPos)<<6);
           // System.out.println("初始化字符"+pos+":"+inerPos+":"+(byte)((inerPos)>>2)+":"+(byte)((rooms[pos][inerPos*size+1]>>6)&3)+":"+(rooms[pos][inerPos*size]<<2));
        }
       // System.out.println("count:"+count);
        count+=num;
        isOut = judgeIsOut(rooms[pos],inerPos,size,count);
        if(isOut) {
            int oldSize = size;
            size += 1;
            byte[] temp = new byte[size*512];
            int j = 0;
            int k = 0;
            for(int i = 0;i < 512;i++){
                temp[i*size] = rooms[pos][i*oldSize];
                /*for(j = 1;j < oldSize-1;j++){
                    temp[(i+1)*size-j] = rooms[pos][(i+1)*oldSize-j];
                }
                temp[i*size+1] = (byte)(0b11000000&rooms[pos][i*oldSize+1]);
                temp[(i+1)*size-(oldSize-1)] = (byte)(0b00111111&rooms[pos][i*oldSize+1]);*/
                /*for(j = (inerPos+1)*size-1,k = (inerPos+1)*oldSize;j > inerPos*size+1 && count > 0&&k>inerPos*oldSize+1;j--,k--){//将count放到对应的位置上去
                    temp[j] = (byte)(rooms[pos][k]&0b00000000 | count);
                    count = count>>8;
                }
                temp[inerPos*size] = rooms[pos][inerPos*oldSize];
                if(count>0){
                    // temp[inerPos*size+1] = (byte)(temp[inerPos*size+1]&0b00111111);
                    temp[inerPos*size+1] = (byte)(rooms[pos][inerPos * oldSize + 1]&11000000  | count);
                }else{
                    temp[inerPos*size+1] = (byte)(rooms[pos][inerPos * oldSize + 1]&11000000);
                    //temp[j] = (byte)count;
                }*/
                for(j = (i+1)*size-1,k = (i+1)*oldSize-1;j > i*size &&k>i*oldSize;j--,k--){//将count放到对应的位置上去
                    temp[j] = rooms[pos][k];
                }
                temp[j+1] &= 63;
                temp[i*size+1]|= (byte)(rooms[pos][i*oldSize+1]&192);
            }
            for(j = (inerPos+1)*size-1,k = (inerPos+1)*oldSize;j > inerPos*size+1 && count > 0&&k>inerPos*oldSize+1;j--,k--){//将count放到对应的位置上去
                temp[j] = (byte)(rooms[pos][k]&0b00000000 | count);
                count = count>>8;
            }
            temp[inerPos*size] = rooms[pos][inerPos*oldSize];
            if(count>0){
                // temp[inerPos*size+1] = (byte)(temp[inerPos*size+1]&0b00111111);
                temp[inerPos*size+1] = (byte)(rooms[pos][inerPos * oldSize + 1]&11000000  | count);
            }else{
                temp[inerPos*size+1] = (byte)(rooms[pos][inerPos * oldSize + 1]&11000000);
                //temp[j] = (byte)count;
            }
            rooms[pos] = temp;
           // System.out.println("越界了:"+size+":"+(temp[inerPos*size+1]>>6+temp[inerPos*size+1]<<2));
            maxSize = maxSize>=size?maxSize:size;
        }else{
            for(int i = (inerPos+1)*size-1;i > inerPos*size+1 && count >0 ;i--){//将count放到对应的位置上去
                rooms[pos][i] = (byte)(rooms[pos][i]&0b00000000 | count);
                count = count>>8;
            }
            if(count>0) {
                rooms[pos][inerPos * size + 1] = (byte)(rooms[pos][inerPos * size + 1]&11000000  | count);
            }
           // if(count!=0){
           //     rooms[pos][inerPos*size] = (byte)(rooms[pos][inerPos*size]&0b11000000 | count);
          //  }
        }
    }
    /*
    * 返回指定字符的个数
    * */
    public int getCount(byte[] context,int inerPos,byte size,byte mask){//第pos个区间第inerPos个字符
        int count = 0;
        count = context[inerPos*size+1]&mask;
        for(byte i = 2;i<size;i++) {
            count = count<<8;
            count += context[inerPos * size+i]&255;
        }
        return count;
    }
    /*
    * 交换元素
    * */
    public void exchange(byte[] context,int pos,int otherPos,int size){
        for(byte i = 0;i<size;i++){
            context[pos*size+i] = (byte)(context[pos*size+i]^context[otherPos*size+i]);
            context[otherPos*size+i] = (byte)(context[pos*size+i]^context[otherPos*size+i]);
            context[pos*size+i] = (byte)(context[pos*size+i]^context[otherPos*size+i]);
        }
    }

    /*
    * 堆排
    * */
    public void  sort(byte[] context,int head,int len,byte size,byte mask) {
        int k = head, m = k*2+1,left = 0,right = 0,now = 0;
        for(;m<len;){
            left = getCount(context,m,size,mask);
            if(m+1<len) {
                right = getCount(context,m+1,size,mask);
                now = left>=right?m:m+1;
            }else {
                now = m;
            }
          //  System.out.println("------count----"+left+":"+right);
            if(getCount(context,k,size, mask)<getCount(context,now,size,mask)) {
                exchange(context, k, now,size);
                k = now;
            }else{//添加的
                break;
            }
            m = k*2+1;
        }
    }
    //元素增加
    public boolean judgeIsOut(byte[] context,int pos,int size,int count){
        int max = (1<<((size-2)*8+6))-1;
        return count>max;
    }

    public String getTop(){//获取前十
        StringBuffer buf = new StringBuffer("{");
        top = new byte[maxSize*255];
        int count = 0, k = 0;
        for(int i = 0;i < 255;i++){
            if(rooms[i]!=null&&rooms[i].length>0) {
                size = (byte) (rooms[i].length / 512);
                initHeap(rooms[i], 512, size,mask);
                top[i * maxSize] = (byte) i;
                count = getCount(rooms[i], 0, size, mask);
                k = 1;
                while (count > 0) {
                    top[(i + 1) * maxSize - k] = (byte) (count & 255);
                    count = count >> 8;
                }
                //System.out.println("getTop----end");
            }else{
                top[i*maxSize] = (byte)i;
                for(int j = 1;j<maxSize;j++){
                    top[i*maxSize+j] = 0;
                }
            }
        }
        initHeap(top,255,maxSize,(byte)63);
        for(int i = 0;i<3;i++){
           // System.out.println("-----step2");
            int pos = top[0]&255;
            if(rooms[pos]==null||rooms[pos].length<=0){
                break;
            }
            size = (byte)(rooms[pos].length/512);
            char chr = (char)(pos*512+(rooms[pos][0]<<2)+((rooms[pos][1]>>6)&3));
           // System.out.println("++"+(rooms[pos][0]<<2)+":"+(int)(rooms[pos][0]<<2+rooms[pos][1]>>6&3));
            count = getCount(rooms[pos],0,size,mask);
            if(count==0){
                break;
            }
            if(chr=='\r'){
                buf.append("\"\\r\":\"" + count + "\",");
            }else if(chr=='\n'){
                buf.append("\"\\n\":\"" + count + "\",");
            }else if(chr=='\''){
                buf.append("\"\\'\":\"" + count + "\",");
            }else if(chr=='\"'){
                buf.append("\"\\\"\":\"" + count + "\",");
            }else if(chr=='\\'){
                buf.append("\"\\\\\":\"" + count + "\",");
            }else {
                buf.append("\"" + chr + "\":\"" + count + "\",");
            }
            setEmpty(rooms[pos],(byte)(rooms[pos].length/512),0);//将最大值置0
           // System.out.println("sort前");
            sort(rooms[pos],0,512,size,mask);
           // System.out.println("sort后");
            count = getCount(rooms[pos],0,size,mask);
           /* if(count==0){
                break;
            }*/
            k = 1;
            while(k<maxSize){//顶级堆更换count值
                top[maxSize-k] = (byte)(count&255);
                count = count>>8;
                k++;
            }
            sort(top,0,255,maxSize,(byte)255);

        }
        if(buf.charAt(buf.length()-1)==',') {
            buf.delete(buf.length() - 1, buf.length());
        }
        buf.append("}");
        return buf.toString();
    }

    public void setEmpty(byte[] context,byte size,int inerPos){
        for(int i = 0;i<size;i++){
            context[inerPos*size+i] = 0;
        }
    }

    public String getResult(){//获取计数结果 返回结果：json字符串
        StringBuffer buf = new StringBuffer("{");
        int pos = 0;
        for(int i = 0;i<255;i++){
            //System.out.println("--------------------"+i);
            if(rooms[i]==null){
                continue;
            }else{
                size = (byte)(rooms[i].length/512);
                pos = 512*i;
                for(int j = 0;j < 512;j++){
                    int count = getCount(rooms[i], j, size, mask);
                    //if(count!=0){
                  //      System.out.println("["+count+"]"+size);
                  //  }
                    if(count>0) {
                       // System.out.println((rooms[i][j * size] << 2) + ((rooms[i][j * size+1] >> 6) & 0b00000011)+"-------------"+j);
                        if((char) (pos + (rooms[i][j * size] << 2) + ((rooms[i][j * size + 1] >> 6) & 3))=='\r'){
                            buf.append("\"\\r\":\"" + getCount(rooms[i], j, size, mask) + "\",");
                        }else if((char) (pos + (rooms[i][j * size] << 2) + ((rooms[i][j * size + 1] >> 6) & 3))=='\n'){
                            buf.append("\"\\n\":\"" + getCount(rooms[i], j, size, mask) + "\",");
                        }else if((char) (pos + (rooms[i][j * size] << 2) + ((rooms[i][j * size + 1] >> 6) & 3))=='\''){
                            buf.append("\"\\'\":\"" + getCount(rooms[i], j, size, mask) + "\",");
                        }else if((char) (pos + (rooms[i][j * size] << 2) + ((rooms[i][j * size + 1] >> 6) & 3))=='\"'){
                            buf.append("\"\\\"\":\"" + getCount(rooms[i], j, size, mask) + "\",");
                        }else if((char) (pos + (rooms[i][j * size] << 2) + ((rooms[i][j * size + 1] >> 6) & 3))=='\\'){
                            buf.append("\"\\\\\":\"" + getCount(rooms[i], j, size, mask) + "\",");
                        }else {
                            buf.append("\"" + (char) (pos + (rooms[i][j * size] << 2) + ((rooms[i][j * size + 1] >> 6) & 3)) + "\":\"" + getCount(rooms[i], j, size, mask) + "\",");
                        }
                    }
                }
            }
        }
        if(buf.charAt(buf.length()-1)==',') {
            buf.delete(buf.length() - 1, buf.length());
        }
        buf.append("}");
        return buf.toString();
    }

    public String getNumber(){
        String result = null;
        result = "{\"characterNum\":\""+characterNum+"\",\"numberNum\":\""+numberNum+"\",\"chineseNum\":\""+chineseNum+"\",\"punctuationNum\":\""+punctuationNum+"\"}";
        return result;
    }
}
