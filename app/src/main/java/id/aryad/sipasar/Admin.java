package id.aryad.sipasar;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class Admin {
    int status_login;
    int id_manager;
    AdminData adminData = new AdminData();

//    ArrayList<AdminModel> adminList = new ArrayList<AdminModel>();
    public int status_login(String username, String password){
//        adminList.add(new AdminModel(4,4,0,"bagasg", "000", "manager"));
//        adminList.add(new AdminModel(1,1,1,"budhi", "123", "manager"));
//        adminList.add(new AdminModel(2,2,1,"dwngakan", "456", "admin"));
//        adminList.add(new AdminModel(3,3,1,"andikap", "789", "pegawai"));

        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
            //Login tidak mengisi data dengan benar
            status_login = 0;
        }else {
            for (int i = 0 ; i <adminData.getAdminList().size() ; i++){
                if(username.equals(adminData.getAdminList().get(i).username) && password.equals(adminData.getAdminList().get(i).password)){
                    if (1 == (adminData.getAdminList().get(i).status)){
                        if ("manager".equals(adminData.getAdminList().get(i).role)){
                            //Login berhasil
                            id_manager = adminData.getAdminList().get(i).id_admin;
//                            Log.d("key", String.valueOf(id_manager));
                            status_login = 1;
                            break;
                        }else {
                            //Login tidak sebagai manager
                            status_login = 2;
                        }
                        break;
                    }else {
                        //Login user sudah tidak aktif
                        status_login = 3;
                    }
                    break;
                }else{
                    status_login = 4;
                }
            }
        }
        return status_login;
    }

    public int getIdManager(String username, String password){
        int adminId =0;
        for(int i=0;i<adminData.getAdminList().size();i++){

            if(adminData.getAdminList().get(i).username.equals(username) && adminData.getAdminList().get(i).password.equals(password)){
                adminId = adminData.getAdminList().get(i).id_admin;
            }
        }
        return adminId;
    }
//    public int getId_manager() {
//        return id_manager;
//    }
}
