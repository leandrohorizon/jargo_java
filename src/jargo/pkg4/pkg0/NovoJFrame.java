/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import Formatacao.FormatarO;
import Menu.Menu;
import Menu.Data;
// import br.akls.javaspeech.sintetizer.Sintetizador;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JScrollBar;

/**
 *
 * @author Leandro Messias
 */
public class NovoJFrame extends javax.swing.JFrame{

    /**
     * Creates new form NovoJFrame
     */
    public NovoJFrame() {
        initComponents();
            URL url = this.getClass().getResource("/imagens/folder/icon2.png"); 
            Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
            this.setIconImage(iconeTitulo);

            jComboBox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){ // para evitar duplicações
                        if(jComboBox1.getSelectedItem().equals("Jargo"))
                            trocar = "selecionar";
                        if(Pesquisar.size()>0){
                            ExoText = Pesquisar.get(jComboBox1.getSelectedIndex());
                            if(ExoText.contains("!pesquisar")){
                                ExoText = ExoText.replace("!pesquisar ", "");
                                trocar = "procurar";
                            }else if(ExoText.contains("!pasta")){
                                ExoText = ExoText.replace("!pasta ", "");
                                trocar = "arquivo";
                                procurar = false;
                            }else if(ExoText.contains("Jargo")){
                                trocar = "selecionar";
                            }
                        }
                    }
                }
            });
                
        new Thread(){
            public void run(){
                atualizar();
                Jtimer      jt  = new Jtimer();
                Pesquisar   p   = new Pesquisar();
                Formatar    ft  = new Formatar();
                Sql         sql = new Sql();
                Comand      cm  = new Comand();
                JScrollBar  vertical = jScrollPane1.getVerticalScrollBar();
                String prc = "";
                jTextField1.requestFocus();
                if(sql.conectar()){
                    prc = p.resposta(" mensagem de boas vindas ");
                }else{
                    prc = "Não foi possivel conectar, verifique sua internet.\nCaso esteja conectado na internet, o problema é no nosso servidor desculpe-nos.";
                    jTextField1.setVisible(false);
                }
                desbloqueado = false;
                
                Thread thread = new Thread(new NovoJFrame.Processar(prc));
                thread.start();
                desbloqueado = true;
                boolean temp = false;
                Pesquisar = sql.Pesquisar(jComboBox1);
//                while(true){
//                    jt.tempo(0);
//                    Horas.setText(cm.result("!dt !horas"));
//                }
            }
        }.start();
    }
    ArrayList<String> Pesquisar = new ArrayList<String>();
    
    public void exibir(String entrada){
        Thread thread = new Thread(new NovoJFrame.Processar(entrada));
        thread.start();
    }
    public class Processar extends Thread implements Runnable{
        private String entrada;
        public Processar(String entrada){
            super();
            this.entrada = entrada;
        }
        public void run(){
            desbloqueado = false;
            JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
            Jtimer jt  = new Jtimer();
//            jTextArea1.append("Você: "+entrada+"\n");
            String prc = entrada;
//            Sintetizador vp = new Sintetizador();
//		String texto = " <JSML> "
//			+ "<BREAK MSECS=\"300\"/>"
//			+ "<PROS PITCH=\"80\" RANGE=\"50\" RATE=\"150\">"
//			+ prc
//			+ "</PROS>"
//			+ " </JSML> ";
//		vp.speak(texto);
            if(prc.length()>500)
                tempo = 0;
            else
                tempo = 0.05;
            Fundo2.append("Jargo: ");
                for(int a = 0;a<prc.length();a++){
                    jt.tempo(tempo);
                    Fundo2.append(prc.substring(a,a+1));
                    Fundo2.setCaretPosition(Fundo2.getDocument().getLength());
                }
            Fundo2.append("\n");
            
            desbloqueado = true;
        }
    }
    FormatarO fot = null;
    public String processamento(String entrada){
        jTextField1.setText("");
        ExoT = trocar;
        Sql sql = new Sql();
        
        Formatar ft = new Formatar();
        String txt = " "+entrada+" ";
        txt = ft.Formatar(txt);
        
        
        Pesquisar p = new Pesquisar();
//        
        String g = "";
        for(int i = 1 ; i < txt.length()-1 ; i++){        
            g += txt.substring(i,i+1);
        }
        txt = g;
//        System.out.println(g);
//        txt = ft.Formatar(txt);
        
        System.out.println(txt);
        String m = ft.Matematica(entrada);
        if(m.matches(".*\\d+[/*+-]\\d+.*") && trocar.equals("selecionar")){
            String n = String.valueOf(new StringMath().Calcular(m));
            if(n.endsWith(".0")){
                return n.replace(".0", "");
            }
            return n;
        }
        if(entrada.matches(".*\\.com.*") && trocar.equals("selecionar")){
            try{
                if(!entrada.matches("http\\w?.*"))
                    entrada = "https://www."+entrada;
                System.out.println(entrada);
                java.awt.Desktop.getDesktop().browse(new java.net.URI(entrada));
                return "Acessando site";
            }catch (IOException | URISyntaxException ex){
                System.out.println(ex);
            }
        }
        if(trocar.equals("selecionar"))
            txt = txt.replaceAll("&","");
            
proces: switch (trocar){
            case "procurar":
                if(txt.equals("parar") || txt.equals("cancelar")){
                    entrada2 = "Interrompendo pesquisa";
                    jComboBox1.setSelectedIndex(0);
                    trocar = "selecionar";
                    break;
                }
                try {
                    java.awt.Desktop.getDesktop().browse( new java.net.URI( ExoText+entrada.replace(" ", "+") ) );
                    entrada2 = "Fazendo pesquisa...";
                    
                } catch (IOException | URISyntaxException ex) {
                    System.out.println(ex);
                }

                break;
                
            case "arquivo":
                if(txt.equals("parar") || txt.equals("cancelar")){
                    entrada2 = "Saindo da pasta";
                    jComboBox1.setSelectedIndex(0);
                    trocar = "selecionar";
                    break;
                }
                ArqExis ae = new ArqExis();
                try{
                      desbloqueado = false;
                      entrada2 ="\n"+ ae.resultados(txt,ExoText.replaceAll("!arquivo ", ""));
                      desbloqueado = true;
                }catch(IOException e){
                    System.out.println(e);
                }
                break;
                
                case "selecionar":
                switch (txt){
                    case "apagar pergunta":
                        entrada2 = "Qual pergunta";

                        trocar = "apagar";
                        break proces;

                    case "resposta errada":
                        entrada2 = "Qual pergunta";
                        trocar = "alterar";
                        break proces;

                    case "editar pergunta":
                        entrada2 = "Qual pergunta";
                        trocar = "editar";
                        break proces;

                    case "perguntas sem respostas":
                        if(sql.SR().equals("")){
                            entrada2 = "nenhuma no momento";
                        }else{
                            entrada2 = "\n"+sql.SR();
                        }
                        trocar = "selecionar";
                        break proces;

                    case "inserir forcado":
                        entrada2 = "O que deseja inserir?";
                        trocar = "inserirf";
                        break proces;
                    
                    case "ensinar palavra":
                        entrada2 = "Qual palavra você deseja me ensinar?";
                        trocar = "formatar";
                        break proces;
                }
                
                String tp;
                tp = p.resposta(" "+txt+" ");
                if(tp.equals("não encontrado") && new Data().isRespostaDireta()){
                    entrada2 = "Informe uma resposta";
                    ExoText = txt;
                    trocar = "inserir";
                }else if(tp.equals("Sem resposta")){
                    entrada2 = "Resposta não definida, informe uma resposta";
                    ExoText = txt;
                    trocar = "alterar2";
                }else if (tp.contains("!pesquisar")){
                    ExoText = tp.replaceAll("!pesquisar ", "");
                    entrada2 = "O que pesquisar?";
                    trocar = "procurar";
                }else if (tp.contains("!pasta")){
                    ExoText = tp.replaceAll("!pasta ", "");
                    entrada2 = "Qual arquivo deseja abrir?, para interromper a pesquisa por arquivos digite \"parar\" ou \"cancelar\".";
                    trocar = "arquivo";
                }else{
                    entrada2 = tp;
                }
                break;
            case "inserir":
                if(txt.equals("cancelar")){
                    entrada2 = "Operação cancelada";
                    trocar = "selecionar";
                    break;
                }
                entrada2 = sql.inserir(ExoText, entrada);
                trocar = "selecionar";
                break;
            case "alterar2":
                if(txt.equals("cancelar")){
                    entrada2 = "Operação cancelada";
                    trocar = "selecionar";
                    break;
                }
                entrada2 = sql.alterar(ExoText, entrada);
                trocar = "selecionar";
                break;
            case "alterar":
                if(txt.equals("cancelar")){
                    entrada2 = "Operação cancelada";
                    trocar = "selecionar";
                    break;
                }
                if(!sql.existe2(" "+txt+" ")){
                    ExoText = txt;
                    entrada2 = "e qual é a resposta correta";
                    trocar = "inserir";
                }else{
                    ExoText = txt;
                    entrada2 = "e qual é a resposta correta";
                    jTextField1.setText(sql.exibir2(txt));
                    trocar = "alterar2";
                }
                break;

            case "apagar":
                if(txt.equals("cancelar")){
                    entrada2 = "Operação cancelada";
                    trocar = "selecionar";
                    break;
                }
                if(sql.existe(" "+txt+" ")){
                    entrada2 = sql.deletar(txt);
                }else{
                    entrada2 = "Pergunta não existe\n";
                }
                trocar = "selecionar";
                break; 
            
            case "editar pergunta":
                if(txt.equals("cancelar")){
                    entrada2 = "Operação cancelada";
                    trocar = "selecionar";
                    break;
                }
                entrada2 = sql.alterar2(ExoText, entrada);
                trocar = "selecionar";
                break;
                
            case "editar":
                if(txt.equals("cancelar")){
                    entrada2 = "Operação cancelada";
                    trocar = "selecionar";
                    break;
                }
                if(sql.existe2(" "+entrada+" ")){
                    ExoText = txt;
                    entrada2 = "Corrija";
                    trocar = "editar pergunta";
                }else{
                    entrada2 = "Pergunta não encontrada";
                    trocar = "selecionar";
                }
                break;
                
            case "inserirf":
                if(sql.existe2(" "+txt+" ")){
                    entrada2 = "Pergunta ja inserida";
                    trocar = "selecionar";
                }else{
                    entrada2 = "E a resposta?";
                    ExoText = txt;
                    trocar = "inserir";
                    
                }
                break;
                
            case "formatar":
                switch(trocar2){// processamento do formatar
                    case "inserirP":
//                    b = false;
                        fot = new FormatarO();
                        entrada2 = "Digite a tradução";
                        fot.setPalavra(entrada);
                        trocar2 = "inserirT";
                        break;

                    case "inserirT":
                        entrada2 = "Obrigado por me ensinar ^^";
                        fot.setTraducao(entrada);
                        sql.inserirFormatacao(fot);
//                    b = true;
                        trocar2 = "inserirP";
                        trocar = "selecionar";
                    break;
                }
                break;
                
            case "void":
                trocar = ExoT;
                break;
        }
        return entrada2;
    }
    
    ArrayList<String> espera = new ArrayList<>();
    ArrayList<String> lista = new ArrayList<>();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fundo1 = new javax.swing.JPanel();
        Barra = new javax.swing.JPanel();
        Horas = new javax.swing.JLabel();
        BtnMenu = new javax.swing.JLabel();
        BtnFechar = new javax.swing.JLabel();
        BtnMin = new javax.swing.JLabel();
        Fundo4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Fundo2 = new javax.swing.JTextArea();
        Fundo3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BtnEnviar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jargo");
        setUndecorated(true);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        Fundo1.setBackground(new java.awt.Color(31, 32, 34));

        Barra.setBackground(new java.awt.Color(39, 41, 38));
        Barra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarraMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BarraMouseMoved(evt);
            }
        });
        Barra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Horas.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        Horas.setForeground(new java.awt.Color(251, 255, 255));
        Horas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Horas.setText("JARGO");
        Barra.add(Horas, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 0, 52, 30));

        BtnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/DesingPreto/btn_menu.png"))); // NOI18N
        BtnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnMenuMouseClicked(evt);
            }
        });
        Barra.add(BtnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 20, 20));

        BtnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/DesingPreto/btn_fechar.png"))); // NOI18N
        BtnFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnFecharMouseClicked(evt);
            }
        });
        Barra.add(BtnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 5, 20, 20));

        BtnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/DesingPreto/btn_mini.png"))); // NOI18N
        BtnMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnMinMouseClicked(evt);
            }
        });
        Barra.add(BtnMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 5, 20, 20));

        Fundo4.setBackground(new java.awt.Color(31, 32, 34));
        Fundo4.setCursor(new java.awt.Cursor(java.awt.Cursor.N_RESIZE_CURSOR));
        Fundo4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Fundo4MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Fundo4MouseMoved(evt);
            }
        });

        javax.swing.GroupLayout Fundo4Layout = new javax.swing.GroupLayout(Fundo4);
        Fundo4.setLayout(Fundo4Layout);
        Fundo4Layout.setHorizontalGroup(
            Fundo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Fundo4Layout.setVerticalGroup(
            Fundo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        jScrollPane1.setBorder(null);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setNextFocusableComponent(jTextField1);

        Fundo2.setEditable(false);
        Fundo2.setBackground(new java.awt.Color(31, 32, 34));
        Fundo2.setColumns(20);
        Fundo2.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        Fundo2.setForeground(new java.awt.Color(251, 255, 255));
        Fundo2.setLineWrap(true);
        Fundo2.setRows(5);
        Fundo2.setWrapStyleWord(true);
        Fundo2.setBorder(null);
        Fundo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fundo2MouseClicked(evt);
            }
        });
        Fundo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Fundo2KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Fundo2);

        Fundo3.setBackground(new java.awt.Color(31, 32, 34));
        Fundo3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fundo3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 80, 20));

        jTextField1.setBackground(new java.awt.Color(39, 41, 38));
        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(251, 255, 255));
        jTextField1.setText("Escreva uma mensagem...");
        jTextField1.setBorder(null);
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        Fundo3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 280, 20));

        jLabel1.setBackground(new java.awt.Color(39, 41, 38));
        jLabel1.setOpaque(true);
        Fundo3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 400, 25));

        BtnEnviar.setBackground(new java.awt.Color(251, 255, 255));
        BtnEnviar.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        BtnEnviar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnEnviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEnviar.setOpaque(true);
        BtnEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnEnviarMouseClicked(evt);
            }
        });
        Fundo3.add(BtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 7, 60, 25));

        javax.swing.GroupLayout Fundo1Layout = new javax.swing.GroupLayout(Fundo1);
        Fundo1.setLayout(Fundo1Layout);
        Fundo1Layout.setHorizontalGroup(
            Fundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fundo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fundo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Fundo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fundo1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        Fundo1Layout.setVerticalGroup(
            Fundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fundo1Layout.createSequentialGroup()
                .addComponent(Barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fundo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fundo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fundo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fundo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Fundo4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fundo4MouseDragged
        int y = evt.getYOnScreen();
        int g = y-this.getY()-50;
        if(g<500 && g>=281){
            this.setSize(this.getWidth(),y-this.getY());
            Fundo4.setLocation(0, g);
        }
//        op.dispose();
    }//GEN-LAST:event_Fundo4MouseDragged

    private void Fundo4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fundo4MouseMoved
        yMouse = evt.getY();
    }//GEN-LAST:event_Fundo4MouseMoved

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if(jTextField1.getText().equals("Escreva uma mensagem..."))
            jTextField1.setText("");
        blo = true;
        
//        op.dispose();
    }//GEN-LAST:event_jTextField1FocusGained
 private boolean blo;
    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if(jTextField1.getText().equals("")){
            jTextField1.setText("Escreva uma mensagem...");
            blo = false;
        }
        
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void BarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x-xMouse2, y-yMouse2);
    }//GEN-LAST:event_BarraMouseDragged

    private void BarraMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraMouseMoved
        xMouse2 = evt.getX();
        yMouse2 = evt.getY();
    }//GEN-LAST:event_BarraMouseMoved

    private void BtnFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnFecharMouseClicked
        System.exit(0);
    }//GEN-LAST:event_BtnFecharMouseClicked

    private void BtnMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMinMouseClicked
        this.setState(NovoJFrame.ICONIFIED);
