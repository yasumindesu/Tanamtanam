package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class Tanaman {
    private final StringProperty namaTanaman;
    private final ObjectProperty<LocalDate> tanggalDisiram;
    private final StringProperty lokasi;

    /**
     * Konstruktor
     * @param namaTanaman Nama tanaman
     * @param tanggalDisiram Tanggal terakhir tanaman disiram
     * @param lokasi Lokasi penempatan tanaman
     */
    public Tanaman(String namaTanaman, LocalDate tanggalDisiram, String lokasi) {
        this.namaTanaman = new SimpleStringProperty(namaTanaman);
        this.tanggalDisiram = new SimpleObjectProperty<>(tanggalDisiram);
        this.lokasi = new SimpleStringProperty(lokasi);
    }

    // --- Getter untuk properti (Digunakan di HomeController.java) ---
    
    public StringProperty namaTanamanProperty() {
        return namaTanaman;
    }

    public ObjectProperty<LocalDate> tanggalDisiramProperty() {
        return tanggalDisiram;
    }

    public StringProperty lokasiProperty() {
        return lokasi;
    }

    // --- Getter dan Setter biasa (Digunakan di HomeController.java) ---

    public String getNamaTanaman() {
        return namaTanaman.get();
    }

    public void setNamaTanaman(String namaTanaman) {
        this.namaTanaman.set(namaTanaman);
    }

    public LocalDate getTanggalDisiram() {
        return tanggalDisiram.get();
    }

    public void setTanggalDisiram(LocalDate tanggalDisiram) {
        this.tanggalDisiram.set(tanggalDisiram);
    }

    public String getLokasi() {
        return lokasi.get();
    }

    public void setLokasi(String lokasi) {
        this.lokasi.set(lokasi);
    }
}