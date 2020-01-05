private void formWindowOpened(java.awt.event.WindowEvent evt) {                                 
        // TODO add your handling code here:
        this.btAdd.setEnabled(true);
        this.btDelete.setEnabled(false);
        this.btSave.setEnabled(false);


        try {
            Statement a = cn.createStatement();
            ResultSet re = a.executeQuery("select fldTenMH from tblMonHoc");

            String abc = "";
            while (re.next()) {
                abc = re.getString("fldTenMH");

                cbTenMH.addItem(abc);

            }
            re.close();
            a.close();
        } catch (Exception e) {
        }
        try {

            String sql2 = "select * from tblGiangVien";
            PreparedStatement se = cn.prepareStatement(sql2);
            ResultSet re = se.executeQuery();
            String strHeader[] = {"MaGV", "HoGV", "TenGV", "MaMH", "NgaySinh", "Email", "GioiTinh", "DiaChi"};
            dt = new DefaultTableModel(strHeader, 0);
            while (re.next()) {
                String strMaGV = "" + re.getString("fldMaGV");
                String strHoGV = "" + re.getString("fldHoGV");
                String strTenGV = "" + re.getString("fldTenGV");
                String strMaMH = "" + re.getString("fldMaMH");
                String strNgaySinh = "" + re.getString("fldNgaySinh");
                String strEmail = "" + re.getString("fldEmail");
                String strGioiTinh = "" + re.getString("fldGioiTinh");
                String strDiaChi = "" + re.getString("fldDiaChi");

                String strelement[] = {strMaGV, strHoGV, strTenGV, strMaMH, strNgaySinh, strEmail, strGioiTinh, strDiaChi};
                dt.addRow(strelement);
            }
            tblGiangVien.setModel(dt);
            re.close();
            se.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
    }                                