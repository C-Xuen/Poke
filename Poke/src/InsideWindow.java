import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class InsideWindow extends JInternalFrame {
    PokeFrame pf;
    JButton[] Key = new JButton[13];
    String[] SaveImages;

    public InsideWindow(PokeFrame frame, String str) {
        super(str);
        this.pf = frame;

        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\Poke\\Poke\\Poke\\src\\img\\bj.jpg");
        JLabel jl = new JLabel(img);
        Container cp = this.getContentPane();
        cp.add(jl);
        JLayeredPane jp = this.getLayeredPane();

        SaveImages = frame.GetPoke();

        int w = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        int h = Toolkit.getDefaultToolkit().getScreenSize().height / 2;

        for (int i = 0; i < 13; i++) {
            ImageIcon images = new ImageIcon("D:\\IdeaProjects\\Poke\\Poke\\Poke\\src\\img\\" + SaveImages[i]);
            JButton jn = new JButton(images);
            Key[i] = jn;
            int key_w = images.getIconWidth();
            int key_h = images.getIconHeight();
            int poke_w = 50 * 12 + key_w;
            int x = (w - poke_w) / 2;   //起始牌位置
            Key[i].setBounds(x + 50 * i, h - key_h - 100, key_w, key_h);  //按键位置，根据需求修改
            jp.add(Key[i], Integer.valueOf(i));

            Key[i].addActionListener(new Listener());
        }
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack(); // 让窗口根据内容自动调整大小
    }

    public static class Listener implements ActionListener {
        HashMap<JButton, Boolean> buttonStates = new HashMap<>();

        @Override
        public void actionPerformed(ActionEvent act) {
            JButton bn = (JButton) act.getSource();
            boolean isMoving = buttonStates.getOrDefault(bn, false);    // 获取按钮的移动状态，如果没有则默认为 false

            int set_y = bn.getY();
            bn.setBounds(bn.getX(), isMoving ? set_y + 50 : set_y - 50, bn.getWidth(), bn.getHeight());
            buttonStates.put(bn, !isMoving);    // 更新按钮的移动状态
        }

        public void initButtons(JButton[] keys) {
            for (JButton button : keys) {
                button.addActionListener(this);
                buttonStates.put(button, false); // 初始化每个按钮的移动状态为 false
            }
        }
    }

    public static void main(String[] args) {
        JButton[] Key = new JButton[13];
        Listener listener = new Listener();
        listener.initButtons(Key); // 初始化按钮和状态
    }
}
