import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        fonctionsBD db=new fonctionsBD();
        Connection conn=db.connect_to_db("defaultdb","avnadmin","AVNS_O7FNZcgdSruUsgcp3SB");
    }
}