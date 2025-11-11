package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Tanaman;

public class HomeController implements Initializable {

    // --- Deklarasi Komponen UI ---
    @FXML private TextField tfNama;
    @FXML private DatePicker dpTanggalDisiram;
    @FXML private TextField tfLokasi;
    @FXML private Button btnTambah;
    @FXML private TableView<Tanaman> tvTabel;
    @FXML private TableColumn<Tanaman, String> tcNama;
    @FXML private TableColumn<Tanaman, LocalDate> tcTanggalDisiram;
    @FXML private TableColumn<Tanaman, String> tcLokasi;
    @FXML private Button btnEdit;
    @FXML private Button btnhapus;

    private ObservableList<Tanaman> dataTanaman;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataTanaman = FXCollections.observableArrayList();
        
        // Contoh data awal
        dataTanaman.add(new Tanaman("Mawar Merah", LocalDate.of(2025, 11, 10), "Taman Depan"));
        dataTanaman.add(new Tanaman("Kaktus Koboi", LocalDate.of(2025, 10, 20), "Meja Kantor"));
        
        // Menghubungkan properti Model ke TableColumn
        tcNama.setCellValueFactory(cellData -> cellData.getValue().namaTanamanProperty());
        tcTanggalDisiram.setCellValueFactory(cellData -> cellData.getValue().tanggalDisiramProperty());
        tcLokasi.setCellValueFactory(cellData -> cellData.getValue().lokasiProperty());

        tvTabel.setItems(dataTanaman);

        // Listener untuk mengisi field saat item di tabel dipilih
        tvTabel.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> tampilkanDetailTanaman(newValue));
    }
    
    /**
     * Menampilkan detail Tanaman yang dipilih ke dalam field input
     */
    private void tampilkanDetailTanaman(Tanaman tanaman) {
        if (tanaman != null) {
            tfNama.setText(tanaman.getNamaTanaman());
            dpTanggalDisiram.setValue(tanaman.getTanggalDisiram());
            tfLokasi.setText(tanaman.getLokasi());
        } else {
            BersihkanField();
        }
    }

    /**
     * Membersihkan semua field input.
     */
    private void BersihkanField() {
        tfNama.setText("");
        dpTanggalDisiram.setValue(null);
        tfLokasi.setText("");
    }

    // --- Metode Aksi FXML ---

    @FXML
    private void TambahTanaman(ActionEvent event) {
        String nama = tfNama.getText();
        LocalDate tanggal = dpTanggalDisiram.getValue();
        String lokasi = tfLokasi.getText();

        if (nama.isEmpty() || tanggal == null || lokasi.isEmpty()) {
            tampilkanPesanError("Semua field harus diisi!");
            return;
        }

        Tanaman newTanaman = new Tanaman(nama, tanggal, lokasi);
        dataTanaman.add(newTanaman);
        BersihkanField(); // Field dibersihkan setelah Tambah
    }

    @FXML
    private void EditTanaman(ActionEvent event) {
        Tanaman selectedTanaman = tvTabel.getSelectionModel().getSelectedItem();

        if (selectedTanaman != null) {
            String nama = tfNama.getText();
            LocalDate tanggal = dpTanggalDisiram.getValue();
            String lokasi = tfLokasi.getText();
            
            if (nama.isEmpty() || tanggal == null || lokasi.isEmpty()) {
                tampilkanPesanError("Semua field harus diisi!");
                return;
            }

            // Perbarui data objek yang dipilih
            selectedTanaman.setNamaTanaman(nama);
            selectedTanaman.setTanggalDisiram(tanggal);
            selectedTanaman.setLokasi(lokasi);
            
            tvTabel.refresh();
            // PANGGILAN BersihkanField() DIHAPUS agar data tetap ada di input field
            tampilkanPesanInformasi("Data tanaman berhasil diubah.");

        } else {
            tampilkanPesanError("Pilih tanaman di tabel untuk diubah.");
        }
    }

    @FXML
    private void HapusTanaman(ActionEvent event) {
        int selectedIndex = tvTabel.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            dataTanaman.remove(selectedIndex);
            BersihkanField();
        } else {
            tampilkanPesanError("Pilih tanaman di tabel untuk dihapus.");
        }
    }
    
    /**
     * Fungsi helper untuk menampilkan dialog error
     */
    private void tampilkanPesanError(String pesan) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Kesalahan Input");
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }
    
    /**
     * Fungsi helper untuk menampilkan dialog informasi
     */
    private void tampilkanPesanInformasi(String pesan) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }
}