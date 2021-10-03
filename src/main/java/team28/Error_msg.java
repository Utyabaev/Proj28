package team28;

import javax.swing.*;


public class Error_msg implements Error {
	public void crash_tiraj() {
		JOptionPane.showMessageDialog(null, "Тираж должен быть больше одного!", "Внимание", JOptionPane.ERROR_MESSAGE);
	}

	public void crash_paper() {
		JOptionPane.showMessageDialog(null, "Выберите тип бумаги!", "Внимание", JOptionPane.ERROR_MESSAGE);
	}

	public void crash_phormat() {
		JOptionPane.showMessageDialog(null, "Выберите формат!", "Внимание", JOptionPane.ERROR_MESSAGE);
	}

	public void crash_perforation() {
		JOptionPane.showMessageDialog(null, "Выберите перфорацию!", "Внимание", JOptionPane.ERROR_MESSAGE);
	}

	public void crash_type() {
		JOptionPane.showMessageDialog(null, "Выберите тип печати!", "Внимание", JOptionPane.ERROR_MESSAGE);
	}
}
