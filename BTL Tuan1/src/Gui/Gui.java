package Gui;

import javax.swing.*;

public abstract class Gui extends JFrame {
    public Gui(String e) { // tạo tiêu đề e;
        super(e);
    }
    public abstract void addEvents(); // thêm sự kiện cho button
    public abstract void addControls(); // thêm thiết kế giao diện
    public abstract JPanel pnMain(); // thiết kế giao diện
    public void showWindows(){ // hiện màn hình chính
        this.setSize(600,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setResizable(false);
    }

}

