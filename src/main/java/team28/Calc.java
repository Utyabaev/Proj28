package team28;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.TextField;
import java.io.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.*;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.BaseFont;
import team28.MyDocumentFilter;

import java.io.File;
import javax.swing.JFileChooser;

public class Calc extends JFrame {
    private MyDocumentFilter documentFilter;
    JTextField tiraj_field, tiraj_field2, result;
    int mn_size_leaflets = 0,mn_size_bcards = 0, mn_format = 0, mn_lamination = 0, mn_color = 0, mn_product = 1; double discount = 0;
    JCheckBox card_y, card_n, leaflets, bcards;
    boolean test;

    public static double calc_bcards(int tiraj, int paper, int phormat, int lam, int ang, int type, int dis){
        double price = 0;
        if (paper == 1) {
            price +=1;
        }
        if (paper == 2) {
            price +=5;
        }
        if (paper == 3) {
            price +=2;
        }
        if (paper == 4) {
            price +=6;
        }
        if (phormat == 1) {
            price +=10;
        }
        if (phormat == 2) {
            price +=9;
        }
        if (lam == 1) {
            price +=40;
        }
        if (ang == 1) {
            price +=30;
        }
        if (type == 1) {
            price +=9;
        }
        if (type == 2) {
            price +=4;
        }
        if (type == 3) {
            price +=3;
        }
        if (type == 4) {
            price +=1;
        }
        if (dis == 1) {
            price -= price * 0.15;
        }
        return price * tiraj;
    }

