package vnua.fita.bookstore.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import vnua.fita.bookstore.bean.Cart;
import vnua.fita.bookstore.bean.CartItem;
import vnua.fita.bookstore.bean.User;

public class MyUtil {
	public static void storeLoginedUser(HttpSession session, User loginedUser) {
		session.setAttribute(Constant.LOGINED_USER, loginedUser);
	}
	
	public static User getLoginedUser(HttpSession session) {
		User user=(User) session.getAttribute(Constant.LOGINED_USER);
		return user;
	}

	public static void storeUserCookie(HttpServletResponse response, User user) {
		System.out.println("Store user cookie");
		Cookie cookieUsername=new Cookie(Constant.USERNAME_STORE_IN_COOKIE_OF_BOOKSTORE, user.getUsername());
		cookieUsername.setMaxAge(24*60*60);
		response.addCookie(cookieUsername);
		
		Cookie cookieToken=new Cookie(Constant.TOKEN_STORE_IN_COOKIE_OF_BOOKSTORE, MyUtil.createTokenFromUserInfo(user));
		cookieUsername.setMaxAge(24*60*60);
		response.addCookie(cookieToken);
	}
	
	public static String getUsernameInCookie(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(Constant.USERNAME_STORE_IN_COOKIE_OF_BOOKSTORE.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	public static String getTokenInStore(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(Constant.TOKEN_STORE_IN_COOKIE_OF_BOOKSTORE.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static String createTokenFromUserInfo(User user) {
		// TODO Auto-generated method stub
		return user.getUsername()+Constant.SECRET_STRING+user.getPassword();
	}

	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUsername=new Cookie(Constant.USERNAME_STORE_IN_COOKIE_OF_BOOKSTORE,null);
		cookieUsername.setMaxAge(0);
		response.addCookie(cookieUsername);
		
		Cookie cookieToken=new Cookie(Constant.TOKEN_STORE_IN_COOKIE_OF_BOOKSTORE,null);
		cookieUsername.setMaxAge(0);
		response.addCookie(cookieToken);
	}

	public static String getPathInfoFromServlet(String path) {
		if (path == null || path.isEmpty()) {
			return ""; // Hoặc có thể ném một ngoại lệ
		}

		String[] result = path.split("/");
		if (result.length == 0) {
			return "";
		}

		return result[result.length - 1];
	}

	public static void storeCart(HttpSession session, Cart cart) {
		session.setAttribute(Constant.CART_OF_CUSTOMER, cart);
	}

	public static Cart getCartOfCustomer(HttpSession session) {
		Cart cart = (Cart) session.getAttribute(Constant.CART_OF_CUSTOMER);
		return cart;
	}

	public static void updateCartOfCustomer(HttpSession session, Map<Integer, CartItem> cartItemList) {
		Cart cart = getCartOfCustomer(session);
		cart.setCartItemList(cartItemList);
		session.setAttribute(Constant.CART_OF_CUSTOMER, cart);
	}

	public static void deleteCartOfCustomer(HttpSession session, Map<Integer, CartItem> cartItemList) {
		session.removeAttribute(Constant.CART_OF_CUSTOMER);
	}

	public static String getTimeLabel() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh_mm");
		return sdf.format(new Date());
	}

	public static String createOrderNo(int orderId) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		int code = orderId % 100;
		return sdf.format(new Date()) + code;
	}

	public static String extracFileExtension(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		int indexOfDot = contentDisp.lastIndexOf(".");
		return contentDisp.substring(indexOfDot, contentDisp.length() - 1);
	}

	public static File getFolderUpload(String appPath, String folderName) {
		File folderUpload = new File(appPath + File.separator + folderName);
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}

	public static String convertDateToString(Date date) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}