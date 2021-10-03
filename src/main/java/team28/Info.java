package team28;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Info implements ActionListener {

	public String madeby = "Разработчики", u2 = "Тукаев А.И. - Разработчик 1", u3 = "Филатов И.М. - Разработчик 2", u4 = "Куликова А.О. - Разработчик 3";

	public void actionPerformed(ActionEvent arg0) {

		final JFrame main_info = new JFrame("subversion");
		main_info.setTitle ("Информация");
		main_info.setBounds(500,400,400,340);
		main_info.setLocationRelativeTo(null);
		main_info.setResizable(false);
		main_info.setVisible(true);

		JPanel main_panel = new JPanel();
		main_panel.setLayout(null);
		main_info.add(main_panel);
		main_panel.setBounds(500,400,400,340);

		JLabel laba_madeby = new JLabel(madeby);
		laba_madeby.setBounds(145,10,300,25);
		main_panel.add(laba_madeby);

		JLabel user228 = new JLabel(u2);
		user228.setBounds(105,35,300,25);
		main_panel.add(user228);

		JLabel user328 = new JLabel(u3);
		user328.setBounds(97,60,300,25);
		main_panel.add(user328);

		JLabel user428 = new JLabel(u4);
		user428.setBounds(95,85,300,25);
		main_panel.add(user428);

		JButton back = new JButton("Назад");
		back.setBounds(150, 110, 100, 25);
		main_panel.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main_info.setVisible(false);
			}
		});

		main_info.setSize(400,180);
		main_info.setVisible(true);
	}
}
