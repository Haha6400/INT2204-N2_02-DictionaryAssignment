package Gui;

import QuanLyThuVien.Dictionary;
import QuanLyThuVien.DictionaryManagement;
import QuanLyThuVien.Word;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThemTu extends Gui {

  JTextField txtEnglish, txtVietnamese;
  JButton btnThemTu, btnBack;

  Container container;

  public ThemTu() {
    super("Thêm từ");
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
    btnThemTu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        themTu();
      }
    });
  }

  private void themTu() {
    String english = txtEnglish.getText().toLowerCase();
    String vietnamese = txtVietnamese.getText().toLowerCase();
    if (Dictionary.hoTroTraCuu.containsKey(english)) {
      JOptionPane.showMessageDialog(null, "Từ nãy đã có trong từ điển");
    } else {
      Word word = new Word();
      word.setEnglish(english);
      word.setVietnamese(vietnamese);
      DictionaryManagement.insertWord(word);
      Dictionary.hoTroTraCuu.put(english, vietnamese);
      DictionaryManagement.exportToFile();
      DictionaryManagement.insertFromFile();
      JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
  }

  private void troVe() {
    this.dispose();
    TrangChu guiTrangChu = new TrangChu();
    guiTrangChu.showWindows();
  }

  @Override
  public void addControls() {
    container = getContentPane();
    container.add(pnMain());
  }

  @Override
  public JPanel pnMain() {
    JPanel pnMain = new JPanel();
    pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

    JPanel pnTitle = new JPanel();
    pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
    JLabel lblTitle = new JLabel("Thêm từ");
    lblTitle.setFont(FontFormat.titleFont);
    pnTitle.add(lblTitle);

    JPanel pnEnglish = new JPanel();
    pnEnglish.setLayout(new FlowLayout(FlowLayout.CENTER));
    JLabel lblEnglish = new JLabel("English:");
    lblEnglish.setFont(FontFormat.generalFont);
    txtEnglish = new JTextField(20);
    txtEnglish.setFont(FontFormat.fontInText);
    pnEnglish.add(lblEnglish);
    pnEnglish.add(txtEnglish);

    JPanel pnVietnamese = new JPanel();
    pnVietnamese.setLayout(new FlowLayout(FlowLayout.CENTER));
    JLabel lblVietNamese = new JLabel("Vietnamese:");
    lblVietNamese.setFont(FontFormat.generalFont);
    txtVietnamese = new JTextField(20);
    txtVietnamese.setFont(FontFormat.fontInText);
    pnVietnamese.add(lblVietNamese);
    pnVietnamese.add(txtVietnamese);

    JPanel pnThemTu = new JPanel();
    pnThemTu.setLayout(new FlowLayout(FlowLayout.CENTER));
    btnThemTu = new JButton("Thêm từ");
    btnThemTu.setFont(FontFormat.generalFont);
    pnThemTu.add(btnThemTu);

    JPanel pnBack = new JPanel();
    pnBack.setLayout(new FlowLayout(FlowLayout.CENTER));
    btnBack = new JButton("Trở về");
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