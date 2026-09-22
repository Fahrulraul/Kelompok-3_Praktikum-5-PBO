import superclassdansubclass.Reguler;
import superclassdansubclass.Premium;
import superclassdansubclass.Paketfoto;

public class Main {
    public static void main(String[] args) {
        Paketfoto reguler = new Reguler(1);
        Paketfoto premium = new Premium(2);

        reguler.tampilkanDetail();
        System.out.println();
        premium.tampilkanDetail();
    }
}