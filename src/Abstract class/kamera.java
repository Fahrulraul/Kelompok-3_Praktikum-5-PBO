/**
 * Class kamera merupakan subclass yang mewarisi abstract class Perangkatphotobooth.
 * Mengimplementasikan seluruh abstract method dari parent class serta menyediakan
 * atribut dan perilaku spesifik untuk kamera photobooth.
 * 
 * Modul Praktikum 5 PBO - Kelompok 3
 */
public class kamera extends Perangkatphotobooth {
    // Atribut Khusus Kamera (Encapsulation)
    private String resolusi;           // Contoh: "24.1 MP (4K UHD)"
    private String jenisLensa;         // Contoh: "Canon EF-S 18-55mm IS STM"
    private int kapasitasBaterai;      // Persentase baterai (0 - 100%)
    private boolean flashAktif;        // Status lampu kilat / blitz
    private int jumlahFotoDiambil;     // Counter jumlah foto yang sudah diambil
    private String modePemotretan;     // Contoh: "Auto Photobooth", "Burst 4-Grid"

    // Constructor Default
    public kamera() {
        super("Kamera Utama Photobooth", "Canon DSLR", 15.0);
        this.resolusi = "24.1 MP";
        this.jenisLensa = "18-55mm f/3.5-5.6";
        this.kapasitasBaterai = 100;
        this.flashAktif = true;
        this.jumlahFotoDiambil = 0;
        this.modePemotretan = "Auto Photobooth";
    }

    // Constructor Berparameter
    public kamera(String namaPerangkat, String merek, double konsumsiDaya, 
                  String resolusi, String jenisLensa, int kapasitasBaterai) {
        super(namaPerangkat, merek, konsumsiDaya);
        this.resolusi = resolusi;
        this.jenisLensa = jenisLensa;
        setKapasitasBaterai(kapasitasBaterai);
        this.flashAktif = true;
        this.jumlahFotoDiambil = 0;
        this.modePemotretan = "Auto Photobooth";
    }

    // =========================================================================
    // IMPLEMENTASI ABSTRACT METHODS DARI SUPERCLASS
    // =========================================================================

    @Override
    public void nyalakan() {
        if (kapasitasBaterai <= 5) {
            this.status = "Error (Low Battery)";
            System.out.println("[Kamera] Gagal menyala! Baterai " + this.namaPerangkat + " kritis (" + kapasitasBaterai + "%). Harap isi daya!");
            return;
        }

        this.status = "Aktif";
        System.out.println("[Kamera] " + this.namaPerangkat + " (" + this.merek + ") berhasil dinyalakan.");
        System.out.println("[Kamera] Sensor gambar aktif, kalibrasi lensa " + this.jenisLensa + " selesai.");
        System.out.println("[Kamera] Kamera siap digunakan pada mode: " + this.modePemotretan);
    }

    @Override
    public void matikan() {
        this.status = "Mati";
        System.out.println("[Kamera] " + this.namaPerangkat + " sedang dinonaktifkan...");
        System.out.println("[Kamera] Menutup shutter dan memposisikan lensa ke park position.");
        System.out.println("[Kamera] Perangkat kini telah MATI.");
    }

    @Override
    public void operasikan() {
        // Operasi dasar kamera photobooth: mengambil 1 foto
        ambilFoto();
    }

    @Override
    public void periksaKondisi() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("         DIAGNOSTIK SISTEM KAMERA PHOTOBOOTH      ");
        System.out.println("--------------------------------------------------");
        System.out.println("Nama Perangkat     : " + this.namaPerangkat);
        System.out.println("Status Perangkat   : " + this.status);
        System.out.println("Persentase Baterai : " + this.kapasitasBaterai + "%");
        System.out.println("Lensa Terpasang    : " + this.jenisLensa);
        System.out.println("Lampu Flash/Blitz  : " + (this.flashAktif ? "Aktif (Siap)" : "Nonaktif"));
        System.out.println("Total Foto Sesi    : " + this.jumlahFotoDiambil + " jepretan");

