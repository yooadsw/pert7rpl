/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pert6_aop.view;

/**
 *
 * @author Aditya Ananta
 */
import com.mycompany.pert6_aop.controller.MahasiswaController;
import com.mycompany.pert6_aop.model.ModelMahasiswa;
import com.mycompany.pert6_aop.model.ModelTabelMahasiswa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GradientPaint;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component; 
import java.awt.EventQueue; 


@Component
public class MahasiswaView extends javax.swing.JFrame {

    private MahasiswaController controller;

    // --- 1. DEKLARASI VARIABEL SESUAI PERMINTAAN ---
    private javax.swing.JButton buangButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JTextField ipkField;
    private javax.swing.JTextField namaField;
    private javax.swing.JTextField npmField;
    private javax.swing.JTextField semesterField;

    // Komponen UI tambahan untuk layout
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JPanel panelTabel;

    // --- 2. KODE DARI INSTRUKSI ANDA ---
    
    @Autowired
    public MahasiswaView(MahasiswaController controller) {
        this.controller = controller;
        initCustomComponents(); // Metode desain Anda
        loadMahasiswaTable();

        // Pengaturan dasar JFrame
        setTitle("Manajemen Data Mahasiswa (Versi Spring)");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    private MahasiswaView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void loadMahasiswaTable() {
        // Ambil data dari controller
        List<ModelMahasiswa> listMahasiswa = controller.getAllMahasiswa();

        // Buat model tabel kustom dengan data mahasiswa
        ModelTabelMahasiswa tableModel = new ModelTabelMahasiswa(listMahasiswa);

        // Set model pada JTable
        dataTable.setModel(tableModel);
    }
    
    // --- AKHIR KODE DARI INSTRUKSI ANDA ---
    

    /**
     * Metode ini menggantikan initComponents() yang dibuat oleh NetBeans GUI Builder.
     * Di sinilah seluruh desain elegan dan gradasi dibuat.
     */
    private void initCustomComponents() {
        
        // Font standar untuk tampilan profesional
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 12);
        
        // Warna
        Color panelColor = new Color(245, 248, 251); // Putih kebiruan
        Color labelColor = new Color(50, 50, 70);
        Color buttonColor = new Color(0, 123, 255); // Biru
        Color buttonColorHover = new Color(0, 105, 217);
        Color deleteColor = new Color(220, 53, 69); // Merah
        Color deleteColorHover = new Color(200, 35, 51);
        Color refreshColor = new Color(23, 162, 184); // Cyan
        Color refreshColorHover = new Color(19, 132, 150);

        // Panel Utama dengan Gradasi
        // Ini adalah panel kustom yang bisa menggambar gradasi
        class GradientPanel extends JPanel {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                // Gradasi dari (biru muda) ke (putih)
                Color color1 = new Color(230, 240, 255);
                Color color2 = Color.WHITE;
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        }
        
        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout(15, 15)); // Jarak antar komponen
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        this.setContentPane(mainPanel);

        // === Panel Form (Kiri) ===
        panelForm = new JPanel(new GridBagLayout());
        panelForm.setOpaque(false); // Transparan agar gradasi terlihat
        panelForm.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Input Data Mahasiswa",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 16), labelColor));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Padding antar sel
        gbc.anchor = GridBagConstraints.LINE_START; // Rata kiri

        // Label NPM
        jLabel1 = new JLabel("NPM");
        jLabel1.setFont(labelFont);
        jLabel1.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(jLabel1, gbc);

        // Field NPM
        npmField = new JTextField(20); // Lebar field
        npmField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Memenuhi lebar
        gbc.weightx = 1.0; // Mengisi ruang horizontal
        panelForm.add(npmField, gbc);

        // Label NAMA
        jLabel2 = new JLabel("NAMA");
        jLabel2.setFont(labelFont);
        jLabel2.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelForm.add(jLabel2, gbc);

        // Field NAMA
        namaField = new JTextField(20);
        namaField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelForm.add(namaField, gbc);

        // Label SEMESTER
        jLabel3 = new JLabel("SEMESTER");
        jLabel3.setFont(labelFont);
        jLabel3.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelForm.add(jLabel3, gbc);

        // Field SEMESTER
        semesterField = new JTextField(20);
        semesterField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelForm.add(semesterField, gbc);

        // Label IPK
        jLabel4 = new JLabel("IPK");
        jLabel4.setFont(labelFont);
        jLabel4.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelForm.add(jLabel4, gbc);

        // Field IPK
        ipkField = new JTextField(20);
        ipkField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelForm.add(ipkField, gbc);

        // === Panel Tombol (di bawah Form) ===
        panelTombol = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelTombol.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelForm.add(panelTombol, gbc);

        simpanButton = new JButton("Simpan");
        styleButton(simpanButton, buttonFont, buttonColor, buttonColorHover);
        
