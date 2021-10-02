import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
 
import javax.swing.*;
 

public class Generator implements ActionListener {
	@Override
    public void actionPerformed(ActionEvent arg0) {
		
		final JFrame fr = new JFrame("Генератор чисел");
	    JPanel gui = new JPanel(new BorderLayout());
        
        fr.setContentPane(gui);
        fr.pack();
        fr.setSize(300,300);
        fr.setLocationByPlatform(true);

        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLayout(null);
        fr.setLocationRelativeTo(null);
       
		
	    JTextField result = new JTextField();
	    result.setBounds(50,100,200,40);
	    result.setEditable(false);
	    gui.add(result);
		
		JButton generate = new JButton("Сгенерировать число");
		generate.setBounds(50,150,200,40);
		gui.add(generate);

		JButton back = new JButton("Назад");
	        back.addActionListener( new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                fr.setVisible(false);
	            }
	        });
	        
	    back.setBounds(50,200,200,40);
	    gui.add(back);
	        
		generate.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	    		final int min = 20; 
	    		final int max = 160; 
	    		final int rnd = rnd(min, max);
	    		String rnd_str = "" + rnd;
	    		result.setText(rnd_str);
	    		return;
	        }
	    });
		
}

	public int rnd(int min, int max)
	{
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
}