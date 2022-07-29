
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCanhBaoSuaTu extends JFrame {

    Word word;

    Container container;

    JButton btnChapNhap,btnHuyBo;

    Font generalFont=new Font("arial",Font.TYPE1_FONT,15);
    Font titleFont=new Font("arial",Font.BOLD,30);
    Font fontInText=new Font("arial",Font.ITALIC,16);

    public GuiCanhBaoSuaTu(Word word){
        super("Cảnh báo");
        this.word=word;
        addControls();
        addEvents();

    }

    public void addControls(){
        container=getContentPane();

        JPanel pnMain=new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));

        JPanel pnThongBao=new JPanel();
        pnThongBao.setPreferredSize(new Dimension(400,300));
        pnThongBao.setLayout(new BorderLayout());
        JLabel lblThongBao=new JLabel("Bạn có thực sự muốn sửa từ này?");
        lblThongBao.setIcon(new ImageIcon("Hinh\\icon\\canhbao.png"));
        lblThongBao.setFont(generalFont);
        pnThongBao.add(lblThongBao);
        pnMain.add(pnThongBao);

        JPanel pnButton=new JPanel();
        btnChapNhap=new JButton("Chấp nhận");
        btnChapNhap.setFont(generalFont);
        btnHuyBo=new JButton("Hủy bỏ");
        btnHuyBo.setFont(generalFont);
        pnButton.add(btnChapNhap);
        pnButton.add(btnHuyBo);

        pnMain.add(pnButton);

        container.add(pnMain);

    }

    public void addEvents(){
        btnChapNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChucNangChapNhanSuaTu();
            }
        });
        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChucNangHuyBo();
            }
        });
    }

    private void xuLyChucNangHuyBo() {
        this.dispose();
    }

    private void xuLyChucNangChapNhanSuaTu() {
        DictionaryManagement.repairWord(word);
        JOptionPane.showMessageDialog(null,"Sửa từ thành công");
        this.dispose();
        GuiSuaTu guiSuaTu=new GuiSuaTu();
        guiSuaTu.showWindow();
    }


    public void showWindow(){
        this.setSize(320,200);// set size
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// thêm chức năng thoát hoàn toàn khỏi chương trình vào nút đỏ
        this.setLocationRelativeTo(null);// để giao diện nằm chính giữa
        this.setResizable(false);// không cho phép thay đổi kích cỡ cửa sổ
        this.setVisible(true);// để giao diện hiện lên

    }
}
