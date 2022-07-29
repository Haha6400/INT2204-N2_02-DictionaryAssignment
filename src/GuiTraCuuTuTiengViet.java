
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiTraCuuTuTiengViet extends JFrame {

    JTextField txtTiengVietCanTra,txtTiengAnhNhanLai;

    JButton btnChucNangTraCuuTiengViet,btnTroVeManHinhChinhTuTraCuuTiengViet,btnPhatAm;

    JPanel pnTraCuuTuTiengViet=pnTraCuuTuTiengViet();

    Container con;


    public GuiTraCuuTuTiengViet(){
        super("Từ Điển");
        addControls();
        addEvents();
    }
    public void addControls(){

        con=getContentPane();

        con.add(pnTraCuuTuTiengViet);

    }

    public void addEvents(){
        btnTroVeManHinhChinhTuTraCuuTiengViet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTroVeManHinhChinhTuTraCuuTiengViet();
            }
        });
        btnChucNangTraCuuTiengViet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChucNangTraCuuTuTiengViet();
            }
        });
        btnPhatAm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChucNangPhatAm();
            }
        });

    }

    private void xuLyChucNangPhatAm() {
        String tiengAnh=txtTiengAnhNhanLai.getText().toLowerCase();
        Pronounce.read(tiengAnh);
    }

    private void xuLyChucNangTraCuuTuTiengViet() {


    }

    private void xuLyTroVeManHinhChinhTuTraCuuTiengViet() {
        this.dispose();
        Gui gui=new Gui();
        gui.showWindow();
    }

    public JPanel pnTraCuuTuTiengViet(){
        Font generalFont=new Font("arial",Font.TYPE1_FONT,20);
        Font titleFont=new Font("arial",Font.BOLD,30);
        Font fontInText=new Font("arial",Font.ITALIC,16);


        JPanel pnMain=new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));

        JPanel pnTitle= new JPanel();
        pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitle=new JLabel("Tra cứu từ tiếng Việt");
        lblTitle.setFont(titleFont);
        pnTitle.add(lblTitle);
        pnMain.add(pnTitle);

        JPanel pnTiengViet=new JPanel();
        pnTiengViet.setLayout(new FlowLayout());
        JLabel lblTiengViet=new JLabel("Tiếng Việt:");
        lblTiengViet.setFont(generalFont);
        txtTiengVietCanTra=new JTextField("Nhập Từ Tiếng Việt cần tra",20);
        txtTiengVietCanTra.setFont(fontInText);
        pnTiengViet.add(lblTiengViet);
        pnTiengViet.add(txtTiengVietCanTra);
        pnMain.add(pnTiengViet);

        JPanel pnTiengAnh=new JPanel();
        pnTiengAnh.setLayout(new FlowLayout());
        JLabel lblTiengAnh=new JLabel(" Nghĩa tiếng Anh:");
        lblTiengAnh.setFont(generalFont);
        txtTiengAnhNhanLai=new JTextField(20);
        txtTiengAnhNhanLai.setFont(fontInText);
        pnTiengAnh.add(lblTiengAnh);
        pnTiengAnh.add(txtTiengAnhNhanLai);
        pnMain.add(pnTiengAnh);

        JPanel pnPhatAm=new JPanel();
        pnPhatAm.setLayout(new FlowLayout());
        btnPhatAm=new JButton("Phát Âm");
        btnPhatAm.setFont(generalFont);
        pnPhatAm.add(btnPhatAm);
        pnMain.add(pnPhatAm);

        JPanel pnTraCuu=new JPanel();
        pnTraCuu.setLayout(new FlowLayout());
        btnChucNangTraCuuTiengViet=new JButton("Tra cứu");
        btnChucNangTraCuuTiengViet.setFont(generalFont);
        pnTraCuu.add(btnChucNangTraCuuTiengViet);
        pnMain.add(pnTraCuu);

        JPanel pnTroVe=new JPanel();
        pnTroVe.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnTroVeManHinhChinhTuTraCuuTiengViet=new JButton("Trở về");
        btnTroVeManHinhChinhTuTraCuuTiengViet.setFont(generalFont);
        pnTroVe.add(btnTroVeManHinhChinhTuTraCuuTiengViet);
        pnMain.add(pnTroVe);

        lblTiengViet.setPreferredSize(lblTiengAnh.getPreferredSize());

        return pnMain;
    }
    public void showWindow(){
        this.setSize(600,600);// set size
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// thêm chức năng thoát hoàn toàn khỏi chương trình vào nút đỏ
        this.setLocationRelativeTo(null);// để giao diện nằm chính giữa
        this.setResizable(false);// không cho phép thay đổi kích cỡ cửa sổ
        this.setVisible(true);// để giao diện hiện lên

    }


}
