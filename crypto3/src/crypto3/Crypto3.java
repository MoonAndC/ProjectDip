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
  
    
// ��������� �����
public class Encryptor {
    public static String encrypt(String key, String initVector, String value) { // ��������� � �������� ����������: ����, ���������������� ������ � ������ ������� ����� ����������
        try { // ���� ������������ ��� ��������� ���������� 9�� ���� �������������� ��������. try ���������� ����  � ������� ����� ���������� ����������
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8")); // ������������� ����������������� �������
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); // ��������� ���������� ����������: ��������, ������� ����������, ����� ��������� ������ ���������� � �������� ���������� �� ������ ����� ������
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv); // ������������� ��������� (��������������)

            byte[] encrypted = cipher.doFinal(value.getBytes()); //������������ ����� � ���� ������
            System.out.println("������������� ������: "
                    + Base64.toBase64String(encrypted)); //���������� �������������� �� ��������� ���� � ����� � ������� ASCII

            return Base64.toBase64String(encrypted); //��������� ������������� ������ � ���� ���������� ������
        } catch (Exception ex) { //� ���� ����� ���������� ��������� ����������
            ex.printStackTrace(); // � ������ ������������� ����������� ������, ������ ����� ��������� ������� ��� ��� ���� ��������
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try { // ���� ������������ ��� ��������� ���������� 9�� ���� �������������� ��������. try ���������� ����  � ������� ����� ���������� ����������
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8")); //�������������� ����������������� ������� � ������������������ ������
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES"); //���������� ��������� ���� ���������� �� �����������

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); // ��������� ���������� ������������: ��������, ������� ����������, ����� ��������� ������ ���������� � �������� ���������� �� ������ ����� ������
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv); // ������������� ��������� (������������)

            byte[] original = cipher.doFinal(Base64.decode(encrypted)); //������ ������ ������������

            return new String(original);
        } catch (Exception ex) { //� ���� ����� ���������� ��������� ����������
            ex.printStackTrace();// � ������ ������������� ����������� ������, ������ ����� ��������� ������� ��� ��� ���� ��������
        }

        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
    	String key, initVector;
        key = "�������"; // 128-������� ���� (16 �������� �� 8 ���,  ��� � ����� - 128)
        initVector = "ParovozParovoz++�������"; // 16-������� ���������������� ������ (16 �������� �� 8 ���,  ��� � ����� - 128)
        initVector = Shifr_Pl.Shiphr(initVector);
        key = Shifr_Pl.Shiphr(key);
        byte[] keyBytes = key.getBytes("UTF-8");
        System.out.println("����� ����� � ������ "+keyBytes.length);
        byte[] vectorBytes = initVector.getBytes("UTF-8");
        System.out.println("����� ������� � ������ "+vectorBytes.length);
      
        if (keyBytes.length < 16) System.out.println("� ���������, ����� ����� � ������ ������������, ���������� �������� ����");
      
        if (vectorBytes.length < 16) System.out.println("� ���������, ����� ������� � ������ ������������, ���������� �������� ������");
  
        if (vectorBytes.length == 16 & keyBytes.length == 16) {       	
        	System.out.println(decrypt(key, initVector,
        			encrypt(key, initVector, "������� �� ������"))); //�����
            } 
        /*
        String s_text, r_text;
        String text = " ";
        if (vectorBytes.length == 16 & keyBytes.length == 16) {
        s_text = decrypt(key, initVector,text);
        System.out.println("������������� �����: " + s_text);
        r_text = encrypt(key, initVector, s_text);
        System.out.println("�������������� �����: " + r_text); 
        
        	//System.out.println(decrypt(key, initVector,
        		//	encrypt(key, initVector, "������� �� ������"))); //�����
            } 
        
        */
  	
    	}
	}	
}