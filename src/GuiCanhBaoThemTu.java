import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCanhBaoThemTu extends JFrame {
    Word word;

    Container container;

    JButton btnChapNhap,btnHuyBo;

    Font generalFont=new Font("arial",Font.TYPE1_FONT,16);
    Font titleFont=new Font("arial",Font.BOLD,30);
    Font fontInText=new Font("arial",Font.ITALIC,16);

    public GuiCanhBaoThemTu(Word word){
        super("Cảnh báo");
        this.word=word;
        addControls();
        addEvents();

    }

    public void addControls(){
        container=getContentPane();

        JPanel pnMain=new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));

        JPanel pnTrong=new JPanel();
        pnMain.add(pnTrong);

        JPanel pnThongBao=new JPanel();
        pnThongBao.setPreferredSize(new Dimension(400,300));
        pnThongBao.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblThongBao=new JLabel("Bạn có chắc muốn thêm từ này?");
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

        pnTrong.setPreferredSize(new Dimension(270,55));
        pnThongBao.setPreferredSize(new Dimension(270,75));
        pnButton.setPreferredSize(new Dimension(270,50));

        container.add(pnMain);

    }

    public void addEvents(){
        btnChapNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xyLyChucNangChapNhanSuaTu();
            }
        });
        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChucNangTroVe();
            }
        });
    }

    private void xuLyChucNangTroVe() {
        this.dispose();
    }

    private void xyLyChucNangChapNhanSuaTu() {
        DictionaryManagement.insertWord(word);
        JOptionPane.showMessageDialog(null,"Thêm thành công");
        this.dispose();
    }

    public void showWindow(){
        this.setSize(320,200);// set size
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// thêm chức năng thoát hoàn toàn khỏi chương trình vào nút đỏ
        this.setLocationRelativeTo(null);// để giao diện nằm chính giữa
        this.setResizable(false);// không cho phép thay đổi kích cỡ cửa sổ
        this.setVisible(true);// để giao diện hiện lên

    }
}
