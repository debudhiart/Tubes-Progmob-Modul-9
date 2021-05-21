package id.aryad.sipasar;

import java.util.ArrayList;

public class AdminData {

    ArrayList<AdminModel> adminList = new ArrayList<AdminModel>();

    public AdminData() {
        adminList.add(new AdminModel(4,4,0,"bagasg", "000", "manager"));
        adminList.add(new AdminModel(1,1,1,"budhi", "123", "manager"));
        adminList.add(new AdminModel(2,2,1,"dwngakan", "456", "admin"));
        adminList.add(new AdminModel(3,3,1,"andikap", "789", "pegawai"));
    }

    public ArrayList<AdminModel> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<AdminModel> adminList) {
        this.adminList = adminList;
    }
}
