/**
 * Copyright(C) 2019 Luvina Software
 * BaseDao.java, Dec 27, 2019, MDung
 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author MDung
 *
 */
public interface BaseDao {
	/**
	 * Hàm kết nối đến DB
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void openConnect() throws SQLException, ClassNotFoundException;

	/**
	 * Hàm đóng kết nối với DB
	 * 
	 * @throws SQLException
	 */
	public void closeConnect() throws SQLException;

	/**
	 * set giá trị cho connection
	 * 
	 * @param connect
	 *            conection kết nối với DB
	 */
	public void setConnect(Connection connect);

	/**
	 * Lấy connection
	 * 
	 * @return connect kết nối với DB
	 */
	Connection getConnect();

	/**
	 * Phương thức commit dữ liệu vào DB
	 * 
	 * @throws SQLException
	 *             Ngoại lệ sai lệnh SQl
	 * @throws ClassNotFoundException
	 *             Ngoại lệ không tìm thấy class
	 * 
	 */
	public void commit() throws SQLException;

	/**
	 * Phương thức rollback lại dữ liệu khi bị lỗi
	 * 
	 * @throws ClassNotFoundExceptionn
	 *             ngoại lệ không tìm thấy class
	 * @throws SQLException
	 *             ngoại lệ sai lệnh SQl
	 * 
	 */
	public void rollback() throws SQLException;

	/**
	 * Phương thức dùng để set giá trị để không tự động commit lên DB
	 * 
	 * @param isCommit
	 *            giá trị truyền vào (false để ngắt tự động commit, true để bật
	 *            tự động commit)
	 * @throws SQLException
	 *             ngoại lệ sai lệnh SQl
	 * @throws ClassNotFoundException
	 *             ngoại lệ khong tìm thấy class
	 */
	public void setAutoCommit(boolean isCommit) throws SQLException;
}