//        op.dispose();
    }//GEN-LAST:event_BtnMinMouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        switch(evt.getKeyCode()){
		case KeyEvent.VK_ENTER:
                    boolean temp = true;
                    Formatar ft = new Formatar();
                    if(ft.Formatar(jTextField1.getText()).equals(""))
                        temp = false;
                    
                    if(ft.Formatar(jTextField1.getText()).equals(" "))
                        temp = false;
                    if(desbloqueado && temp){
                        
                        lista.add(String.valueOf(jTextField1.getText()));
//                        processamento(jTextField1.getText());
                        Fundo2.append("Você: "+jTextField1.getText()+"\n");
                        Thread thread = new Thread(new NovoJFrame.Processar(processamento(jTextField1.getText())));
                        Processar _jC = new NovoJFrame.Processar("");
                        procurar = true;
                        thread.start();
                        
                        j = lista.size();
                    }
                    break;
                    
                case KeyEvent.VK_DOWN:
                    if(j<lista.size()-1){
                        j++;
                        jTextField1.setText(lista.get(j));
                    }else{
                        j = lista.size();
                        jTextField1.setText("");
                    }
                    break;
		case KeyEvent.VK_UP:
                    if(j>0){
                        j--;
                        jTextField1.setText(lista.get(j));
                    }
                    break;
        }
        tempo = 0;
    }//GEN-LAST:event_jTextField1KeyPressed

    private void Fundo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Fundo2KeyPressed
        switch(evt.getKeyCode()){
            case KeyEvent.VK_END:
                JScrollBar vertical = jScrollPane1.getVerticalScrollBar();;
                vertical.setValue(vertical.getMaximum());
                break;

            case KeyEvent.VK_HOME:
                JScrollBar vertical2 = jScrollPane1.getVerticalScrollBar();
                vertical2.setValue(vertical2.getMinimum());
                break;
        }
    }//GEN-LAST:event_Fundo2KeyPressed

    private void Fundo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fundo2MouseClicked
        tempo = 0;
    }//GEN-LAST:event_Fundo2MouseClicked

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        tempo = 0;
    }//GEN-LAST:event_jTextField1MouseClicked

    private void BtnEnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEnviarMouseClicked
        boolean temp = true;
            Formatar ft = new Formatar();
            if(ft.Formatar(jTextField1.getText()).equals(""))
                temp = false;
        if(desbloqueado && temp && blo){
            lista.add(String.valueOf(jTextField1.getText()));
//            processamento(jTextField1.getText());
            Fundo2.append("Você: "+jTextField1.getText()+"\n");
            Thread thread = new Thread(new NovoJFrame.Processar(processamento(jTextField1.getText())));
            thread.start();
            jTextField1.setText("");
            j = lista.size();
        }
    }//GEN-LAST:event_BtnEnviarMouseClicked
    private void atualizar(){
        Data o = new Data();
        
        Fundo1      .setBackground(o.getCor1());
        Fundo2      .setBackground(o.getCor1());
        Fundo3      .setBackground(o.getCor1());
        Fundo4      .setBackground(o.getCor1());
        
        jTextField1 .setBackground(o.getCor2());
        jLabel1     .setBackground(o.getCor2());
        Barra       .setBackground(o.getCor2());
        
        Fundo2      .setForeground(o.getCorFonte());
        jTextField1 .setForeground(o.getCorFonte());
        Horas       .setForeground(o.getCorFonte());
        BtnEnviar   .setBackground(o.getCorFonte());
//        Image[] img = new Botoes().trocarCor(new Data().getCorFonte());
//        JLabel[]    labels = new JLabel[]{BtnFechar, BtnMin, BtnMenu};
//        int i = 0;
//        for(JLabel botao : labels){
//            ImageIcon icone = new ImageIcon(img[i]);
//            botao.setIcon(icone);
//            i++;
//        }
        setAlwaysOnTop(o.isSobreTela());
    }
    private void BtnMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMenuMouseClicked
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        int xlabel = evt.getX();
        int ylabel = evt.getY();
        Menu opa = new Menu(this, true);
        opa.setLocation(x-xlabel, y-ylabel);
        opa.setVisible(true);
        atualizar();
    }//GEN-LAST:event_BtnMenuMouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained
    private int xMouse;
    private int yMouse;
    private int xMouse2;
    private int yMouse2;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NovoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NovoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Barra;
    private javax.swing.JLabel BtnEnviar;
    private javax.swing.JLabel BtnFechar;
    private javax.swing.JLabel BtnMenu;
    private javax.swing.JLabel BtnMin;
    private javax.swing.JPanel Fundo1;
    private javax.swing.JTextArea Fundo2;
    private javax.swing.JPanel Fundo3;
    private javax.swing.JPanel Fundo4;
    private javax.swing.JLabel Horas;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private String trocar = "selecionar";
    private String trocar2 = "inserirP";
    private String entrada2 = "";
    private String ExoText;
    private String ExoT = "";
    private double tempo = 0.05;
    private int j;
    private boolean desbloqueado = true;
    private boolean procurar;
}