    public Calc() {

        final Error_msg error_msg_paper = new Error_msg();
        final Error_msg error_msg_tiraj = new Error_msg();
        final Error_msg error_msg_phormat = new Error_msg();
        final Error_msg error_msg_perforation = new Error_msg();
        final  Error_msg error_msg_type = new Error_msg();

        final JFrame frame = new JFrame("Êàëüêóëÿòîð ñòîèìîñòè ïðîèçâîäñòâà ðåêëàìíûõ ìàòåðèàëîâ. Ïîëèãðàôèÿ.");
        frame.setLayout(null);
        frame.setSize(490, 650);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        final JPanel contents = new JPanel(new FlowLayout());
        contents.setLayout(null);
        contents.setBounds(0,15,490, 650);
        frame.setVisible(false);

        JMenuBar bar = new JMenuBar();
        frame.add(bar);
        bar.setVisible(true);
        bar.setSize(490,15);

        JMenu createFile = new JMenu("Ôàéë");
        bar.add(createFile);
        final JMenuItem pdf = new JMenuItem("Âûãðóçèòü â PDF");
        createFile.add(pdf);

        JMenu user = new JMenu("Àêêàóíò");
        bar.add(user);
        JMenuItem logout = new JMenuItem("Âûéòè èç àêêàóíòà");
        user.add(logout);
        final JMenuItem admin = new JMenuItem("Ïàíåëü óïðàâëåíèÿ");
        user.add(admin);
        admin.setVisible(false);

        final JFrame admin_panel = new JFrame("Ïàíåëü óïðàâëåíèÿ");
        admin_panel.setLayout(null);
        admin_panel.setSize(365, 450);
        admin_panel.setResizable(false);
        admin_panel.setLocationRelativeTo(null);

        JLabel color_label = new JLabel("Èçìåíèòü öâåò");
        color_label.setBounds(100,270,100,20);
        admin_panel.add(color_label);

        String[] colors = {"Ñòàíäàðòíûé","Êðàñíûé","Æåëòûé","Çåëåíûé","Ðîçîâûé"};
        final JComboBox color = new JComboBox(colors);
        color.setBounds(100, 290, 150, 30);
        admin_panel.add(color);

        JLabel p1_label = new JLabel("Áóìàãà");
        p1_label.setBounds(20,30,200,20);
        admin_panel.add(p1_label);

        JLabel f1_label = new JLabel("Ôîðìàò");
        f1_label.setBounds(20,90,200,20);
        admin_panel.add(f1_label);

        JLabel perf_label = new JLabel("Ïåðôîðàöèÿ");
        perf_label.setBounds(20,150,200,20);
        admin_panel.add(perf_label);

        JLabel type1_label = new JLabel("Òèï ïå÷àòè");
        type1_label.setBounds(20,210,200,20);
        admin_panel.add(type1_label);

        JLabel p2_label = new JLabel("Áóìàãà");
        p2_label.setBounds(180,30,200,20);
        admin_panel.add(p2_label);

        JLabel f2_label = new JLabel("Ôîðìàò");
        f2_label.setBounds(180,90,60,20);
        admin_panel.add(f2_label);

        JLabel angles_label = new JLabel("Ëàìèíàöèÿ");
        angles_label.setBounds(180,150,80,20);
        admin_panel.add(angles_label);

        JLabel lam_label = new JLabel("Ñêð. óãëîâ");
        lam_label.setBounds(260,150,80,20);
        admin_panel.add(lam_label);

        JLabel type2_label = new JLabel("Òèï ïå÷àòè");
        type2_label.setBounds(180,210,200,20);
        admin_panel.add(type2_label);

        JLabel leaf_label = new JLabel("Ëèñòîâêè");
        leaf_label.setBounds(20,5,200,20);
        admin_panel.add(leaf_label);

        JLabel bc_label = new JLabel("Âèçèòêè");
        bc_label.setBounds(180,5,200,20);
        admin_panel.add(bc_label);

        final JTextField paper_field1 = new JTextField(15);
        ((AbstractDocument) paper_field1.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        paper_field1.setBounds(20, 50, 150, 30);
        admin_panel.add(paper_field1);

        final JTextField format_field1 = new JTextField(15);
        ((AbstractDocument) format_field1.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        format_field1.setBounds(20, 110, 150, 30);
        admin_panel.add(format_field1);

        final JTextField perf_field = new JTextField(15);
        ((AbstractDocument) perf_field.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        perf_field.setBounds(20, 170, 150, 30);
        admin_panel.add(perf_field);

        final JTextField type_field1 = new JTextField(15);
        ((AbstractDocument) type_field1.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        type_field1.setBounds(20, 230, 150, 30);
        admin_panel.add(type_field1);

        final JTextField paper_field2 = new JTextField(15);
        ((AbstractDocument) paper_field2.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        paper_field2.setBounds(180, 50, 150, 30);
        admin_panel.add(paper_field2);

        final JTextField phormat_field2 = new JTextField(15);
        ((AbstractDocument) phormat_field2.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        phormat_field2.setBounds(180, 110, 150, 30);
        admin_panel.add(phormat_field2);

        final JTextField lam_field = new JTextField(15);
        ((AbstractDocument) lam_field.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        lam_field.setBounds(180, 170, 70, 30);
        admin_panel.add(lam_field);

        final JTextField ang_field = new JTextField(15);
        ((AbstractDocument) ang_field.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        ang_field.setBounds(260, 170, 70, 30);
        admin_panel.add(ang_field);

        final JTextField type_field2 = new JTextField(15);
        ((AbstractDocument) type_field2.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        type_field2.setBounds(180, 230, 150, 30);
        admin_panel.add(type_field2);

        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin_panel.setVisible(true);
            }
        });

        JLabel tiraj_text2 = new JLabel("Òèðàæ");
        tiraj_text2.setBounds(150, 45, 200, 15);
        contents.add(tiraj_text2);

        tiraj_field = new JTextField(15);
        ((AbstractDocument) tiraj_field.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        tiraj_field.setBounds(150, 60, 200, 25);
        contents.add(tiraj_field);
        tiraj_field.setEnabled(false);

        final JCheckBox leaflets = new JCheckBox("Ëèñòîâêè");
        leaflets.setBounds (160,15,100,25);
        contents.add(leaflets);

        final JCheckBox bcards = new JCheckBox("Âèçèòêè");
        bcards.setBounds(260,15,80,25);
        contents.add(bcards);

        leaflets.setEnabled(true);
        bcards.setEnabled(true);

        final JFrame fauth = new JFrame("subversion");
        fauth.setTitle ("Àâòîðèçàöèÿ");
        fauth.setBounds(500,400,400,400);
        fauth.setLocationRelativeTo(null);
        fauth.setResizable(false);

        logout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setVisible(false);
                fauth.setVisible(true);
                admin_panel.setVisible(false);
            }
        });

        JPanel main_panel2 = new JPanel();
        main_panel2.setLayout(null);
        fauth.add(main_panel2);
        main_panel2.setBounds(500,400,400,400);

        final JTextField login = new JTextField(15);
        login.setBounds(100,20,200,25);
        main_panel2.add(login);

        JLabel login_label = new JLabel("Ëîãèí:");
        login_label.setBounds(40,20,600,20);
        main_panel2.add(login_label);

        final JPasswordField pass = new JPasswordField(15);
        pass.setBounds(100,60,200,25);
        main_panel2.add(pass);

        JLabel pass_label = new JLabel("Ïàðîëü:");
        pass_label.setBounds(40,60,600,20);
        main_panel2.add(pass_label);

        JButton enter = new JButton("Âõîä");
        enter.setBounds(150, 110, 100, 25);
        main_panel2.add(enter);

        final User Admin = new User();
        final User Guest = new User();
        final User user128 = new User();
        final User user228 = new User();
        final User user328 = new User();
        final User user428 = new User();

        Admin.setUser("1","1","admin");
        Guest.setUser("2","2","user");
        user128.setUser("user128", "user128","admin");
        user228.setUser("user228", "user228","admin");
        user328.setUser("user328", "user328","admin");
        user428.setUser("user428", "user428","admin");



        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String log_info = login.getText() + " " + pass.getText();

                if (    ((Admin.getLogin() + " " + Admin.getPass()).equals(log_info)) ||
                        ((Guest.getLogin() + " " + Guest.getPass()).equals(log_info)) ||
                        ((user128.getLogin() + " " + user128.getPass()).equals(log_info)) ||
                        ((user228.getLogin() + " " + user228.getPass()).equals(log_info)) ||
                        ((user328.getLogin() + " " + user328.getPass()).equals(log_info)) ||
                        ((user428.getLogin() + " " + user428.getPass()).equals(log_info))

                ) {
                    frame.setVisible(true);
                    fauth.setVisible(false);
                    login.setText("");
                    pass.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Íåâåðíûé ëîãèí èëè ïàðîëü.", "Âíèìàíèå", JOptionPane.ERROR_MESSAGE);
                    pass.setText("");
                }
            }

        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String role;

                if (login.getText().equals(Admin.getLogin()) ||
                        login.getText().equals(user128.getLogin()) ||
                        login.getText().equals(user228.getLogin()) ||
                        login.getText().equals(user328.getLogin()) ||
                        login.getText().equals(user428.getLogin())
                ) {
                    role = Admin.getRole();
                } else {
                    role = "user";
                }

                if ( role == "admin" ) {
                    admin.setVisible(true);
                } else if (role == "user") {
                    admin.setVisible(false);
                }
            }
        });

        JButton apply = new JButton("Ïðèìåíèòü");
        apply.setBounds(100,330,150,30);
        admin_panel.add(apply);

        JButton q = new JButton("?");
        q.setBounds(20,290,60,30);
        admin_panel.add(q);

        final Color c = new Color(238,238,238);

        q.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Â ïîëÿõ íåîáõîäèìî ââåñòè íà ñêîëüêî íóíæî óâåëè÷èòü êîýôôèöèåíòû.", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String color_switch = (String)color.getSelectedItem();
                switch (color_switch) {
                    case "Ñòàíäàðòíûé":
                        contents.setBackground(c);
                        break;
                    case "Êðàñíûé":
                        contents.setBackground(Color.red);
                        break;
                    case "Æåëòûé":
                        contents.setBackground(Color.yellow);
                        break;
                    case "Çåëåíûé":
                        contents.setBackground(Color.green);
                        break;
                    case "Ðîçîâûé":
                        contents.setBackground(Color.pink);
                        break;
                }
            }
        });

        JButton back = new JButton("Íàçàä");
        back.setBounds(100, 370, 150, 30);
        admin_panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin_panel.setVisible(false);
            }
        });



        fauth.setSize(400,180);

        String[] ch_paper = {"--ÂÛÁÅÐÈÒÅ--","105ãð ìåëîâàííàÿ","170ãð ìåëîâàííàÿ","300ãð ìåëîâàííàÿ","80ãð îôñåòíàÿ"};
        final JComboBox paper = new JComboBox(ch_paper);
        paper.setBounds(20, 110, 200, 25);
        contents.add(paper);
        paper.setEnabled(false);

        String[] ch_paper2 = {"--ÂÛÁÅÐÈÒÅ--","200ãð ãëÿíöåâàÿ","300ãð ãëÿíöåâàÿ","200ãð ìàòîâàÿ","300ãð ìàòîâàÿ"};
        final JComboBox paper2 = new JComboBox(ch_paper2);
        paper2.setBounds(250, 110, 200, 25);
        contents.add(paper2);
        paper2.setEnabled(false);

        String[] ch_phormat_leaflets = {"--ÂÛÁÅÐÈÒÅ--","A4","A5","A6","A7"};
        final JComboBox ch_phormat_leaflets2 = new JComboBox(ch_phormat_leaflets);
        ch_phormat_leaflets2.setBounds  (20, 160, 200, 25);
        contents.add(ch_phormat_leaflets2);
        ch_phormat_leaflets2.setEnabled(false);

        String[] ch_phormat_bcards = {"--ÂÛÁÅÐÈÒÅ--","Ñòàíäàðò (50õ90ìì)","Åâðî (55õ85ìì)"};
        final JComboBox ch_phormat_bcards2 = new JComboBox(ch_phormat_bcards);
        ch_phormat_bcards2.setBounds(250, 160, 200, 25);
        contents.add(ch_phormat_bcards2);
        ch_phormat_bcards2.setEnabled(false);

        JLabel perforation_text = new JLabel("Ïåðôîðàöèÿ");
        perforation_text.setBounds(20, 195, 200, 15);
        contents.add(perforation_text);

        String[] ch_perforation = {"--ÂÛÁÅÐÈÒÅ--","1 ñãèá/ëèíèÿ","2 ñãèáà/ëèíèè","3 ñãèáà/ëèíèè","4 ñãèáà/ëèíèè"};
        final JComboBox ch_perforation2 = new JComboBox(ch_perforation);
        ch_perforation2.setBounds(20, 210, 200, 25);
        contents.add(ch_perforation2);
        ch_perforation2.setEnabled(false);

        JLabel color_text = new JLabel("Äîï. îáðàáîòêà");
        color_text.setBounds(250, 195, 200, 15);
        contents.add(color_text);

        final JCheckBox lamination = new JCheckBox("Ëàìèíàöèÿ");
        lamination .setBounds(250,210,90,25);
        contents.add(lamination);
        lamination.setEnabled(false);

        final JCheckBox angles = new JCheckBox("Ñêðóãëåíèå óãëîâ");
        angles .setBounds(340,210,130,25);
        contents.add(angles);
        angles.setEnabled(false);

        JLabel print_type = new JLabel("Òèï ïå÷àòè");
        print_type.setBounds(20, 245, 200, 15);
        contents.add(print_type);

        String[] ch_type_leaflets = {"--ÂÛÁÅÐÈÒÅ--","4+4 (äâóñòîðîííÿÿ)","4+0 (îäíîñòîðîííÿÿ)","1+0 (÷/á îäíîñòîðîííÿÿ)","1+1 (÷/á äâóñòîðîííÿÿ)"};
        final JComboBox ch_type_leaflets2 = new JComboBox(ch_type_leaflets);
        ch_type_leaflets2.setBounds(20, 260, 200, 25);
        contents.add(ch_type_leaflets2);
        ch_type_leaflets2.setEnabled(false);

        JLabel print_type2 = new JLabel("Òèï ïå÷àòè");
        print_type2.setBounds(250, 245, 200, 15);
        contents.add(print_type2);

        String[] ch_type_bcards = {"--ÂÛÁÅÐÈÒÅ--","4+4 (äâóñòîðîííÿÿ)","4+0 (îäíîñòîðîííÿÿ)","1+0 (÷/á îäíîñòîðîííÿÿ)","1+1 (÷/á äâóñòîðîííÿÿ)"};
        final JComboBox ch_type_bcards2 = new JComboBox(ch_type_bcards);
        ch_type_bcards2.setBounds(250, 260, 200, 25);
        contents.add(ch_type_bcards2);
        ch_type_bcards2.setEnabled(false);

        JLabel card = new JLabel("Äèñêîíòíàÿ êàðòà (15% ñêèäêà)");
        card.setBounds(135, 310, 232, 15);
        contents.add(card);

        card_y = new JCheckBox("Åñòü");
        card_y.setBounds(135, 325, 75, 25);
        contents.add(card_y);
        card_y.setEnabled(false);

        card_n = new JCheckBox("Íåò", true);
        card_n.setBounds(230, 325, 75, 25);
        contents.add(card_n);
        card_n.setEnabled(false);

        JLabel size_leaflets = new JLabel("Ôîðìàò");
        size_leaflets.setBounds(250, 145, 200, 15);
        contents.add(size_leaflets);

        JLabel size_bcards = new JLabel("Ôîðìàò");
        size_bcards.setBounds(20, 145, 200, 15);
        contents.add(size_bcards);

        JLabel material_leaflets = new JLabel("Áóìàãà");
        material_leaflets.setBounds(250, 95, 200, 15);
        contents.add(material_leaflets);

        JLabel material_bcards = new JLabel("Áóìàãà");
        material_bcards.setBounds(20, 95, 200, 15);
        contents.add(material_bcards);

        leaflets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leaflets.isSelected()) {
                    bcards.setSelected(false);
                }
            }
        });

        if (bcards.isSelected() == false) {
            test = false;
        }



        leaflets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(leaflets.isSelected())) {
                    leaflets.setSelected(true);
                }
            }
        });

        bcards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bcards.isSelected()) {
                    leaflets.setSelected(false);
                    mn_size_bcards = 0;
                }
            }
        });

        bcards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(bcards.isSelected())) {
                    bcards.setSelected(true);
                }
            }
        });

        card_y.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !(card_n.isSelected()) ) {
                    card_y.setSelected(true);
                }
            }
        });

        card_y.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (card_n.isSelected()) {
                    card_n.setSelected(false);
                }
            }
        });

        card_n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !(card_y.isSelected()) ) {
                    card_n.setSelected(true);
                }
            }
        });

        card_n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (card_y.isSelected()) {
                    card_y.setSelected(false);
                }
            }
        });

        JLabel result_text = new JLabel("Ñòîèìîñòü ïðîèçâîäñòâà ðåêëàìíûõ ìàòåðèàëîâ:");
        result_text.setBounds(90,355,352,30);
        contents.add(result_text);

        final JTextField result = new JTextField();
        result.setBounds(110, 380, 250, 30);
        result.setText(" 0");
        result.setEditable(false);
        contents.add(result);

        final JButton calculate = new JButton("Ðàññ÷èòàòü");
        calculate.setBounds(160, 420, 150, 30);
        contents.add(calculate);
        calculate.setEnabled(false);

        final JButton close = new JButton("ÂÛÕÎÄ");
        close.setBounds(160, 460, 150, 30);
        contents.add(close);

        final JButton info = new JButton("Èíôîðìàöèÿ");
        info.setBounds(160, 500, 150, 30);
        ActionListener informationListener = new Info();
        info.addActionListener(informationListener);
        contents.add(info);

       /* ActionListener pdfListener = new CreatePDF();
        pdf.addActionListener(pdfListener);*/

        leaflets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card_y.setEnabled(true);
                card_n.setEnabled(true);
                tiraj_field.setEnabled(true);
                paper.setEnabled(true);
                ch_phormat_leaflets2.setEnabled(true);
                ch_perforation2.setEnabled(true);
                ch_type_leaflets2.setEnabled(true);
                calculate.setEnabled(true);
                paper2.setEnabled(false);
                ch_phormat_bcards2.setEnabled(false);
                lamination.setEnabled(false);
                angles.setEnabled(false);
                ch_type_bcards2.setEnabled(false);
            }
        });

        bcards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiraj_field.setEnabled(true);
                card_y.setEnabled(true);
                card_n.setEnabled(true);
                paper2.setEnabled(true);
                ch_phormat_bcards2.setEnabled(true);
                lamination.setEnabled(true);
                angles.setEnabled(true);
                ch_type_bcards2.setEnabled(true);
                calculate.setEnabled(true);
                paper.setEnabled(false);
                ch_phormat_leaflets2.setEnabled(false);
                ch_perforation2.setEnabled(false);
                ch_type_leaflets2.setEnabled(false);
            }
        });

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int paper_leaflets_val = 0, paper_bcards_val = 0, phormat_leaflets_val = 0, phormat_bcards_val = 0,
                        perforation_val = 0, lam_val = 0, ang_val = 0, type_leaflets_val = 0, type_bcards_val = 0, tiraj_val = 0;

                if ( card_y.isSelected()) {
                    discount = 0.15;
                }

                Paper leaflets_pap = new Paper();
                Paper bcards_pap = new Paper();

                String p1_val_str = paper_field1.getText();
                if (paper_field1.getText().isEmpty()) {
                    p1_val_str = "0";
                }
                int p1_val = Integer.parseInt(p1_val_str);

                String f1_val_str = format_field1.getText();
                if (format_field1.getText().isEmpty()) {
                    f1_val_str = "0";
                }
                int f1_val = Integer.parseInt(f1_val_str);

                String perf_val_str = perf_field.getText();
                if (perf_field.getText().isEmpty()) {
                    perf_val_str = "0";
                }
                int perf_val = Integer.parseInt(perf_val_str);

                String type1_val_str = type_field1.getText();
                if (type_field1.getText().isEmpty()) {
                    type1_val_str = "0";
                }
                int type1_val = Integer.parseInt(type1_val_str);

                String paper2_val_str = paper_field2.getText();
                if (paper_field2.getText().isEmpty()) {
                    paper2_val_str = "0";
                }
                int paper2_val = Integer.parseInt(paper2_val_str);

                String f2_val_str = phormat_field2.getText();
                if (phormat_field2.getText().isEmpty()) {
                    f2_val_str = "0";
                }
                int f2_val = Integer.parseInt(f2_val_str);

                String lam_val_str = lam_field.getText();
                if (lam_field.getText().isEmpty()) {
                    lam_val_str = "0";
                }
                int lamin_val = Integer.parseInt(lam_val_str);

                String t2_val_str = type_field2.getText();
                if (type_field2.getText().isEmpty()) {
                    t2_val_str = "0";
                }
                int t2_val = Integer.parseInt(t2_val_str);

                String msg = (String)paper.getSelectedItem();
                switch (msg) {
                    case "--ÂÛÁÅÐÈÒÅ--":
                        leaflets_pap.setPrice(0+p1_val);
                        break;
                    case "105ãð ìåëîâàííàÿ":
                        leaflets_pap.setPrice(2+p1_val);
                        break;
                    case "170ãð ìåëîâàííàÿ":
                        leaflets_pap.setPrice(3+p1_val);
                        break;
                    case "300ãð ìåëîâàííàÿ":
                        leaflets_pap.setPrice(4+p1_val);
                        break;
                    case "80ãð îôñåòíàÿ":
                        leaflets_pap.setPrice(5+p1_val);
                        break;

                }

                String msg2 = (String)paper2.getSelectedItem();
                switch (msg2) {
                    case "--ÂÛÁÅÐÈÒÅ--":
                        bcards_pap.setPrice(0+paper2_val);
                        break;
                    case "200ãð ãëÿíöåâàÿ":
                        bcards_pap.setPrice(1+paper2_val);
                        break;
                    case "300ãð ãëÿíöåâàÿ":
                        bcards_pap.setPrice(5+paper2_val);
                        break;
                    case "200ãð ìàòîâàÿ":
                        bcards_pap.setPrice(2+paper2_val);
                        break;
                    case "300ãð ìàòîâàÿ":
                        bcards_pap.setPrice(6+paper2_val);
                        break;

                }

                Phormat ph_leaflets = new Phormat();
                Phormat ph_bcards = new Phormat();

                String msg3 = (String)ch_phormat_leaflets2.getSelectedItem();
                switch (msg3) {
                    case "--ÂÛÁÅÐÈÒÅ--":
                        ph_leaflets.setPrice(0+f1_val);
                        break;
                    case "A4":
                        ph_leaflets.setPrice(10+f1_val);
                        break;
                    case "A5":
                        ph_leaflets.setPrice(8+f1_val);
                        break;
                    case "A6":
                        ph_leaflets.setPrice(6+f1_val);
                        break;
                    case "A7":
                        ph_leaflets.setPrice(4+f1_val);
                        break;

                }

                String msg4 = (String)ch_phormat_bcards2.getSelectedItem();
                switch (msg4) {
                    case "--ÂÛÁÅÐÈÒÅ--":
                        ph_bcards.setPrice(0+f2_val);
                        break;
                    case "Ñòàíäàðò (50õ90ìì)":
                        ph_bcards.setPrice(10+f2_val);
                        break;
                    case "Åâðî (55õ85ìì)":
                        ph_bcards.setPrice(9+f2_val);
                        break;

                }

                Perforation per = new Perforation();

                String msg5 = (String)ch_perforation2.getSelectedItem();
                switch (msg5) {
                    case "--ÂÛÁÅÐÈÒÅ--":
                        per.setPrice(0+perf_val);
                        break;
                    case "1 ñãèá/ëèíèÿ":
                        per.setPrice(2+perf_val);
                        break;
                    case "2 ñãèáà/ëèíèè":
                        per.setPrice(4+perf_val);
                        break;
                    case "3 ñãèáà/ëèíèè":
                        per.setPrice(6+perf_val);
                        break;
                    case "4 ñãèáà/ëèíèè":
                        per.setPrice(8+perf_val);
                        break;

                }

                Typee t_leaflets = new Typee();
                Typee t_bcards = new Typee();


                String msg6 = (String)ch_type_leaflets2.getSelectedItem();
                switch (msg6) {
                    case "--ÂÛÁÅÐÈÒÅ--":
                        t_leaflets.setPrice(0+type1_val);
                        break;
                    case "4+4 (äâóñòîðîííÿÿ)":
                        t_leaflets.setPrice(10+type1_val);
                        break;
                    case "4+0 (îäíîñòîðîííÿÿ)":
                        t_leaflets.setPrice(5+type1_val);
                        break;
                    case "1+0 (÷/á îäíîñòîðîííÿÿ)":
                        t_leaflets.setPrice(2+type1_val);
                        break;
                    case "1+1 (÷/á äâóñòîðîííÿÿ)":
                        t_leaflets.setPrice(1+type1_val);
                        break;

                }

                String msg7 = (String)ch_type_bcards2.getSelectedItem();
                switch (msg7) {
                    case "--ÂÛÁÅÐÈÒÅ--":
                        t_bcards.setPrice(0+t2_val);
                        break;
                    case "4+4 (äâóñòîðîííÿÿ)":
                        t_bcards.setPrice(9+t2_val);
                        break;
                    case "4+0 (îäíîñòîðîííÿÿ)":
                        t_bcards.setPrice(4+t2_val);
                        break;
                    case "1+0 (÷/á îäíîñòîðîííÿÿ)":
                        t_bcards.setPrice(3+t2_val);
                        break;
                    case "1+1 (÷/á äâóñòîðîííÿÿ)":
                        t_bcards.setPrice(1+t2_val);
                        break;

                }

                Lamination lam = new Lamination();
                Angles ang = new Angles();

                if (lamination.isSelected()) {
                    lam.setPrice(40+lamin_val);
                }

                String ang_val_str = ang_field.getText();
                if (ang_field.getText().isEmpty()) {
                    ang_val_str = "0";
                }
                int angles_val = Integer.parseInt(ang_val_str);

                if (angles.isSelected()) {
                    ang.setPrice(30 + angles_val);
                }


                if(tiraj_field.getText().trim().equals("0") || tiraj_field.getText().trim().isEmpty())
                {
                    error_msg_tiraj.crash_tiraj();
                    return;
                }

                paper_leaflets_val = leaflets_pap.getPrice();
                phormat_leaflets_val = ph_leaflets.getPrice();
                perforation_val = per.getPrice();
                type_leaflets_val = t_leaflets.getPrice();

                paper_bcards_val = bcards_pap.getPrice();
                phormat_bcards_val = ph_bcards.getPrice();
                type_bcards_val= t_bcards.getPrice();
                lam_val = lam.getPrice();
                ang_val = ang.getPrice();

                if ( (leaflets.isSelected()) || (bcards.isSelected()) ) {
                    String tiraj_val_str = tiraj_field.getText();
                    tiraj_val = Integer.parseInt(tiraj_val_str);
                }

                if ( (paper_leaflets_val == 0) && leaflets.isSelected())  {
                    error_msg_paper.crash_paper();
                    return;
                }

                if ( (paper_bcards_val == 0) && bcards.isSelected())  {
                    error_msg_paper.crash_paper();
                    return;
                }

                if ( (phormat_leaflets_val == 0) && (leaflets.isSelected()) ) {
                    error_msg_phormat.crash_phormat();
                    return;
                }

                if ( (phormat_bcards_val == 0) && (bcards.isSelected()) ) {
                    error_msg_phormat.crash_phormat();
                    return;
                }

                if ( (perforation_val == 0) && leaflets.isSelected() ) {
                    error_msg_perforation.crash_perforation();
                    return;
                }

                if ( (type_leaflets_val == 0) && leaflets.isSelected() ) {
                    error_msg_type.crash_type();
                    return;
                }

                if ( (type_bcards_val == 0) && bcards.isSelected() ) {
                    error_msg_type.crash_type();
                    return;
                }

                if (leaflets.isSelected()) {
                    double rezz1 = (paper_leaflets_val + phormat_leaflets_val + perforation_val + type_leaflets_val) * tiraj_val;
                    double rez1 = rezz1 - rezz1 * discount;
                    final String rez1_value = ""+rez1;

                    final int finalTiraj_val1 = tiraj_val;
                    final int finalPaper_leaflets_val1 = paper_leaflets_val;
                    final int finalPhormat_leaflets_val1 = phormat_leaflets_val;
                    final int finalPerforation_val1 = perforation_val;
                    final int finalType_leaflets_val1 = type_leaflets_val;
                    pdf.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {

                                JOptionPane.showMessageDialog(null, "Óñïåøíî!", "Âûãðóçèòü â PDF", JOptionPane.INFORMATION_MESSAGE);

                                String file_name = "Èòîã.pdf";
                                Document doc = new Document();

                                PdfWriter.getInstance(doc, new FileOutputStream(file_name));

                                doc.open();

                                Font font = FontFactory.getFont(FontFactory.HELVETICA, "cp1251", BaseFont.EMBEDDED);

                                Paragraph para = new Paragraph("Listovki: " +
                                        "\nTiraj: " + finalTiraj_val1 +
                                        "\nBumaga: " + finalPaper_leaflets_val1 +
                                        "\nFormat: " + finalPhormat_leaflets_val1 +
                                        "\nPerforatsia: " + finalPerforation_val1 +
                                        "\nTip: " + finalType_leaflets_val1 +
                                        "\nSkidka: " + discount +
                                        "\nCena: " + rez1_value, font);;

                                doc.add(para);
                                doc.close();

                            } catch (Exception ee) {
                                System.err.println(ee);
                            }

                            Desktop desktop = null;
                            if (Desktop.isDesktopSupported()) {
                                desktop = Desktop.getDesktop();
                            }

                            try {
                                desktop.open(new File("Èòîã.pdf"));
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }

                        }
                    });

                    result.setText(rez1_value+" Ðóáëåé");
                    discount = 0;
                    mn_size_leaflets = 0;
                    return;
                }

                if (bcards.isSelected()) {
                    double rezz2 = (paper_bcards_val + phormat_bcards_val + type_bcards_val + lam_val + ang_val) * tiraj_val;
                    double rez4 = rezz2 - rezz2 * discount;
                    final String rez4_value = ""+rez4;

                    final int finalTiraj_val2 = tiraj_val;
                    final int finalPaper_bcards_val = paper_bcards_val;
                    final int finalPhormat_bcards_val = phormat_bcards_val;
                    final int finalType_bcards_val = type_bcards_val;
                    final int finalLam_val = lam_val;
                    final int finalAng_val = ang_val;
                    pdf.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {

                                JOptionPane.showMessageDialog(null, "Óñïåøíî!", "Âûãðóçèòü â PDF", JOptionPane.INFORMATION_MESSAGE);

                                String file_name = "Èòîã.pdf";
                                Document doc = new Document();

                                PdfWriter.getInstance(doc, new FileOutputStream(file_name));

                                doc.open();

                                Font font = FontFactory.getFont(FontFactory.HELVETICA, "cp1251", BaseFont.EMBEDDED);

                                Paragraph para = new Paragraph("Vizitki" +
                                        "\nTiraj: " + finalTiraj_val2 +
                                        "\nBumaga: " + finalPaper_bcards_val +
                                        "\nFormat: " + finalPhormat_bcards_val +
                                        "\nTip: " + finalType_bcards_val +
                                        "\nLaminatsia: " + finalLam_val +
                                        "\nSKruglenie Uglov: " + finalAng_val +
                                        "\nSkidka: " + discount +
                                        "\nCena: " + rez4_value);

                                doc.add(para);
                                doc.close();

                                para.clear();

                            } catch (Exception ee) {
                                System.err.println(ee);
                            }

                            System.out.println(discount);

                            Desktop desktop = null;
                            if (Desktop.isDesktopSupported()) {
                                desktop = Desktop.getDesktop();
                            }

                            try {
                                desktop.open(new File("Èòîã.pdf"));
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }

                        }
                    });
                    result.setText(rez4_value+" Ðóáëåé");
                    discount = 0;
                    mn_size_bcards = 0;
                    return;
                }
            }
        });



        setContentPane(contents);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        frame.add(contents);

        frame.setLayout(null);

        contents.setVisible(true);
        fauth.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fauth.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                new Calc();
            }
        };
        EventQueue.invokeLater(runnable);

    }

}
