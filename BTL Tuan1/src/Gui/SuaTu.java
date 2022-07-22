package Gui;

import QuanLyThuVien.Dictionary;
import QuanLyThuVien.DictionaryManagement;
import QuanLyThuVien.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuaTu extends Gui{
    JTextField txtEnglish,txtVietnamese;
    JButton btnSuaTu,btnBack,btnTraCuu;

    Container container;
    public SuaTu(){
        super("Sửa từ");
        addControls();
        addEvents();
    }
    @Override
    public void addEvents() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                troVe();
            }
        });
        btnTraCuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traCuu();
            }
        });
        btnSuaTu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suaTu();
            }
        });
    }

    private void suaTu() {
        String english=txtEnglish.getText().toLowerCase();
        if (Dictionary.hoTroTraCuu.containsKey(english)==false)
        {
            JOptionPane.showMessageDialog(null,"Không tồn tại từ này");
        }
        else
        {
            Word word=new Word();
            word.setEnglish(english);
            System.out.println(english);
            for(int i=0;i<Dictionary.dictionary.size();i++)
            {
                if (Dictionary.dictionary.get(i).getEnglish().equals(english)==true)
                {
                    System.out.println(Dictionary.dictionary.get(i).getVietnamese());
                    Dictionary.dictionary.remove(i);
                    break;
                }
            }
            word.setVietnamese(txtVietnamese.getText().toLowerCase());
            Dictionary.dictionary.add(word);
            Dictionary.hoTroTraCuu.put(word.getEnglish(),word.getVietnamese());
            DictionaryManagement.exportToFle();
            DictionaryManagement.insertFromFile();
            JOptionPane.showMessageDialog(null,"Sửa thành công");
        }
    }

    private void traCuu() {
        String english=txtEnglish.getText().toLowerCase();
        if (Dictionary.hoTroTraCuu.containsKey(english)==false)
        {
            JOptionPane.showMessageDialog(null,"Không tồn tại từ này");
        }
        else
        {
            txtVietnamese.setText(Dictionary.hoTroTraCuu.get(english));
        }

    }

    private void troVe() {
        this.dispose();
        TrangChu trangchu=new TrangChu();
        trangchu.showWindows();
    }

    @Override
    public void addControls() {
        container=getContentPane();
        container.add(pnMain());
    }

    @Override
    public JPanel pnMain() {
        JPanel pnMain=new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));

        JPanel pnTitle=new JPanel();
        pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitle=new JLabel("Sửa từ");
        lblTitle.setFont(FontFormat.titleFont);
        pnTitle.add(lblTitle);


        JPanel pnEnglish=new JPanel();
        pnEnglish.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblEnglish=new JLabel("English:");
        lblEnglish.setFont(FontFormat.generalFont);
        txtEnglish=new JTextField(20);
        txtEnglish.setFont(FontFormat.fontInText);
        pnEnglish.add(lblEnglish);
        pnEnglish.add(txtEnglish);

        JPanel pnVietnamese=new JPanel();
        pnVietnamese.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblVietNamese=new JLabel("Vietnamese:");
        lblVietNamese.setFont(FontFormat.generalFont);
        txtVietnamese=new JTextField(20);
        txtVietnamese.setFont(FontFormat.fontInText);
        pnVietnamese.add(lblVietNamese);
        pnVietnamese.add(txtVietnamese);


        JPanel pnThemTu=new JPanel();
        pnThemTu.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnTraCuu=new JButton("Tra cứu");
        btnTraCuu.setFont(FontFormat.generalFont);
        btnSuaTu=new JButton("Sửa từ");
        btnSuaTu.setFont(FontFormat.generalFont);
        pnThemTu.add(btnTraCuu);
        pnThemTu.add(btnSuaTu);


        JPanel pnBack=new JPanel();
        pnBack.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnBack=new JButton("Trở về");
        btnBack.setFont(FontFormat.generalFont);
        pnBack.add(btnBack);


        pnMain.add(pnTitle);
        pnMain.add(pnEnglish);
        pnMain.add(pnVietnamese);
        pnMain.add(pnThemTu);
        pnMain.add(pnBack);
        lblEnglish.setPreferredSize(lblVietNamese.getPreferredSize());
        return pnMain;
    }
}