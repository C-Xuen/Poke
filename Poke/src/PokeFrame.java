import javax.swing.*;
import java.awt.*;

public class PokeFrame extends JFrame {
    JDesktopPane jdp = new JDesktopPane();
    GridBagConstraints gbc = new GridBagConstraints();

    public PokeFrame() {
        super("MyWindow");
        getContentPane().add(jdp);

        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\Poke\\Poke\\Poke\\src\\img\\bj.jpg");
        JLabel jl = new JLabel(img);
        // 将 JLabel 添加到 JDesktopPane 的背景层
        jdp.add(jl, JLayeredPane.DEFAULT_LAYER);

        jdp.setLayout(new GridBagLayout());

        // 设置填充方式为 BOTH，表示组件在水平方向和垂直方向都填充所占用的空间
        gbc.fill = GridBagConstraints.BOTH;
        // 设置该组件在 水平 方向上的权重为 1.0，表示在拥有多余空间时，组件会获得相同的分配
        gbc.weightx = 1.0;
        // 设置该组件在 垂直 方向上的权重为 1.0，表示在拥有多余空间时，组件会获得相同的分配
        gbc.weighty = 1.0;

        AddWindows(4);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置窗口为全屏
        this.setVisible(true);    // 窗口可视化
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // 设置退出按钮
    }

    public void AddWindows(int nums){
        int rows = GetRows(nums);
        int cols = GetCols(nums, rows);
        jdp.removeAll(); // 清除之前的窗口
        int count = 0;  //计算玩家个数（count + 1）
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (count < nums) {
                    InsideWindow iw = new InsideWindow(this, "Player " + (count + 1));
                    gbc.gridx = j;  // 设置网格布局中该组件的 x 坐标
                    gbc.gridy = i;  // 设置网格布局中该组件的 y 坐标
                    jdp.add(iw, gbc);    // 将窗体添加到指定的布局管理器中
                    iw.setVisible(true);
                    count++;
                }
            }
        }
        jdp.revalidate(); // 刷新布局
        jdp.repaint();    // 重绘面板
    }

    public String[] GetPoke() {
        Poke p = new Poke();
        p.Create();
        return p.Get();
        //存在只能返回13个的问题
    }

    public int GetRows(int nums) {
        int rows = (int) Math.sqrt(nums);   //行
        System.out.println("行：" + rows);
        return rows;
    }

    public int GetCols(int nums, int rows) {
        int cols = (nums + rows - 1) / rows;    //列
        System.out.println("列：" + cols);
        return cols;
    }

    public static void main(String[] args) {
        PokeFrame pokeFrame = new PokeFrame();
    }
}