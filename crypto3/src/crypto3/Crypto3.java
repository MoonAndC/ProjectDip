package crypto3;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;
//import Double_kv;

public class Crypto3 {	
public static final String kod_s = "raven";	
  
    
// шифрующий класс
public class Encryptor {
    public static String encrypt(String key, String initVector, String value) { // принимает в качестве параметров: ключ, инициализирующий вектор и строку которую нужно кодировать
        try { // блок используется для обработки исключений 9по сути непредвиденных ситуаций. try определяет блок  в котором может возникнуть исключение
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8")); // использование инициализирующего вектора
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); // настройка параметров шифрования: алгоритм, который используем, режим сцепления блоков шифртекста и алгоритм дополнения до нкжной длины блоков
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv); // инициализация алгоритма (зашифровывание)

            byte[] encrypted = cipher.doFinal(value.getBytes()); //представляем текст в виде байтов
            System.out.println("Зашифрованная строка: "
                    + Base64.toBase64String(encrypted)); //происходит преобразование из двоичного кода в текст в формате ASCII

            return Base64.toBase64String(encrypted); //сохраняем зашифрованную строку в виде результата метода
        } catch (Exception ex) { //в этом блоке происходит обработка исключения
            ex.printStackTrace(); // в случае возникновения неожиданной ошибки, данный метод конкретно покажет где она была допущена
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try { // блок используется для обработки исключений 9по сути непредвиденных ситуаций. try определяет блок  в котором может возникнуть исключение
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8")); //преобразование инициализирующего вектора в последовательность байтов
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES"); //определяет секретный ключ независимо от праовайдера

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); // настройка параметров дешифрования: алгоритм, который используем, режим сцепления блоков шифртекста и алгоритм дополнения до нужной длины блоков
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv); // инициализация алгоритма (дешифровывка)

            byte[] original = cipher.doFinal(Base64.decode(encrypted)); //массив байтов декодируется

            return new String(original);
        } catch (Exception ex) { //в этом блоке происходит обработка исключения
            ex.printStackTrace();// в случае возникновения неожиданной ошибки, данный метод конкретно покажет где она была допущена
        }

        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
    	String key, initVector;
        key = "йцукенг"; // 128-битовый ключ (16 символов по 8 бит,  что в сумме - 128)
        initVector = "ParovozParovoz++ртатрап"; // 16-битовый инициализирующий вектор (16 символов по 8 бит,  что в сумме - 128)
        initVector = Shifr_Pl.Shiphr(initVector);
        key = Shifr_Pl.Shiphr(key);
        byte[] keyBytes = key.getBytes("UTF-8");
        System.out.println("Длина ключа в байтах "+keyBytes.length);
        byte[] vectorBytes = initVector.getBytes("UTF-8");
        System.out.println("Длина вектора в байтах "+vectorBytes.length);
      
        if (keyBytes.length < 16) System.out.println("К сожалению, длины ключа в байтах недостаточно, попробуйте изменить ключ");
      
        if (vectorBytes.length < 16) System.out.println("К сожалению, длины вектора в байтах недостаточно, попробуйте изменить вектор");
  
        if (vectorBytes.length == 16 & keyBytes.length == 16) {       	
        	System.out.println(decrypt(key, initVector,
        			encrypt(key, initVector, "Никогда не поздно"))); //вывод
            } 
        /*
        String s_text, r_text;
        String text = " ";
        if (vectorBytes.length == 16 & keyBytes.length == 16) {
        s_text = decrypt(key, initVector,text);
        System.out.println("Зашифрованный текст: " + s_text);
        r_text = encrypt(key, initVector, s_text);
        System.out.println("Расшифрованный текст: " + r_text); 
        
        	//System.out.println(decrypt(key, initVector,
        		//	encrypt(key, initVector, "Никогда не поздно"))); //вывод
            } 
        
        */
  	
    	}
	}	
}