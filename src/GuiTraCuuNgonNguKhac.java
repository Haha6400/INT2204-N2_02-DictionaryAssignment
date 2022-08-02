import com.darkprograms.speech.translator.GoogleTranslate;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class GuiTraCuuNgonNguKhac extends JFrame {


  Border borderChung = BorderFactory.createLineBorder(Color.blue);

  JLabel lblTuDong;

  Container con;

  JTextArea txtAreaCanTra, txtAreaNhanLai;

  JComboBox<String> cbChonNgonNguNhanLai;

  JButton btnTraCuu, btnBack;

  Font generalFont = new Font("arial", Font.TYPE1_FONT, 20);
  Font titleFont = new Font("arial", Font.BOLD, 30);
  Font fontInText = new Font("arial", Font.ITALIC, 16);

  JList listTiengAnh;

  public GuiTraCuuNgonNguKhac() {
    super("Từ Điển");
    addControls();
    addEvents();
  }

  public void addControls() {
    con = getContentPane();
    con.add(pnTraCuuTuNgonNguKhac());
  }

  public void addEvents() {
    btnBack.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        xuLyChucNangBack();
      }
    });
    btnTraCuu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        xuLyChucNangTraCuu();
      }
    });
  }

  private void xuLyChucNangTraCuu() {
    String canTra = txtAreaCanTra.getText();
    String targetLanguage = (String) cbChonNgonNguNhanLai.getSelectedItem();
    String key;
    switch (targetLanguage) {
      case "Vietnamese":
        key = "vi";
        break;
      case "English":
        key = "en";
        break;
      case "Japanese":
        key = "ja";
        break;
      case "Indonesian":
        key = "id";
        break;
      case "French":
        key = "fr";
        break;
      case "German":
        key = "de";
        break;
      default:
        key = "en";
    }
    String nhanLai = "";
    try {
      nhanLai = GoogleTranslate.translate(GoogleTranslate.detectLanguage(canTra), key, canTra);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    txtAreaNhanLai.setText(nhanLai);

  }

  private void xuLyChucNangBack() {
    this.dispose();
    Gui gui = new Gui();
    gui.showWindow();
  }


  public JPanel pnTraCuuTuNgonNguKhac() {

//        TitledBorder titledBorderTiengViet=new TitledBorder(borderChung,"");
//        titledBorderTiengViet.setTitleJustification(TitledBorder.CENTER);

    JPanel pnMain = new JPanel();
    pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

    JPanel pnTitle = new JPanel();
    pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
    JLabel lblTitle = new JLabel("API Google Translate");
    lblTitle.setFont(titleFont);
    pnTitle.add(lblTitle);

    JPanel pnTraCuu = new JPanel();
    pnTraCuu.setLayout(new FlowLayout());

    JPanel pnTrongLeft = new JPanel();
    JPanel pnTrongMid = new JPanel();
    JPanel pnTrongRight = new JPanel();

    JPanel pnTraCuuNgonNguKhac = new JPanel();
    pnTraCuuNgonNguKhac.setLayout(new BoxLayout(pnTraCuuNgonNguKhac, BoxLayout.Y_AXIS));
    JPanel pnPhu = new JPanel();
    pnPhu.setLayout(new FlowLayout(FlowLayout.CENTER));
    lblTuDong = new JLabel("Tự động");
    lblTuDong.setFont(fontInText);
    txtAreaCanTra = new JTextArea();
    txtAreaCanTra.setFont(fontInText);
    txtAreaCanTra.setWrapStyleWord(true);
    txtAreaCanTra.setLineWrap(true);
    pnPhu.add(lblTuDong);
    pnTraCuuNgonNguKhac.add(pnPhu);
    pnTraCuuNgonNguKhac.add(txtAreaCanTra);

    JPanel pnNghiaNhanLai = new JPanel();
    pnNghiaNhanLai.setLayout(new BoxLayout(pnNghiaNhanLai, BoxLayout.Y_AXIS));
    cbChonNgonNguNhanLai = new JComboBox<String>();
    cbChonNgonNguNhanLai.addItem("Vietnamese");
    cbChonNgonNguNhanLai.addItem("English");
    cbChonNgonNguNhanLai.addItem("Japanese");
    cbChonNgonNguNhanLai.addItem("Indonesian");
    cbChonNgonNguNhanLai.addItem("French");
    cbChonNgonNguNhanLai.addItem("German");
    cbChonNgonNguNhanLai.setFont(fontInText);
    txtAreaNhanLai = new JTextArea();
    pnNghiaNhanLai.add(cbChonNgonNguNhanLai);
    pnNghiaNhanLai.add(txtAreaNhanLai);

    pnTraCuu.add(pnTrongLeft);
    pnTraCuu.add(pnTraCuuNgonNguKhac);
    pnTraCuu.add(pnTrongMid);
    pnTraCuu.add(pnNghiaNhanLai);
    pnTraCuu.add(pnTrongRight);

    JPanel pnButtonTraCuu = new JPanel();
    pnButtonTraCuu.setLayout(new FlowLayout(FlowLayout.CENTER));
    btnTraCuu = new JButton();
    btnTraCuu.setIcon(new ImageIcon("Hinh\\icon\\search.png"));
    btnTraCuu.setFont(generalFont);
    pnButtonTraCuu.add(btnTraCuu);

    JPanel pnButtonBack = new JPanel();
    pnButtonBack.setLayout(new FlowLayout(FlowLayout.RIGHT));
    btnBack = new JButton();
    btnBack.setIcon(new ImageIcon("Hinh\\icon\\back.png"));
    btnBack.setFont(generalFont);
    pnButtonBack.add(btnBack);

    pnMain.add(pnTitle);
    pnTitle.setPreferredSize(new Dimension(600, 80));

    pnMain.add(pnTraCuu);
    lblTuDong.setPreferredSize(cbChonNgonNguNhanLai.getPreferredSize());
    pnTraCuu.setPreferredSize(new Dimension(600, 420));
    pnTrongLeft.setPreferredSize(new Dimension(5, 400));
    pnTrongMid.setPreferredSize(new Dimension(5, 400));
    pnTrongRight.setPreferredSize(new Dimension(5, 400));
    pnTraCuuNgonNguKhac.setPreferredSize(new Dimension(270, 400));
    pnNghiaNhanLai.setPreferredSize(new Dimension(270, 400));
    pnPhu.setPreferredSize(cbChonNgonNguNhanLai.getPreferredSize());

    pnMain.add(pnButtonTraCuu);
    pnButtonTraCuu.setPreferredSize(new Dimension(600, 50));

    pnMain.add(pnButtonBack);
    pnButtonBack.setPreferredSize(new Dimension(600, 50));

    return pnMain;
  }

  public void showWindow() {
    this.setSize(600, 600);// set size
    this.setDefaultCloseOperation(
        WindowConstants.EXIT_ON_CLOSE);// thêm chức năng thoát hoàn toàn khỏi chương trình vào nút đỏ
    this.setLocationRelativeTo(null);// để giao diện nằm chính giữa
    this.setResizable(false);// không cho phép thay đổi kích cỡ cửa sổ
    this.setVisible(true);// để giao diện hiện lên

  }
}
