/**
 * Classe da JanelaPrincipal Principal do JanelaPrincipal
 * @Descrição:
 * Esta classe modela a JanelaPrincipal do Servidor Principal
 * É muito importante notar que uma implementação
 * mais profissional deveria usar 2 Threads para gerenciar
 * as conexão de controle e dados, e sincronizar as funções necessárias...
 */

package cliente;

import base.Arquivo;
import base.Mensagem;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JanelaCliente extends javax.swing.JFrame {

    /**
     * Creates new form ServidorPrincipal
     */
    public JanelaCliente() {

        initComponents();

        // Coloa janela no centro da tela
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneTabela = new javax.swing.JScrollPane();
        jTableArquivos = new javax.swing.JTable();
        jLabelMsgListaArquivos = new javax.swing.JLabel();
        jButtonUp = new javax.swing.JButton();
        jButtonDown = new javax.swing.JButton();
        jButtonFechar = new javax.swing.JButton();
        jPanelBarraStatus = new javax.swing.JPanel();
        jSeparator = new javax.swing.JSeparator();
        jLabelBarraStatus = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuItemSair = new javax.swing.JMenuItem();
        jMenuAjuda = new javax.swing.JMenu();
        jMenuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente do Servidor de Arquivos");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setName("framePrincipal");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                windowOpenedActionPerformed(evt);
            }
        });

        jTableArquivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome do Arquivo", "Tamanho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableArquivos.setColumnSelectionAllowed(true);
        jTableArquivos.getTableHeader().setReorderingAllowed(false);
        jScrollPaneTabela.setViewportView(jTableArquivos);
        jTableArquivos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableArquivos.getColumnModel().getColumn(1).setMinWidth(120);
        jTableArquivos.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTableArquivos.getColumnModel().getColumn(1).setMaxWidth(120);

        jLabelMsgListaArquivos.setText("Lista de Arquivos Disponíveis - No Servidor Principal");

        jButtonUp.setText("Upload");

        jButtonDown.setText("Download");
        jButtonDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDownActionPerformed(evt);
            }
        });

        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jLabelBarraStatus.setText("Carregando...");

        javax.swing.GroupLayout jPanelBarraStatusLayout = new javax.swing.GroupLayout(jPanelBarraStatus);
        jPanelBarraStatus.setLayout(jPanelBarraStatusLayout);
        jPanelBarraStatusLayout.setHorizontalGroup(
            jPanelBarraStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBarraStatusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelBarraStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );
        jPanelBarraStatusLayout.setVerticalGroup(
            jPanelBarraStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBarraStatusLayout.createSequentialGroup()
                .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBarraStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuArquivo.setText("Arquivo");

        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemSair);

        jMenuBar.add(jMenuArquivo);

        jMenuAjuda.setText("Ajuda");

        jMenuItemSobre.setText("Sobre");
        jMenuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSobreActionPerformed(evt);
            }
        });
        jMenuAjuda.add(jMenuItemSobre);

        jMenuBar.add(jMenuAjuda);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelBarraStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonUp, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDown, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelMsgListaArquivos)
                            .addComponent(jScrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMsgListaArquivos)
                .addGap(8, 8, 8)
                .addComponent(jScrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonFechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanelBarraStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSobreActionPerformed
        exibirSobre();
    }//GEN-LAST:event_jMenuItemSobreActionPerformed

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        fecharJanela();
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        fecharJanela();
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDownActionPerformed
        // Faz download de arquivo
        downloadDeArquivo();
    }//GEN-LAST:event_jButtonDownActionPerformed

    private void windowOpenedActionPerformed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowOpenedActionPerformed
        inicializacao();
    }//GEN-LAST:event_windowOpenedActionPerformed

    // Método principal da Janela
    public static void main(String args[]) {
        ativarLookFeel(false);

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JanelaCliente cliente = new JanelaCliente();

                cliente.setVisible(true);
                cliente.iniciarComunicao();

                // cliente.atualizarTabelaArquivos();
            }
        });
    }

    /**
     * Aqui inicia a implementaçãos dos meus métodos, deste ponto em diante
     * não existe código que não tenha sido feito por mim.
     */

    private static void ativarLookFeel(boolean ativar) {
        if(!ativar) return;

        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    /**
     * Este método é executado no momento de abertura da janela
     */
    private void inicializacao() {
        try {
            conexaoControle = new ConexaoControle(this);    // cria conexão de controle
            new Thread(conexaoControle).start();            // cria a nova Thread
        }
        catch(Exception ex) {
            escreverNaBarraStatus("Erro ao criar conexão de controle com o servidor.");
        }
    }

    /**
     * Este método abri a janela de configuração de conexão com o servidor
     */
    private void abrirJanelaConfigConServidor() {
//        janConfiConServidor = new JanelaConfigConServidorCli(this, true);
//
//        janConfiConServidor.setVisible(true);
    }

    // Método que exibe a mensagem do Sobre
    private void exibirSobre() {
        JOptionPane.showMessageDialog(rootPane,
        "<html><b><font size='5'>Cliente do Servidor de Arquivos</font></b><br>"                +
        "<b><font size='3'>IESAM - Institudo de Estudos Superiores da Amazônia</font></b><br>"  +
        "<b><font size='3'>Engenharia de Computação</font></b><br><br>"                         /*+
        "Programador: <b><font color='red'>Jorge Augusto C. dos Reis</font></b><br>"            +
        "E-mail: <b><font color='blue'>engjorgeaugusto@gmail.com</font></b><br>"*/,
        "Sobre", JOptionPane.INFORMATION_MESSAGE);
    }

    // Este método fecha a janela do programa
    private void fecharJanela() {
        dispose();
    }


    // Este método, cria o socket do cliente e inicia a comunicação com co servidor
    private void iniciarComunicao() {
        try {
//            // Conecto ao servidor
//            socketCliente = new Socket(InfoServidorPrincipal.SERVIDOR_PRINCIPAL.ip,
//                                                InfoServidorPrincipal.SERVIDOR_PRINCIPAL.porta);
        }
        catch(Exception ex) {
            jLabelBarraStatus.setText("Erro em inciar comunicação com o Servidor: " + ex);
        }
    }



    /**
     * Este método escreve uma mensagem na barra de status
     */
    public void escreverNaBarraStatus(String mensagens) {
        jLabelBarraStatus.setText(mensagens);
    }

//    // Este método envia uma solicitação ao servidor...
//    private void enviaSolicitacao(TipoSolicitacao solicitacao) {
//        try {
//            saida = new ObjectOutputStream(socketCliente.getOutputStream());
//            saida.writeObject(solicitacao);
//        }
//        catch(Exception ex) {
//            jLabelBarraStatus.setText("Erro ao enviar solicitação: " + ex);
//        }
//    }

//    // Envia uma solicitação de listagem de arquivos...
//    // E lê a resposta do mesmo...
//    private void solicitaListaArquivos() {
//        enviaSolicitacao(TipoSolicitacao.LISTAGEM_ARQUIVOS);
//
//        try {
//            entrada  = new ObjectInputStream(socketCliente.getInputStream());
//            listaDeArquivos          = (ArrayList<InfoDeArquivo>) entrada.readObject();
//        }
//        catch(Exception ex) {
//            jLabelBarraStatus.setText("Ao receber resposta da listagem dos arquivos: " + ex);
//        }
//    }

    /**
     * Este método preenche a JTable com os dados dos arquivos
     */
    public void atualizarTabelaArquivos() {
        DefaultTableModel model = (DefaultTableModel) jTableArquivos.getModel();
        model.setRowCount(0);

        for(Arquivo arquivo : conexaoControle.getListaArquivos()) {
            model.addRow(arquivo.getArray());
        }

        escreverNaBarraStatus("Total de Arquivos: " + conexaoControle.getListaArquivos().size());
    }

    /**
     * Retorna o arquivo selecinado.
     */
    private Arquivo getArquivoSelecionado() {
        DefaultTableModel modelo = (DefaultTableModel) jTableArquivos.getModel();
        return conexaoControle.getListaArquivos().get(jTableArquivos.getSelectedRow());
    }

    /**
     * Este método cria uma nova Thread que é responsável por fazer o Download do arquivo.
     */
    private void downloadDeArquivo() {
        try {
            new Thread(new ConexaoDados(this, getArquivoSelecionado(), Mensagem.TipoMensagem.DOWNLOAD)).start();
        }
        catch(Exception ex) {
            escreverNaBarraStatus("Erro ao criar conexão de dados...");
        }
    }

    // Este método retorna o arquivo selecionado
    public Arquivo getInfoDeArquivo() {
//        DefaultTableModel modelo = (DefaultTableModel) jTableArquivos.getModel();
//
//        return cliente.getListaArquivo().get(jTableArquivos.getSelectedRow());

        return null;
    }

//    /**
//     * Retorna o atribudo escravo
//     */
//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public JanelaDownload getJanelaDownload() {
//        return janelaDownload;
//    }

    /**
     * Declaração dos meus atributos.
     */
    ConexaoControle conexaoControle;




    //private JanelaConfigConServidorCli  janConfiConServidor;
    // private Cliente                     cliente;
    //private JanelaDownload              janelaDownload;

    /// ANTIGAMENTE...

    // Socket para conexão de controle...
    private Socket                      socketCliente;
    private ObjectInputStream           entrada;
    private ObjectOutputStream          saida;
//
//    private ArrayList<InfoDeArquivo>    listaDeArquivos = new ArrayList<InfoDeArquivo>();
//    private JanelaDownload              janDownload;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDown;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonUp;
    private javax.swing.JLabel jLabelBarraStatus;
    private javax.swing.JLabel jLabelMsgListaArquivos;
    private javax.swing.JMenu jMenuAjuda;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemSobre;
    private javax.swing.JPanel jPanelBarraStatus;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPaneTabela;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JTable jTableArquivos;
    // End of variables declaration//GEN-END:variables
}
