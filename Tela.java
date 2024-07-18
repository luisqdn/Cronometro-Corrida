package cronometro;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.*;
import cronometro.Cronometro;
import cronometro.Dados;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tela extends WindowAdapter implements ActionListener, FocusListener, KeyListener{
    private Frame janela;
    private Panel painelEndereco,painelBotoes;
    private Label lNumEquipe, lPilotoNome, lCarCor;
    TextField tNumEquipe, tPilotoNome, tCarCor, tCronometro;
    private TextArea tObs;
    private Button bIniciar, bIniciarCronometro, bSalva,bParar,bSalvaVoltas;
    private MenuBar mb;
    private Menu m1;
    private MenuItem mi11, mi12;
    private Cronometro cronometro;
    private Timer timer;
    private String tempoVolta1;
    private String tempoVolta2;
    private int volta = 0; // Para contar as voltas
    private String tempoTotal = "00:00:00"; // Para armazenar o tempo total
        
    // Declara o vetor para armazenar os dados
    private Vector vDados;

    // Declara uma variavel para controlar a movimentação no vetor
    private int posicao;

    // INICIO DO METODO CONSTRUTOR DA CLASSE
    public Tela(){
    // Cria o objeto VDados para armazenar os dados
    vDados=new Vector();
    cronometro = new Cronometro();
    volta = 0;
    tempoTotal = "00:00:00";
                
    // Cria a janela Cronometro
    janela = new Frame();
    janela.setTitle("Cronometro");
    janela.setSize(400,500);
    janela.setBackground(new Color(255, 255, 255));
    janela.setLayout(null);
    janela.setLocationRelativeTo(null);
    // Define um Listener(“escutador”) para a janela da Cronometro
    janela.addWindowListener(this);
    adicionarKeyListener();
              
    // Cria o painel Endereço
    painelEndereco = new Panel();
    painelEndereco.setBackground(new Color(255, 255, 255));
    painelEndereco.setLayout(null);

    // Cria o painel Botões
    painelBotoes = new Panel();
    painelBotoes.setBackground(new Color(255, 255, 255));
    painelBotoes.setLayout(null);

    // Cria menu para a janela Cronometro
    mb = new MenuBar();
    m1 = new Menu("Cronometro");
    mi11 = new MenuItem("Tempos");
    mi12 = new MenuItem("Sair");
    m1.add(mi11);
    m1.addSeparator();
    m1.add(mi12);
    mb.add(m1);
    mi11.addActionListener(this);
    mi12.addActionListener(this);
       
    // Cria Rótulos(Label) do painel Endereço
    lNumEquipe = new Label("Numero da Equipe:");
    lPilotoNome = new Label("Nome do Piloto:");
    lCarCor = new Label("Cor do veiculo:");
		
    //Cria os campos de texto (TextField) do painel Endereço
    // Define um Listener(escutador) para controlar o foco do campo nome
    tNumEquipe = new TextField(10);
    tNumEquipe.addFocusListener(this);
    tNumEquipe.setEnabled(false);
    tPilotoNome = new TextField(45);
    tPilotoNome.addFocusListener(this);
    tPilotoNome.setEnabled(false);
    tCarCor = new TextField(60);
    tCarCor.setEnabled(false);
    tCronometro = new TextField(8);
    tCronometro.setEditable(false);
                      	
    // Rótulos e campos de texto do painel Endereço
    lNumEquipe.setBounds(10,15,110,13);
    tNumEquipe.setBounds(120,12,240,19);
    lPilotoNome.setBounds(10,37,95,13);
    tPilotoNome.setBounds(105,34,255,19);
    lCarCor.setBounds(10,59,85,13);
    tCarCor.setBounds(95,56,265,19);
    tCronometro.setBounds(135,140,120,30);

    // Cria, define tamanho e posição de uma área de texto para observações
    tObs = new TextArea("",15,90,TextArea.SCROLLBARS_BOTH);
    tObs.setBounds(30,230,330,90);
    tObs.setEnabled(false);

    // Adiciona Labels e campos de texto ao painel Endereco
    painelEndereco.add(lNumEquipe);
    painelEndereco.add(tNumEquipe);
    painelEndereco.add(lPilotoNome);
    painelEndereco.add(tPilotoNome);
    painelEndereco.add(lCarCor);
    painelEndereco.add(tCarCor);
    painelEndereco.add(tCronometro);
    painelEndereco.add(tObs);

    // Cria botões
    bIniciar = new Button("Cadastrar Dados");
    // Define um Listener(escutador) para cada botao
    bIniciar.addActionListener(this);
                
    bSalva = new Button("Salvar dados");
    bSalva.addActionListener(this);
                
    bParar = new Button("Pausar Cronômetro");
    bParar.addActionListener(this);
                
    bSalvaVoltas=new Button("Salvar Voltas");
    bSalvaVoltas.addActionListener(this);
                
    bIniciarCronometro = new Button("Iniciar Cronômetro");
    bIniciarCronometro.addActionListener(this);
    

    // Define o gerenciador de layout para o painel botões
    painelBotoes.setLayout(new FlowLayout());

    // Adiciona botões ao painel botões
    painelBotoes.add(bIniciar);
    painelBotoes.add(bSalva);
    painelBotoes.add(bIniciarCronometro);
    painelBotoes.add(bParar);
    painelBotoes.add(bSalvaVoltas);
    
    // Adiciona os paineis e o menu à janela Cronometro
    janela.add(painelEndereco);
    janela.add(painelBotoes);
    janela.setMenuBar(mb);

    //Desabilita botões XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    bIniciar.setEnabled(true);
    bSalva.setEnabled(false);
    bParar.setEnabled(false);
    bSalvaVoltas.setEnabled(false);
    bIniciarCronometro.setEnabled(true);
  }//FINAL DO MÉTODO CONSTRUTOR

    public void keyReleased(KeyEvent e) {
        // Não é necessário implementar este método para o KeyListener
    }

    public void keyTyped(KeyEvent e) {
        // Não é necessário implementar este método para o KeyListener
    }

    public void setNumEquipe(String numEquipe){
	tNumEquipe.setText(numEquipe);
    }
    public void setPilotoNome(String pilotoNome){
	tPilotoNome.setText(pilotoNome);
    }
    public void setCarCor(String carCor){
	tCarCor.setText(carCor);
    }
    public void setCronometro(String cronometro){
	tCronometro.setText(cronometro);
    }
    public void setObs(String Obs){
	tObs.setText(Obs);
    }
    public void setMenuBar(MenuBar mb)	{
	janela.setMenuBar(mb);
    }

    //METODOS MUTADORES (Permitem receber o conteúdo dos campos)

    public String getPilotoNome(){
	return tPilotoNome.getText();
    }
    public String getCarCor(){
	return tCarCor.getText();
    }
    public String getCronometro(){
	return tCronometro.getText();
    }
    public String getObs(){
	return tObs.getText();
    }
    public MenuBar getMenuBar()	{
	return janela.getMenuBar();
    }

    // Método para tornar visíveis os paineis Endereco e Botoes
    public void mostraPainel()	{
	painelEndereco.setSize(400,345);  // Tamanho do painel
	painelEndereco.setLocation(0,45);  // Posição do painel
	painelBotoes.setSize(350,75);
	painelBotoes.setLocation(20,420);
	janela.show();   // Reorganiza a janela Cronometro
    }

    // Método para detectar se determinada estrutura recebeu o foco
    public void focusGained(FocusEvent e){

    }

    boolean teste = false;
    // Método para detectar se determinada estrutura perdeu o foco
    public void focusLost(FocusEvent e)	{
	 if(getPilotoNome().length()>0)
	 bSalva.setEnabled(true);
    }

    // Método para detectar que ações foram executadas
    public void actionPerformed(ActionEvent e)	{
	if (e.getSource().equals(mi11))// Se a ação foi clicar em 'Cadastro'
		{                                // no Menu Cronometro
		   this.mostraPainel();
                   // Iniciar o cronômetro ao selecionar o item de menu "Tempos"
                   // Atualizar o campo de texto do cronômetro na interface
		   return;
		}

	if (e.getSource().equals(mi12))// Se a ação foi clicar em 'Sair' no
		{                               // Menu Cronometro
		   System.exit(0);
		}
                
	// Determina a ação correspondente a cada botão quando clicado.
	Button b=(Button)e.getSource();
	if (b==bIniciar)          this.botaoNovo();
        if (b==bIniciarCronometro)     this.bIniciarCronometro();
	else if (b==bSalva)	   this.botaoSalva();
	else if (b==bParar)   this.cronometro.parar();
	else if (b==bSalvaVoltas) this.botaoSalvaVoltas();
           
    }
        
    private void atualizarCronometro() {
        //código para atualizar o campo de texto tCronometro com o tempo decorrido do cronômetro
        tCronometro.setText(cronometro.getTempoDecorrido());
        cronometro.iniciar();
        timer = new Timer();
        timer.scheduleAtFixedRate(new AtualizaCronometroTask(), 0, 1); 
    }
        
    // Método para parar a atualização do cronômetro na tela
    private void pararAtualizacaoCronometro() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
        
    private class AtualizaCronometroTask extends TimerTask {
        @Override
        public void run() {
        SwingUtilities.invokeLater(() -> {
        tCronometro.setText(cronometro.getTempoDecorrido());
           });
        }
    }
        

//----------------------Seção de acesso oa banco de dados ----------------
    Connection conecta()
    {
	    //String url="jdbc:odbc:banco"; --para outros bancos de dados
	    String url="jdbc:mysql://localhost/cronometro";
	    Connection con;
    /* Try Catch são blocos de comandos que tem como principal objetivo tratar exceções que o programador não tem como prever que irão acontecer ou controlar. */
	    try{
		 //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con=DriverManager.getConnection(url,"root","");
		 return con;
		}
		catch(ClassNotFoundException cnf){
		      System.out.println("Houve um erro no DRIVER: classnotfoundexcepition-"+cnf);
		      return null;
		}
		catch(SQLException sql){
		      System.out.println("Houve um erro no SQL:sqlexception sql-"+sql);
		      return null;
		}
    }

    //Método do botão Salva
    public void botaoSalva()
    {	   bParar.setEnabled(true);
           tNumEquipe.setEnabled(true);
           // Insire o tempo salvo na área de observações
           String tempoSalvo = getCronometro() + "\n";
           
           //Tempo total na área de observações
           String tempoVolta = "Volta " + volta + ": " + getCronometro();
               
	   Connection con=conecta();
	   //con=conecta();
	   try
	   {
	     //Cria um Stantement para acesso ao banco
	     Statement st=con.createStatement();

             //executa um cmd SQL para inserir os dados na tabela
	     int resultado=st.executeUpdate("insert into pilotos (pil_nome, pil_cor_carro, pil_voltas, pil_tempo_total) values('"+getPilotoNome()+"','"+getCarCor()+"','"+getObs()+"','"+tempoTotal+"')");
			
	     //fechar cnx
	     st.close();
	     //rs.close();
	     con.close();
            
	     JOptionPane.showMessageDialog(null, "Registro" + getNumEquipe() + " salvo." +"\n" +"Tempo Total: " +tempoTotal);
             this.limpaDados();

	     bSalva.setEnabled(false);
	     tNumEquipe.setEnabled(false);
	     tPilotoNome.setEnabled(true);
	     tCarCor.setEnabled(false);
	     tCronometro.setEnabled(false);
	     tObs.setEnabled(false);
	    }

	    catch(SQLException sql){
	     JOptionPane.showMessageDialog(null,"Nao salvo.");
	    }
    }       	
   
    // Método do botão Salva Voltas
    public void botaoSalvaVoltas() {
            // Incrementa o número da volta a cada clique
            volta++;
    
            // Salva o tempo da volta atual
            String tempoVolta = "Volta " + volta + ": " + getCronometro() + "\n";
            tObs.append(tempoVolta );
            
            // Soma o tempo da volta atual ao tempo total
            tempoTotal = somaTempos(tempoTotal, cronometro.getTempoDecorrido());
            
            // Mostra o tempo total na área de observações
            String tempoTotalText = "Tempo Total: " + tempoTotal + "\n";
           // tObs.append(tempoTotalText);

            // Habilita o botão de salvar voltas após salvar todas as voltas
            bSalvaVoltas.setEnabled(true);

            // Inicia o cronômetro
            cronometro.iniciar();
    }

    // Método para somar dois tempos no formato "mm:ss:SSS"
    private String somaTempos(String tempo1, String tempo2) {
    // Divide os tempos em minutos, segundos e milissegundos
    String[] partesTempo1 = tempo1.split(":");
    String[] partesTempo2 = tempo2.split(":");
     
    // Converte as partes para inteiros
    int minutos1 = Integer.parseInt(partesTempo1[0]);
    int segundos1 = Integer.parseInt(partesTempo1[1]);
    int milissegundos1 = Integer.parseInt(partesTempo1[2]);
    
    int minutos2 = Integer.parseInt(partesTempo2[0]);
    int segundos2 = Integer.parseInt(partesTempo2[1]);
    int milissegundos2 = Integer.parseInt(partesTempo2[2]);
    
    // Soma os milissegundos
    int somaMilissegundos = milissegundos1 + milissegundos2;
    // Se a soma dos milissegundos ultrapassar 1000, ajusta os segundos e os milissegundos
    if (somaMilissegundos >= 1000) {
        somaMilissegundos -= 1000;
        segundos1++;
    }
    
    // Soma os segundos
    int somaSegundos = segundos1 + segundos2;
    // Se a soma dos segundos ultrapassar 60, ajusta os minutos e os segundos
    if (somaSegundos >= 60) {
        somaSegundos -= 60;
        minutos1++;
    }
    
    // Soma os minutos
    int somaMinutos = minutos1 + minutos2;
    
    // Formata a soma para o formato "mm:ss:SSS"
    return String.format("%02d:%02d:%03d", somaMinutos, somaSegundos, somaMilissegundos );
}
    
		
      

    //Método do botão Novo
    void botaoNovo() {
       this.limpaDados();
                
        bIniciar.setEnabled(true);
        bSalva.setEnabled(false);
        bParar.setEnabled(false);
        bSalvaVoltas.setEnabled(true);   
        tNumEquipe.setEnabled(false);
        tPilotoNome.setEnabled(true);
        tCarCor.setEnabled(true);
        tCronometro.setEnabled(true);
        tObs.setEnabled(false);
        volta = 0;
        tPilotoNome.requestFocus();
    }
        
    void bIniciarCronometro() {
        this.atualizarCronometro();           
        bIniciar.setEnabled(false);
        bSalva.setEnabled(true);
        bSalvaVoltas.setEnabled(true);
        bParar.setEnabled(true);
    }

    //Método para limpar o conteúdo do campos
    public void limpaDados()	{
	this.setNumEquipe("");   // move vazio para os campos de texto
	this.setPilotoNome("");
	this.setCarCor("");
	this.setCronometro("");
	this.setObs("");
    }

    //Obtem os dados do objeto contato e mostra-os nos seus respectivos
    //componentes visuais.
    public void obterDados(int pos) {
		//cria um objeto para receber o conteudo na posicao do vetor
		Dados dadoAtual=(Dados)vDados.elementAt(pos);
		//Utiliza o metodo getCodigo do objeto e devolve para o método setCodigo do componente
		this.setNumEquipe(dadoAtual.getNumEquipe());
		this.setPilotoNome(dadoAtual.getPilotoNome());
		this.setCarCor(dadoAtual.getCarCor());
		this.setCronometro(dadoAtual.getCronometro());
		this.setObs(dadoAtual.getObs());
	}

    // Define ações a serem executadas quando o for pressionado o botão
    // fechar da janela Cronometro
	public void windowClosing(WindowEvent e) {
		System.exit(0); // Sai do App-Programa
	}

	// Cria método para tornar a janela Cronometro visível
	public void mostraCronometro(){
		janela.setVisible(true);
	}  
        public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {
            // Código para mudar a cor de fundo para preto por alguns segundos
            janela.setBackground(Color.BLACK);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
            public void run() {
                // Volte para a cor original após alguns segundos
                janela.setBackground(new Color(255, 255, 255));
                }
            }, 3000); // 3000 milissegundos = 3 segundos
        }
    }
         
        private void adicionarKeyListener() {
            // Registra a classe Tela3 como o KeyListener da janela
            janela.addKeyListener(this);
        }
    
     public static void main(String[] args) {
        Tela Cronometro3 = new Tela();
        Cronometro3.mostraCronometro(); }

        private void botaoParar() {
           throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
    }
}