package testDomain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import bean.User;
import impl.GoodsDaoImp;
import impl.UserServiceImp;
import utils.JdbcUtil;

public class UserQueryTest {
	public static void main(String[] args) {
/*		JdbcUtil jdbcutil = new JdbcUtil();
		jdbcutil.getConnection();
		UserServiceImp userService = new UserServiceImp();
//		boolean flag = userService.verifyUser("admin", "admin");
		
//		User user = new User(null,"test1","test1","agaga@qq.com");
//		
//		userService.saveUser(user);

//		try {
//			List<Map<String,Object>> result = jdbcutil.findResult(
//					"select * from user", null);
//			for(Map<String,Object> m:result) {
//				System.out.println(m);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			jdbcutil.releaseConnection();
//		}
*/		GoodsDaoImp goodsDao = new GoodsDaoImp();
		List<String> list = goodsDao.getGoodsName();
		System.out.println(list.toString());

		
		
		
		
		
	}
}
