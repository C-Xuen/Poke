public class Poke {
    String[] a = new String[52];  // 存储生成的牌
    String[] b = new String[52];  // 存储打乱后的牌
    String[] c = new String[13];  // 存储抽取的牌
    String[] d = new String[13];  // 存储排序后的牌
    String[] e = new String[52];  // 存储合并后的牌

    public static void main(String[] args) {
        Poke poker = new Poke();

        System.out.println("开始生成牌：");
        poker.Create();     //创建牌
        poker.Print(poker.a);
        System.out.println(" ");

        System.out.println("开始打乱牌：");
        poker.Disrupt();    //打乱牌
        poker.Print(poker.b);
        System.out.println(" ");

        System.out.println("开始抽取牌：");
        poker.Draw(0,13);   //抽取牌
        poker.Print(poker.c);
        System.out.println(" ");

        System.out.println("开始整理牌：");
        poker.Draw(0,13);
        poker.d = poker.Sort();     // 对抽取的牌进行排序
        poker.Print(poker.d);
        System.out.println(" ");

        // 抽取并合并多副牌
        poker.Draw(0,13);
        String[] p1 = poker.Sort();
        poker.Merge(p1,0);
        poker.Draw(13,26);
        String[] p2 = poker.Sort();
        poker.Merge(p2,13);
        poker.Draw(26,39);
        String[] p3 = poker.Sort();
        poker.Merge(p3,26);
        poker.Draw(39,52);
        String[] p4 = poker.Sort();
        poker.Merge(p4,39);

        System.out.println("开始合并牌：");
        poker.Print(poker.e);
    }
    public void Create() {  // 生成牌名存于数组a
        String[] PokeName = {"fang", "hong", "mei", "hei"};
        int x = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++, x++) {
                a[x] = PokeName[i] + j + ".jpg";
            }
        }
    }
    public void Print(String[] p) {
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + '\t');
            if (i % 13 == 12) {
                System.out.println(" ");
            }
        }
    }
    public void Disrupt() {     //打乱牌
        System.arraycopy(a, 0, b, 0, 52); // 将数组a的内容复制到数组b
        // 随机交换100次牌的位置
        for (int i = 0; i < 100; i++) {
            String temp; // 临时变量用于交换
            int x = (int) (Math.random() * 52); // 随机选择一个位置x
            int y = (int) (Math.random() * 52); // 随机选择另一个位置y
            // 交换b数组中位置x和y的牌名
            temp = b[x];
            b[x] = b[y];
            b[y] = temp;
        }
    }
    boolean Compare(String a, String b) {       //比大小
        int x = 0, y = 0; // 用于存储两张牌的索引
        Poke pk = new Poke();
        pk.Create(); // 调用Create方法以初始化牌名
        for (int i = 0; i < 52; i++) {
            // 如果a和目前的牌名相等，存储索引x
            if (a.equals(pk.a[i])) {
                x = i;
            }
            // 如果b和目前的牌名相等，存储索引y
            if (b.equals(pk.a[i])) {
                y = i;
            }
        }
        // 返回a是否比b大的判断结果
        return x > y;
    }
    public void Draw(int n, int m){    //抽取牌
        for(int i = n,x = 0; i < m; i++, x++){
            c[x] = b[i];     // 从打乱后的牌中抽取,放入c数组
        }
    }
    public String[] Sort() {     //排序牌
        String[] tmp1 = new String[13];
        System.arraycopy(c, 0, tmp1, 0, 13);
        for(int i = 0; i < tmp1.length; i++){
            for(int j = i; j < tmp1.length; j++){
                if(Compare(tmp1[i], tmp1[j])){  // 比较牌的大小
                    String temp = tmp1[i];
                    tmp1[i] = tmp1[j];
                    tmp1[j] = temp;
                }
            }
        }
        return tmp1;
    }
    public void Merge(String[] p, int n) {      //合并牌
        int x = n;
        for(int i = 0; i < 13; i++, x++){
            e[x] = p[i];
        }
    }
    public String[] Get() {
        Create();
        Disrupt();
        Draw(0, 13);
        String[] save = Sort();
        return save.clone();
    }
}