        if (kapasitasBaterai < 20) {
            System.out.println("[Peringatan] Baterai rendah! Segera pasang adaptor daya AC.");
        } else {
            System.out.println("[Diagnostik] Kondisi sistem kamera prima dan siap beroperasi.");
        }
        System.out.println("--------------------------------------------------\n");
    }

    // =========================================================================
    // METHOD KHUSUS KAMERA
    // =========================================================================

    /**
     * Mengambil 1 foto dengan kamera photobooth.
     */
    public void ambilFoto() {
        if (!"Aktif".equalsIgnoreCase(this.status)) {
            System.out.println("[Kamera] Gagal memotret! Perangkat berstatus '" + this.status + "'. Nyalakan kamera terlebih dahulu.");
            return;
        }

        if (kapasitasBaterai <= 2) {
            System.out.println("[Kamera] Baterai habis saat memotret! Kamera mati otomatis.");
            matikan();
            return;
        }

        System.out.println("\n[Hitung Mundur: 3... 2... 1... *SENYUM!*]");
        if (flashAktif) {
            System.out.println("[Flash Photobooth] *BLITZ!* Flash menyala menerangi subjek foto.");
        }
        System.out.println("[Kamera] *CEKREK!* Foto berhasil ditangkap pada resolusi " + this.resolusi + ".");

        this.jumlahFotoDiambil++;
        this.kapasitasBaterai -= 2; // Simulasi pemakaian daya per pemotretan
        if (this.kapasitasBaterai < 0) this.kapasitasBaterai = 0;

        System.out.println("[Kamera] Foto tersimpan ke kartu memori. Total foto sesi ini: " + this.jumlahFotoDiambil);
        System.out.println("[Kamera] Sisa baterai: " + this.kapasitasBaterai + "%");
    }

    /**
     * Overloading method ambilFoto untuk mengambil beberapa foto sekaligus (misal sesi 4-pose).
     * @param jumlahFrame Banyaknya pose foto yang akan diambil
     */
    public void ambilFoto(int jumlahFrame) {
        if (!"Aktif".equalsIgnoreCase(this.status)) {
            System.out.println("[Kamera] Gagal memulai sesi beruntun! Kamera sedang tidak aktif.");
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("   MEMULAI SESI PEMOTRETAN OTOMATIS (" + jumlahFrame + " POSE)");
        System.out.println("==================================================");
        for (int i = 1; i <= jumlahFrame; i++) {
            System.out.println("\n>> Mengambil Frame Pose #" + i + " dari " + jumlahFrame);
            ambilFoto();
            try {
                Thread.sleep(1000); // Simulasi jeda antar pose
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\n=== SESI SELESAI (" + jumlahFrame + " FOTO BERHASIL DIPROSES) ===\n");
    }

    /**
     * Mengganti jenis lensa kamera.
     */
    public void gantiLensa(String lensaBaru) {
        System.out.println("[Kamera] Melepaskan lensa: " + this.jenisLensa);
        this.jenisLensa = lensaBaru;
        System.out.println("[Kamera] Memasang lensa baru: " + this.jenisLensa + " (Berhasil)");
    }

    /**
     * Mengisi ulang baterai kamera.
     */
    public void isiDayaBaterai(int penambahan) {
        this.kapasitasBaterai += penambahan;
        if (this.kapasitasBaterai > 100) {
            this.kapasitasBaterai = 100;
        }
        System.out.println("[Kamera] Daya terisi (" + penambahan + "%). Baterai sekarang: " + this.kapasitasBaterai + "%");
    }

    // =========================================================================
    // OVERRIDE METHOD DARI SUPERCLASS
    // =========================================================================

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Resolusi Sensor: " + this.resolusi);
        System.out.println("Lensa Terpasang: " + this.jenisLensa);
        System.out.println("Sisa Baterai   : " + this.kapasitasBaterai + "%");
        System.out.println("Status Flash   : " + (this.flashAktif ? "Aktif (Auto-Sync)" : "Nonaktif"));
        System.out.println("Mode Potret    : " + this.modePemotretan);
        System.out.println("Total Jepretan : " + this.jumlahFotoDiambil + " frame foto");
        System.out.println("==================================================");
    }

    // =========================================================================
    // GETTER & SETTER KHUSUS KAMERA
    // =========================================================================

    public String getResolusi() {
        return resolusi;
    }

    public void setResolusi(String resolusi) {
        this.resolusi = resolusi;
    }

    public String getJenisLensa() {
        return jenisLensa;
    }

    public void setJenisLensa(String jenisLensa) {
        this.jenisLensa = jenisLensa;
    }

    public int getKapasitasBaterai() {
        return kapasitasBaterai;
    }

    public void setKapasitasBaterai(int kapasitasBaterai) {
        if (kapasitasBaterai < 0) {
            this.kapasitasBaterai = 0;
        } else if (kapasitasBaterai > 100) {
            this.kapasitasBaterai = 100;
        } else {
            this.kapasitasBaterai = kapasitasBaterai;
        }
    }

    public boolean isFlashAktif() {
        return flashAktif;
    }

    public void setFlashAktif(boolean flashAktif) {
        this.flashAktif = flashAktif;
        System.out.println("[Kamera] Pengaturan flash diubah menjadi: " + (flashAktif ? "AKTIF" : "NONAKTIF"));
    }

    public int getJumlahFotoDiambil() {
        return jumlahFotoDiambil;
    }

    public String getModePemotretan() {
        return modePemotretan;
    }

    public void setModePemotretan(String modePemotretan) {
        this.modePemotretan = modePemotretan;
    }
}
