import javax.swing.JFrame;

public class MyFrameViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setSize(400, 600);
		frame.setTitle("this is a frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyComponent comp = new MyComponent();
		frame.add(comp);
		
		frame.setVisible(true);
		
	}

}
