package kai.template.utils.safe;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法加密
 */
public class Md5Util {

    /**
     * 小写结果MD5
     *
     * @param info
     * @return
     */
    public static String getMd5Lower(String info) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = info.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    /**
     * 大写结果MD5
     *
     * @param info
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getMd5Upper(String info) {

        StringBuffer hexString = null;
        byte[] defaultBytes = info.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                if (Integer.toHexString(0xFF & messageDigest[i]).length() == 1) {
                    hexString.append(0);
                }
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            messageDigest.toString();

            info = hexString + "";
        } catch (NoSuchAlgorithmException nsae) {

        }

        return hexString.toString().toUpperCase();

    }

    /**
     * 加盐值加密
     *
     * @param info 加密信息
     * @param salt 盐值
     * @return
     */
    public static String getMd5Salt(String info, String salt) {
        return Md5Util.getMd5Lower(info + "{" + salt + "}");
    }


    /**
     * 解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    /**
     * 接口md5加密方法
     *
     * @param source 源字符串
     * @return 加密后的字符
     * @throws NoSuchAlgorithmException
     */
    public static String apiSignMd5(String source) {
        try {
            char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(source.getBytes("UTF-8"));
            byte[] tmp = md.digest();
            char[] str = new char[16 * 2];

            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] eccrypt(String info) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        //使用srcBytes更新摘要  
        md5.update(srcBytes);
        //完成哈希计算，得到result  
        byte[] resultBytes = md5.digest();
        return resultBytes;
    }

    /**
     * 密码箱编号解密
     *
     * @param token 字符串
     * @return String 返回加密字符串
     */
    public static String decrypt(String token) {
        try {
            String name = new String();
            java.util.StringTokenizer st = new java.util.StringTokenizer(token, "I");
            while (st.hasMoreElements()) {
                int asc = Integer.parseInt((String) st.nextElement()) - 27;
                name = name + (char) asc;
            }

            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 密码箱编号加密
     *
     * @param token 字符串
     * @return String 返回加密字符串
     */
    public static String encrypt(String token) {
        try {
            byte[] _ssoToken = token.getBytes("utf-8");
            String name = new String();
            // char[] _ssoToken = ssoToken.toCharArray();
            for (int i = 0; i < _ssoToken.length; i++) {
                int asc = _ssoToken[i];
                _ssoToken[i] = (byte) (asc + 27);
                name = name + (asc + 27) + "I";
            }
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hexString(byte[] bytes) {
        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
		System.out.println(Md5Util.getMd5Salt("kai", "123456"));
//		System.out.println(convertMD5("Kai123456"));

//        String msg = "123456";
//        byte[] resultBytes = null;
//        try {
//            resultBytes = eccrypt(msg);
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println("明文是：" + msg);
//        System.out.println("密文是：" + hexString(resultBytes));
//
//
//        String inStr = "dept";
//        System.out.println("小写：" + Md5Util.getMd5Lower(inStr));
//        System.out.println("大写：" + Md5Util.getMd5Upper(inStr));
//        System.out.println(Md5Util.getMd5Lower("123456{dept}"));
//        System.out.println(Md5Util.getMd5Upper("II4vMCqu{admin}"));
//		System.out.println(Md5Util.getMd5Salt("kai", "123456"));
//
//        String convertInfo = convertMD5(inStr);
//        System.out.println("加密的：" + convertInfo);
//        System.out.println("解密的：" + convertMD5(convertInfo));
//
//        System.out.println("apiSignMd5:" + apiSignMd5("123"));
//        System.out.println(apiSignMd5("7606cd21532922466fe19dbdb98867c4" + apiSignMd5("A1" + "api")));
//
//        System.out.println(encrypt("MH08951"));
//        System.out.println(decrypt(encrypt("MH00001")));
    }
}
