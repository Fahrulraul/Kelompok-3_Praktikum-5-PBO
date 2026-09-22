package superclassdansubclass;

public class Paketfoto {
    private int id;
    private int waktu;       
    private int harga;
    private int jumlahFoto;

    public Paketfoto(int id, int waktu, int harga, int jumlahFoto) {
        this.id = id;
        this.waktu = waktu;
        this.harga = harga;
        this.jumlahFoto = jumlahFoto;
    }

    public int getId() { return id; }
    public int getWaktu() { return waktu; }
    public int getHarga() { return harga; }
    public int getJumlahFoto() { return jumlahFoto; }

    public void tampilkanDetail() {
        System.out.println("=== Detail Paket ===");
        System.out.println("ID Paket   : " + id);
        System.out.println("Waktu Sesi : " + waktu + " menit");
        System.out.println("Harga      : Rp" + harga);
        System.out.println("Jumlah Foto: " + jumlahFoto + " foto");
    }
}