package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lexico.Classes;
import lexico.LexicalError;
import lexico.Lexico;
import lexico.Token;

/**
 * @author Ailsson L. Hafemann
 * @author Fredy Schlag
 * @author Paulo Weber
 */

public class FrmCompilador extends JFrame {	
	
	private String				nomeArquivo	= "";
	private JTextArea			taEditor;
	private JScrollPane			spMensagens;
	private JTextArea			taMensagens;
	private JPanel				paStatusBar;
	private JLabel				lblStatusbar;
	private JPanel				paEsqStatusBar;
	private JButton				btNovo;
	private JButton				btAbrir;
	private JButton				btSalvar;
	private JButton				btCopiar;
	private JButton				btColar;
	private JButton				btRecortar;
	private JButton				btCompilar;
	private JButton				btGerarCodigo;
	private JButton				btEquipe;
	private JPanel				paEditor;
	private JScrollPane			spEditor;
	
	public FrmCompilador() {
		initialize();
		novo();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		setTitle("Compilador - 2014/I");
		setMinimumSize(new Dimension(990, 315));
		setBounds(100, 100, 990, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel paBotoes = new JPanel();
		getContentPane().add(paBotoes, BorderLayout.NORTH);
		
		Action acNovo = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				novo();
			}
		};
		
		Action acAbrir = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				try {
					abrir();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		Action acSalvar = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				try {
					salvar();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		Action acCopiar = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				copiar();
			}
		};
		
