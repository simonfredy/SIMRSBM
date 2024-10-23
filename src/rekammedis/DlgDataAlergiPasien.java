package rekammedis;

import bridging.SatuSehatCariAllergy;
import bridging.SatuSehatCariAllergy;
import fungsi.BackgroundMusic;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author dosen
 */
public class DlgDataAlergiPasien extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, nilai_detik, bookingbaru = 0;
    private String alarm = "", nol_detik, detik, sql = "", mode = "";
    private boolean aktif = false;
    private BackgroundMusic music;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private StringBuilder htmlContent;
    private SatuSehatCariAllergy allergycode = new SatuSehatCariAllergy(null, false);

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgDataAlergiPasien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "J.K.", "Umur", "No.Telp", "Cara Bayar", "TANGGAL", "KODE", "ALERGI", "KATEGORI", "REAKSI KODE", "SEVERITY", "NOTE", "NIP", "Nama Petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(120);
            } else if (i == 8) {
                column.setPreferredWidth(115);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            } else if (i == 15) {
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
        }

        allergycode.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (allergycode.getTable().getSelectedRow() != -1) {
                    if (mode.equals("alergi")) {
                        AlergiCode.setText(allergycode.getTable().getValueAt(allergycode.getTable().getSelectedRow(), 0).toString());
                        AlergySystem.setText(allergycode.getTable().getValueAt(allergycode.getTable().getSelectedRow(), 1).toString());
                        AlergyDisplay.setText(allergycode.getTable().getValueAt(allergycode.getTable().getSelectedRow(), 2).toString());
                    } else if (mode.equals("reaksialergi")) {
                        ReaksiCode.setText(allergycode.getTable().getValueAt(allergycode.getTable().getSelectedRow(), 0).toString());
                        ReaksiSystem.setText(allergycode.getTable().getValueAt(allergycode.getTable().getSelectedRow(), 1).toString());
                        ReaksiDisplay.setText(allergycode.getTable().getValueAt(allergycode.getTable().getSelectedRow(), 2).toString());
                    }

                }
                btnVaksinCode.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        LoadHTML = new widget.editorpane();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelCari = new widget.panelisi();
        DTPCari1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        NmPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        btnVaksinCode = new widget.Button();
        jLabel10 = new widget.Label();
        AlergyDisplay = new widget.TextBox();
        AlergySystem = new widget.TextBox();
        jLabel4 = new widget.Label();
        AlergiCode = new widget.TextBox();
        jLabel13 = new widget.Label();
        scrollPane1 = new widget.ScrollPane();
        TKeterangan = new widget.TextArea();
        jLabel18 = new widget.Label();
        cmbKategory = new widget.ComboBox();
        jLabel14 = new widget.Label();
        btnVaksinCode1 = new widget.Button();
        ReaksiDisplay = new widget.TextBox();
        ReaksiSystem = new widget.TextBox();
        ReaksiCode = new widget.TextBox();
        jLabel19 = new widget.Label();
        jLabel15 = new widget.Label();
        cmbSeverity = new widget.ComboBox();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Alergi Pasien ]::", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 88));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        panelCari.add(DTPCari1);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(30, 23));
        panelCari.add(jLabel25);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari2ItemStateChanged(evt);
            }
        });
        panelCari.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(68, 23));
        panelCari.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(370, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelCari.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelCari.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelCari.add(BtnAll);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelCari.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelCari.add(LCount);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(150, 180));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(150, 90));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(68, 10, 130, 23);

        NmPasien.setEditable(false);
        NmPasien.setHighlighter(null);
        NmPasien.setName("NmPasien"); // NOI18N
        FormInput.add(NmPasien);
        NmPasien.setBounds(288, 10, 410, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(200, 10, 86, 23);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 69, 23);

        btnVaksinCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnVaksinCode.setMnemonic('1');
        btnVaksinCode.setText("Jenis Alergi");
        btnVaksinCode.setToolTipText("Alt+1");
        btnVaksinCode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        btnVaksinCode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVaksinCode.setName("btnVaksinCode"); // NOI18N
        btnVaksinCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaksinCodeActionPerformed(evt);
            }
        });
        btnVaksinCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnVaksinCodeKeyPressed(evt);
            }
        });
        FormInput.add(btnVaksinCode);
        btnVaksinCode.setBounds(710, 40, 110, 23);

        jLabel10.setText("Display :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(410, 40, 60, 23);

        AlergyDisplay.setEditable(false);
        AlergyDisplay.setHighlighter(null);
        AlergyDisplay.setName("AlergyDisplay"); // NOI18N
        AlergyDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergyDisplayKeyPressed(evt);
            }
        });
        FormInput.add(AlergyDisplay);
        AlergyDisplay.setBounds(470, 40, 230, 23);

        AlergySystem.setEditable(false);
        AlergySystem.setHighlighter(null);
        AlergySystem.setName("AlergySystem"); // NOI18N
        AlergySystem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergySystemKeyPressed(evt);
            }
        });
        FormInput.add(AlergySystem);
        AlergySystem.setBounds(210, 40, 200, 23);

        jLabel4.setText("System :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(150, 40, 60, 23);

        AlergiCode.setEditable(false);
        AlergiCode.setHighlighter(null);
        AlergiCode.setName("AlergiCode"); // NOI18N
        AlergiCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiCodeKeyPressed(evt);
            }
        });
        FormInput.add(AlergiCode);
        AlergiCode.setBounds(70, 40, 80, 23);

        jLabel13.setText("Note / Keterangan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(200, 100, 120, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        TKeterangan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKeterangan.setColumns(20);
        TKeterangan.setRows(5);
        TKeterangan.setName("TKeterangan"); // NOI18N
        TKeterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeteranganKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(TKeterangan);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(330, 100, 370, 70);

        jLabel18.setText("Code :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 40, 70, 23);

        cmbKategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Makanan", "Medication", "Lingkungan", "Biologis" }));
        cmbKategory.setName("cmbKategory"); // NOI18N
        cmbKategory.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbKategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKategoryKeyPressed(evt);
            }
        });
        FormInput.add(cmbKategory);
        cmbKategory.setBounds(70, 110, 120, 23);

        jLabel14.setText("Kategori :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 110, 70, 23);

        btnVaksinCode1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnVaksinCode1.setMnemonic('1');
        btnVaksinCode1.setText("Reaksi Alergi");
        btnVaksinCode1.setToolTipText("Alt+1");
        btnVaksinCode1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        btnVaksinCode1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVaksinCode1.setName("btnVaksinCode1"); // NOI18N
        btnVaksinCode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaksinCode1ActionPerformed(evt);
            }
        });
        btnVaksinCode1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnVaksinCode1KeyPressed(evt);
            }
        });
        FormInput.add(btnVaksinCode1);
        btnVaksinCode1.setBounds(710, 70, 110, 23);

        ReaksiDisplay.setEditable(false);
        ReaksiDisplay.setHighlighter(null);
        ReaksiDisplay.setName("ReaksiDisplay"); // NOI18N
        ReaksiDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ReaksiDisplayKeyPressed(evt);
            }
        });
        FormInput.add(ReaksiDisplay);
        ReaksiDisplay.setBounds(420, 70, 280, 23);

        ReaksiSystem.setEditable(false);
        ReaksiSystem.setHighlighter(null);
        ReaksiSystem.setName("ReaksiSystem"); // NOI18N
        ReaksiSystem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ReaksiSystemKeyPressed(evt);
            }
        });
        FormInput.add(ReaksiSystem);
        ReaksiSystem.setBounds(160, 70, 250, 23);

        ReaksiCode.setEditable(false);
        ReaksiCode.setHighlighter(null);
        ReaksiCode.setName("ReaksiCode"); // NOI18N
        ReaksiCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ReaksiCodeKeyPressed(evt);
            }
        });
        FormInput.add(ReaksiCode);
        ReaksiCode.setBounds(70, 70, 80, 23);

        jLabel19.setText("Reaksi :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 70, 70, 23);

        jLabel15.setText("Critical :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 140, 70, 23);

        cmbSeverity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "low", "high", "unable-to-assess" }));
        cmbSeverity.setName("cmbSeverity"); // NOI18N
        cmbSeverity.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbSeverity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbSeverityKeyPressed(evt);
            }
        });
        FormInput.add(cmbSeverity);
        cmbSeverity.setBounds(70, 140, 120, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        //Valid.pindah(evt,Status,KdDokter);

}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || NmPasien.getText().trim().equals("")) {
            Valid.textKosong(TCari, "Pasien");
        } else if (AlergiCode.getText().trim().equals("")) {
            Valid.textKosong(AlergiCode, "Kode Vaksin");
        } else if (ReaksiCode.getText().trim().equals("")) {
            Valid.textKosong(ReaksiCode, "Reaksi");
        } else if (TKeterangan.getText().trim().equals("")) {
            Valid.textKosong(TKeterangan, "Note / Keterangan");
        } else {
            if (Sequel.menyimpantf("alergi_pasien", "?,?,?,?,?,?,?,?", "No.Rawat", 8, new String[]{
                TNoRw.getText(), Sequel.cariIsi("select CURRENT_TIMESTAMP()"), AlergiCode.getText(), cmbKategory.getSelectedItem().toString(), akses.getkode(), TKeterangan.getText(), ReaksiCode.getText(), cmbSeverity.getSelectedItem().toString()
            }) == true) {
                tampil();
                emptTeks();

            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            //Valid.pindah(evt,Catatan,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                if (Sequel.queryu2tf("delete from alergi_pasien where no_rawat=?", 1, new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Gagal menghapus..!!");
                }
            } else {
                if (akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString())) {
                    if (Sequel.queryu2tf("delete from alergi_pasien where no_rawat=?", 1, new String[]{
                        tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                    }) == true) {
                        tampil();
                        emptTeks();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Gagal menghapus..!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed

        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed

}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, NmPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {

            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || NmPasien.getText().trim().equals("")) {
            Valid.textKosong(TCari, "Pasien");
        } else if (AlergiCode.getText().trim().equals("")) {
            Valid.textKosong(AlergiCode, "Kode Vaksin");
        } else if (ReaksiCode.getText().trim().equals("")) {
            Valid.textKosong(ReaksiCode, "Reaksi");
        } else if (TKeterangan.getText().trim().equals("")) {
            Valid.textKosong(TKeterangan, "Note / Keterangan");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        aktif = true;
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        aktif = false;
    }//GEN-LAST:event_formWindowClosed

    private void DTPCari2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari2ItemStateChanged

    }//GEN-LAST:event_DTPCari2ItemStateChanged

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged

    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void btnVaksinCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaksinCodeActionPerformed
        mode = "alergi";
        allergycode.isCek("alergi");
        allergycode.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        allergycode.setLocationRelativeTo(internalFrame1);
        allergycode.setVisible(true);
    }//GEN-LAST:event_btnVaksinCodeActionPerformed

    private void btnVaksinCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnVaksinCodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVaksinCodeKeyPressed

    private void AlergyDisplayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergyDisplayKeyPressed

    }//GEN-LAST:event_AlergyDisplayKeyPressed

    private void AlergySystemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergySystemKeyPressed

    }//GEN-LAST:event_AlergySystemKeyPressed

    private void AlergiCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiCodeKeyPressed
        Valid.pindah(evt, TCari, AlergySystem);
    }//GEN-LAST:event_AlergiCodeKeyPressed

    private void TKeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeteranganKeyPressed

    }//GEN-LAST:event_TKeteranganKeyPressed

    private void cmbKategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKategoryKeyPressed

    }//GEN-LAST:event_cmbKategoryKeyPressed

    private void btnVaksinCode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaksinCode1ActionPerformed
        mode = "reaksialergi";
        allergycode.isCek("reaksialergi");
        allergycode.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        allergycode.setLocationRelativeTo(internalFrame1);
        allergycode.setVisible(true);
    }//GEN-LAST:event_btnVaksinCode1ActionPerformed

    private void btnVaksinCode1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnVaksinCode1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVaksinCode1KeyPressed

    private void ReaksiDisplayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReaksiDisplayKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReaksiDisplayKeyPressed

    private void ReaksiSystemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReaksiSystemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReaksiSystemKeyPressed

    private void ReaksiCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReaksiCodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReaksiCodeKeyPressed

    private void cmbSeverityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSeverityKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSeverityKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDataAlergiPasien dialog = new DlgDataAlergiPasien(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox AlergiCode;
    private widget.TextBox AlergyDisplay;
    private widget.TextBox AlergySystem;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.TextBox NmPasien;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox ReaksiCode;
    private widget.TextBox ReaksiDisplay;
    private widget.TextBox ReaksiSystem;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextArea TKeterangan;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.Button btnVaksinCode;
    private widget.Button btnVaksinCode1;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbKategory;
    private widget.ComboBox cmbSeverity;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel25;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            //"No.Rawat", "No.RM", "Nama Pasien", "J.K.", "Umur", "No.Telp", "Cara Bayar", "TANGGAL", "KODE", "ALERGI", "KATEGORI", "REAKSI KODE", "SEVERITY", "NOTE", "NIP", "Nama Petugas"
            ps = koneksi.prepareStatement(
                    "SELECT alergi_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,TIMESTAMPDIFF(YEAR, pasien.tgl_lahir, CURDATE()) AS umur, "
                    + "	pasien.no_tlp,penjab.png_jawab,alergi_pasien.tgl_perawatan,alergi_pasien.allergy_code,satu_sehat_ref_allergy.display,alergi_pasien.category, "
                    + "	alergi_pasien.reactioncode,alergi_pasien.severity,alergi_pasien.note,alergi_pasien.nippetugas,pegawai.nama FROM reg_periksa INNER JOIN "
                    + "	pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj INNER JOIN alergi_pasien "
                    + "	ON reg_periksa.no_rawat = alergi_pasien.no_rawat INNER JOIN pegawai ON alergi_pasien.nippetugas = pegawai.nik INNER JOIN "
                    + "	satu_sehat_ref_allergy ON alergi_pasien.allergy_code = satu_sehat_ref_allergy.kode INNER JOIN satu_sehat_ref_allergy_reaction ON "
                    + "	alergi_pasien.reactioncode = satu_sehat_ref_allergy_reaction.kode where alergi_pasien.tgl_perawatan between ? and ? "
                    + (TCari.getText().equals("") ? "" : " and alergi_pasien.no_rawat like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? ")
                    + " order by alergi_pasien.no_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                if (!TCari.getText().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                }
//                System.out.println(ps.toString());
                rs = ps.executeQuery();
                while (rs.next()) {
                    //"No.Rawat", "No.RM", "Nama Pasien", "J.K.", "Umur", "No.Telp", "Cara Bayar", "TANGGAL", "KODE", "ALERGI", "KATEGORI", "REAKSI KODE", "SEVERITY", "NOTE", "NIP", "Nama Petugas"
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("jk"), rs.getString("umur"), rs.getString("no_tlp"),
                        rs.getString("png_jawab"), rs.getString("tgl_perawatan"), rs.getString("allergy_code"), rs.getString("display"), rs.getString("category"), rs.getString("reactioncode"),
                        rs.getString("severity"), rs.getString("note"), rs.getString("nippetugas"), rs.getString("nama")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif Vaksin : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TNoRw.setText("");
        TNoRM.setText("");
        NmPasien.setText("");
        ReaksiCode.setText("");
        AlergiCode.setText("");
        AlergySystem.setText("");
        AlergyDisplay.setText("");
        ReaksiDisplay.setText("");
        ReaksiSystem.setText("");
        TKeterangan.setText("");
        cmbKategory.setSelectedIndex(0);
        cmbSeverity.setSelectedIndex(0);
    }

    private void getData() {
        // "No.Rawat", "No.RM", "Nama Pasien", "J.K.", "Umur", "No.Telp", "Cara Bayar", "TANGGAL", "KODE", "ALERGI", "KATEGORI", "REAKSI KODE", "SEVERITY", "NOTE", "NIP", "Nama Petugas"
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            NmPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            ReaksiCode.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            AlergiCode.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            AlergySystem.setText(Sequel.cariIsi("select system from satu_sehat_ref_allergy where kode='" + AlergiCode.getText() + "'"));
            AlergyDisplay.setText(Sequel.cariIsi("select display from satu_sehat_ref_allergy where kode='" + AlergiCode.getText() + "'"));
            ReaksiDisplay.setText(Sequel.cariIsi("select display from satu_sehat_ref_allergy_reaction where kode='" + ReaksiCode.getText() + "'"));
            ReaksiSystem.setText(Sequel.cariIsi("select system from satu_sehat_ref_allergy_reaction where kode='" + ReaksiCode.getText() + "'"));
            TKeterangan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            cmbKategory.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            cmbSeverity.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
        }
    }

    public void setNoRm(String norwt, String norm, String nama) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        NmPasien.setText(nama);
        TCari.setText(norwt);
        //DTPCari2.setDate(tgl2);
        aktif = false;
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getresume_pasien());
        BtnHapus.setEnabled(akses.getresume_pasien());
        BtnEdit.setEnabled(akses.getresume_pasien());
    }

    private void ganti() {

        if (Sequel.mengedittf("alergi_pasien", "no_rawat=?", "no_rawat=?,tgl_perawatan=?,allergy_code=?,category=?,nippetugas=?,note=?,reactioncode=?,severity=?", 9, new String[]{
            TNoRw.getText(), Sequel.cariIsi("select CURRENT_TIMESTAMP()"), AlergiCode.getText(), cmbKategory.getSelectedItem().toString(), akses.getkode(), TKeterangan.getText(), ReaksiCode.getText(), cmbSeverity.getSelectedItem().toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
        }) == true) {
            tampil();
            emptTeks();
        }
    }

    public JTable getTable() {
        return tbObat;
    }

}
