package vn.iotstar.utils;

public class Constant {
    public static final String SESSION_USERNAME = "username";
    public static final String COOKIE_REMEMBER = "username";
    

    public static final String UPLOAD_DIRECTORY = "D:\\upload";
    public static final String DEFAULT_FILENAME = "default.file";

    public static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public static class Path {
        public static final String LOGIN = "/views/login.jsp";
        public static final String REGISTER = "/views/register.jsp";
        public static final String HOME = "/views/web/index.jsp";
        public static final String DASHBOARD = "/views/admin/login.jsp";
        public static final String MYACCOUNT = "/views/web/myaccount.jsp";
        // Thêm các đường dẫn mới cho category
        public static final String USER_HOME = "/views/user-home.jsp";
        public static final String MANAGER_HOME = "/views/manager-home.jsp";
        public static final String ADMIN_CATEGORIES = "/views/admin/category-list.jsp";
        public static final String CATEGORY_ADD = "/views/admin/category-add.jsp";
        public static final String CATEGORY_EDIT = "/views/admin/category-edit.jsp";
    }
}