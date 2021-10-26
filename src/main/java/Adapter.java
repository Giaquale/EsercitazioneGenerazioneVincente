import java.sql.*;

public class Adapter {
    private Connection con = null;
    private Statement stm = null;

    public void connection(){

        try {
            con = DriverManager.getConnection("jdbc:sqlite:db.db");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean insertLink(String url){
        PreparedStatement ps;
        try {
            ps = this.con.prepareStatement("insert into linkOttenuti(link) values (?);");
            ps.setString(1, url);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            if (ps.executeUpdate()> 0){
                this.insertLogger("inserimento");
            }

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        try {
                ps.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void insertImmagini(String url){
        try {
            String q = "insert into immaginiOttenute(nome) values (?);";
            PreparedStatement ps = this.con.prepareStatement(q);
            ps.setString(1, url);
            int n = ps.executeUpdate();
            if (n > 0){
                Adapter adapter = new Adapter();
                adapter.insertLogger("inserimento");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertLogger(String op){
        try {
            String q = "insert into logger (operazione) values (op);";
            PreparedStatement ps = this.con.prepareStatement(q);
            int n = ps.executeUpdate();
            if (n > 0){


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateLink(int codeR, String link){
        try {
            String q = "update linkOttenuti set link = ? where codeR1 = ?;";
            PreparedStatement ps = this.con.prepareStatement(q);
            ps.setString(1, link);
            ps.setInt(2, codeR);
            int n = ps.executeUpdate();
            if (n > 0){
                Adapter adapter = new Adapter();
                adapter.insertLogger("aggiornamento");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void updateImmagini(int codeR, String titolo){
        try {
            String q = "update immaginiOttenute set titolo = ? where codeR2 = ?;";
            PreparedStatement ps = this.con.prepareStatement(q);
            ps.setString(1, titolo);
            ps.setInt(2, codeR);
            int n = ps.executeUpdate();
            if (n > 0){
                Adapter adapter = new Adapter();
                adapter.insertLogger("aggiornamento");

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteImmagini(int codeR){
        try {
            String q = "delete from immaginiOttenute where codeR2 = codeR;";
            PreparedStatement ps = this.con.prepareStatement(q);
            int n = ps.executeUpdate();
            if (n > 0){
                Adapter adapter = new Adapter();
                adapter.insertLogger("cancellazione");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteLink(int codeR){
        try {
            String q = "delete from linkOttenuti where codeR1 = codeR;";
            PreparedStatement ps = this.con.prepareStatement(q);
            int n = ps.executeUpdate();
            if (n > 0){
                Adapter adapter = new Adapter();
                adapter.insertLogger("cancellazione");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.connection();
        adapter.insertLink("wikipedia");
    }



    //update
    //delete
    //seconda con operazione effetutata, 3 optional attrib aggiuntivo che puo' essere anche nulll
    /*
    operazioni scre
    ritorno nome sito e link sito
    q.selector

    lista di link
    mappa con nome immagine  e url immagine

    enum operation strinv valoreoperazione e le altre de stringhe le controlliamo con is empity-no


     */
}
