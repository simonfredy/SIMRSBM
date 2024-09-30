/*
 * Kontribusi dari RSUD Prembun
 */


package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianAwalMedisRanapTHT extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianAwalMedisRanapTHT(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Anamnesis","Hubungan","Keluhan Utama",
            "Riwayat Penyakit Sekarang","Riwayat Penyakit Dahulu","Riwayat Penggunaan Obat","Alergi","TD","Nadi","RR","Suhu","BB","TB",
            "Nyeri","Status Nutrisi","Kondisi Umum","Sekret Telinga Kanan","Sekret Telinga Kiri","Tuli Telinga Kanan","Tuli Telinga Kiri",
            "Tumor Telinga Kanan","Tumor Telinga Kiri","Tinitus Telinga Kanan","Tinitus Telinga Kiri","Sakit Telinga Kanan","Sakit Telinga Kiri",
            "Korpus Alienum Telinga Kanan","Korpus Alienum Telinga Kiri","Vertigo Telinga Kanan","Vertigo Telinga Kiri","Sekret Hidung Kanan",
            "Sekret Hidung Kiri","Tersumbat Hidung Kanan","Tersumbat Hidung Kiri","Tumor Hidung Kanan","Tumor Hidung Kiri","Pilek Hidung Kanan",
            "Pilek Hidung Kiri","Sakit Hidung Kanan","Sakit Hidung Kiri","Korpus Alienum Hidung Kanan","Korpus Alienum Hidung Kiri","Bersin Hidung Kanan",
            "Bersin Hidung Kiri","Riak Tenggorok","Gangguan Tenggorok","Suara Tenggorok","Tumor Tenggorok","Batuk Tenggorok","Korpus ALienum Tenggorok",
            "Sesak Nafas Tenggorok","Ket.Lokalis","Daun Telinga Kanan","Daun Telinga Kiri","Liang Telinga Kanan","Liang Telinga Kiri",
            "Discharge Telinga Kanan","Discharge Telinga Kiri","Membrana Timpani Telinga Kanan","Membrana Timpani Telinga Kiri","Tumor Telinga Kanan",
            "Tumor Telinga Kiri","Mastoid Telinga Kanan","Mastoid Telinga Kiri","Berbisik Telinga Kanan","Berbisik Telinga Kiri","Weber Telinga Kanan",
            "Weber Telinga Kiri","Rinne Telinga Kanan","Rinne Telinga Kiri","Scwabach Telinga Kanan","Scwabach Telinga Kiri","BOA Telinga Kanan","BOA Telinga Kiri","Tympanometri Telinga Kanan",
            "Tympanometri Telinga Kiri","Audiometri Telinga Kanan","Audiometri Telinga Kiri","Nada Murni Telinga Kanan","Nada Murni Telinga Kiri",
            "BERA Telinga Kanan","BERA Telinga Kiri","OAE Telinga Kanan","OAE Telinga Kiri","Tes Alat Keseimbangan Telinga Kanan","Tes Alat Keseimbangan Telinga Kiri",
            "Hidung Luar Kanan","Hidung Luar Kiri","Kavum Nasi Kanan","Kavum Nasi Kiri","Septum Kanan","Septum Kiri","Discharge Hidung Kanan",
            "Discharge Hidung Kiri","Mukosa Hidung Kanan","Mukosa Hidung Kiri","Tumor Hidung Kanan","Tumor Hidung Kiri","Konka Kanan","Konka Kiri",
            "Naso Endoskopi Kanan","Naso Endoskopi Kiri","Dispenu Tenggorok","Stridor Tenggorok","Sianosis Tenggorok","Suara Tenggorok","Mucosa Tenggorok",
            "Tonsil Tenggorok","Dinding Belakang Tenggorok","Epiglotis","Plika Vokalis","Aritenoid","Rimaglotis","Plika Ventrikuloris","Endoskopi",
            "Diagnosa Kerja","Diagnosa Pembanding","Kelenjar Limpe Leher","Terapi","Boleh Pulang","Tgl.Keluar","Jam Keluar","Kontrol","Tgl.Kontrol",
            "Jam Kontrol","Dirawat Diruang","Ruang Lain"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 135; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(55);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(115);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(300);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setPreferredWidth(120);
            }else if(i==15){
                column.setPreferredWidth(60);
            }else if(i==16){
                column.setPreferredWidth(40);
            }else if(i==17){
                column.setPreferredWidth(35);
            }else if(i==18){
                column.setPreferredWidth(35);
            }else if(i==19){
                column.setPreferredWidth(35);
            }else if(i==20){
                column.setPreferredWidth(35);
            }else if(i==21){
                column.setPreferredWidth(120);
            }else if(i==22){
                column.setPreferredWidth(120);
            }else if(i==23){
                column.setPreferredWidth(200);
            }else if(i==24){
                column.setPreferredWidth(200);
            }else if(i==25){
                column.setPreferredWidth(170);
            }else if(i==26){
                column.setPreferredWidth(170);
            }else if(i==27){
                column.setPreferredWidth(170);
            }else if(i==28){
                column.setPreferredWidth(170);
            }else if(i==29){
                column.setPreferredWidth(150);
            }else if(i==30){
                column.setPreferredWidth(150);
            }else if(i==31){
                column.setPreferredWidth(200);
            }else if(i==32){
                column.setPreferredWidth(200);
            }else if(i==33){
                column.setPreferredWidth(200);
            }else if(i==34){
                column.setPreferredWidth(200);
            }else if(i==35){
                column.setPreferredWidth(150);
            }else if(i==36){
                column.setPreferredWidth(150);
            }else if(i==37){
                column.setPreferredWidth(150);
            }else if(i==38){
                column.setPreferredWidth(150);
            }else if(i==39){
                column.setPreferredWidth(150);
            }else if(i==40){
                column.setPreferredWidth(150);
            }else if(i==41){
                column.setPreferredWidth(150);
            }else if(i==42){
                column.setPreferredWidth(150);
            }else if(i==43){
                column.setPreferredWidth(150);
            }else if(i==44){
                column.setPreferredWidth(150);
            }else if(i==45){
                column.setPreferredWidth(150);
            }else if(i==46){
                column.setPreferredWidth(150);
            }else if(i==47){
                column.setPreferredWidth(150);
            }else if(i==48){
                column.setPreferredWidth(150);
            }else if(i==49){
                column.setPreferredWidth(150);
            }else if(i==50){
                column.setPreferredWidth(150);
            }else if(i==51){
                column.setPreferredWidth(150);
            }else if(i==52){
                column.setPreferredWidth(150);
            }else if(i==53){
                column.setPreferredWidth(150);
            }else if(i==54){
                column.setPreferredWidth(150);
            }else if(i==55){
                column.setPreferredWidth(150);
            }else if(i==56){
                column.setPreferredWidth(150);
            }else if(i==57){
                column.setPreferredWidth(150);
            }else if(i==58){
                column.setPreferredWidth(150);
            }else if(i==59){
                column.setPreferredWidth(150);
            }else if(i==60){
                column.setPreferredWidth(150);
            }else if(i==61){
                column.setPreferredWidth(150);
            }else if(i==62){
                column.setPreferredWidth(150);
            }else if(i==63){
                column.setPreferredWidth(150);
            }else if(i==64){
                column.setPreferredWidth(150);
            }else if(i==65){
                column.setPreferredWidth(150);
            }else if(i==66){
                column.setPreferredWidth(150);
            }else if(i==67){
                column.setPreferredWidth(150);
            }else if(i==68){
                column.setPreferredWidth(150);
            }else if(i==69){
                column.setPreferredWidth(150);
            }else if(i==70){
                column.setPreferredWidth(150);
            }else if(i==71){
                column.setPreferredWidth(150);
            }else if(i==72){
                column.setPreferredWidth(150);
            }else if(i==73){
                column.setPreferredWidth(150);
            }else if(i==74){
                column.setPreferredWidth(150);
            }else if(i==75){
                column.setPreferredWidth(150);
            }else if(i==76){
                column.setPreferredWidth(150);
            }else if(i==77){
                column.setPreferredWidth(150);
            }else if(i==78){
                column.setPreferredWidth(150);
            }else if(i==79){
                column.setPreferredWidth(150);
            }else if(i==80){
                column.setPreferredWidth(150);
            }else if(i==81){
                column.setPreferredWidth(150);
            }else if(i==82){
                column.setPreferredWidth(150);
            }else if(i==83){
                column.setPreferredWidth(150);
            }else if(i==84){
                column.setPreferredWidth(150);
            }else if(i==85){
                column.setPreferredWidth(150);
            }else if(i==86){
                column.setPreferredWidth(150);
            }else if(i==87){
                column.setPreferredWidth(150);
            }else if(i==88){
                column.setPreferredWidth(150);
            }else if(i==89){
                column.setPreferredWidth(150);
            }else if(i==90){
                column.setPreferredWidth(150);
            }else if(i==91){
                column.setPreferredWidth(150);
            }else if(i==92){
                column.setPreferredWidth(150);
            }else if(i==93){
                column.setPreferredWidth(150);
            }else if(i==94){
                column.setPreferredWidth(150);
            }else if(i==95){
                column.setPreferredWidth(150);
            }else if(i==96){
                column.setPreferredWidth(150);
            }else if(i==97){
                column.setPreferredWidth(150);
            }else if(i==98){
                column.setPreferredWidth(150);
            }else if(i==99){
                column.setPreferredWidth(150);
            }else if(i==100){
                column.setPreferredWidth(150);
            }else if(i==101){
                column.setPreferredWidth(150);
            }else if(i==102){
                column.setPreferredWidth(150);
            }else if(i==103){
                column.setPreferredWidth(150);
            }else if(i==104){
                column.setPreferredWidth(150);
            }else if(i==105){
                column.setPreferredWidth(150);
            }else if(i==106){
                column.setPreferredWidth(150);
            }else if(i==107){
                column.setPreferredWidth(150);
            }else if(i==108){
                column.setPreferredWidth(150);
            }else if(i==109){
                column.setPreferredWidth(150);
            }else if(i==110){
                column.setPreferredWidth(150);
            }else if(i==111){
                column.setPreferredWidth(150);
            }else if(i==112){
                column.setPreferredWidth(150);
            }else if(i==113){
                column.setPreferredWidth(150);
            }else if(i==114){
                column.setPreferredWidth(150);
            }else if(i==115){
                column.setPreferredWidth(150);
            }else if(i==116){
                column.setPreferredWidth(150);
            }else if(i==117){
                column.setPreferredWidth(150);
            }else if(i==118){
                column.setPreferredWidth(150);
            }else if(i==119){
                column.setPreferredWidth(150);
            }else if(i==120){
                column.setPreferredWidth(150);
            }else if(i==121){
                column.setPreferredWidth(150);
            }else if(i==120){
                column.setPreferredWidth(150);
            }else if(i==121){
                column.setPreferredWidth(150);
            }else if(i==122){
                column.setPreferredWidth(150);
            }else if(i==123){
                column.setPreferredWidth(150);
            }else if(i==124){
                column.setPreferredWidth(150);
            }else if(i==125){
                column.setPreferredWidth(150);
            }else if(i==126){
                column.setPreferredWidth(150);
            }else if(i==127){
                column.setPreferredWidth(150);
            }else if(i==128){
                column.setPreferredWidth(150);
            }else if(i==129){
                column.setPreferredWidth(150);
            }else if(i==130){
                column.setPreferredWidth(150);
            }else if(i==131){
                column.setPreferredWidth(150);
            }else if(i==132){
                column.setPreferredWidth(150);
            }else if(i==133){
                column.setPreferredWidth(150);
            }else if(i==134){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Hubungan.setDocument(new batasInput((int)30).getKata(Hubungan));
        KeluhanUtama.setDocument(new batasInput((int)2000).getKata(KeluhanUtama));
        RPS.setDocument(new batasInput((int)2000).getKata(RPS));
        RPD.setDocument(new batasInput((int)1000).getKata(RPD));
        RPO.setDocument(new batasInput((int)1000).getKata(RPO));
        Alergi.setDocument(new batasInput((int)50).getKata(Alergi));
        TD.setDocument(new batasInput((byte)8).getKata(TD));
        Nadi.setDocument(new batasInput((byte)5).getKata(Nadi));
        RR.setDocument(new batasInput((byte)5).getKata(RR));
        Suhu.setDocument(new batasInput((byte)5).getKata(Suhu));
        BB.setDocument(new batasInput((byte)5).getKata(BB));
        TB.setDocument(new batasInput((byte)5).getKata(TB));
        Nyeri.setDocument(new batasInput((byte)50).getKata(Nyeri));
        StatusNutrisi.setDocument(new batasInput((byte)50).getKata(StatusNutrisi));
        Kondisi.setDocument(new batasInput((int)3000).getKata(Kondisi));
        SekretKanan.setDocument(new batasInput((int)50).getKata(SekretKanan));
        SekretKiri.setDocument(new batasInput((int)50).getKata(SekretKiri));
        TuliKanan.setDocument(new batasInput((int)50).getKata(TuliKanan));
        TuliKiri.setDocument(new batasInput((int)50).getKata(TuliKiri));
        TumorKanan.setDocument(new batasInput((int)50).getKata(TumorKanan));
        TumorKiri.setDocument(new batasInput((int)50).getKata(TumorKiri));
        TinitusKanan.setDocument(new batasInput((int)50).getKata(TinitusKanan));
        TinitusKiri.setDocument(new batasInput((int)50).getKata(TinitusKiri));
        SakitKanan.setDocument(new batasInput((int)50).getKata(SakitKanan));
        SakitKiri.setDocument(new batasInput((int)50).getKata(SakitKiri));
        KorpusAlienumKanan.setDocument(new batasInput((int)50).getKata(KorpusAlienumKanan));
        KorpusAlienumKiri.setDocument(new batasInput((int)50).getKata(KorpusAlienumKiri));
        VertigoKanan.setDocument(new batasInput((int)50).getKata(VertigoKanan));
        VertigoKiri.setDocument(new batasInput((int)50).getKata(VertigoKiri));
        SekretHidungKanan.setDocument(new batasInput((int)50).getKata(SekretHidungKanan));
        SekretHidungKiri.setDocument(new batasInput((int)50).getKata(SekretHidungKiri));
        TersumbatKanan.setDocument(new batasInput((int)50).getKata(TersumbatKanan));
        TersumbatKiri.setDocument(new batasInput((int)50).getKata(TersumbatKiri));
        TumorHidungKanan.setDocument(new batasInput((int)50).getKata(TumorHidungKanan));
        TumorHidungKiri.setDocument(new batasInput((int)50).getKata(TumorHidungKiri));
        PilekKanan.setDocument(new batasInput((int)50).getKata(PilekKanan));
        PilekKiri.setDocument(new batasInput((int)50).getKata(PilekKiri));
        SakitHidungKanan.setDocument(new batasInput((int)50).getKata(SakitHidungKanan));
        SakitHidungKiri.setDocument(new batasInput((int)50).getKata(SakitHidungKiri));
        KorpusAlienumHidungKanan.setDocument(new batasInput((int)50).getKata(KorpusAlienumHidungKanan));
        KorpusAlienumHidungKiri.setDocument(new batasInput((int)50).getKata(KorpusAlienumHidungKiri));
        BersinKanan.setDocument(new batasInput((int)50).getKata(BersinKanan));
        BersinKiri.setDocument(new batasInput((int)50).getKata(BersinKiri));
        TenggorokRiak.setDocument(new batasInput((int)50).getKata(TenggorokRiak));
        TenggorokGangguan.setDocument(new batasInput((int)50).getKata(TenggorokGangguan));
        TenggorokSuara.setDocument(new batasInput((int)50).getKata(TenggorokSuara));
        TenggorokTumor.setDocument(new batasInput((int)50).getKata(TenggorokTumor));
        TenggorokBatuk.setDocument(new batasInput((int)50).getKata(TenggorokBatuk));
        TinitusKanan.setDocument(new batasInput((int)50).getKata(TinitusKanan));
        TenggorokKorpusAlienum.setDocument(new batasInput((int)50).getKata(TenggorokKorpusAlienum));
        TenggorokSesakNafas.setDocument(new batasInput((int)50).getKata(TenggorokSesakNafas));
        TenggorokKorpusAlienum.setDocument(new batasInput((int)50).getKata(TenggorokKorpusAlienum));
        KetLokalis.setDocument(new batasInput((int)3000).getKata(KetLokalis));
        DaunTelingaKanan.setDocument(new batasInput((int)50).getKata(DaunTelingaKanan));
        DaunTelingaKiri.setDocument(new batasInput((int)50).getKata(DaunTelingaKiri));
        DaunTelingaKanan.setDocument(new batasInput((int)50).getKata(LiangTelingaKanan));
        DaunTelingaKiri.setDocument(new batasInput((int)50).getKata(DaunTelingaKiri));
        DischargeKanan.setDocument(new batasInput((int)50).getKata(DischargeKanan));
        DischargeKiri.setDocument(new batasInput((int)50).getKata(DischargeKiri));
        MembranTimpaniKanan.setDocument(new batasInput((int)50).getKata(MembranTimpaniKanan));
        MembranTimpaniKiri.setDocument(new batasInput((int)50).getKata(MembranTimpaniKiri));
        TumorKanan.setDocument(new batasInput((int)50).getKata(TumorKanan));
        TumorKiri.setDocument(new batasInput((int)50).getKata(TumorKiri));
        MastoidKanan.setDocument(new batasInput((int)50).getKata(MastoidKanan));
        MastoidKiri.setDocument(new batasInput((int)50).getKata(MastoidKiri));
        BerbisikKanan.setDocument(new batasInput((int)50).getKata(BerbisikKanan));
        BerbisikKiri.setDocument(new batasInput((int)50).getKata(BerbisikKiri));
        WeberKanan.setDocument(new batasInput((int)50).getKata(WeberKanan));
        WeberKiri.setDocument(new batasInput((int)50).getKata(WeberKiri));
        RinneKanan.setDocument(new batasInput((int)50).getKata(RinneKanan));
        RinneKiri.setDocument(new batasInput((int)50).getKata(RinneKiri));
        ScwabachKanan.setDocument(new batasInput((int)50).getKata(ScwabachKanan));
        ScwabachKiri.setDocument(new batasInput((int)50).getKata(ScwabachKiri));
        BOAKanan.setDocument(new batasInput((int)50).getKata(BOAKanan));
        BOAKiri.setDocument(new batasInput((int)50).getKata(BOAKiri));
        TympanometriKanan.setDocument(new batasInput((int)50).getKata(TympanometriKanan));
        TympanometriKiri.setDocument(new batasInput((int)50).getKata(TympanometriKiri));
        AudiometriKanan.setDocument(new batasInput((int)50).getKata(AudiometriKanan));
        AudiometriKiri.setDocument(new batasInput((int)50).getKata(AudiometriKiri));
        NadaMurniKanan.setDocument(new batasInput((int)50).getKata(NadaMurniKanan));
        NadaMurniKiri.setDocument(new batasInput((int)50).getKata(NadaMurniKiri));
        BERAKanan.setDocument(new batasInput((int)50).getKata(BERAKanan));
        BERAKiri.setDocument(new batasInput((int)50).getKata(BERAKiri));
        OAEKanan.setDocument(new batasInput((int)50).getKata(OAEKanan));
        OAEKiri.setDocument(new batasInput((int)50).getKata(OAEKiri));
        TesAlatKeseimbanganKanan.setDocument(new batasInput((int)50).getKata(TesAlatKeseimbanganKanan));
        TesAlatKeseimbanganKiri.setDocument(new batasInput((int)50).getKata(TesAlatKeseimbanganKiri));
        HidungLuarKanan.setDocument(new batasInput((int)50).getKata(HidungLuarKanan));
        HidungLuarKiri.setDocument(new batasInput((int)50).getKata(HidungLuarKiri));
        KavumNasiKanan.setDocument(new batasInput((int)50).getKata(KavumNasiKanan));
        KavumNasiKiri.setDocument(new batasInput((int)50).getKata(KavumNasiKiri));
        SeptumHidungKanan.setDocument(new batasInput((int)50).getKata(SeptumHidungKanan));
        SeptumHidungKiri.setDocument(new batasInput((int)50).getKata(SeptumHidungKiri));
        DischargeHidungKanan.setDocument(new batasInput((int)50).getKata(DischargeHidungKanan));
        DischargeHidungKiri.setDocument(new batasInput((int)50).getKata(DischargeHidungKiri));
        MukosaHidungKanan.setDocument(new batasInput((int)50).getKata(MukosaHidungKanan));
        MukosaHidungKiri.setDocument(new batasInput((int)50).getKata(MukosaHidungKiri));
        TumorHidungKanann.setDocument(new batasInput((int)50).getKata(TumorHidungKanann));
        TumorHidungKirii.setDocument(new batasInput((int)50).getKata(TumorHidungKirii));
        KonkaHidungKanan.setDocument(new batasInput((int)50).getKata(KonkaHidungKanan));
        KonkaHidungKiri.setDocument(new batasInput((int)50).getKata(KonkaHidungKiri));
        NasoEndoskopiKanan.setDocument(new batasInput((int)50).getKata(NasoEndoskopiKanan));
        NasoEndoskopiKiri.setDocument(new batasInput((int)50).getKata(NasoEndoskopiKiri));
        TenggorokDispenu.setDocument(new batasInput((int)50).getKata(TenggorokDispenu));
        TenggorokStridor.setDocument(new batasInput((int)50).getKata(TenggorokStridor));
        TenggorokSianosis.setDocument(new batasInput((int)50).getKata(TenggorokSianosis));
        TenggorokSuaraa.setDocument(new batasInput((int)50).getKata(TenggorokSuaraa));
        TenggorokMucosa.setDocument(new batasInput((int)50).getKata(TenggorokMucosa));
        TenggorokTonsil.setDocument(new batasInput((int)50).getKata(TenggorokTonsil));
        TenggorokDindingBelakang.setDocument(new batasInput((int)50).getKata(TenggorokDindingBelakang));
        LaringEpiglotis.setDocument(new batasInput((int)50).getKata(LaringEpiglotis));
        LaringPlikaVokalis.setDocument(new batasInput((int)50).getKata(LaringPlikaVokalis));
        LaringArienoid.setDocument(new batasInput((int)50).getKata(LaringArienoid));
        LaringRimaglotis.setDocument(new batasInput((int)50).getKata(LaringRimaglotis));
        LaringPlikaVentrikuloris.setDocument(new batasInput((int)50).getKata(LaringPlikaVentrikuloris));
        LaringEndoskopi.setDocument(new batasInput((int)50).getKata(LaringEndoskopi));
        DiagnosaKerja.setDocument(new batasInput((int)500).getKata(DiagnosaKerja));
        DiagnosaBanding.setDocument(new batasInput((int)500).getKata(DiagnosaBanding));
        Terapi.setDocument(new batasInput((int)3000).getKata(Terapi));
        KelenjerLimpeLeher.setDocument(new batasInput((int)3000).getKata(KelenjerLimpeLeher));
        JamPulang.setDocument(new batasInput((int)50).getKata(JamPulang));
        JamKontrol.setDocument(new batasInput((int)50).getKata(JamKontrol));
        RuangLain.setDocument(new batasInput((int)50).getKata(RuangLain));


        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KdDokter.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianMedis = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel9 = new widget.Label();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        BB = new widget.TextBox();
        jLabel13 = new widget.Label();
        TB = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel22 = new widget.Label();
        TD = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        RR = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel37 = new widget.Label();
        Alergi = new widget.TextBox();
        Anamnesis = new widget.ComboBox();
        scrollPane1 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        jLabel30 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        RPD = new widget.TextArea();
        jLabel31 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        RPO = new widget.TextArea();
        scrollPane5 = new widget.ScrollPane();
        Kondisi = new widget.TextArea();
        jLabel28 = new widget.Label();
        StatusNutrisi = new widget.TextBox();
        jLabel94 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel38 = new widget.Label();
        Hubungan = new widget.TextBox();
        jLabel33 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        RPS = new widget.TextArea();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel29 = new widget.Label();
        Nyeri = new widget.TextBox();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel99 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        scrollPane8 = new widget.ScrollPane();
        KetLokalis = new widget.TextArea();
        jLabel100 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        DiagnosaKerja = new widget.TextArea();
        jSeparator16 = new javax.swing.JSeparator();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jSeparator17 = new javax.swing.JSeparator();
        scrollPane15 = new widget.ScrollPane();
        DiagnosaBanding = new widget.TextArea();
        jLabel40 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        Terapi = new widget.TextArea();
        scrollPane17 = new widget.ScrollPane();
        KelenjerLimpeLeher = new widget.TextArea();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel85 = new widget.Label();
        jLabel86 = new widget.Label();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        jLabel95 = new widget.Label();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        DaunTelingaKanan = new widget.TextBox();
        jLabel105 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel98 = new widget.Label();
        LiangTelingaKanan = new widget.TextBox();
        jLabel118 = new widget.Label();
        DaunTelingaKiri = new widget.TextBox();
        LiangTelingaKiri = new widget.TextBox();
        DischargeKanan = new widget.TextBox();
        DischargeKiri = new widget.TextBox();
        MembranTimpaniKanan = new widget.TextBox();
        MembranTimpaniKiri = new widget.TextBox();
        TumorKanan = new widget.TextBox();
        TumorKiri = new widget.TextBox();
        MastoidKanan = new widget.TextBox();
        MastoidKiri = new widget.TextBox();
        BerbisikKanan = new widget.TextBox();
        BerbisikKiri = new widget.TextBox();
        WeberKanan = new widget.TextBox();
        WeberKiri = new widget.TextBox();
        RinneKanan = new widget.TextBox();
        RinneKiri = new widget.TextBox();
        ScwabachKanan = new widget.TextBox();
        ScwabachKiri = new widget.TextBox();
        BOAKanan = new widget.TextBox();
        BOAKiri = new widget.TextBox();
        TympanometriKanan = new widget.TextBox();
        TympanometriKiri = new widget.TextBox();
        AudiometriKanan = new widget.TextBox();
        AudiometriKiri = new widget.TextBox();
        NadaMurniKanan = new widget.TextBox();
        NadaMurniKiri = new widget.TextBox();
        BERAKanan = new widget.TextBox();
        BERAKiri = new widget.TextBox();
        OAEKanan = new widget.TextBox();
        OAEKiri = new widget.TextBox();
        TesAlatKeseimbanganKanan = new widget.TextBox();
        TesAlatKeseimbanganKiri = new widget.TextBox();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel101 = new widget.Label();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel104 = new widget.Label();
        SekretKanan = new widget.TextBox();
        SekretKiri = new widget.TextBox();
        jLabel108 = new widget.Label();
        TuliKanan = new widget.TextBox();
        TuliKiri = new widget.TextBox();
        jLabel120 = new widget.Label();
        TumorrKanan = new widget.TextBox();
        TumorrKiri = new widget.TextBox();
        jLabel121 = new widget.Label();
        TinitusKanan = new widget.TextBox();
        TinitusKiri = new widget.TextBox();
        jLabel122 = new widget.Label();
        SakitKanan = new widget.TextBox();
        SakitKiri = new widget.TextBox();
        jLabel123 = new widget.Label();
        KorpusAlienumKanan = new widget.TextBox();
        KorpusAlienumKiri = new widget.TextBox();
        jLabel124 = new widget.Label();
        VertigoKanan = new widget.TextBox();
        VertigoKiri = new widget.TextBox();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        jLabel127 = new widget.Label();
        jLabel128 = new widget.Label();
        SekretHidungKanan = new widget.TextBox();
        SekretHidungKiri = new widget.TextBox();
        jLabel129 = new widget.Label();
        TersumbatKanan = new widget.TextBox();
        TersumbatKiri = new widget.TextBox();
        jLabel130 = new widget.Label();
        TumorHidungKanan = new widget.TextBox();
        TumorHidungKiri = new widget.TextBox();
        jLabel131 = new widget.Label();
        PilekKanan = new widget.TextBox();
        PilekKiri = new widget.TextBox();
        jLabel132 = new widget.Label();
        SakitHidungKanan = new widget.TextBox();
        SakitHidungKiri = new widget.TextBox();
        jLabel133 = new widget.Label();
        KorpusAlienumHidungKanan = new widget.TextBox();
        KorpusAlienumHidungKiri = new widget.TextBox();
        jLabel134 = new widget.Label();
        BersinKanan = new widget.TextBox();
        BersinKiri = new widget.TextBox();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel135 = new widget.Label();
        jLabel136 = new widget.Label();
        jLabel137 = new widget.Label();
        jLabel138 = new widget.Label();
        jLabel139 = new widget.Label();
        jLabel140 = new widget.Label();
        jLabel141 = new widget.Label();
        jLabel142 = new widget.Label();
        TenggorokRiak = new widget.TextBox();
        TenggorokGangguan = new widget.TextBox();
        TenggorokSuara = new widget.TextBox();
        TenggorokTumor = new widget.TextBox();
        TenggorokBatuk = new widget.TextBox();
        TenggorokKorpusAlienum = new widget.TextBox();
        TenggorokSesakNafas = new widget.TextBox();
        jLabel143 = new widget.Label();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel144 = new widget.Label();
        HidungLuarKanan = new widget.TextBox();
        jLabel145 = new widget.Label();
        jLabel146 = new widget.Label();
        HidungLuarKiri = new widget.TextBox();
        jLabel147 = new widget.Label();
        KavumNasiKanan = new widget.TextBox();
        KavumNasiKiri = new widget.TextBox();
        jLabel148 = new widget.Label();
        SeptumHidungKanan = new widget.TextBox();
        SeptumHidungKiri = new widget.TextBox();
        jLabel149 = new widget.Label();
        DischargeHidungKanan = new widget.TextBox();
        DischargeHidungKiri = new widget.TextBox();
        jLabel150 = new widget.Label();
        MukosaHidungKanan = new widget.TextBox();
        MukosaHidungKiri = new widget.TextBox();
        jLabel151 = new widget.Label();
        TumorHidungKanann = new widget.TextBox();
        TumorHidungKirii = new widget.TextBox();
        jLabel152 = new widget.Label();
        KonkaHidungKanan = new widget.TextBox();
        KonkaHidungKiri = new widget.TextBox();
        jLabel153 = new widget.Label();
        NasoEndoskopiKanan = new widget.TextBox();
        NasoEndoskopiKiri = new widget.TextBox();
        jLabel154 = new widget.Label();
        jLabel155 = new widget.Label();
        TenggorokDispenu = new widget.TextBox();
        jLabel156 = new widget.Label();
        TenggorokSianosis = new widget.TextBox();
        jLabel157 = new widget.Label();
        TenggorokMucosa = new widget.TextBox();
        jLabel158 = new widget.Label();
        TenggorokDindingBelakang = new widget.TextBox();
        jLabel159 = new widget.Label();
        TenggorokStridor = new widget.TextBox();
        jLabel160 = new widget.Label();
        TenggorokSuaraa = new widget.TextBox();
        jLabel161 = new widget.Label();
        TenggorokTonsil = new widget.TextBox();
        jLabel162 = new widget.Label();
        jLabel163 = new widget.Label();
        LaringEpiglotis = new widget.TextBox();
        jLabel164 = new widget.Label();
        LaringArienoid = new widget.TextBox();
        jLabel165 = new widget.Label();
        LaringPlikaVokalis = new widget.TextBox();
        jLabel166 = new widget.Label();
        LaringRimaglotis = new widget.TextBox();
        jLabel167 = new widget.Label();
        LaringPlikaVentrikuloris = new widget.TextBox();
        jLabel168 = new widget.Label();
        LaringEndoskopi = new widget.TextBox();
        jLabel109 = new widget.Label();
        jLabel169 = new widget.Label();
        BolehPulang = new widget.ComboBox();
        jLabel170 = new widget.Label();
        JamPulang = new widget.TextBox();
        jLabel171 = new widget.Label();
        DTPTanggalKeluar = new widget.Tanggal();
        jLabel172 = new widget.Label();
        KontrolPoliklinik = new widget.ComboBox();
        jLabel173 = new widget.Label();
        DTPTanggalKontrol = new widget.Tanggal();
        jLabel174 = new widget.Label();
        JamKontrol = new widget.TextBox();
        jLabel175 = new widget.Label();
        DirawatDiRuang = new widget.ComboBox();
        RuangLain = new widget.TextBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianMedis.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianMedis.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianMedis.setText("Laporan Penilaian Medis");
        MnPenilaianMedis.setName("MnPenilaianMedis"); // NOI18N
        MnPenilaianMedis.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPenilaianMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianMedisActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianMedis);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pengkajian Awal Medis Rawat Inap THT ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 857));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2250));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        label14.setText("Dokter :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(74, 40, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(166, 40, 180, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(348, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        jLabel9.setText("Riwayat Penggunaan Obat :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 170, 180, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        jLabel12.setText("BB :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(325, 240, 30, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(359, 240, 45, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(407, 240, 30, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(234, 240, 45, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(200, 240, 30, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(670, 240, 50, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(622, 240, 45, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(578, 240, 40, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(448, 240, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(492, 240, 45, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 240, 64, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(68, 240, 76, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(540, 240, 30, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(147, 240, 50, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("cm");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(282, 240, 30, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(815, 240, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(767, 240, 45, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(698, 240, 65, 23);

        jLabel37.setText("Riwayat Alergi :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(440, 190, 150, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(594, 190, 260, 23);

        Anamnesis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Anamnesis.setName("Anamnesis"); // NOI18N
        Anamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis);
        Anamnesis.setBounds(644, 40, 128, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        KeluhanUtama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanUtama.setColumns(20);
        KeluhanUtama.setRows(5);
        KeluhanUtama.setName("KeluhanUtama"); // NOI18N
        KeluhanUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanUtamaKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(KeluhanUtama);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(129, 90, 310, 73);

        jLabel30.setText("Riwayat Penyakit Sekarang :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(440, 90, 150, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        RPD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPD.setColumns(20);
        RPD.setRows(5);
        RPD.setName("RPD"); // NOI18N
        RPD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPDKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(RPD);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(594, 140, 260, 43);

        jLabel31.setText("Riwayat Penyakit Dahulu :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(440, 140, 150, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        RPO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPO.setColumns(20);
        RPO.setRows(5);
        RPO.setName("RPO"); // NOI18N
        RPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPOKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(RPO);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(184, 170, 255, 42);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Kondisi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Kondisi.setColumns(20);
        Kondisi.setRows(5);
        Kondisi.setName("Kondisi"); // NOI18N
        Kondisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KondisiKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Kondisi);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(124, 300, 730, 73);

        jLabel28.setText("Status Nutrisi :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(450, 270, 100, 23);

        StatusNutrisi.setFocusTraversalPolicyProvider(true);
        StatusNutrisi.setName("StatusNutrisi"); // NOI18N
        StatusNutrisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusNutrisiKeyPressed(evt);
            }
        });
        FormInput.add(StatusNutrisi);
        StatusNutrisi.setBounds(554, 270, 300, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("TANDA-TANDA VITAL");
        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 220, 180, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 880, 1);

        jLabel38.setText("Anamnesis :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(570, 40, 70, 23);

        Hubungan.setName("Hubungan"); // NOI18N
        Hubungan.setPreferredSize(new java.awt.Dimension(207, 23));
        Hubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganKeyPressed(evt);
            }
        });
        FormInput.add(Hubungan);
        Hubungan.setBounds(774, 40, 80, 23);

        jLabel33.setText("Keluhan Utama :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 90, 125, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        RPS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPS.setColumns(20);
        RPS.setRows(5);
        RPS.setName("RPS"); // NOI18N
        RPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPSKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(RPS);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(594, 90, 260, 43);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 220, 1320, 1);

        jLabel29.setText("Nyeri :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(0, 270, 76, 23);

        Nyeri.setFocusTraversalPolicyProvider(true);
        Nyeri.setName("Nyeri"); // NOI18N
        Nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriKeyPressed(evt);
            }
        });
        FormInput.add(Nyeri);
        Nyeri.setBounds(80, 270, 300, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 650, 1320, 1);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("ANAMNESA");
        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 70, 180, 23);

        PanelWall.setBackground(new java.awt.Color(255, 255, 255));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/medis awal THT.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setOpaque(true);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(40, 730, 809, 300);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        KetLokalis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KetLokalis.setColumns(20);
        KetLokalis.setRows(5);
        KetLokalis.setName("KetLokalis"); // NOI18N
        KetLokalis.setPreferredSize(new java.awt.Dimension(182, 92));
        KetLokalis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetLokalisKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(KetLokalis);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(40, 1050, 810, 83);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("III. PEMERIKSAAN THT");
        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(10, 650, 180, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        DiagnosaKerja.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DiagnosaKerja.setColumns(20);
        DiagnosaKerja.setRows(3);
        DiagnosaKerja.setName("DiagnosaKerja"); // NOI18N
        DiagnosaKerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKerjaKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(DiagnosaKerja);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(40, 1750, 400, 130);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(0, 1900, 1320, 1);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(380, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2024 20:20:39" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(436, 40, 130, 23);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(0, 2060, 1320, 1);

        scrollPane15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane15.setName("scrollPane15"); // NOI18N

        DiagnosaBanding.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DiagnosaBanding.setColumns(20);
        DiagnosaBanding.setRows(3);
        DiagnosaBanding.setName("DiagnosaBanding"); // NOI18N
        DiagnosaBanding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaBandingKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(DiagnosaBanding);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(460, 1750, 400, 130);

        jLabel40.setText("Kondisi Umum :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 300, 120, 23);

        scrollPane16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane16.setName("scrollPane16"); // NOI18N

        Terapi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Terapi.setColumns(20);
        Terapi.setRows(3);
        Terapi.setName("Terapi"); // NOI18N
        Terapi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerapiKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Terapi);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(450, 1930, 400, 110);

        scrollPane17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane17.setName("scrollPane17"); // NOI18N

        KelenjerLimpeLeher.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KelenjerLimpeLeher.setColumns(20);
        KelenjerLimpeLeher.setRows(3);
        KelenjerLimpeLeher.setName("KelenjerLimpeLeher"); // NOI18N
        KelenjerLimpeLeher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelenjerLimpeLeherKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(KelenjerLimpeLeher);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(40, 1930, 400, 110);

        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("Terapi/Pengobatan :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(450, 1910, 190, 20);

        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel107.setText("Kelenjar Limpe Leher :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(40, 1910, 320, 20);

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Diagnosa Kerja : ");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(40, 1730, 150, 23);

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("Diagnosa Banding :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(460, 1730, 150, 23);

        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setText("  Liang Telinga :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(50, 1200, 140, 23);

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("Keterangan Status Lokalis:");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(40, 1030, 140, 23);

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("TELINGA");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(50, 1150, 140, 23);

        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("  Daun Telinga :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(50, 1170, 140, 23);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("  Discharge :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(50, 1230, 140, 23);

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("  Membrana Timpani :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(50, 1260, 140, 23);

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("  Tumor :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(50, 1290, 140, 23);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("  Mastoid :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(50, 1320, 140, 23);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("  TES PENDENGARAN");
        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(50, 1350, 140, 23);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("    Berbisik :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(50, 1380, 140, 23);

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("    Weber :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(50, 1410, 140, 23);

        DaunTelingaKanan.setFocusTraversalPolicyProvider(true);
        DaunTelingaKanan.setName("DaunTelingaKanan"); // NOI18N
        DaunTelingaKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DaunTelingaKananKeyPressed(evt);
            }
        });
        FormInput.add(DaunTelingaKanan);
        DaunTelingaKanan.setBounds(200, 1170, 130, 23);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("    Rinne :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(50, 1440, 140, 23);

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("    Scwabach :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(50, 1470, 140, 23);

        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("    BOA :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(50, 1500, 140, 23);

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("    Tympanometri :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(50, 1530, 140, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("    Audiometri :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(50, 1560, 140, 23);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("    Nada Murni :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(50, 1590, 140, 23);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("    BERA :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(50, 1620, 140, 23);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("    OAE :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(50, 1650, 140, 23);

        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel117.setText("    Tes Alat Keseimbangan :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(50, 1680, 140, 23);

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("KANAN");
        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(200, 1150, 130, 23);

        LiangTelingaKanan.setFocusTraversalPolicyProvider(true);
        LiangTelingaKanan.setName("LiangTelingaKanan"); // NOI18N
        LiangTelingaKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LiangTelingaKananKeyPressed(evt);
            }
        });
        FormInput.add(LiangTelingaKanan);
        LiangTelingaKanan.setBounds(200, 1200, 130, 23);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("KIRI");
        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(340, 1150, 130, 23);

        DaunTelingaKiri.setFocusTraversalPolicyProvider(true);
        DaunTelingaKiri.setName("DaunTelingaKiri"); // NOI18N
        DaunTelingaKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DaunTelingaKiriKeyPressed(evt);
            }
        });
        FormInput.add(DaunTelingaKiri);
        DaunTelingaKiri.setBounds(340, 1170, 130, 23);

        LiangTelingaKiri.setFocusTraversalPolicyProvider(true);
        LiangTelingaKiri.setName("LiangTelingaKiri"); // NOI18N
        LiangTelingaKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LiangTelingaKiriKeyPressed(evt);
            }
        });
        FormInput.add(LiangTelingaKiri);
        LiangTelingaKiri.setBounds(340, 1200, 130, 23);

        DischargeKanan.setFocusTraversalPolicyProvider(true);
        DischargeKanan.setName("DischargeKanan"); // NOI18N
        DischargeKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DischargeKananKeyPressed(evt);
            }
        });
        FormInput.add(DischargeKanan);
        DischargeKanan.setBounds(200, 1230, 130, 23);

        DischargeKiri.setFocusTraversalPolicyProvider(true);
        DischargeKiri.setName("DischargeKiri"); // NOI18N
        DischargeKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DischargeKiriKeyPressed(evt);
            }
        });
        FormInput.add(DischargeKiri);
        DischargeKiri.setBounds(340, 1230, 130, 23);

        MembranTimpaniKanan.setFocusTraversalPolicyProvider(true);
        MembranTimpaniKanan.setName("MembranTimpaniKanan"); // NOI18N
        MembranTimpaniKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MembranTimpaniKananKeyPressed(evt);
            }
        });
        FormInput.add(MembranTimpaniKanan);
        MembranTimpaniKanan.setBounds(200, 1260, 130, 23);

        MembranTimpaniKiri.setFocusTraversalPolicyProvider(true);
        MembranTimpaniKiri.setName("MembranTimpaniKiri"); // NOI18N
        MembranTimpaniKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MembranTimpaniKiriKeyPressed(evt);
            }
        });
        FormInput.add(MembranTimpaniKiri);
        MembranTimpaniKiri.setBounds(340, 1260, 130, 23);

        TumorKanan.setFocusTraversalPolicyProvider(true);
        TumorKanan.setName("TumorKanan"); // NOI18N
        TumorKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumorKananKeyPressed(evt);
            }
        });
        FormInput.add(TumorKanan);
        TumorKanan.setBounds(200, 1290, 130, 23);

        TumorKiri.setFocusTraversalPolicyProvider(true);
        TumorKiri.setName("TumorKiri"); // NOI18N
        TumorKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumorKiriKeyPressed(evt);
            }
        });
        FormInput.add(TumorKiri);
        TumorKiri.setBounds(340, 1290, 130, 23);

        MastoidKanan.setFocusTraversalPolicyProvider(true);
        MastoidKanan.setName("MastoidKanan"); // NOI18N
        MastoidKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MastoidKananKeyPressed(evt);
            }
        });
        FormInput.add(MastoidKanan);
        MastoidKanan.setBounds(200, 1320, 130, 23);

        MastoidKiri.setFocusTraversalPolicyProvider(true);
        MastoidKiri.setName("MastoidKiri"); // NOI18N
        MastoidKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MastoidKiriKeyPressed(evt);
            }
        });
        FormInput.add(MastoidKiri);
        MastoidKiri.setBounds(340, 1320, 130, 23);

        BerbisikKanan.setFocusTraversalPolicyProvider(true);
        BerbisikKanan.setName("BerbisikKanan"); // NOI18N
        BerbisikKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BerbisikKananKeyPressed(evt);
            }
        });
        FormInput.add(BerbisikKanan);
        BerbisikKanan.setBounds(200, 1380, 130, 23);

        BerbisikKiri.setFocusTraversalPolicyProvider(true);
        BerbisikKiri.setName("BerbisikKiri"); // NOI18N
        BerbisikKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BerbisikKiriKeyPressed(evt);
            }
        });
        FormInput.add(BerbisikKiri);
        BerbisikKiri.setBounds(340, 1380, 130, 23);

        WeberKanan.setFocusTraversalPolicyProvider(true);
        WeberKanan.setName("WeberKanan"); // NOI18N
        WeberKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WeberKananKeyPressed(evt);
            }
        });
        FormInput.add(WeberKanan);
        WeberKanan.setBounds(200, 1410, 130, 23);

        WeberKiri.setFocusTraversalPolicyProvider(true);
        WeberKiri.setName("WeberKiri"); // NOI18N
        WeberKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WeberKiriKeyPressed(evt);
            }
        });
        FormInput.add(WeberKiri);
        WeberKiri.setBounds(340, 1410, 130, 23);

        RinneKanan.setFocusTraversalPolicyProvider(true);
        RinneKanan.setName("RinneKanan"); // NOI18N
        RinneKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RinneKananKeyPressed(evt);
            }
        });
        FormInput.add(RinneKanan);
        RinneKanan.setBounds(200, 1440, 130, 23);

        RinneKiri.setFocusTraversalPolicyProvider(true);
        RinneKiri.setName("RinneKiri"); // NOI18N
        RinneKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RinneKiriKeyPressed(evt);
            }
        });
        FormInput.add(RinneKiri);
        RinneKiri.setBounds(340, 1440, 130, 23);

        ScwabachKanan.setFocusTraversalPolicyProvider(true);
        ScwabachKanan.setName("ScwabachKanan"); // NOI18N
        ScwabachKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ScwabachKananKeyPressed(evt);
            }
        });
        FormInput.add(ScwabachKanan);
        ScwabachKanan.setBounds(200, 1470, 130, 23);

        ScwabachKiri.setFocusTraversalPolicyProvider(true);
        ScwabachKiri.setName("ScwabachKiri"); // NOI18N
        ScwabachKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ScwabachKiriKeyPressed(evt);
            }
        });
        FormInput.add(ScwabachKiri);
        ScwabachKiri.setBounds(340, 1470, 130, 23);

        BOAKanan.setFocusTraversalPolicyProvider(true);
        BOAKanan.setName("BOAKanan"); // NOI18N
        BOAKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BOAKananKeyPressed(evt);
            }
        });
        FormInput.add(BOAKanan);
        BOAKanan.setBounds(200, 1500, 130, 23);

        BOAKiri.setFocusTraversalPolicyProvider(true);
        BOAKiri.setName("BOAKiri"); // NOI18N
        BOAKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BOAKiriKeyPressed(evt);
            }
        });
        FormInput.add(BOAKiri);
        BOAKiri.setBounds(340, 1500, 130, 23);

        TympanometriKanan.setFocusTraversalPolicyProvider(true);
        TympanometriKanan.setName("TympanometriKanan"); // NOI18N
        TympanometriKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TympanometriKananKeyPressed(evt);
            }
        });
        FormInput.add(TympanometriKanan);
        TympanometriKanan.setBounds(200, 1530, 130, 23);

        TympanometriKiri.setFocusTraversalPolicyProvider(true);
        TympanometriKiri.setName("TympanometriKiri"); // NOI18N
        TympanometriKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TympanometriKiriKeyPressed(evt);
            }
        });
        FormInput.add(TympanometriKiri);
        TympanometriKiri.setBounds(340, 1530, 130, 23);

        AudiometriKanan.setFocusTraversalPolicyProvider(true);
        AudiometriKanan.setName("AudiometriKanan"); // NOI18N
        AudiometriKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AudiometriKananKeyPressed(evt);
            }
        });
        FormInput.add(AudiometriKanan);
        AudiometriKanan.setBounds(200, 1560, 130, 23);

        AudiometriKiri.setFocusTraversalPolicyProvider(true);
        AudiometriKiri.setName("AudiometriKiri"); // NOI18N
        AudiometriKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AudiometriKiriKeyPressed(evt);
            }
        });
        FormInput.add(AudiometriKiri);
        AudiometriKiri.setBounds(340, 1560, 130, 23);

        NadaMurniKanan.setFocusTraversalPolicyProvider(true);
        NadaMurniKanan.setName("NadaMurniKanan"); // NOI18N
        NadaMurniKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadaMurniKananKeyPressed(evt);
            }
        });
        FormInput.add(NadaMurniKanan);
        NadaMurniKanan.setBounds(200, 1590, 130, 23);

        NadaMurniKiri.setFocusTraversalPolicyProvider(true);
        NadaMurniKiri.setName("NadaMurniKiri"); // NOI18N
        NadaMurniKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadaMurniKiriKeyPressed(evt);
            }
        });
        FormInput.add(NadaMurniKiri);
        NadaMurniKiri.setBounds(340, 1590, 130, 23);

        BERAKanan.setFocusTraversalPolicyProvider(true);
        BERAKanan.setName("BERAKanan"); // NOI18N
        BERAKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BERAKananKeyPressed(evt);
            }
        });
        FormInput.add(BERAKanan);
        BERAKanan.setBounds(200, 1620, 130, 23);

        BERAKiri.setFocusTraversalPolicyProvider(true);
        BERAKiri.setName("BERAKiri"); // NOI18N
        BERAKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BERAKiriKeyPressed(evt);
            }
        });
        FormInput.add(BERAKiri);
        BERAKiri.setBounds(340, 1620, 130, 23);

        OAEKanan.setFocusTraversalPolicyProvider(true);
        OAEKanan.setName("OAEKanan"); // NOI18N
        OAEKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OAEKananKeyPressed(evt);
            }
        });
        FormInput.add(OAEKanan);
        OAEKanan.setBounds(200, 1650, 130, 23);

        OAEKiri.setFocusTraversalPolicyProvider(true);
        OAEKiri.setName("OAEKiri"); // NOI18N
        OAEKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OAEKiriKeyPressed(evt);
            }
        });
        FormInput.add(OAEKiri);
        OAEKiri.setBounds(340, 1650, 130, 23);

        TesAlatKeseimbanganKanan.setFocusTraversalPolicyProvider(true);
        TesAlatKeseimbanganKanan.setName("TesAlatKeseimbanganKanan"); // NOI18N
        TesAlatKeseimbanganKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TesAlatKeseimbanganKananKeyPressed(evt);
            }
        });
        FormInput.add(TesAlatKeseimbanganKanan);
        TesAlatKeseimbanganKanan.setBounds(200, 1680, 130, 23);

        TesAlatKeseimbanganKiri.setFocusTraversalPolicyProvider(true);
        TesAlatKeseimbanganKiri.setName("TesAlatKeseimbanganKiri"); // NOI18N
        TesAlatKeseimbanganKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TesAlatKeseimbanganKiriKeyPressed(evt);
            }
        });
        FormInput.add(TesAlatKeseimbanganKiri);
        TesAlatKeseimbanganKiri.setBounds(340, 1680, 130, 23);

        jSeparator19.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator19.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator19.setName("jSeparator19"); // NOI18N
        FormInput.add(jSeparator19);
        jSeparator19.setBounds(0, 1140, 1320, 1);

        jSeparator20.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator20.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator20.setName("jSeparator20"); // NOI18N
        FormInput.add(jSeparator20);
        jSeparator20.setBounds(0, 1720, 1320, 1);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("STATUS LOKALIS");
        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(30, 680, 180, 23);

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("TELINGA");
        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(50, 400, 140, 23);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("KANAN");
        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(200, 400, 130, 23);

        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("KIRI");
        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(340, 400, 130, 23);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("  Sekret :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(50, 430, 140, 23);

        SekretKanan.setFocusTraversalPolicyProvider(true);
        SekretKanan.setName("SekretKanan"); // NOI18N
        SekretKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SekretKananKeyPressed(evt);
            }
        });
        FormInput.add(SekretKanan);
        SekretKanan.setBounds(200, 430, 130, 23);

        SekretKiri.setFocusTraversalPolicyProvider(true);
        SekretKiri.setName("SekretKiri"); // NOI18N
        SekretKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SekretKiriKeyPressed(evt);
            }
        });
        FormInput.add(SekretKiri);
        SekretKiri.setBounds(340, 430, 130, 23);

        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel108.setText("  Tuli :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(50, 460, 140, 23);

        TuliKanan.setFocusTraversalPolicyProvider(true);
        TuliKanan.setName("TuliKanan"); // NOI18N
        TuliKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TuliKananKeyPressed(evt);
            }
        });
        FormInput.add(TuliKanan);
        TuliKanan.setBounds(200, 460, 130, 23);

        TuliKiri.setFocusTraversalPolicyProvider(true);
        TuliKiri.setName("TuliKiri"); // NOI18N
        TuliKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TuliKiriKeyPressed(evt);
            }
        });
        FormInput.add(TuliKiri);
        TuliKiri.setBounds(340, 460, 130, 23);

        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel120.setText("  Tumor :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(50, 490, 140, 23);

        TumorrKanan.setFocusTraversalPolicyProvider(true);
        TumorrKanan.setName("TumorrKanan"); // NOI18N
        TumorrKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumorrKananKeyPressed(evt);
            }
        });
        FormInput.add(TumorrKanan);
        TumorrKanan.setBounds(200, 490, 130, 23);

        TumorrKiri.setFocusTraversalPolicyProvider(true);
        TumorrKiri.setName("TumorrKiri"); // NOI18N
        TumorrKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumorrKiriKeyPressed(evt);
            }
        });
        FormInput.add(TumorrKiri);
        TumorrKiri.setBounds(340, 490, 130, 23);

        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel121.setText("  Tinitus :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(50, 520, 140, 23);

        TinitusKanan.setFocusTraversalPolicyProvider(true);
        TinitusKanan.setName("TinitusKanan"); // NOI18N
        TinitusKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinitusKananKeyPressed(evt);
            }
        });
        FormInput.add(TinitusKanan);
        TinitusKanan.setBounds(200, 520, 130, 23);

        TinitusKiri.setFocusTraversalPolicyProvider(true);
        TinitusKiri.setName("TinitusKiri"); // NOI18N
        TinitusKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinitusKiriKeyPressed(evt);
            }
        });
        FormInput.add(TinitusKiri);
        TinitusKiri.setBounds(340, 520, 130, 23);

        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("  Sakit :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(50, 550, 140, 23);

        SakitKanan.setFocusTraversalPolicyProvider(true);
        SakitKanan.setName("SakitKanan"); // NOI18N
        SakitKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SakitKananKeyPressed(evt);
            }
        });
        FormInput.add(SakitKanan);
        SakitKanan.setBounds(200, 550, 130, 23);

        SakitKiri.setFocusTraversalPolicyProvider(true);
        SakitKiri.setName("SakitKiri"); // NOI18N
        SakitKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SakitKiriKeyPressed(evt);
            }
        });
        FormInput.add(SakitKiri);
        SakitKiri.setBounds(340, 550, 130, 23);

        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel123.setText("  Korpus Alienum :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(50, 580, 140, 23);

        KorpusAlienumKanan.setFocusTraversalPolicyProvider(true);
        KorpusAlienumKanan.setName("KorpusAlienumKanan"); // NOI18N
        KorpusAlienumKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KorpusAlienumKananKeyPressed(evt);
            }
        });
        FormInput.add(KorpusAlienumKanan);
        KorpusAlienumKanan.setBounds(200, 580, 130, 23);

        KorpusAlienumKiri.setFocusTraversalPolicyProvider(true);
        KorpusAlienumKiri.setName("KorpusAlienumKiri"); // NOI18N
        KorpusAlienumKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KorpusAlienumKiriKeyPressed(evt);
            }
        });
        FormInput.add(KorpusAlienumKiri);
        KorpusAlienumKiri.setBounds(340, 580, 130, 23);

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("  Vertigo :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(50, 610, 140, 23);

        VertigoKanan.setFocusTraversalPolicyProvider(true);
        VertigoKanan.setName("VertigoKanan"); // NOI18N
        VertigoKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VertigoKananKeyPressed(evt);
            }
        });
        FormInput.add(VertigoKanan);
        VertigoKanan.setBounds(200, 610, 130, 23);

        VertigoKiri.setFocusTraversalPolicyProvider(true);
        VertigoKiri.setName("VertigoKiri"); // NOI18N
        VertigoKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VertigoKiriKeyPressed(evt);
            }
        });
        FormInput.add(VertigoKiri);
        VertigoKiri.setBounds(340, 610, 130, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(490, 400, 1, 240);

        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel125.setText("HIDUNG");
        jLabel125.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(510, 400, 140, 23);

        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("KANAN");
        jLabel126.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(660, 400, 130, 23);

        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel127.setText("KIRI");
        jLabel127.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(800, 400, 130, 23);

        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel128.setText("  Sekret :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(510, 430, 140, 23);

        SekretHidungKanan.setFocusTraversalPolicyProvider(true);
        SekretHidungKanan.setName("SekretHidungKanan"); // NOI18N
        SekretHidungKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SekretHidungKananKeyPressed(evt);
            }
        });
        FormInput.add(SekretHidungKanan);
        SekretHidungKanan.setBounds(660, 430, 130, 23);

        SekretHidungKiri.setFocusTraversalPolicyProvider(true);
        SekretHidungKiri.setName("SekretHidungKiri"); // NOI18N
        SekretHidungKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SekretHidungKiriKeyPressed(evt);
            }
        });
        FormInput.add(SekretHidungKiri);
        SekretHidungKiri.setBounds(800, 430, 130, 23);

        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel129.setText("  Tersumbat :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(510, 460, 140, 23);

        TersumbatKanan.setFocusTraversalPolicyProvider(true);
        TersumbatKanan.setName("TersumbatKanan"); // NOI18N
        TersumbatKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TersumbatKananKeyPressed(evt);
            }
        });
        FormInput.add(TersumbatKanan);
        TersumbatKanan.setBounds(660, 460, 130, 23);

        TersumbatKiri.setFocusTraversalPolicyProvider(true);
        TersumbatKiri.setName("TersumbatKiri"); // NOI18N
        TersumbatKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TersumbatKiriKeyPressed(evt);
            }
        });
        FormInput.add(TersumbatKiri);
        TersumbatKiri.setBounds(800, 460, 130, 23);

        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("  Tumor :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(510, 490, 140, 23);

        TumorHidungKanan.setFocusTraversalPolicyProvider(true);
        TumorHidungKanan.setName("TumorHidungKanan"); // NOI18N
        TumorHidungKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumorHidungKananKeyPressed(evt);
            }
        });
        FormInput.add(TumorHidungKanan);
        TumorHidungKanan.setBounds(660, 490, 130, 23);

        TumorHidungKiri.setFocusTraversalPolicyProvider(true);
        TumorHidungKiri.setName("TumorHidungKiri"); // NOI18N
        TumorHidungKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumorHidungKiriKeyPressed(evt);
            }
        });
        FormInput.add(TumorHidungKiri);
        TumorHidungKiri.setBounds(800, 490, 130, 23);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("  Pilek :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(510, 520, 140, 23);

        PilekKanan.setFocusTraversalPolicyProvider(true);
        PilekKanan.setName("PilekKanan"); // NOI18N
        PilekKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PilekKananKeyPressed(evt);
            }
        });
        FormInput.add(PilekKanan);
        PilekKanan.setBounds(660, 520, 130, 23);

        PilekKiri.setFocusTraversalPolicyProvider(true);
        PilekKiri.setName("PilekKiri"); // NOI18N
        PilekKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PilekKiriKeyPressed(evt);
            }
        });
        FormInput.add(PilekKiri);
        PilekKiri.setBounds(800, 520, 130, 23);

        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel132.setText("  Sakit :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(510, 550, 140, 23);

        SakitHidungKanan.setFocusTraversalPolicyProvider(true);
        SakitHidungKanan.setName("SakitHidungKanan"); // NOI18N
        SakitHidungKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SakitHidungKananKeyPressed(evt);
            }
        });
        FormInput.add(SakitHidungKanan);
        SakitHidungKanan.setBounds(660, 550, 130, 23);

        SakitHidungKiri.setFocusTraversalPolicyProvider(true);
        SakitHidungKiri.setName("SakitHidungKiri"); // NOI18N
        SakitHidungKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SakitHidungKiriKeyPressed(evt);
            }
        });
        FormInput.add(SakitHidungKiri);
        SakitHidungKiri.setBounds(800, 550, 130, 23);

        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel133.setText("  Korpus Alienum :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(510, 580, 140, 23);

        KorpusAlienumHidungKanan.setFocusTraversalPolicyProvider(true);
        KorpusAlienumHidungKanan.setName("KorpusAlienumHidungKanan"); // NOI18N
        KorpusAlienumHidungKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KorpusAlienumHidungKananKeyPressed(evt);
            }
        });
        FormInput.add(KorpusAlienumHidungKanan);
        KorpusAlienumHidungKanan.setBounds(660, 580, 130, 23);

        KorpusAlienumHidungKiri.setFocusTraversalPolicyProvider(true);
        KorpusAlienumHidungKiri.setName("KorpusAlienumHidungKiri"); // NOI18N
        KorpusAlienumHidungKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KorpusAlienumHidungKiriKeyPressed(evt);
            }
        });
        FormInput.add(KorpusAlienumHidungKiri);
        KorpusAlienumHidungKiri.setBounds(800, 580, 130, 23);

        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("  Bersin :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(510, 610, 140, 23);

        BersinKanan.setFocusTraversalPolicyProvider(true);
        BersinKanan.setName("BersinKanan"); // NOI18N
        BersinKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BersinKananKeyPressed(evt);
            }
        });
        FormInput.add(BersinKanan);
        BersinKanan.setBounds(660, 610, 130, 23);

        BersinKiri.setFocusTraversalPolicyProvider(true);
        BersinKiri.setName("BersinKiri"); // NOI18N
        BersinKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BersinKiriKeyPressed(evt);
            }
        });
        FormInput.add(BersinKiri);
        BersinKiri.setBounds(800, 610, 130, 23);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(943, 400, 1, 240);

        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel135.setText("TENGGOROK");
        jLabel135.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(970, 400, 350, 23);

        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("  Riak :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(970, 430, 140, 23);

        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel137.setText("  Gangguan :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(970, 460, 140, 23);

        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel138.setText("  Suara :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(970, 490, 140, 23);

        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel139.setText("  Tumor :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(970, 520, 140, 23);

        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel140.setText("  Batuk :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(970, 550, 140, 23);

        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel141.setText("  Korpus Alienum :");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(970, 580, 140, 23);

        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel142.setText("  Sesak Nafas :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(970, 610, 140, 23);

        TenggorokRiak.setFocusTraversalPolicyProvider(true);
        TenggorokRiak.setName("TenggorokRiak"); // NOI18N
        TenggorokRiak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokRiakKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokRiak);
        TenggorokRiak.setBounds(1120, 430, 200, 23);

        TenggorokGangguan.setFocusTraversalPolicyProvider(true);
        TenggorokGangguan.setName("TenggorokGangguan"); // NOI18N
        TenggorokGangguan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokGangguanKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokGangguan);
        TenggorokGangguan.setBounds(1120, 460, 200, 23);

        TenggorokSuara.setFocusTraversalPolicyProvider(true);
        TenggorokSuara.setName("TenggorokSuara"); // NOI18N
        TenggorokSuara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokSuaraKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokSuara);
        TenggorokSuara.setBounds(1120, 490, 200, 23);

        TenggorokTumor.setFocusTraversalPolicyProvider(true);
        TenggorokTumor.setName("TenggorokTumor"); // NOI18N
        TenggorokTumor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokTumorKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokTumor);
        TenggorokTumor.setBounds(1120, 520, 200, 23);

        TenggorokBatuk.setFocusTraversalPolicyProvider(true);
        TenggorokBatuk.setName("TenggorokBatuk"); // NOI18N
        TenggorokBatuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokBatukKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokBatuk);
        TenggorokBatuk.setBounds(1120, 550, 200, 23);

        TenggorokKorpusAlienum.setFocusTraversalPolicyProvider(true);
        TenggorokKorpusAlienum.setName("TenggorokKorpusAlienum"); // NOI18N
        TenggorokKorpusAlienum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokKorpusAlienumKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokKorpusAlienum);
        TenggorokKorpusAlienum.setBounds(1120, 580, 200, 23);

        TenggorokSesakNafas.setFocusTraversalPolicyProvider(true);
        TenggorokSesakNafas.setName("TenggorokSesakNafas"); // NOI18N
        TenggorokSesakNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokSesakNafasKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokSesakNafas);
        TenggorokSesakNafas.setBounds(1120, 610, 200, 23);

        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel143.setText("HIDUNG");
        jLabel143.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(520, 1150, 140, 23);

        jSeparator18.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator18.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator18.setName("jSeparator18"); // NOI18N
        FormInput.add(jSeparator18);
        jSeparator18.setBounds(490, 1150, 1, 550);

        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel144.setText("  Hidung Luar :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(520, 1170, 140, 23);

        HidungLuarKanan.setFocusTraversalPolicyProvider(true);
        HidungLuarKanan.setName("HidungLuarKanan"); // NOI18N
        HidungLuarKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HidungLuarKananKeyPressed(evt);
            }
        });
        FormInput.add(HidungLuarKanan);
        HidungLuarKanan.setBounds(670, 1170, 130, 23);

        jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel145.setText("KANAN");
        jLabel145.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(670, 1150, 130, 23);

        jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel146.setText("KIRI");
        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(810, 1150, 130, 23);

        HidungLuarKiri.setFocusTraversalPolicyProvider(true);
        HidungLuarKiri.setName("HidungLuarKiri"); // NOI18N
        HidungLuarKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HidungLuarKiriKeyPressed(evt);
            }
        });
        FormInput.add(HidungLuarKiri);
        HidungLuarKiri.setBounds(810, 1170, 130, 23);

        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel147.setText("  Kavum Nasi :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(520, 1200, 140, 23);

        KavumNasiKanan.setFocusTraversalPolicyProvider(true);
        KavumNasiKanan.setName("KavumNasiKanan"); // NOI18N
        KavumNasiKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KavumNasiKananKeyPressed(evt);
            }
        });
        FormInput.add(KavumNasiKanan);
        KavumNasiKanan.setBounds(670, 1200, 130, 23);

        KavumNasiKiri.setFocusTraversalPolicyProvider(true);
        KavumNasiKiri.setName("KavumNasiKiri"); // NOI18N
        KavumNasiKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KavumNasiKiriKeyPressed(evt);
            }
        });
        FormInput.add(KavumNasiKiri);
        KavumNasiKiri.setBounds(810, 1200, 130, 23);

        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel148.setText("  Septum :");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(520, 1230, 140, 23);

        SeptumHidungKanan.setFocusTraversalPolicyProvider(true);
        SeptumHidungKanan.setName("SeptumHidungKanan"); // NOI18N
        SeptumHidungKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SeptumHidungKananKeyPressed(evt);
            }
        });
        FormInput.add(SeptumHidungKanan);
        SeptumHidungKanan.setBounds(670, 1230, 130, 23);

        SeptumHidungKiri.setFocusTraversalPolicyProvider(true);
        SeptumHidungKiri.setName("SeptumHidungKiri"); // NOI18N
        SeptumHidungKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SeptumHidungKiriKeyPressed(evt);
            }
        });
        FormInput.add(SeptumHidungKiri);
        SeptumHidungKiri.setBounds(810, 1230, 130, 23);

        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel149.setText("  Discharge :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(520, 1260, 140, 23);

        DischargeHidungKanan.setFocusTraversalPolicyProvider(true);
        DischargeHidungKanan.setName("DischargeHidungKanan"); // NOI18N
        DischargeHidungKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DischargeHidungKananKeyPressed(evt);
            }
        });
        FormInput.add(DischargeHidungKanan);
        DischargeHidungKanan.setBounds(670, 1260, 130, 23);

        DischargeHidungKiri.setFocusTraversalPolicyProvider(true);
        DischargeHidungKiri.setName("DischargeHidungKiri"); // NOI18N
        DischargeHidungKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DischargeHidungKiriKeyPressed(evt);
            }
        });
        FormInput.add(DischargeHidungKiri);
        DischargeHidungKiri.setBounds(810, 1260, 130, 23);

        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel150.setText("  Mukosa :");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(520, 1290, 140, 23);

        MukosaHidungKanan.setFocusTraversalPolicyProvider(true);
        MukosaHidungKanan.setName("MukosaHidungKanan"); // NOI18N
        MukosaHidungKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MukosaHidungKananKeyPressed(evt);
            }
        });
        FormInput.add(MukosaHidungKanan);
        MukosaHidungKanan.setBounds(670, 1290, 130, 23);

        MukosaHidungKiri.setFocusTraversalPolicyProvider(true);
        MukosaHidungKiri.setName("MukosaHidungKiri"); // NOI18N
        MukosaHidungKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MukosaHidungKiriKeyPressed(evt);
            }
        });
        FormInput.add(MukosaHidungKiri);
        MukosaHidungKiri.setBounds(810, 1290, 130, 23);

        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel151.setText("  Tumor :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(520, 1320, 140, 23);

        TumorHidungKanann.setFocusTraversalPolicyProvider(true);
        TumorHidungKanann.setName("TumorHidungKanann"); // NOI18N
        TumorHidungKanann.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumorHidungKanannKeyPressed(evt);
            }
        });
        FormInput.add(TumorHidungKanann);
        TumorHidungKanann.setBounds(670, 1320, 130, 23);

        TumorHidungKirii.setFocusTraversalPolicyProvider(true);
        TumorHidungKirii.setName("TumorHidungKirii"); // NOI18N
        TumorHidungKirii.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumorHidungKiriiKeyPressed(evt);
            }
        });
        FormInput.add(TumorHidungKirii);
        TumorHidungKirii.setBounds(810, 1320, 130, 23);

        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel152.setText("  Konka :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(520, 1350, 140, 23);

        KonkaHidungKanan.setFocusTraversalPolicyProvider(true);
        KonkaHidungKanan.setName("KonkaHidungKanan"); // NOI18N
        KonkaHidungKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KonkaHidungKananKeyPressed(evt);
            }
        });
        FormInput.add(KonkaHidungKanan);
        KonkaHidungKanan.setBounds(670, 1350, 130, 23);

        KonkaHidungKiri.setFocusTraversalPolicyProvider(true);
        KonkaHidungKiri.setName("KonkaHidungKiri"); // NOI18N
        KonkaHidungKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KonkaHidungKiriKeyPressed(evt);
            }
        });
        FormInput.add(KonkaHidungKiri);
        KonkaHidungKiri.setBounds(810, 1350, 130, 23);

        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel153.setText("  Naso Endoskopi :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(520, 1380, 140, 23);

        NasoEndoskopiKanan.setFocusTraversalPolicyProvider(true);
        NasoEndoskopiKanan.setName("NasoEndoskopiKanan"); // NOI18N
        NasoEndoskopiKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NasoEndoskopiKananKeyPressed(evt);
            }
        });
        FormInput.add(NasoEndoskopiKanan);
        NasoEndoskopiKanan.setBounds(670, 1380, 130, 23);

        NasoEndoskopiKiri.setFocusTraversalPolicyProvider(true);
        NasoEndoskopiKiri.setName("NasoEndoskopiKiri"); // NOI18N
        NasoEndoskopiKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NasoEndoskopiKiriKeyPressed(evt);
            }
        });
        FormInput.add(NasoEndoskopiKiri);
        NasoEndoskopiKiri.setBounds(810, 1380, 130, 23);

        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel154.setText("TENGGOROK");
        jLabel154.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(520, 1410, 430, 23);

        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel155.setText("    Dispenu :");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(520, 1440, 80, 23);

        TenggorokDispenu.setFocusTraversalPolicyProvider(true);
        TenggorokDispenu.setName("TenggorokDispenu"); // NOI18N
        TenggorokDispenu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokDispenuKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokDispenu);
        TenggorokDispenu.setBounds(610, 1440, 130, 23);

        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel156.setText("    Sianosis :");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(520, 1470, 80, 23);

        TenggorokSianosis.setFocusTraversalPolicyProvider(true);
        TenggorokSianosis.setName("TenggorokSianosis"); // NOI18N
        TenggorokSianosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokSianosisKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokSianosis);
        TenggorokSianosis.setBounds(610, 1470, 130, 23);

        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel157.setText("    Mucosa :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(520, 1500, 80, 23);

        TenggorokMucosa.setFocusTraversalPolicyProvider(true);
        TenggorokMucosa.setName("TenggorokMucosa"); // NOI18N
        TenggorokMucosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokMucosaKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokMucosa);
        TenggorokMucosa.setBounds(610, 1500, 130, 23);

        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel158.setText("    Dinding Belakang :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(520, 1530, 110, 23);

        TenggorokDindingBelakang.setFocusTraversalPolicyProvider(true);
        TenggorokDindingBelakang.setName("TenggorokDindingBelakang"); // NOI18N
        TenggorokDindingBelakang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokDindingBelakangKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokDindingBelakang);
        TenggorokDindingBelakang.setBounds(630, 1530, 310, 23);

        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel159.setText("    Stridor :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(750, 1440, 60, 23);

        TenggorokStridor.setFocusTraversalPolicyProvider(true);
        TenggorokStridor.setName("TenggorokStridor"); // NOI18N
        TenggorokStridor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokStridorKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokStridor);
        TenggorokStridor.setBounds(810, 1440, 130, 23);

        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel160.setText("    Suara :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(750, 1470, 60, 23);

        TenggorokSuaraa.setFocusTraversalPolicyProvider(true);
        TenggorokSuaraa.setName("TenggorokSuaraa"); // NOI18N
        TenggorokSuaraa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokSuaraaKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokSuaraa);
        TenggorokSuaraa.setBounds(810, 1470, 130, 23);

        jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel161.setText("    Tonsil :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(750, 1500, 60, 23);

        TenggorokTonsil.setFocusTraversalPolicyProvider(true);
        TenggorokTonsil.setName("TenggorokTonsil"); // NOI18N
        TenggorokTonsil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokTonsilKeyPressed(evt);
            }
        });
        FormInput.add(TenggorokTonsil);
        TenggorokTonsil.setBounds(810, 1500, 130, 23);

        jLabel162.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel162.setText("LARING");
        jLabel162.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(520, 1560, 450, 23);

        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel163.setText("    Epiglotis :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(520, 1590, 80, 23);

        LaringEpiglotis.setFocusTraversalPolicyProvider(true);
        LaringEpiglotis.setName("LaringEpiglotis"); // NOI18N
        LaringEpiglotis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaringEpiglotisKeyPressed(evt);
            }
        });
        FormInput.add(LaringEpiglotis);
        LaringEpiglotis.setBounds(610, 1590, 130, 23);

        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel164.setText("    Aritenoid :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(520, 1620, 80, 23);

        LaringArienoid.setFocusTraversalPolicyProvider(true);
        LaringArienoid.setName("LaringArienoid"); // NOI18N
        LaringArienoid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaringArienoidKeyPressed(evt);
            }
        });
        FormInput.add(LaringArienoid);
        LaringArienoid.setBounds(610, 1620, 130, 23);

        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel165.setText("    Plika Vokalis :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(750, 1590, 80, 23);

        LaringPlikaVokalis.setFocusTraversalPolicyProvider(true);
        LaringPlikaVokalis.setName("LaringPlikaVokalis"); // NOI18N
        LaringPlikaVokalis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaringPlikaVokalisKeyPressed(evt);
            }
        });
        FormInput.add(LaringPlikaVokalis);
        LaringPlikaVokalis.setBounds(830, 1590, 130, 23);

        jLabel166.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel166.setText("    Rimaglotis :");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(750, 1620, 80, 23);

        LaringRimaglotis.setFocusTraversalPolicyProvider(true);
        LaringRimaglotis.setName("LaringRimaglotis"); // NOI18N
        LaringRimaglotis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaringRimaglotisKeyPressed(evt);
            }
        });
        FormInput.add(LaringRimaglotis);
        LaringRimaglotis.setBounds(830, 1620, 130, 23);

        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel167.setText("    Plika Ventrikuloris :");
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(520, 1650, 110, 23);

        LaringPlikaVentrikuloris.setFocusTraversalPolicyProvider(true);
        LaringPlikaVentrikuloris.setName("LaringPlikaVentrikuloris"); // NOI18N
        LaringPlikaVentrikuloris.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaringPlikaVentrikulorisKeyPressed(evt);
            }
        });
        FormInput.add(LaringPlikaVentrikuloris);
        LaringPlikaVentrikuloris.setBounds(630, 1650, 330, 23);

        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel168.setText("    Endoskopi :");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(520, 1680, 80, 23);

        LaringEndoskopi.setFocusTraversalPolicyProvider(true);
        LaringEndoskopi.setName("LaringEndoskopi"); // NOI18N
        LaringEndoskopi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaringEndoskopiKeyPressed(evt);
            }
        });
        FormInput.add(LaringEndoskopi);
        LaringEndoskopi.setBounds(600, 1680, 360, 23);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("Boleh Pulang :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(40, 2100, 80, 20);

        jLabel169.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel169.setText("DISPOSISI");
        jLabel169.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(40, 2070, 320, 20);

        BolehPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        BolehPulang.setName("BolehPulang"); // NOI18N
        BolehPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BolehPulangKeyPressed(evt);
            }
        });
        FormInput.add(BolehPulang);
        BolehPulang.setBounds(130, 2100, 80, 23);

        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel170.setText("Jam Keluar :");
        jLabel170.setName("jLabel170"); // NOI18N
        FormInput.add(jLabel170);
        jLabel170.setBounds(450, 2100, 70, 20);

        JamPulang.setFocusTraversalPolicyProvider(true);
        JamPulang.setName("JamPulang"); // NOI18N
        JamPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamPulangKeyPressed(evt);
            }
        });
        FormInput.add(JamPulang);
        JamPulang.setBounds(520, 2100, 100, 23);

        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel171.setText("Tanggal Keluar :");
        jLabel171.setName("jLabel171"); // NOI18N
        FormInput.add(jLabel171);
        jLabel171.setBounds(230, 2100, 90, 20);

        DTPTanggalKeluar.setForeground(new java.awt.Color(50, 70, 50));
        DTPTanggalKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2024" }));
        DTPTanggalKeluar.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalKeluar.setName("DTPTanggalKeluar"); // NOI18N
        DTPTanggalKeluar.setOpaque(false);
        DTPTanggalKeluar.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(DTPTanggalKeluar);
        DTPTanggalKeluar.setBounds(320, 2100, 110, 23);

        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel172.setText("Kontrol Poliklinik :");
        jLabel172.setName("jLabel172"); // NOI18N
        FormInput.add(jLabel172);
        jLabel172.setBounds(40, 2140, 90, 20);

        KontrolPoliklinik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        KontrolPoliklinik.setName("KontrolPoliklinik"); // NOI18N
        KontrolPoliklinik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KontrolPoliklinikKeyPressed(evt);
            }
        });
        FormInput.add(KontrolPoliklinik);
        KontrolPoliklinik.setBounds(130, 2140, 80, 23);

        jLabel173.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel173.setText("Tanggal Kontrol :");
        jLabel173.setName("jLabel173"); // NOI18N
        FormInput.add(jLabel173);
        jLabel173.setBounds(230, 2140, 90, 20);

        DTPTanggalKontrol.setForeground(new java.awt.Color(50, 70, 50));
        DTPTanggalKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2024" }));
        DTPTanggalKontrol.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalKontrol.setName("DTPTanggalKontrol"); // NOI18N
        DTPTanggalKontrol.setOpaque(false);
        DTPTanggalKontrol.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(DTPTanggalKontrol);
        DTPTanggalKontrol.setBounds(320, 2140, 110, 23);

        jLabel174.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel174.setText("Jam Kontrol :");
        jLabel174.setName("jLabel174"); // NOI18N
        FormInput.add(jLabel174);
        jLabel174.setBounds(450, 2140, 70, 20);

        JamKontrol.setFocusTraversalPolicyProvider(true);
        JamKontrol.setName("JamKontrol"); // NOI18N
        JamKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKontrolKeyPressed(evt);
            }
        });
        FormInput.add(JamKontrol);
        JamKontrol.setBounds(520, 2140, 100, 23);

        jLabel175.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel175.setText("Dirawat di ruang :");
        jLabel175.setName("jLabel175"); // NOI18N
        FormInput.add(jLabel175);
        jLabel175.setBounds(40, 2180, 90, 20);

        DirawatDiRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Intensif", "Ruang Lain" }));
        DirawatDiRuang.setName("DirawatDiRuang"); // NOI18N
        DirawatDiRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DirawatDiRuangKeyPressed(evt);
            }
        });
        FormInput.add(DirawatDiRuang);
        DirawatDiRuang.setBounds(130, 2180, 80, 23);

        RuangLain.setFocusTraversalPolicyProvider(true);
        RuangLain.setName("RuangLain"); // NOI18N
        RuangLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RuangLainKeyPressed(evt);
            }
        });
        FormInput.add(RuangLain);
        RuangLain.setBounds(220, 2180, 210, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Data Pengkajian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Lihat Data Pengkajian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(KeluhanUtama.getText().trim().equals("")){
            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
        }else if(RPS.getText().trim().equals("")){
            Valid.textKosong(RPS,"Riwayat Penyakit Sekarang");
        }else if(RPD.getText().trim().equals("")){
            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
        }else if(RPO.getText().trim().equals("")){
            Valid.textKosong(RPO,"Riwayat Pengunaan obat");
        }else{
            if(Sequel.menyimpantf("pengkajian_medis_ranap_tht","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",130,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),
                    KeluhanUtama.getText(),RPS.getText(),RPD.getText(),RPO.getText(),Alergi.getText(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),BB.getText(),TB.getText(),Nyeri.getText(),StatusNutrisi.getText(),
                    Kondisi.getText(),SekretKanan.getText(),SekretKiri.getText(),TuliKanan.getText(),TuliKiri.getText(),TumorrKanan.getText(),TumorrKiri.getText(),TinitusKanan.getText(),TinitusKiri.getText(),SakitKanan.getText(),
                    SakitKiri.getText(),KorpusAlienumKanan.getText(),KorpusAlienumKiri.getText(),VertigoKanan.getText(),VertigoKiri.getText(),SekretHidungKanan.getText(),SekretHidungKiri.getText(),TersumbatKanan.getText(),
                    TersumbatKiri.getText(),TumorHidungKanan.getText(),TumorHidungKiri.getText(),PilekKanan.getText(),PilekKiri.getText(),SakitHidungKanan.getText(),SakitHidungKiri.getText(),KorpusAlienumHidungKanan.getText(),
                    KorpusAlienumHidungKiri.getText(),BersinKanan.getText(),BersinKiri.getText(),TenggorokRiak.getText(),TenggorokGangguan.getText(),TenggorokSuara.getText(),TenggorokTumor.getText(),TenggorokBatuk.getText(),
                    TenggorokKorpusAlienum.getText(),TenggorokSesakNafas.getText(),KetLokalis.getText(),DaunTelingaKanan.getText(),DaunTelingaKiri.getText(),LiangTelingaKanan.getText(),LiangTelingaKiri.getText(),DischargeKanan.getText(),
                    DischargeKiri.getText(),MembranTimpaniKanan.getText(),MembranTimpaniKiri.getText(),TumorKanan.getText(),TumorKiri.getText(),MastoidKanan.getText(),MastoidKiri.getText(),BerbisikKanan.getText(),BerbisikKiri.getText(),
                    WeberKanan.getText(),WeberKiri.getText(),RinneKanan.getText(),RinneKiri.getText(),ScwabachKanan.getText(),ScwabachKiri.getText(),BOAKanan.getText(),BOAKiri.getText(),TympanometriKanan.getText(),TympanometriKiri.getText(),AudiometriKanan.getText(),
                    AudiometriKiri.getText(),NadaMurniKanan.getText(),NadaMurniKiri.getText(),BERAKanan.getText(),BERAKiri.getText(),OAEKanan.getText(),OAEKiri.getText(),TesAlatKeseimbanganKanan.getText(),TesAlatKeseimbanganKiri.getText(),
                    HidungLuarKanan.getText(),HidungLuarKiri.getText(),KavumNasiKanan.getText(),KavumNasiKiri.getText(),SeptumHidungKanan.getText(),SeptumHidungKiri.getText(),DischargeHidungKanan.getText(),DischargeHidungKiri.getText(),
                    MukosaHidungKanan.getText(),MukosaHidungKiri.getText(),TumorHidungKanan.getText(),TumorHidungKiri.getText(),KonkaHidungKanan.getText(),KonkaHidungKiri.getText(),NasoEndoskopiKanan.getText(),NasoEndoskopiKiri.getText(),
                    TenggorokDispenu.getText(),TenggorokStridor.getText(),TenggorokSianosis.getText(),TenggorokSuara.getText(),TenggorokMucosa.getText(),TenggorokTonsil.getText(),TenggorokDindingBelakang.getText(),LaringEpiglotis.getText(),
                    LaringPlikaVokalis.getText(),LaringArienoid.getText(),LaringRimaglotis.getText(),LaringPlikaVentrikuloris.getText(),LaringEndoskopi.getText(),DiagnosaKerja.getText(),DiagnosaBanding.getText(),KelenjerLimpeLeher.getText(),
                    Terapi.getText(),BolehPulang.getSelectedItem().toString(),Valid.SetTgl(DTPTanggalKeluar.getSelectedItem()+""),JamPulang.getText(),KontrolPoliklinik.getSelectedItem().toString(),Valid.SetTgl(DTPTanggalKontrol.getSelectedItem()+""),
                    JamKontrol.getText(),DirawatDiRuang.getSelectedItem().toString(),RuangLain.getText()
                })==true){
                    emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Kondisi,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }              
            
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(KeluhanUtama.getText().trim().equals("")){
            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
        }else if(RPS.getText().trim().equals("")){
            Valid.textKosong(RPS,"Riwayat Penyakit Sekarang");
        }else if(RPD.getText().trim().equals("")){
            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
        }else if(RPO.getText().trim().equals("")){
            Valid.textKosong(RPO,"Riwayat Pengunaan obat");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            try {
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>No.RM</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>J.K.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'>Kode Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Nama Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'>Anamnesis</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Hubungan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'>Keluhan Utama</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Penyakit Sekarang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Penyakit Dahulu</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Penggunaan Obat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>Alergi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>TD</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nadi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>RR</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>Suhu</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>BB</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>TB</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>Nyeri</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>Status Nutrisi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Kondisi Umum</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Keterangan Lokalis</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'>Laboratorium</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'>Radiolgi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'>Tes Pendengaran</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'>Penunjang Lainnya</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Diagnosis Kerja</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Diagnosis Banding</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Permasalahan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Terapi/Pengobatan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Tindakan/Rencana Pengobatan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Tatalaksana Lainnya</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Edukasi</b></td>"+
                    "</tr>"
                );
                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                        "<tr class='isi'>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,0).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,1).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,2).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,3).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,4).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,5).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,6).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,7).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,8).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,9).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,10).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,11).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,12).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,13).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,14).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,15).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,16).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,17).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,18).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,19).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,20).toString()+"</td>"+ 
                            "<td valign='top'>"+tbObat.getValueAt(i,21).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,22).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,23).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,24).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,25).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,26).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,27).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,28).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,29).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,30).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,31).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,32).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,33).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,34).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,35).toString()+"</td>"+
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='4600px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>"
                );

                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                    ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                    ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                    ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                    ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                    ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                    ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                );
                bg.close();

                File f = new File("DataPenilaianAwalMedisRalan.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='4650px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA PENILAIAN AWAL MEDIS RAWAT JALAN<br><br></font>"+        
                                    "</td>"+
                               "</tr>"+
                            "</table>")
                );
                bw.close();                         
                Desktop.getDesktop().browse(f.toURI());
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } 
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbObat.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void MnPenilaianMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianMedisActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            try {
                param.put("lokalis",getClass().getResource("/picture/medis awal THT.png").openStream());
            } catch (Exception e) {
            } 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString())); 
            
            Valid.MyReportqry("rptCetakPenilaianAwalMedisRanapTHT.jasper","report","::[ Pengkajian Awal Medis Ranap THT]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pengkajian_medis_ranap_tht.tanggal,"+
                "pengkajian_medis_ranap_tht.kd_dokter,pengkajian_medis_ranap_tht.anamnesis,pengkajian_medis_ranap_tht.hubungan,pengkajian_medis_ranap_tht.keluhan_utama,pengkajian_medis_ranap_tht.rps,"+
                "pengkajian_medis_ranap_tht.rpd,pengkajian_medis_ranap_tht.rpo,pengkajian_medis_ranap_tht.alergi,pengkajian_medis_ranap_tht.td,pengkajian_medis_ranap_tht.nadi,pengkajian_medis_ranap_tht.rr,"+
                "pengkajian_medis_ranap_tht.suhu,pengkajian_medis_ranap_tht.bb,pengkajian_medis_ranap_tht.tb,pengkajian_medis_ranap_tht.nyeri,pengkajian_medis_ranap_tht.status_nutrisi,"+
                "pengkajian_medis_ranap_tht.kondisi,pengkajian_medis_ranap_tht.sekret_telinga_kanan,pengkajian_medis_ranap_tht.sekret_telinga_kiri,pengkajian_medis_ranap_tht.tuli_telinga_kanan,"+
                "pengkajian_medis_ranap_tht.tuli_telinga_kiri,pengkajian_medis_ranap_tht.tumor_telinga_kanan,pengkajian_medis_ranap_tht.tumor_telinga_kiri,pengkajian_medis_ranap_tht.tinitus_telinga_kanan,"+
                "pengkajian_medis_ranap_tht.tinitus_telinga_kiri,pengkajian_medis_ranap_tht.sakit_telinga_kanan,pengkajian_medis_ranap_tht.sakit_telinga_kiri,pengkajian_medis_ranap_tht.korpus_alienum_telinga_kanan,"+
                "pengkajian_medis_ranap_tht.korpus_alienum_telinga_kiri,pengkajian_medis_ranap_tht.vertigo_telinga_kanan,pengkajian_medis_ranap_tht.vertigo_telinga_kiri,pengkajian_medis_ranap_tht.ket_lokalis,dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join pengkajian_medis_ranap_tht on reg_periksa.no_rawat=pengkajian_medis_ranap_tht.no_rawat "+
                "inner join dokter on pengkajian_medis_ranap_tht.kd_dokter=dokter.kd_dokter where pengkajian_medis_ranap_tht.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void TesAlatKeseimbanganKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TesAlatKeseimbanganKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TesAlatKeseimbanganKiriKeyPressed

    private void TesAlatKeseimbanganKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TesAlatKeseimbanganKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TesAlatKeseimbanganKananKeyPressed

    private void OAEKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OAEKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OAEKiriKeyPressed

    private void OAEKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OAEKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OAEKananKeyPressed

    private void BERAKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BERAKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BERAKiriKeyPressed

    private void BERAKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BERAKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BERAKananKeyPressed

    private void NadaMurniKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadaMurniKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadaMurniKiriKeyPressed

    private void NadaMurniKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadaMurniKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadaMurniKananKeyPressed

    private void AudiometriKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AudiometriKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AudiometriKiriKeyPressed

    private void AudiometriKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AudiometriKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AudiometriKananKeyPressed

    private void TympanometriKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TympanometriKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TympanometriKiriKeyPressed

    private void TympanometriKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TympanometriKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TympanometriKananKeyPressed

    private void BOAKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BOAKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BOAKiriKeyPressed

    private void BOAKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BOAKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BOAKananKeyPressed

    private void ScwabachKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ScwabachKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ScwabachKiriKeyPressed

    private void ScwabachKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ScwabachKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ScwabachKananKeyPressed

    private void RinneKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RinneKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RinneKiriKeyPressed

    private void RinneKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RinneKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RinneKananKeyPressed

    private void WeberKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WeberKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_WeberKiriKeyPressed

    private void WeberKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WeberKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_WeberKananKeyPressed

    private void BerbisikKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BerbisikKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BerbisikKiriKeyPressed

    private void BerbisikKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BerbisikKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BerbisikKananKeyPressed

    private void MastoidKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MastoidKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MastoidKiriKeyPressed

    private void MastoidKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MastoidKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MastoidKananKeyPressed

    private void TumorKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumorKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TumorKiriKeyPressed

    private void TumorKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumorKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TumorKananKeyPressed

    private void MembranTimpaniKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MembranTimpaniKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MembranTimpaniKiriKeyPressed

    private void MembranTimpaniKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MembranTimpaniKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MembranTimpaniKananKeyPressed

    private void DischargeKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DischargeKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DischargeKiriKeyPressed

    private void DischargeKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DischargeKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DischargeKananKeyPressed

    private void LiangTelingaKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LiangTelingaKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LiangTelingaKiriKeyPressed

    private void DaunTelingaKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DaunTelingaKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DaunTelingaKiriKeyPressed

    private void LiangTelingaKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LiangTelingaKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LiangTelingaKananKeyPressed

    private void DaunTelingaKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DaunTelingaKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DaunTelingaKananKeyPressed

    private void KelenjerLimpeLeherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelenjerLimpeLeherKeyPressed

    }//GEN-LAST:event_KelenjerLimpeLeherKeyPressed

    private void TerapiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerapiKeyPressed

    }//GEN-LAST:event_TerapiKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed

    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void KetLokalisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetLokalisKeyPressed

    }//GEN-LAST:event_KetLokalisKeyPressed

    private void NyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NyeriKeyPressed
        Valid.pindah(evt,RR,StatusNutrisi);
    }//GEN-LAST:event_NyeriKeyPressed

    private void RPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPSKeyPressed
        Valid.pindah2(evt,KeluhanUtama,RPO);
    }//GEN-LAST:event_RPSKeyPressed

    private void HubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeyPressed
        Valid.pindah(evt,Anamnesis,KeluhanUtama);
    }//GEN-LAST:event_HubunganKeyPressed

    private void StatusNutrisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusNutrisiKeyPressed
        Valid.pindah(evt,Nyeri,Kondisi);
    }//GEN-LAST:event_StatusNutrisiKeyPressed

    private void KondisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KondisiKeyPressed
        Valid.pindah2(evt,StatusNutrisi,KetLokalis);
    }//GEN-LAST:event_KondisiKeyPressed

    private void RPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPOKeyPressed
        Valid.pindah2(evt,RPS,RPD);
    }//GEN-LAST:event_RPOKeyPressed

    private void RPDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPDKeyPressed
        Valid.pindah2(evt,RPO,Alergi);
    }//GEN-LAST:event_RPDKeyPressed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        Valid.pindah2(evt,Hubungan,RPS);
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
        Valid.pindah(evt,TglAsuhan,Hubungan);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt,RPO,TD);
    }//GEN-LAST:event_AlergiKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt,Nadi,Nyeri);
    }//GEN-LAST:event_RRKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt,Alergi,TB);
    }//GEN-LAST:event_TDKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt,BB,Nadi);
    }//GEN-LAST:event_SuhuKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt,Suhu,RR);
    }//GEN-LAST:event_NadiKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt,TD,BB);
    }//GEN-LAST:event_TBKeyPressed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt,TB,Suhu);
    }//GEN-LAST:event_BBKeyPressed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{
            Valid.pindah(evt,TCari,BtnDokter);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void DiagnosaKerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKerjaKeyPressed

    }//GEN-LAST:event_DiagnosaKerjaKeyPressed

    private void DiagnosaBandingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaBandingKeyPressed

    }//GEN-LAST:event_DiagnosaBandingKeyPressed

    private void SekretKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SekretKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SekretKananKeyPressed

    private void SekretKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SekretKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SekretKiriKeyPressed

    private void TuliKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TuliKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TuliKananKeyPressed

    private void TuliKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TuliKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TuliKiriKeyPressed

    private void TumorrKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumorrKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TumorrKananKeyPressed

    private void TumorrKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumorrKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TumorrKiriKeyPressed

    private void TinitusKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinitusKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TinitusKananKeyPressed

    private void TinitusKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinitusKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TinitusKiriKeyPressed

    private void SakitKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SakitKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SakitKananKeyPressed

    private void SakitKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SakitKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SakitKiriKeyPressed

    private void KorpusAlienumKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KorpusAlienumKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KorpusAlienumKananKeyPressed

    private void KorpusAlienumKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KorpusAlienumKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KorpusAlienumKiriKeyPressed

    private void VertigoKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VertigoKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VertigoKananKeyPressed

    private void VertigoKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VertigoKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VertigoKiriKeyPressed

    private void SekretHidungKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SekretHidungKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SekretHidungKananKeyPressed

    private void SekretHidungKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SekretHidungKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SekretHidungKiriKeyPressed

    private void TersumbatKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TersumbatKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TersumbatKananKeyPressed

    private void TersumbatKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TersumbatKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TersumbatKiriKeyPressed

    private void TumorHidungKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumorHidungKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TumorHidungKananKeyPressed

    private void TumorHidungKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumorHidungKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TumorHidungKiriKeyPressed

    private void PilekKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PilekKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PilekKananKeyPressed

    private void PilekKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PilekKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PilekKiriKeyPressed

    private void SakitHidungKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SakitHidungKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SakitHidungKananKeyPressed

    private void SakitHidungKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SakitHidungKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SakitHidungKiriKeyPressed

    private void KorpusAlienumHidungKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KorpusAlienumHidungKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KorpusAlienumHidungKananKeyPressed

    private void KorpusAlienumHidungKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KorpusAlienumHidungKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KorpusAlienumHidungKiriKeyPressed

    private void BersinKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BersinKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BersinKananKeyPressed

    private void BersinKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BersinKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BersinKiriKeyPressed

    private void TenggorokRiakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokRiakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokRiakKeyPressed

    private void TenggorokGangguanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokGangguanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokGangguanKeyPressed

    private void TenggorokSuaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokSuaraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokSuaraKeyPressed

    private void TenggorokTumorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokTumorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokTumorKeyPressed

    private void TenggorokBatukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokBatukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokBatukKeyPressed

    private void TenggorokKorpusAlienumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokKorpusAlienumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokKorpusAlienumKeyPressed

    private void TenggorokSesakNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokSesakNafasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokSesakNafasKeyPressed

    private void HidungLuarKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HidungLuarKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HidungLuarKananKeyPressed

    private void HidungLuarKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HidungLuarKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HidungLuarKiriKeyPressed

    private void KavumNasiKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KavumNasiKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KavumNasiKananKeyPressed

    private void KavumNasiKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KavumNasiKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KavumNasiKiriKeyPressed

    private void SeptumHidungKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SeptumHidungKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeptumHidungKananKeyPressed

    private void SeptumHidungKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SeptumHidungKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeptumHidungKiriKeyPressed

    private void DischargeHidungKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DischargeHidungKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DischargeHidungKananKeyPressed

    private void DischargeHidungKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DischargeHidungKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DischargeHidungKiriKeyPressed

    private void MukosaHidungKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MukosaHidungKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MukosaHidungKananKeyPressed

    private void MukosaHidungKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MukosaHidungKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MukosaHidungKiriKeyPressed

    private void TumorHidungKanannKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumorHidungKanannKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TumorHidungKanannKeyPressed

    private void TumorHidungKiriiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumorHidungKiriiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TumorHidungKiriiKeyPressed

    private void KonkaHidungKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KonkaHidungKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KonkaHidungKananKeyPressed

    private void KonkaHidungKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KonkaHidungKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KonkaHidungKiriKeyPressed

    private void NasoEndoskopiKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NasoEndoskopiKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NasoEndoskopiKananKeyPressed

    private void NasoEndoskopiKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NasoEndoskopiKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NasoEndoskopiKiriKeyPressed

    private void TenggorokDispenuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokDispenuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokDispenuKeyPressed

    private void TenggorokSianosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokSianosisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokSianosisKeyPressed

    private void TenggorokMucosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokMucosaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokMucosaKeyPressed

    private void TenggorokDindingBelakangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokDindingBelakangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokDindingBelakangKeyPressed

    private void TenggorokStridorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokStridorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokStridorKeyPressed

    private void TenggorokSuaraaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokSuaraaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokSuaraaKeyPressed

    private void TenggorokTonsilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokTonsilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenggorokTonsilKeyPressed

    private void LaringEpiglotisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaringEpiglotisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaringEpiglotisKeyPressed

    private void LaringArienoidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaringArienoidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaringArienoidKeyPressed

    private void LaringPlikaVokalisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaringPlikaVokalisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaringPlikaVokalisKeyPressed

    private void LaringRimaglotisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaringRimaglotisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaringRimaglotisKeyPressed

    private void LaringPlikaVentrikulorisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaringPlikaVentrikulorisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaringPlikaVentrikulorisKeyPressed

    private void LaringEndoskopiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaringEndoskopiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaringEndoskopiKeyPressed

    private void BolehPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BolehPulangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BolehPulangKeyPressed

    private void JamPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamPulangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JamPulangKeyPressed

    private void KontrolPoliklinikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KontrolPoliklinikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KontrolPoliklinikKeyPressed

    private void JamKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKontrolKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JamKontrolKeyPressed

    private void DirawatDiRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirawatDiRuangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DirawatDiRuangKeyPressed

    private void RuangLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RuangLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RuangLainKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalMedisRanapTHT dialog = new RMPenilaianAwalMedisRanapTHT(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alergi;
    private widget.ComboBox Anamnesis;
    private widget.TextBox AudiometriKanan;
    private widget.TextBox AudiometriKiri;
    private widget.TextBox BB;
    private widget.TextBox BERAKanan;
    private widget.TextBox BERAKiri;
    private widget.TextBox BOAKanan;
    private widget.TextBox BOAKiri;
    private widget.TextBox BerbisikKanan;
    private widget.TextBox BerbisikKiri;
    private widget.TextBox BersinKanan;
    private widget.TextBox BersinKiri;
    private widget.ComboBox BolehPulang;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTanggalKeluar;
    private widget.Tanggal DTPTanggalKontrol;
    private widget.TextBox DaunTelingaKanan;
    private widget.TextBox DaunTelingaKiri;
    private widget.TextArea DiagnosaBanding;
    private widget.TextArea DiagnosaKerja;
    private widget.ComboBox DirawatDiRuang;
    private widget.TextBox DischargeHidungKanan;
    private widget.TextBox DischargeHidungKiri;
    private widget.TextBox DischargeKanan;
    private widget.TextBox DischargeKiri;
    private widget.PanelBiasa FormInput;
    private widget.TextBox HidungLuarKanan;
    private widget.TextBox HidungLuarKiri;
    private widget.TextBox Hubungan;
    private widget.TextBox JamKontrol;
    private widget.TextBox JamPulang;
    private widget.TextBox Jk;
    private widget.TextBox KavumNasiKanan;
    private widget.TextBox KavumNasiKiri;
    private widget.TextBox KdDokter;
    private widget.TextArea KelenjerLimpeLeher;
    private widget.TextArea KeluhanUtama;
    private widget.TextArea KetLokalis;
    private widget.TextArea Kondisi;
    private widget.TextBox KonkaHidungKanan;
    private widget.TextBox KonkaHidungKiri;
    private widget.ComboBox KontrolPoliklinik;
    private widget.TextBox KorpusAlienumHidungKanan;
    private widget.TextBox KorpusAlienumHidungKiri;
    private widget.TextBox KorpusAlienumKanan;
    private widget.TextBox KorpusAlienumKiri;
    private widget.Label LCount;
    private widget.TextBox LaringArienoid;
    private widget.TextBox LaringEndoskopi;
    private widget.TextBox LaringEpiglotis;
    private widget.TextBox LaringPlikaVentrikuloris;
    private widget.TextBox LaringPlikaVokalis;
    private widget.TextBox LaringRimaglotis;
    private widget.TextBox LiangTelingaKanan;
    private widget.TextBox LiangTelingaKiri;
    private widget.editorpane LoadHTML;
    private widget.TextBox MastoidKanan;
    private widget.TextBox MastoidKiri;
    private widget.TextBox MembranTimpaniKanan;
    private widget.TextBox MembranTimpaniKiri;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox MukosaHidungKanan;
    private widget.TextBox MukosaHidungKiri;
    private widget.TextBox NadaMurniKanan;
    private widget.TextBox NadaMurniKiri;
    private widget.TextBox Nadi;
    private widget.TextBox NasoEndoskopiKanan;
    private widget.TextBox NasoEndoskopiKiri;
    private widget.TextBox NmDokter;
    private widget.TextBox Nyeri;
    private widget.TextBox OAEKanan;
    private widget.TextBox OAEKiri;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.TextBox PilekKanan;
    private widget.TextBox PilekKiri;
    private widget.TextArea RPD;
    private widget.TextArea RPO;
    private widget.TextArea RPS;
    private widget.TextBox RR;
    private widget.TextBox RinneKanan;
    private widget.TextBox RinneKiri;
    private widget.TextBox RuangLain;
    private widget.TextBox SakitHidungKanan;
    private widget.TextBox SakitHidungKiri;
    private widget.TextBox SakitKanan;
    private widget.TextBox SakitKiri;
    private widget.ScrollPane Scroll;
    private widget.TextBox ScwabachKanan;
    private widget.TextBox ScwabachKiri;
    private widget.TextBox SekretHidungKanan;
    private widget.TextBox SekretHidungKiri;
    private widget.TextBox SekretKanan;
    private widget.TextBox SekretKiri;
    private widget.TextBox SeptumHidungKanan;
    private widget.TextBox SeptumHidungKiri;
    private widget.TextBox StatusNutrisi;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TenggorokBatuk;
    private widget.TextBox TenggorokDindingBelakang;
    private widget.TextBox TenggorokDispenu;
    private widget.TextBox TenggorokGangguan;
    private widget.TextBox TenggorokKorpusAlienum;
    private widget.TextBox TenggorokMucosa;
    private widget.TextBox TenggorokRiak;
    private widget.TextBox TenggorokSesakNafas;
    private widget.TextBox TenggorokSianosis;
    private widget.TextBox TenggorokStridor;
    private widget.TextBox TenggorokSuara;
    private widget.TextBox TenggorokSuaraa;
    private widget.TextBox TenggorokTonsil;
    private widget.TextBox TenggorokTumor;
    private widget.TextArea Terapi;
    private widget.TextBox TersumbatKanan;
    private widget.TextBox TersumbatKiri;
    private widget.TextBox TesAlatKeseimbanganKanan;
    private widget.TextBox TesAlatKeseimbanganKiri;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.TextBox TinitusKanan;
    private widget.TextBox TinitusKiri;
    private widget.TextBox TuliKanan;
    private widget.TextBox TuliKiri;
    private widget.TextBox TumorHidungKanan;
    private widget.TextBox TumorHidungKanann;
    private widget.TextBox TumorHidungKiri;
    private widget.TextBox TumorHidungKirii;
    private widget.TextBox TumorKanan;
    private widget.TextBox TumorKiri;
    private widget.TextBox TumorrKanan;
    private widget.TextBox TumorrKiri;
    private widget.TextBox TympanometriKanan;
    private widget.TextBox TympanometriKiri;
    private widget.TextBox VertigoKanan;
    private widget.TextBox VertigoKiri;
    private widget.TextBox WeberKanan;
    private widget.TextBox WeberKiri;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel15;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel158;
    private widget.Label jLabel159;
    private widget.Label jLabel16;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel166;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel169;
    private widget.Label jLabel17;
    private widget.Label jLabel170;
    private widget.Label jLabel171;
    private widget.Label jLabel172;
    private widget.Label jLabel173;
    private widget.Label jLabel174;
    private widget.Label jLabel175;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel33;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel40;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel82;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pengkajian_medis_ranap_tht.tanggal,"+
                        "pengkajian_medis_ranap_tht.kd_dokter,pengkajian_medis_ranap_tht.anamnesis,pengkajian_medis_ranap_tht.hubungan,pengkajian_medis_ranap_tht.keluhan_utama,pengkajian_medis_ranap_tht.rps,"+
                        "pengkajian_medis_ranap_tht.rpd,pengkajian_medis_ranap_tht.rpo,pengkajian_medis_ranap_tht.alergi,pengkajian_medis_ranap_tht.td,pengkajian_medis_ranap_tht.nadi,pengkajian_medis_ranap_tht.rr,"+
                        "pengkajian_medis_ranap_tht.suhu,pengkajian_medis_ranap_tht.bb,pengkajian_medis_ranap_tht.tb,pengkajian_medis_ranap_tht.nyeri,pengkajian_medis_ranap_tht.status_nutrisi,"+
                        "pengkajian_medis_ranap_tht.kondisi,pengkajian_medis_ranap_tht.sekret_telinga_kanan,pengkajian_medis_ranap_tht.sekret_telinga_kiri,pengkajian_medis_ranap_tht.tuli_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.tuli_telinga_kiri,pengkajian_medis_ranap_tht.tumor_telinga_kanan,pengkajian_medis_ranap_tht.tumor_telinga_kiri,pengkajian_medis_ranap_tht.tinitus_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.tinitus_telinga_kiri,pengkajian_medis_ranap_tht.sakit_telinga_kanan,pengkajian_medis_ranap_tht.sakit_telinga_kiri,pengkajian_medis_ranap_tht.korpus_alienum_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.korpus_alienum_telinga_kiri,pengkajian_medis_ranap_tht.vertigo_telinga_kanan,pengkajian_medis_ranap_tht.vertigo_telinga_kiri,pengkajian_medis_ranap_tht.sekret_hidung_kanan,"+
                        "pengkajian_medis_ranap_tht.sekret_hidung_kiri,pengkajian_medis_ranap_tht.tersumbat_hidung_kanan,pengkajian_medis_ranap_tht.tersumbat_hidung_kiri,pengkajian_medis_ranap_tht.tumor_hidung_kanan,"+
                        "pengkajian_medis_ranap_tht.tumor_hidung_kiri,pengkajian_medis_ranap_tht.pilek_hidung_kanan,pengkajian_medis_ranap_tht.pilek_hidung_kiri,pengkajian_medis_ranap_tht.sakit_hidung_kanan,"+
                        "pengkajian_medis_ranap_tht.sakit_hidung_kiri,pengkajian_medis_ranap_tht.korpus_alienum_hidung_kanan,pengkajian_medis_ranap_tht.korpus_alienum_hidung_kiri,pengkajian_medis_ranap_tht.bersin_hidung_kanan,"+
                        "pengkajian_medis_ranap_tht.bersin_hidung_kiri,pengkajian_medis_ranap_tht.riak_tenggorok,pengkajian_medis_ranap_tht.gangguan_tenggorok,pengkajian_medis_ranap_tht.suara_tenggorok,pengkajian_medis_ranap_tht.tumor_tenggorok,"+
                        "pengkajian_medis_ranap_tht.batuk_tenggorok,pengkajian_medis_ranap_tht.korpus_alienum_tenggorok,pengkajian_medis_ranap_tht.sesak_nafas_tenggorok,pengkajian_medis_ranap_tht.ket_lokalis,pengkajian_medis_ranap_tht.daun_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.daun_telinga_kiri,pengkajian_medis_ranap_tht.liang_telinga_kanan,pengkajian_medis_ranap_tht.liang_telinga_kiri,pengkajian_medis_ranap_tht.discharge_telinga_kanan,pengkajian_medis_ranap_tht.discharge_telinga_kiri,"+
                        "pengkajian_medis_ranap_tht.membrana_timpani_telinga_kanan,pengkajian_medis_ranap_tht.membrana_timpani_telinga_kiri,pengkajian_medis_ranap_tht.tumor_telinga_kanann,pengkajian_medis_ranap_tht.tumor_telinga_kirii,"+
                        "pengkajian_medis_ranap_tht.mastoid_telinga_kanan,pengkajian_medis_ranap_tht.mastoid_telinga_kiri,pengkajian_medis_ranap_tht.berbisik_telinga_kanan,pengkajian_medis_ranap_tht.berbisik_telinga_kiri,pengkajian_medis_ranap_tht.weber_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.weber_telinga_kiri,pengkajian_medis_ranap_tht.rinne_telinga_kanan,pengkajian_medis_ranap_tht.rinne_telinga_kiri,pengkajian_medis_ranap_tht.scwabach_telinga_kanan,pengkajian_medis_ranap_tht.scwabach_telinga_kiri,"+
                        "pengkajian_medis_ranap_tht.boa_telinga_kanan,pengkajian_medis_ranap_tht.boa_telinga_kiri,pengkajian_medis_ranap_tht.tympanometri_telinga_kanan,pengkajian_medis_ranap_tht.tympanometri_telinga_kiri,pengkajian_medis_ranap_tht.audiometri_telinga_kanan,pengkajian_medis_ranap_tht.audiometri_telinga_kiri,"+
                        "pengkajian_medis_ranap_tht.nada_murni_telinga_kanan,pengkajian_medis_ranap_tht.nada_murni_telinga_kiri,pengkajian_medis_ranap_tht.bera_telinga_kanan,pengkajian_medis_ranap_tht.bera_telinga_kiri,pengkajian_medis_ranap_tht.oae_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.oae_telinga_kiri,pengkajian_medis_ranap_tht.seimbang_kanan,pengkajian_medis_ranap_tht.seimbang_kiri,pengkajian_medis_ranap_tht.hidung_luar_kanan,pengkajian_medis_ranap_tht.hidung_luar_kiri,"+
                        "pengkajian_medis_ranap_tht.kavum_nasi_kanan,pengkajian_medis_ranap_tht.kavum_nasi_kiri,pengkajian_medis_ranap_tht.septum_kanan,pengkajian_medis_ranap_tht.septum_kiri,pengkajian_medis_ranap_tht.discharge_hidung_kanan,pengkajian_medis_ranap_tht.discharge_hidung_kiri,"+
                        "pengkajian_medis_ranap_tht.mukosa_hidung_kanan,pengkajian_medis_ranap_tht.mukosa_hidung_kiri,pengkajian_medis_ranap_tht.tumor_hidung_kanann,pengkajian_medis_ranap_tht.tumor_hidung_kirii,pengkajian_medis_ranap_tht.konka_kanan,"+
                        "pengkajian_medis_ranap_tht.konka_kiri,pengkajian_medis_ranap_tht.naso_endoskopi_kanan,pengkajian_medis_ranap_tht.naso_endoskopi_kiri,pengkajian_medis_ranap_tht.dispenu_tenggorok,pengkajian_medis_ranap_tht.stridor_tenggorok,pengkajian_medis_ranap_tht.sianosis_tenggorok,"+
                        "pengkajian_medis_ranap_tht.suara_tenggorokk,pengkajian_medis_ranap_tht.mucosa_tenggorok,pengkajian_medis_ranap_tht.tonsil_tenggorok,pengkajian_medis_ranap_tht.dinding_belakang_tenggorok,pengkajian_medis_ranap_tht.epiglotis,pengkajian_medis_ranap_tht.plika_vokalis,"+
                        "pengkajian_medis_ranap_tht.aritenoid,pengkajian_medis_ranap_tht.rimaglotis,pengkajian_medis_ranap_tht.plika_ventrikuloris,pengkajian_medis_ranap_tht.endoskopi,pengkajian_medis_ranap_tht.diagnosa_kerja,pengkajian_medis_ranap_tht.diagnosa_pembanding,pengkajian_medis_ranap_tht.kelenjar_limpe_leher,pengkajian_medis_ranap_tht.terapi,"+
                        "pengkajian_medis_ranap_tht.boleh_pulang,pengkajian_medis_ranap_tht.tgl_keluar,pengkajian_medis_ranap_tht.jam_keluar,pengkajian_medis_ranap_tht.kontrol,pengkajian_medis_ranap_tht.tgl_kontrol,pengkajian_medis_ranap_tht.jam_kontrol,pengkajian_medis_ranap_tht.dirawat_ruang,pengkajian_medis_ranap_tht.ruang_lain,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join pengkajian_medis_ranap_tht on reg_periksa.no_rawat=pengkajian_medis_ranap_tht.no_rawat "+
                        "inner join dokter on pengkajian_medis_ranap_tht.kd_dokter=dokter.kd_dokter where "+
                        "pengkajian_medis_ranap_tht.tanggal between ? and ? order by pengkajian_medis_ranap_tht.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pengkajian_medis_ranap_tht.tanggal,"+
                        "pengkajian_medis_ranap_tht.kd_dokter,pengkajian_medis_ranap_tht.anamnesis,pengkajian_medis_ranap_tht.hubungan,pengkajian_medis_ranap_tht.keluhan_utama,pengkajian_medis_ranap_tht.rps,"+
                        "pengkajian_medis_ranap_tht.rpd,pengkajian_medis_ranap_tht.rpo,pengkajian_medis_ranap_tht.alergi,pengkajian_medis_ranap_tht.td,pengkajian_medis_ranap_tht.nadi,pengkajian_medis_ranap_tht.rr,"+
                        "pengkajian_medis_ranap_tht.suhu,pengkajian_medis_ranap_tht.bb,pengkajian_medis_ranap_tht.tb,pengkajian_medis_ranap_tht.nyeri,pengkajian_medis_ranap_tht.status_nutrisi,"+
                        "pengkajian_medis_ranap_tht.kondisi,pengkajian_medis_ranap_tht.sekret_telinga_kanan,pengkajian_medis_ranap_tht.sekret_telinga_kiri,pengkajian_medis_ranap_tht.tuli_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.tuli_telinga_kiri,pengkajian_medis_ranap_tht.tumor_telinga_kanan,pengkajian_medis_ranap_tht.tumor_telinga_kiri,pengkajian_medis_ranap_tht.tinitus_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.tinitus_telinga_kiri,pengkajian_medis_ranap_tht.sakit_telinga_kanan,pengkajian_medis_ranap_tht.sakit_telinga_kiri,pengkajian_medis_ranap_tht.korpus_alienum_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.korpus_alienum_telinga_kiri,pengkajian_medis_ranap_tht.vertigo_telinga_kanan,pengkajian_medis_ranap_tht.vertigo_telinga_kiri,pengkajian_medis_ranap_tht.sekret_hidung_kanan,"+
                        "pengkajian_medis_ranap_tht.sekret_hidung_kiri,pengkajian_medis_ranap_tht.tersumbat_hidung_kanan,pengkajian_medis_ranap_tht.tersumbat_hidung_kiri,pengkajian_medis_ranap_tht.tumor_hidung_kanan,"+
                        "pengkajian_medis_ranap_tht.tumor_hidung_kiri,pengkajian_medis_ranap_tht.pilek_hidung_kanan,pengkajian_medis_ranap_tht.pilek_hidung_kiri,pengkajian_medis_ranap_tht.sakit_hidung_kanan,"+
                        "pengkajian_medis_ranap_tht.sakit_hidung_kiri,pengkajian_medis_ranap_tht.korpus_alienum_hidung_kanan,pengkajian_medis_ranap_tht.korpus_alienum_hidung_kiri,pengkajian_medis_ranap_tht.bersin_hidung_kanan,"+
                        "pengkajian_medis_ranap_tht.bersin_hidung_kiri,pengkajian_medis_ranap_tht.riak_tenggorok,pengkajian_medis_ranap_tht.gangguan_tenggorok,pengkajian_medis_ranap_tht.suara_tenggorok,pengkajian_medis_ranap_tht.tumor_tenggorok,"+
                        "pengkajian_medis_ranap_tht.batuk_tenggorok,pengkajian_medis_ranap_tht.korpus_alienum_tenggorok,pengkajian_medis_ranap_tht.sesak_nafas_tenggorok,pengkajian_medis_ranap_tht.ket_lokalis,pengkajian_medis_ranap_tht.daun_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.daun_telinga_kiri,pengkajian_medis_ranap_tht.liang_telinga_kanan,pengkajian_medis_ranap_tht.liang_telinga_kiri,pengkajian_medis_ranap_tht.discharge_telinga_kanan,pengkajian_medis_ranap_tht.discharge_telinga_kiri,"+
                        "pengkajian_medis_ranap_tht.membrana_timpani_telinga_kanan,pengkajian_medis_ranap_tht.membrana_timpani_telinga_kiri,pengkajian_medis_ranap_tht.tumor_telinga_kanann,pengkajian_medis_ranap_tht.tumor_telinga_kirii,"+
                        "pengkajian_medis_ranap_tht.mastoid_telinga_kanan,pengkajian_medis_ranap_tht.mastoid_telinga_kiri,pengkajian_medis_ranap_tht.berbisik_telinga_kanan,pengkajian_medis_ranap_tht.berbisik_telinga_kiri,pengkajian_medis_ranap_tht.weber_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.weber_telinga_kiri,pengkajian_medis_ranap_tht.rinne_telinga_kanan,pengkajian_medis_ranap_tht.rinne_telinga_kiri,pengkajian_medis_ranap_tht.scwabach_telinga_kanan,pengkajian_medis_ranap_tht.scwabach_telinga_kiri,"+
                        "pengkajian_medis_ranap_tht.boa_telinga_kanan,pengkajian_medis_ranap_tht.boa_telinga_kiri,pengkajian_medis_ranap_tht.tympanometri_telinga_kanan,pengkajian_medis_ranap_tht.tympanometri_telinga_kiri,pengkajian_medis_ranap_tht.audiometri_telinga_kanan,pengkajian_medis_ranap_tht.audiometri_telinga_kiri,"+
                        "pengkajian_medis_ranap_tht.nada_murni_telinga_kanan,pengkajian_medis_ranap_tht.nada_murni_telinga_kiri,pengkajian_medis_ranap_tht.bera_telinga_kanan,pengkajian_medis_ranap_tht.bera_telinga_kiri,pengkajian_medis_ranap_tht.oae_telinga_kanan,"+
                        "pengkajian_medis_ranap_tht.oae_telinga_kiri,pengkajian_medis_ranap_tht.seimbang_kanan,pengkajian_medis_ranap_tht.seimbang_kiri,pengkajian_medis_ranap_tht.hidung_luar_kanan,pengkajian_medis_ranap_tht.hidung_luar_kiri,"+
                        "pengkajian_medis_ranap_tht.kavum_nasi_kanan,pengkajian_medis_ranap_tht.kavum_nasi_kiri,pengkajian_medis_ranap_tht.septum_kanan,pengkajian_medis_ranap_tht.septum_kiri,pengkajian_medis_ranap_tht.discharge_hidung_kanan,pengkajian_medis_ranap_tht.discharge_hidung_kiri,"+
                        "pengkajian_medis_ranap_tht.mukosa_hidung_kanan,pengkajian_medis_ranap_tht.mukosa_hidung_kiri,pengkajian_medis_ranap_tht.tumor_hidung_kanann,pengkajian_medis_ranap_tht.tumor_hidung_kirii,pengkajian_medis_ranap_tht.konka_kanan,"+
                        "pengkajian_medis_ranap_tht.konka_kiri,pengkajian_medis_ranap_tht.naso_endoskopi_kanan,pengkajian_medis_ranap_tht.naso_endoskopi_kiri,pengkajian_medis_ranap_tht.dispenu_tenggorok,pengkajian_medis_ranap_tht.stridor_tenggorok,pengkajian_medis_ranap_tht.sianosis_tenggorok,"+
                        "pengkajian_medis_ranap_tht.suara_tenggorokk,pengkajian_medis_ranap_tht.mucosa_tenggorok,pengkajian_medis_ranap_tht.tonsil_tenggorok,pengkajian_medis_ranap_tht.dinding_belakang_tenggorok,pengkajian_medis_ranap_tht.epiglotis,pengkajian_medis_ranap_tht.plika_vokalis,"+
                        "pengkajian_medis_ranap_tht.aritenoid,pengkajian_medis_ranap_tht.rimaglotis,pengkajian_medis_ranap_tht.plika_ventrikuloris,pengkajian_medis_ranap_tht.endoskopi,pengkajian_medis_ranap_tht.diagnosa_kerja,pengkajian_medis_ranap_tht.diagnosa_pembanding,pengkajian_medis_ranap_tht.kelenjar_limpe_leher,pengkajian_medis_ranap_tht.terapi,"+
                        "pengkajian_medis_ranap_tht.boleh_pulang,pengkajian_medis_ranap_tht.tgl_keluar,pengkajian_medis_ranap_tht.jam_keluar,pengkajian_medis_ranap_tht.kontrol,pengkajian_medis_ranap_tht.tgl_kontrol,pengkajian_medis_ranap_tht.jam_kontrol,pengkajian_medis_ranap_tht.dirawat_ruang,pengkajian_medis_ranap_tht.ruang_lain,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join pengkajian_medis_ranap_tht on reg_periksa.no_rawat=pengkajian_medis_ranap_tht.no_rawat "+
                        "inner join dokter on pengkajian_medis_ranap_tht.kd_dokter=dokter.kd_dokter where "+
                        "pengkajian_medis_ranap_tht.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "pengkajian_medis_ranap_tht.kd_dokter like ? or dokter.nm_dokter like ?) order by pengkajian_medis_ranap_tht.tanggal");
            }
                
            try {
                if(TCari.getText().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("tanggal"),
                        rs.getString("anamnesis"),rs.getString("hubungan"),rs.getString("keluhan_utama"),rs.getString("rps"),rs.getString("rpd"),rs.getString("rpo"),rs.getString("alergi"),rs.getString("td"),rs.getString("nadi"),
                        rs.getString("rr"),rs.getString("suhu"),rs.getString("bb"),rs.getString("tb"),rs.getString("nyeri"),rs.getString("status_nutrisi"),rs.getString("kondisi"),rs.getString("sekret_telinga_kanan"),rs.getString("sekret_telinga_kiri"),
                        rs.getString("tuli_telinga_kanan"),rs.getString("tuli_telinga_kiri"),rs.getString("tumor_telinga_kanan"),rs.getString("tumor_telinga_kiri"),rs.getString("tinitus_telinga_kanan"),rs.getString("tinitus_telinga_kiri"),
                        rs.getString("sakit_telinga_kanan"),rs.getString("sakit_telinga_kiri"),rs.getString("korpus_alienum_telinga_kanan"),rs.getString("korpus_alienum_telinga_kiri"),rs.getString("vertigo_telinga_kanan"),rs.getString("vertigo_telinga_kiri"),
                        rs.getString("sekret_hidung_kanan"),rs.getString("sekret_hidung_kiri"),rs.getString("tersumbat_hidung_kanan"),rs.getString("tersumbat_hidung_kiri"),rs.getString("tumor_hidung_kanan"),rs.getString("tumor_hidung_kiri"),rs.getString("pilek_hidung_kanan"),
                        rs.getString("pilek_hidung_kiri"),rs.getString("sakit_hidung_kanan"),rs.getString("sakit_hidung_kiri"),rs.getString("korpus_alienum_hidung_kanan"),rs.getString("korpus_alienum_hidung_kiri"),rs.getString("bersin_hidung_kanan"),rs.getString("bersin_hidung_kiri"),
                        rs.getString("riak_tenggorok"),rs.getString("gangguan_tenggorok"),rs.getString("suara_tenggorok"),rs.getString("tumor_tenggorok"),rs.getString("batuk_tenggorok"),rs.getString("korpus_alienum_tenggorok"),rs.getString("sesak_nafas_tenggorok"),rs.getString("ket_lokalis"),
                        rs.getString("daun_telinga_kanan"),rs.getString("daun_telinga_kiri"),rs.getString("liang_telinga_kanan"),rs.getString("liang_telinga_kiri"),rs.getString("discharge_telinga_kanan"),rs.getString("discharge_telinga_kiri"),rs.getString("membrana_timpani_telinga_kanan"),
                        rs.getString("membrana_timpani_telinga_kiri"),rs.getString("tumor_telinga_kanann"),rs.getString("tumor_telinga_kirii"),rs.getString("mastoid_telinga_kanan"),rs.getString("mastoid_telinga_kiri"),rs.getString("berbisik_telinga_kanan"),rs.getString("berbisik_telinga_kiri"),
                        rs.getString("weber_telinga_kanan"),rs.getString("weber_telinga_kiri"),rs.getString("rinne_telinga_kanan"),rs.getString("rinne_telinga_kiri"),rs.getString("scwabach_telinga_kanan"),rs.getString("scwabach_telinga_kiri"),rs.getString("boa_telinga_kanan"),rs.getString("boa_telinga_kiri"),rs.getString("tympanometri_telinga_kanan"),
                        rs.getString("tympanometri_telinga_kiri"),rs.getString("audiometri_telinga_kanan"),rs.getString("audiometri_telinga_kiri"),rs.getString("nada_murni_telinga_kanan"),rs.getString("nada_murni_telinga_kiri"),rs.getString("bera_telinga_kanan"),rs.getString("bera_telinga_kiri"),
                        rs.getString("oae_telinga_kanan"),rs.getString("oae_telinga_kiri"),rs.getString("seimbang_kanan"),rs.getString("seimbang_kiri"),rs.getString("hidung_luar_kanan"),rs.getString("hidung_luar_kiri"),rs.getString("kavum_nasi_kanan"),rs.getString("kavum_nasi_kiri"),rs.getString("septum_kanan"),
                        rs.getString("septum_kiri"),rs.getString("discharge_hidung_kanan"),rs.getString("discharge_hidung_kiri"),rs.getString("mukosa_hidung_kanan"),rs.getString("mukosa_hidung_kiri"),rs.getString("tumor_hidung_kanann"),rs.getString("tumor_hidung_kirii"),rs.getString("konka_kanan"),
                        rs.getString("konka_kiri"),rs.getString("naso_endoskopi_kanan"),rs.getString("naso_endoskopi_kiri"),rs.getString("dispenu_tenggorok"),rs.getString("stridor_tenggorok"),rs.getString("sianosis_tenggorok"),rs.getString("suara_tenggorokk"),rs.getString("mucosa_tenggorok"),
                        rs.getString("tonsil_tenggorok"),rs.getString("dinding_belakang_tenggorok"),rs.getString("epiglotis"),rs.getString("plika_vokalis"),rs.getString("aritenoid"),rs.getString("rimaglotis"),rs.getString("plika_ventrikuloris"),rs.getString("endoskopi"),rs.getString("diagnosa_kerja"),
                        rs.getString("diagnosa_pembanding"),rs.getString("kelenjar_limpe_leher"),rs.getString("terapi"),rs.getString("boleh_pulang"),rs.getString("tgl_keluar"),rs.getString("jam_keluar"),rs.getString("kontrol"),rs.getString("tgl_kontrol"),rs.getString("jam_kontrol"),rs.getString("dirawat_ruang"),
                        rs.getString("ruang_lain")  
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        Anamnesis.setSelectedIndex(0);
        Hubungan.setText("");
        KeluhanUtama.setText("");
        RPS.setText("");
        RPD.setText("");
        RPO.setText("");
        Alergi.setText("");
        StatusNutrisi.setText("");
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");
        Nyeri.setText("");
        BB.setText("");
        TB.setText("");
        Kondisi.setText("");
        SekretKanan.setText("TAK");
        SekretKiri.setText("TAK");
        TuliKanan.setText("TAK");
        TuliKiri.setText("TAK");
        TumorKanan.setText("TAK");
        TumorKiri.setText("TAK");
        TinitusKanan.setText("TAK");
        TinitusKiri.setText("TAK");
        SakitKanan.setText("TAK");
        SakitKiri.setText("TAK");
        KorpusAlienumKanan.setText("TAK");
        KorpusAlienumKiri.setText("TAK");
        VertigoKanan.setText("TAK");
        VertigoKiri.setText("TAK");
        SekretHidungKanan.setText("TAK");
        SekretHidungKiri.setText("TAK");
        TersumbatKanan.setText("TAK");
        TersumbatKiri.setText("TAK");
        TumorHidungKanan.setText("TAK");
        TumorHidungKiri.setText("TAK");
        PilekKanan.setText("TAK");
        PilekKiri.setText("TAK");
        SakitHidungKanan.setText("TAK");
        SakitHidungKiri.setText("TAK");
        KorpusAlienumHidungKanan.setText("TAK");
        KorpusAlienumHidungKiri.setText("TAK");
        BersinKanan.setText("TAK");
        BersinKiri.setText("TAK");
        TenggorokRiak.setText("TAK");
        TenggorokGangguan.setText("TAK");
        TenggorokSuara.setText("TAK");
        TenggorokTumor.setText("TAK");
        TenggorokBatuk.setText("TAK");
        TenggorokKorpusAlienum.setText("TAK");
        TenggorokSesakNafas.setText("TAK");
        TenggorokKorpusAlienum.setText("TAK");
        KetLokalis.setText("");
        DaunTelingaKanan.setText("TAK");
        DaunTelingaKiri.setText("TAK");
        LiangTelingaKanan.setText("TAK");
        LiangTelingaKiri.setText("TAK");
        DischargeKanan.setText("TAK");
        DischargeKiri.setText("TAK");
        MembranTimpaniKanan.setText("TAK");
        MembranTimpaniKiri.setText("TAK");
        TumorrKanan.setText("TAK");
        TumorrKiri.setText("TAK");
        MastoidKanan.setText("TAK");
        MastoidKiri.setText("TAK");
        BerbisikKanan.setText("TAK");
        BerbisikKiri.setText("TAK");
        WeberKanan.setText("TAK");
        WeberKiri.setText("TAK");
        RinneKanan.setText("TAK");
        RinneKiri.setText("TAK");
        ScwabachKanan.setText("TAK");
        ScwabachKiri.setText("TAK");
        BOAKanan.setText("TAK");
        BOAKiri.setText("TAK");
        TympanometriKanan.setText("TAK");
        TympanometriKiri.setText("TAK");
        AudiometriKanan.setText("TAK");
        AudiometriKiri.setText("TAK");
        NadaMurniKanan.setText("TAK");
        NadaMurniKiri.setText("TAK");
        BERAKanan.setText("TAK");
        BERAKiri.setText("TAK");
        OAEKanan.setText("TAK");
        OAEKiri.setText("TAK");
        TesAlatKeseimbanganKanan.setText("TAK");
        TesAlatKeseimbanganKiri.setText("TAK");
        HidungLuarKanan.setText("TAK");
        HidungLuarKiri.setText("TAK");
        KavumNasiKanan.setText("TAK");
        KavumNasiKiri.setText("TAK");
        SeptumHidungKanan.setText("TAK");
        SeptumHidungKiri.setText("TAK");
        DischargeHidungKanan.setText("TAK");
        DischargeHidungKiri.setText("TAK");
        MukosaHidungKanan.setText("TAK");
        MukosaHidungKiri.setText("TAK");
        TumorHidungKanann.setText("TAK");
        TumorHidungKirii.setText("TAK");
        KonkaHidungKanan.setText("TAK");
        KonkaHidungKiri.setText("TAK");
        NasoEndoskopiKanan.setText("TAK");
        NasoEndoskopiKiri.setText("TAK");
        TenggorokDispenu.setText("TAK");
        TenggorokStridor.setText("TAK");
        TenggorokSianosis.setText("TAK");
        TenggorokSuaraa.setText("TAK");
        TenggorokMucosa.setText("TAK");
        TenggorokTonsil.setText("TAK");
        TenggorokDindingBelakang.setText("TAK");
        LaringEpiglotis.setText("TAK");
        LaringPlikaVokalis.setText("TAK");
        LaringArienoid.setText("TAK");
        LaringRimaglotis.setText("TAK");
        LaringPlikaVentrikuloris.setText("TAK");
        LaringEndoskopi.setText("TAK");
        KetLokalis.setText("");
        DiagnosaKerja.setText("");
        DiagnosaBanding.setText("");
        Terapi.setText("");
        KelenjerLimpeLeher.setText("");
        BolehPulang.setSelectedIndex(0);
        DTPTanggalKeluar.setDate(new Date());
        JamPulang.setText("");
        KontrolPoliklinik.setSelectedIndex(0);
        DTPTanggalKontrol.setDate(new Date());
        JamKontrol.setText("");
        DirawatDiRuang.setSelectedIndex(0);
        RuangLain.setText("");

        TglAsuhan.setDate(new Date());
        TabRawat.setSelectedIndex(0);
        Anamnesis.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Anamnesis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            Hubungan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            RPS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            RPD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            RPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            Nyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            StatusNutrisi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            Kondisi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            SekretKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            SekretKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            TuliKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            TuliKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            TumorKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            TumorKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            TinitusKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            TinitusKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            SakitKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            SakitKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            KorpusAlienumKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            KorpusAlienumKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            VertigoKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            VertigoKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            SekretHidungKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            SekretHidungKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            TersumbatKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            TersumbatKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            TumorHidungKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            TumorHidungKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            PilekKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            PilekKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            SakitHidungKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            SakitHidungKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            KorpusAlienumHidungKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            KorpusAlienumHidungKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            BersinKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            BersinKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            TenggorokRiak.setText(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            TenggorokGangguan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
            TenggorokSuara.setText(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            TenggorokTumor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            TenggorokBatuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
            TenggorokKorpusAlienum.setText(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            TenggorokSesakNafas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            KetLokalis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());;
            DaunTelingaKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            DaunTelingaKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());
            LiangTelingaKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            LiangTelingaKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            DischargeKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
            DischargeKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
            MembranTimpaniKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),66).toString());
            MembranTimpaniKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());
            TumorrKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
            TumorrKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
            MastoidKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),70).toString());
            MastoidKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),71).toString());
            BerbisikKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString());
            BerbisikKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),73).toString());
            WeberKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),74).toString());
            WeberKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),75).toString());
            RinneKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),76).toString());
            RinneKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),77).toString());
            ScwabachKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            ScwabachKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),79).toString());
            BOAKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),80).toString());
            BOAKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),81).toString());
            TympanometriKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),82).toString());
            TympanometriKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),83).toString());
            AudiometriKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),84).toString());
            AudiometriKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),85).toString());
            NadaMurniKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),86).toString());
            NadaMurniKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),87).toString());
            BERAKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),88).toString());
            BERAKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),89).toString());
            OAEKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),90).toString());
            OAEKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),91).toString());
            TesAlatKeseimbanganKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),92).toString());
            TesAlatKeseimbanganKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),93).toString());
            HidungLuarKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),94).toString());
            HidungLuarKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),95).toString());
            KavumNasiKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),96).toString());
            KavumNasiKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),97).toString());
            SeptumHidungKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),98).toString());
            SeptumHidungKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),99).toString());
            DischargeHidungKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),100).toString());
            DischargeHidungKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),101).toString());
            MukosaHidungKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),102).toString());
            MukosaHidungKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),103).toString());
            TumorHidungKanann.setText(tbObat.getValueAt(tbObat.getSelectedRow(),104).toString());
            TumorHidungKirii.setText(tbObat.getValueAt(tbObat.getSelectedRow(),105).toString());
            KonkaHidungKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),106).toString());
            KonkaHidungKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),107).toString());
            NasoEndoskopiKanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),108).toString());
            NasoEndoskopiKiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),109).toString());
            TenggorokDispenu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),110).toString());
            TenggorokStridor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),111).toString());
            TenggorokSianosis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),112).toString());
            TenggorokSuaraa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),113).toString());
            TenggorokMucosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),114).toString());
            TenggorokTonsil.setText(tbObat.getValueAt(tbObat.getSelectedRow(),115).toString());
            TenggorokDindingBelakang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),116).toString());
            LaringEpiglotis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),117).toString());
            LaringPlikaVokalis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),118).toString());
            LaringArienoid.setText(tbObat.getValueAt(tbObat.getSelectedRow(),119).toString());
            LaringRimaglotis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),120).toString());
            LaringPlikaVentrikuloris.setText(tbObat.getValueAt(tbObat.getSelectedRow(),121).toString());
            LaringEndoskopi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),122).toString());
            DiagnosaKerja.setText(tbObat.getValueAt(tbObat.getSelectedRow(),123).toString());
            DiagnosaBanding.setText(tbObat.getValueAt(tbObat.getSelectedRow(),124).toString());
            KelenjerLimpeLeher.setText(tbObat.getValueAt(tbObat.getSelectedRow(),125).toString());
            Terapi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),126).toString());
            BolehPulang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),127).toString());
            JamPulang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),129).toString());
            KontrolPoliklinik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),130).toString());
            JamKontrol.setText(tbObat.getValueAt(tbObat.getSelectedRow(),132).toString());
            DirawatDiRuang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),133).toString());
            RuangLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),134).toString());
            
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            Valid.SetTgl2(DTPTanggalKeluar,tbObat.getValueAt(tbObat.getSelectedRow(),128).toString());
            Valid.SetTgl2(DTPTanggalKontrol,tbObat.getValueAt(tbObat.getSelectedRow(),131).toString());
        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
 
    public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_awal_medis_ralan_tht());
        BtnHapus.setEnabled(akses.getpenilaian_awal_medis_ralan_tht());
        BtnEdit.setEnabled(akses.getpenilaian_awal_medis_ralan_tht());
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(dokter.tampil3(KdDokter.getText()));
            if(NmDokter.getText().equals("")){
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan Dokter...!!");
            }
        }            
    }
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_medis_ralan_tht where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        if(Sequel.mengedittf("pengkajian_medis_ranap_tht","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,anamnesis=?,hubungan=?,keluhan_utama=?,rps=?,rpd=?,rpo=?,alergi=?,td=?,nadi=?,rr=?,suhu=?,bb=?,tb=?,nyeri=?,status_nutrisi=?,kondisi=?,"
                + "sekret_telinga_kanan=?,sekret_telinga_kiri=?,tuli_telinga_kanan=?,tuli_telinga_kiri=?,tumor_telinga_kanan=?,tumor_telinga_kiri=?,tinitus_telinga_kanan=?,tinitus_telinga_kiri=?,sakit_telinga_kanan=?,sakit_telinga_kiri=?,"
                + "korpus_alienum_telinga_kanan=?,korpus_alienum_telinga_kiri=?,vertigo_telinga_kanan=?,vertigo_telinga_kiri=?,sekret_hidung_kanan=?,sekret_hidung_kiri=?,tersumbat_hidung_kanan=?,tersumbat_hidung_kiri=?,tumor_hidung_kanan=?,"
                + "tumor_hidung_kiri=?,pilek_hidung_kanan=?,pilek_hidung_kiri=?,sakit_hidung_kanan=?,sakit_hidung_kiri=?,korpus_alienum_hidung_kanan=?,korpus_alienum_hidung_kiri=?,bersin_hidung_kanan=?,bersin_hidung_kiri=?,riak_tenggorok=?,"
                + "gangguan_tenggorok=?,suara_tenggorok=?,tumor_tenggorok=?,batuk_tenggorok=?,korpus_alienum_tenggorok=?,sesak_nafas_tenggorok=?,ket_lokalis=?,daun_telinga_kanan=?,daun_telinga_kiri=?,liang_telinga_kanan=?,liang_telinga_kiri=?,"
                + "discharge_telinga_kanan=?,discharge_telinga_kiri=?,membrana_timpani_telinga_kanan=?,membrana_timpani_telinga_kiri=?,tumor_telinga_kanann=?,tumor_telinga_kirii=?,mastoid_telinga_kanan=?,mastoid_telinga_kiri=?,berbisik_telinga_kanan=?,"
                + "berbisik_telinga_kiri=?,weber_telinga_kanan=?,weber_telinga_kiri=?,rinne_telinga_kanan=?,rinne_telinga_kiri=?,scwabach_telinga_kanan=?,scwabach_telinga_kiri=?,boa_telinga_kanan=?,boa_telinga_kiri=?,tympanometri_telinga_kanan=?,"
                + "tympanometri_telinga_kiri=?,audiometri_telinga_kanan=?,audiometri_telinga_kiri=?,nada_murni_telinga_kanan=?,nada_murni_telinga_kiri=?,bera_telinga_kanan=?,bera_telinga_kiri=?,oae_telinga_kanan=?,oae_telinga_kiri=?,seimbang_kanan=?,"
                + "seimbang_kiri=?,hidung_luar_kanan=?,hidung_luar_kiri=?,kavum_nasi_kanan=?,kavum_nasi_kiri=?,septum_kanan=?,septum_kiri=?,discharge_hidung_kanan=?,discharge_hidung_kiri=?,mukosa_hidung_kanan=?,mukosa_hidung_kiri=?,tumor_hidung_kanann=?,"
                + "tumor_hidung_kirii=?,konka_kanan=?,konka_kiri=?,naso_endoskopi_kanan=?,naso_endoskopi_kiri=?,dispenu_tenggorok=?,stridor_tenggorok=?,sianosis_tenggorok=?,suara_tenggorok=?,mucosa_tenggorok=?,tonsil_tenggorok=?,dinding_belakang_tenggorok=?,"
                + "epiglotis=?,plika_vokalis=?,aritenoid=?,rimaglotis=?,plika_ventrikuloris=?,endoskopi=?,diagnosa_kerja=?,diagnosa_pembanding=?,kelenjar_limpe_leher=?,terapi=?,boleh_pulang=?,tgl_keluar=?,jam_keluar=?,kontrol=?,tgl_kontrol=?,jam_kontrol=?,"
                + "dirawat_ruang=?,ruang_lain=?",131,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),
                KeluhanUtama.getText(),RPS.getText(),RPD.getText(),RPO.getText(),Alergi.getText(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),BB.getText(),TB.getText(),Nyeri.getText(),StatusNutrisi.getText(),
                Kondisi.getText(),SekretKanan.getText(),SekretKiri.getText(),TuliKanan.getText(),TuliKiri.getText(),TumorKanan.getText(),TumorKiri.getText(),TinitusKanan.getText(),TinitusKiri.getText(),SakitKanan.getText(),
                SakitKiri.getText(),KorpusAlienumKanan.getText(),KorpusAlienumKiri.getText(),VertigoKanan.getText(),VertigoKiri.getText(),SekretHidungKanan.getText(),SekretHidungKiri.getText(),TersumbatKanan.getText(),TersumbatKiri.getText(),
                TumorHidungKanan.getText(),TumorHidungKiri.getText(),PilekKanan.getText(),PilekKiri.getText(),SakitHidungKanan.getText(),SakitHidungKiri.getText(),KorpusAlienumHidungKanan.getText(),KorpusAlienumHidungKiri.getText(),
                BersinKanan.getText(),BersinKiri.getText(),TenggorokRiak.getText(),TenggorokGangguan.getText(),TenggorokSuara.getText(),TenggorokTumor.getText(),TenggorokBatuk.getText(),TenggorokKorpusAlienum.getText(),TenggorokSesakNafas.getText(),
                KetLokalis.getText(),DaunTelingaKanan.getText(),DaunTelingaKiri.getText(),LiangTelingaKanan.getText(),LiangTelingaKiri.getText(),DischargeKanan.getText(),DischargeKiri.getText(),MembranTimpaniKanan.getText(),MembranTimpaniKiri.getText(),
                TumorrKanan.getText(),TumorrKiri.getText(),MastoidKanan.getText(),MastoidKiri.getText(),BerbisikKanan.getText(),BerbisikKiri.getText(),WeberKanan.getText(),WeberKiri.getText(),RinneKanan.getText(),RinneKiri.getText(),ScwabachKanan.getText(),
                ScwabachKiri.getText(),BOAKanan.getText(),BOAKiri.getText(),TympanometriKanan.getText(),TympanometriKiri.getText(),AudiometriKanan.getText(),AudiometriKiri.getText(),NadaMurniKanan.getText(),NadaMurniKiri.getText(),BERAKanan.getText(),BERAKiri.getText(),
                OAEKanan.getText(),OAEKiri.getText(),TesAlatKeseimbanganKanan.getText(),TesAlatKeseimbanganKiri.getText(),HidungLuarKanan.getText(),HidungLuarKiri.getText(),KavumNasiKanan.getText(),KavumNasiKiri.getText(),SeptumHidungKanan.getText(),SeptumHidungKiri.getText(),
                DischargeHidungKanan.getText(),DischargeHidungKiri.getText(),MukosaHidungKanan.getText(),MukosaHidungKiri.getText(),TumorHidungKanann.getText(),TumorHidungKirii.getText(),KonkaHidungKanan.getText(),KonkaHidungKiri.getText(),NasoEndoskopiKanan.getText(),NasoEndoskopiKiri.getText(),
                TenggorokDispenu.getText(),TenggorokStridor.getText(),TenggorokSianosis.getText(),TenggorokSuaraa.getText(),TenggorokMucosa.getText(),TenggorokTonsil.getText(),TenggorokDindingBelakang.getText(),LaringEpiglotis.getText(),LaringPlikaVokalis.getText(),LaringArienoid.getText(),
                LaringRimaglotis.getText(),LaringPlikaVentrikuloris.getText(),LaringEndoskopi.getText(),DiagnosaKerja.getText(),DiagnosaBanding.getText(),KelenjerLimpeLeher.getText(),Terapi.getText(),BolehPulang.getSelectedItem().toString(),Valid.SetTgl(DTPTanggalKeluar.getSelectedItem()+""),
                JamPulang.getText(),KontrolPoliklinik.getSelectedItem().toString(),Valid.SetTgl(DTPTanggalKontrol.getSelectedItem()+""),JamKontrol.getText(),DirawatDiRuang.getSelectedItem().toString(),RuangLain.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