        refreshButton = new JButton("Refresh");
        styleButton(refreshButton, buttonFont, refreshColor, refreshColorHover);

        buangButton = new JButton("Buang");
        styleButton(buangButton, buttonFont, deleteColor, deleteColorHover);

        panelTombol.add(simpanButton);
        panelTombol.add(refreshButton);
        panelTombol.add(buangButton);
        
        // Menambahkan panel form ke panel utama
        mainPanel.add(panelForm, BorderLayout.NORTH);

        // === Panel Tabel (Kanan/Tengah) ===
        panelTabel = new JPanel(new BorderLayout());
        panelTabel.setOpaque(false);
        panelTabel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Data Mahasiswa",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 16), labelColor));
        
        jScrollPane1 = new JScrollPane();
        dataTable = new JTable();
        
        // Styling Tabel
        dataTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        dataTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        dataTable.getTableHeader().setBackground(new Color(230, 230, 230));
        dataTable.setRowHeight(25);
        dataTable.setGridColor(new Color(220, 220, 220));
        dataTable.setSelectionBackground(new Color(0, 123, 255));
        dataTable.setSelectionForeground(Color.WHITE);
        
        jScrollPane1.setViewportView(dataTable);
        jScrollPane1.setPreferredSize(new Dimension(600, 300)); // Ukuran tabel
        
        panelTabel.add(jScrollPane1, BorderLayout.CENTER);
        
        // Menambahkan panel tabel ke panel utama
        mainPanel.add(panelTabel, BorderLayout.CENTER);

        // --- 3. KODE EVENT LISTENER TOMBOL ---
        
        // A. Tombol Simpan
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Kode dari Anda
                String npm = getNpmField().getText();
                String nama = getNamaField().getText();
                int semester = Integer.parseInt(getSemesterField().getText());
                float ipk = Float.parseFloat(getIpkField().getText());
                ModelMahasiswa mahasiswa = new ModelMahasiswa(0, npm, nama, semester, ipk);
                System.out.println(mahasiswa.getIpk());
                System.out.println(mahasiswa.getNama());
                System.out.println(mahasiswa.getSemester());
                System.out.println(mahasiswa.getNpm());

                controller.addMahasiswa(mahasiswa);
                loadMahasiswaTable();
                
                // Tambahan: Bersihkan form setelah simpan
                getNpmField().setText("");
                getNamaField().setText("");
                getSemesterField().setText("");
                getIpkField().setText("");
            }
        });

        // B. Tombol Refresh
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Kode dari Anda
                loadMahasiswaTable();
            }
        });

        // C. Tombol Buang
        buangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Kode dari Anda
                
                // Membuat JTextField untuk memasukkan ID
                JTextField idField = new JTextField(10);
                idField.setFont(fieldFont);

                // Membuat panel untuk menampung JTextField
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel promptLabel = new JLabel("Masukkan ID yang ingin dihapus:");
                promptLabel.setFont(labelFont);
                panel.add(promptLabel);
                panel.add(idField);

                // Menampilkan dialog box dengan JTextField, tombol OK, dan Cancel
                int result = JOptionPane.showConfirmDialog(MahasiswaView.this, panel,
                        "Hapus Mahasiswa", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                // Jika tombol OK ditekan
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Mengambil input ID dan memanggil metode deleteMhs
                        int id = Integer.parseInt(idField.getText());
                        controller.deleteMahasiswa(id);
                        JOptionPane.showMessageDialog(MahasiswaView.this, "Data berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                        loadMahasiswaTable();
                    } catch (NumberFormatException e) {
                        // Menangani error jika ID yang dimasukkan bukan angka
                        JOptionPane.showMessageDialog(MahasiswaView.this, "ID harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    
    /**
     * Helper method untuk styling tombol
     */
    private void styleButton(JButton button, Font font, Color background, Color hover) {
        button.setFont(font);
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        // Efek hover sederhana
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(background);
            }
        });
    }

    // --- GETTER UNTUK KODE ANDA ---
    // Kode Anda memanggil getNpmField(), jadi kita perlu membuatnya
    
    public JTextField getNpmField() {
        return npmField;
    }

    public JTextField getNamaField() {
        return namaField;
    }

    public JTextField getSemesterField() {
        return semesterField;
    }

    public JTextField getIpkField() {
        return ipkField;
    }

    // --- MAIN METHOD UNTUK MENJALANKAN ---
    // (Hanya untuk tes, Anda bisa hapus jika menjalankan dari file lain)
//    public static void main(String args[]) {
//        /* Set Look and Feel Windows untuk tampilan yang lebih modern */
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MahasiswaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//
//        /* Buat dan tampilkan form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                // Buat controller "palsu" untuk tes
//                MahasiswaController mockController = new MahasiswaController(null); 
//                new MahasiswaView(mockController).setVisible(true);
//            }
//        });
//    }
}
