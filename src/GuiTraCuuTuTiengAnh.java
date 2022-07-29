import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class GuiTraCuuTuTiengAnh extends JFrame {


    Border borderChung=BorderFactory.createLineBorder(Color.blue);

    JTextField txtTiengAnhCanTra;

    JTextArea txtTiengVietNhanLai;

    JButton btnChucNangTraCuuTiengAnh,btnTroVeManHinhChinhTuTraCuuTiengAnh,btnPhatAm;

    Container con;

    JPanel pnTraCuuTuTiengAnh=pnTraCuuTuTiengAnh();

    JList listTiengAnh;

    public GuiTraCuuTuTiengAnh(){
        super("Từ Điển");
        addControls();
        addEvents();
    }
    public void addControls(){
        con=getContentPane();
        con.add(pnTraCuuTuTiengAnh);
    }

    public void addEvents(){
        btnTroVeManHinhChinhTuTraCuuTiengAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTroVeManHinhChinhTuTraCuuTiengAnh();
            }
        });
        btnChucNangTraCuuTiengAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChucNangTraCuuTuTiengAnh();
            }
        });
        btnPhatAm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChucNangPhatAm();
            }
        });

        listTiengAnh.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String english=(String) listTiengAnh.getSelectedValue();
                if (english!=null)
                {
                    Word word=DictionaryManagement.dictionaryLookUp(english).get(0);
                    txtTiengVietNhanLai.setText(word.getDescription()+"\n"+ word.getPronounce());
                    txtTiengAnhCanTra.setText(english);
                }
            }
        });

    }

    private void xuLyChucNangPhatAm() {
        String tiengAnh=txtTiengAnhCanTra.getText().toLowerCase();
        Pronounce.read(tiengAnh);
    }

    private void xuLyChucNangTraCuuTuTiengAnh() {
        String english=txtTiengAnhCanTra.getText().toLowerCase();
        ArrayList<Word> words=DictionaryManagement.dictionaryLookUp(english);
        if (words.size()==0)
        {
            JOptionPane.showMessageDialog(null,"Không tìm thấy từ này trong từ điển");
        }
        else {
            Vector<String> vector=new Vector<String>();
            for (Word word : words)
            {
                vector.add(word.getWord());
            }
            listTiengAnh.setListData(vector);
            if (words.get(0).getWord().equals(english))
            {
                txtTiengVietNhanLai.setText(words.get(0).getDescription()+"\n"+words.get(0).getPronounce());
            }

        }
    }
    private void xuLyTroVeManHinhChinhTuTraCuuTiengAnh() {
        this.dispose();
        Gui gui=new Gui();
        gui.showWindow();
    }

    public JPanel pnTraCuuTuTiengAnh(){

        Font generalFont=new Font("arial",Font.TYPE1_FONT,20);
        Font titleFont=new Font("arial",Font.BOLD,30);
        Font fontInText=new Font("arial",Font.ITALIC,16);

        JPanel pnMain=new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));

        JPanel pnTitle= new JPanel();
        pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitle=new JLabel("Tra cứu từ tiếng Anh");
        lblTitle.setFont(titleFont);
        pnTitle.add(lblTitle);
        pnMain.add(pnTitle);

        JPanel pnTraCuuBox=new JPanel();
        pnTraCuuBox.setLayout(new GridLayout(1,2));
        JPanel pnTiengAnh=new JPanel();
        JPanel pnTxtTiengAnh=new JPanel();
        JPanel pnAreaTiengAnh=new JPanel();
        pnAreaTiengAnh.setPreferredSize(new Dimension(100,300));
        pnTiengAnh.setLayout(new BoxLayout(pnTiengAnh,BoxLayout.Y_AXIS));
        TitledBorder titledBorderTiengAnh=new TitledBorder(borderChung,"Tiếng Anh");
        titledBorderTiengAnh.setTitleJustification(TitledBorder.CENTER);
        titledBorderTiengAnh.setTitleFont(generalFont);
        txtTiengAnhCanTra=new JTextField(20);
        txtTiengAnhCanTra.setFont(fontInText);
        listTiengAnh=new JList();
        listTiengAnh.setFont(fontInText);
        JScrollPane scListTiengAnh=new JScrollPane(listTiengAnh,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //pnTiengAnh.add(txtTiengAnhCanTra);
        pnTxtTiengAnh.add(txtTiengAnhCanTra);
        //pnTiengAnh.add(scListTiengAnh);
        scListTiengAnh.setPreferredSize(new Dimension(270,350));
        pnAreaTiengAnh.add(scListTiengAnh);
        pnTiengAnh.add(pnTxtTiengAnh);
        pnTiengAnh.add(pnAreaTiengAnh);
        pnTiengAnh.setBorder(titledBorderTiengAnh);

        JPanel pnTiengViet=new JPanel();
        pnTiengViet.setLayout(new BoxLayout(pnTiengViet,BoxLayout.Y_AXIS));
        TitledBorder titledBorderTiengViet=new TitledBorder(borderChung,"Nghĩa tiếng Việt");
        titledBorderTiengViet.setTitleJustification(TitledBorder.CENTER);
        titledBorderTiengViet.setTitleFont(generalFont);
        txtTiengVietNhanLai=new JTextArea();
        txtTiengVietNhanLai.setFont(fontInText);
        txtTiengVietNhanLai.setWrapStyleWord(true);
        txtTiengVietNhanLai.setLineWrap(true);
        pnTiengViet.add(txtTiengVietNhanLai);
        pnTiengViet.setBorder(titledBorderTiengViet);


        pnTraCuuBox.add(pnTiengAnh);
        pnTraCuuBox.add(pnTiengViet);
        pnMain.add(pnTraCuuBox);

        JPanel pnPhatAm=new JPanel();
        pnPhatAm.setLayout(new FlowLayout());
        btnPhatAm=new JButton();
        btnPhatAm.setIcon(new ImageIcon("Hinh\\icon\\speaker.png"));
        btnPhatAm.setFont(generalFont);
        pnPhatAm.add(btnPhatAm);
        pnMain.add(pnPhatAm);

        JPanel pnTraCuu=new JPanel();
        pnTraCuu.setLayout(new FlowLayout());
        btnChucNangTraCuuTiengAnh=new JButton("Tra cứu");
        btnChucNangTraCuuTiengAnh.setFont(generalFont);
        pnTraCuu.add(btnChucNangTraCuuTiengAnh);
        pnMain.add(pnTraCuu);

        JPanel pnTroVe=new JPanel();
        pnTroVe.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnTroVeManHinhChinhTuTraCuuTiengAnh=new JButton();
        btnTroVeManHinhChinhTuTraCuuTiengAnh.setIcon(new ImageIcon("Hinh\\icon\\back.png"));
        btnTroVeManHinhChinhTuTraCuuTiengAnh.setFont(generalFont);
        pnTroVe.add(btnTroVeManHinhChinhTuTraCuuTiengAnh);
        pnMain.add(pnTroVe);

//        lblTiengAnh.setPreferredSize(lblTiengViet.getPreferredSize());

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
