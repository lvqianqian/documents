package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MousePixInfo extends JFrame {

	private final JPanel contentPanel = new JPanel();
	JLabel value_x = null;
	JLabel value_y = null;
	JLabel myPixColor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Robot myRobot = new Robot();
			MousePixInfo info_frame = new MousePixInfo();
			info_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			info_frame.setVisible(true);
			info_frame.setAlwaysOnTop(true);
			Timer timer = new Timer();
			info_frame.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						System.exit(0);
					}
				}
			});
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
					Color myPixColor = myRobot.getPixelColor(point.x, point.y);
					String r = Integer.toHexString(myPixColor.getRed());
					String g = Integer.toHexString(myPixColor.getGreen());
					String b = Integer.toHexString(myPixColor.getBlue());

					info_frame.value_x.setText("" + point.x); // 双引号""把int转成string
					info_frame.value_y.setText("" + point.y);
					info_frame.myPixColor.setText("#" + r + g + b);
				}
			}, 100, 50);// 设置100ms后开始,每50ms检测一次坐标
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MousePixInfo() {
		setTitle("\u9F20\u6807\u5750\u6807\u83B7\u53D6\u5668");
		setBounds(100, 100, 317, 192);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblx = new JLabel("X :");
		lblx.setFont(new Font("宋体", Font.PLAIN, 15));
		lblx.setBounds(22, 27, 66, 31);
		contentPanel.add(lblx);

		JLabel lbly = new JLabel("Y :");
		lbly.setFont(new Font("宋体", Font.PLAIN, 15));
		lbly.setBounds(22, 68, 66, 31);
		contentPanel.add(lbly);

		value_x = new JLabel("0");
		value_x.setForeground(Color.BLUE);
		value_x.setFont(new Font("宋体", Font.PLAIN, 20));
		value_x.setBounds(64, 27, 66, 31);
		contentPanel.add(value_x);

		value_y = new JLabel("0");
		value_y.setForeground(Color.BLUE);
		value_y.setFont(new Font("宋体", Font.PLAIN, 20));
		value_y.setBounds(64, 68, 66, 31);
		contentPanel.add(value_y);

		myPixColor = new JLabel("0");
		myPixColor.setForeground(Color.RED);
		myPixColor.setFont(new Font("宋体", Font.PLAIN, 32));
		myPixColor.setBounds(142, 48, 128, 31);
		contentPanel.add(myPixColor);

	}
}