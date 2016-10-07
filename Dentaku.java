package dentaku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dentaku extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
	BorderLayout border1 = new BorderLayout();
	JTextField result = new JTextField("");//計算結果表示用
	double stackedValue = 0.0;//演算子ボタンを押す前にテキストフィールドにあった値
	boolean isStacked = false;//stackedValueに数値を入れたか
	boolean afterCalc = false; //演算子ボタンを押した直後か
	String currentOp ="";//押された演算子ボタンの名前
	
	//frameのビルド
	public Dentaku() {
		panel.setLayout(border1);
		this.setSize(new Dimension(250, 300));
		this.setTitle("ぶひ");
		this.setContentPane(panel);
		
		panel.add(result, BorderLayout.NORTH);//テキストフィールド
		
		JPanel keyPanel = new JPanel();//ボタン配置用パネル
		keyPanel.setLayout(new GridLayout(4,4));
		panel.add(keyPanel, BorderLayout.CENTER);
		
//		ぼたんはめこみ
//		0  1   2  3
//		4  5   6  7
//		8  9  10 11
//		12 13 14 15
		
		keyPanel.add(new NumButton("7"), 0);
		keyPanel.add(new NumButton("8"), 1);
		keyPanel.add(new NumButton("9"), 2);
		keyPanel.add(new CalcButton("÷"), 3);
		keyPanel.add(new NumButton("4"), 4);
		keyPanel.add(new NumButton("5"), 5);
		keyPanel.add(new NumButton("6"), 6);
		keyPanel.add(new CalcButton("✕"), 7);
		keyPanel.add(new NumButton("1"), 8);
		keyPanel.add(new NumButton("2"), 9);
		keyPanel.add(new NumButton("3"), 10);
		keyPanel.add(new CalcButton("-"), 11);
		keyPanel.add(new NumButton("0"), 12);
		keyPanel.add(new NumButton("."), 13);
		keyPanel.add(new CalcButton("+"), 14);
		keyPanel.add(new CalcButton("="), 15);
		
		panel.add(new AaaButton(), BorderLayout.SOUTH);//Cボタンを配置する		
		this.setVisible(true);
		
	}
	//テキストフィールドに引数の文字列をつなげる
	public void appendResult(String c){
		if(!afterCalc){//演算子ボタンを押した直後でないなら
			result.setText(result.getText() + c);//押したボタンの名前をつなげる
		}else{
			result.setText(c);
			afterCalc = false;
		}
	}
	
//	数字を入力するボタンの定義
	public class NumButton extends JButton implements ActionListener {
		private static final long serialVisionUID = 1L;
		
		public NumButton(String keyTop){
			super(keyTop);//JButtonクラスのコンストラクﾀをよびだす
			this.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt){
			String keyNum = this.getText();
			appendResult(keyNum);
		}
	}

	//	演算子ボタンの定義
	public class CalcButton extends JButton implements ActionListener{
		private static final long serialVisionUID = 1L;
		
		public CalcButton(String op){
			super(op);
			this.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e){
			if(isStacked) {//以前演算子ボタンが押されたなら結果を出す
				double resultValue = (Double.valueOf(result.getText())).doubleValue();
				
				if(currentOp.equals("+")){//演算子に応じて計算
					stackedValue += resultValue;
				}else if(currentOp.equals("-")){
					stackedValue -= resultValue;
				}else if(currentOp.equals("✕")){
					stackedValue *= resultValue;
				}else if(currentOp.equals("÷")){
					stackedValue /= resultValue;
				}
				result.setText(String.valueOf(stackedValue));//結果をテキストフィールドに設定
			}
			currentOp = this.getText();//ボタン名から押されたボタンの演算子を取り出す
			stackedValue = (Double.valueOf(result.getText())).doubleValue();
			afterCalc = true;
			if(currentOp.equals("=")){
				isStacked = false;
			}else {
				isStacked = true;
			}
		}
	}
		
		//Cボタンの定義
		public class AaaButton extends JButton implements ActionListener{
			private static final long serialVisionUID = 1L;
			
			public AaaButton() {
				super("C");
				this.addActionListener(this);
			}
			
			public void actionPerformed(ActionEvent evt){
				
				stackedValue = 0.0;
			    isStacked = false;
			    afterCalc = false;
			    result.setText("");
				
			}
		}
	}
	
	
	