		Action acColar = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				colar();
			}
		};
		
		Action acRecortar = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				recortar();
			}
		};
		
		Action acCompilar = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				compilar();
			}
		};
		
		Action acGerarCodigo = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				gerarCodigo();
			}
		};
		
		Action acEquipe = new AbstractAction() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				equipe();
			}
		};
		
		ActionMap acoes = paBotoes.getActionMap();
		acoes.put("btNovo", acNovo);
		acoes.put("btAbrir", acAbrir);
		acoes.put("btSalvar", acSalvar);
		acoes.put("btCopiar", acCopiar);
		acoes.put("btColar", acColar);
		acoes.put("btRecortar", acRecortar);
		acoes.put("btCompilar", acCompilar);
		acoes.put("btGerarCodigo", acGerarCodigo);
		acoes.put("btEquipe", acEquipe);
		
		paBotoes.setActionMap(acoes);
		InputMap atalhos = paBotoes.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		atalhos.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), "btNovo");
		// o Ctrl A deve sobrescrever o tratamento do JTextArea (Selecionar tudo)
		atalhos.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK), "btAbrir");
		atalhos.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "btSalvar");
		
		// begin - Verificar se vamos 'retratar' ou deixar o JTextArea tratar
		atalhos.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), "btCopiar");
		atalhos.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK), "btColar");
		atalhos.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "btRecortar");
		// end
		atalhos.put(KeyStroke.getKeyStroke("F8"), "btCompilar");
		atalhos.put(KeyStroke.getKeyStroke("F9"), "btGerarCodigo");
		atalhos.put(KeyStroke.getKeyStroke("F1"), "btEquipe");
				
		btNovo = new JButton("novo [ctrl-n]");
		btNovo.addActionListener(acNovo);
		defineBotao(btNovo, 95, "novo.png");
		paBotoes.add(btNovo);
		
		btAbrir = new JButton("abrir [ctrl-a]");
		btAbrir.addActionListener(acAbrir);
		defineBotao(btAbrir, 95, "abrir.png");
		paBotoes.add(btAbrir);
		
		btSalvar = new JButton("salvar [ctrl-s]");
		btSalvar.addActionListener(acSalvar);
		defineBotao(btSalvar, 100, "salvar.png");
		paBotoes.add(btSalvar);
		
		btCopiar = new JButton("copiar [ctrl-c]");
		btCopiar.addActionListener(acCopiar);
		defineBotao(btCopiar, 100, "copiar.png");
		paBotoes.add(btCopiar);
		
		btColar = new JButton("colar [ctrl-v]");
		btColar.addActionListener(acColar);
		defineBotao(btColar, 95, "colar.png");
		paBotoes.add(btColar);
		
		btRecortar = new JButton("recortar [ctrl-x]");
		btRecortar.addActionListener(acRecortar);
		defineBotao(btRecortar, 115, "recortar.png");
		paBotoes.add(btRecortar);
		
		btCompilar = new JButton("compilar [F8]");
		btCompilar.addActionListener(acCompilar);
		defineBotao(btCompilar, 100, "compilar.png");
		paBotoes.add(btCompilar);
		
		btGerarCodigo = new JButton("gerar c\u00F3digo [F9]");
		btGerarCodigo.addActionListener(acGerarCodigo);
		defineBotao(btGerarCodigo, 120, "gerarcodigo.png");
		paBotoes.add(btGerarCodigo);
		
		btEquipe = new JButton("equipe [F1]");
		btEquipe.addActionListener(acEquipe);
		defineBotao(btEquipe, 100, "equipe.png");
		paBotoes.add(btEquipe);
		
		paEditor = new JPanel();
		getContentPane().add(paEditor, BorderLayout.CENTER);
		paEditor.setLayout(new BorderLayout(0, 0));
		
		spEditor = new JScrollPane();
		spEditor.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spEditor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		paEditor.add(spEditor, BorderLayout.CENTER);
		
		taEditor = new JTextArea();
		taEditor.setFont(new Font("Consolas", Font.PLAIN, 12));
		taEditor.setBorder(new NumberedBorder());
		taEditor.getInputMap(JPanel.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK), "none");		
		taEditor.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				atualizaStatusBar(true);				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				atualizaStatusBar(true);				
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				atualizaStatusBar(true);				
			}
		});		
		spEditor.setViewportView(taEditor);
		
		spMensagens = new JScrollPane();
		spMensagens.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spMensagens.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMensagens.setPreferredSize(new Dimension(0, 100));
		paEditor.add(spMensagens, BorderLayout.SOUTH);
		
		taMensagens = new JTextArea();
		taMensagens.setFont(new Font("Consolas", Font.PLAIN, 12));
		taMensagens.setEditable(false);
		taMensagens.getInputMap(JPanel.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK), "none");
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
	}

	private void defineBotao(JButton botao, int largura, String img) {
		botao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		botao.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		botao.setPreferredSize(new Dimension(largura, 40));
		botao.setIcon(new ImageIcon(getClass().getResource("ico/" + img))); // pega img de dentro do jar		
		botao.setMaximumSize(new Dimension(120, 40));
	}
	
	private void novo() {
		taEditor.setText("");
		taMensagens.setText("");
		nomeArquivo = "";
		atualizaStatusBar(false);
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
				taMensagens.setText("");
				atualizaStatusBar(false);
			} finally {
				br.close();
			}
		}
	}
	
	private void salvar() throws IOException {
		if (nomeArquivo.isEmpty()) {
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
			taMensagens.setText("");
			atualizaStatusBar(false);
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
		taMensagens.setText("");
		Lexico lexico = new Lexico();
		lexico.setInput(taEditor.getText());
		ArrayList<String> list = new ArrayList<>();
		Token t = null;
		try {
		    list.add(formatMessage("linha", "classe", "lexema"));
		    while ( (t = lexico.nextToken()) != null )
		    {
		    	list.add(newline(t));
		    }
		    
		    for(String line: list){
		    	imprimirMensagem(line);
		    }
		    imprimirMensagem("programa compilado com sucesso");
		}
		catch ( LexicalError e ) {
		    imprimirMensagem(taEditor.getText().charAt(lexico.getPosition()-1) + e.getMessage());
		}
	}
	
	private String formatMessage(String line, String clazz, String lexeme) {
		StringBuilder builder = new StringBuilder(line);
		builder.append("  ");		
		
		for (int i = builder.length(); i < 8; i++) { // 6 colunas para número da linha
			builder.append(" ");
		}
		
		builder.append(clazz).append("  ");
		
		for (int i = builder.length(); i < 30; i++) { // 26 colunas para classe
			builder.append(" "); 
		}
		
		builder.append(lexeme);
		return builder.toString();		
	}
	
	private String newline(Token t) {
		return formatMessage(String.valueOf(t.getLine()), Classes.get(t.getId()), t.getLexeme());
	}

	private void gerarCodigo() {
		imprimirMensagem("Geração de código ainda não foi implementado");
	}	
	
	private void equipe() {
		imprimirMensagem("Equipe: Ailsson L. Hafemann, Fredy Schlag, Paulo Weber");
	}
	
	private void imprimirMensagem(String mensagem) {			
		taMensagens.insert(mensagem + "\n", taMensagens.getCaretPosition());
		//taMensagens.setCaretPosition(0); //posiciona na primeira linha
	}
	
	private void atualizaStatusBar(boolean modificado) {
		lblStatusbar.setText((nomeArquivo.isEmpty() ? "" : nomeArquivo + ": ") + (modificado ? "Modificado" : "Não modificado"));		
	}	
	
}
