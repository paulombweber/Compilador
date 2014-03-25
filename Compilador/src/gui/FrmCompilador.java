package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class FrmCompilador extends JFrame {

	private String nomeArquivo;
	private JTextArea taEditor;
	private JScrollPane spMensagens;
	private JTextArea taMensagens;
	private JPanel paStatusBar;
	private JLabel lblStatusbar;
	private JPanel paEsqStatusBar;
	private JButton btNovo;
	private JButton btAbrir;
	private JButton btSalvar;
	private JButton btCopiar;
	private JButton btColar;
	private JButton btRecortar;
	private JButton btCompilar;
	private JButton btGerarCodigo;
	private JButton btEquipe;
	private JPanel paEditor;
	private JScrollPane spEditor;

	public FrmCompilador() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		setTitle("Compilador - 2014/I");
		setMinimumSize(new Dimension(945, 310));
		setBounds(100, 100, 945, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel paBotoes = new JPanel();
		getContentPane().add(paBotoes, BorderLayout.NORTH);

		Action acNovo = new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				novo();
			}
		};

		btNovo = new JButton("novo [ctrl-n]");
		btNovo.addActionListener(acNovo);
		defineBotao(btNovo);
		paBotoes.add(btNovo);

		btAbrir = new JButton("abrir [ctrl-a]");
		btAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					abrir();
				} catch (IOException e1) {
					//TODO
					e1.printStackTrace();
				}
			}
			
		});
		defineBotao(btAbrir);
		paBotoes.add(btAbrir);

		btSalvar = new JButton("salvar [ctrl-s]");
		btSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					salvar();
				} catch (IOException e1) {
					// TODO
					e1.printStackTrace();
				}
			}
		});
		defineBotao(btSalvar);
		paBotoes.add(btSalvar);

		btCopiar = new JButton("copiar [ctrl-c]");
		btCopiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				copiar();
			}
		});
		defineBotao(btCopiar);
		paBotoes.add(btCopiar);

		btColar = new JButton("colar [ctrl-v]");
		btColar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				colar();
			}
		});
		defineBotao(btColar);
		paBotoes.add(btColar);

		btRecortar = new JButton("recortar [ctrl-x]");
		btRecortar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				recortar();
			}
		});
		defineBotao(btRecortar);
		paBotoes.add(btRecortar);

		btCompilar = new JButton("compilar [F8]");
		btCompilar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compilar();
			}
		});
		defineBotao(btCompilar);
		paBotoes.add(btCompilar);

		btGerarCodigo = new JButton("gerar c\u00F3digo [F9]");
		btGerarCodigo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gerarCodigo();
			}
		});
		defineBotao(btGerarCodigo);
		paBotoes.add(btGerarCodigo);

		btEquipe = new JButton("equipe [F1]");
		btEquipe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				equipe();
			}
		});
		defineBotao(btEquipe);
		paBotoes.add(btEquipe);

		paEditor = new JPanel();
		getContentPane().add(paEditor, BorderLayout.CENTER);
		paEditor.setLayout(new BorderLayout(0, 0));

		spEditor = new JScrollPane();
		spEditor.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spEditor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		paEditor.add(spEditor, BorderLayout.CENTER);

		taEditor = new JTextArea();
		taEditor.setBorder(new NumberedBorder());
		taEditor.getInputMap(JPanel.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK),
				"none");
		spEditor.setViewportView(taEditor);

		spMensagens = new JScrollPane();
		spMensagens
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spMensagens
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMensagens.setPreferredSize(new Dimension(0, 100));
		paEditor.add(spMensagens, BorderLayout.SOUTH);

		taMensagens = new JTextArea();
		taMensagens.setEditable(false);
		taMensagens.getInputMap(JPanel.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK),
				"none");
		spMensagens.setViewportView(taMensagens);

		paStatusBar = new JPanel();
		paStatusBar.setPreferredSize(new Dimension(50, 25));
		paStatusBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		paStatusBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(paStatusBar, BorderLayout.SOUTH);
		paStatusBar.setLayout(new BorderLayout(0, 0));

		lblStatusbar = new JLabel("StatusBar");
		lblStatusbar.setHorizontalAlignment(SwingConstants.LEFT);
		paStatusBar.add(lblStatusbar);

		paEsqStatusBar = new JPanel();
		paEsqStatusBar.setPreferredSize(new Dimension(5, 10));
		paStatusBar.add(paEsqStatusBar, BorderLayout.WEST);
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()){
					case KeyEvent.VK_A:
						if (e.isControlDown()){
							try {
								abrir();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						break;
					case KeyEvent.VK_N:
						if (e.isControlDown()){
							novo();
						}
						break;
					case KeyEvent.VK_S:
						if (e.isControlDown()){
						try {
							salvar();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					break;
					case KeyEvent.VK_C:
						if (e.isControlDown()){
							copiar();
						}
						break;
					case KeyEvent.VK_V:
						if (e.isControlDown()){
							colar();
						}
						break;
					case KeyEvent.VK_X:
						if (e.isControlDown()){
							recortar();
						}
						break;
					case KeyEvent.VK_F8:
						compilar();
						break;
					case KeyEvent.VK_F9:
						gerarCodigo();
						break;
					case KeyEvent.VK_F1:
						equipe();
						break;
					}
				}
		});
	}

	private void defineBotao(JButton botao) {
		botao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		botao.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		botao.setPreferredSize(new Dimension(95, 30));
		botao.setMaximumSize(new Dimension(95, 30));
	}

	private void novo() {
		taEditor.setText("");
		nomeArquivo = "";
	}

	private void abrir() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			nomeArquivo = file.getAbsolutePath();

			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				String line;
				StringBuilder sb = new StringBuilder();
				while ((line = br.readLine()) != null) {
					sb.append(line);
					sb.append(System.getProperty("line.separator"));
				}
				taEditor.setText(sb.toString());
			} finally {
				br.close();
			}
		}
	}

	private void salvar() throws IOException {
		if (nomeArquivo.isEmpty()){
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				nomeArquivo = fileChooser.getSelectedFile().getAbsolutePath();
			}
		}
		File file = new File(nomeArquivo);
		file.delete();
		BufferedWriter br = new BufferedWriter(new FileWriter(file));
		try {
			br.write(taEditor.getText());
		} finally {
			br.close();
		}
	}

	private void copiar() {
		taEditor.copy();
	}

	private void colar() {
		taEditor.paste();
	}

	private void recortar() {
		taEditor.cut();
	}

	private void compilar() {
		System.out.println("Compilar");
	}

	private void gerarCodigo() {
		System.out.println("Gerar código");
	}

	private void equipe() {
		JOptionPane.showMessageDialog(this, "Equipe: Fredy Schlag \n"
				+ "        Jonathan Souza \n" + "        Paulo Weber");
	}

}
