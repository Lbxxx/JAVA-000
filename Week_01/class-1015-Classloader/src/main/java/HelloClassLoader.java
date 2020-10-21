import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class clazz = new HelloClassLoader().findClass("Hello");
            Object object = clazz.newInstance();
            clazz.getMethod("hello").invoke(object);  //调用方法
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException{
        byte[] bytes = null;
        try {
            InputStream is = getClass().getResourceAsStream("/Hello.xlass");
            if(is != null){
                bytes = IOUtils.toByteArray(is);
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (0xFF - bytes[i]);
                }
            }

//            File file = new File(getClass().getResource("/Hello.xlass").getPath());
//            int length = (int)file.length();
//            bytes = new byte[length];
//            new FileInputStream(file).read(bytes);
//            for (int i = 0; i < bytes.length; i++) {
//                bytes[i] = (byte) (0xFF - bytes[i]);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  验证
//        try (InputStream is = getClass().getResourceAsStream("/Hello.class")){
//            if(is != null){
//                bytes = IOUtils.toByteArray(is);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return this.defineClass(name, bytes, 0, bytes.length);
    }


}
