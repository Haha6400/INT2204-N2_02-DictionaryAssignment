package Gui;

import QuanLyThuVien.Dictionary;
import QuanLyThuVien.DictionaryManagement;
import QuanLyThuVien.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XoaTu extends Gui{
    JTextField txtEnglish;
    JButton btnXoaTu,btnBack;

    Container container;
    public XoaTu(){
        super("Xóa từ");
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
        btnXoaTu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaTu();
            }
        });

    }

    private void xoaTu() {
        String english=txtEnglish.getText().toLowerCase();
        if (Dictionary.hoTroTraCuu.containsKey(english)==false)
        {
            JOptionPane.showMessageDialog(null,"Từ này không tồn tại trong từ điển");
        }
        else
        {
            Word word=new Word();
            String vietnamese=Dictionary.hoTroTraCuu.get(english);
            word.setEnglish(english);
            word.setVietnamese(vietnamese);
            Dictionary.hoTroTraCuu.remove(english,vietnamese);

            for(int i=0;i<Dictionary.dictionary.size();i++)
            {
                if (Dictionary.dictionary.get(i).getEnglish().equals(english)==true)
                {
                    System.out.println(Dictionary.dictionary.get(i).getVietnamese());
                    Dictionary.dictionary.remove(i);
                    break;
                }
            }

            DictionaryManagement.exportToFile();
            DictionaryManagement.insertFromFile();
            JOptionPane.showMessageDialog(null,"Xóa thành công");
        }
    }

    private void troVe() {
        this.dispose();
        TrangChu trangchu = new TrangChu();
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
        JLabel lblTitle=new JLabel("Xóa từ");
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


        JPanel pnThemTu=new JPanel();
        pnThemTu.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnXoaTu=new JButton("Xóa từ");
        btnXoaTu.setFont(FontFormat.generalFont);
        pnThemTu.add(btnXoaTu);


        JPanel pnBack=new JPanel();
        pnBack.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnBack=new JButton("Trở về");
        btnBack.setFont(FontFormat.generalFont);
        pnBack.add(btnBack);


        pnMain.add(pnTitle);
        pnMain.add(pnEnglish);
        pnMain.add(pnThemTu);
        pnMain.add(pnBack);
        return pnMain;
    }
}
