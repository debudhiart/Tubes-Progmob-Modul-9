package id.aryad.sipasar;

public class AdminModel {
    int id_admin, id_pegawai, status;
    String username, password, role;

    public AdminModel(int id_admin, int id_pegawai, int status, String username, String password, String role){
        this.id_admin = id_admin;
        this.id_pegawai = id_pegawai;
        this.status = status;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId_admin() {
        return id_admin;
    }

    public int getId_pegawai() {
        return id_pegawai;
    }

    public int getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
