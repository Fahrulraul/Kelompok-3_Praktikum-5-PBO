/**
 * Abstract Class Perangkatphotobooth
 * Berfungsi sebagai kerangka dasar (blueprint) untuk semua perangkat keras (hardware)
 * yang digunakan dalam sistem photobooth (seperti kamera, printer, lighting, dll).
 * 
 * Modul Praktikum 5 PBO - Kelompok 3
 */
public abstract class Perangkatphotobooth {
    // Atribut perangkat (Encapsulation)
    protected String namaPerangkat;
    protected String merek;
    protected String status; // Contoh: "Mati", "Standby", "Aktif", "Error"
    protected double konsumsiDaya; // Dalam satuan Watt

    // Constructor Default
    public Perangkatphotobooth() {
        this.namaPerangkat = "Perangkat Photobooth";
        this.merek = "Generic";
        this.status = "Mati";
        this.konsumsiDaya = 0.0;
    }

    // Constructor Berparameter
    public Perangkatphotobooth(String namaPerangkat, String merek, double konsumsiDaya) {
        this.namaPerangkat = namaPerangkat;
        this.merek = merek;
        this.status = "Mati"; // Status awal perangkat dalam kondisi mati
        this.konsumsiDaya = konsumsiDaya;
    }

    // =========================================================================
    // ABSTRACT METHODS
    // Wajib diimplementasikan oleh setiap subclass turunan Perangkatphotobooth
    // =========================================================================

    /**
     * Menyalakan perangkat photobooth.
     */
    public abstract void nyalakan();

    /**
     * Mematikan perangkat photobooth.
     */
    public abstract void matikan();

    /**
     * Mengoperasikan fungsi utama dari perangkat.
     */
    public abstract void operasikan();

    /**
     * Melakukan pengecekan / diagnostik kondisi perangkat.
     */
    public abstract void periksaKondisi();

    // =========================================================================
    // CONCRETE METHODS
    // Method yang memiliki implementasi dan dapat langsung digunakan atau di-override
    // =========================================================================

    /**
     * Merestart perangkat (mematikan lalu menyalakan kembali).
     * Contoh penerapan reuse logic / template flow pada abstract class.
     */
    public void restart() {
        System.out.println("\n[Sistem] Merestart perangkat: " + this.namaPerangkat + "...");
        matikan();
        try {
            Thread.sleep(800); // Simulasi jeda restart
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        nyalakan();
        System.out.println("[Sistem] Perangkat " + this.namaPerangkat + " berhasil direstart.\n");
    }

    /**
     * Menampilkan informasi umum mengenai perangkat photobooth.
     */
    public void tampilkanInfo() {
        System.out.println("==================================================");
        System.out.println("            INFORMASI PERANGKAT PHOTOBOOTH        ");
        System.out.println("==================================================");
        System.out.println("Nama Perangkat : " + this.namaPerangkat);
        System.out.println("Merek / Brand  : " + this.merek);
        System.out.println("Status Operasi : " + this.status);
        System.out.println("Konsumsi Daya  : " + this.konsumsiDaya + " Watt");
    }

    // =========================================================================
    // GETTER & SETTER (Encapsulation)
    // =========================================================================

    public String getNamaPerangkat() {
        return namaPerangkat;
    }

    public void setNamaPerangkat(String namaPerangkat) {
        this.namaPerangkat = namaPerangkat;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getKonsumsiDaya() {
        return konsumsiDaya;
    }

    public void setKonsumsiDaya(double konsumsiDaya) {
        if (konsumsiDaya >= 0) {
            this.konsumsiDaya = konsumsiDaya;
        } else {
            System.out.println("[Peringatan] Konsumsi daya tidak boleh bernilai negatif!");
        }
    }
}
